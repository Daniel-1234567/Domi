/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Code Technology Studio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.jpom;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.jiangzeyin.common.EnableCommonBoot;
import cn.jiangzeyin.common.spring.SpringUtil;
import cn.jiangzeyin.common.spring.event.ApplicationEventLoad;
import io.jpom.common.Type;
import io.jpom.common.interceptor.IpInterceptor;
import io.jpom.common.interceptor.LoginInterceptor;
import io.jpom.common.interceptor.OpenApiInterceptor;
import io.jpom.common.interceptor.PermissionInterceptor;
import io.jpom.model.data.BackupInfoModel;
import io.jpom.model.data.SystemIpConfigModel;
import io.jpom.service.dblog.BackupInfoService;
import io.jpom.service.system.SystemParametersServer;
import io.jpom.service.user.UserService;
import io.jpom.system.db.DbConfig;
import io.jpom.system.init.InitDb;
import io.jpom.util.StringUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.File;
import java.util.concurrent.Future;

/**
 * jpom ?????????
 *
 * @author jiangzeyin
 * @since 2017/9/14
 */
@SpringBootApplication
@ServletComponentScan
@EnableCommonBoot
public class JpomServerApplication implements ApplicationEventLoad {

    /**
     * ??????
     */
    private static String[] ARGS;

    /**
     * ????????????
     * <p>
     * --rest:ip_config ?????? IP ???????????????
     * <p>
     * --rest:load_init_db ????????????????????????????????????
     * <p>
     * --rest:super_user_pwd ???????????????????????????
     * <p>
     * --recover:h2db ??? h2 ??????????????????????????????????????????????????????
     * <p>
     * --close:super_user_mfa ????????????????????? mfa
     * <p>
     * --backup-h2 ???????????????
     * <p>
     * --import-h2-sql=/xxxx.sql ?????????????????? sql
     * <p>
     * --replace-import-h2-sql=/xxxx.sql ???????????????????????? sql????????????????????????????????????
     * <p>
     * --transform-sql ?????? sql ??????(????????????????????????),???????????? sql ?????????????????????--import-h2-sql=/xxxx.sql???--replace-import-h2-sql=/xxxx.sql
     *
     * @param args ??????
     * @throws Exception ??????
     */
    public static void main(String[] args) throws Exception {
        long time = SystemClock.now();
        ARGS = args;
        //
        JpomApplication jpomApplication = new JpomApplication(Type.Server, JpomServerApplication.class, args);
        jpomApplication
            // ?????????
            .addInterceptor(IpInterceptor.class)
            .addInterceptor(LoginInterceptor.class)
            .addInterceptor(OpenApiInterceptor.class)
            .addInterceptor(PermissionInterceptor.class)
            .run(args);
        // ?????? ip ???????????????
        if (ArrayUtil.containsIgnoreCase(args, "--rest:ip_config")) {
            SystemParametersServer parametersServer = SpringUtil.getBean(SystemParametersServer.class);
            parametersServer.delByKey(SystemIpConfigModel.ID);
            Console.log("Clear IP whitelist configuration successfully");
        }
        //  ???????????????????????????
        if (ArrayUtil.containsIgnoreCase(args, "--rest:super_user_pwd")) {
            UserService userService = SpringUtil.getBean(UserService.class);
            String restResult = userService.restSuperUserPwd();
            if (restResult != null) {
                Console.log(restResult);
            } else {
                Console.log("There is no super administrator account in the system");
            }
        }
        // ????????????????????? mfa
        if (ArrayUtil.containsIgnoreCase(args, "--close:super_user_mfa")) {
            UserService userService = SpringUtil.getBean(UserService.class);
            String restResult = userService.closeSuperUserMfa();
            if (restResult != null) {
                Console.log(restResult);
            } else {
                Console.log("There is no super administrator account in the system");
            }
        }
        Console.log("Time-consuming to start this time???{}", DateUtil.formatBetween(SystemClock.now() - time, BetweenFormatter.Level.MILLISECOND));
    }

    @Override
    public void applicationLoad() {
        DbConfig instance = DbConfig.getInstance();
        if (ArrayUtil.containsIgnoreCase(ARGS, "--rest:load_init_db")) {
            // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
            instance.clearExecuteSqlLog();
        }
        if (ArrayUtil.containsIgnoreCase(ARGS, "--recover:h2db")) {
            // ???????????????????????????????????????????????????????????????????????????????????????????????????
            try {
                instance.recoverDb();
            } catch (Exception e) {
                e.printStackTrace();
                JpomApplication.consoleExit(-2, "Failed to restore database???{}", e.getMessage());
            }
        }
        if (ArrayUtil.containsIgnoreCase(ARGS, "--backup-h2")) {
            // ???????????????
            InitDb.addCallback(() -> {
                Console.log("Start backing up the database");
                BackupInfoService backupInfoService = SpringUtil.getBean(BackupInfoService.class);
                Future<BackupInfoModel> backupInfoModelFuture = backupInfoService.autoBackup();
                try {
                    BackupInfoModel backupInfoModel = backupInfoModelFuture.get();
                    JpomApplication.consoleExit(0, "Complete the backup database, save the path as {}", backupInfoModel.getFilePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    JpomApplication.consoleExit(-2, "Backup database failed???{}", e.getMessage());
                }
                return false;
            });
        }
        String importH2Sql = StringUtil.getArgsValue(ARGS, "import-h2-sql");
        if (StrUtil.isNotEmpty(importH2Sql)) {
            // ????????????
            importH2Sql(importH2Sql);
        }
        String replaceImportH2Sql = StringUtil.getArgsValue(ARGS, "replace-import-h2-sql");
        if (StrUtil.isNotEmpty(replaceImportH2Sql)) {
            // ??????????????????
            InitDb.addBeforeCallback(() -> {
                try {
                    String dbFiles = instance.deleteDbFiles();
                    if (dbFiles != null) {
                        Console.log("Automatically backup data files to {} path", dbFiles);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JpomApplication.consoleExit(-2, "Failed to import according to sql,{}", replaceImportH2Sql);
                }
            });
            // ????????????
            importH2Sql(replaceImportH2Sql);
        }
        //
    }

    private static void importH2Sql(String importH2Sql) {
        InitDb.addCallback(() -> {
            File file = FileUtil.file(importH2Sql);
            String sqlPath = FileUtil.getAbsolutePath(file);
            if (!FileUtil.isFile(file)) {
                JpomApplication.consoleExit(2, "sql file does not exist :{}", sqlPath);
            }
            //
            if (ArrayUtil.containsIgnoreCase(ARGS, "--transform-sql")) {
                DbConfig.getInstance().transformSql(file);
            }
            //
            Console.log("Start importing data:{}", sqlPath);
            BackupInfoService backupInfoService = SpringUtil.getBean(BackupInfoService.class);
            boolean flag = backupInfoService.restoreWithSql(sqlPath);
            if (!flag) {
                JpomApplication.consoleExit(2, "Failed to import according to sql,{}", sqlPath);
            }
            Console.log("Import successfully according to sql,{}", sqlPath);
            return true;
        });
    }
}

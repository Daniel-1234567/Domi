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
package io.jpom.controller.node.manage.file;

import cn.jiangzeyin.common.validator.ValidatorItem;
import io.jpom.common.BaseServerController;
import io.jpom.common.forward.NodeForward;
import io.jpom.common.forward.NodeUrl;
import io.jpom.permission.ClassFeature;
import io.jpom.permission.Feature;
import io.jpom.permission.NodeDataPermission;
import io.jpom.service.node.ProjectInfoCacheService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bwcx_jzy
 * @since 2022/5/11
 */
@RestController
@RequestMapping(value = "/node/manage/file/")
@Feature(cls = ClassFeature.PROJECT_FILE)
@NodeDataPermission(cls = ProjectInfoCacheService.class)
public class ProjectFileBackupController extends BaseServerController {

    /**
     * ??????????????????
     *
     * @param id ??????ID
     * @return list
     */
    @RequestMapping(value = "list-backup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String listBackup(String id) {
        return NodeForward.request(getNode(), getRequest(), NodeUrl.MANAGE_FILE_BACKUP_LIST_BACKUP).toString();
    }

    /**
     * ?????????????????????????????????
     *
     * @param id       ??????
     * @param path     ?????????????????????
     * @param backupId ??????id
     * @return list
     */
    @RequestMapping(value = "backup-item-files", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String backupItemFiles(String id, String path, @ValidatorItem String backupId) {
        return NodeForward.request(getNode(), getRequest(), NodeUrl.MANAGE_FILE_BACKUP_LIST_ITEM_FILES).toString();
    }

    /**
     * ????????????????????????????????? ??????
     *
     * @param id        ??????id
     * @param filename  ?????????
     * @param levelName ????????????
     * @param backupId  ??????id
     */
    @GetMapping(value = "backup-download", produces = MediaType.APPLICATION_JSON_VALUE)
    public void download(String id, @ValidatorItem String backupId, @ValidatorItem String filename, String levelName) {
        NodeForward.requestDownload(getNode(), getRequest(), getResponse(), NodeUrl.MANAGE_FILE_BACKUP_DOWNLOAD);
    }

    /**
     * ????????????
     *
     * @param id        ??????ID
     * @param backupId  ??????ID
     * @param filename  ?????????
     * @param levelName ????????????
     * @return msg
     */
    @RequestMapping(value = "backup-delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteFile(String id, @ValidatorItem String backupId, @ValidatorItem String filename, String levelName) {
        return NodeForward.request(getNode(), getRequest(), NodeUrl.MANAGE_FILE_BACKUP_DELETE).toString();
    }

    /**
     * ??????????????????
     *
     * @param id        ??????ID
     * @param backupId  ??????ID
     * @param type      ?????? clear ????????????
     * @param filename  ?????????
     * @param levelName ??????
     * @return msg
     */
    @RequestMapping(value = "backup-recover", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String recoverFile(String id, @ValidatorItem String backupId, String type, String filename, String levelName) {
        return NodeForward.request(getNode(), getRequest(), NodeUrl.MANAGE_FILE_BACKUP_RECOVER).toString();
    }
}

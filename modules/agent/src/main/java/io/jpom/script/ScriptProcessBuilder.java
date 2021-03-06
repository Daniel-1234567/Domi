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
package io.jpom.script;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.jiangzeyin.common.JsonMessage;
import cn.jiangzeyin.common.spring.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import io.jpom.model.data.NodeScriptModel;
import io.jpom.model.system.WorkspaceEnvVarModel;
import io.jpom.service.system.AgentWorkspaceEnvVarService;
import io.jpom.socket.ConsoleCommandOp;
import io.jpom.system.ExtConfigBean;
import io.jpom.util.CommandUtil;
import io.jpom.util.SocketSessionUtil;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ????????????
 *
 * @author jiangzeyin
 * @since 2019/4/25
 */
@Slf4j
public class ScriptProcessBuilder extends BaseRunScript implements Runnable {
    /**
     * ??????????????????
     */
    private static final ConcurrentHashMap<String, ScriptProcessBuilder> FILE_SCRIPT_PROCESS_BUILDER_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    private final ProcessBuilder processBuilder;
    private final Set<Session> sessions = new HashSet<>();
    private final String executeId;

    private ScriptProcessBuilder(NodeScriptModel nodeScriptModel, String executeId, String args) {
        super(nodeScriptModel.logFile(executeId));
        this.executeId = executeId;

        File scriptFile = nodeScriptModel.getFile(true);
        //
        String script = FileUtil.getAbsolutePath(scriptFile);
        processBuilder = new ProcessBuilder();
        List<String> command = StrUtil.splitTrim(args, StrUtil.SPACE);
        command.add(0, script);
        CommandUtil.paddingPrefix(command);
        log.debug(CollUtil.join(command, StrUtil.SPACE));
        String workspaceId = nodeScriptModel.getWorkspaceId();
        // ??????????????????
        AgentWorkspaceEnvVarService workspaceService = SpringUtil.getBean(AgentWorkspaceEnvVarService.class);
        WorkspaceEnvVarModel item = workspaceService.getItem(workspaceId);
        if (item != null) {
            Map<String, WorkspaceEnvVarModel.WorkspaceEnvVarItemModel> varData = item.getVarData();
            if (varData != null) {
                Map<String, String> envMap = CollStreamUtil.toMap(varData.values(), WorkspaceEnvVarModel.WorkspaceEnvVarItemModel::getName, WorkspaceEnvVarModel.WorkspaceEnvVarItemModel::getValue);
                Map<String, String> environment = processBuilder.environment();
                environment.putAll(envMap);
            }
        }
        processBuilder.redirectErrorStream(true);
        processBuilder.command(command);
        processBuilder.directory(scriptFile.getParentFile());
    }

    /**
     * ???????????? ?????????
     *
     * @param nodeScriptModel ????????????
     * @param executeId       ??????ID
     * @param args            ??????
     */
    public static ScriptProcessBuilder create(NodeScriptModel nodeScriptModel, String executeId, String args) {
        return FILE_SCRIPT_PROCESS_BUILDER_CONCURRENT_HASH_MAP.computeIfAbsent(executeId, file1 -> {
            ScriptProcessBuilder scriptProcessBuilder1 = new ScriptProcessBuilder(nodeScriptModel, executeId, args);
            ThreadUtil.execute(scriptProcessBuilder1);
            return scriptProcessBuilder1;
        });
    }

    /**
     * ???????????? ?????????
     *
     * @param nodeScriptModel ????????????
     * @param executeId       ??????ID
     * @param args            ??????
     * @param session         ??????
     */
    public static void addWatcher(NodeScriptModel nodeScriptModel, String executeId, String args, Session session) {
        ScriptProcessBuilder scriptProcessBuilder = create(nodeScriptModel, executeId, args);
        //
        if (scriptProcessBuilder.sessions.add(session)) {
            if (FileUtil.exist(scriptProcessBuilder.logFile)) {
                // ??????????????????????????????
                FileUtil.readLines(scriptProcessBuilder.logFile, CharsetUtil.CHARSET_UTF_8, (LineHandler) line -> {
                    try {
                        SocketSessionUtil.send(session, line);
                    } catch (IOException e) {
                        log.error("??????????????????", e);
                    }
                });
            }
        }
    }

    /**
     * ???????????????????????????
     *
     * @param executeId ??????id
     * @return true ????????????
     */
    public static boolean isRun(String executeId) {
        return FILE_SCRIPT_PROCESS_BUILDER_CONCURRENT_HASH_MAP.containsKey(executeId);
    }

    /**
     * ????????????
     *
     * @param session ??????
     */
    public static void stopWatcher(Session session) {
        Collection<ScriptProcessBuilder> scriptProcessBuilders = FILE_SCRIPT_PROCESS_BUILDER_CONCURRENT_HASH_MAP.values();
        for (ScriptProcessBuilder scriptProcessBuilder : scriptProcessBuilders) {
            Set<Session> sessions = scriptProcessBuilder.sessions;
            sessions.removeIf(session1 -> session1.getId().equals(session.getId()));
        }
    }

    /**
     * ??????????????????
     *
     * @param executeId ??????ID
     */
    public static void stopRun(String executeId) {
        ScriptProcessBuilder scriptProcessBuilder = FILE_SCRIPT_PROCESS_BUILDER_CONCURRENT_HASH_MAP.get(executeId);
        if (scriptProcessBuilder != null) {
            scriptProcessBuilder.end("????????????");
        }
    }

    @Override
    public void run() {
        //?????????ProcessBuilder??????
        try {
            this.handle("start execute:" + DateUtil.now());
            process = processBuilder.start();
            {
                inputStream = process.getInputStream();
                IoUtil.readLines(inputStream, ExtConfigBean.getInstance().getConsoleLogCharset(), (LineHandler) ScriptProcessBuilder.this::handle);
            }
            int waitFor = process.waitFor();
            JsonMessage<String> jsonMessage = new JsonMessage<>(200, "????????????:" + waitFor);
            JSONObject jsonObject = jsonMessage.toJson();
            jsonObject.put("op", ConsoleCommandOp.stop.name());
            this.end(jsonObject.toString());
            this.handle("execute done:" + waitFor + " time:" + DateUtil.now());
        } catch (Exception e) {
            log.error("????????????", e);
            this.end("???????????????" + e.getMessage());
        } finally {
            this.close();
        }
    }

    /**
     * ????????????
     *
     * @param msg ???????????????
     */
    @Override
    protected void end(String msg) {
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            try {
                SocketSessionUtil.send(session, msg);
            } catch (IOException e) {
                log.error("??????????????????", e);
            }
            iterator.remove();
        }
        FILE_SCRIPT_PROCESS_BUILDER_CONCURRENT_HASH_MAP.remove(this.executeId);
    }

    /**
     * ??????
     *
     * @param line ??????
     */
    @Override
    protected void handle(String line) {
        super.handle(line);
        //
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            try {
                SocketSessionUtil.send(session, line);
            } catch (IOException e) {
                log.error("??????????????????", e);
                iterator.remove();
            }
        }
    }
}

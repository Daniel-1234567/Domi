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
package io.jpom.common.commander.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.text.StrSplitter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import com.alibaba.fastjson.JSONObject;
import io.jpom.common.commander.AbstractSystemCommander;
import io.jpom.model.system.ProcessModel;
import io.jpom.util.CommandUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author User
 */
@Slf4j
public class MacOsSystemCommander extends AbstractSystemCommander {

	@Override
	public JSONObject getAllMonitor() {
		String result = CommandUtil.execSystemCommand("top -l 1 | head");
		if (StrUtil.isEmpty(result)) {
			return null;
		}
		String[] split = result.split(StrUtil.LF);
		int length = split.length;
		JSONObject jsonObject = new JSONObject();
		// cpu ?????? 4 ??????????????? 3
		if (length > 3) {
			String cpus = split[3];
			// cpu??????
			String cpu = getLinuxCpu(cpus);
			jsonObject.put("cpu", cpu);
		}
		// ???????????? ??? 7 ??? ????????? 6
		if (length > 6) {
			String mem = split[6];
			//????????????
			String memory = getLinuxMemory(mem);
			jsonObject.put("memory", memory);
		}
		jsonObject.put("disk", getHardDisk());
		log.info("Mac OS monitor data: {}", jsonObject.toJSONString());
		return jsonObject;
	}

	/**
	 * ????????????????????????
	 * ???????????????????????? Mac OS ???????????????????????????????????????????????????????????????
	 * ???????????????????????????????????????????????????
	 *
	 * @param info
	 * @return ???????????? / ????????? * 100%
	 */
	private String getLinuxMemory(final String info) {
		if (StrUtil.isEmpty(info)) {
			return null;
		}
		double used = 0, free = 0;
		log.debug("Mac Os mem info: {}", info);
		int index = info.indexOf(CharPool.COLON) + 1;
		String[] split = info.substring(index).split(StrUtil.COMMA);
		for (String str : split) {
			str = str.trim();
			if (str.contains("unused.")) {
				String value = str.split("\\s+")[0].replace("M", "");
				free = Convert.toDouble(value, 0.0);
			} else if (str.contains("used")) {
				String value = str.split("\\s+")[0].replace("M", "");
				used = Convert.toDouble(value, 0.0);
			}
		}
		log.debug("Mac OS mem: used: {}, unused: {}", used, free);
		return String.format("%.2f", used / (used + free) * 100);
	}

	/**
	 * ?????? Mac OS cpu ????????????
	 *
	 * @param info
	 * @return 100 - idle (100 - ????????? cpu)
	 */
	private String getLinuxCpu(final String info) {
		if (StrUtil.isEmpty(info)) {
			return null;
		}
		log.debug("Mac Os cpu info: {}", info);
		int i = info.indexOf(CharPool.COLON);
		String[] split = info.substring(i + 1).split(StrUtil.COMMA);
		for (String str : split) {
			str = str.trim();
			if (str.contains("idle")) {
				String value = str.split("\\s+")[0].replace("%", "");
				double val = Convert.toDouble(value, 0.0);
				return String.format("%.2f", 100.00 - val);
			}
		}
		return "0";
	}

	@Override
	public List<ProcessModel> getProcessList(String processName) {
		String s = CommandUtil.execSystemCommand("top -l 1 | grep " + processName);
		return formatLinuxTop(s, false);
	}

	@Override
	public String emptyLogFile(File file) {
		return CommandUtil.execSystemCommand("cp /dev/null " + file.getAbsolutePath());
	}

	/**
	 * ??? top ??????????????????????????????
	 *
	 * @param top
	 * @param header ????????? header
	 * @return
	 */
	private List<ProcessModel> formatLinuxTop(final String top, final boolean header) {
		List<String> list = StrSplitter.splitTrim(top, StrUtil.LF, true);
		if (list.size() <= 0) {
			return null;
		}
		List<ProcessModel> list1 = new ArrayList<>();
		ProcessModel processModel;
		for (String item : list) {
			processModel = new ProcessModel();
			log.debug("process item: {}", item);
			List<String> values = StrSplitter.splitTrim(item, StrUtil.SPACE, true);
			//log.debug(JSON.toJSONString(values));
			processModel.setPid(Convert.toInt(values.get(0), 0));
			//processModel.setPort(values.get(6));
			processModel.setCommand(values.get(1));
			processModel.setCpu(values.get(2) + "%");
			processModel.setMem(values.get(14) + "%");
			processModel.setStatus(formStatus(values.get(12)));
			processModel.setTime(values.get(3));
			processModel.setRes(values.get(7));
			processModel.setUser(values.get(29));
			list1.add(processModel);
		}
		return list1;
	}

	private String formStatus(final String val) {
		String tempVal = val.toUpperCase();
		String value = "??????";
		if (tempVal.startsWith("S")) {
			value = "??????";
		} else if (tempVal.startsWith("R")) {
			value = "??????";
		} else if (tempVal.startsWith("T")) {
			value = "??????/??????";
		} else if (tempVal.startsWith("Z")) {
			value = "???????????? ";
		} else if (tempVal.startsWith("D")) {
			value = "??????????????????????????? ";
		} else if (tempVal.startsWith("I")) {
			value = "????????? ";
		}
		return value;
	}

	@Override
	public ProcessModel getPidInfo(int pid) {
		String command = "top -l 1 | grep " + pid;
		String internal = CommandUtil.execSystemCommand(command);
		List<ProcessModel> processModels = formatLinuxTop(internal, true);
		if (processModels == null || processModels.isEmpty()) {
			return null;
		}
		return processModels.get(0);
	}

	@Override
	public boolean getServiceStatus(String serviceName) {
		if (StrUtil.startWith(serviceName, StrUtil.SLASH)) {
			String ps = getPs(serviceName);
			return StrUtil.isNotEmpty(ps);
		}
		/**
		 * Mac OS ?????????????????????????????? launchctl list | grep serverName
		 * ??????????????????????????? PID??????????????????????????????????????????????????????????????? "-"
		 * ?????????????????????????????????????????????????????????????????????????????????????????????????????????
		 * ?????????????????????
		 */
		String format = StrUtil.format("service {} status", serviceName);
		String result = CommandUtil.execSystemCommand(format);
		return StrUtil.containsIgnoreCase(result, "RUNNING");
	}

	private String getPs(final String serviceName) {
		String ps = StrUtil.format(" ps -ef |grep -w {} | grep -v grep", serviceName);
		return CommandUtil.execSystemCommand(ps);
	}

	@Override
	public String startService(String serviceName) {
		if (StrUtil.startWith(serviceName, StrUtil.SLASH)) {
			try {
				CommandUtil.asyncExeLocalCommand(FileUtil.file(SystemUtil.getUserInfo().getHomeDir()), serviceName);
				return "ok";
			} catch (Exception e) {
				log.error("????????????", e);
				return "???????????????" + e.getMessage();
			}
		}
		/**
		 * Mac OS ??????????????????????????? launchctl start serverName
		 */
		String format = StrUtil.format("service {} start", serviceName);
		return CommandUtil.execSystemCommand(format);
	}

	@Override
	public String stopService(String serviceName) {
		if (StrUtil.startWith(serviceName, StrUtil.SLASH)) {
			String ps = getPs(serviceName);
			List<String> list = StrUtil.splitTrim(ps, StrUtil.LF);
			if (list == null || list.isEmpty()) {
				return "stop";
			}
			String s = list.get(0);
			list = StrUtil.splitTrim(s, StrUtil.SPACE);
			if (list == null || list.size() < 2) {
				return "stop";
			}
			File file = new File(SystemUtil.getUserInfo().getHomeDir());
			int pid = Convert.toInt(list.get(1), 0);
			if (pid <= 0) {
				return "error stop";
			}
			return kill(file, pid);
		}
		/**
		 * Mac OS ??????????????????????????? launchctl stop serverName
		 */
		String format = StrUtil.format("service {} stop", serviceName);
		return CommandUtil.execSystemCommand(format);
	}

	@Override
	public String buildKill(int pid) {
		return String.format("kill  %s", pid);
	}
}

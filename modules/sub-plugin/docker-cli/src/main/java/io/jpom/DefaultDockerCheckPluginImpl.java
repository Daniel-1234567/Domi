package io.jpom;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Version;
import com.github.dockerjava.core.RemoteApiVersion;
import io.jpom.plugin.IDefaultPlugin;
import io.jpom.plugin.PluginConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * docker 验证 实现
 *
 * @author bwcx_jzy
 * @since 2022/1/26
 */
@PluginConfig(name = "docker-cli:check")
@Slf4j
public class DefaultDockerCheckPluginImpl implements IDefaultPlugin {

	@Override
	public Object execute(Object main, Map<String, Object> parameter) {
		String type = main.toString();
		switch (type) {
			case "certPath":
				return this.checkCertPath(parameter);
			case "apiVersions":
				return getApiVersions();
			case "host":
				return this.checkUrl(parameter);
			case "ping":
				return this.checkPing(parameter);
			case "pullVersion":
				return this.pullVersion(parameter);
			default:
				break;
		}
		return null;
	}

	/**
	 * 获取版本信息
	 *
	 * @param parameter 参数
	 * @return true 可以通讯
	 */
	private Version pullVersion(Map<String, Object> parameter) {
		parameter.putIfAbsent("timeout", 5);
		DockerClient dockerClient = DockerUtil.build(parameter, 1);
		return dockerClient.versionCmd().exec();
	}

	/**
	 * 检查 ping docker 超时时间 5 秒
	 *
	 * @param parameter 参数
	 * @return true 可以通讯
	 */
	private boolean checkPing(Map<String, Object> parameter) {
		try {
			parameter.putIfAbsent("timeout", 5);
			DockerClient dockerClient = DockerUtil.build(parameter, 1);
			dockerClient.pingCmd().exec();
			return true;
		} catch (Exception e) {
			log.warn("检查 docker url 异常 {}", e.getMessage());
			return false;
		}
	}

	/**
	 * 检查 docker url 是否可用
	 *
	 * @param parameter 参数
	 * @return true 可用
	 */
	private boolean checkUrl(Map<String, Object> parameter) {
		String url = (String) parameter.get("host");
		URI dockerHost;
		try {
			dockerHost = URI.create(url);
		} catch (Exception e) {
			return false;
		}
		switch (dockerHost.getScheme()) {
			case "tcp":
			case "unix":
			case "npipe":
				return true;
			default:
				return false;
		}
	}

	/**
	 * 获取支持到所有 api 版本
	 *
	 * @return list
	 */
	private List<JSONObject> getApiVersions() {
		Field[] fields = ReflectUtil.getFields(RemoteApiVersion.class);
		return Arrays.stream(fields).map(field -> {
			boolean aFinal = Modifier.isFinal(field.getModifiers());
			boolean aStatic = Modifier.isStatic(field.getModifiers());
			boolean aPublic = Modifier.isPublic(field.getModifiers());
			if (!aFinal || !aStatic || !aPublic) {
				return null;
			}
			Object fieldValue = ReflectUtil.getFieldValue(null, field);
			if (fieldValue instanceof RemoteApiVersion) {
				return (RemoteApiVersion) fieldValue;
			}
			return null;
		}).filter(apiVersion -> apiVersion != null && apiVersion != RemoteApiVersion.UNKNOWN_VERSION).map(apiVersion -> {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("webVersion", apiVersion.asWebPathPart());
			jsonObject.put("version", apiVersion.getVersion());
			return jsonObject;
		}).collect(Collectors.toList());
	}

	/**
	 * 验证 证书是否满足条件
	 *
	 * @param parameter 参数
	 * @return 都是文件满足条件
	 */
	private boolean checkCertPath(Map<String, Object> parameter) {
		String certPath = (String) parameter.get("certPath");
		Assert.hasText(certPath, "certPath is empty");
		File caPemPath = FileUtil.file(certPath, "ca.pem");
		File keyPemPath = FileUtil.file(certPath, "key.pem");
		File certPemPath = FileUtil.file(certPath, "cert.pem");
		return FileUtil.isFile(caPemPath) && FileUtil.isFile(keyPemPath) && FileUtil.isFile(certPemPath);
	}
}
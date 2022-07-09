<!-- <p align="center">
	<a href="https://Domi.io/"  target="_blank">
	    <img src="https://Domi-docs.keepbx.cn/images/Domi_logo.png" width="400" alt="logo">
	</a>
</p> -->
<p align="center">
	<strong>Simple and lightweight low-intrusive online construction, automatic deployment, daily operation and maintenance, project monitoring software</strong>
</p>

<!-- <p align="center">
	<a target="_blank" href="https://github.com/Daniel-1234567/Domi">
        <img src='https://github.com/Daniel-1234567/Domi/badge/star.svg?theme=gvp' alt='gitee star'/>
    </a>
 	<a target="_blank" href="https://github.com/dromara/Domi">
		<img src="https://img.shields.io/github/stars/dromara/Domi.svg?style=social" alt="github star"/>
    </a>
    <a target="_blank" href="https://github.com/Daniel-1234567/Domi">
        <img src='https://img.shields.io/github/license/dromara/Domi?style=flat' alt='license'/>
    </a>
    <a target="_blank" href="https://github.com/Daniel-1234567/Domi">
        <img src='https://img.shields.io/badge/JDK-1.8.0_40+-green.svg' alt='jdk'/>
    </a>
</p>

<p align="center">
    <a target="_blank" href="https://travis-ci.org/dromara/Domi">
        <img src='https://travis-ci.org/dromara/Domi.svg?branch=master' alt='travis'/>
    </a>
    <a target="_blank" href="https://www.codacy.com/gh/dromara/Domi/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=dromara/Domi&amp;utm_campaign=Badge_Grade">
      <img src="https://app.codacy.com/project/badge/Grade/843b953f1446449c9a075e44ea778336" alt="codacy"/>
    </a>
	<a target="_blank" href="https://Domi-docs.keepbx.cn/images/wx-qrcode-praise.png">
		<img src='https://img.shields.io/badge/%E5%BE%AE%E4%BF%A1%E7%BE%A4(%E8%AF%B7%E5%A4%87%E6%B3%A8%3ADomi)-Domi66-yellowgreen.svg' alt='Domi66 请备注Domi'/>
	</a>
</p>

<p align="center">
	👉 <a target="_blank" href="https://Domi.io/">https://Domi.io/</a> 👈
</p>
<p align="center">
	备用地址：<a target="_blank" href="https://Domi-docs.keepbx.cn">https://Domi-docs.keepbx.cn</a> | <a target="_blank" href="https://Domi.keepbx.cn/">https://Domi.keepbx.cn/</a>
</p> -->

## 😭 Do you have the following pain points in daily development?

- There is no special operation and maintenance in the team, and the development needs to do the operation and maintenance work, and you need to manually build and deploy the project yourself.
- Different projects have different build and deploy commands.
- There are requirements for multi-environment packaging such as development, testing, and production.
- Need to monitor the running status of multiple projects at the same time.
- You need to download the SSH tool to connect to the server remotely.
- Need to download FTP tool to transfer files to server.
- When there are multiple servers, it is inconvenient to synchronize account passwords between different computers.
- I want to use some automation tools, but the performance of the server is too high and it is too troublesome to build.
- I have individual needs for automation tools and want to modify the project myself, but the tools on the market are too complicated.

> 如果是分布式的项目，以上步骤则更加繁琐。
>
> 让 Domi 来帮你解决这些痛点吧！然而，这些只是 Domi 解决的最基础的功能。

### 😁 After using [Domi](https://github.com/Daniel-1234567/Domi)

1. Use a browser to log in to manage projects quickly and easily
2. Convenient user management
    1. User operation monitoring, monitoring designated user designated operation notification in the form of email
    2. Multi-user management, independent user project permissions (upload and delete permissions can be controlled), perfect operation log, use workspace to isolate permissions
    3. Accounts can enable MFA two-step verification to improve account security
3. Real-time viewing of project running status, console log, and project file management in interface form
    1. Modify the project text file online
4. Docker container management, docker swarm cluster management
5. Online SSH terminal, allowing you to easily manage the server without Xshell, FinalShell and other software
    1. Operation and maintenance do not need to know the server password after logging in to the Domi system
    2. Can specify commands that SSH prohibits to execute, avoid executing high-risk commands, and automatically execute command logs
    3. Set the file directory, view and manage the corresponding project files and configuration files online
    4. SSH command template online execution script can also be executed regularly
    5. Modify text files online
    6. Lightweight implementation of a simple "fortress machine" function
6. Use project distribution to get cluster project multi-machine deployment with one click
7. Online build does not need to manually update the upgrade project
    1. Support pulling GIT and SVN repositories
    2. Support container construction (docker)
    3. Support SSH release
    4. Support timing build
    5. Support WebHook trigger build
8. Support online editing of nginx configuration files and automatic reload operations
    1. Manage nginx status, manage SSL certificates
9. Automatic alarm for abnormal project status monitoring, automatic restart attempt
    1. Support email + DingTalk group + WeChat group notification to actively perceive project operation status
10. Node script template + timing execution, expand more possibilities
11. Important path whitelist mode to prevent users from mistakenly operating system files

### 🔔️ Special Reminder

> 1. Some functions in Windows server may cause compatibility problems due to system characteristics. It is recommended to fully test in actual use. Linux currently has good compatibility
> 2. Please install the server and plug-in in different directories, not in the same directory
> 3. To uninstall the Domi plug-in or server, stop the corresponding service first, and then delete the corresponding program files, log folders, and data directory folders.
> 4. The local build depends on the system environment. If the build command needs to use maven or node, the corresponding environment needs to be installed on the server where the project is built. If the corresponding environment of the server has been started and then installed, the environment will take effect only after restarting the server through the command line.
> 5. In Ubuntu/Debian server as the plugin side may fail to add, please create a .bash_profile file in the root directory of the current user
> 6. Downgrade operation is not recommended after upgrading 2.7.x, it will involve data incompatibility
> 7. Since the current 2.x.x version of the plug-in side and the server side mainly use the http protocol for communication, the plug-in side and the server side require network communication, please pay attention when using it.
> 8. Domi 3.0 version has been planned to be updated, please look forward to the birth of the new version

<!-- ### 🗒️ [CHANGELOG](https://github.com/Daniel-1234567/Domi/blob/master/CHANGELOG.md)

升级前必看：[CHANGELOG.md](https://github.com/Daniel-1234567/Domi/blob/master/CHANGELOG.md) -->

## 📥 Install Domi

Domi supports a variety of installation methods to meet the individual needs of different users. You only need to choose one installation method.

### Method 1: 🚀 (recommended) One-click installation (Linux)

#### One-click server installation

> **NOTE: The installed directory is located in the directory where the command was executed!**
>
> ⚠️ Special reminder: When one-click installation, pay attention that the execution command cannot be in the same directory, that is, the server side and the agent side cannot be installed in the same directory!
>
> If you need to modify the path of server data and log storage, please modify the  `Domi.path` parameter in [`extConfig.yml`](https://github.com/Daniel-1234567/Domi/blob/master/modules/server/src/main/resources/bin/extConfig.yml).

```shell
# 仅安装服务端
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/Domi/docs/install.sh && \
	bash install.sh Server

# 仅安装服务端，备用下载地址
yum install -y wget && \
	wget -O install.sh https://Domi-docs.keepbx.cn/docs/install.sh && \
	bash install.sh Server

# 安装服务端和 jdk 环境
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/Domi/docs/install.sh && \
	bash install.sh Server jdk

# 安装服务端和 jdk、maven 环境
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/Domi/docs/install.sh && \
	bash install.sh Server jdk+mvn

# ubuntu
apt install -y wget && \
	wget -O install.sh https://dromara.gitee.io/Domi/docs/install.sh && \
	bash install.sh Server jdk
```

启动成功后，服务端的端口为 `2122`，可通过 `http://127.0.0.1:2122/` 访问管理页面（如果不是本机访问，需要把 127.0.0.1 换成你安装的服务器 IP 地址）。

>如无法访问管理系统，执行命令 `systemctl status firewalld` 检查下是否开启了防火墙 ，如状态栏看到绿色显示 `Active: active (running)` 需要放行 `2122` 端口。
>
>```bash
># 放行管理系统的 2122 端口
>firewall-cmd --add-port=2122/tcp --permanent
># 重启防火墙才会生效
>firewall-cmd --reload
>```
>
>如果在操作系统上放行了端口仍无法访问，并且你使用的是云服务器，请到云服务器后台中检查安全组规则是否放行 2122 端口。
>
>⚠️ 注意： Linux 系统中有多种防火墙：Firewall、Iptables、SELinux 等，再检查防火墙配置时候需要都检查一下。

#### 一键安装插件端

> 如果安装服务端的服务器也需要被管理，在服务端上也需要安装插件端（同一个服务器中可以同时安装服务端和插件端）
>
> ⚠️ 特别提醒：一键安装的时候注意执行命令不可在同一目录下，即 Server 端和 Agent 端不可安装在同一目录下！
>
> 如果需要修改插件端数据、日志存储的路径请修改 [`extConfig.yml`](https://github.com/Daniel-1234567/Domi/blob/master/modules/agent/src/main/resources/bin/extConfig.yml) 文件中 `Domi.path` 配置属性。

```shell
# 仅安装插件端
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/Domi/docs/install.sh && \
	bash install.sh Agent

# 仅安装插件端，备用下载地址
yum install -y wget && \
	wget -O install.sh https://Domi-docs.keepbx.cn/docs/install.sh && \
	bash install.sh Agent

# 安装插件端和 jdk 环境
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/Domi/docs/install.sh && \
	bash install.sh Agent jdk

# ubuntu
apt install -y wget && \
	wget -O install.sh https://dromara.gitee.io/Domi/docs/install.sh && \
	bash install.sh Agent jdk
```

启动成功后，插件端的端口为 `2123`，插件端提供给服务端使用。

### 方式二：📦 容器化安装

> ⚠️ 注意：容器化安装方式需要先安装 docker，[点击跳转docker安装文档](https://Domi.io/pages/b63dc5/)


#### 使用挂载方式存储相关数据（在部分环境可能出现兼容性问题）

```shell
docker pull Domidocker/Domi
mkdir -p /home/Domi-server/log
mkdir -p /home/Domi-server/data
docker run -d -p 2122:2122 \
	--name Domi-server \
	-v /home/Domi-server/log:/usr/local/Domi-server/log \
	-v /home/Domi-server/data:/usr/local/Domi-server/data \
	Domidocker/Domi
```

#### 使用容器卷方式存储相关数据

```shell
docker pull Domidocker/Domi
docker volume create Domi-server-data
docker volume create Domi-server-log
docker run -d -p 2122:2122 \
	--name Domi-server \
	-v Domi-server-data:/usr/local/Domi-server/data \
	-v Domi-server-log:/usr/local/Domi-server/log \
	Domidocker/Domi
```

> 容器化安装仅提供服务端版。由于容器和宿主机环境隔离，而导致插件端的很多功能无法正常使用，因此对插件端容器化意义不大。
>
> 安装docker、配置镜像、自动启动、查找安装后所在目录等可参考文档 [https://Domi.io/pages/b63dc5/](https://Domi.io/pages/b63dc5/)

### 方式三：💾 下载安装

1. 下载安装包 [https://github.com/Daniel-1234567/Domi/attach_files](https://github.com/Daniel-1234567/Domi/attach_files)
2. 解压文件
3. 安装插件端
    1. agent-x.x.x-release 目录为插件端的全部安装文件
    2. 上传到对应服务器（整个目录）
    3. 启动插件端，Windows 环境用 bat 脚本，Linux 环境用 sh 脚本。（如果出现乱码或者无法正常执行，请检查编码格式、换行符是否匹配。）
    4. 插件端默认运行端口：`2123`
4. 安装服务端
    1. server-x.x.x-release 目录为服务端的全部安装文件
    2. 上传到对应服务器（整个目录）
    3. 启动服务端，Windows 环境用 bat 脚本，Linux 环境用 sh 脚本。（如果出现乱码或者无法正常执行，请检查编码格式、换行符是否匹配。）
    4. 服务端默认运行端口：`2122`，访问管理页面：`http://127.0.0.1:2122/`（非本机访问把 127.0.0.1 换成你的服务器 IP 地址）

### 方式四：⌨️ 编译安装


1. 访问 [Domi](https://github.com/Daniel-1234567/Domi) 的码云主页，拉取最新完整代码（建议使用 master 分支）
2. 切换到 `web-vue` 目录，执行 `npm install`（vue 环境需要提前搭建和安装依赖包详情可以查看 web-vue 目录下 README.md）
3. 执行 `npm run build` 进行 vue 项目打包
4. 切换到项目根目录执行：`mvn clean package`
5. 安装插件端
    1. 查看插件端安装包 modules/agent/target/agent-x.x.x-release
    2. 打包上传服务器运行（整个目录）
    3. 启动插件端，Windows 环境用 bat 脚本，Linux 环境用 sh 脚本。（如果出现乱码或者无法正常执行，请检查编码格式、换行符是否匹配。）
    4. 默认运行端口：`2123`
6. 安装服务端
    1. 查看插件端安装包 modules/server/target/server-x.x.x-release
    2. 打包上传服务器运行（整个目录）
    3. 启动服务端，Windows 环境用 bat 脚本，Linux 环境用 sh 脚本。（如果出现乱码或者无法正常执行，请检查编码格式、换行符是否匹配。）
    4. 服务端默认运行端口：`2122`，访问管理页面：`http://127.0.0.1:2122/`（非本机访问把 127.0.0.1 换成你的服务器 IP 地址）

> 也可以使用 `script/release.bat` 或 `script/release.sh` 快速打包。

### 方式五：📦 一键启动 docker-compose

- 无需安装任何环境,自动编译构建

> 需要注意修改 `.env` 文件中的 token 值

```shell
yum install -y git
git clone https://github.com/Daniel-1234567/Domi.git
cd Domi
docker-compose up
```

### 方式六：💻 编译运行

1. 访问 [Domi](https://github.com/Daniel-1234567/Domi) 的码云主页，拉取最新完整代码（建议使用 master 分支，如果想体验新功能可以使用 dev 分支）
2. 运行插件端
    1. 运行 `io.Domi.DomiAgentApplication`
    2. 留意控制台打印的默认账号密码信息
    3. 插件端默认运行端口：`2123`
3. 运行服务端
    1. 运行 `io.Domi.DomiServerApplication`
    2. 服务端默认运行端口：`2122`
4. 构建 vue 页面，切换到 `web-vue` 目录（前提需要本地开发环境有 node、npm 环境）
5. 安装项目 vue 依赖，控制台执行 `npm install`
6. 启动开发模式，控制台执行 `npm run serve`
7. 根据控制台输出的地址访问前端页面：`http://127.0.0.1:3000/`（非本机访问把 127.0.0.1 换成你的服务器 IP 地址）

## 管理 Domi 命令

1. Windows 系统使用 bat 脚本文件。

```
# 服务端管理脚本，按照面板提示输入操作
Server.bat

# 插件端管理脚本，按照面板提示输入操作
Agent.bat
```

> Windows 系统中执行启动后需要根据日志去跟进启动的状态，如果出现乱码请检查或者修改编码格式，Windows 系统中 bat 编码格式推荐为 `GB2312`

2. Linux 系统中使用 sh 脚本文件。

```
# 服务端
Server.sh start     启动Domi服务端
Server.sh stop      停止Domi服务端
Server.sh restart   重启Domi服务端
Server.sh status    查看Domi服务端运行状态
Server.sh create    创建Domi服务端的应用服务（Domi-server）

# 插件端
Agent.sh start     启动Domi插件端
Agent.sh stop      停止Domi插件端
Agent.sh restart   重启Domi插件端
Agent.sh status    查看Domi插件端运行状态
Agent.sh create    创建Domi插件端的应用服务（Domi-agent）
```

## Linux 服务方式管理

> 这里安装服务仅供参考，实际中可以根据需求自定义配置

> 在使用 `Server.sh create`/`Agent.sh create` 成功后
>
> service Domi-server {status | start | stop}
>
> service Domi-agent {status | start | stop}

## ⚙️ Domi 的参数配置

在项目运行的根路径下的 `extConfig.yml` 文件

1. 插件端示例：[`extConfig.yml`](https://github.com/Daniel-1234567/Domi/blob/master/modules/agent/src/main/resources/bin/extConfig.yml)
2. 服务端示例：[`extConfig.yml`](https://github.com/Daniel-1234567/Domi/blob/master/modules/server/src/main/resources/bin/extConfig.yml)

## 💻 演示项目

[https://Domi.keepbx.cn](https://Domi.keepbx.cn)

```   
账号：demo
密码：Domi123
```

> 演示系统有部分功能做了限制，完整功能请自行部署体验
>
> 如果出现登录不上，请联系我们，联系方式在最底部

### 构建案例仓库代码

1. [Jboot 案例代码](https://gitee.com/keepbx/Domi-demo-case/tree/master/jboot-test)
2. [SpringBoot 案例代码(ClassPath)](https://gitee.com/keepbx/Domi-demo-case/tree/master/springboot-test)
3. [SpringBoot 案例代码(Jar)](https://gitee.com/keepbx/Domi-demo-case/tree/master/springboot-test-jar)
4. [node vue 案例代码(antdv)](https://gitee.com/keepbx/Domi-demo-case/tree/master/antdv)
5. [python 案例代码](https://gitee.com/keepbx/Domi-demo-case/tree/master/python)

> nodejs 编译指定目录：

```
yarn --cwd xxxx/ install
yarn --cwd xxxx/ build
```

> maven 编译指定目录：

```
mvn -f xxxx/pom.xml clean package
```

## 📝 常见问题、操作说明

### Github Pages

- [文档主页](https://Domi.io/)
- [FQA](https://Domi.io/pages/FQA/)
- [名词解释](https://Domi.io/pages/FQA/proper-noun/)

### Gitee Pages

- [文档主页](https://Domi-docs.keepbx.cn/)
- [FQA](https://Domi-docs.keepbx.cn/pages/FQA/)
- [名词解释](https://Domi-docs.keepbx.cn/pages/FQA/proper-noun/)

### 实践案例

> 里面有部分图片加载可能比较慢

1. [本地构建 + SSH 发布 java 项目](https://Domi.io/pages/practice/build-java-ssh-release)
2. [本地构建 + 项目发布 node 项目](https://Domi.io/pages/practice/build-node-release)
3. [本地构建 + SSH 发布 node 项目](https://Domi.io/pages/practice/build-node-ssh-release)
4. [本地构建 + 自定义管理 python 项目](https://Domi.io/pages/practice/project-dsl-python)
5. [自定义管理 java 项目](https://Domi.io/pages/practice/project-dsl-java)
6. [管理编译安装的 nginx](https://Domi.io/pages/practice/node-nginx)
7. [管理 docker](https://Domi.io/pages/practice/docker-cli)
8. [容器构建 + 项目发布 java 项目](https://Domi.io/pages/practice/build-docker-java-node-release)

## 🛠️ 整体架构

![Domi-func-arch](https://Domi-docs.keepbx.cn/images/Domi-func-arch.jpg)

## 🔨贡献指南

### 贡献须知

Domi 作为开源项目，离不开社区的支持，欢迎任何人修改和提出建议。贡献无论大小，你的贡献会帮助背后成千上万的使用者以及开发者，你做出的贡献也会永远的保留在项目的贡献者名单中，这也是开源项目的意义所在！

为了保证项目代码的质量与规范，以及帮助你更快的了解项目的结构，请在贡献之前阅读：

- [Domi 贡献说明](https://github.com/Daniel-1234567/Domi/blob/master/CONTRIBUTING.md)
- [中英文排版规范](https://github.com/Daniel-1234567/Domi/blob/dev/typography-specification.md)

### 贡献步骤

1. Fork 本仓库。

2. Fork 后会在你的帐号下多了一个和本仓库一模一样的仓库，把你帐号的仓库 clone 到本地。

   注意替换掉链接中的`分支名`和`用户名`。

   如果是贡献代码，分支名填 `dev`；如果是贡献文档，分支名填 `docs`
   
   ```bash
   git clone -b 分支名 https://gitee.com/用户名/Domi.git
   ```

3. 修改代码/文档，修改后提交上来。

   ```bash
   # 把修改的文件添加到暂存区
   git add .
   # 提交到本地仓库，说明你具体做了什么修改
   git commit -m '填写你做了什么修改'
   # 推送到远程仓库，分支名替换成 dev 或者 docs
   git push origin 分支名
   ```

4. 登录你的仓库，然后会看到一条 PR 请求，点击请求合并，等待管理员把你的代码合并进来。

### 项目分支说明

| 分支     | 说明                                            |
|--------|-----------------------------------------------|
| master | 主分支，受保护分支，此分支不接受 PR。在 dev 分支后经过测试没问题后会合并到此分支。 |
| dev    | 开发分支，接受 PR，PR 请提交到 dev 分支。                    |
| docs   | 项目文档分支，接受 PR，介绍项目功能、汇总常见问题等。                  |

> 目前用到的主要是 dev 和 docs 分支，接受 PR 修改，其他的分支为归档分支，贡献者可以不用管。


## 🐞 交流讨论 、反馈 BUG、提出建议等

1. 快扫描下方左侧微信群二维码和我们一起交流讨论吧！（添加小助手：备注 Domi 进群）
2. 开源项目离不开社区的支持，如果项目帮助到了你，并且想给我们加个餐，欢迎扫描下方右侧[微信收款码赞赏](https://Domi-docs.keepbx.cn/images/wx-qrcode-praise.png)或通过[码云赞赏](https://github.com/Daniel-1234567/Domi)（在项目首页下方点击捐赠，支持微信和支付宝）。[赞赏记录](https://Domi-docs.keepbx.cn/docs/index.html#/praise)
3. 微信公众号：[CodeGzh](https://Domi-docs.keepbx.cn/images/CodeGzh-QrCode.jpg) 查看一些基础教程
4. 反馈 BUG、提出建议，欢迎新建：[issues](https://github.com/Daniel-1234567/Domi/issues)，开发人员会不定时查看回复。
6. 参与贡献，请查看[贡献指南](#🔨贡献指南)。

感谢所有赞赏以及参与贡献的小伙伴，你们的支持是我们不断更新前进的动力！

![wx-qrcode-praise.png](https://Domi-docs.keepbx.cn/images/wx-qrcode-praise.png)

## 🔔 精品项目推荐

| 项目名称          | 项目地址                                                                       | 项目介绍                                          |
|---------------|----------------------------------------------------------------------------|-----------------------------------------------|
| SpringBoot_v2 | [https://gitee.com/bdj/SpringBoot_v2](https://gitee.com/bdj/SpringBoot_v2) | 基于springboot的一款纯净脚手架                          |
| TLog GVP 项目   | [https://gitee.com/dromara/TLog](https://gitee.com/dromara/TLog)           | 一个轻量级的分布式日志标记追踪神器，10分钟即可接入，自动对日志打标签完成微服务的链路追踪 |
| Sa-Token      | [https://gitee.com/dromara/sa-token](https://gitee.com/dromara/sa-token)   | 这可能是史上功能最全的 Java 权限认证框架！                      |
| Erupt         | [https://gitee.com/erupt/erupt](https://gitee.com/erupt/erupt)             | 零前端代码，纯注解开发 admin 管理后台                        |

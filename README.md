<p align="center">
	<a href="https://jpom.io/"  target="_blank">
	    <img src="https://jpom-docs.keepbx.cn/images/jpom_logo.png" width="400" alt="logo">
	</a>
</p>
<p align="center">
	<strong>Simple and lightweight low-intrusive online construction, automatic deployment, daily operation and maintenance, project monitoring software</strong>
</p>

<p align="center">
	<a target="_blank" href="https://gitee.com/dromara/Jpom">
        <img src='https://gitee.com/dromara/Jpom/badge/star.svg?theme=gvp' alt='gitee star'/>
    </a>
 	<a target="_blank" href="https://github.com/dromara/Jpom">
		<img src="https://img.shields.io/github/stars/dromara/Jpom.svg?style=social" alt="github star"/>
    </a>
    <a target="_blank" href="https://gitee.com/dromara/Jpom">
        <img src='https://img.shields.io/github/license/dromara/Jpom?style=flat' alt='license'/>
    </a>
    <a target="_blank" href="https://gitee.com/dromara/Jpom">
        <img src='https://img.shields.io/badge/JDK-1.8.0_40+-green.svg' alt='jdk'/>
    </a>
</p>

<p align="center">
    <a target="_blank" href="https://travis-ci.org/dromara/Jpom">
        <img src='https://travis-ci.org/dromara/Jpom.svg?branch=master' alt='travis'/>
    </a>
    <a target="_blank" href="https://www.codacy.com/gh/dromara/Jpom/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=dromara/Jpom&amp;utm_campaign=Badge_Grade">
      <img src="https://app.codacy.com/project/badge/Grade/843b953f1446449c9a075e44ea778336" alt="codacy"/>
    </a>
	<a target="_blank" href="https://jpom-docs.keepbx.cn/images/wx-qrcode-praise.png">
		<img src='https://img.shields.io/badge/%E5%BE%AE%E4%BF%A1%E7%BE%A4(%E8%AF%B7%E5%A4%87%E6%B3%A8%3AJpom)-jpom66-yellowgreen.svg' alt='jpom66 请备注jpom'/>
	</a>
</p>

<p align="center">
	👉 <a target="_blank" href="https://jpom.io/">https://jpom.io/</a> 👈
</p>
<p align="center">
	备用地址：<a target="_blank" href="https://jpom-docs.keepbx.cn">https://jpom-docs.keepbx.cn</a> | <a target="_blank" href="https://jpom.keepbx.cn/">https://jpom.keepbx.cn/</a>
</p>

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
> 让 Jpom 来帮你解决这些痛点吧！然而，这些只是 Jpom 解决的最基础的功能。

### 😁 使用 [Jpom](https://gitee.com/dromara/Jpom) 后

1. 使用浏览器登录方便快捷管理项目
2. 方便的用户管理
    1. 用户操作监控，监控指定用户指定操作以邮件形式通知
    2. 多用户管理，用户项目权限独立（上传、删除权限可控制），完善的操作日志，使用工作空间隔离权限
    3. 账号可以开启 MFA 两步验证提高账号安全性
3. 界面形式实时查看项目运行状态、控制台日志、管理项目文件
    1. 在线修改项目文本文件
4. docker 容器管理、docker swarm 集群管理
5. 在线 SSH 终端，让您在没有 Xshell、FinalShell 等软件也能轻松管理服务器
    1. 运维登录 Jpom 系统后不需要知道服务器密码
    2. 能指定 SSH 禁止执行的命令，避免执行高风险命令，并且能自动执行命令日志
    3. 设置文件目录，在线查看管理对应项目文件及配置文件
    4. SSH 命令模版在线执行脚本还能定时执行
    5. 在线修改文本文件
    6. 轻量的实现了简单的"堡垒机"功能
6. 使用项目分发一键搞定集群项目多机部署
7. 在线构建不用手动更新升级项目
    1. 支持拉取 GIT、SVN 仓库
    2. 支持容器构建（docker）
    3. 支持 SSH 方式发布
    4. 支持定时构建
    5. 支持 WebHook 形式触发构建
8. 支持在线编辑 nginx 配置文件并自动 reload 等操作
    1. 管理 nginx 状态，管理 SSL 证书
9. 项目状态监控异常自动报警、自动尝试重启
    1. 支持邮件 + 钉钉群 + 微信群通知，主动感知项目运行状况
10. 节点脚本模版+定时执行，拓展更多可能
11. 重要路径白名单模式，杜绝用户误操作系统文件

### 🔔️ 特别提醒

> 1. 在 Windows 服务器中可能有部分功能因为系统特性造成兼容性问题，建议在实际使用中充分测试。Linux 目前兼容性良好
> 2. 服务端和插件端请安装到不同目录中，切勿安装到同一目录中
> 3. 卸载 Jpom 插件端或者服务端，先停止对应服务，然后删除对应的程序文件、日志文件夹、数据目录文件夹即可
> 4. 本地构建依赖的是系统环境，如果构建命令需要使用 maven 或者 node 需要在构建项目的服务器安装好对应的环境。如果已经启动服务端再安装的对应环境需要通过命令行重启服务端后环境才会生效。
> 5. 在 Ubuntu/Debian 服务器作为插件端可能会添加失败，请在当前用户的根目录创建 .bash_profile 文件
> 6. 升级 2.7.x 后不建议降级操作，会涉及到数据不兼容的情况
> 7. 由于目前 2.x.x 版本插件端和服务端主要采用 http 协议通讯，插件端和服务端网络要求互通，在使用的时候请注意。
> 8. Jpom 3.0 版本已经开始规划更新了，尽请期待新版本的诞生吧

### 🗒️ [版本更新日志](https://gitee.com/dromara/Jpom/blob/master/CHANGELOG.md)

升级前必看：[CHANGELOG.md](https://gitee.com/dromara/Jpom/blob/master/CHANGELOG.md)

## 📥 安装 Jpom

Jpom 支持多种安装方式，满足不同用户的个性化需求，您只需要选择一种方式安装即可。

### 方式一：🚀（推荐） 一键安装（Linux）

#### 一键安装服务端

> **注意：安装的目录位于执行命令的目录！**
>
> ⚠️ 特别提醒：一键安装的时候注意执行命令不可在同一目录下，即 Server 端和 Agent 端不可安装在同一目录下！
>
> 如果需要修改服务端数据、日志存储的路径请修改 [`extConfig.yml`](https://gitee.com/dromara/Jpom/blob/master/modules/server/src/main/resources/bin/extConfig.yml) 文件中 `jpom.path` 配置属性。

```shell
# 仅安装服务端
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/jpom/docs/install.sh && \
	bash install.sh Server

# 仅安装服务端，备用下载地址
yum install -y wget && \
	wget -O install.sh https://jpom-docs.keepbx.cn/docs/install.sh && \
	bash install.sh Server

# 安装服务端和 jdk 环境
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/jpom/docs/install.sh && \
	bash install.sh Server jdk

# 安装服务端和 jdk、maven 环境
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/jpom/docs/install.sh && \
	bash install.sh Server jdk+mvn

# ubuntu
apt install -y wget && \
	wget -O install.sh https://dromara.gitee.io/jpom/docs/install.sh && \
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
> 如果需要修改插件端数据、日志存储的路径请修改 [`extConfig.yml`](https://gitee.com/dromara/Jpom/blob/master/modules/agent/src/main/resources/bin/extConfig.yml) 文件中 `jpom.path` 配置属性。

```shell
# 仅安装插件端
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/jpom/docs/install.sh && \
	bash install.sh Agent

# 仅安装插件端，备用下载地址
yum install -y wget && \
	wget -O install.sh https://jpom-docs.keepbx.cn/docs/install.sh && \
	bash install.sh Agent

# 安装插件端和 jdk 环境
yum install -y wget && \
	wget -O install.sh https://dromara.gitee.io/jpom/docs/install.sh && \
	bash install.sh Agent jdk

# ubuntu
apt install -y wget && \
	wget -O install.sh https://dromara.gitee.io/jpom/docs/install.sh && \
	bash install.sh Agent jdk
```

启动成功后，插件端的端口为 `2123`，插件端提供给服务端使用。

### 方式二：📦 容器化安装

> ⚠️ 注意：容器化安装方式需要先安装 docker，[点击跳转docker安装文档](https://jpom.io/pages/b63dc5/)


#### 使用挂载方式存储相关数据（在部分环境可能出现兼容性问题）

```shell
docker pull jpomdocker/jpom
mkdir -p /home/jpom-server/log
mkdir -p /home/jpom-server/data
docker run -d -p 2122:2122 \
	--name jpom-server \
	-v /home/jpom-server/log:/usr/local/jpom-server/log \
	-v /home/jpom-server/data:/usr/local/jpom-server/data \
	jpomdocker/jpom
```

#### 使用容器卷方式存储相关数据

```shell
docker pull jpomdocker/jpom
docker volume create jpom-server-data
docker volume create jpom-server-log
docker run -d -p 2122:2122 \
	--name jpom-server \
	-v jpom-server-data:/usr/local/jpom-server/data \
	-v jpom-server-log:/usr/local/jpom-server/log \
	jpomdocker/jpom
```

> 容器化安装仅提供服务端版。由于容器和宿主机环境隔离，而导致插件端的很多功能无法正常使用，因此对插件端容器化意义不大。
>
> 安装docker、配置镜像、自动启动、查找安装后所在目录等可参考文档 [https://jpom.io/pages/b63dc5/](https://jpom.io/pages/b63dc5/)

### 方式三：💾 下载安装

1. 下载安装包 [https://gitee.com/dromara/Jpom/attach_files](https://gitee.com/dromara/Jpom/attach_files)
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


1. 访问 [Jpom](https://gitee.com/dromara/Jpom) 的码云主页，拉取最新完整代码（建议使用 master 分支）
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
git clone https://gitee.com/dromara/Jpom.git
cd Jpom
docker-compose up
```

### 方式六：💻 编译运行

1. 访问 [Jpom](https://gitee.com/dromara/Jpom) 的码云主页，拉取最新完整代码（建议使用 master 分支，如果想体验新功能可以使用 dev 分支）
2. 运行插件端
    1. 运行 `io.jpom.JpomAgentApplication`
    2. 留意控制台打印的默认账号密码信息
    3. 插件端默认运行端口：`2123`
3. 运行服务端
    1. 运行 `io.jpom.JpomServerApplication`
    2. 服务端默认运行端口：`2122`
4. 构建 vue 页面，切换到 `web-vue` 目录（前提需要本地开发环境有 node、npm 环境）
5. 安装项目 vue 依赖，控制台执行 `npm install`
6. 启动开发模式，控制台执行 `npm run serve`
7. 根据控制台输出的地址访问前端页面：`http://127.0.0.1:3000/`（非本机访问把 127.0.0.1 换成你的服务器 IP 地址）

## 管理 Jpom 命令

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
Server.sh start     启动Jpom服务端
Server.sh stop      停止Jpom服务端
Server.sh restart   重启Jpom服务端
Server.sh status    查看Jpom服务端运行状态
Server.sh create    创建Jpom服务端的应用服务（jpom-server）

# 插件端
Agent.sh start     启动Jpom插件端
Agent.sh stop      停止Jpom插件端
Agent.sh restart   重启Jpom插件端
Agent.sh status    查看Jpom插件端运行状态
Agent.sh create    创建Jpom插件端的应用服务（jpom-agent）
```

## Linux 服务方式管理

> 这里安装服务仅供参考，实际中可以根据需求自定义配置

> 在使用 `Server.sh create`/`Agent.sh create` 成功后
>
> service jpom-server {status | start | stop}
>
> service jpom-agent {status | start | stop}

## ⚙️ Jpom 的参数配置

在项目运行的根路径下的 `extConfig.yml` 文件

1. 插件端示例：[`extConfig.yml`](https://gitee.com/dromara/Jpom/blob/master/modules/agent/src/main/resources/bin/extConfig.yml)
2. 服务端示例：[`extConfig.yml`](https://gitee.com/dromara/Jpom/blob/master/modules/server/src/main/resources/bin/extConfig.yml)

## 💻 演示项目

[https://jpom.keepbx.cn](https://jpom.keepbx.cn)

```   
账号：demo
密码：jpom123
```

> 演示系统有部分功能做了限制，完整功能请自行部署体验
>
> 如果出现登录不上，请联系我们，联系方式在最底部

### 构建案例仓库代码

1. [Jboot 案例代码](https://gitee.com/keepbx/Jpom-demo-case/tree/master/jboot-test)
2. [SpringBoot 案例代码(ClassPath)](https://gitee.com/keepbx/Jpom-demo-case/tree/master/springboot-test)
3. [SpringBoot 案例代码(Jar)](https://gitee.com/keepbx/Jpom-demo-case/tree/master/springboot-test-jar)
4. [node vue 案例代码(antdv)](https://gitee.com/keepbx/Jpom-demo-case/tree/master/antdv)
5. [python 案例代码](https://gitee.com/keepbx/Jpom-demo-case/tree/master/python)

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

- [文档主页](https://jpom.io/)
- [FQA](https://jpom.io/pages/FQA/)
- [名词解释](https://jpom.io/pages/FQA/proper-noun/)

### Gitee Pages

- [文档主页](https://jpom-docs.keepbx.cn/)
- [FQA](https://jpom-docs.keepbx.cn/pages/FQA/)
- [名词解释](https://jpom-docs.keepbx.cn/pages/FQA/proper-noun/)

### 实践案例

> 里面有部分图片加载可能比较慢

1. [本地构建 + SSH 发布 java 项目](https://jpom.io/pages/practice/build-java-ssh-release)
2. [本地构建 + 项目发布 node 项目](https://jpom.io/pages/practice/build-node-release)
3. [本地构建 + SSH 发布 node 项目](https://jpom.io/pages/practice/build-node-ssh-release)
4. [本地构建 + 自定义管理 python 项目](https://jpom.io/pages/practice/project-dsl-python)
5. [自定义管理 java 项目](https://jpom.io/pages/practice/project-dsl-java)
6. [管理编译安装的 nginx](https://jpom.io/pages/practice/node-nginx)
7. [管理 docker](https://jpom.io/pages/practice/docker-cli)
8. [容器构建 + 项目发布 java 项目](https://jpom.io/pages/practice/build-docker-java-node-release)

## 🛠️ 整体架构

![jpom-func-arch](https://jpom-docs.keepbx.cn/images/jpom-func-arch.jpg)

## 🔨贡献指南

### 贡献须知

Jpom 作为开源项目，离不开社区的支持，欢迎任何人修改和提出建议。贡献无论大小，你的贡献会帮助背后成千上万的使用者以及开发者，你做出的贡献也会永远的保留在项目的贡献者名单中，这也是开源项目的意义所在！

为了保证项目代码的质量与规范，以及帮助你更快的了解项目的结构，请在贡献之前阅读：

- [Jpom 贡献说明](https://gitee.com/dromara/Jpom/blob/master/CONTRIBUTING.md)
- [中英文排版规范](https://gitee.com/dromara/Jpom/blob/dev/typography-specification.md)

### 贡献步骤

1. Fork 本仓库。

2. Fork 后会在你的帐号下多了一个和本仓库一模一样的仓库，把你帐号的仓库 clone 到本地。

   注意替换掉链接中的`分支名`和`用户名`。

   如果是贡献代码，分支名填 `dev`；如果是贡献文档，分支名填 `docs`
   
   ```bash
   git clone -b 分支名 https://gitee.com/用户名/Jpom.git
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

1. 快扫描下方左侧微信群二维码和我们一起交流讨论吧！（添加小助手：备注 Jpom 进群）
2. 开源项目离不开社区的支持，如果项目帮助到了你，并且想给我们加个餐，欢迎扫描下方右侧[微信收款码赞赏](https://jpom-docs.keepbx.cn/images/wx-qrcode-praise.png)或通过[码云赞赏](https://gitee.com/dromara/Jpom)（在项目首页下方点击捐赠，支持微信和支付宝）。[赞赏记录](https://jpom-docs.keepbx.cn/docs/index.html#/praise)
3. 微信公众号：[CodeGzh](https://jpom-docs.keepbx.cn/images/CodeGzh-QrCode.jpg) 查看一些基础教程
4. 反馈 BUG、提出建议，欢迎新建：[issues](https://gitee.com/dromara/Jpom/issues)，开发人员会不定时查看回复。
6. 参与贡献，请查看[贡献指南](#🔨贡献指南)。

感谢所有赞赏以及参与贡献的小伙伴，你们的支持是我们不断更新前进的动力！

![wx-qrcode-praise.png](https://jpom-docs.keepbx.cn/images/wx-qrcode-praise.png)

## 🔔 精品项目推荐

| 项目名称          | 项目地址                                                                       | 项目介绍                                          |
|---------------|----------------------------------------------------------------------------|-----------------------------------------------|
| SpringBoot_v2 | [https://gitee.com/bdj/SpringBoot_v2](https://gitee.com/bdj/SpringBoot_v2) | 基于springboot的一款纯净脚手架                          |
| TLog GVP 项目   | [https://gitee.com/dromara/TLog](https://gitee.com/dromara/TLog)           | 一个轻量级的分布式日志标记追踪神器，10分钟即可接入，自动对日志打标签完成微服务的链路追踪 |
| Sa-Token      | [https://gitee.com/dromara/sa-token](https://gitee.com/dromara/sa-token)   | 这可能是史上功能最全的 Java 权限认证框架！                      |
| Erupt         | [https://gitee.com/erupt/erupt](https://gitee.com/erupt/erupt)             | 零前端代码，纯注解开发 admin 管理后台                        |

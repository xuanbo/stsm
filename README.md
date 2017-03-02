# stsm

毕业设计-分布式软件测试管理系统的设计与实现

## 1项目模块

* common 通用依赖、接口等
* provide 服务提供方，依赖common
* web 服务消费方，依赖common，web-ui
* web-ui vue+webpack前端项目
* cloud-config-repo 配置信息
* cloud-config-server 配置服务端

## 2设计方案

* 采用dubbo实现分布式rpc，zookeeper作为注册中心
* web服务部署多个，nginx反向代理，其中要实现session共享，采用spring-session的redis集群存储方案
* mysql主从复制，读写分离

## 3技术要点

后端：
* spring-boot、spring-session、spring-security等全家桶
* dubbo + zookeeper分布式rpc服务
* redis缓存
* mysql数据库
* flowable工作流
* mybatis、jdbcTemplate、spring jpa持久层
* hibernate-validation数据校验
* spring-cloud-config管理配置
* 其他
* nginx反向代理

前端：
* 采用vuejs + webpack + vue各大组件(vue-router、vue-resource等)
* ui采用bootstrap、elementui等
* 其他插件

## 4运行(暂未配置集群)

### 4.1安装环境

默认你安装了：
* nodejs
* gradle
* mysql
* redis
* zookeeper

由于采用了`spring-cloud-config`管理配置，因此最好将`cloud-config-repo`模块上传到你的git仓库，并修改为自己的配置。

同时，记得修改`cloud-config-server`模块`bootstrap.yml`中的git地址，修改为你的远程地址。

### 4.2启动项目

首先启动mysql、redis、zookeeper服务。

在stsm目录下运行：`gradle build -x test`后，接着启动各个模块

* 运行`cloud-config-server`
* 运行`provider`
* 运行`web`

分别进入各个模块的目录，执行：`gradle bootRun`

提示：如果采用idea或eclipse开发环境，则分别运行`Application`的`main`方法即可。

浏览器输入：localhost:9090/ui/index.html即可访问web-ui模块中的静态资源。
# stsm

毕业设计-分布式软件测试管理系统的设计与实现

# 1.项目模块

* common 通用依赖、接口等
* provide 服务提供方，依赖common
* web 服务消费方，依赖common

# 2.设计方案

* 采用dubbo实现分布式rpc，zookeeper作为注册中心
* web服务部署多个，nginx反向代理，其中要实现session共享，采用spring-session的redis集群存储方案
* mysql主从复制，读写分离（或者mongo主从复制）

# 3.技术要点

后端：
* spring-boot、spring-session、spring-security等全家桶
* dubbo + zookeeper分布式rpc服务
* redis缓存
* mysql、mongo数据库
* activiti5或flowable工作流
* mybatis、jdbcTemplate、spring jpa持久层
* hibernate-validation数据校验
* 其他
* nginx反向代理

前端：
* 采用vuejs + webpack + vue各大组件(vue-router、vue-resource、vuex等)
* ui采用bootstrap、elementui等
* 其他插件
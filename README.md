# spring-cloud
spring cloud demo

1. 先在spring cloud 官网下载 Consul 并且解压， 然后 cd 到对应的目录下，使用 cmd 启动. Consul <br>
 启动命令：consul agent -dev #-dev 表示开发模式运行，另外还有-server 表示服务模式. <br>
 启动成功之后访问：http://localhost:8500，可以看到 Consul 的管理界面.<br>
2.然后启动项目config 运行配置中心 <br>
3.启动其他服务(user,chat).<br>
4. gateway 配置文件没有加入到config中，之后抽空处理。


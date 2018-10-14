# raspberrypiecli
使用java socket开发的一套用来管理树莓派的控制台程序

# 功能
- 文件上传
- 接受服务端指令

# 使用说明
raspberrypiecli使用jdk8.0开发，为了运行该程序，需要提高基本的java运行环境(jre)
首先解压目录并进入，运行rpc脚本即可启动程序

- 配置参数
raspberrypiecli拥有五个配置参数
```
id=12345678
password=12345678
ipAddress=127.0.0.1
port_cmd=8991
port_upload=8990

```

- 配置参数说明  
`id 标示客户端的id，用来告知服务器身份`  
`password 服务器验证所需提供的密码`  
`ipAddress 服务器的地址`  
`port_cmd 端口号，供控制客户端使用`  
`port_ipload 端口号，供上传功能使用`  

- 命令  
  - 查看参数
`rpc -c`或`rpc --config`  
  - 设置参数
`rpc --set [参数1] [值1] [参数2] [值2] ... [参数3] [值3]`  
  - 启动服务
`rpc -s`或`rpc --server`  
  - 上传文件
`rpc -u [文件1] [文件2] ... [文件n]`或`rpc --upload [文件1] [文件2] ... [文件n]`
  - 查看帮助
`rpc --help`或`rpc -h`


android_12306
=============

android client for 12306.cn

#架构图说明

 ![架构图](http://pic1.qnpic.com/img/dengbodb/8e6accec/)
 
 参照MVC框架的思想，前台activity把请求以action的方式发出去，service接到请求后，调用initmodel初始化model。
 
 initmodel会根据action的类型来初始化特定的model，返回model。service将返回的model传给网络模块，网络模块负
 
 责所有的网络交互的工作，如果有错则抛出并返回null，否则以ByteArrayOutputStream的形式返回给service，service
 
 收到数据后，有错则捕获，交给错误处理模块处理，无错则会调用parse模块，parse会根据action的类型来调用不同的
 
 解析器解析数据，数据以bundle形式返回，service会将返回到的bundle以收到的action为参数广播出去，前台activity
 
 收到广播后会更新界面。所有的action和bundle的key统一存储在一个stringpool中，便于管理。
 
 #模块图划分说明
 
 模块的划分暂时参照ios端的划分
 
  ![模块图](http://pic1.qnpic.com/img/dengbodb/290a6d28/)

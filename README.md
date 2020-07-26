# Zjmzxfzhl-Cloud

## 开源不易，如果 `Zjmzxfzhl-Cloud` 对您有帮助，请点右上角 `Star`或者 `Fork` 支持一下。

## 您的支持，是我继续做开源项目的动力，谢谢！

# 项目介绍

当前最新版本: 1.0.0（发布日期: 2020-07-26）
## 项目介绍

Zjmzxfzhl-Cloud 是基于 Spring Boot、Spring Cloud & Alibaba、OAuth2、Flowable 的分布式微服务架构。

gitee: https://gitee.com/zjm16/zjmzxfzhl-cloud

github: https://github.com/zhuangjinming16/zjmzxfzhl-cloud

## 感谢

[pig](https://gitee.com/log4j/pig) , [Ruoyi-Cloud](https://gitee.com/y_project/RuoYi-Cloud) , [open-capacity-platform](https://gitee.com/owenwangwen/open-capacity-platform)

## 技术架构
### 后端技术

* 基础框架：Java8 & Spring Boot & Spring Cloud & Spring Cloud Alibaba & Maven
* 数据库：Mysql 等
* 鉴权框架：Spring Security OAuth2
* 服务注册及配置中心：Nacos
* 流量监控：Sentinel
* 缓存框架：Redis & Redisson
* 持久层框架：Mybatis Plus
* 日志记录：Logback
* 工作流框架：Flowable
* 其他依赖：Lombok、Kaptcha、EasyExcel、Jsoup 等

### 前端技术
- vue , vuex , vue-router
- elementui
- vue-element-admin
- vue-form-making
- mock

## 模块介绍

~~~
zjmzxfzhl-cloud
├── zjmzxfzhl-app             // App模块 [8020]
├── zjmzxfzhl-auth            // 认证中心 [8000]     
├── zjmzxfzhl-common          // 通用模块
│     ├── zjmzxfzhl-common-core                       // 核心模块
│     ├── zjmzxfzhl-common-log                        // 日志模块
│     ├── zjmzxfzhl-common-mybatis                    // Mybatis配置模块
│     ├── zjmzxfzhl-common-redis                      // Redis缓存模块
│     ├── zjmzxfzhl-common-remote                     // Feign远端交易模块
│     ├── zjmzxfzhl-common-security                   // 安全模块
├── zjmzxfzhl-demo            // 示例模块 [8050]
├── zjmzxfzhl-flowable        // 工作流模块 [8030]
├── zjmzxfzhl-gateway         // 网关模块 [8080]
├── zjmzxfzhl-sys             // 系统模块 [8010]
│     ├── zjmzxfzhl-sys-api                           // 系统Api模块
│     ├── zjmzxfzhl-sys-biz                           // 系统业务模块
│     ├── zjmzxfzhl-sys-job                           // 系统定时任务模块
├── zjmzxfzhl-visual          // 图形化管理模块
│     ├── zjmzxfzhl-monitor                           // 监控中心 [8040]
├── zjmzxfzhl-vue             // 前端模块 [8888]
其他
├── Nacos                     // 服务注册及配置中心 [8848]
├── Sentinel                  // 流量监控 [8858]
~~~

## 主要实现内容
1. 系统管理、流程管理、示例管理

2. 功能权限，菜单权限、按钮权限细粒度配置

3. 数据权限，注解实现 + 后台配置实现

4. 流程管理，包含自定义表单、流程定义、流程实例、任务管理、发起流程、我的流程、我的待办、我的已办，任务执行包含提交、终止、转办、委派、退回（已实现退回并行网关节点、子流程退回）等

   流程设计约定：

   - 发起者启动流程后若要自动完成第一个用户任务，则第一个 `userTask`的`id`要定义为`__initiator__`，若涉及流程表单，则可设置`__initiator__`的任务表单`formKey`与流程表单相同

   - 如果涉及并行网关，并行网关需成对出现，且发散节点要以 `_begin` 结尾，汇聚节点要以 `_end` 结尾，可以嵌套但不能交叉嵌套，这样就能确保可以退回到并行网关的单个节点上（不会退回到并行网关的其他分支）

   - 如果流程涉及业务主键key，流程设计时加入流程数据对象即可

     ```xml
     <dataObject id="showBusinessKey" name="showBusinessKey" itemSubjectRef="xsd:boolean">
     	<extensionElements>
     		<activiti:value>true</activiti:value>
     	</extensionElements>
     </dataObject>
     ```

   - 流程设计可参考工程源代码 `zjmzxfzhl/src/main/resources/processes_test` 下的流程（使用的是`Flowable`的`Eclipse`插件 `Flowable Diagram Editor 设计的）

   - 自定义表单使用LGPL协议开源的 [FormMaking](http://form.xiaoyaoji.cn/pricing/#/zh-CN/)，若使用的 `FormMaking` 安装包（及通过 `npm` 引入）的方式，不需要购买授权，但若有使用 `FormMaking` 的源代码，需要到官方购买授权

5. `Redis`分布式锁，可实现交易防重发等业务场景

6. `App`开发示例，包含：注册、登录、获取用户信息

7. 代码生成器，包含前端和后端

8. 定时任务、异步任务线程池管理

9. `Excel` 导入导出

## 文档及演示环境
文档地址：暂未更新

演示环境：暂无 `Zjmzxfzhl-Cloud` 微服务演示环境，可先查看`Zjmzxfzhl`演示环境 ：[http://118.190.100.3:8080/zjmzxfzhl](http://118.190.100.3:8080/zjmzxfzhl) 

测试用户（默认密码都是1）：

`admin`用户，拥有所有权限，测试通用功能、工作流程连贯性等，可以使用`admin`用户，例如执行工程内的`complex-嵌套并行网关子流程`可以使用`admin`用户

`zjmzxfzhl`普通员工岗位，可以发起请假流程

`zjm`经理岗位，可以审批员工的请假流程

`zxf`老板岗位，可以审批员工的请假流程

请假流程详见`zjmzxfzhl/src/main/resources/processes_test/leave.bpmn20.xml`或者`zjmzxfzhl/src/main/resources/processes_test/leaveBusinessKey.bpmn20.xml`

## 技术交流
QQ群 : 913659692

## 界面展示

![](https://img-blog.csdnimg.cn/20200328211217434.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/2020032821122660.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211234880.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211245801.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211256213.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211323783.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211441117.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211448303.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211505420.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211524258.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211756182.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211559996.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211539437.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211642491.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211850894.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211904277.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211910561.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211924102.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211930452.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200328211936458.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqbTE2,size_16,color_FFFFFF,t_70)
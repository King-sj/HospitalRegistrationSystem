# 专家挂号系统 version 0.0.0 初步设计(draft)

[toc]
## 总体架构

+ front-end layer: webapp，负责与用户（患者、专家、数据管理员）交互，展示数据， 暂时使用vue实现
+ controller layer: 处理用户的请求(http),并转发到服务层， 暂时使用**SpringBoot Web**
+ service layer: 负责业务逻辑处理， 链接controller和 DAO layer
+ DAO layer: 负责与数据库交互
+ DataBase layer: 采用MongoDB, Redis
****
## front-end layer
### store-layer
  保存用户状态
### api-layer
  call: store-layer: userStore
  负责与后端交互
### loginSystem
  call: api-layer
  called: view-layer
  账号登录，并根据用户角色调用不同的页面
### view-layer
  call: loginSystem
  用户交互与数据展示
+ patient-view
+ doctor-view
+ admin-view

### exception-layer:
  异常管理
## controller layer
BackendController.java (class)
面向前端提供接口
## service layer

## DAO layer

## DataBase layer
# 课程管理系统
## 项目背景介绍
项目名称：课程管理系统

项目目标用户群：教务员、教师、学生

项目实现的功能：
1. 验证登录
2. 教务员——审批活动、审批开课、审批成绩、管理人员信息、个人主页、一系列查询等
3. 教师——评分、开课、课表显示、个人主页、查询教学班成绩等
4. 学生——选退课、查询成绩、评教、个人主页等

## 技术背景和特点
开发框架:

前端开发框架——VUE.JS(Vue3)

后端开发框架——Java SpringBoot3(JDK19)

数据库——MySQL 8.0, 部署在腾讯云数据库

项目开发特点：

系统权限分级控制：教务员、教师、学生 三种角色对应三种权级

服务端分层架构设计:

表现层（Controller）

持久层（DAO, Mapper）

实体层（Entity）
## 数据库介绍（具体信息可以查看sql文件）
数据库字段：

![image](https://github.com/user-attachments/assets/f54e789a-b48e-417f-a709-7244709643ee)

E-R图：

![image](https://github.com/user-attachments/assets/54b50806-109b-425a-b674-ca257e7a3e64)

## 项目功能介绍
### 登录系统
登录界面与登录接口相连，输入账号密码，选择身份之后，点击登录即可进行验证。如果验证成功，则跳转到主页面；这里增加了账号密码错误或者用户已停用的报错逻辑。

<img width="415" alt="image" src="https://github.com/user-attachments/assets/0adda4ad-2a42-44aa-83be-9c144848ace1" />

### 个人主页系统
个人信息页面也是通过个人信息接口来获得各种数据，其中有一个修改个人信息的按钮，可以跳转到修改个人信息页面。修改个人信息时，如果想改密码，有一个验证两次密码是否一致的操作

<img width="642" alt="image" src="https://github.com/user-attachments/assets/2f5838dc-5bc7-45eb-8bae-5e300c3a5184" />


<img width="586" alt="image" src="https://github.com/user-attachments/assets/00dca8fc-e44d-4788-ad55-8e9db9532626" />

### 学生系统
<img width="608" alt="image" src="https://github.com/user-attachments/assets/0141415f-bdd7-498f-a236-d48289efc3ae" />

选课退课页面：进去之后会根据当前的学生所在学院展示所有可选课程和相关信息，同时提供了几个条件供学生筛选来快速寻找自己心仪的课程，重置可以将筛选条件清空并恢复所有课程的显示

<img width="595" alt="image" src="https://github.com/user-attachments/assets/7b28bfbd-715a-4532-a470-d91ec1873256" />

查询成绩页面：在成绩查询模块可查询学生自己的成绩，在排名总览模块可查看统计数据，在成绩分布模块可查看成绩分布饼状图

<img width="646" alt="image" src="https://github.com/user-attachments/assets/95bb77c9-23e8-41f6-b890-df38bf684cf5" />

评教页面：学生可以根据自己所选的课程，对授课教师进行评教

<img width="646" alt="image" src="https://github.com/user-attachments/assets/5552062c-3b7b-492d-b355-6978af96d3d5" />

### 教师系统
评定成绩页面：教师评分这里跟教师的课程接口相连接，实现了将教师已经分配好教学班号的课程显示在表格中。教师具体评分页面中，首先与教学班成绩的接口相连接，实现通过教学班id查看成绩并显示在表格中。改变评分项后，总分和绩点都会实时更新。最后，点击保存成绩即可与打分接口连接，实现给学生打分。

<img width="640" alt="image" src="https://github.com/user-attachments/assets/3a8cc215-72a8-4be4-8cda-2ca1248102fe" />
<img width="651" alt="image" src="https://github.com/user-attachments/assets/4b3d14ef-e4dd-448e-81f1-90945e496045" />

我的课程页面：我的课程页面中，与通过id查询课程的接口相连接，可以查询教师的所有课程信息，显示在表格中。这里有添加课程以及编辑课程的按钮。同时，为了画面简洁，将字数比较多的课程大纲和审批意见通过对话框的形式呈现

<img width="654" alt="image" src="https://github.com/user-attachments/assets/4d654178-dcac-4653-b378-7ddd29e4d4cc" />

编辑课程页面：这里有修改课程与添加课程的功能

<img width="655" alt="image" src="https://github.com/user-attachments/assets/0287ee87-64de-40a8-b950-d2926a798248" />

查询教学班成绩页面：点击详情可以查看明细

<img width="597" alt="image" src="https://github.com/user-attachments/assets/559ef8e0-efce-405b-bd58-1fcd8b0da389" />

### 教务员系统
用户管理页面：这里分为学生、教师、教务员，表格的内容一致，均为身份、账号、姓名、状态。三种身份对应不同的查询方法。同时，有查看用户，添加用户和编辑用户的功能（用户的增删改查）

<img width="674" alt="image" src="https://github.com/user-attachments/assets/c677113a-d61e-4ef1-bc20-ca547c1c7645" />
<img width="650" alt="image" src="https://github.com/user-attachments/assets/9fa6bfb8-3445-4e7c-97eb-2e0829a196d2" />

课程审批页面：先会显式所有待审批的课程信息。对于审批通过的课程教务员无法修改审批内容，也无法删除审批。对于驳回或未审批的课程则可以进行重复修改或删除

<img width="676" alt="image" src="https://github.com/user-attachments/assets/1a00d783-0d2d-4df9-b4b0-d96c4daa4afe" />

活动审批页面：与课程审批逻辑类似，有添加活动的功能

<img width="660" alt="image" src="https://github.com/user-attachments/assets/0797d85e-c411-4bd7-b57f-a821366bf569" />






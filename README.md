# README

## 项目名称

浙东唐诗之路可视化系统

## 所用技术栈列表

mysql 8.0.31 

jdk8 

vue 

springboot

## 项目安装包

前台安装vue install安装包

后台使用maven直接安装依赖包

## 安装指导书

### 1、项目安装包清单（使用的是mac系统）

前端：

  "axios": "^1.3.1",

  "core-js": "^3.8.3",

  "echarts": "^5.4.2",

  "element-ui": "^2.15.12",

  "vue": "^2.6.14",

  "vue-baidu-map": "^0.21.22",

  "vue-router": "^3.6.5",

  "vuex": "^3.6.2",

  "vuex-along": "^1.2.13"

后台：

maven

### 2、项目安装包使用安装顺序

#### SQL

直接把文件中的sql文件导入mysql数据库中

后台登陆密码为数据库中的**admin**表中数据

#### 前端ZheDongFront（开发使用的vscode）

1、使用vue，打开项目使用终端用**npm install**安装所有的依赖包

2、安装完后用**npm run serve**运行前端项目，端口是8080，运行完点击local的地址即可运行

![image-20230601220328451](/Users/fuxiaolong/Pictures/Typora/image-20230601220328451.png)

3、城市和诗人管理的数据导入如文件夹中的excel为例子

#### 后端ZheDongBack（开发使用的idea）

1、springboot，jdk8，maven项目

2、在项目的FileUploadController中要修改一下项目图片的存储路径，路径是前端文件的assets/image文件夹

3、配置一下application中的数据库信息

4、后端的默认端口是8088，直接运行就可以

能够打开并且数据库中的数据可以显示在项目中

# springweb-ai

一个基于 Spring Boot 的后台管理项目，用于练习 Java Web 后端开发流程。

## 技术栈

- Spring Boot
- Spring MVC
- MyBatis
- PageHelper
- Spring AOP
- JWT
- Aliyun OSS

## 本地启动

1. 创建 MySQL 数据库 `zpk`，并准备业务表数据。
2. 配置数据库连接，默认读取以下环境变量：
   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
3. 如果需要上传文件到 OSS，配置阿里云官方环境变量：
   - `OSS_ACCESS_KEY_ID`
   - `OSS_ACCESS_KEY_SECRET`
   - `ALIYUN_OSS_ENDPOINT`
   - `ALIYUN_OSS_BUCKET_NAME`
   - `ALIYUN_OSS_REGION`
4. 进入模块目录并启动：

```bash
cd zpk-web-management
mvn spring-boot:run
```

## 常用命令

```bash
mvn test
mvn package
```

## 接口说明

- `POST /login` 登录并返回 JWT。
- 业务接口需要携带 `token` 请求头，或使用标准 `Authorization: Bearer <token>`。

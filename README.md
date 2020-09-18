# Wapache OpenAPI Project

`Wapache OpenAPI Project`是一个整合了openapi spec, swagger-api, springdoc, rapidoc, openapi-generator等多个开源项目, 
用于OpenAPI学习, 提供Spring整合, 代码生成和文档生成, 可视化展示等功能整合的项目。
本项目在众多开源项目基础上针对中文环境和使用习惯做了一定裁剪,扩展和汉化。

主要引入了以下项目:

1. [swagger-api/swagger-core](https://github.com/https://github.com/swagger-api/swagger-core)
2. [swagger-api/swagger-parser](https://github.com/swagger-api/swagger-parser)
3. [swagger-api/swagger-js](https://github.com/swagger-api/swagger-js)
4. [springdoc/springdoc-openapi](https://github.com/springdoc/springdoc-openapi)
4. [OpenAPITools/openapi-generator](https://github.com/OpenAPITools/openapi-generator)
5. [mrin9/RapiDoc](https://github.com/mrin9/RapiDoc)

主要做了以下修改:

1. 统一了包名前缀, 统一为`org.wapache.openapi`。
2. 删除`swagger-core`中的JAXRS支持, 删除`openapi-generator`中不常用到的语言支持。
3. 添加了`wapa` JS客户端代码生成模板, 修改了`openapi-generator`的代码, 增加了生成`AllApi.js`的功能。
4. 删除`springdoc-openapi`中除Java语言外的其他语言支持, 删除`webflux`支持, `spring-boot`降级为`2.0.4`版本。
5. 原`springdoc`的`RequestBody`重命名为`ApiRequestBody`,以避免与Spring的`RequestBody`名称重复导致使用不便。
6. 部分汉化`Rapidoc`, 对`Rapidoc`界面布局和样式做了调整和增强。

## 编译运行

```shell script
cd openapi-ui/rapidoc
npm install
npm run build # windows下运行: npm run build_windows

cd ../..
mvn clean package
cd openapi-spring-webmvc-demo/target
java -jar openapi-spring-webmvc-demo-0.9.18-SNAPSHOT.jar
```

## 界面预览

暗黑主题:

![暗黑主题](docs/images/openapi-ui-dark.jpg)

明亮主题:

![明亮主题](docs/images/openapi-ui-light.jpg)


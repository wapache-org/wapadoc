
访问地址:

```
http://localhost/
http://localhost/openapi-ui.html
http://localhost/swagger-ui.html
```

1. `HomeController` 做了一次重定向, 将`/`重定向到`openapi-ui.html`或者`swagger-ui.html`
2. `OpenApiWelcome.redirectToUi()`或`SwaggerWelcome.redirectToUi()` 又做了一次重定向到`/openapi-ui 或 swagger-ui/index.html`
3. `OpenApiWebMvcConfigurer` 或 `SwaggerWebMvcConfigurer` 将URL映射为静态资源`/webjars/openapi-ui 或 swagger-ui/index.html`
4. `webjars-locator-core`搜索webjars,找到匹配的jar, 返回对应的html。



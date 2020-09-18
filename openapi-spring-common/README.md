
它能运行起来的秘密就在`META-INF/spring.factories`和`OpenAPIBuilder.build`方法, 

前者告知Spring有哪些Configuration类和Properties类, Spring会读取配置文件填充Properties类的实例, 以及根据Configuration类执行配置,

后者会获取到所有Controller实例, 生成api-docs.

```java

class OpenAPIBuilder {

    public void build() {

		this.mappingsMap.putAll(context.getBeansWithAnnotation(RestController.class));
		this.mappingsMap.putAll(context.getBeansWithAnnotation(RequestMapping.class));
		this.mappingsMap.putAll(context.getBeansWithAnnotation(Controller.class));

    }

}

```

如果允许预加载, 那么在new的时候就会调用

OpenApiResource -> AbstractOpenApiResource -> OpenAPIBuilder.build() 加入所有controller.

否则, 是在真正调用的时候才触发(一般是通过http访问`/v3/api-docs`的时候).

疑问: 预加载不会出现有的controller还没扫描到就调用了build()的情况吗?

估计Spring是先扫描了所有Class, 得到所有bean的信息后, 才在某个时刻开始创建这些bean的实例的.
调用这里的build方法就会导致所有controller bean 都创建好了实例.

然后会根据这个`mappingMap`从`RequestMappingInfoHandlerMapping`里得到所有需要的信息, 从而构建出OpenAPI对象.



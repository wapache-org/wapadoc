
gen-client.bat

```shell script

@echo off

cd /d %~dp0

java -jar ../modules/openapi-generator-cli/target/openapi-generator-cli.jar generate ^
-t ../modules/openapi-generator/src/main/resources/Javascript/wapa ^
-g javascript ^
--additional-properties appName=ApiClient,usePromises=true,useES6=true ^
-i %1 ^
-o ../generated/client

```

生成`openapi-spring-webmvc-demo`的javascript客户端:

Windows:

```shell script

java -jar openapi-generator-cli/target/openapi-generator-cli-1.4.7-SNAPSHOT.jar generate ^
-t openapi-generator/src/main/resources/Javascript/wapa ^
-g javascript ^
--additional-properties appName=ApiClient,usePromises=true,useES6=true ^
-i http://localhost:8080/v3/api-docs/users ^
-o openapi-generator-cli/generated/client


java -jar openapi-generator-cli/target/openapi-generator-cli-1.4.7-SNAPSHOT.jar generate -t openapi-generator/src/main/resources/Javascript/wapa -g javascript --additional-properties appName=ApiClient,usePromises=true,useES6=true -i http://localhost:8080/v3/api-docs/users -o openapi-generator-cli/generated/client

```

Linux:

```shell script

java -jar target/openapi-generator-cli-1.4.7-SNAPSHOT.jar generate \
-t ../openapi-generator/src/main/resources/Javascript/wapa \
-g javascript \
--additional-properties appName=ApiClient,usePromises=true,useES6=true \
-i http://localhost:8080/v3/api-docs/users \
-o generated/client

```
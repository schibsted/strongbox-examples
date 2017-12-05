
##Example spring-boot application with an api that exposes two secrets from Strongbox. 


###Running the app locally
1) Have a secrets group set up in your AWS environment
2) Create dummy secrets called `api.clientId` and `api.secret`
2) Configure `src/main/resources/bootstrap.yml` to match a secret group in your AWS environment.
3) Load AWS credentials in your shell 
4) run `mvn spring-boot:run`

During boot you will now see log lines like:
```shell
DEBUG [2017-12-05 13:03:47,054] [] com.schibsted.security.strongbox.springboot.StrongboxBootstrapConfiguration: Cannot get current AWS region, using default region eu-west-1

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)

DEBUG [2017-12-05 13:04:06,571] [] com.schibsted.security.strongbox.springboot.StrongboxPropertySource: Setting property 'api.clientId' from Strongbox, version 1
DEBUG [2017-12-05 13:04:06,571] [] com.schibsted.security.strongbox.springboot.StrongboxPropertySource: Setting property 'api.secret' from Strongbox, version 1
INFO  [2017-12-05 13:04:06,576] [] com.schibsted.security.springboot.example.Application: No active profile set, falling back to default profiles: default
WARN  [2017-12-05 13:04:13,264] [] org.springframework.boot.starter.remote.shell.RemoteShellStarterDeprecatedWarningAutoConfiguration: spring-boot-starter-remote-shell is deprecated as of Spring Boot 1.5 and will be removed in Spring Boot 2.0
INFO  [2017-12-05 13:04:13,763] [] com.schibsted.security.springboot.example.Application: Started Application in 44.72 seconds (JVM running for 48.26)
```


the `ApiClientConfig` Configuration class will now have its properties `secret` and `clientId` set. Visit http://localhost:8080/api-info to see the values.


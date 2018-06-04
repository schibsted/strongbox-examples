
# Example spring-boot application with an api that exposes two secrets from Strongbox. 


## Running the app locally
1) Have a secrets group set up in your AWS environment
2) Create dummy secrets called `api.clientId`, `api.secret`, and `signatureKey`
3) Configure `src/main/resources/bootstrap.yml` to match a secret group in your AWS environment.
4) Load AWS credentials in your shell 
5) run `mvn spring-boot:run`

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
DEBUG [2017-12-05 13:04:06,571] [] com.schibsted.security.strongbox.springboot.StrongboxPropertySource: Setting property 'signatureKey' from Strongbox, version 1
INFO  [2017-12-05 13:04:06,576] [] com.schibsted.security.springboot.example.Application: No active profile set, falling back to default profiles: default
INFO  [2017-12-05 13:04:13,763] [] com.schibsted.security.springboot.example.Application: Started Application in 44.72 seconds (JVM running for 48.26)
```

Since we have annotated `ApiClientConfig` with `@ConfigurationProperties("api")`, this class will now get the fields `secret` and `clientId` populated with values from Strongbox.

We are also using the ` @StrongboxValue` annotation in `ApiController` in order to get the value of `signatureKey`. 

### Disabling strongbox-spring-boot-starter based on Spring profiles
In this example application we have disabled the starter for the `test` profile in `bootstrap.yml`. You can also disable it for other profiles like `local` as in the example below. Usually developers like to supply parameters like `spring.datasource.password` on the command line when starting the application locally.


bootstrap.yml:
```shell
strongbox:
  groupname: my.group
  enabled: true
---
spring:
  profiles: test, local
strongbox:
  enabled: false
```

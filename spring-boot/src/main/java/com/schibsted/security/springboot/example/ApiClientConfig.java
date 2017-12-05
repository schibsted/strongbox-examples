package com.schibsted.security.springboot.example;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("api")
public class ApiClientConfig {

    @Setter
    @Getter
    private String secret;

    @Setter
    @Getter
    private String clientId;

}

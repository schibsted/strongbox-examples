package com.schibsted.security.springboot.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiController {

    @Autowired
    private ApiClientConfig apiConfig;

    @GetMapping("/api-info")
    @ResponseBody
    public Map<String, String> apiConfig() {
        HashMap<String, String> map = new HashMap<>();
        map.put("secret", apiConfig.getSecret());
        map.put("clientId", apiConfig.getClientId());
        return map;
    }
}

package com.mxxk.lightdream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * UrlConfig
 *
 * @auther zhang
 * @date 2020/7/6
 **/
@Component
public class ApiConfig {

    @Value("${apiUrl.starUrl}")
    private String starUrl;

    @Value("${apiUrl.starToken}")
    private String starToken;

    public String getStarUrl() {
        return starUrl;
    }

    public void setStarUrl(String starUrl) {
        this.starUrl = starUrl;
    }

    public String getStarToken() {
        return starToken;
    }

    public void setStarToken(String starToken) {
        this.starToken = starToken;
    }
}

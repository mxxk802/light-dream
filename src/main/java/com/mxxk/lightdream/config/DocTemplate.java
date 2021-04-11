package com.mxxk.lightdream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * DocTemplate
 *
 * @auther zhang
 * @date 2020/8/3
 **/
@Component
public class DocTemplate {

    @Value("${docTemplate.stockFreemarkerTemplate}")
    private String stockFreemarkerTemplate;

    @Value("${docTemplate.stockVelocityTemplate}")
    private String stockVelocityTemplate;

    public String getStockFreemarkerTemplate() {
        return stockFreemarkerTemplate;
    }

    public void setStockFreemarkerTemplate(String stockFreemarkerTemplate) {
        this.stockFreemarkerTemplate = stockFreemarkerTemplate;
    }

    public String getStockVelocityTemplate() {
        return stockVelocityTemplate;
    }

    public void setStockVelocityTemplate(String stockVelocityTemplate) {
        this.stockVelocityTemplate = stockVelocityTemplate;
    }
}

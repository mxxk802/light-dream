package com.mxxk.lightdream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * PackConfig
 *
 * @auther zhang
 * @date 2020/12/18
 **/
@Component
public class PackConfig {

    @Value("${dirName.stockReport}")
    private String stockReport;

    @Value("${dirName.stockBasePackage}")
    private String stockBasePackage;

    public String getStockReport() {
        return stockReport;
    }

    public void setStockReport(String stockReport) {
        this.stockReport = stockReport;
    }

    public String getStockBasePackage() {
        return stockBasePackage;
    }

    public void setStockBasePackage(String stockBasePackage) {
        this.stockBasePackage = stockBasePackage;
    }
}

package com.mxxk.lightdream.entity;

/**
 * UpAndDownRanking
 *
 * @auther zhang
 * @date 2021/1/21
 **/
public class UpAndDownRanking {

    private String stockName;//上市公司名称
    private double currentPrice;//当前价格
    private String changePrice;//变动价格
    private String upAndDownRange;//涨跌幅
    private String mark;//1.涨幅排行2.跌幅排行


    public UpAndDownRanking() {
    }

    public UpAndDownRanking(String stockName, double currentPrice, String changePrice, String upAndDownRange, String mark) {
        this.stockName = stockName;
        this.currentPrice = currentPrice;
        this.changePrice = changePrice;
        this.upAndDownRange = upAndDownRange;
        this.mark = mark;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getUpAndDownRange() {
        return upAndDownRange;
    }

    public void setUpAndDownRange(String upAndDownRange) {
        this.upAndDownRange = upAndDownRange;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}

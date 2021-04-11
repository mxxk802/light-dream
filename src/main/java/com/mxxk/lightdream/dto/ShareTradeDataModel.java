package com.mxxk.lightdream.dto;

import com.mxxk.lightdream.entity.UpAndDownRanking;

import java.io.Serializable;
import java.util.List;

/**
 * ShareTradeDataModel
 *
 * @auther zhang
 * @date 2021/1/21
 **/
public class ShareTradeDataModel implements Serializable {

    private static final long serialVersionUID = -212199624406986608L;

    private List<UpAndDownRanking> upAndDownRanking;

    public ShareTradeDataModel() {
    }

    public ShareTradeDataModel(List<UpAndDownRanking> upAndDownRanking) {
        this.upAndDownRanking = upAndDownRanking;
    }

    public List<UpAndDownRanking> getUpAndDownRanking() {
        return upAndDownRanking;
    }

    public void setUpAndDownRanking(List<UpAndDownRanking> upAndDownRanking) {
        this.upAndDownRanking = upAndDownRanking;
    }
}

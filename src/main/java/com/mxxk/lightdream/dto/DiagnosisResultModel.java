package com.mxxk.lightdream.dto;

import com.mxxk.lightdream.entity.ShareNature;
import com.mxxk.lightdream.entity.Shares;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * DiagnosisResultModel 雷达图
 *
 * @auther zhang
 * @date 2020/9/2
 **/
public class DiagnosisResultModel extends BaseModel{
    private static Logger log= LoggerFactory.getLogger(DiagnosisResultModel.class);

    public DiagnosisResultModel() {
    }

    public DiagnosisResultModel(String language, List<ShareNature> fields, String modelName, Shares shares) {
        super(language, fields, modelName, shares);
    }
}

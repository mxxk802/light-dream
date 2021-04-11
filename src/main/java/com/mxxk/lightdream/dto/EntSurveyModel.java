package com.mxxk.lightdream.dto;

import com.mxxk.lightdream.entity.ShareNature;
import com.mxxk.lightdream.entity.Shares;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * EntSurveyModel
 *
 * @auther zhang
 * @date 2020/8/27
 **/
public class EntSurveyModel extends BaseModel{

    private static Logger log= LoggerFactory.getLogger(EntSurveyModel.class);

    public EntSurveyModel() {
    }

    public EntSurveyModel(String language, List<ShareNature> fields, String modelName, Shares shares) {
        super(language, fields, modelName, shares);
    }
}

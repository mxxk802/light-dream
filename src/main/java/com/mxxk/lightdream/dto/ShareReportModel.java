package com.mxxk.lightdream.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ShareReportModel
 *
 * @auther zhang
 * @date 2020/12/16
 **/

public class ShareReportModel extends ShareReportAbstract {


    private static Logger logger= LoggerFactory.getLogger(ShareReportModel.class);

    public ShareReportModel(String stockName, String language) {
        super(stockName, language);
    }



    public EntSurveyModel entSurveyModel;//企业概况
    public DiagnosisResultModel diagnosisResultModel;//诊断结果


    public EntSurveyModel getEntSurveyModel() {
        return entSurveyModel;
    }

    public void setEntSurveyModel(EntSurveyModel entSurveyModel) {
        this.entSurveyModel = entSurveyModel;
    }

    public DiagnosisResultModel getDiagnosisResultModel() {
        return diagnosisResultModel;
    }

    public void setDiagnosisResultModel(DiagnosisResultModel diagnosisResultModel) {
        this.diagnosisResultModel = diagnosisResultModel;
    }


}

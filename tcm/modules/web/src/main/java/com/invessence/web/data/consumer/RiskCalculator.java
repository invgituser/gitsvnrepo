package com.invessence.web.data.consumer;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.CustomerData;
import com.invmodel.Const.InvConst;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/14/16
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RiskCalculator
{

   public Integer getRiskAge();
   public Integer getRiskHorizon();
   public Integer getRetireAge();
   public void setRiskAge(Integer age);
   public void setRiskHorizon(Integer horizon);
   public void setRetireAge(Integer value);
   public String getRiskFormula();
   public void setRiskFormula(String value);
   public Integer getAnswerValue(Integer index);
   public void setAnswer(Integer index, String value);
   public Integer getRiskValue(Integer index);
   public Double getTotalRisk();
   public void setTotalRisk(Double value);
   public void resetAllData();
   public Integer convertRiskWeight2Index(Double weight);
   public Double convertIndex2RiskWeight(Double index);
}

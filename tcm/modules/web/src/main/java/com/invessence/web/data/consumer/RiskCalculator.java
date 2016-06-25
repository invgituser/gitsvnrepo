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

   public void setAnswer(Integer index, String value);
   public Integer getRiskOffset();
   public Integer convertRiskWeight2Index(Double weight);
   public Double convertIndex2RiskWeight(Double index);

   // Deprecating: 2016-06-14 Prashant
   public Integer getRiskOffset(Integer[] choice);
   public Double offsetRiskIndex(CustomerData cdata);
   public Double calcRiskOffset(Integer age, Integer horizon, Double riskIndex);
}

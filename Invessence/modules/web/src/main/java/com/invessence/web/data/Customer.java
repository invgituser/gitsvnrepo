package com.invessence.web.data;

import com.invessence.web.data.common.CustomerData;
import com.invessence.web.util.Logger;
import com.invmodel.risk.dao.RiskFetchDAO;
import com.invmodel.risk.data.*;

/**
 * Created by prashant on 11/16/2017.
 */
public class Customer extends CustomerData implements Logger
{

   UserRisk userRisk;
   RiskCalc riskCalc;

   RiskFetchDAO riskfetchDAO;

   public Customer()
   {
      userRisk = new UserRisk();
      riskCalc = new RiskCalc();
      riskfetchDAO = new RiskFetchDAO();
   }

   public void preRender()
   {

   }
   public void fetchUserRisk (String advisor, Long acctnum)
   {
      try
      {
         userRisk.setAdvisor(advisor);
         userRisk.setAcctnum(acctnum);
         riskfetchDAO.fetchRiskData(userRisk);
         riskfetchDAO.fetchRiskScores(userRisk);
      }
      catch (Exception ex) {
         getLogger().error(ex.getStackTrace());
      }
   }

}

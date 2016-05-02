package com.invessence.bean.consumer;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.LTAMOptimizer;
import com.invessence.dao.consumer.ConsumerListDataDAO;
import com.invessence.data.LTAMTheme;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.ConsumerData;
import com.invessence.data.ltam.LTAMRiskData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 5/1/16
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "testBean")
@SessionScoped
public class TestBean
{

   @ManagedProperty("#{consumerListDataDAO}")
   static private ConsumerListDataDAO listDAO;

   public void setListDAO(ConsumerListDataDAO consumerListDataDAO)
   {
      this.listDAO = consumerListDataDAO;
   }

   @ManagedProperty("#{ltamOptimizer}")
   private LTAMOptimizer ltamoptimizer;

   public void setLtamoptimizer(LTAMOptimizer ltamoptimizer)
   {
      this.ltamoptimizer = ltamoptimizer;
   }

   private LTAMTheme theme;


   List<AccountData> newAcctList = null;

   public List<AccountData> getNewAcctList()
   {
      return newAcctList;
   }

   public void preRenderView()
   {
      if (!FacesContext.getCurrentInstance().isPostback())
      {
         collectData();
      }
   }

   public void collectData()
   {
      List<ConsumerData> consumerList;
      LTAMRiskData riskData = new LTAMRiskData();
      newAcctList = new ArrayList<AccountData>();
      consumerList = listDAO.getClientProfileData(10L, "A", null);
/*
      System.out.println("Account#" + "," +
                            "Age" + "," + "Ans2" + "," + "Ans3" + "," +
                            "Ans4" + "," + "Ans5" + "," + "Ans6" + "," +
                            "OrigRisk" + "," + "NewRisk");
*/
      for (ConsumerData cd : consumerList)
      {
         AccountData ad = listDAO.getAccountData(cd.getLogonid(), cd.getAcctnum());
         if (ad != null)
         {
            riskData.setAgeforRisk(ad.getAge());  // This is same as ans1
            riskData.setAns2(ad.getAns2());
            riskData.setAns3(ad.getAns3());
            riskData.setAns4(ad.getAns4());
            riskData.setAns5(ad.getAns5());
            riskData.setAns6(ad.getAns6());
            Double riskIndex = riskData.calcRiskIndex();
            ad.setTotalDebt(riskIndex);
            theme = ltamoptimizer.getTheme(riskIndex);
            ad.setMailCountry(theme.getSecurityname());
            newAcctList.add(ad);
         }
      }
   }

}

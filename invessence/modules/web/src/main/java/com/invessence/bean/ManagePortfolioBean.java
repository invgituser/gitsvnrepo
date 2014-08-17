package com.invessence.bean;

import com.invessence.bo.PortfolioBo;
import com.invessence.data.*;
import com.invmodel.portfolio.data.Portfolio;
import com.invmodel.portfolio.data.PortfolioSecurityData;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagePortfolioBean implements Serializable
{

   HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
   private Portfolio[] pfclass; // = new Portfolio[0];
   private List<DataPortfolio> portfolioList;
   private DataPortfolio selectedPortfolioList;
   private PortfolioBo portfolioBo;


   private Integer totalshare;
   private Double  totalAssetWeight;
   private Double totalvalue;


   public ManagePortfolioBean()
   {
   }

   public void createPortfolio(ManageGoals goalsData)
   {
      Integer data_year = 0;
      try
      {
         if (goalsData != null)
         {
            setTotalshare(0);
            setTotalvalue(0.0);
            setTotalAssetWeight(0.0);

            pfclass = goalsData.getPortfolioData();

            int rowSize = pfclass[data_year].getPortfolio().size();
            portfolioList = new ArrayList<DataPortfolio>();
            for (int loop = 0; loop < rowSize; loop++)
            {
               PortfolioSecurityData pfList = pfclass[data_year].getPortfolio().get(loop);
               DataPortfolio dp = new DataPortfolio(pfList.getAssetclass(), pfList.getSubclass(), pfList.getColor(),
                                                    pfList.getTicker(), pfList.getName(), (int) pfList.getShares(),
                                                    pfList.getDailyprice(), pfList.getMoney(), pfList.getSortorder(),
                                                    pfList.getTickerWeights());
               portfolioList.add(loop, dp);

               this.totalshare = totalshare + (int) pfList.getShares();
               this.totalAssetWeight = this.totalAssetWeight  + pfList.getTickerWeights();

               totalvalue = totalvalue + pfList.getMoney();
            }
         }
      }
      catch (Exception ex)
      {

      }
   }

   public Integer getTotalshare()
   {
      return totalshare;
   }

   public void setTotalshare(Integer totalshare)
   {
      this.totalshare = totalshare;
   }

   public Double getTotalAssetWeight()
   {
      return totalAssetWeight;
   }

   public void setTotalAssetWeight(Double totalAssetWeight)
   {
      this.totalAssetWeight = totalAssetWeight;
   }

   public Double getTotalvalue()
   {
      return totalvalue;
   }

   public void setTotalvalue(Double totalvalue)
   {
      this.totalvalue = totalvalue;
   }

   public DataPortfolio getSelectedPortfolioList()
   {
      return selectedPortfolioList;
   }

   public void setSelectedPortfolioList(DataPortfolio selectedPortfolioList)
   {
      this.selectedPortfolioList = selectedPortfolioList;
   }

   public String getPopulatetotalvalue()
   {
      DecimalFormat df = new DecimalFormat("###,####,###.00");
      String strValue = df.format(totalvalue);
      return strValue;
   }

   public String getPopulatetotalshare()
   {
      String strShare = NumberFormat.getIntegerInstance().format(totalshare);
      return strShare;
   }

   public List<DataPortfolio> getPortfolioList()
   {
      return portfolioList;
   }

   public void setPortfolioBo(PortfolioBo portfolioBo)
   {
      this.portfolioBo = portfolioBo;
   }

   public String savePortfolio(ManageGoals goals)
   {

      //portfolioBo = new PortfolioBo();
      portfolioBo.addPortfolio(goals);

      return "success";
   }
}

package com.invessence.bean;

import com.invessence.bo.AllocationBo;
import com.invessence.data.ManageGoals;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.portfolio.data.Portfolio;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManageAllocationBean implements Serializable
{
   private PieChartModel pieModel;
   private CartesianChartModel lineModel;

   private Integer minYear = 2013;
   private Integer maxYear = 2015;
   private Integer year = 2013;
   private Integer minY = 0;
   private Integer maxY = 1000000;
   private String seriesColor;
   private Integer legendXrotation;

   private String headerText;

   private AssetClass[] aamc;
   private Portfolio[] portfolio;
   private Integer slices;


   AllocationBo allocationBo;

   private Long acctnum;
   private String userid;
   private String addmodflag;
   private String model = "D";
   private Integer assetyear = 0;
   private String active = "P";

   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getAddmodflag()
   {
      return addmodflag;
   }

   public void setAddmodflag(String addmodflag)
   {
      this.addmodflag = addmodflag;
   }


   public void setAllocationBo(AllocationBo allocationBo)
   {
      this.allocationBo = allocationBo;
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(String model)
   {
      this.model = model;
   }

   public Integer getAssetyear()
   {
      return assetyear;
   }

   public void setAssetyear(Integer assetyear)
   {
      this.assetyear = assetyear;
   }

   public String getActive()
   {
      return active;
   }

   public void setActive(String active)
   {
      this.active = active;
   }

   public PieChartModel getPieModel()
   {
      return pieModel;
   }

   public void setPieModel(PieChartModel pieModel)
   {
      this.pieModel = pieModel;
   }

   public CartesianChartModel getLineModel()
   {
      return lineModel;
   }

   public void setLineModel(CartesianChartModel lineModel)
   {
      this.lineModel = lineModel;
   }

   public Integer getSlices()
   {
      return slices;
   }

   public void setSlices(Integer slices)
   {
      this.slices = slices;
   }

   public Integer getMinYear()
   {
      return minYear;
   }

   public void setMinYear(Integer minYear)
   {
      this.minYear = minYear;
   }

   public Integer getMaxYear()
   {
      return maxYear;
   }

   public void setMaxYear(Integer maxYear)
   {
      this.maxYear = maxYear;
   }

   public Integer getYear()
   {
      return year;
   }

   public void setYear(Integer year)
   {
      this.year = year;
   }

   public Integer getLegendXrotation()
   {
      return legendXrotation;
   }

   public void setLegendXrotation(Integer legendXrotation)
   {
      this.legendXrotation = legendXrotation;
   }

   public String getSeriesColor()
   {
      return seriesColor;
   }

   public void setSeriesColor(String seriesColor)
   {
      this.seriesColor = seriesColor;
   }

   public Integer getMinY()
   {
      return minY;
   }

   public void setMinY(Integer minY)
   {
      this.minY = minY;
   }

   public String getHeaderText()
   {
      return headerText;
   }

   public void setHeaderText(String name)
   {
      this.headerText = name.trim() + " Profile";
   }

   public Integer getMaxY()
   {
      return maxY;
   }

   public void setMaxY(Integer maxY)
   {
      this.maxY = maxY;
   }

   public AssetClass[] getAamc()
   {
      return aamc;
   }

   public void setAamc(AssetClass[] aamc)
   {
      this.aamc = aamc;
   }

   public Portfolio[] getPortfolio()
   {
      return portfolio;
   }

   public void setPortfolio(Portfolio[] portfolio)
   {
      this.portfolio = portfolio;
   }

   public void createAllocation(ManageGoals goalsData)
   {
      int duration;
      int firstyear = 0;

      try
      {
         duration = goalsData.getHorizon();
         try
         {
            Calendar cal = Calendar.getInstance();
            setMinYear(cal.get(cal.YEAR));
            setMaxYear(getMinYear() + duration);
            setYear(this.minYear);
         }
         catch (Exception ex)
         {
            System.out.println("Manage AllocationBean, getting Calender Year");
         }

         aamc = goalsData.getAssetData();
         portfolio = goalsData.getPortfolioData();
         //yearlyGrowthData = goalsData.getYearlyGrowthData();
         if (goalsData.getName() == null)
         {
            setHeaderText("Your");
         }
         else
         {
            setHeaderText(goalsData.getName());
         }
         setMaxY(goalsData.getInitialInvestment());
         setMaxYear(getMinYear() + (aamc.length - 1));

         setSlices(aamc[firstyear].getOrderedAsset().size());
         setMinY(goalsData.getInitialInvestment());

         createPieModel(aamc[firstyear]);       //  This create Pie is also called from refresh.
         createLineModel(portfolio);

      }
      catch (Exception ex)
      {
         System.out.println("Creating Allocation Page:" + ex.getMessage());
      }
   }

   public String saveAllocation(ManageGoals goals)
   {
      allocationBo.saveAllocation(goals);
      return "success";
   }

   private void createLineModel(Portfolio[] portfolio)
   {
      Integer year;
      Double maxGrowth = 0.0;
      Integer noOfYlabels = 0;
      Integer totalYlabels = 0;
      Integer yIncrement = 1;
      Integer MAXPOINTONGRAPH = 30;
      try
      {
         this.lineModel = new CartesianChartModel();

         ChartSeries growth = new ChartSeries();
         ChartSeries totalMoney = new ChartSeries();
         ChartSeries totalInvested = new ChartSeries();

         //growth.setLabel("Growth");
         totalMoney.setLabel("Growth**");
         totalInvested.setLabel("Invested");
         yIncrement = (int) (((double) portfolio.length) / ((double) MAXPOINTONGRAPH));
         yIncrement = yIncrement + 1;  // offset by 1
         noOfYlabels = (int) (((double) portfolio.length) / ((double) yIncrement)) % MAXPOINTONGRAPH;
         // Mod returns 0 at its interval.  So on 30, we want to rotate it 90.
         noOfYlabels = (noOfYlabels == 0) ? portfolio.length : noOfYlabels;
         if (noOfYlabels <= 10)
         {
            this.legendXrotation = 0;
         }
         else if (noOfYlabels < 15)
         {
            this.legendXrotation = 30;
         }
         else
         {
            this.legendXrotation = 90;
         }

         int y = 0;
         totalYlabels = portfolio.length - 1;
         while (y <= totalYlabels)
         {
            year = getMinYear() + y;
            // System.out.println("Year:" + year.toString() + ", Value=" + yearlyGrowthData[y][2]);
            maxGrowth = (maxGrowth > portfolio[y].getTotalMoney()) ? maxGrowth : portfolio[y].getTotalMoney();
            //growth.set(year, portfolio[y].getTotalCapitalGrowth());
            totalMoney.set(year.toString(), portfolio[y].getTotalMoney());
            totalInvested.set(year.toString(), portfolio[y].getActualInvestments());
            // If incrementing anything other then 1, then make sure that last year is displayed.
            if (y == totalYlabels)
            {
               y++;  // If last point is plotted, then quit.
            }
            else
            {
               y = ((y + yIncrement) > totalYlabels) ? y = totalYlabels : y + yIncrement;
            }
         }

         setMaxY(maxGrowth.intValue() + 10000);
         //lineModel.addSeries(growth);
         lineModel.addSeries(totalMoney);
         lineModel.addSeries(totalInvested);
      }
      catch (Exception ex)
      {
         System.out.println("Error, attempting to create chart:" + ex.getMessage());
      }
   }


   private void createPieModel(AssetClass aac)
   {
      String color;
      this.pieModel = new PieChartModel();
      for (int i = 0; i < getSlices(); i++)
      {
         String assetname = aac.getOrderedAsset().get(i);
         String label = assetname + " - " + aac.getAssetRoundedActualWeight(assetname) + "%";
         pieModel.set(label, aac.getAssetRoundedActualWeight(assetname));
         color = aac.getAssetColor(assetname);//.replace('#',' ');
         color.trim();
         if (i == 0)
         {
            seriesColor = color;
         }
         else
         {
            seriesColor = seriesColor + ", " + color;
         }
      }
   }

   public void refreshChart(AssetClass aac)
   {
      createPieModel(aac);
   }

   public void refreshChart(SlideEndEvent event)
   {

      setYear(event.getValue());
      Integer offset = getYear() - getMinYear();

      createPieModel(aamc[offset]);
   }

}

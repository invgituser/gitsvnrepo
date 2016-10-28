package com.invessence.bean.common;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.dao.common.PositionDAO;
import com.invessence.data.common.Position;
import com.invessence.util.*;
import com.invmodel.asset.data.*;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "positionBean")
@SessionScoped
public class PositionBean implements Serializable
{
   private static final long serialVersionUID = 1003L;
   private List<Position> positionList;
   private List<Position> displayPositionList = new ArrayList<Position>();
   private DataDisplayConverter dconveter = new DataDisplayConverter();
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{positionDAO}")
   private PositionDAO positionDAO;
   private Map<String, Asset> managedAssetsMap = new LinkedHashMap<String, Asset>();
   private List<Asset> managedAssetsList = new ArrayList<Asset>();

   private Double totalAllocation;
   private Integer totalshare;
   private Double totalvalue;
   private Double totalmoney;
   private Double totalpnl;
   private Double totalYield;
   private Double totalExpenseRatio;
   private Double totalRisk;
   private Double totalFees;
   private Double goalDesired;
   private Double goalReached;
   private Long acctnum;
   private String firstname, lastname, dateOpened, clientAccountID;
   private Boolean managed;

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (this.acctnum != null && this.acctnum > 0L) {
               collectData();
            }
            else {
               webutil.redirect("/access-denied.xhtml", null);
            }
         }
      }
      catch (Exception e)
      {
         webutil.redirect("/access-denied.xhtml", null);
      }
   }

   @PostConstruct
   public void init()
   {
      String userName;
      try
      {
         webutil.validatePriviledge(null);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void setPositionDAO(PositionDAO positionDAO)
   {
      this.positionDAO = positionDAO;
   }

   public PositionDAO getPositionDAO()
   {
      return positionDAO;
   }

   public List<Position> getDisplayPositionList()
   {
      return displayPositionList;
   }

   public boolean isPostback()
   {
      FacesContext context = FacesContext.getCurrentInstance();
      return context.getRenderKit().getResponseStateManager().isPostback(context);
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getFullName() {
      if (lastname != null && lastname.length() > 0)
         return this.lastname + ", " + this.firstname;
      else
         return firstname + " Portfolio";
   }

   public String getDateOpened()
   {
      if (dateOpened != null)
      {
         return dateOpened.substring(0, 4) + "-" + dateOpened.substring(4, 6) + "-" + dateOpened.substring(6);
      }
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public Boolean getManaged()
   {
      return managed;
   }

   public void setManaged(Boolean managed)
   {
      this.managed = managed;
   }

   public Map<String, Asset> getManagedAssetsMap()
   {
      return managedAssetsMap;
   }

   public void setManagedAssetsMap(Map<String, Asset> managedAssetsMap)
   {
      this.managedAssetsMap = managedAssetsMap;
   }

   public List<Asset> getManagedAssetsList()
   {
      return managedAssetsList;
   }

   public void setManagedAssetsList(List<Asset> managedAssetsList)
   {
      this.managedAssetsList = managedAssetsList;
   }

   public void collectData()
   {
      try
      {
         if (this.acctnum == null)
            webutil.redirect("/access-denied.xhtml", null);
         else
         {
            // Note: If no data is found, then blank form will display.  It is better then going to access denied, page.
            positionList = positionDAO.loadDBPosition(webutil.getLogonid(), this.acctnum);
            addTotals();
         }
      }
      catch (Exception ex)
      {
         System.out.println("Error in collect data from DB :" + ex.getMessage());
      }

   }

   private void addTotals()
   {
      Integer rows;
      try
      {
         totalAllocation = 0.0;
         totalshare = 0;
         totalvalue = 0.0;
         totalmoney = 0.0;
         totalpnl = 0.0;
         totalRisk = 0.0;
         totalYield = 0.0;
         totalExpenseRatio = 0.0;
         totalFees = 0.0;
         goalDesired = 0.0;
         goalReached = 0.0;

         displayPositionList.clear();
         if (positionList == null)
         {
            return;
         }
         rows = positionList.size();
         managedAssetsMap.clear();
         managedAssetsList.clear();
         int counter=0;
         this.managed = true;
         Boolean infoData = false;
         for (int loop = 0; loop < rows; loop++)
         {
            Position position = positionList.get(loop);
            if (position.getTicker() != null && position.getTicker().length() > 0)
               displayPositionList.add(position);
            String assetname =  (position.getAssetclass() == null) ? "Other": position.getAssetclass();
            if (assetname.toUpperCase().contains("STK"))
               this.managed = false;
            if (!managedAssetsMap.containsKey(assetname))
            {
               Asset asset = new Asset(assetname,
                                       null,    // Display name is not defined in Managed Asset.
                                       position.getColor(),
                                       0.0, 0.0, 0.0, 0.0,0.0,0.0,
                                       position.getWeight(), position.getYield(), position.getRisk(), position.getExpenseRatio(), position.getPositionValue());
               managedAssetsMap.put(assetname, asset);
               managedAssetsList.add(counter++, asset);
            }
            else
            {
               Asset asset = managedAssetsMap.get(assetname);
               asset.setHoldingweight(asset.getHoldingweight() + position.getWeight());
               asset.setHoldingValue(asset.getHoldingValue() + position.getPositionValue());
               asset.setHoldingReturn(asset.getHoldingReturn() + position.getYield());
               asset.setHoldingRisk(asset.getHoldingRisk() + position.getRisk());
               asset.setHoldingExpenseRatio(asset.getHoldingExpenseRatio() + position.getExpenseRatio());
            }

            if ((!infoData) && (position.getClientAccountID() != null))
            {
               infoData = true;
               setFirstname(position.getFirstname());
               setLastname(position.getLastname());
               setDateOpened(position.getDateOpened());
               setClientAccountID(position.getClientAccountID());
               totalFees = position.getFees();  // NOTE: This field contains the ytd total.  It is not by security;
            }
            totalAllocation = totalAllocation + position.getWeight();
            totalshare = totalshare + position.getQty();
            totalmoney = totalmoney + position.getCostBasisMoney();
            totalvalue = totalvalue + position.getPositionValue();
            totalpnl = totalpnl + position.getFifoPnlUnrealized();
            totalRisk = totalRisk + (position.getRisk());
            totalExpenseRatio = totalExpenseRatio + (position.getExpenseRatio());
            totalYield = totalYield + (position.getYield());
            if (position.getGoalAmount() != null) {
               goalDesired = position.getGoalAmount();
            }

            if (position.getGoalAmount() > 0.0) {
               goalReached = totalmoney / goalDesired;
            }

         }
         createPieModel();
      }
      catch (Exception ex)
      {
         System.out.println("Error when attempting to addTotal(position)" + ex.getMessage());
      }

   }

   public List<Position> getPositionList()
   {
      return positionList;
   }

   public void setPositionList(List<Position> positionList)
   {
      this.positionList = positionList;
   }

   public Double getTotalAllocation()
   {
      return totalAllocation;
   }

   public void setTotalAllocation(Double totalAllocation)
   {
      this.totalAllocation = totalAllocation;
   }

   public Integer getTotalshare()
   {
      return totalshare;
   }

   public Double getTotalvalue()
   {
      return totalvalue;
   }

   public Double getTotalmoney()
   {
      return totalmoney;
   }

   public void setTotalmoney(Double totalmoney)
   {
      this.totalmoney = totalmoney;
   }

   public Double getTotalpnl()
   {
      return totalpnl;
   }

   public void setTotalpnl(Double totalpnl)
   {
      this.totalpnl = totalpnl;
   }

   public Double getTotalYield()
   {
      return totalYield;
   }

   public void setTotalYield(Double totalYield)
   {
      this.totalYield = totalYield;
   }

   public Double getTotalExpenseRatio()
   {
      return totalExpenseRatio;
   }

   public void setTotalExpenseRatio(Double totalExpenseRatio)
   {
      this.totalExpenseRatio = totalExpenseRatio;
   }

   public Double getTotalRisk()
   {
      return totalRisk;
   }

   public void setTotalRisk(Double totalRisk)
   {
      this.totalRisk = totalRisk;
   }

   public Double getTotalFees()
   {
      return totalFees;
   }

   public void setTotalFees(Double totalFees)
   {
      this.totalFees = totalFees;
   }

   public Double getGoalDesired()
   {
      return goalDesired;
   }

   public Double getGoalReached()
   {
      return goalReached;
   }

   public String reviseRisk()
   {
      return "success";
   }

   private PieChartModel pieModel;
   private String pieIsValid = "false";

   public PieChartModel getPieModel()
   {
      return pieModel;
   }

   public void setPieModel(PieChartModel pieModel)
   {
      this.pieModel = pieModel;
   }

   public String getPieIsValid()
   {
      return pieIsValid;
   }

   public void setPieIsValid(String pieIsValid)
   {
      this.pieIsValid = pieIsValid;
   }

   private void createPieModel()
   {
      String seriescolor = "";
      this.pieModel = new PieChartModel();
      int slice = 0;
      if (managedAssetsMap.size() > 0)
      {
         pieIsValid = "true";
         for (String name : managedAssetsMap.keySet())
         {
            Asset asset = managedAssetsMap.get(name);
            if (asset != null)
            {
               Double displayWeight = asset.getHoldingweight();
               String label = name + " - " + dconveter.displayAsPercent(displayWeight);
               pieModel.set(label, displayWeight);
               String color = asset.getColor().replace('#',' ');
               if (slice == 0)
                  seriescolor = color.trim();
               else
                  seriescolor =  seriescolor + "," + color.trim();
               slice++;
            }
         }
         pieModel.setFill(true);
         pieModel.setShowDataLabels(false);
         pieModel.setDiameter(150);
         pieModel.setSeriesColors(seriescolor);
         pieModel.setExtender("pie_extensions");

      }
   }


}


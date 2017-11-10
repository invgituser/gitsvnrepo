package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.bean.consumer.tcm.TCMCharts;
import com.invessence.web.controller.HighChartsController;
import com.invessence.web.data.common.*;
import com.invmodel.asset.data.*;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "uobpos")
@SessionScoped
public class PositionBean extends PositionData implements Serializable
{
   private static final long serialVersionUID = 1003L;

   private TCMCharts tcmChart = new TCMCharts();
   private String strDonutString;
   private List<String> keyList,exTranKeyList;
   private int inTtlCrncyCnt,exTranTtlCrncyCnt;
   private String dfltCrncy;
   private Map<String,Double> tranCrcyWsTtl;
   private Double tranSettTtl;
   private boolean dsplHstry=false;

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.isUserLoggedIn()) {
               if (getAcctnum() != null && this.getAcctnum() > 0L) {
                  collectData();
                  return;
               }
            }
            webutil.redirect("/access-denied.xhtml", null);
         }
      }
      catch (Exception e)
      {
         webutil.redirect("/access-denied.xhtml", null);
      }
   }

   public boolean isPostback()
   {
      FacesContext context = FacesContext.getCurrentInstance();
      return context.getRenderKit().getResponseStateManager().isPostback(context);
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

   @Override
   public void addTotals() {
      super.addTotals();
//      createPieModel();
      keyList = new ArrayList<String>(getCurrencyWiseTotal().keySet());
      inTtlCrncyCnt=keyList.size()+1;
      dfltCrncy=getWebutil().getWebprofile().getInfo("DEFAULT.CURRENCY");
      collectTranData();
      createPositionCharts("2DDONUT");
      if(getTransactionList()!=null && getTransactionList().size()>0){
         dsplHstry=true;
      }else{
         dsplHstry=false;
      }
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
      pieModel = null;
      pieIsValid = "false";
      if (getManagedAssetsMap().size() > 0)
      {
         AssetClass[] assetclassArray = new AssetClass[1];
         assetclassArray[0] = new AssetClass();
         for (String name : getManagedAssetsMap().keySet())
         {
            Asset asset = getManagedAssetsMap().get(name);
            assetclassArray[0].addAssetClass(
               asset.getAsset(),
               asset.getDisplayName(),
               asset.getColor(),
               asset.getActualweight(),
               asset.getAvgReturn());
         }
         tcmChart.createPieModel(assetclassArray, 0);
         pieModel = tcmChart.getPieChart();
      }
   }
   private void createPositionCharts(String chartType)
   {
//      pieModel = null;
//      pieIsValid = "false";
      if (getManagedAssetsMap().size() > 0)
      {
         AssetClass[] assetclassArray = new AssetClass[1];
         assetclassArray[0] = new AssetClass();

         if(chartType.equalsIgnoreCase("PIE"))
         {
            pieModel = null;
            pieIsValid = "false";
            for (String name : getManagedAssetsMap().keySet())
            {
               Asset asset = getManagedAssetsMap().get(name);
               assetclassArray[0].addAssetClass(
                  asset.getAsset(),
                  asset.getDisplayName(),
                  asset.getColor(),
                  asset.getActualweight(),
                  asset.getAvgReturn());
            }
            tcmChart.createPieModel(assetclassArray, 0);
            pieModel = tcmChart.getPieChart();
         }  else if(chartType.equalsIgnoreCase("2DDONUT")){
            for (String name : getManagedAssetsMap().keySet())
            {
               Asset asset = getManagedAssetsMap().get(name);
               assetclassArray[0].addAssetClass(asset);
//               assetclassArray[0].addAssetClass(
//                  asset.getAsset(),
//                  asset.getDisplayName(),
//                  asset.getColor(),
//                  asset.getHoldingweight(),
//                  asset.getHoldingValue());
            }
            Map<String, String> configMap = webutil.getWebprofile().getWebInfo();
            HighChartsController highChartsController = new HighChartsController();
             strDonutString=highChartsController.highChartrequesthandler(null,assetclassArray,configMap);
            System.out.println("HD strDonutString~~>"+strDonutString+"<~~");
         }
      }
   }

   public void collectTranData()
   {
      if (getTransactionList() != null && getTransactionList().size() > 0)
      {
         if (tranCrcyWsTtl != null)
         {
            tranCrcyWsTtl.clear();
         }
         else
         {
            tranCrcyWsTtl = new HashMap<String, Double>();
         }
         for (int i = 0; i < getTransactionList().size(); i++)
         {
            Transaction objTran = getTransactionList().get(i);
            if (tranCrcyWsTtl.isEmpty())
            {
               tranCrcyWsTtl.put(objTran.getSettleCurrency(), objTran.getSettleNetAmount());
            }
            else
            {
               if (tranCrcyWsTtl.containsKey(objTran.getSettleCurrency()))
               {
                  Double value = objTran.getSettleMoney() + tranCrcyWsTtl.get(objTran.getSettleCurrency());
                  tranCrcyWsTtl.put(objTran.getSettleCurrency(), value);
               }
               else
               {
                  tranCrcyWsTtl.put(objTran.getSettleCurrency(), objTran.getSettleNetAmount());
               }
            }
            tranSettTtl = tranSettTtl + objTran.getSettleNetAmount();
         }

         exTranKeyList = new ArrayList<String>(tranCrcyWsTtl.keySet());
         exTranTtlCrncyCnt = exTranKeyList.size() + 1;
      }else{
         exTranKeyList = new ArrayList<String>();
         exTranTtlCrncyCnt=0;
         tranSettTtl=0.0;
         tranCrcyWsTtl=new HashMap<>();
      }
   }

   public String getStrDonutString()
   {
      return strDonutString;
   }

   public void setStrDonutString(String strDonutString)
   {
      this.strDonutString = strDonutString;
   }

   public List<String> getKeyList()
   {
      return keyList;
   }

   public void setKeyList(List<String> keyList)
   {
      this.keyList = keyList;
   }

   public int getInTtlCrncyCnt()
   {
      return inTtlCrncyCnt;
   }

   public void setInTtlCrncyCnt(int inTtlCrncyCnt)
   {
      this.inTtlCrncyCnt = inTtlCrncyCnt;
   }

   public String getDfltCrncy()
   {
      return dfltCrncy;
   }

   public void setDfltCrncy(String dfltCrncy)
   {
      this.dfltCrncy = dfltCrncy;
   }

   public Double getTranSettTtl()
   {
      return tranSettTtl;
   }

   public void setTranSettTtl(Double tranSettTtl)
   {
      this.tranSettTtl = tranSettTtl;
   }

   public Map<String, Double> getTranCrcyWsTtl()
   {
      return tranCrcyWsTtl;
   }

   public void setTranCrcyWsTtl(Map<String, Double> tranCrcyWsTtl)
   {
      this.tranCrcyWsTtl = tranCrcyWsTtl;
   }

   public int getExTranTtlCrncyCnt()
   {
      return exTranTtlCrncyCnt;
   }

   public void setExTranTtlCrncyCnt(int exTranTtlCrncyCnt)
   {
      this.exTranTtlCrncyCnt = exTranTtlCrncyCnt;
   }

   public List<String> getExTranKeyList()
   {
      return exTranKeyList;
   }

   public void setExTranKeyList(List<String> exTranKeyList)
   {
      this.exTranKeyList = exTranKeyList;
   }

   public boolean isDsplHstry()
   {
      return dsplHstry;
   }

   public void setDsplHstry(boolean dsplHstry)
   {
      this.dsplHstry = dsplHstry;
   }
}


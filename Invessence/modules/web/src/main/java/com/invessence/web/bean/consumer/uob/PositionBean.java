package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.bean.consumer.tcm.TCMCharts;
import com.invessence.web.data.common.*;
import com.invmodel.asset.data.*;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "uobpos")
@SessionScoped
public class PositionBean extends PositionData implements Serializable
{
   private static final long serialVersionUID = 1003L;

   private TCMCharts tcmChart = new TCMCharts();

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
      createPieModel();
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
}


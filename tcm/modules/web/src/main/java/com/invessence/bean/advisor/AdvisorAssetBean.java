package com.invessence.bean.advisor;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.advisor.*;
import com.invessence.data.advisor.*;
import com.invessence.data.common.AccountData;
import com.invessence.util.*;


@ManagedBean(name = "advisorAssetBean")
@ViewScoped
public class AdvisorAssetBean implements Serializable
{
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   @ManagedProperty("#{advisorSaveDataDAO}")
   private AdvisorSaveDataDAO advisorSaveDataDAO;

   private AdvisorTheme advisorTheme;
   private String selectedTheme;
   private String taxableFilter;
   private ArrayList<AssetData> filteredAsssetData;
   private ArrayList<PrimeAssetData> filteredPrimeAssetData;

   private AssetData selectedAsset;
   private PrimeAssetData selectedPrimeAssetData;

   public AdvisorAssetBean()
   {
      advisorTheme = new AdvisorTheme();
      filteredAsssetData = null;
      filteredPrimeAssetData = null;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   public void setAdvisorSaveDataDAO(AdvisorSaveDataDAO advisorSaveDataDAO)
   {
      this.advisorSaveDataDAO = advisorSaveDataDAO;
   }

   public String getSelectedTheme()
   {
      return selectedTheme;
   }

   public void setSelectedTheme(String selectedTheme)
   {
      this.selectedTheme = selectedTheme;
   }

   public String getTaxableFilter()
   {
      return taxableFilter;
   }

   public void setTaxableFilter(String taxableFilter)
   {
      this.taxableFilter = taxableFilter;
   }

   public ArrayList<AssetData> getFilteredAsssetData()
   {
      return filteredAsssetData;
   }

   public ArrayList<PrimeAssetData> getFilteredPrimeAssetData()
   {
      return filteredPrimeAssetData;
   }

   public AssetData getSelectedAsset()
   {
      return selectedAsset;
   }

   public void setSelectedAsset(AssetData selectedAsset)
   {
      this.selectedAsset = selectedAsset;
   }

   public PrimeAssetData getSelectedPrimeAssetData()
   {
      return selectedPrimeAssetData;
   }

   public void setSelectedPrimeAssetData(PrimeAssetData selectedPrimeAssetData)
   {
      this.selectedPrimeAssetData = selectedPrimeAssetData;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(Const.ROLE_ADVISOR))
            {
               Long logonid;
               logonid = webutil.getLogonid();

               if (logonid != null)
               {
                  collectALLData(logonid);
               }
            }
         }
      }
      catch (Exception ex)
      {
         String stack = ex.getStackTrace().toString();
         webutil.seriousError("Asset Class Master", "Error: During PreRenderView", "Serious Error during the preload process", stack);
      }
   }

   public void collectALLData(Long logonid)
   {
      try
      {
         if (logonid != null)
         {
            advisorListDataDAO.collectAssetClass(logonid, advisorTheme);
            advisorListDataDAO.collectPrimeAssetClass(logonid, advisorTheme);
         }
      }
      catch (Exception ex)
      {
         String stack = ex.getStackTrace().toString();
         webutil.seriousError("Asset Class Master", "Error: During Collecting Data", "Serious Error during the Data Load process", stack);
      }
   }

   public void filterTheme()
   {
      filteredAsssetData = null;
      filteredPrimeAssetData = null;
      selectedTheme = null;
   }

   public void filterData()
   {
      filteredAsssetData = null;
      filteredPrimeAssetData = null;
      if (advisorTheme != null && selectedTheme != null)
      {
         filteredAsssetData = advisorTheme.getAssetdata(selectedTheme, true);
      }
      if (advisorTheme != null && selectedTheme != null)
      {
         filteredPrimeAssetData = advisorTheme.getPrimeassetdata(selectedTheme, true);
      }
   }

   public Map<String, String> getThemes()
   {
      if (advisorTheme != null)
      {
         if (taxableFilter != null)
         {
            if (taxableFilter.toUpperCase().startsWith("T"))
            {
               return advisorTheme.getTheme(true);
            }
            else
            {
               return advisorTheme.getTheme(false);
            }
         }
         else
            return advisorTheme.getTheme(false);
      }
      return null;
   }

   public void saveAsset(AssetData saveAssetData) {
      try {
         if (saveAssetData != null) {
            if (webutil.isUserLoggedIn()) {
               Boolean saved = advisorSaveDataDAO.saveAssetData(saveAssetData);
               if (! saved) {
                  webutil.alertSupport("SaveAssetData", "Attempting to Save", "Attempting to Save", null );
               }
            }
         }

      }
      catch (Exception ex) {
         webutil.alertSupport("SaveAssetData", "Attempting to Save", "Exception, When Attempting to Save", null );
      }
   }

   public void savePrimeAsset(PrimeAssetData savePrimeAssetData) {
      try {
         if (savePrimeAssetData != null) {
            if (webutil.isUserLoggedIn()) {
               Boolean saved = advisorSaveDataDAO.savePrimeAssetData(savePrimeAssetData);
               if (! saved) {
                  webutil.alertSupport("SavePrimeAsset", "Attempting to Save", "Attempting to Save", null );
               }
            }
         }

      }
      catch (Exception ex) {
         webutil.alertSupport("SavePrimeAsset", "Attempting to Save", "Exception, When Attempting to Save", null );
      }
   }

}

package com.invessence.web.bean.advisor;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;

import com.invessence.web.dao.advisor.*;
import com.invessence.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by prashant on 4/25/2017.
 */
@ManagedBean(name = "investmentmodel")
@SessionScoped
public class InvestmentModel implements Serializable
{
   @Autowired
   private WebUtil webutil;

   private String whichModel;
   private Map<String,String> downloadlist = new HashMap<String, String>();
   private ArrayList<String> selecteddownloadfiles = new ArrayList<String>();
   Boolean downloadPanel, uploadPanel, verifyPanel, reviewPanel;

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   @ManagedProperty("#{advisorSaveDataDAO}")
   private AdvisorSaveDataDAO advisorSaveDataDAO;
   public void setAdvisorSaveDataDAO(AdvisorSaveDataDAO advisorSaveDataDAO)
   {
      this.advisorSaveDataDAO = advisorSaveDataDAO;
   }

   public String getWhichModel()
   {
      return whichModel;
   }

   public void setWhichModel(String whichModel)
   {
      this.whichModel = whichModel;
   }

   public Map<String, String> getDownloadlist()
   {
      return downloadlist;
   }

   public ArrayList<String> getSelecteddownloadfiles()
   {
      return selecteddownloadfiles;
   }

   public void setSelecteddownloadfiles(ArrayList<String> selecteddownloadfiles)
   {
      this.selecteddownloadfiles = selecteddownloadfiles;
   }

   public Boolean getDownloadPanel()
   {
      return downloadPanel;
   }

   public void setDownloadPanel(Boolean downloadPanel)
   {
      this.downloadPanel = downloadPanel;
   }

   public Boolean getReviewPanel()
   {
      return reviewPanel;
   }

   public void setReviewPanel(Boolean reviewPanel)
   {
      this.reviewPanel = reviewPanel;
   }

   public Boolean getVerifyPanel()
   {
      return verifyPanel;
   }

   public void setVerifyPanel(Boolean verifyPanel)
   {
      this.verifyPanel = verifyPanel;
   }

   public Boolean getUploadPanel()
   {
      return uploadPanel;
   }

   public void setUploadPanel(Boolean uploadPanel)
   {
      this.uploadPanel = uploadPanel;
   }

   public void preRenderView()
   {

      try
      {
         setWhichModel("PREDEFINED");
         loaddownloadfile(getWhichModel());

      }
      catch (Exception ex)
      {

      }
   }

   public void setModel(String modeltype) {
      if (modeltype != null && ! modeltype.equalsIgnoreCase(getWhichModel()))
      {
         setWhichModel(modeltype);
      }
      loaddownloadfile(getWhichModel());
   }

   public void loaddownloadfile(String template) {
      if (advisorListDataDAO != null) {
         downloadlist = advisorListDataDAO.getAssetModelTemplates(whichModel);
      }

   }

   public void downloadfile() {
      if (advisorListDataDAO != null) {
         downloadlist = advisorListDataDAO.getAssetModelTemplates(whichModel);
      }

   }

   public void changeDownloadPanel(Boolean activate) {
      if (activate != null) {
        setDownloadPanel(activate);
      }
   }
   public void uploadfile(String filename) {

   }

   public Boolean validatedata() {
      return true;
   }

   public void review () {

   }

   public void savedata () {

   }


   }

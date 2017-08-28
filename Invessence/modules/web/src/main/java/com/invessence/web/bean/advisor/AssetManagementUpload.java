package com.invessence.web.bean.advisor;

import java.io.*;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.dao.advisor.AdvisorListDataDAO;
import com.invessence.web.dao.advisor.AdvisorSaveDataDAO;
import com.invessence.web.dao.advisor.AdvisorSaveQuery;
import com.invessence.web.data.common.AssetFileUploadList;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;

/**
 * Created by sagar on 7/14/2017.
 */
@ManagedBean(name = "AssetManagementUpd")
@SessionScoped
public class AssetManagementUpload implements Serializable
{
   private String templateName;
   private String templateID;
   private String modelID;
   private String fileDtl;
   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   private List<AssetFileUploadList> lstFileList;
   private List<AssetFileUploadList> lstExtFileList;
   private List<AssetFileUploadList> lstTempList;
   private List<AssetFileUploadList> lstFileDtlList;
   @ManagedProperty("#{advisorSaveDataDAO}")
   private AdvisorSaveDataDAO advisorSaveDataDAO;
   @ManagedProperty("#{advisorSaveQuery}")
   private AdvisorSaveQuery advisorSaveQuery;
   private boolean validationStatus;
   private String validationError;
   private String templateType;
   private boolean displayTempTxt;
   private boolean displayTempDD;
   private boolean displayReviewPanel;

   private List<AssetFileUploadList> listValidateTemplate;


   public void handleFileUpload(FileUploadEvent event)
   {
      InputStream is = null;
      BufferedReader br = null;
      String strLine = null, strName = null, query = null;
      StringBuilder sb = null;
      AssetFileUploadList data = null;
      String tmp_fileDtl[]=null;
      try
      {
         System.out.println("File NAme " + event.getFile().getFileName() + " template name " + getTemplateName() + " tempalete id " + getTemplateID());
         tmp_fileDtl=getFileDtl().split("~");
         is = event.getFile().getInputstream();
         br = new BufferedReader(new InputStreamReader(is));
//Read File Line By Line
         int i = 0;
         sb = new StringBuilder();
         while ((strLine = br.readLine()) != null)
         {
            if (i == 0)
            {
               sb.append("insert into ").append (tmp_fileDtl[1]+" (").append(strLine).append(") values ");
            }
            else if (i == 1)
            {
               String s1[] = strLine.split(",");
               strName = s1[0];
               sb.append("('").append(strLine.replaceAll(",", "','")).append("'),");
               s1 = null;
            }
            else
            {
               sb.append("('").append(strLine.replaceAll(",", "','")).append("'),");
            }
            i++;
         }
         query = sb.toString() + "]";
         query = query.replaceAll(strName, getTemplateName());
         query = query.replaceAll(",]", ";");
         query = query.replaceAll("' ", "'");
         System.out.println("Query String[" + query + "]");
//Close the input stream
         br.close();
         data = new AssetFileUploadList(getTemplateName(), getTemplateName(), "predefined", tmp_fileDtl[0], 4l, "","","","","");
         advisorSaveDataDAO.saveUpdFileDtls(data);
         advisorSaveQuery.saveFileData(query);
         lstFileList = advisorListDataDAO.collectUploadedAssetFileList("predefined", getTemplateName(), 4);
         FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
         FacesContext.getCurrentInstance().addMessage(null, message);
      }
      catch (Exception e)
      {
         System.out.println("Error " + e);
         FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is failed.");
         FacesContext.getCurrentInstance().addMessage(null, message);
      }
      finally
      {
         try
         {
            is = null;
            br = null;
            strLine = null;
            strName = null;
            query = null;
            sb = null;
            data = null;
         }
         catch (Exception e)
         {
         }
      }
   }

   public void handleExistingFileUpload(FileUploadEvent event)
   {

      InputStream is = null;
      BufferedReader br = null;
      String strLine = null, strName = null, query = null;
      StringBuilder sb = null;
      AssetFileUploadList data = null;
      String tmp_fileDtl[]=null;
      String temNm=null;
      try
      {
         System.out.println("File NAme " + event.getFile().getFileName() + " template name " + getTemplateName() + " tempalete id " + getTemplateID());
            if(getTemplateID()== null ){
               temNm=getTemplateName();
            }else{
               temNm=getTemplateID();
            }
         tmp_fileDtl=getFileDtl().split("~");
         is = event.getFile().getInputstream();
         br = new BufferedReader(new InputStreamReader(is));
//Read File Line By Line
         int i = 0;
         sb = new StringBuilder();
         while ((strLine = br.readLine()) != null)
         {
            if (i == 0)
            {
               sb.append("insert into ").append (tmp_fileDtl[1]+" (").append(strLine).append(") values ");
            }
            else if (i == 1)
            {
               String s1[] = strLine.split(",");
               strName = s1[0];
               sb.append("('").append(strLine.replaceAll(",", "','")).append("'),");
               s1 = null;
            }
            else
            {
               sb.append("('").append(strLine.replaceAll(",", "','")).append("'),");
            }
            i++;
         }
         query = sb.toString() + "]";
         query = query.replaceAll(strName, temNm);
         query = query.replaceAll(",]", ";");
         query = query.replaceAll("' ", "'");
         System.out.println("Query String[" + query + "]");
//Close the input stream
         br.close();
         data = new AssetFileUploadList(temNm,event.getFile().getFileName(), "predefined", tmp_fileDtl[0], 4l, "","","","","");
         advisorSaveDataDAO.saveUpdFileDtls(data);
         advisorSaveQuery.saveFileData(query);
         lstExtFileList = advisorListDataDAO.collectUploadedAssetFileList("predefined", temNm, 4);
         FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
         FacesContext.getCurrentInstance().addMessage(null, message);
      }
      catch (Exception e)
      {
         System.out.println("Error " + e);
         FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is failed.");
         FacesContext.getCurrentInstance().addMessage(null, message);
      }
      finally
      {
         try
         {
            is = null;
            br = null;
            strLine = null;
            strName = null;
            query = null;
            sb = null;
            data = null;
         }
         catch (Exception e)
         {
         }
      }
   }
   public void onModelChange(){

      System.out.println("onModelChange " +getModelID());
      lstTempList=advisorListDataDAO.collectUploadedAssetTemplateList(getModelID());
      System.out.println("onModelChange " +lstTempList.size());
      listValidateTemplate=advisorListDataDAO.collectUpdatedThemeList("Predefined","Validate Success");
      System.out.println("onModelChange  listValidateTemplate " +listValidateTemplate.size());

   }
   public void onUpdModelChange(){

      System.out.println("onUpdModelChange " +getModelID());
      lstFileDtlList=advisorListDataDAO.collectFileTypeList(getModelID());
      System.out.println("onUpdModelChange " +lstFileDtlList.size());
      listValidateTemplate=advisorListDataDAO.collectUpdatedThemeList("Predefined","Validate Success");
      System.out.println("onModelChange  listValidateTemplate " +listValidateTemplate.size());

   }
   public void onTemplateTypeChange(){

      System.out.println("templateType "+templateType);
      if(templateType.equalsIgnoreCase("new")){
         displayTempDD=false;
         displayTempTxt=true;
      }else{
         displayTempDD=true;
         displayTempTxt=false;
      }
      System.out.println("displayTempDD "+displayTempDD);
      System.out.println("displayTempTxt "+displayTempTxt);
   }

   public void onTemplateChange(){

      System.out.println("Template Id " +getTemplateID());
      lstExtFileList = advisorListDataDAO.collectUploadedAssetFileList("predefined", getTemplateID(), 4);
      System.out.println("Model NAme " +lstExtFileList.size());

   }
   public void onFileTypeChange(){

      System.out.println("File Type" +getFileDtl());

   }
   public void onValidate(){

      System.out.println("Template Id " +getTemplateID());
       validationError=advisorListDataDAO.validateAssetData(getTemplateID(),"0.bb");
      if(validationError.length()>0){
         validationStatus=true;
      }else{
         validationStatus=true;
         advisorListDataDAO.copyDataOnValidate(getTemplateID());
      }
      listValidateTemplate=advisorListDataDAO.collectUpdatedThemeList("Predefined","Validate Success");
      if(listValidateTemplate!=null && listValidateTemplate.size()>0){
         displayReviewPanel=true;
      }
      System.out.println("Model NAme " +lstExtFileList.size());
   }


   public String getTemplateName()
   {
      return templateName;
   }

   public void setTemplateName(String templateName)
   {
      this.templateName = templateName;
   }

   public String getTemplateID()
   {
      return templateID;
   }

   public void setTemplateID(String templateID)
   {
      this.templateID = templateID;
   }

   public AdvisorListDataDAO getAdvisorListDataDAO()
   {
      return advisorListDataDAO;
   }

   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   public List<AssetFileUploadList> getLstFileList()
   {
      return lstFileList;
   }

   public void setLstFileList(List<AssetFileUploadList> lstFileList)
   {
      this.lstFileList = lstFileList;
   }

   public AdvisorSaveDataDAO getAdvisorSaveDataDAO()
   {
      return advisorSaveDataDAO;
   }

   public void setAdvisorSaveDataDAO(AdvisorSaveDataDAO advisorSaveDataDAO)
   {
      this.advisorSaveDataDAO = advisorSaveDataDAO;
   }

   public List<AssetFileUploadList> getLstExtFileList()
   {
      return lstExtFileList;
   }

   public void setLstExtFileList(List<AssetFileUploadList> lstExtFileList)
   {
      this.lstExtFileList = lstExtFileList;
   }

   public AdvisorSaveQuery getAdvisorSaveQuery()
   {
      return advisorSaveQuery;
   }

   public void setAdvisorSaveQuery(AdvisorSaveQuery advisorSaveQuery)
   {
      this.advisorSaveQuery = advisorSaveQuery;
   }

   public String getModelID()
   {
      return modelID;
   }

   public void setModelID(String modelID)
   {
      this.modelID = modelID;
   }

   public List<AssetFileUploadList> getLstTempList()
   {
      return lstTempList;
   }

   public void setLstTempList(List<AssetFileUploadList> lstTempList)
   {
      this.lstTempList = lstTempList;
   }

   public String getFileDtl()
   {
      return fileDtl;
   }

   public void setFileDtl(String fileDtl)
   {
      this.fileDtl = fileDtl;
   }

   public List<AssetFileUploadList> getLstFileDtlList()
   {
      return lstFileDtlList;
   }

   public void setLstFileDtlList(List<AssetFileUploadList> lstFileDtlList)
   {
      this.lstFileDtlList = lstFileDtlList;
   }

   public boolean isValidationStatus()
   {
      return validationStatus;
   }

   public void setValidationStatus(boolean validationStatus)
   {
      this.validationStatus = validationStatus;
   }

   public String getValidationError()
   {
      return validationError;
   }

   public void setValidationError(String validationError)
   {
      this.validationError = validationError;
   }

   public String getTemplateType()
   {
      return templateType;
   }

   public void setTemplateType(String templateType)
   {
      this.templateType = templateType;
   }

   public boolean isDisplayTempTxt()
   {
      return displayTempTxt;
   }

   public void setDisplayTempTxt(boolean displayTempTxt)
   {
      this.displayTempTxt = displayTempTxt;
   }

   public boolean isDisplayTempDD()
   {
      return displayTempDD;
   }

   public void setDisplayTempDD(boolean displayTempDD)
   {
      this.displayTempDD = displayTempDD;
   }

   public List<AssetFileUploadList> getListValidateTemplate()
   {
      return listValidateTemplate;
   }

   public void setListValidateTemplate(List<AssetFileUploadList> listValidateTemplate)
   {
      this.listValidateTemplate = listValidateTemplate;
   }

   public boolean isDisplayReviewPanel()
   {
      return displayReviewPanel;
   }

   public void setDisplayReviewPanel(boolean displayReviewPanel)
   {
      this.displayReviewPanel = displayReviewPanel;
   }
}
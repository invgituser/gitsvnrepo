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
import com.invmodel.model.ModelUtil;
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
   private String templateID,rollbackId;
   private String modelID,rvwModelId,updModelId;
   private String fileDtl;
   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   private List<AssetFileUploadList> lstFileList;
   private List<AssetFileUploadList> lstExtFileList;
   private List<AssetFileUploadList> lstTempList;
   private List<AssetFileUploadList> lstFileDtlList;
   private List<AssetFileUploadList> lstVldfileDtl;
   private List<AssetFileUploadList> lstRlbkfileDtl;
   @ManagedProperty("#{advisorSaveDataDAO}")
   private AdvisorSaveDataDAO advisorSaveDataDAO;
   @ManagedProperty("#{advisorSaveQuery}")
   private AdvisorSaveQuery advisorSaveQuery;
   private boolean vldSuccessStatus;
   private boolean vldFailureStatus;
   private String validationError;
   private String templateType;
   private boolean displayTempTxt;
   private boolean displayTempDD;
   private boolean displayReviewPanel;
   private boolean displayValidateBtn;
   private String rollbackMsg;

   private List<AssetFileUploadList> listValidateTemplate;

   public void prerender(){
      lstRlbkfileDtl=advisorListDataDAO.collectRollbackList("BB");
   }
   public void onRollBack()
   {
      String theme=null,themeRpl=null;
      System.out.println("rollbackId " + rollbackId);
      if(lstRlbkfileDtl!=null){
         for (int i=0;i<lstRlbkfileDtl.size();i++){
            if(lstRlbkfileDtl.get(i).getSavedTemplateName().equalsIgnoreCase(rollbackId)){
               themeRpl=lstRlbkfileDtl.get(i).getProdTemplateName();
               theme=lstRlbkfileDtl.get(i).getTemplatename();

            }
         }
      }
      if(theme!=null)
      {
         rollbackMsg = advisorListDataDAO.assetMgmtDataMove(rollbackId, "audittoinvdb", themeRpl, 4l);
      }

      System.out.println("Rollback "+rollbackMsg);
      if(rollbackMsg.equalsIgnoreCase("success")){
         advisorListDataDAO.updateTemplateStatus("Predefined",theme,"validation","Rollbacked");
         ModelUtil objModelUtil=new ModelUtil();
         objModelUtil.refreshData();
         rollbackMsg="Theme "+themeRpl+" rollbacked succefully";
      }else{
         rollbackMsg="Ttheme "+themeRpl+" rollback failed ";
      }

//      System.out.println("assocTempId " + assocTempId);
//
//      outputMsg=advisorListDataDAO.assetMgmtDataMove(assocTempId,"invdbtoaudit",apprvTempId,4l);
//      System.out.println("Approve "+outputMsg);
//      if(outputMsg.equalsIgnoreCase("success")){
//         advisorListDataDAO.updateTemplateStatus("Predefined",apprvTempId,"validation","Approved");
//         ModelUtil objModelUtil=new ModelUtil();
//         objModelUtil.refreshData();
//         outputMsg="Theme "+assocTempId+" updated succefully";
//      }else{
//         outputMsg="Ttheme "+assocTempId+" updation failed ";
//      }
   }


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
         boolean bMsgFlag=false;
         if(lstExtFileList!=null)
         {
            for (int i1 = 0; i1 < lstExtFileList.size(); i1++)
            {
               if (lstExtFileList.get(i1).getFileType().equalsIgnoreCase(tmp_fileDtl[0]))
               {
                  bMsgFlag = true;
                  break;
               }
            }
         }
         if(bMsgFlag)
         {
            advisorSaveDataDAO.deleteUpdFileDtls(data);
         }
         advisorSaveDataDAO.saveUpdFileDtls(data);
         advisorSaveQuery.saveFileData(query);
         lstExtFileList = advisorListDataDAO.collectUploadedAssetFileList("predefined", temNm, 4);
         if(bMsgFlag){
            FacesMessage message = new FacesMessage("Succesful","File of type "+tmp_fileDtl[0]+ " is overrided.");
            FacesContext.getCurrentInstance().addMessage(null, message);
         }else
         {
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
         }
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

      System.out.println("onModelChange " +getUpdModelId());
      lstTempList=advisorListDataDAO.collectUpdatedThemeList(getUpdModelId(),"Uploaded");
      // .collectUploadedAssetTemplateList(getUpdModelId());
      System.out.println("onModelChange " +lstTempList.size());
      lstVldfileDtl=advisorListDataDAO.collectFileTypeList(getUpdModelId());
      listValidateTemplate=advisorListDataDAO.collectUpdatedThemeList("Predefined","Validate Success");
      System.out.println("onModelChange  listValidateTemplate " +listValidateTemplate.size());

   }

   public void onRvwModelChange(){

      System.out.println("onRvwModelChange " +getModelID());
      lstTempList=advisorListDataDAO.collectUploadedAssetTemplateList(getModelID());
      System.out.println("onRvwModelChange " +lstTempList.size());
      listValidateTemplate=advisorListDataDAO.collectUpdatedThemeList("Predefined","Validate Success");
      System.out.println("onRvwModelChange  listValidateTemplate " +listValidateTemplate.size());

   }

   public void onUpdModelChange(){

      System.out.println("onUpdModelChange " +getModelID());
      lstTempList=advisorListDataDAO.collectUpdatedThemeList("Predefined","Uploaded");
//      .collectUploadedAssetTemplateList(getModelID());
      System.out.println("onModelChange " +lstTempList.size());
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
      System.out.println("getUpdModelId Id " +getUpdModelId());
      lstExtFileList = advisorListDataDAO.collectUploadedAssetFileList(getUpdModelId(), getTemplateID(), 4);
      System.out.println("Model NAme " +lstExtFileList.size());
      if(lstVldfileDtl.size()==lstExtFileList.size()){
         displayValidateBtn=true;
      }else{
         displayValidateBtn=false;
      }

   }
   public void onFileTypeChange(){

      System.out.println("File Type" +getFileDtl());

   }
   public void onValidate(){

      System.out.println("Template Id " +getTemplateID());
      validationError=advisorListDataDAO.validateAssetData(getTemplateID(),"0.bb");
      if(validationError.length()>0){
         vldSuccessStatus=false;
         vldFailureStatus=true;
      }else{
         vldSuccessStatus=true;
         vldFailureStatus=false;
         advisorListDataDAO.copyDataOnValidate(getTemplateID());
      }
      listValidateTemplate=advisorListDataDAO.collectUpdatedThemeList(getUpdModelId(),"Validate Success");
      if(validationError!=null && vldSuccessStatus){
         displayReviewPanel=true;
         ModelUtil objModelUtil=new ModelUtil();
         objModelUtil.refreshData();
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

   public String getRvwModelId()
   {
      return rvwModelId;
   }

   public void setRvwModelId(String rvwModelId)
   {
      this.rvwModelId = rvwModelId;
   }

   public boolean isDisplayValidateBtn()
   {
      return displayValidateBtn;
   }

   public void setDisplayValidateBtn(boolean displayValidateBtn)
   {
      this.displayValidateBtn = displayValidateBtn;
   }

   public String getUpdModelId()
   {
      return updModelId;
   }

   public void setUpdModelId(String updModelId)
   {
      this.updModelId = updModelId;
   }

   public List<AssetFileUploadList> getLstVldfileDtl()
   {
      return lstVldfileDtl;
   }

   public void setLstVldfileDtl(List<AssetFileUploadList> lstVldfileDtl)
   {
      this.lstVldfileDtl = lstVldfileDtl;
   }

   public boolean isVldSuccessStatus()
   {
      return vldSuccessStatus;
   }

   public void setVldSuccessStatus(boolean vldSuccessStatus)
   {
      this.vldSuccessStatus = vldSuccessStatus;
   }

   public boolean isVldFailureStatus()
   {
      return vldFailureStatus;
   }

   public void setVldFailureStatus(boolean vldFailureStatus)
   {
      this.vldFailureStatus = vldFailureStatus;
   }

   public List<AssetFileUploadList> getLstRlbkfileDtl()
   {
      return lstRlbkfileDtl;
   }

   public void setLstRlbkfileDtl(List<AssetFileUploadList> lstRlbkfileDtl)
   {
      this.lstRlbkfileDtl = lstRlbkfileDtl;
   }

   public String getRollbackId()
   {
      return rollbackId;
   }

   public void setRollbackId(String rollbackId)
   {
      this.rollbackId = rollbackId;
   }

   public String getRollbackMsg()
   {
      return rollbackMsg;
   }

   public void setRollbackMsg(String rollbackMsg)
   {
      this.rollbackMsg = rollbackMsg;
   }
}
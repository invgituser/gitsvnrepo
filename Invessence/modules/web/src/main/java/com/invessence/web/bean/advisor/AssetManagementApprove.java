package com.invessence.web.bean.advisor;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.advisor.AdvisorListDataDAO;
import com.invessence.web.data.common.*;
import com.invessence.web.util.WebUtil;
import com.invmodel.model.ModelUtil;

/**
 * Created by sagar on 8/22/2017.
 */
@ManagedBean(name = "AssetManagementAppr")
@SessionScoped
public class AssetManagementApprove implements Serializable
{
   private String apprvTempId;
   private String assocTempId;
   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   private String outputMsg;
   private long logonId;
   @ManagedProperty("#{webutil}")
   public WebUtil webutil;

   public void onApprove()
   {
      System.out.println("apprvTempId " + apprvTempId);
      System.out.println("assocTempId " + assocTempId);
      boolean bflag=true;
      outputMsg="";
      if(apprvTempId==null || apprvTempId=="0"){
         outputMsg="Template Name is not selected.";
         bflag=false;
      }
      if(assocTempId==null || assocTempId=="0"){
         outputMsg="Theme Name is not selected.";
         bflag=false;
      }
      if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO) != null)
      {
         UserInfoData userInfo = (UserInfoData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);
         logonId = userInfo.getLogonID();
      }
      else
      {
         logonId = 0l;
         outputMsg="Error occured while processing.";
         bflag=false;
      }
      if(bflag)
      {

         String defaultAdvisor= getWebutil().getWebprofile().getWebInfo().get("DEFAULT.ADVISOR").toString();
         List<AdvisorBasket> listBasket=advisorListDataDAO.getAdvisorTheme(defaultAdvisor);
         String themeName="";
         for(int i=0;i<listBasket.size();i++){
            if(listBasket.get(i).getTheme().equalsIgnoreCase(assocTempId)){
               themeName=listBasket.get(i).getBasket();
               break;
            }
         }
         outputMsg = advisorListDataDAO.assetMgmtDataMove(assocTempId, WebConst.INVDB_TO_AUDIT, apprvTempId, logonId);
         System.out.println("Approve " + outputMsg);
         if (outputMsg.equalsIgnoreCase(WebConst.SUCCESS))
         {
            advisorListDataDAO.updateTemplateStatus(WebConst.PREDEFINED, apprvTempId, WebConst.VALIDATION, WebConst.APPROVED);
            ModelUtil objModelUtil = new ModelUtil();
            objModelUtil.refreshData();
            outputMsg = "Theme " + themeName + " updated succefully";
         }
         else
         {
            outputMsg = "Theme " + themeName + " updation failed ";
         }
      }
   }



   public String getApprvTempId()
   {
      return apprvTempId;
   }

   public void setApprvTempId(String apprvTempId)
   {
      this.apprvTempId = apprvTempId;
   }

   public String getAssocTempId()
   {
      return assocTempId;
   }

   public void setAssocTempId(String assocTempId)
   {
      this.assocTempId = assocTempId;
   }

   public AdvisorListDataDAO getAdvisorListDataDAO()
   {
      return advisorListDataDAO;
   }

   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   public String getOutputMsg()
   {
      return outputMsg;
   }

   public void setOutputMsg(String outputMsg)
   {
      this.outputMsg = outputMsg;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }
}

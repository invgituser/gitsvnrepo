package com.invessence.web.bean.advisor;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.advisor.AdvisorListDataDAO;
import com.invessence.web.data.common.UserInfoData;
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

   public void onApprove()
   {
      System.out.println("apprvTempId " + apprvTempId);
      System.out.println("assocTempId " + assocTempId);
      if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO)!=null)
      {
         UserInfoData userInfo = (UserInfoData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);
         logonId=userInfo.getLogonID();
      }else{
         logonId=0l;
      }
      outputMsg=advisorListDataDAO.assetMgmtDataMove(assocTempId, WebConst.INVDB_TO_AUDIT, apprvTempId, logonId);
      System.out.println("Approve "+outputMsg);
      if(outputMsg.equalsIgnoreCase(WebConst.SUCCESS)){
         advisorListDataDAO.updateTemplateStatus(WebConst.PREDEFINED,apprvTempId,WebConst.VALIDATION,WebConst.APPROVED);
         ModelUtil objModelUtil=new ModelUtil();
         objModelUtil.refreshData();
         outputMsg="Theme "+assocTempId+" updated succefully";
      }else{
         outputMsg="Theme "+assocTempId+" updation failed ";
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

}

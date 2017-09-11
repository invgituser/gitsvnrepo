package com.invessence.web.bean.advisor;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.web.dao.advisor.AdvisorListDataDAO;
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

   public void onApprove()
   {

      System.out.println("apprvTempId " + apprvTempId);

      System.out.println("assocTempId " + assocTempId);

      outputMsg=advisorListDataDAO.assetMgmtDataMove(assocTempId,"invdbtoaudit",apprvTempId,4l);
      System.out.println("Approve "+outputMsg);
      if(outputMsg.equalsIgnoreCase("success")){
         advisorListDataDAO.updateTemplateStatus("Predefined",apprvTempId,"validation","Approved");
         ModelUtil objModelUtil=new ModelUtil();
         objModelUtil.refreshData();
         outputMsg="Theme "+assocTempId+" updated succefully";
      }else{
         outputMsg="Ttheme "+assocTempId+" updation failed ";
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

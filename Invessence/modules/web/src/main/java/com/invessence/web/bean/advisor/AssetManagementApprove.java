package com.invessence.web.bean.advisor;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.web.dao.advisor.AdvisorListDataDAO;

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

   public void onApprove()
   {

      System.out.println("apprvTempId " + apprvTempId);

      System.out.println("assocTempId " + assocTempId);

      String Output=advisorListDataDAO.assetMgmtDataMove(assocTempId,"invdbtoaudit",apprvTempId);
      System.out.println("Approve "+Output);
      if(Output.equalsIgnoreCase("success")){
         advisorListDataDAO.updateTemplateStatus("Predefined",apprvTempId,"validation","Approved");
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
}

package com.invessence.bean.advisor;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.advisor.AdvisorListDataDAO;
import com.invessence.dao.common.CommonDAO;
import com.invessence.data.advisor.AdvisorDashData;
import com.invessence.util.*;

@ManagedBean(name = "adashbean")
@SessionScoped
public class AdvisorDashBean implements Serializable
{

   private AdvisorDashData advisorDashData = new AdvisorDashData();
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO listDAO;

   public void setListDAO(AdvisorListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;

   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public AdvisorDashData getAdvisorDashData()
   {
      return advisorDashData;
   }

   public void setAdvisorDashData(AdvisorDashData advisorDashData)
   {
      this.advisorDashData = advisorDashData;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(Const.WEB_ADVISOR)) {
               loadData();
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private void loadData() {
        try {
           advisorDashData = new AdvisorDashData(webutil.getLogonid(), webutil.getUserInfoData().getAdvisor());
           listDAO.advisorDashBoard(advisorDashData);
           advisorDashData.setNotification(commonDAO.getNotificationInfo(webutil.getLogonid()));
        }
        catch (Exception ex) {

        }
   }


}
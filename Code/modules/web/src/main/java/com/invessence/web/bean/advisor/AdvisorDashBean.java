package com.invessence.web.bean.advisor;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.*;
import com.invessence.web.dao.advisor.*;
import com.invessence.web.data.advisor.*;
import com.invessence.web.util.*;

@ManagedBean(name = "adashbean")
@SessionScoped
public class AdvisorDashBean extends AdvisorDashData implements Serializable
{

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(WebConst.ROLE_ADVISOR)) {
               setLogonid(webutil.getLogonid());
               setAdvisorname(webutil.getUserInfoData().getFullName());
               reloadData();
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}
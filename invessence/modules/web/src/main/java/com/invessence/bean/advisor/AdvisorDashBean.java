package com.invessence.bean.advisor;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import com.invessence.bean.consumer.Charts;
import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.advisor.*;
import com.invessence.data.MsgData;
import com.invessence.data.advisor.*;
import com.invessence.data.common.UserInfoData;
import com.invessence.util.*;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.CustomAllocation;
import com.invmodel.portfolio.data.*;
import org.primefaces.model.TreeNode;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "adbean")
@SessionScoped
public class AdvisorDashBean extends AdvisorDashData implements Serializable
{
   private static final long serialVersionUID = 100002L;

   @ManagedProperty("#{menu}")
   private Menu menu;
   public void setMenu(Menu menu)
   {
      this.menu = menu;
   }

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
            if (webutil.validatePriviledge(Const.ROLE_ADVISOR)) {
               setAdvisorname(webutil.getUserInfoData().getFullName());
               setTitle("Advisor");
               collectStat();
               collectSecurityInfo();
               collectNewsInfo();
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void collectStat()
   {

      try
      {
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void collectSecurityInfo()
   {

      try
      {
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void collectNewsInfo()
   {

      try
      {
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


}
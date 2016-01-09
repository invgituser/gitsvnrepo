package com.invessence.bean.ltam;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.converter.SQLData;
import com.invessence.dao.ltam.*;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.Impl.PagesImpl;
import com.invessence.util.WebUtil;
import com.ltammodel.LTAMOptimizer;
import com.ltammodel.data.*;
import org.primefaces.context.RequestContext;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ltampostback")
@SessionScoped
public class LTAMPostBack implements Serializable
{
   private String beanAdvisor;
   private String beanETID;
   private String beanAcctNum;
   SQLData converter = new SQLData();

   @ManagedProperty("#{ltamSaveDataDAO}")
   private LTAMSaveDataDAO saveDAO;

   public void setSaveDAO(LTAMSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }


   WebUtil webutil = new WebUtil();

   public String getBeanAdvisor()
   {
      return beanAdvisor;
   }

   public void setBeanAdvisor(String beanAdvisor)
   {
      this.beanAdvisor = beanAdvisor;
   }

   public String getBeanETID()
   {
      return beanETID;
   }

   public void setBeanETID(String beanETID)
   {
      this.beanETID = beanETID;
   }

   public String getBeanAcctNum()
   {
      return beanAcctNum;
   }

   public void setBeanAcctNum(String beanAcctNum)
   {
      this.beanAcctNum = beanAcctNum;
   }

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            // pagemanager.setPage(0);
            if (getBeanAcctNum() != null) {
               String msg = saveData();
            }
            else {
               webutil.redirect("/access-denied.xhtml", null);
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public String saveData() {
      String msg = null;
      try {
         if (beanETID != null) {
            msg = saveDAO.savePostBack(beanETID, beanAdvisor, beanAcctNum);
         }
      }
      catch (Exception ex) {

      }
    return msg;
   }

}


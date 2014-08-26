package com.invessence.bean;

import java.io.Serializable;
import java.text.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.Const;
import com.invessence.dao.PositionDAO;
import com.invessence.data.*;

@ManagedBean(name = "positionBean")
@SessionScoped
public class PositionBean implements Serializable
{
   private static final long serialVersionUID = 1003L;
   private List<Position> positionList;

   @ManagedProperty("#{positionDAO}")
   private PositionDAO positionDAO;

   private Integer totalshare;
   private Double totalvalue;
   private Double totalmoney;
   private Double totalpnl;
   private Long logonid;
   private Long acctnum;
   private String firstname, lastname, dateOpened, clientAccountID;

   @PostConstruct
   public void init()
   {
      String userName;
      try
      {
         if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) == null)
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void setPositionDAO(PositionDAO positionDAO)
   {
      this.positionDAO = positionDAO;
   }

   public PositionDAO getPositionDAO()
   {
      return positionDAO;
   }


   public boolean isPostback()
   {
      FacesContext context = FacesContext.getCurrentInstance();
      return context.getRenderKit().getResponseStateManager().isPostback(context);
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getDateOpened()
   {
      if (dateOpened != null) {
         return dateOpened.substring(0,4) +"-" + dateOpened.substring(4,6) + "-" +dateOpened.substring(6);
      }
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public void collectData()
   {
      try
      {
         if (this.acctnum != null) {
            positionList = positionDAO.loadDBPosition(this.logonid, this.acctnum);
            addTotals();
         }
      }
      catch (Exception ex)
      {
         System.out.println("Error in collect data from DB :" + ex.getMessage());
      }

   }

   public String findPosition(Long logonid, Long acctnum)
   {
      //Long logonid;
      //Long acctnum;
      try
      {
         setAcctnum(acctnum);
         setLogonid(logonid);
         collectData();
         return ("success");
      }
      catch (Exception e)
      {
         System.out.println("Error in collecting data from DB (position):" + e.getMessage());
      }
      return ("failed");
   }

   private void addTotals()
   {
      Integer rows;
      try
      {
         this.totalshare = 0;
         this.totalvalue = 0.0;
         this.totalmoney = 0.0;
         this.totalpnl = 0.0;
         if (positionList == null)
         {
            return;
         }
         rows = positionList.size();
         for (int loop = 0; loop < rows; loop++)
         {
            Position position = positionList.get(loop);
            if (loop == 0) {
               setFirstname(position.getFirstname());
               setLastname(position.getLastname());
               setDateOpened(position.getDateOpened());
               setClientAccountID(position.getClientAccountID());
            }
            this.totalshare = this.totalshare + position.getQty();
            this.totalmoney = this.totalmoney + position.getCostBasisMoney();
            this.totalvalue = this.totalvalue + position.getInvested();
            this.totalpnl = this.totalpnl + position.getFifoPnlUnrealized();
         }

      }
      catch (Exception ex)
      {
         System.out.println("Error when attempting to addTotal(position)" + ex.getMessage());
      }

   }

   public List<Position> getPositionList()
   {
      return positionList;
   }

   public void setPositionList(List<Position> positionList)
   {
      this.positionList = positionList;
   }

   public Integer getTotalshare()
   {
      return totalshare;
   }

   public Double getTotalvalue()
   {
      return totalvalue;
   }

   public Double getTotalmoney()
   {
      return totalmoney;
   }

   public void setTotalmoney(Double totalmoney)
   {
      this.totalmoney = totalmoney;
   }

   public Double getTotalpnl()
   {
      return totalpnl;
   }

   public void setTotalpnl(Double totalpnl)
   {
      this.totalpnl = totalpnl;
   }

   public String getPopulatetotalvalue()
   {
      if (getTotalvalue() != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,##0.00");
         String strValue = df.format(getTotalvalue());
         return "$" + strValue;
      }
      else
      {
         return "";
      }
   }

   public String getPopulatetotalshare()
   {
      if (getTotalshare() != null)
      {
         String strShare = NumberFormat.getIntegerInstance().format(getTotalshare());
         return strShare;
      }
      else
      {
         return "";
      }
   }

   public String getPopulatetotalmoney()
   {
      if (getTotalmoney() != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,##0.00");
         String strValue = df.format(getTotalmoney());
         return "$" + strValue;
      }
      else
      {
         return "";
      }
   }

   public String getPopulatetotalpnl()
   {
      if (getTotalpnl() != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,##0.00");
         String strValue = df.format(getTotalpnl());
         return "$" + strValue;
      }
      else
      {
         return "";
      }
   }

   public String reviseRisk() {
      return "success";
   }

}


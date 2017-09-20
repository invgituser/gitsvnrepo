package com.invessence.web.bean.consumer.ltam;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ltamposbean")
@SessionScoped
public class PositionBean implements Serializable
{
/*
   private SQLData converter = new SQLData();
   private String beanacctnum;
   private PositionCharts positionCharts  = new PositionCharts();
   private PositionSummaryData positionSummaryData;

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO consumerListDataDAO;
   public void setConsumerListDataDAO(ConsumerListDataDAO consumerListDataDAO)
   {
      this.consumerListDataDAO = consumerListDataDAO;
   }

   @ManagedProperty("#{webutil}")
   WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public PositionCharts getPositionCharts()
   {
      return positionCharts;
   }

   public PositionSummaryData getPositionSummaryData()
   {
      return positionSummaryData;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(Const.WEB_USER)) {
               Long logonid = webutil.getLogonid();
               collectPositionData(logonid, beanacctnum);
            }
         }
      }
      catch (Exception e)
      {
         webutil.redirecttoMessagePage("Error", "",
                                       "This session has expired.  Or you do not have access to this account."
         );
      }
   }

   public void collectPositionData(Long logonid, String beanacctnum)
   {
      try {
         if (beanacctnum != null && ! beanacctnum.isEmpty()) {
            Long acctnum = converter.getLongData(beanacctnum);
            if (acctnum != null && acctnum > 0) {
               positionSummaryData = consumerListDataDAO.getPositionData(logonid, acctnum);
               if (positionSummaryData == null) {
                  webutil.redirect("/access-denied.xhtml", null);
               }
               else {
                  positionCharts.createSummaryPieModel(positionSummaryData.getAssetMap());
               }
            }
         }
      }
      catch (Exception ex) {
         webutil.redirecttoMessagePage("Warning", "",
                                       "There is no data associated with this account number. <br/>" +
                                          "Please contact support."
         );
      }
   }

   private void showMessage(String msg) {

   }

*/

}


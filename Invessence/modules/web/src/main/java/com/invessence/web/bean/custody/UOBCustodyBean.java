package com.invessence.web.bean.custody;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.service.custody.CustodyService;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.web.util.WebUtil;
import org.apache.commons.logging.*;

/**
 * Created by abhangp on 11/10/2017.
 */
@ManagedBean(name = "uobCustodyBean")
@SessionScoped
public class UOBCustodyBean
{
   protected final Log logger = LogFactory.getLog(getClass());
   @ManagedProperty("#{uobCustodyService}")
   private CustodyService custodyService;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   private PagesImpl pagemanager = new PagesImpl(11);
   private long beanAcctNum;
   private boolean dsplExtIndAcctCat = false, dsplExtIndAcctInp = false, dsplExtJntAcctCat = false, dsplExtJntAcctInp = false, dsplAcctTyp = false;
   private boolean dspExtAcctPnl=false,dspNewAcctPnl=false,dspIntroAcctPnl=false,dsblSubmtBtn=true,dspJntTab=false;
   private String acctCat, extAcctInd, extAcctJnt, clientAcctId, slsPrsnNm,selIndAcctTyp, selJntAcctTyp;
   private Integer activeTab = 0;
   public void initCustody()
   {
      String msgheader;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (getBeanAcctNum() == 0)
            {
               msgheader = "dctd.100";
               getWebutil().redirecttoMessagePage("ERROR", "Access Denied", msgheader);
               return;
            }
            dspIntroAcctPnl=true;
            dsblSubmtBtn=true;
            dspJntTab=false;
            custodyService.fetch(new Long(3560));
//            // clear all data.
//            resetBaseTD();
//
//            setPagemanager(new PagesImpl(10));
//            setTdMasterData(new TDMasterData(getPagemanager(), getLongBeanacctnum()));
//            String loadVal = loadData();
//            Boolean check = false;
//
//            // If account is managed
//            if (loadVal.equalsIgnoreCase("ACCOUNTMANAGED"))
//            {
//               msgheader = "dctd.103";
//               getWebutil().redirecttoMessagePage("ERROR", "Access Denied", msgheader);
//               return;
//            }
//
//            if (!loadVal.equalsIgnoreCase("success"))
//            {
//               // msgheader = "dctd.102";
//               // webutil.redirecttoMessagePage("ERROR", "Access Denied", msgheader);
//               getWebutil().accessdenied();
//               return;
//            }
//
//            // load firm list for ACAt details
//            loadACATFirmList();
//            // Check all data to find out where they left off last time.
//            // Just don't display error. Just go to that page.
//            // Start fresh, clean and start from top page.
//            Boolean status = validateAllPage();
//            Integer currentPage = getPagemanager().getPage();
//            getPagemanager().initPage();
//            resetActiveTab(0);
//            getPagemanager().setLastPageVisited(currentPage);
//            getPagemanager().clearAllErrorMessage();
//            saveandOpenError = null;
         }
      }
      catch (Exception ex)
      {
         logger.info("Exception: raised during Starting TD CTO process.");
      }
   }

   public void onChangeValue()
   {
      if (getAcctCat().equalsIgnoreCase("yes"))
      {
         dsplAcctTyp = false;
         dsplExtIndAcctCat = true;
      }
      else
      {
         dsplAcctTyp = true;
         dsplExtIndAcctCat = false;
      }
      if (getExtAcctInd() != null)
      {
         if (getExtAcctInd().equalsIgnoreCase("yes"))
         {
            dsplExtIndAcctInp = true;
            dsplExtJntAcctCat = false;
         }
         else
         {
            dsplExtIndAcctInp = false;
            dsplExtJntAcctCat = true;
         }
      }
      else
      {
         dsplExtIndAcctInp = false;
         dsplExtJntAcctCat = false;
      }

      if (getExtAcctJnt() != null)
      {
         if (getExtAcctJnt().equalsIgnoreCase("yes"))
         {
            dsplExtJntAcctInp = true;
         }
         else
         {
            dsplExtJntAcctInp = false;
         }
      }
      else
      {
         dsplExtJntAcctInp = false;
      }
   }

   public void onSelAcctTypAsInd()
   {
      setSelJntAcctTyp(null);
      System.out.println("selIndAcctTyp " + selIndAcctTyp);
      System.out.println("selJntAcctTyp " + selJntAcctTyp);
   }

   public void onSelAcctTypAsJnt()
   {
      setSelIndAcctTyp(null);
      System.out.println("selIndAcctTyp " + selIndAcctTyp);
      System.out.println("selJntAcctTyp " + selJntAcctTyp);
   }

   public void introNextPage()
   {
      dspIntroAcctPnl=false;
      getPagemanager().setPage(0);
      if(getAcctCat().equalsIgnoreCase("yes")){
         dspExtAcctPnl=true;
         dspNewAcctPnl=false;
         setPagemanager(new PagesImpl(2));
         Integer currentPage = getPagemanager().getPage();
         getPagemanager().initPage();
//         resetActiveTab(0);
         getPagemanager().setLastPageVisited(currentPage);
         getPagemanager().clearAllErrorMessage();

      }else if(getAcctCat().equalsIgnoreCase("no")){

         setPagemanager(new PagesImpl(8));
         Integer currentPage = getPagemanager().getPage();
         getPagemanager().initPage();
//         resetActiveTab(0);
         getPagemanager().setLastPageVisited(currentPage);
         getPagemanager().clearAllErrorMessage();
         dspExtAcctPnl=false;
         dspNewAcctPnl=true;
      }else{
         System.out.println("Need to add in validation condition");
      }
   }
   public void nextPage()
   {
      System.out.println("page current"+getPagemanager().getPage());
      getPagemanager().nextPage();
      System.out.println("page next"+getPagemanager().getPage());
      activeTab=getPagemanager().getPage();
   }
   public void prevPage()
   {
      System.out.println("page current"+getPagemanager().getPage());
      getPagemanager().prevPage();
      System.out.println("page prev"+getPagemanager().getPage());
      activeTab=getPagemanager().getPage();
   }

   public Boolean isAccordianDisabled(Integer pageno)
   {
      if (pageno != null)
      {
         if (pageno > 0 && pageno < getPagemanager().getMaxNoofPages())
         {
            if (getPagemanager().getLastPageVisited() >= pageno)
            {
               return false;
            }
         }
      }
      return true;
   }

   private void resetActiveTab(Integer pagenum)
   {
//      Integer nextTab = pageControl(pagenum);
//      activeTab = nextTab;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public long getBeanAcctNum()
   {
      return beanAcctNum;
   }

   public void setBeanAcctNum(long beanAcctNum)
   {
      this.beanAcctNum = beanAcctNum;
   }

   public CustodyService getCustodyService()
   {
      return custodyService;
   }

   public void setCustodyService(CustodyService custodyService)
   {
      this.custodyService = custodyService;
   }

   public boolean isDsplExtIndAcctCat()
   {
      return dsplExtIndAcctCat;
   }

   public void setDsplExtIndAcctCat(boolean dsplExtIndAcctCat)
   {
      this.dsplExtIndAcctCat = dsplExtIndAcctCat;
   }

   public boolean isDsplExtJntAcctCat()
   {
      return dsplExtJntAcctCat;
   }

   public void setDsplExtJntAcctCat(boolean dsplExtJntAcctCat)
   {
      this.dsplExtJntAcctCat = dsplExtJntAcctCat;
   }

   public String getAcctCat()
   {
      return acctCat;
   }

   public void setAcctCat(String acctCat)
   {
      this.acctCat = acctCat;
   }

   public boolean isDsplAcctTyp()
   {
      return dsplAcctTyp;
   }

   public void setDsplAcctTyp(boolean dsplAcctTyp)
   {
      this.dsplAcctTyp = dsplAcctTyp;
   }

   public String getExtAcctInd()
   {
      return extAcctInd;
   }

   public void setExtAcctInd(String extAcctInd)
   {
      this.extAcctInd = extAcctInd;
   }

   public String getExtAcctJnt()
   {
      return extAcctJnt;
   }

   public void setExtAcctJnt(String extAcctJnt)
   {
      this.extAcctJnt = extAcctJnt;
   }

   public boolean isDsplExtIndAcctInp()
   {
      return dsplExtIndAcctInp;
   }

   public void setDsplExtIndAcctInp(boolean dsplExtIndAcctInp)
   {
      this.dsplExtIndAcctInp = dsplExtIndAcctInp;
   }

   public boolean isDsplExtJntAcctInp()
   {
      return dsplExtJntAcctInp;
   }

   public void setDsplExtJntAcctInp(boolean dsplExtJntAcctInp)
   {
      this.dsplExtJntAcctInp = dsplExtJntAcctInp;
   }

   public String getClientAcctId()
   {
      return clientAcctId;
   }

   public void setClientAcctId(String clientAcctId)
   {
      this.clientAcctId = clientAcctId;
   }

   public String getSlsPrsnNm()
   {
      return slsPrsnNm;
   }

   public void setSlsPrsnNm(String slsPrsnNm)
   {
      this.slsPrsnNm = slsPrsnNm;
   }

   public String getSelIndAcctTyp()
   {
      return selIndAcctTyp;
   }

   public void setSelIndAcctTyp(String selIndAcctTyp)
   {
      this.selIndAcctTyp = selIndAcctTyp;
   }

   public String getSelJntAcctTyp()
   {
      return selJntAcctTyp;
   }

   public void setSelJntAcctTyp(String selJntAcctTyp)
   {
      this.selJntAcctTyp = selJntAcctTyp;
   }

   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }

   public void setPagemanager(PagesImpl pagemanager)
   {
      this.pagemanager = pagemanager;
   }

   public boolean isDspExtAcctPnl()
   {
      return dspExtAcctPnl;
   }

   public void setDspExtAcctPnl(boolean dspExtAcctPnl)
   {
      this.dspExtAcctPnl = dspExtAcctPnl;
   }

   public boolean isDspNewAcctPnl()
   {
      return dspNewAcctPnl;
   }

   public void setDspNewAcctPnl(boolean dspNewAcctPnl)
   {
      this.dspNewAcctPnl = dspNewAcctPnl;
   }

   public boolean isDspIntroAcctPnl()
   {
      return dspIntroAcctPnl;
   }

   public void setDspIntroAcctPnl(boolean dspIntroAcctPnl)
   {
      this.dspIntroAcctPnl = dspIntroAcctPnl;
   }

   public boolean isDsblSubmtBtn()
   {
      return dsblSubmtBtn;
   }

   public void setDsblSubmtBtn(boolean dsblSubmtBtn)
   {
      this.dsblSubmtBtn = dsblSubmtBtn;
   }

   public boolean isDspJntTab()
   {
      return dspJntTab;
   }

   public void setDspJntTab(boolean dspJntTab)
   {
      this.dspJntTab = dspJntTab;
   }

   public Integer getActiveTab()
   {
      return activeTab;
   }

   public void setActiveTab(Integer activeTab)
   {
      this.activeTab = activeTab;
   }
}

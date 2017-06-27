package com.invessence.web.bean.advisor;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.common.*;
import com.invessence.web.dao.consumer.ConsumerSaveDataDAO;
import com.invessence.web.data.common.*;
import com.invessence.web.io.TradeWriter;
import com.invessence.web.util.*;
import com.invmodel.rebalance.RebalanceProcess;
import com.invmodel.rebalance.data.*;
import org.primefaces.event.*;
import org.primefaces.model.*;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "tradeBean")
@SessionScoped
public class TradeBean extends TradeClientData implements Serializable
{
   private static final long serialVersionUID = -8001L;

   private List<TradeClientData> tradeClientDataList;
   private List<TradeClientData> filteredClientList;
   private List<TradeClientData> selectedClientList;
   private TradeClientData selectedClient;
   private String[] selectedFilterOptions;
   private String[] selectedReviewFilterOptions;
   private String nextRebalDate;

   private List<TradeSummary> tradeSummaryData;
   private List<TradeSummary> filteredSummaryList;
   private List<TradeSummary> selectedSummaryList;
   private TradeSummary selectedSummary;

   private List<RebalanceTradeData> rebalancetradedatalist;

   private List<String> tradeFilters;


   @ManagedProperty("#{filesIO}")
   private FilesIO fileIO;

   @ManagedProperty("#{tradeDAO}")
   private TradeDAO tradeDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO csdDAO;


   @ManagedProperty("#{rebalanceProcess}")
   private RebalanceProcess rebalProcess;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   public FilesIO getFileIO()
   {
      return fileIO;
   }

   public void setFileIO(FilesIO fileIO)
   {
      this.fileIO = fileIO;
   }

   @PostConstruct
   public void init()
   {
      tradeFilters = new ArrayList<String>();
      tradeFilters.add("New");
      tradeFilters.add("Date");
      tradeFilters.add("Allocation");
      tradeFilters.add("Other");
   }


   public void setCsdDAO(ConsumerSaveDataDAO csdDAO)
   {
      this.csdDAO = csdDAO;
   }

   public void setTradeDAO(TradeDAO tradeDAO)
   {
      this.tradeDAO = tradeDAO;
   }

   public void setRebalProcess(RebalanceProcess rebalProcess)
   {
      this.rebalProcess = rebalProcess;
   }

   public List<TradeClientData> getTradeClientDataList()
   {
      return tradeClientDataList;
   }

   public void setTradeClientDataList(List<TradeClientData> tradeClientDataList)
   {
      this.tradeClientDataList = tradeClientDataList;
   }

   public List<TradeClientData> getFilteredClientList()
   {
      return filteredClientList;
   }

   public void setFilteredClientList(List<TradeClientData> filteredClientList)
   {
      this.filteredClientList = filteredClientList;
   }

   public String[] getSelectedFilterOptions()
   {
      return selectedFilterOptions;
   }

   public void setSelectedFilterOptions(String[] selectedFilterOptions)
   {
      this.selectedFilterOptions = selectedFilterOptions;
   }

   public String[] getSelectedReviewFilterOptions()
   {
      return selectedReviewFilterOptions;
   }

   public void setSelectedReviewFilterOptions(String[] selectedReviewFilterOptions)
   {
      this.selectedReviewFilterOptions = selectedReviewFilterOptions;
   }

   public List<TradeClientData> getSelectedClientList()
   {
      return selectedClientList;
   }

   public void setSelectedClientList(List<TradeClientData> selectedClientList)
   {
      this.selectedClientList = selectedClientList;
   }

   public String getNextRebalDate()
   {
      return nextRebalDate;
   }

   public void setNextRebalDate(String nextRebalDate)
   {
      this.nextRebalDate = nextRebalDate;
   }

   public TradeClientData getSelectedClient()
   {
      return selectedClient;
   }

   public void setSelectedClient(TradeClientData selectedClient)
   {
      this.selectedClient = selectedClient;
   }

   public List<TradeSummary> getTradeSummaryData()
   {
      return tradeSummaryData;
   }

   public void setTradeSummaryData(List<TradeSummary> tradeSummaryData)
   {
      this.tradeSummaryData = tradeSummaryData;
   }

   public List<TradeSummary> getFilteredSummaryList()
   {
      return filteredSummaryList;
   }

   public void setFilteredSummaryList(List<TradeSummary> filteredSummaryList)
   {
      this.filteredSummaryList = filteredSummaryList;
   }

   public List<TradeSummary> getSelectedSummaryList()
   {
      return selectedSummaryList;
   }

   public void setSelectedSummaryList(List<TradeSummary> selectedSummaryList)
   {
      this.selectedSummaryList = selectedSummaryList;
   }

   public TradeSummary getSelectedSummary()
   {
      return selectedSummary;
   }

   public void setSelectedSummary(TradeSummary selectedSummary)
   {
      this.selectedSummary = selectedSummary;
   }

   public List<String> getTradeFilters()
   {
      return tradeFilters;
   }

   public void setTradeFilters(List<String> tradeFilters)
   {
      this.tradeFilters = tradeFilters;
   }

   public List<RebalanceTradeData> getRebalancetradedatalist()
   {
      return rebalancetradedatalist;
   }

   public void preRender()
   {
      Long logonid;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(WebConst.ROLE_ADVISOR))
            {
               collectTradeInfo();
            }
         }
      }
      catch (Exception e)
      {
      }
   }

   private void showGrowl(String msg, String msgType)
   {
      try
      {
         if (msgType.startsWith("E"))
         {
            FacesContext.getCurrentInstance().addMessage("growltrademsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         }
         else if (msgType.startsWith("W"))
         {
            FacesContext.getCurrentInstance().addMessage("growltrademsg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
         }
         else
         {
            FacesContext.getCurrentInstance().addMessage("growltrademsg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
         }

      }
      catch (Exception ex)
      {
         FacesContext.getCurrentInstance().addMessage("growltrademsg",
                                                      new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Error", "Please contact support desk."));
      }
   }

/*
   public void onTabChange(TabChangeEvent event)
   {
      try
      {

         if (event != null)
         {
            String tabname = event.getTab().getId();
            String tabnum = tabname.substring(3);
            Integer num = webutil.getConverter().getIntData(tabnum);
            switch (num)
            {
               case 0:
                  reloadTradeClient();
                  break;
               case 1:
                  reloadTradeSummary();
                  break;
            }
         }
      }
      catch (Exception ex)
      {

         //setActiveTab(0);
      }
   }
*/


   private void collectTradeInfo()
   {
      try
      {
         Long logonid = webutil.getLogonid();
         tradeClientDataList = tradeDAO.getTradeProfileData(logonid, "N");
         filterClientData2Display();
      }
      catch (Exception ex)
      {
         String msg = "Error in collecting Clients To Trade" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();
      }
   }


   private void filterClientData2Display()
   {
      Integer numOptions = 0;
      Boolean skip;
      try
      {
         if (getSelectedFilterOptions() == null || getSelectedFilterOptions().length == 0)
         {
            filteredClientList = getTradeClientDataList();
         }
         else
         {
            numOptions = getSelectedFilterOptions().length;
            filteredClientList = new ArrayList<TradeClientData>();

            for (Integer loop = 0; loop < getTradeClientDataList().size(); loop++)
            {
               skip = false;
               Integer opt = 0;
               String filter, tradeDataFilter;
                  while (!skip && opt < numOptions)
               {
                  filter = getSelectedFilterOptions()[opt];
                  tradeDataFilter = getTradeClientDataList().get(loop).getTradeStatus();
                  if (filter.startsWith(tradeDataFilter))
                  {
                     skip = true;
                  }
                  else
                  {
                     opt++;
                  }
               }
               if (skip)
               {
                  filteredClientList.add(getTradeClientDataList().get(loop));
               }
            }
         }
      }
      catch (Exception ex)
      {

      }
   }

   public void reloadTradeClient()
   {
      //tradeDAO.deletePendingTrades();
      collectTradeInfo();
      String msg = getFilteredClientList().size() + " Account(s) were loaded.";
      showGrowl(msg, "Info");

   }

   private void collectTradeDetails(Long acctnum)
   {
      try
      {
         rebalancetradedatalist = tradeDAO.loadRebalTrades(acctnum);
      }
      catch (Exception ex)
      {
         String msg = "Error in collecting Rebalance Trade Details" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();
      }
   }

   public void refreshFilteredClient()
   {
      filterClientData2Display();
   }

   public void saveNextRebalDate()
   {
      String saveDate;
      try
      {
         saveDate = getNextRebalDate().substring(6, 10) + getNextRebalDate().substring(0, 2) + getNextRebalDate().substring(3, 5);
         tradeDAO.saveNextRebalDate(saveDate);
      }
      catch (Exception ex)
      {
         String msg = "Error in saving Next Rebalance Date" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();

      }
   }

   public void doManagedAction()
   {
      try
      {
         if (getSelectedClient() != null)
         {
            uiLayout.doMenuAction("consumer", "cadd.xhtml?app=E&acct=" + getSelectedClient().getAcctnum().toString());
         }
      }
      catch (Exception ex)
      {
         return;
      }

   }

   public void showTradeUI(Integer whichScreen)
   {
      if (whichScreen == null)
         whichScreen = 1;

      switch (whichScreen)
      {
         case 1:
            reloadTradeClient();
            uiLayout.doMenuAction("advisor", "trade/operations/trade.xhtml");
            break;
         case 2:
            reloadTradeSummary();
            uiLayout.doMenuAction("advisor", "trade/operations/reviewTrades.xhtml");
            break;
         case 3:
            if (getSelectedSummary() != null)
            {
               collectTradeDetails(getSelectedSummary().getAcctnum());
               uiLayout.doMenuAction("advisor", "trade/operations/tradesummary.xhtml");
            }
            break;
         default:
            uiLayout.doMenuAction("advisor", "trade/operations/trade.xhtml");
      }
   }


   public void createTrades(String mode)
   {
      TradeClientData tData = null;
      Long logonid;

      ArrayList<RebalanceTradeData> tradedata = null;
      try
      {
         if (!mode.equalsIgnoreCase("I"))
         {
            setSelectedClientList(getFilteredClientList());
         }

         if (getSelectedClientList() != null)
         {
            webutil.progessreset();
            logonid = webutil.getLogonid();
            Integer numClients = getSelectedClientList().size();
            for (Integer loop = 0; loop < numClients; loop++)
            {
               webutil.setProgressbar((loop / numClients) * 100);
               tData = getSelectedClientList().get(loop);
               tradedata = rebalProcess.process(logonid, tData.getAcctnum());
               if (tradedata != null)
               {
                  tData.setProcessStatus("R");
                  tradeDAO.saveTradeProcessIdentifier(tData.getAcctnum(), tData.getTradeStatus(), tData.getProcessStatus(), tData.getReason());
               }

            }
            String msg = "";
            if (tradedata == null || tradedata.size() == 0)
            {
               msg = "No customers were processed to create trades.  Action ignored!";
               showGrowl(msg, "Warning");
            }
            else if (tradedata != null && tradedata.size() > 0)
            {
               if (numClients == 1)
               {
                  msg = "Account#: " + tData.getAcctnum().toString() + " was processed, successfully";
               }
               else
               {
                  msg = numClients.toString() + " accounts were processed, successfully";
               }
               showGrowl(msg, "Info");
            }

         }

      }
      catch (Exception ex)
      {
         String msg = "Error in creating trades" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();
      }
   }

   private String getFileNameWithDateAndTime(String prefix)
   {
      Date date = new Date();
      SimpleDateFormat dateFormatWithDateAndTime = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
      return dateFormatWithDateAndTime.format(date) + prefix + ".csv";
   }

   public void reloadTradeSummary()
   {
      //tradeDAO.deletePendingTrades();
      collectTradeSummary();
      String msg = getFilteredSummaryList().size() + " Account(s) were loaded.";
      showGrowl(msg, "Info");

   }


   private void collectTradeSummary()
   {
      try
      {
         Long logonid = webutil.getLogonid();
         tradeSummaryData = tradeDAO.getTradeSummaryData(logonid, "R");
         filterSummaryData2Display();
      }
      catch (Exception ex)
      {
         String msg = "Error in collecting Clients To Trade" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();
      }
   }


   private void filterSummaryData2Display()
   {
      Integer numOptions = 0;
      Boolean skip;
      try
      {
         if (getSelectedReviewFilterOptions() == null || getSelectedReviewFilterOptions().length == 0)
         {
            filteredSummaryList = getTradeSummaryData();
         }
         else
         {
            numOptions = getSelectedReviewFilterOptions().length;
            filteredSummaryList = new ArrayList<TradeSummary>();

            for (Integer loop = 0; loop < getTradeSummaryData().size(); loop++)
            {
               skip = false;
               Integer opt = 0;
               String filter, tradeDataFilter;
               while (!skip && opt < numOptions)
               {
                  filter = getSelectedReviewFilterOptions()[opt];
                  tradeDataFilter = getTradeSummaryData().get(loop).getTradeStatus();
                  if (filter.startsWith(tradeDataFilter))
                  {
                     skip = true;
                  }
                  else
                  {
                     opt++;
                  }
               }
               if (skip)
               {
                  filteredSummaryList.add(getTradeSummaryData().get(loop));
               }
            }
         }
      }
      catch (Exception ex)
      {

      }
   }

   public void generateTrades(String mode)
   {
      Long logonid;

      try
      {
         TradeSummary tData = null;

         if (!mode.equalsIgnoreCase("I"))
         {
            setSelectedSummaryList(getFilteredSummaryList());
         }

         if (getSelectedSummaryList() != null)
         {
            webutil.progessreset();
            logonid = webutil.getLogonid();
            Integer numClients = getSelectedSummaryList().size();
            for (Integer loop = 0; loop < numClients; loop++)
            {
               webutil.setProgressbar((loop / numClients) * 100);
               tData = getSelectedSummaryList().get(loop);
               // Move each of these trades into executed mode.
               tradeDAO.executeTrade(tData.getAcctnum());
               tData.setProcessStatus("S");
            }
            String product = webutil.getWebprofile().getWebInfo().get("SERVICE.PRODUCT");
            String serviceMode = webutil.getWebprofile().getWebInfo().get("SERVICE.FILEPROCESS.MODE");
            fileIO.processDownloadFile("DOWD1", product, serviceMode);

            // If for some reason, there is an exception, then the trades remain in the same list here.
            // If there is no error from generating file, then move all of them as processed.
            for (Integer loop = 0; loop < numClients; loop++)
            {
               webutil.setProgressbar((loop / numClients) * 100);
               tData = getSelectedSummaryList().get(loop);
               // Move each of these trades into executed mode.
               tradeDAO.saveTradeProcessIdentifier(tData.getAcctnum(), tData.getTradeStatus(), tData.getProcessStatus(), "Processed");
            }

            // Finally show the status.
            String msg = "";
            if (numClients == 0)
            {
               msg = "No customers were processed to create trades.  Action ignored!";
               showGrowl(msg, "Warning");
            }
            else if (numClients == 1)
            {
               msg = "Account#: " + tData.getAcctnum().toString() + " was processed, successfully";
            }
            else
            {
               msg = numClients.toString() + " accounts were processed, successfully";
            }
            showGrowl(msg, "Info");

            // Refresh the list.
            reloadTradeSummary();
         }

      }
      catch (Exception ex)
      {
         String msg = "Error in creating trades" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();
      }
   }


}
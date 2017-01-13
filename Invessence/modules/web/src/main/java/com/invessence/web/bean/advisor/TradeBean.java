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
import com.invessence.web.util.WebUtil;
import com.invmodel.rebalance.RebalanceProcess;
import com.invmodel.rebalance.data.*;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "tradeBean")
@SessionScoped
public class TradeBean extends TradeClientData implements Serializable
{
   private static final long serialVersionUID = -8001L;

   private List<TradeClientData> tradeClientDataList;
   private List<TradeClientData> filteredClientList;
   private List<TradeClientData> selectedClientList;
   private List<String> selectedFilterOptions;
   private String nextRebalDate;

   private Map<String, TradeSummary> tradeDetailsData = null;
   private TradeClientData selectedClient;
   private TradeSummary selectedTradeSummary;

   @ManagedProperty("#{tradeDAO}")
   private TradeDAO tradeDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO csdDAO;

/*
   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO consumerSaveDataDAO;


   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;
*/

   @ManagedProperty("#{rebalanceProcess}")
   private RebalanceProcess rebalProcess;


   private TradeWriter tw = new TradeWriter();
   @Autowired
   private WebUtil webutil;

/*
   public void setCommonListDataDAO(CommonDAO commonDAO)
   {
      this.commonListDataDAO = commonDAO;
   }
*/

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

   public List<String> getSelectedFilterOptions()
   {
      return selectedFilterOptions;
   }

   public void setSelectedFilterOptions(List<String> selectedFilterOptions)
   {
      this.selectedFilterOptions = selectedFilterOptions;
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

   public Map<String, TradeSummary> getTradeDetailsData()
   {
      return tradeDetailsData;
   }

   public void setTradeDetailsData(Map<String, TradeSummary> tradeDetailsData)
   {
      this.tradeDetailsData = tradeDetailsData;
   }

   public ArrayList<TradeSummary> getTradeSummary() {
      ArrayList<TradeSummary> tradeSummary = new ArrayList<TradeSummary>();
      if (tradeDetailsData != null) {
         for (TradeSummary ts : tradeDetailsData.values())
            tradeSummary.add(ts);
      }

      return tradeSummary;
   }

   public TradeClientData getSelectedClient()
   {
      return selectedClient;
   }

   public void setSelectedClient(TradeClientData selectedClient)
   {
      this.selectedClient = selectedClient;
   }

   public TradeSummary getSelectedTradeSummary()
   {
      return selectedTradeSummary;
   }

   public void setSelectedTradeSummary(TradeSummary selectedTradeSummary)
   {
      this.selectedTradeSummary = selectedTradeSummary;
   }

   public void init()
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

   private void showGrowl(String msg, String msgType) {
      try {
         if (msgType.startsWith("E"))
            FacesContext.getCurrentInstance().addMessage("growltrademsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         else if (msgType.startsWith("W"))
            FacesContext.getCurrentInstance().addMessage("growltrademsg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
         else
            FacesContext.getCurrentInstance().addMessage("growltrademsg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

      }
      catch (Exception ex) {
         FacesContext.getCurrentInstance().addMessage("growltrademsg",
                                                      new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Error", "Please contact support desk."));
      }
   }

   private void collectTradeInfo()
   {
      try
      {
         tradeClientDataList = tradeDAO.getTradeProfileData("P");
         filterClientData2Display();
      }
      catch (Exception ex)
      {
         String msg = "Error in collecting Clients To Trade" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();
      }
   }


   private void filterClientData2Display() {
      Integer numOptions=0;
      Boolean skip;
      try {
         if (getSelectedFilterOptions() == null || getSelectedFilterOptions().size() == 0) {
            filteredClientList = getTradeClientDataList();
         }
         else {
            numOptions = getSelectedFilterOptions().size();
            filteredClientList = new ArrayList<TradeClientData>();

            for (Integer loop=0; loop < getTradeClientDataList().size(); loop++) {
               skip=false;
               Integer opt=0;
               String filter;
               while (! skip && opt < numOptions) {
                  if (getTradeClientDataList().get(loop).getReason().startsWith(getSelectedFilterOptions().get(opt)))
                     skip=true;
                  else
                     opt++;
               }
               if (!skip)
                  filteredClientList.add(getTradeClientDataList().get(loop));
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public void reloadTradeClient() {
      getSelectedFilterOptions().clear();
      setSelectedFilterOptions(null);
      tradeDAO.deletePendingTrades();
      collectTradeInfo();
      String msg = getTradeClientDataList().size() + " Account(s) were loaded.";
      showGrowl(msg, "Info");

   }

   public void refreshTradeClient() {
      filterClientData2Display();
   }

   public void saveNextRebalDate()
   {
      String saveDate;
      try
      {
         saveDate = getNextRebalDate().substring(6,10) + getNextRebalDate().substring(0,2) + getNextRebalDate().substring(3,5);
         tradeDAO.saveNextRebalDate(saveDate);
      }
      catch (Exception ex)
      {
         String msg = "Error in saving Next Rebalance Date" + ex.getMessage();
         showGrowl(msg, "Error");
         ex.printStackTrace();

      }
   }

   public String doManagedAction()
   {
      try
      {
         if (getSelectedClient() == null)
            return "failed";
         else
            webutil.redirect("/consumer/cadd.xhtml?acct="+getSelectedClient().getAcctnum().toString(),null);
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public void onCellEdit(CellEditEvent event) {
      Object oldValue = event.getOldValue();
      Object newValue = event.getNewValue();

      if(newValue != null && !newValue.equals(oldValue)) {
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
         FacesContext.getCurrentInstance().addMessage(null, msg);
      }
   }

   public void createtlhTrades()
   {
      TradeClientData tData = null;
      Long logonid;
      ArrayList<RebalanceTradeData> tradedata = null;
      try
      {
         if (getSelectedClientList() != null)
         {
            webutil.progessreset();
            logonid = webutil.getLogonid();
            Integer numClients = getSelectedClientList().size();
            for (Integer loop = 0; loop < numClients; loop++)
            {
               webutil.setProgressbar((loop/numClients) * 100);
               tData = getSelectedClientList().get(loop);
               tradedata = rebalProcess.process(logonid, tData.getAcctnum());
            }
            String msg = "";
            if (tradedata == null  || tradedata.size() == 0) {
               msg = "No customers were processed to create trades.  Action ignored!";
               showGrowl(msg, "Warning");
            }
            else if (tradedata != null && tradedata.size() > 0) {
               if (numClients == 1) {
                  msg = "Account#: " + tData.getAcctnum().toString() + " was processed, successfully";
               }
               else {
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

   public void createsqlTrades()
   {
      TradeClientData data;
      try
      {
         if (getSelectedClientList() != null)
         {
            Integer numClients = 0;
            for (Integer loop = 0; loop < numClients; loop++)
            {
               data = getSelectedClientList().get(loop);
               if (data != null)
               {
                  this.getInstance().copyData((CustomerData) data);
                  this.getInstance().setNumOfAllocation(1);
                  this.getInstance().setNumOfPortfolio(1);
                  this.getInstance().buildAssetClass();
                  this.getInstance().buildPortfolio();
                  csdDAO.saveAllocation(this.getInstance());
                  csdDAO.savePortfolio(this.getInstance());
                  csdDAO.createTrades(this.getInstance().getAcctnum());
                  numClients++;
               }
            }
            if (numClients > 0) {
               String msg = numClients.toString() + " trade(s) were created, successfully";
               showGrowl(msg, "Info");
            }
            else {
               String msg = " No customers were seletected to create trades.  Action ignored!";
               showGrowl(msg, "Warning");
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

   public void collectTradeSummary()
   {
      webutil.getProgressbar();
      if (getTradeDetailsData() != null)
         getTradeDetailsData().clear();
      setTradeDetailsData(tradeDAO.loadTradesDetails(null));
      addTotals();
      webutil.setProgressbar(100);
   }

   private void addTotals() {
      if (getTradeDetailsData() != null && getTradeDetailsData().size() > 0) {
         for (TradeSummary ts : getTradeDetailsData().values())
            ts.addTotals();
      }
   }
   private String getFileNameWithDateAndTime(String prefix)
   {
      Date date = new Date();
      SimpleDateFormat dateFormatWithDateAndTime = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
      return dateFormatWithDateAndTime.format(date) + prefix + ".csv";
   }

   String tradeFile1, tradeFile2, allocFile1, allocFile2;
   public void generateTrades()
   {
      try {
         String userHomeDir = null;
         Properties prop = new Properties();
         InputStream propInput = null;
         HttpServletRequest req = (HttpServletRequest) getCurrentInstance().getExternalContext().getRequest();
         propInput = req.getSession().getServletContext().getResourceAsStream("/WEB-INF/classes/invessence.properties");
         prop.load(propInput);
         userHomeDir = prop.getProperty("doc.location");
         // System.out.println("Data File Location :" + userHomeDir);
         webutil.getProgressbar();

         tradeDAO.createTrades(null);
         List<Map<String, Object>> data;
         data = tradeDAO.getTradeData();
         tradeFile1 = getFileNameWithDateAndTime("_TRADES_BUY");
         tradeFile1 = userHomeDir + tradeFile1;
         allocFile1 = getFileNameWithDateAndTime("_ALLOC_BUY");
         allocFile1 =  userHomeDir + allocFile1;
         tw.downloadTradesAllocationFile(data, tradeFile1, allocFile1, "BUY");
         tradeFile2 = getFileNameWithDateAndTime("_TRADES_SELL");
         tradeFile2 = userHomeDir + tradeFile2;
         allocFile2 = getFileNameWithDateAndTime("_ALLOC_SELL");
         allocFile2 =  userHomeDir + allocFile2;
         tw.downloadTradesAllocationFile(data, tradeFile2, allocFile2, "SELL");
         tradeDAO.updateExecutedTrades();
         webutil.setProgressbar(100);
         String msg = "Trade and Allocation files produced";
         showGrowl(msg, "Info");
         // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
      }
      catch (Exception ex) {
         String msg = "Error when producing file" + ex.getMessage();
         showGrowl(msg, "Error");
         // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         ex.printStackTrace();
      }
   }

   public StreamedContent getTrade1()
   {
      return downloadFile(tradeFile1, "TradeBUY.csv");
   }

   public StreamedContent getTrade2()
   {
      return downloadFile(tradeFile2, "TradeSELL.csv");
   }

   public StreamedContent getAlloc1()
   {
      return downloadFile(allocFile1, "AllocBUY.csv");
   }

   public StreamedContent getAlloc2()
   {
      return downloadFile(allocFile2, "AllocSELL.csv");
   }

   private StreamedContent downloadFile(String name, String outputName) {
      StreamedContent file = null;
      try {
         InputStream stream = new FileInputStream(new File(name))
         {
            @Override
            public int read() throws IOException
            {
               return 0;
            }
         };
         file = new DefaultStreamedContent(stream, "application/csv", outputName);
         return file;
      }
      catch (Exception ex) {

      }
      return file;
   }

}
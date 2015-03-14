package com.invessence.data.admin;

import java.util.List;

import com.invessence.data.common.*;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.portfolio.PortfolioModel;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class AdminData
{
   private AssetAllocationModel allocModel;
   private PortfolioModel portfolioModel;

   private String userid;
   private String logoid;
   private Long acctnum;
   private String email;
   private String name;
   private String clientAccountID;
   private String IBStatus;
   private String filter = "All";
   private Integer riskIndex;
   private Long investment;
   private IBData selectedIB;
   private InvessenceData selectedInv;
   private Long acctFound;
   private Integer modifyFlag;
   private String nextRebalDate;
   private List<String> filteredOption;

   private List<IBData> ibList;
   private List<InvessenceData> invessenceList;
   private List<TradeClientData> tradeDataList;
   private TradeClientData[] selectedTradeAccounts;
   private TradeClientData singleTradeAccount;
   private List<TradeDetails> tradedetails;

   public AssetAllocationModel getAllocModel()
   {
      return allocModel;
   }

   public void setAllocModel(AssetAllocationModel allocModel)
   {
      this.allocModel = allocModel;
   }

   public PortfolioModel getPortfolioModel()
   {
      return portfolioModel;
   }

   public void setPortfolioModel(PortfolioModel portfolioModel)
   {
      this.portfolioModel = portfolioModel;
   }

   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
   }

   public String getLogoid()
   {
      return logoid;
   }

   public void setLogoid(String logoid)
   {
      this.logoid = logoid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getIBStatus()
   {
      return IBStatus;
   }

   public void setIBStatus(String IBStatus)
   {
      this.IBStatus = IBStatus;
   }

   public String getFilter()
   {
      return filter;
   }

   public void setFilter(String filter)
   {
      this.filter = filter;
   }

   public Integer getRiskIndex()
   {
      return riskIndex;
   }

   public void setRiskIndex(Integer riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public Long getInvestment()
   {
      return investment;
   }

   public void setInvestment(Long investment)
   {
      this.investment = investment;
   }

   public IBData getSelectedIB()
   {
      return selectedIB;
   }

   public void setSelectedIB(IBData selectedIB)
   {
      this.selectedIB = selectedIB;
   }

   public InvessenceData getSelectedInv()
   {
      return selectedInv;
   }

   public void setSelectedInv(InvessenceData selectedInv)
   {
      this.selectedInv = selectedInv;
   }

   public Long getAcctFound()
   {
      return acctFound;
   }

   public void setAcctFound(Long acctFound)
   {
      this.acctFound = acctFound;
   }

   public Integer getModifyFlag()
   {
      return modifyFlag;
   }

   public void setModifyFlag(Integer modifyFlag)
   {
      this.modifyFlag = modifyFlag;
   }

   public List<IBData> getIbList()
   {
      return ibList;
   }

   public void setIbList(List<IBData> ibList)
   {
      this.ibList = ibList;
   }

   public List<InvessenceData> getInvessenceList()
   {
      return invessenceList;
   }

   public void setInvessenceList(List<InvessenceData> invessenceList)
   {
      this.invessenceList = invessenceList;
   }

   public String getNextRebalDate()
   {
      return nextRebalDate;
   }

   public void setNextRebalDate(String nextRebalDate)
   {
      this.nextRebalDate = nextRebalDate;
   }

   public List<String> getFilteredOption()
   {
      return filteredOption;
   }

   public void setFilteredOption(List<String> filteredOption)
   {
      this.filteredOption = filteredOption;
   }

   public List<TradeClientData> getTradeDataList()
   {
      return tradeDataList;
   }

   public void setTradeDataList(List<TradeClientData> tradeClientDataList)
   {
      this.tradeDataList = tradeClientDataList;
   }

   public TradeClientData[] getSelectedTradeAccounts()
   {
      return selectedTradeAccounts;
   }

   public void setSelectedTradeAccounts(TradeClientData[] selectedTradeAccounts)
   {
         this.selectedTradeAccounts = selectedTradeAccounts;
   }

   public TradeClientData getSingleTradeAccount()
   {
      return singleTradeAccount;
   }

   public void setSingleTradeAccount(TradeClientData singleTradeAccount)
   {
      this.singleTradeAccount = singleTradeAccount;
   }

   public List<TradeDetails> getTradedetails()
   {
      return tradedetails;
   }

   public void setTradedetails(List<TradeDetails> tradedetails)
   {
      this.tradedetails = tradedetails;
   }
}

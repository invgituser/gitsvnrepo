package com.invessence.ws.provider.td.dao;

import java.sql.SQLException;
import java.util.List;

import com.docusign.esign.model.EnvelopeSummary;
import com.invessence.ws.provider.td.bean.*;

/**
 * Created by abhangp on 8/17/2016.
 */
public interface TDDaoLayer
{
   public List<DCRequest> getDCRequests(Long acctNum, Integer eventNum)throws SQLException;

   public AcctDetails getAcctDetails(Long acctNum, Long reqId, boolean reqAllData)throws SQLException;

   public List<AcctOwnerDetails> getAcctOwnerDetails(Long acctNum, Long reqId, boolean reqAllData)throws SQLException;

   public List<BenefiaciaryDetails> getBenefiaciaryDetails(Long acctNum, Long reqId)throws SQLException;

   public MoveMoneyDetails getMoveMoneyDetail(Long acctNum, Integer eventNum)throws SQLException;

   public List<MoveMoneyDetails> getMoveMoneyDetails(Long acctNum, Integer eventNum)throws SQLException;

   public List<MMAchBankDetails> getMMAchBankDetails(Long acctNum, Long moveMoneyPayMethodID)throws SQLException;

   public List<MMFedwireAcctDetails> getMMFedwireAcctDetails(Long acctNum, Long moveMoneyPayMethodID)throws SQLException;

   public List<MMInternalTransferDetails> getMMInternalTransferDetails(Long acctNum, Long moveMoneyPayMethodID)throws SQLException;

   public AcctTransferDetails getAcctTransferDetails(Long acctNum, Long reqId)throws SQLException;

   public ElecFundTransferDetails getElecFundTransferDetails(Long acctNum, Long reqId)throws SQLException;


   void updateEnvelopDetails(int eventNum, EnvelopeSummary envelopeSummary)throws SQLException;

   public boolean callDCAuditSP(DCRequestAudit dcRequest);
}

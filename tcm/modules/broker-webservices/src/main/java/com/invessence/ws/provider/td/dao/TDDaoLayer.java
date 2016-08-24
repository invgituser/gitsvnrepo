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
   public List<DCRequest> getDCRequests(Long acctNum, int eventNum)throws SQLException;

   public AcctDetails getAcctDetails(Long acctNum, Long reqId)throws SQLException;

   public List<AcctOwnerDetails> getAcctOwnerDetails(Long acctNum, Long reqId)throws SQLException;

   void updateEnvelopDetails(int eventNum, EnvelopeSummary envelopeSummary)throws SQLException;
}

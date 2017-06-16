package com.invessence.ws.data.common;

import java.util.Date;

public class ReqAcctOpening {

	private Long acctnum;
	private String clientAccountID;
	private String caseNumber;
	private Long advisorId;
	private String acctTypeId;
	private String cashSweepVehicleChoiceId;
	private String divIntPrefId;
	private String monthStmtId;
	private String tradConfId;
	private String dupStatement;
	private String dupTradeConfirm;
	private String proxyAuthorizationId;
	private Date signedTimeStamp;
	private String terminalDetails;
	private Date created;
	private String createdBy;
	private Date updated;
	private String updatedBy;

	public Long getAcctnum()
	{
		return acctnum;
	}

	public void setAcctnum(Long acctnum)
	{
		this.acctnum = acctnum;
	}

	public String getClientAccountID()
	{
		return clientAccountID;
	}

	public void setClientAccountID(String clientAccountID)
	{
		this.clientAccountID = clientAccountID;
	}

	public String getCaseNumber()
	{
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber)
	{
		this.caseNumber = caseNumber;
	}

	public Long getAdvisorId()
	{
		return advisorId;
	}

	public void setAdvisorId(Long advisorId)
	{
		this.advisorId = advisorId;
	}

	public String getAcctTypeId()
	{
		return acctTypeId;
	}

	public void setAcctTypeId(String acctTypeId)
	{
		this.acctTypeId = acctTypeId;
	}

	public String getCashSweepVehicleChoiceId()
	{
		return cashSweepVehicleChoiceId;
	}

	public void setCashSweepVehicleChoiceId(String cashSweepVehicleChoiceId)
	{
		this.cashSweepVehicleChoiceId = cashSweepVehicleChoiceId;
	}

	public String getDivIntPrefId()
	{
		return divIntPrefId;
	}

	public void setDivIntPrefId(String divIntPrefId)
	{
		this.divIntPrefId = divIntPrefId;
	}

	public String getMonthStmtId()
	{
		return monthStmtId;
	}

	public void setMonthStmtId(String monthStmtId)
	{
		this.monthStmtId = monthStmtId;
	}

	public String getTradConfId()
	{
		return tradConfId;
	}

	public void setTradConfId(String tradConfId)
	{
		this.tradConfId = tradConfId;
	}

	public String getDupStatement()
	{
		return dupStatement;
	}

	public void setDupStatement(String dupStatement)
	{
		this.dupStatement = dupStatement;
	}

	public String getDupTradeConfirm()
	{
		return dupTradeConfirm;
	}

	public void setDupTradeConfirm(String dupTradeConfirm)
	{
		this.dupTradeConfirm = dupTradeConfirm;
	}

	public String getProxyAuthorizationId()
	{
		return proxyAuthorizationId;
	}

	public void setProxyAuthorizationId(String proxyAuthorizationId)
	{
		this.proxyAuthorizationId = proxyAuthorizationId;
	}

	public Date getSignedTimeStamp()
	{
		return signedTimeStamp;
	}

	public void setSignedTimeStamp(Date signedTimeStamp)
	{
		this.signedTimeStamp = signedTimeStamp;
	}

	public String getTerminalDetails()
	{
		return terminalDetails;
	}

	public void setTerminalDetails(String terminalDetails)
	{
		this.terminalDetails = terminalDetails;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public String getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}
}

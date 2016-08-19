package com.invessence.web.data.custody;

import java.util.Date;

public class ReqMoveMoney {
	private Integer moveMoneyID;
	private Long acctnum;
	private String clientAccountID;
	private String caseNumber;
	private Long advisorId;
	private String reqType;
	private String advisoryFirm;
	private Long acctOwnerDetailsId;
	private Date signedTimeStamp;
	private String terminalDetails;
	private Date created;
	private String createdBy;
	private Date updated;
	private String updatedBy;

	public Integer getMoveMoneyID()
	{
		return moveMoneyID;
	}

	public void setMoveMoneyID(Integer moveMoneyID)
	{
		this.moveMoneyID = moveMoneyID;
	}

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

	public String getReqType()
	{
		return reqType;
	}

	public void setReqType(String reqType)
	{
		this.reqType = reqType;
	}

	public String getAdvisoryFirm()
	{
		return advisoryFirm;
	}

	public void setAdvisoryFirm(String advisoryFirm)
	{
		this.advisoryFirm = advisoryFirm;
	}

	public Long getAcctOwnerDetailsId()
	{
		return acctOwnerDetailsId;
	}

	public void setAcctOwnerDetailsId(Long acctOwnerDetailsId)
	{
		this.acctOwnerDetailsId = acctOwnerDetailsId;
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

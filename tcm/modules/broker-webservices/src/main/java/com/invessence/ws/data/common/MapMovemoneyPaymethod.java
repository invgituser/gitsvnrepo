package com.invessence.ws.data.common;

public class MapMovemoneyPaymethod {

	private Long moveMoneyPayMethId;
	private Integer moveMoneyId;
	private Long acctnum;
	private String payMethodId;


	public Long getMoveMoneyPayMethId()
	{
		return moveMoneyPayMethId;
	}

	public void setMoveMoneyPayMethId(Long moveMoneyPayMethId)
	{
		this.moveMoneyPayMethId = moveMoneyPayMethId;
	}

	public Integer getMoveMoneyId()
	{
		return moveMoneyId;
	}

	public void setMoveMoneyId(Integer moveMoneyId)
	{
		this.moveMoneyId = moveMoneyId;
	}

	public Long getAcctnum()
	{
		return acctnum;
	}

	public void setAcctnum(Long acctnum)
	{
		this.acctnum = acctnum;
	}

	public String getPayMethodId()
	{
		return payMethodId;
	}

	public void setPayMethodId(String payMethodId)
	{
		this.payMethodId = payMethodId;
	}
}

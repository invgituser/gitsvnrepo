package com.invessence.ws.provider.td.bean;

import java.util.Date;

public class BenefiaciaryDetails {

	private Long acctnum;
	private Integer beneId;
	private String beneFirstName;
	private String beneMidInitial;
	private String beneLastName;
	private String beneSSN;
	private String beneDOB;
	private String beneRel;
	private String beneType;
	private String perStirpes;
	private Double beneShare;

	public Long getAcctnum()
	{
		return acctnum;
	}

	public void setAcctnum(Long acctnum)
	{
		this.acctnum = acctnum;
	}

	public Integer getBeneId()
	{
		return beneId;
	}

	public void setBeneId(Integer beneId)
	{
		this.beneId = beneId;
	}

	public String getBeneFirstName()
	{
		return beneFirstName;
	}

	public void setBeneFirstName(String beneFirstName)
	{
		this.beneFirstName = beneFirstName;
	}

	public String getBeneMidInitial()
	{
		return beneMidInitial;
	}

	public void setBeneMidInitial(String beneMidInitial)
	{
		this.beneMidInitial = beneMidInitial;
	}

	public String getBeneLastName()
	{
		return beneLastName;
	}

	public void setBeneLastName(String beneLastName)
	{
		this.beneLastName = beneLastName;
	}

	public String getBeneSSN()
	{
		return beneSSN;
	}

	public void setBeneSSN(String beneSSN)
	{
		this.beneSSN = beneSSN;
	}

	public String getBeneDOB()
	{
		return beneDOB;
	}

	public void setBeneDOB(String beneDOB)
	{
		this.beneDOB = beneDOB;
	}

	public String getBeneRel()
	{
		return beneRel;
	}

	public void setBeneRel(String beneRel)
	{
		this.beneRel = beneRel;
	}

	public String getBeneType()
	{
		return beneType;
	}

	public void setBeneType(String beneType)
	{
		this.beneType = beneType;
	}

	public String getPerStirpes()
	{
		return perStirpes;
	}

	public void setPerStirpes(String perStirpes)
	{
		this.perStirpes = perStirpes;
	}

	public Double getBeneShare()
	{
		return beneShare;
	}

	public void setBeneShare(Double beneShare)
	{
		this.beneShare = beneShare;
	}
}
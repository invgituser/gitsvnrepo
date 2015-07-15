package com.invessence.data.consumer.CTO;

import java.io.Serializable;

public class FinancialProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6106107504555812948L;
	private Long acctnum;
	private String userid;
    private String maritalstatus;
	private Integer dependents;
	private Integer annualincome;
	private Integer networth;
	private Integer liquidnetworth;
	private Integer investmentexp;
	
    public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
    public Long getAcctnum() {
		return acctnum;
	}
	public void setAcctnum(Long acctnum) {
		this.acctnum = acctnum;
	}
    public String getMaritalstatus() {
        return maritalstatus;
    }
    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }
    
    public Integer getDependents() {
		return dependents;
	}
	public void setDependents(Integer dependents) {
		this.dependents = dependents;
	}
	public Integer getAnnualincome() {
		return annualincome;
	}
	public void setAnnualincome(Integer annualincome) {
		this.annualincome = annualincome;
	}
	public Integer getNetworth() {
		return networth;
	}
	public void setNetworth(Integer networth) {
		this.networth = networth;
	}
	public Integer getLiquidnetworth() {
		return liquidnetworth;
	}
	public void setLiquidnetworth(Integer liquidnetworth) {
		this.liquidnetworth = liquidnetworth;
	}
	public Integer getInvestmentexp() {
		return investmentexp;
	}
	public void setInvestmentexp(Integer investmentexp) {
		this.investmentexp = investmentexp;
	}

}

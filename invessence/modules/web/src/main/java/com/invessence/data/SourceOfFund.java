package com.invessence.data;

import java.io.Serializable;

public class SourceOfFund implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8404013206099057432L;
	private Long acctnum;
	private String userid;
    private String bankaccount;
	private String bankroutingno;
	
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
    public String getBankaccount() {
        return bankaccount;
    }
    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }
    public String getBankroutingno() {
        return bankroutingno;
    }
    public void setBankroutingno(String bankroutingno) {
        this.bankroutingno = bankroutingno;
    }
}

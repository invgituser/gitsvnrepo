package com.invessence.data.consumer.CTO;

import java.io.Serializable;

public class AccountType implements Serializable{

	private Long acctnum;
	private String userid;
    private String type;
    
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}

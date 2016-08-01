package com.invessence.ws.data.common;

import java.util.Date;

public class DupDocReqParty {
	private long id;
	private long acctnum;
	private String dupAddressName;
	private String dupCompany;
	private String dupAddressStreet;
	private String dupAddressCity;
	private String dupAddressState;
	private String dupAddressZipCode;
	private Date created;
	private String createdBy;
	private Date updated;
	private String updatedBy;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAcctnum() {
		return acctnum;
	}
	public void setAcctnum(long acctnum) {
		this.acctnum = acctnum;
	}
	public String getDupAddressName() {
		return dupAddressName;
	}
	public void setDupAddressName(String dupAddressName) {
		this.dupAddressName = dupAddressName;
	}
	public String getDupCompany() {
		return dupCompany;
	}
	public void setDupCompany(String dupCompany) {
		this.dupCompany = dupCompany;
	}
	public String getDupAddressStreet() {
		return dupAddressStreet;
	}
	public void setDupAddressStreet(String dupAddressStreet) {
		this.dupAddressStreet = dupAddressStreet;
	}
	public String getDupAddressCity() {
		return dupAddressCity;
	}
	public void setDupAddressCity(String dupAddressCity) {
		this.dupAddressCity = dupAddressCity;
	}
	public String getDupAddressState() {
		return dupAddressState;
	}
	public void setDupAddressState(String dupAddressState) {
		this.dupAddressState = dupAddressState;
	}
	public String getDupAddressZipCode() {
		return dupAddressZipCode;
	}
	public void setDupAddressZipCode(String dupAddressZipCode) {
		this.dupAddressZipCode = dupAddressZipCode;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	

}

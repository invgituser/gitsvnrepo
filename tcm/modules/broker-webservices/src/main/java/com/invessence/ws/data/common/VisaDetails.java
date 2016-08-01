package com.invessence.ws.data.common;

import java.util.Date;

public class VisaDetails {

	private long id;
	private long acctOwnerId;
	private String usVisa;
	private String visaType;
	private String visaNumber;
	private String visaExpiration;
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
	public long getAcctOwnerId() {
		return acctOwnerId;
	}
	public void setAcctOwnerId(long acctOwnerId) {
		this.acctOwnerId = acctOwnerId;
	}
	public String getUsVisa() {
		return usVisa;
	}
	public void setUsVisa(String usVisa) {
		this.usVisa = usVisa;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getVisaNumber() {
		return visaNumber;
	}
	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}
	public String getVisaExpiration() {
		return visaExpiration;
	}
	public void setVisaExpiration(String visaExpiration) {
		this.visaExpiration = visaExpiration;
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

package com.invessence.web.data.custody;

import java.util.Date;

public class AdvisorDetails {

	private Integer id;
	private String advisorCode;
	private String firmName;
	private String pirmaryContact;
	private Date created;
	private String createdBy;
	private Date updated;
	private String updatedBy;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getAdvisorCode() {
		return advisorCode;
	}
	public void setAdvisorCode(String advisorCode) {
		this.advisorCode = advisorCode;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getPirmaryContact() {
		return pirmaryContact;
	}
	public void setPirmaryContact(String pirmaryContact) {
		this.pirmaryContact = pirmaryContact;
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

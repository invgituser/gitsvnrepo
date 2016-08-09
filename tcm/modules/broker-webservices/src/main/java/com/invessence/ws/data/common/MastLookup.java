package com.invessence.ws.data.common;

import java.util.Date;

public class MastLookup {

	private String lookupSet;
	private String lookupCode;
	private String displayName;
	private String parentLookupId;
	private String value;
	private String remark;
	private Integer sortOrder;
	private String status;
	private Date created;
	private String createdBy;
	private Date updated;
	private String updatedBy;
	public String getLookupSet() {
		return lookupSet;
	}
	public void setLookupSet(String lookupSet) {
		this.lookupSet = lookupSet;
	}
	public String getLookupCode() {
		return lookupCode;
	}
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getParentLookupId() {
		return parentLookupId;
	}
	public void setParentLookupId(String parentLookupId) {
		this.parentLookupId = parentLookupId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSortOrder()
	{
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder)
	{
		this.sortOrder = sortOrder;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

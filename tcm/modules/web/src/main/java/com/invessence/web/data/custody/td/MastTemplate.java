package com.invessence.web.data.custody.td;

import java.util.Date;

public class MastTemplate {

	private Long tempId;
	private String tempName;
	private String dcTempId;
	private String status;
	private String remark;
	private Date created;
	private String createdBy;
	private Date updated;
	private String updatedBy;

	public String getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Long getTempId()
	{
		return tempId;
	}

	public void setTempId(Long tempId)
	{
		this.tempId = tempId;
	}

	public String getTempName()
	{
		return tempName;
	}

	public void setTempName(String tempName)
	{
		this.tempName = tempName;
	}

	public String getDcTempId()
	{
		return dcTempId;
	}

	public void setDcTempId(String dcTempId)
	{
		this.dcTempId = dcTempId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
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
}

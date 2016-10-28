package com.invessence.web.data.custody.td;

import java.util.Date;

public class DecedentInformation {

	private Long id;
	private String dFirstName;
	private String dMiddleName;
	private String dLastName;
	private String dAccountNumber;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getdFirstName() {
		return dFirstName;
	}

	public void setdFirstName(String dFirstName) {
		this.dFirstName = dFirstName;
	}

	public String getdMiddleName() {
		return dMiddleName;
	}

	public void setdMiddleName(String dMiddleName) {
		this.dMiddleName = dMiddleName;
	}

	public String getdLastName() {
		return dLastName;
	}

	public void setdLastName(String dLastName) {
		this.dLastName = dLastName;
	}

	public String getdAccountNumber() {
		return dAccountNumber;
	}

	public void setdAccountNumber(String dAccountNumber) {
		this.dAccountNumber = dAccountNumber;
	}

	public String getdRelationship() {
		return dRelationship;
	}

	public void setdRelationship(String dRelationship) {
		this.dRelationship = dRelationship;
	}

	public Date getdDeathDate() {
		return dDeathDate;
	}

	public void setdDeathDate(Date dDeathDate) {
		this.dDeathDate = dDeathDate;
	}

	public String getRbd() {
		return rbd;
	}

	public void setRbd(String rbd) {
		this.rbd = rbd;
	}

	public String getDeceasedRelationship() {
		return deceasedRelationship;
	}

	public void setDeceasedRelationship(String deceasedRelationship) {
		this.deceasedRelationship = deceasedRelationship;
	}

	public String getTdExististingIRAAccount() {
		return tdExististingIRAAccount;
	}

	public void setTdExististingIRAAccount(String tdExististingIRAAccount) {
		this.tdExististingIRAAccount = tdExististingIRAAccount;
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

	private String dRelationship;
	private Date dDeathDate;
	private String rbd;
	private String deceasedRelationship;
	private String tdExististingIRAAccount;
	private Date created;
	private String createdBy;
	private Date updated;
	private String updatedBy;

}

package com.invessence.ws.data.common;

import java.util.Date;

public class EmploymentDetails {

	private long id;
	private long acctOwnerId;
	private String employerName;
	private String occupation;
	private String typeOfBusiness;
	private String employerStreetAddress;
	private String employerCity;
	private String employerState;
	private String employerZipCode;
	private Date fromDate;
	private Date toDate;
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
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getTypeOfBusiness() {
		return typeOfBusiness;
	}
	public void setTypeOfBusiness(String typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
	}
	public String getEmployerStreetAddress() {
		return employerStreetAddress;
	}
	public void setEmployerStreetAddress(String employerStreetAddress) {
		this.employerStreetAddress = employerStreetAddress;
	}
	public String getEmployerCity() {
		return employerCity;
	}
	public void setEmployerCity(String employerCity) {
		this.employerCity = employerCity;
	}
	public String getEmployerState() {
		return employerState;
	}
	public void setEmployerState(String employerState) {
		this.employerState = employerState;
	}
	public String getEmployerZipCode() {
		return employerZipCode;
	}
	public void setEmployerZipCode(String employerZipCode) {
		this.employerZipCode = employerZipCode;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
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

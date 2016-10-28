package com.invessence.yodlee.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * YdlUserLogon entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_user_logon", catalog = "invdb")
public class UserLogon implements java.io.Serializable {

	// Fields

	private Long id;
	private Long invUserId;
	private String userId;
	private String password;
	private String email;
	private String status;
	private Date registeredOn;
	private Long registeredBy;
	private Date updatedOn;
	private Long updatedBy;
	
	private transient String userSessionToken;


	@Transient
	public String getUserSessionToken() {
		return userSessionToken;
	}

	public void setUserSessionToken(String userSessionToken) {
		this.userSessionToken = userSessionToken;
	}

	private Set<SiteDetail> siteDetail = new HashSet<SiteDetail>(0);
	private Set<ConsolidateData> consolidateData = new HashSet<ConsolidateData>(0);

	// Constructors

	/** default constructor */
	public UserLogon() {
	}

	/** minimal constructor */
	public UserLogon(Long id, Long invUserId, String userId,
			String password, String email, Date registeredOn,
			Long registeredBy, Date updatedOn) {
		this.id = id;
		this.invUserId = invUserId;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.registeredOn = registeredOn;
		this.registeredBy = registeredBy;
		this.updatedOn = updatedOn;
	}

	/** full constructor */
	public UserLogon(Long id, Long invUserId, String userId,
			String password, String email, String status,
			Date registeredOn, Long registeredBy, Date updatedOn,
			Long updatedBy, Set<SiteDetail> siteDetail,
			Set<ConsolidateData> consolidateData) {
		this.id = id;
		this.invUserId = invUserId;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.status = status;
		this.registeredOn = registeredOn;
		this.registeredBy = registeredBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.siteDetail = siteDetail;
		this.consolidateData = consolidateData;
	}

	// Property accessors
	@Id @GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "INV_USER_ID", nullable = false, unique=true)
	public Long getInvUserId() {
		return this.invUserId;
	}

	public void setInvUserId(Long invUserId) {
		this.invUserId = invUserId;
	}

	@Column(name = "USER_ID", nullable = false, length = 100)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTERED_ON", nullable = false, length = 19)
	public Date getRegisteredOn() {
		return this.registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	@Column(name = "REGISTERED_BY", nullable = false)
	public Long getRegisteredBy() {
		return this.registeredBy;
	}

	public void setRegisteredBy(Long registeredBy) {
		this.registeredBy = registeredBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON", length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "UPDATED_BY")
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userLogon")
	public Set<SiteDetail> getSiteDetails() {
		return this.siteDetail;
	}

	public void setSiteDetails(Set<SiteDetail> siteDetail) {
		this.siteDetail = siteDetail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userLogon")
	public Set<ConsolidateData> getConsolidateDatas() {
		return this.consolidateData;
	}

	public void setConsolidateDatas(
			Set<ConsolidateData> consolidateData) {
		this.consolidateData = consolidateData;
	}

	@Override
	public String toString() {
		return "UserLogon [id=" + id + ", invUserId=" + invUserId + ", userId=" + userId + ", email=" + email
				+ ", status=" + status + "]";
	}

	

}
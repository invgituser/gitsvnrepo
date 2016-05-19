package com.invessence.yodlee.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * YdlItemDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_item_details", catalog = "invdb")
public class ItemDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private SiteDetail siteDetail;
	private Long itemId;
	private String itemDispName;
	private Long contServId;
	private String contServName;
	private String status;
	private Timestamp insertedOn;
	private Long insertedBy;
	private Timestamp updatedOn;
	private Long updatedBy;
	private Set<AccountDetail> accountDetails = new HashSet<AccountDetail>(0);
	private Set<ConsolidateData> consolidateData = new HashSet<ConsolidateData>(0);

	// Constructors

	/** default constructor */
	public ItemDetail() {
	}

	/** minimal constructor */
	public ItemDetail(Long id, SiteDetail siteDetail, Long itemId,
			Long contServId, String contServName, Timestamp insertedOn,
			Long insertedBy, Timestamp updatedOn) {
		this.id = id;
		this.siteDetail = siteDetail;
		this.itemId = itemId;
		this.contServId = contServId;
		this.contServName = contServName;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
	}

	/** full constructor */
	public ItemDetail(Long id, SiteDetail siteDetail, Long itemId,
			Long contServId, String contServName, String status,
			Timestamp insertedOn, Long insertedBy, Timestamp updatedOn,
			Long updatedBy, Set<AccountDetail> accountDetails,
			Set<ConsolidateData> consolidateData) {
		this.id = id;
		this.siteDetail = siteDetail;
		this.itemId = itemId;
		this.contServId = contServId;
		this.contServName = contServName;
		this.status = status;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.accountDetails = accountDetails;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SITE_DET_ID", nullable = false)
	public SiteDetail getSiteDetail() {
		return this.siteDetail;
	}

	public void setSiteDetail(SiteDetail siteDetail) {
		this.siteDetail = siteDetail;
	}

	@Column(name = "ITEM_ID", nullable = false)
	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "ITEM_DISP_NAME", nullable = false, length = 100)
	public String getItemDispName() {
		return this.itemDispName;
	}

	public void setItemDispName(String itemDispName) {
		this.itemDispName = itemDispName;
	}
	
	@Column(name = "CONT_SERV_ID", nullable = false)
	public Long getContServId() {
		return this.contServId;
	}

	public void setContServId(Long contServId) {
		this.contServId = contServId;
	}
	@Column(name = "CONT_SERV_NAME", nullable = false, length = 100)
	public String getContServName() {
		return this.contServName;
	}

	public void setContServName(String contServName) {
		this.contServName = contServName;
	}

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "INSERTED_ON", nullable = false, length = 19)
	public Timestamp getInsertedOn() {
		return this.insertedOn;
	}

	public void setInsertedOn(Timestamp insertedOn) {
		this.insertedOn = insertedOn;
	}

	@Column(name = "INSERTED_BY", nullable = false)
	public Long getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name = "UPDATED_ON", length = 19)
	public Timestamp getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "UPDATED_BY")
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itemDetail")
	public Set<AccountDetail> setAccountDetails() {
		return this.accountDetails;
	}

	public void setAccountDetails(
			Set<AccountDetail> accountDetails) {
		this.accountDetails = accountDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itemDetail")
	public Set<ConsolidateData> getConsolidateDatas() {
		return this.consolidateData;
	}

	public void setConsolidateDatas(
			Set<ConsolidateData> consolidateData) {
		this.consolidateData = consolidateData;
	}

}
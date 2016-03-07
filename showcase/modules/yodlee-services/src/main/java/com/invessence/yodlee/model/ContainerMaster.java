package com.invessence.yodlee.model;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

/**
 * YdlAccountDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_container_master", catalog = "invdb")
public class ContainerMaster implements java.io.Serializable {

	// Fields

	private Long id;
	private String contName;
	private String contDesc;
	private Long contId;
	private String status;
	private Timestamp insertedOn;
	private Long insertedBy;
	private Timestamp updatedOn;
	private Long updatedBy;

	// Constructors

	/** default constructor */

	// Property accessors
	@Id @GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CONT_ID", nullable = false, length = 100, unique = true)
	public Long getContId()	{		return contId;	}

	public void setContId(Long contId)	{		this.contId = contId;	}

	@Column(name = "CONT_NAME", nullable = false, length = 100, unique = true)
	public String getContName()	{		return contName;	}

	public void setContName(String contName)	{		this.contName = contName;	}

	@Column(name = "CONT_DESC", nullable = false, length = 100)
	public String getContDesc()	{		return contDesc;	}

	public void setContDesc(String contDesc)	{		this.contDesc = contDesc;	}

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
}
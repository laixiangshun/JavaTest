package com.foxera.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RtvHead entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RTV_HEAD", schema = "RMA")
public class RtvHead implements java.io.Serializable {

	// Fields

	private String rtvNo;
	private String orgCode;
	private String dcCode;
	private String repairsiteCode;
	private Double thirdpartysiteId;
	private Double rtvSourceType;
	private Double rtvType;
	private Double rtvStatus;
	private String shipoutBy;
	private Timestamp shipoutDt;
	private String receiveBy;
	private Timestamp receiveDt;
	private String createdBy;
	private Timestamp createdDt;
	private String lastModifiedBy;
	private Timestamp lastModifiedDt;

	// Constructors

	/** default constructor */
	public RtvHead() {
	}

	/** minimal constructor */
	public RtvHead(String rtvNo, String orgCode, String dcCode,
			String repairsiteCode, Double thirdpartysiteId,
			Double rtvSourceType, Double rtvType, Double rtvStatus,
			String createdBy, Timestamp createdDt) {
		this.rtvNo = rtvNo;
		this.orgCode = orgCode;
		this.dcCode = dcCode;
		this.repairsiteCode = repairsiteCode;
		this.thirdpartysiteId = thirdpartysiteId;
		this.rtvSourceType = rtvSourceType;
		this.rtvType = rtvType;
		this.rtvStatus = rtvStatus;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
	}

	/** full constructor */
	public RtvHead(String rtvNo, String orgCode, String dcCode,
			String repairsiteCode, Double thirdpartysiteId,
			Double rtvSourceType, Double rtvType, Double rtvStatus,
			String shipoutBy, Timestamp shipoutDt, String receiveBy,
			Timestamp receiveDt, String createdBy, Timestamp createdDt,
			String lastModifiedBy, Timestamp lastModifiedDt) {
		this.rtvNo = rtvNo;
		this.orgCode = orgCode;
		this.dcCode = dcCode;
		this.repairsiteCode = repairsiteCode;
		this.thirdpartysiteId = thirdpartysiteId;
		this.rtvSourceType = rtvSourceType;
		this.rtvType = rtvType;
		this.rtvStatus = rtvStatus;
		this.shipoutBy = shipoutBy;
		this.shipoutDt = shipoutDt;
		this.receiveBy = receiveBy;
		this.receiveDt = receiveDt;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDt = lastModifiedDt;
	}

	// Property accessors
	@Id
	@Column(name = "RTV_NO", unique = true, nullable = false, length = 32)
	public String getRtvNo() {
		return this.rtvNo;
	}

	public void setRtvNo(String rtvNo) {
		this.rtvNo = rtvNo;
	}

	@Column(name = "ORG_CODE", nullable = false, length = 16)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "DC_CODE", nullable = false, length = 16)
	public String getDcCode() {
		return this.dcCode;
	}

	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}

	@Column(name = "REPAIRSITE_CODE", nullable = false, length = 32)
	public String getRepairsiteCode() {
		return this.repairsiteCode;
	}

	public void setRepairsiteCode(String repairsiteCode) {
		this.repairsiteCode = repairsiteCode;
	}

	@Column(name = "THIRDPARTYSITE_ID", nullable = false, precision = 0)
	public Double getThirdpartysiteId() {
		return this.thirdpartysiteId;
	}

	public void setThirdpartysiteId(Double thirdpartysiteId) {
		this.thirdpartysiteId = thirdpartysiteId;
	}

	@Column(name = "RTV_SOURCE_TYPE", nullable = false, precision = 0)
	public Double getRtvSourceType() {
		return this.rtvSourceType;
	}

	public void setRtvSourceType(Double rtvSourceType) {
		this.rtvSourceType = rtvSourceType;
	}

	@Column(name = "RTV_TYPE", nullable = false, precision = 0)
	public Double getRtvType() {
		return this.rtvType;
	}

	public void setRtvType(Double rtvType) {
		this.rtvType = rtvType;
	}

	@Column(name = "RTV_STATUS", nullable = false, precision = 0)
	public Double getRtvStatus() {
		return this.rtvStatus;
	}

	public void setRtvStatus(Double rtvStatus) {
		this.rtvStatus = rtvStatus;
	}

	@Column(name = "SHIPOUT_BY", length = 32)
	public String getShipoutBy() {
		return this.shipoutBy;
	}

	public void setShipoutBy(String shipoutBy) {
		this.shipoutBy = shipoutBy;
	}

	@Column(name = "SHIPOUT_DT", length = 7)
	public Timestamp getShipoutDt() {
		return this.shipoutDt;
	}

	public void setShipoutDt(Timestamp shipoutDt) {
		this.shipoutDt = shipoutDt;
	}

	@Column(name = "RECEIVE_BY", length = 32)
	public String getReceiveBy() {
		return this.receiveBy;
	}

	public void setReceiveBy(String receiveBy) {
		this.receiveBy = receiveBy;
	}

	@Column(name = "RECEIVE_DT", length = 7)
	public Timestamp getReceiveDt() {
		return this.receiveDt;
	}

	public void setReceiveDt(Timestamp receiveDt) {
		this.receiveDt = receiveDt;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 32)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DT", nullable = false, length = 7)
	public Timestamp getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	@Column(name = "LAST_MODIFIED_BY", length = 32)
	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name = "LAST_MODIFIED_DT", length = 7)
	public Timestamp getLastModifiedDt() {
		return this.lastModifiedDt;
	}

	public void setLastModifiedDt(Timestamp lastModifiedDt) {
		this.lastModifiedDt = lastModifiedDt;
	}

}
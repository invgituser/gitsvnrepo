
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				A DVP instruction specifies the settlement information for
 * 				clear-away trading.
 * 			
 * 
 * <p>Java class for DVPInstruction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DVPInstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ExternalAcctID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AcctID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DVP_Type" />
 *       &lt;attribute name="Role" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DVPRole_Type" />
 *       &lt;attribute name="AgentID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FirmID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AccountID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AgentName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AccountName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DayDoID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TXGroupCode" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TXGroupCode_Type" />
 *       &lt;attribute name="BrokerCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AssetClass" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssetClass_Type" />
 *       &lt;attribute name="Exchange" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ExchangeCode_Type" />
 *       &lt;attribute name="PrepayTax" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="PrepayCommission" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="Default" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="Expiry" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DVPInstruction", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class DVPInstruction {

    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "external_id", required = true)
    protected String externalId;
    @XmlAttribute(name = "ExternalAcctID")
    protected String externalAcctID;
    @XmlAttribute(name = "AcctID")
    protected String acctID;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Type", required = true)
    protected DVPType type;
    @XmlAttribute(name = "Role", required = true)
    protected DVPRoleType role;
    @XmlAttribute(name = "AgentID", required = true)
    protected String agentID;
    @XmlAttribute(name = "FirmID", required = true)
    protected String firmID;
    @XmlAttribute(name = "AccountID", required = true)
    protected String accountID;
    @XmlAttribute(name = "AgentName", required = true)
    protected String agentName;
    @XmlAttribute(name = "AccountName", required = true)
    protected String accountName;
    @XmlAttribute(name = "DayDoID", required = true)
    protected String dayDoID;
    @XmlAttribute(name = "TXGroupCode", required = true)
    protected TXGroupCodeType txGroupCode;
    @XmlAttribute(name = "BrokerCode", required = true)
    protected String brokerCode;
    @XmlAttribute(name = "AssetClass", required = true)
    protected AssetClassType assetClass;
    @XmlAttribute(name = "Exchange", required = true)
    protected ExchangeCodeType exchange;
    @XmlAttribute(name = "PrepayTax")
    protected Boolean prepayTax;
    @XmlAttribute(name = "PrepayCommission")
    protected Boolean prepayCommission;
    @XmlAttribute(name = "Default")
    protected Boolean _default;
    @XmlAttribute(name = "Expiry", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expiry;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the externalAcctID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalAcctID() {
        return externalAcctID;
    }

    /**
     * Sets the value of the externalAcctID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalAcctID(String value) {
        this.externalAcctID = value;
    }

    /**
     * Gets the value of the acctID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctID() {
        return acctID;
    }

    /**
     * Sets the value of the acctID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctID(String value) {
        this.acctID = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DVPType }
     *     
     */
    public DVPType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DVPType }
     *     
     */
    public void setType(DVPType value) {
        this.type = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link DVPRoleType }
     *     
     */
    public DVPRoleType getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link DVPRoleType }
     *     
     */
    public void setRole(DVPRoleType value) {
        this.role = value;
    }

    /**
     * Gets the value of the agentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentID() {
        return agentID;
    }

    /**
     * Sets the value of the agentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentID(String value) {
        this.agentID = value;
    }

    /**
     * Gets the value of the firmID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmID() {
        return firmID;
    }

    /**
     * Sets the value of the firmID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmID(String value) {
        this.firmID = value;
    }

    /**
     * Gets the value of the accountID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Sets the value of the accountID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountID(String value) {
        this.accountID = value;
    }

    /**
     * Gets the value of the agentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * Sets the value of the agentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentName(String value) {
        this.agentName = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the dayDoID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayDoID() {
        return dayDoID;
    }

    /**
     * Sets the value of the dayDoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayDoID(String value) {
        this.dayDoID = value;
    }

    /**
     * Gets the value of the txGroupCode property.
     * 
     * @return
     *     possible object is
     *     {@link TXGroupCodeType }
     *     
     */
    public TXGroupCodeType getTXGroupCode() {
        return txGroupCode;
    }

    /**
     * Sets the value of the txGroupCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link TXGroupCodeType }
     *     
     */
    public void setTXGroupCode(TXGroupCodeType value) {
        this.txGroupCode = value;
    }

    /**
     * Gets the value of the brokerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrokerCode() {
        return brokerCode;
    }

    /**
     * Sets the value of the brokerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrokerCode(String value) {
        this.brokerCode = value;
    }

    /**
     * Gets the value of the assetClass property.
     * 
     * @return
     *     possible object is
     *     {@link AssetClassType }
     *     
     */
    public AssetClassType getAssetClass() {
        return assetClass;
    }

    /**
     * Sets the value of the assetClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetClassType }
     *     
     */
    public void setAssetClass(AssetClassType value) {
        this.assetClass = value;
    }

    /**
     * Gets the value of the exchange property.
     * 
     * @return
     *     possible object is
     *     {@link ExchangeCodeType }
     *     
     */
    public ExchangeCodeType getExchange() {
        return exchange;
    }

    /**
     * Sets the value of the exchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangeCodeType }
     *     
     */
    public void setExchange(ExchangeCodeType value) {
        this.exchange = value;
    }

    /**
     * Gets the value of the prepayTax property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPrepayTax() {
        if (prepayTax == null) {
            return false;
        } else {
            return prepayTax;
        }
    }

    /**
     * Sets the value of the prepayTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrepayTax(Boolean value) {
        this.prepayTax = value;
    }

    /**
     * Gets the value of the prepayCommission property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPrepayCommission() {
        if (prepayCommission == null) {
            return false;
        } else {
            return prepayCommission;
        }
    }

    /**
     * Sets the value of the prepayCommission property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrepayCommission(Boolean value) {
        this.prepayCommission = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDefault() {
        if (_default == null) {
            return true;
        } else {
            return _default;
        }
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefault(Boolean value) {
        this._default = value;
    }

    /**
     * Gets the value of the expiry property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiry() {
        return expiry;
    }

    /**
     * Sets the value of the expiry property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiry(XMLGregorianCalendar value) {
        this.expiry = value;
    }

}


package com.invessence.ibservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Details for transferring positions from a third party broker to
 * 				IB using a specific transfer mechanism such as ACATS or ATON.
 * 			
 * 
 * <p>Java class for ExtPositionsTransferType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtPositionsTransferType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}PositionsTransfer_Type" />
 *       &lt;attribute name="sub_type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}PositionsTransfer_SubType" />
 *       &lt;attribute name="broker_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="broker_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="acct_at_broker" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="src_IRA_type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRA_Type" />
 *       &lt;attribute name="margin_loan" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="short_pos" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="option_pos" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ib_acct" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="third_party_type" type="{http://www.w3.org/2001/XMLSchema}string" default="Not informed" />
 *       &lt;attribute name="approximate_acct_value" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" default="1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtPositionsTransferType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class ExtPositionsTransferType {

    @XmlAttribute(name = "type", required = true)
    protected PositionsTransferType type;
    @XmlAttribute(name = "sub_type", required = true)
    protected PositionsTransferSubType subType;
    @XmlAttribute(name = "broker_id", required = true)
    protected String brokerId;
    @XmlAttribute(name = "broker_name")
    protected String brokerName;
    @XmlAttribute(name = "acct_at_broker", required = true)
    protected String acctAtBroker;
    @XmlAttribute(name = "src_IRA_type")
    protected IRAType srcIRAType;
    @XmlAttribute(name = "margin_loan", required = true)
    protected boolean marginLoan;
    @XmlAttribute(name = "short_pos", required = true)
    protected boolean shortPos;
    @XmlAttribute(name = "option_pos", required = true)
    protected boolean optionPos;
    @XmlAttribute(name = "ib_acct")
    protected String ibAcct;
    @XmlAttribute(name = "third_party_type")
    protected String thirdPartyType;
    @XmlAttribute(name = "approximate_acct_value")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger approximateAcctValue;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PositionsTransferType }
     *     
     */
    public PositionsTransferType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionsTransferType }
     *     
     */
    public void setType(PositionsTransferType value) {
        this.type = value;
    }

    /**
     * Gets the value of the subType property.
     * 
     * @return
     *     possible object is
     *     {@link PositionsTransferSubType }
     *     
     */
    public PositionsTransferSubType getSubType() {
        return subType;
    }

    /**
     * Sets the value of the subType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionsTransferSubType }
     *     
     */
    public void setSubType(PositionsTransferSubType value) {
        this.subType = value;
    }

    /**
     * Gets the value of the brokerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrokerId() {
        return brokerId;
    }

    /**
     * Sets the value of the brokerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrokerId(String value) {
        this.brokerId = value;
    }

    /**
     * Gets the value of the brokerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrokerName() {
        return brokerName;
    }

    /**
     * Sets the value of the brokerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrokerName(String value) {
        this.brokerName = value;
    }

    /**
     * Gets the value of the acctAtBroker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctAtBroker() {
        return acctAtBroker;
    }

    /**
     * Sets the value of the acctAtBroker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctAtBroker(String value) {
        this.acctAtBroker = value;
    }

    /**
     * Gets the value of the srcIRAType property.
     * 
     * @return
     *     possible object is
     *     {@link IRAType }
     *     
     */
    public IRAType getSrcIRAType() {
        return srcIRAType;
    }

    /**
     * Sets the value of the srcIRAType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRAType }
     *     
     */
    public void setSrcIRAType(IRAType value) {
        this.srcIRAType = value;
    }

    /**
     * Gets the value of the marginLoan property.
     * 
     */
    public boolean isMarginLoan() {
        return marginLoan;
    }

    /**
     * Sets the value of the marginLoan property.
     * 
     */
    public void setMarginLoan(boolean value) {
        this.marginLoan = value;
    }

    /**
     * Gets the value of the shortPos property.
     * 
     */
    public boolean isShortPos() {
        return shortPos;
    }

    /**
     * Sets the value of the shortPos property.
     * 
     */
    public void setShortPos(boolean value) {
        this.shortPos = value;
    }

    /**
     * Gets the value of the optionPos property.
     * 
     */
    public boolean isOptionPos() {
        return optionPos;
    }

    /**
     * Sets the value of the optionPos property.
     * 
     */
    public void setOptionPos(boolean value) {
        this.optionPos = value;
    }

    /**
     * Gets the value of the ibAcct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbAcct() {
        return ibAcct;
    }

    /**
     * Sets the value of the ibAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbAcct(String value) {
        this.ibAcct = value;
    }

    /**
     * Gets the value of the thirdPartyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPartyType() {
        if (thirdPartyType == null) {
            return "Not informed";
        } else {
            return thirdPartyType;
        }
    }

    /**
     * Sets the value of the thirdPartyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPartyType(String value) {
        this.thirdPartyType = value;
    }

    /**
     * Gets the value of the approximateAcctValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApproximateAcctValue() {
        if (approximateAcctValue == null) {
            return new BigInteger("1");
        } else {
            return approximateAcctValue;
        }
    }

    /**
     * Sets the value of the approximateAcctValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApproximateAcctValue(BigInteger value) {
        this.approximateAcctValue = value;
    }

}

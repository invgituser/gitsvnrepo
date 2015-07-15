
package com.invessence.ibservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Details of an Automated Advisor Wrap Fee Strategy for a Client.
 * 			
 * 
 * <p>Java class for AutomatedWrapFeeDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AutomatedWrapFeeDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="per_trade_markups" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CommissionScheduleType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AutomatedWrapFees_Type" />
 *       &lt;attribute name="max_fee" type="{http://www.w3.org/2001/XMLSchema}decimal" default="0" />
 *       &lt;attribute name="num_contracts" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" default="1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AutomatedWrapFeeDetailsType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "perTradeMarkups"
})
public class AutomatedWrapFeeDetailsType {

    @XmlElement(name = "per_trade_markups", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected CommissionScheduleType perTradeMarkups;
    @XmlAttribute(name = "type", required = true)
    protected AutomatedWrapFeesType type;
    @XmlAttribute(name = "max_fee")
    protected BigDecimal maxFee;
    @XmlAttribute(name = "num_contracts")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numContracts;

    /**
     * Gets the value of the perTradeMarkups property.
     * 
     * @return
     *     possible object is
     *     {@link CommissionScheduleType }
     *     
     */
    public CommissionScheduleType getPerTradeMarkups() {
        return perTradeMarkups;
    }

    /**
     * Sets the value of the perTradeMarkups property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommissionScheduleType }
     *     
     */
    public void setPerTradeMarkups(CommissionScheduleType value) {
        this.perTradeMarkups = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AutomatedWrapFeesType }
     *     
     */
    public AutomatedWrapFeesType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AutomatedWrapFeesType }
     *     
     */
    public void setType(AutomatedWrapFeesType value) {
        this.type = value;
    }

    /**
     * Gets the value of the maxFee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxFee() {
        if (maxFee == null) {
            return new BigDecimal("0");
        } else {
            return maxFee;
        }
    }

    /**
     * Sets the value of the maxFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxFee(BigDecimal value) {
        this.maxFee = value;
    }

    /**
     * Gets the value of the numContracts property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumContracts() {
        if (numContracts == null) {
            return new BigInteger("1");
        } else {
            return numContracts;
        }
    }

    /**
     * Sets the value of the numContracts property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumContracts(BigInteger value) {
        this.numContracts = value;
    }

}

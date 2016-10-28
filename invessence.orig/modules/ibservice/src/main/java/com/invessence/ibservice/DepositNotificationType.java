
package com.invessence.ibservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Notification details for deposit to be used to fund the account
 * 				from an external source.
 * 			
 * 
 * <p>Java class for DepositNotificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepositNotificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CheckDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CheckDetailsType" minOccurs="0"/>
 *         &lt;element name="WireDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}WireDetailsType" minOccurs="0"/>
 *         &lt;element name="ACHDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ACHDetailsType" minOccurs="0"/>
 *         &lt;element name="IRADepositDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRADepositDetailsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Deposit_Type" />
 *       &lt;attribute name="amount" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="currency" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Currency_Type" default="USD" />
 *       &lt;attribute name="ib_acct" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepositNotificationType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "checkDetails",
    "wireDetails",
    "achDetails",
    "iraDepositDetails"
})
public class DepositNotificationType {

    @XmlElement(name = "CheckDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected CheckDetailsType checkDetails;
    @XmlElement(name = "WireDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected WireDetailsType wireDetails;
    @XmlElement(name = "ACHDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected ACHDetailsType achDetails;
    @XmlElement(name = "IRADepositDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IRADepositDetailsType iraDepositDetails;
    @XmlAttribute(name = "type", required = true)
    protected DepositType type;
    @XmlAttribute(name = "amount", required = true)
    protected BigDecimal amount;
    @XmlAttribute(name = "currency")
    protected CurrencyType currency;
    @XmlAttribute(name = "ib_acct")
    protected String ibAcct;

    /**
     * Gets the value of the checkDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CheckDetailsType }
     *     
     */
    public CheckDetailsType getCheckDetails() {
        return checkDetails;
    }

    /**
     * Sets the value of the checkDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckDetailsType }
     *     
     */
    public void setCheckDetails(CheckDetailsType value) {
        this.checkDetails = value;
    }

    /**
     * Gets the value of the wireDetails property.
     * 
     * @return
     *     possible object is
     *     {@link WireDetailsType }
     *     
     */
    public WireDetailsType getWireDetails() {
        return wireDetails;
    }

    /**
     * Sets the value of the wireDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link WireDetailsType }
     *     
     */
    public void setWireDetails(WireDetailsType value) {
        this.wireDetails = value;
    }

    /**
     * Gets the value of the achDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ACHDetailsType }
     *     
     */
    public ACHDetailsType getACHDetails() {
        return achDetails;
    }

    /**
     * Sets the value of the achDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACHDetailsType }
     *     
     */
    public void setACHDetails(ACHDetailsType value) {
        this.achDetails = value;
    }

    /**
     * Gets the value of the iraDepositDetails property.
     * 
     * @return
     *     possible object is
     *     {@link IRADepositDetailsType }
     *     
     */
    public IRADepositDetailsType getIRADepositDetails() {
        return iraDepositDetails;
    }

    /**
     * Sets the value of the iraDepositDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRADepositDetailsType }
     *     
     */
    public void setIRADepositDetails(IRADepositDetailsType value) {
        this.iraDepositDetails = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DepositType }
     *     
     */
    public DepositType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepositType }
     *     
     */
    public void setType(DepositType value) {
        this.type = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyType }
     *     
     */
    public CurrencyType getCurrency() {
        if (currency == null) {
            return CurrencyType.USD;
        } else {
            return currency;
        }
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyType }
     *     
     */
    public void setCurrency(CurrencyType value) {
        this.currency = value;
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

}

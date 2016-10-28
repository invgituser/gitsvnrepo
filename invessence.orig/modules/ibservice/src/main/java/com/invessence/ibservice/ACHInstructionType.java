
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				Details of an ACH instruction to be used to transfer funds
 * 				between the account and an external source. Currently only
 * 				instructions for credit transactions initiated by IB are
 * 				supported.
 * 			
 * 
 * <p>Java class for ACHInstructionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACHInstructionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="cust_init_ACH" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ACHInstructionTx_Type" default="CREDIT" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bank_country" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CountryCode_Type" default="USA" />
 *       &lt;attribute name="currency" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Currency_Type" default="USD" />
 *       &lt;attribute name="routing_number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="acct_number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACHInstructionType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class ACHInstructionType {

    @XmlAttribute(name = "cust_init_ACH")
    protected Boolean custInitACH;
    @XmlAttribute(name = "type")
    protected ACHInstructionTxType type;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "bank_country")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String bankCountry;
    @XmlAttribute(name = "currency")
    protected CurrencyType currency;
    @XmlAttribute(name = "routing_number", required = true)
    protected String routingNumber;
    @XmlAttribute(name = "acct_number", required = true)
    protected String acctNumber;

    /**
     * Gets the value of the custInitACH property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCustInitACH() {
        if (custInitACH == null) {
            return false;
        } else {
            return custInitACH;
        }
    }

    /**
     * Sets the value of the custInitACH property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustInitACH(Boolean value) {
        this.custInitACH = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ACHInstructionTxType }
     *     
     */
    public ACHInstructionTxType getType() {
        if (type == null) {
            return ACHInstructionTxType.CREDIT;
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACHInstructionTxType }
     *     
     */
    public void setType(ACHInstructionTxType value) {
        this.type = value;
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
     * Gets the value of the bankCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCountry() {
        if (bankCountry == null) {
            return "USA";
        } else {
            return bankCountry;
        }
    }

    /**
     * Sets the value of the bankCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCountry(String value) {
        this.bankCountry = value;
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
     * Gets the value of the routingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingNumber() {
        return routingNumber;
    }

    /**
     * Sets the value of the routingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingNumber(String value) {
        this.routingNumber = value;
    }

    /**
     * Gets the value of the acctNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctNumber() {
        return acctNumber;
    }

    /**
     * Sets the value of the acctNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctNumber(String value) {
        this.acctNumber = value;
    }

}

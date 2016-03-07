
package com.invessence.ibservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				Details of scheduled periodic withdrawals or deposits between
 * 				the account and an external source. Currently, only ACH
 * 				withdrawals are supported.
 * 			
 * 
 * <p>Java class for RecurringTransactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecurringTransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ACHInstruction" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ACHInstructionType" minOccurs="0"/>
 *         &lt;element name="IRAWithdrawalDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRAWithdrawalDetailsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RecurringTx_Type" />
 *       &lt;attribute name="method" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Deposit_Type" />
 *       &lt;attribute name="instruction" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="frequency" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RecurringTxFreq_Type" />
 *       &lt;attribute name="start_date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "RecurringTransactionType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "achInstruction",
    "iraWithdrawalDetails"
})
public class RecurringTransactionType {

    @XmlElement(name = "ACHInstruction", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected ACHInstructionType achInstruction;
    @XmlElement(name = "IRAWithdrawalDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IRAWithdrawalDetailsType iraWithdrawalDetails;
    @XmlAttribute(name = "type", required = true)
    protected RecurringTxType type;
    @XmlAttribute(name = "method", required = true)
    protected DepositType method;
    @XmlAttribute(name = "instruction", required = true)
    protected String instruction;
    @XmlAttribute(name = "frequency", required = true)
    protected RecurringTxFreqType frequency;
    @XmlAttribute(name = "start_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlAttribute(name = "end_date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "amount", required = true)
    protected BigDecimal amount;
    @XmlAttribute(name = "currency")
    protected CurrencyType currency;
    @XmlAttribute(name = "ib_acct")
    protected String ibAcct;

    /**
     * Gets the value of the achInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link ACHInstructionType }
     *     
     */
    public ACHInstructionType getACHInstruction() {
        return achInstruction;
    }

    /**
     * Sets the value of the achInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACHInstructionType }
     *     
     */
    public void setACHInstruction(ACHInstructionType value) {
        this.achInstruction = value;
    }

    /**
     * Gets the value of the iraWithdrawalDetails property.
     * 
     * @return
     *     possible object is
     *     {@link IRAWithdrawalDetailsType }
     *     
     */
    public IRAWithdrawalDetailsType getIRAWithdrawalDetails() {
        return iraWithdrawalDetails;
    }

    /**
     * Sets the value of the iraWithdrawalDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRAWithdrawalDetailsType }
     *     
     */
    public void setIRAWithdrawalDetails(IRAWithdrawalDetailsType value) {
        this.iraWithdrawalDetails = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link RecurringTxType }
     *     
     */
    public RecurringTxType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecurringTxType }
     *     
     */
    public void setType(RecurringTxType value) {
        this.type = value;
    }

    /**
     * Gets the value of the method property.
     * 
     * @return
     *     possible object is
     *     {@link DepositType }
     *     
     */
    public DepositType getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepositType }
     *     
     */
    public void setMethod(DepositType value) {
        this.method = value;
    }

    /**
     * Gets the value of the instruction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Sets the value of the instruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstruction(String value) {
        this.instruction = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link RecurringTxFreqType }
     *     
     */
    public RecurringTxFreqType getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecurringTxFreqType }
     *     
     */
    public void setFrequency(RecurringTxFreqType value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
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

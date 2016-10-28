
package com.invessence.ibservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Interest Rate markups for a specific currency.
 * 			
 * 
 * <p>Java class for InterestMarkupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InterestMarkupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="currency" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Currency_Type" />
 *       &lt;attribute name="debit_markup" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="credit_markdown" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="short_credit_markdown" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestMarkupType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class InterestMarkupType {

    @XmlAttribute(name = "currency", required = true)
    protected CurrencyType currency;
    @XmlAttribute(name = "debit_markup")
    protected BigDecimal debitMarkup;
    @XmlAttribute(name = "credit_markdown")
    protected BigDecimal creditMarkdown;
    @XmlAttribute(name = "short_credit_markdown")
    protected BigDecimal shortCreditMarkdown;

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyType }
     *     
     */
    public CurrencyType getCurrency() {
        return currency;
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
     * Gets the value of the debitMarkup property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDebitMarkup() {
        return debitMarkup;
    }

    /**
     * Sets the value of the debitMarkup property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDebitMarkup(BigDecimal value) {
        this.debitMarkup = value;
    }

    /**
     * Gets the value of the creditMarkdown property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditMarkdown() {
        return creditMarkdown;
    }

    /**
     * Sets the value of the creditMarkdown property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditMarkdown(BigDecimal value) {
        this.creditMarkdown = value;
    }

    /**
     * Gets the value of the shortCreditMarkdown property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getShortCreditMarkdown() {
        return shortCreditMarkdown;
    }

    /**
     * Sets the value of the shortCreditMarkdown property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setShortCreditMarkdown(BigDecimal value) {
        this.shortCreditMarkdown = value;
    }

}

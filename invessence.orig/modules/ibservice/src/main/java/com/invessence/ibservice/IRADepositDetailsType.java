
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Additional deposit details relevant to a deposit to an IRA
 * 				account. Required for deposit notifications for IRA accounts.
 * 				These details must be provided in addition to the basic deposit
 * 				type specific details.
 * 			
 * 
 * <p>Java class for IRADepositDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IRADepositDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="deposit_type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRADeposit_Type" />
 *       &lt;attribute name="tax_year" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRAContribTaxYear_Type" />
 *       &lt;attribute name="from_ira_type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRA_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRADepositDetailsType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class IRADepositDetailsType {

    @XmlAttribute(name = "deposit_type", required = true)
    protected IRADepositType depositType;
    @XmlAttribute(name = "tax_year")
    protected IRAContribTaxYearType taxYear;
    @XmlAttribute(name = "from_ira_type")
    protected IRAType fromIraType;

    /**
     * Gets the value of the depositType property.
     * 
     * @return
     *     possible object is
     *     {@link IRADepositType }
     *     
     */
    public IRADepositType getDepositType() {
        return depositType;
    }

    /**
     * Sets the value of the depositType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRADepositType }
     *     
     */
    public void setDepositType(IRADepositType value) {
        this.depositType = value;
    }

    /**
     * Gets the value of the taxYear property.
     * 
     * @return
     *     possible object is
     *     {@link IRAContribTaxYearType }
     *     
     */
    public IRAContribTaxYearType getTaxYear() {
        return taxYear;
    }

    /**
     * Sets the value of the taxYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRAContribTaxYearType }
     *     
     */
    public void setTaxYear(IRAContribTaxYearType value) {
        this.taxYear = value;
    }

    /**
     * Gets the value of the fromIraType property.
     * 
     * @return
     *     possible object is
     *     {@link IRAType }
     *     
     */
    public IRAType getFromIraType() {
        return fromIraType;
    }

    /**
     * Sets the value of the fromIraType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRAType }
     *     
     */
    public void setFromIraType(IRAType value) {
        this.fromIraType = value;
    }

}

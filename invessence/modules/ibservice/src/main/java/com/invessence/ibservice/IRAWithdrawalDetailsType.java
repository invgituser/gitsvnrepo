
package com.invessence.ibservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				Additional details relevant to a withdrawal from an IRA account.
 * 				Required for withdrawal instruction for IRA accounts. These
 * 				details must be provided in addition to the basic withdrawal
 * 				type specific details.
 * 			
 * 
 * <p>Java class for IRAWithdrawalDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IRAWithdrawalDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="distribution_type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRADistribution_Type" />
 *       &lt;attribute name="excess_contrib_yr" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="fed_tax_rate" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="legal_residence_state" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}StateCode_Type" />
 *       &lt;attribute name="state_tax_rate" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRAWithdrawalDetailsType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class IRAWithdrawalDetailsType {

    @XmlAttribute(name = "distribution_type", required = true)
    protected IRADistributionType distributionType;
    @XmlAttribute(name = "excess_contrib_yr")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger excessContribYr;
    @XmlAttribute(name = "fed_tax_rate")
    protected BigDecimal fedTaxRate;
    @XmlAttribute(name = "legal_residence_state", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String legalResidenceState;
    @XmlAttribute(name = "state_tax_rate")
    protected BigDecimal stateTaxRate;

    /**
     * Gets the value of the distributionType property.
     * 
     * @return
     *     possible object is
     *     {@link IRADistributionType }
     *     
     */
    public IRADistributionType getDistributionType() {
        return distributionType;
    }

    /**
     * Sets the value of the distributionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRADistributionType }
     *     
     */
    public void setDistributionType(IRADistributionType value) {
        this.distributionType = value;
    }

    /**
     * Gets the value of the excessContribYr property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExcessContribYr() {
        return excessContribYr;
    }

    /**
     * Sets the value of the excessContribYr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExcessContribYr(BigInteger value) {
        this.excessContribYr = value;
    }

    /**
     * Gets the value of the fedTaxRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFedTaxRate() {
        return fedTaxRate;
    }

    /**
     * Sets the value of the fedTaxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFedTaxRate(BigDecimal value) {
        this.fedTaxRate = value;
    }

    /**
     * Gets the value of the legalResidenceState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalResidenceState() {
        return legalResidenceState;
    }

    /**
     * Sets the value of the legalResidenceState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalResidenceState(String value) {
        this.legalResidenceState = value;
    }

    /**
     * Gets the value of the stateTaxRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStateTaxRate() {
        return stateTaxRate;
    }

    /**
     * Sets the value of the stateTaxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStateTaxRate(BigDecimal value) {
        this.stateTaxRate = value;
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An individual who is the customer and beneficial owner of an
 * 				account. Ownership and Title are fixed at 100% and Account
 * 				Holder respectively and provided values will be ignored.
 * 			
 * 
 * <p>Java class for IndividualApplicant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndividualApplicant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountHolderDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssociatedIndividual"/>
 *         &lt;element name="FinancialInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}FinancialInformation" minOccurs="0"/>
 *         &lt;element name="RegulatoryInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatoryInformation"/>
 *         &lt;element name="RegulatedMemberships" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatedMemberships" minOccurs="0"/>
 *         &lt;element name="AccreditedInvestorInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AccreditedInvestorInformation" minOccurs="0"/>
 *         &lt;element name="TaxInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualTaxInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualApplicant", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "accountHolderDetails",
    "financialInformation",
    "regulatoryInformation",
    "regulatedMemberships",
    "accreditedInvestorInformation",
    "taxInformation"
})
public class IndividualApplicant {

    @XmlElement(name = "AccountHolderDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected AssociatedIndividual accountHolderDetails;
    @XmlElement(name = "FinancialInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected FinancialInformation financialInformation;
    @XmlElement(name = "RegulatoryInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected RegulatoryInformation regulatoryInformation;
    @XmlElement(name = "RegulatedMemberships", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected RegulatedMemberships regulatedMemberships;
    @XmlElement(name = "AccreditedInvestorInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AccreditedInvestorInformation accreditedInvestorInformation;
    @XmlElement(name = "TaxInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IndividualTaxInformation taxInformation;

    /**
     * Gets the value of the accountHolderDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedIndividual }
     *     
     */
    public AssociatedIndividual getAccountHolderDetails() {
        return accountHolderDetails;
    }

    /**
     * Sets the value of the accountHolderDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedIndividual }
     *     
     */
    public void setAccountHolderDetails(AssociatedIndividual value) {
        this.accountHolderDetails = value;
    }

    /**
     * Gets the value of the financialInformation property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialInformation }
     *     
     */
    public FinancialInformation getFinancialInformation() {
        return financialInformation;
    }

    /**
     * Sets the value of the financialInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialInformation }
     *     
     */
    public void setFinancialInformation(FinancialInformation value) {
        this.financialInformation = value;
    }

    /**
     * Gets the value of the regulatoryInformation property.
     * 
     * @return
     *     possible object is
     *     {@link RegulatoryInformation }
     *     
     */
    public RegulatoryInformation getRegulatoryInformation() {
        return regulatoryInformation;
    }

    /**
     * Sets the value of the regulatoryInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegulatoryInformation }
     *     
     */
    public void setRegulatoryInformation(RegulatoryInformation value) {
        this.regulatoryInformation = value;
    }

    /**
     * Gets the value of the regulatedMemberships property.
     * 
     * @return
     *     possible object is
     *     {@link RegulatedMemberships }
     *     
     */
    public RegulatedMemberships getRegulatedMemberships() {
        return regulatedMemberships;
    }

    /**
     * Sets the value of the regulatedMemberships property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegulatedMemberships }
     *     
     */
    public void setRegulatedMemberships(RegulatedMemberships value) {
        this.regulatedMemberships = value;
    }

    /**
     * Gets the value of the accreditedInvestorInformation property.
     * 
     * @return
     *     possible object is
     *     {@link AccreditedInvestorInformation }
     *     
     */
    public AccreditedInvestorInformation getAccreditedInvestorInformation() {
        return accreditedInvestorInformation;
    }

    /**
     * Sets the value of the accreditedInvestorInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccreditedInvestorInformation }
     *     
     */
    public void setAccreditedInvestorInformation(AccreditedInvestorInformation value) {
        this.accreditedInvestorInformation = value;
    }

    /**
     * Gets the value of the taxInformation property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualTaxInformation }
     *     
     */
    public IndividualTaxInformation getTaxInformation() {
        return taxInformation;
    }

    /**
     * Sets the value of the taxInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualTaxInformation }
     *     
     */
    public void setTaxInformation(IndividualTaxInformation value) {
        this.taxInformation = value;
    }

}

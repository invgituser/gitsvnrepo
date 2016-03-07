
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A Trust which is the customer and beneficial owner of an
 * 				account.
 * 			
 * 
 * <p>Java class for TrustApplicant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustApplicant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identification" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TrustIdentification"/>
 *         &lt;element name="FinancialInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}FinancialInformation" minOccurs="0"/>
 *         &lt;element name="RegulatoryInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatoryInformation"/>
 *         &lt;element name="RegulatedMemberships" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatedMemberships" minOccurs="0"/>
 *         &lt;element name="AccreditedInvestorInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AccreditedInvestorInformation" minOccurs="0"/>
 *         &lt;element name="Trustees" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TrusteesType"/>
 *         &lt;element name="Beneficiaries" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssociationTypeEntities"/>
 *         &lt;element name="Grantors" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssociationTypeEntities"/>
 *       &lt;/sequence>
 *       &lt;attribute name="third_party_mgmt" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustApplicant", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "identification",
    "financialInformation",
    "regulatoryInformation",
    "regulatedMemberships",
    "accreditedInvestorInformation",
    "trustees",
    "beneficiaries",
    "grantors"
})
public class TrustApplicant {

    @XmlElement(name = "Identification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected TrustIdentification identification;
    @XmlElement(name = "FinancialInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected FinancialInformation financialInformation;
    @XmlElement(name = "RegulatoryInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected RegulatoryInformation regulatoryInformation;
    @XmlElement(name = "RegulatedMemberships", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected RegulatedMemberships regulatedMemberships;
    @XmlElement(name = "AccreditedInvestorInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AccreditedInvestorInformation accreditedInvestorInformation;
    @XmlElement(name = "Trustees", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected TrusteesType trustees;
    @XmlElement(name = "Beneficiaries", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected AssociationTypeEntities beneficiaries;
    @XmlElement(name = "Grantors", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected AssociationTypeEntities grantors;
    @XmlAttribute(name = "third_party_mgmt", required = true)
    protected boolean thirdPartyMgmt;

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link TrustIdentification }
     *     
     */
    public TrustIdentification getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustIdentification }
     *     
     */
    public void setIdentification(TrustIdentification value) {
        this.identification = value;
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
     * Gets the value of the trustees property.
     * 
     * @return
     *     possible object is
     *     {@link TrusteesType }
     *     
     */
    public TrusteesType getTrustees() {
        return trustees;
    }

    /**
     * Sets the value of the trustees property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrusteesType }
     *     
     */
    public void setTrustees(TrusteesType value) {
        this.trustees = value;
    }

    /**
     * Gets the value of the beneficiaries property.
     * 
     * @return
     *     possible object is
     *     {@link AssociationTypeEntities }
     *     
     */
    public AssociationTypeEntities getBeneficiaries() {
        return beneficiaries;
    }

    /**
     * Sets the value of the beneficiaries property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociationTypeEntities }
     *     
     */
    public void setBeneficiaries(AssociationTypeEntities value) {
        this.beneficiaries = value;
    }

    /**
     * Gets the value of the grantors property.
     * 
     * @return
     *     possible object is
     *     {@link AssociationTypeEntities }
     *     
     */
    public AssociationTypeEntities getGrantors() {
        return grantors;
    }

    /**
     * Sets the value of the grantors property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociationTypeEntities }
     *     
     */
    public void setGrantors(AssociationTypeEntities value) {
        this.grantors = value;
    }

    /**
     * Gets the value of the thirdPartyMgmt property.
     * 
     */
    public boolean isThirdPartyMgmt() {
        return thirdPartyMgmt;
    }

    /**
     * Sets the value of the thirdPartyMgmt property.
     * 
     */
    public void setThirdPartyMgmt(boolean value) {
        this.thirdPartyMgmt = value;
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An organization which is the customer and beneficial owner of an
 * 				account.
 * 			
 * 
 * <p>Java class for OrganizationApplicant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrganizationApplicant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identification" type="{http://www.interactivebrokers.com/schemas/IBCust_import}OrganizationIdentification"/>
 *         &lt;element name="AccountSupport" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AccountSupportType" minOccurs="0"/>
 *         &lt;element name="FinancialInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}FinancialInformation" minOccurs="0"/>
 *         &lt;element name="AccreditedInvestorInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AccreditedInvestorInformation" minOccurs="0"/>
 *         &lt;element name="RegulatoryInformation" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatoryInformation"/>
 *         &lt;element name="PrimaryTrader" type="{http://www.interactivebrokers.com/schemas/IBCust_import}PrimaryTrader" minOccurs="0"/>
 *         &lt;element name="ControllingOfficer" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ControllingOfficer" minOccurs="0"/>
 *         &lt;element name="AssociatedEntities" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssociatedEntities" minOccurs="0"/>
 *         &lt;element name="RegulatedMemberships" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatedMemberships" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type_of_trading" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TradingFor_Type" />
 *       &lt;attribute name="type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}OrgApplicant_Type" default="CORPORATION" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationApplicant", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "identification",
    "accountSupport",
    "financialInformation",
    "accreditedInvestorInformation",
    "regulatoryInformation",
    "primaryTrader",
    "controllingOfficer",
    "associatedEntities",
    "regulatedMemberships"
})
public class OrganizationApplicant {

    @XmlElement(name = "Identification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected OrganizationIdentification identification;
    @XmlElement(name = "AccountSupport", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AccountSupportType accountSupport;
    @XmlElement(name = "FinancialInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected FinancialInformation financialInformation;
    @XmlElement(name = "AccreditedInvestorInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AccreditedInvestorInformation accreditedInvestorInformation;
    @XmlElement(name = "RegulatoryInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected RegulatoryInformation regulatoryInformation;
    @XmlElement(name = "PrimaryTrader", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected PrimaryTrader primaryTrader;
    @XmlElement(name = "ControllingOfficer", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected ControllingOfficer controllingOfficer;
    @XmlElement(name = "AssociatedEntities", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AssociatedEntities associatedEntities;
    @XmlElement(name = "RegulatedMemberships", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected RegulatedMemberships regulatedMemberships;
    @XmlAttribute(name = "type_of_trading", required = true)
    protected TradingForType typeOfTrading;
    @XmlAttribute(name = "type")
    protected OrgApplicantType type;

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationIdentification }
     *     
     */
    public OrganizationIdentification getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationIdentification }
     *     
     */
    public void setIdentification(OrganizationIdentification value) {
        this.identification = value;
    }

    /**
     * Gets the value of the accountSupport property.
     * 
     * @return
     *     possible object is
     *     {@link AccountSupportType }
     *     
     */
    public AccountSupportType getAccountSupport() {
        return accountSupport;
    }

    /**
     * Sets the value of the accountSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSupportType }
     *     
     */
    public void setAccountSupport(AccountSupportType value) {
        this.accountSupport = value;
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
     * Gets the value of the primaryTrader property.
     * 
     * @return
     *     possible object is
     *     {@link PrimaryTrader }
     *     
     */
    public PrimaryTrader getPrimaryTrader() {
        return primaryTrader;
    }

    /**
     * Sets the value of the primaryTrader property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimaryTrader }
     *     
     */
    public void setPrimaryTrader(PrimaryTrader value) {
        this.primaryTrader = value;
    }

    /**
     * Gets the value of the controllingOfficer property.
     * 
     * @return
     *     possible object is
     *     {@link ControllingOfficer }
     *     
     */
    public ControllingOfficer getControllingOfficer() {
        return controllingOfficer;
    }

    /**
     * Sets the value of the controllingOfficer property.
     * 
     * @param value
     *     allowed object is
     *     {@link ControllingOfficer }
     *     
     */
    public void setControllingOfficer(ControllingOfficer value) {
        this.controllingOfficer = value;
    }

    /**
     * Gets the value of the associatedEntities property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedEntities }
     *     
     */
    public AssociatedEntities getAssociatedEntities() {
        return associatedEntities;
    }

    /**
     * Sets the value of the associatedEntities property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedEntities }
     *     
     */
    public void setAssociatedEntities(AssociatedEntities value) {
        this.associatedEntities = value;
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
     * Gets the value of the typeOfTrading property.
     * 
     * @return
     *     possible object is
     *     {@link TradingForType }
     *     
     */
    public TradingForType getTypeOfTrading() {
        return typeOfTrading;
    }

    /**
     * Sets the value of the typeOfTrading property.
     * 
     * @param value
     *     allowed object is
     *     {@link TradingForType }
     *     
     */
    public void setTypeOfTrading(TradingForType value) {
        this.typeOfTrading = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link OrgApplicantType }
     *     
     */
    public OrgApplicantType getType() {
        if (type == null) {
            return OrgApplicantType.CORPORATION;
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgApplicantType }
     *     
     */
    public void setType(OrgApplicantType value) {
        this.type = value;
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A customer is the beneficial owner of an account. Customer
 * 				details are specific to the customer type and expected in
 * 				corresponding elements.
 * 			
 * 
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Organization" type="{http://www.interactivebrokers.com/schemas/IBCust_import}OrganizationApplicant" minOccurs="0"/>
 *         &lt;element name="AccountHolder" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualApplicant" minOccurs="0"/>
 *         &lt;element name="JointHolders" type="{http://www.interactivebrokers.com/schemas/IBCust_import}JointApplicant" minOccurs="0"/>
 *         &lt;element name="Trust" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TrustApplicant" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Customer_Type" default="ORG" />
 *       &lt;attribute name="prefix" type="{http://www.interactivebrokers.com/schemas/IBCust_import}UserNamePrefix_Type" />
 *       &lt;attribute name="email" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="md_status_nonpro" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="legal_residence_country" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tax_treaty_country" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="meet_aml_standard" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="has_direct_trading_access" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="origin_country" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "organization",
    "accountHolder",
    "jointHolders",
    "trust"
})
public class Customer {

    @XmlElement(name = "Organization", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected OrganizationApplicant organization;
    @XmlElement(name = "AccountHolder", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IndividualApplicant accountHolder;
    @XmlElement(name = "JointHolders", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected JointApplicant jointHolders;
    @XmlElement(name = "Trust", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected TrustApplicant trust;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "external_id", required = true)
    protected String externalId;
    @XmlAttribute(name = "type")
    protected CustomerType type;
    @XmlAttribute(name = "prefix")
    protected String prefix;
    @XmlAttribute(name = "email")
    protected String email;
    @XmlAttribute(name = "md_status_nonpro")
    protected Boolean mdStatusNonpro;
    @XmlAttribute(name = "legal_residence_country")
    protected String legalResidenceCountry;
    @XmlAttribute(name = "tax_treaty_country")
    protected String taxTreatyCountry;
    @XmlAttribute(name = "meet_aml_standard")
    protected Boolean meetAmlStandard;
    @XmlAttribute(name = "has_direct_trading_access")
    protected Boolean hasDirectTradingAccess;
    @XmlAttribute(name = "origin_country")
    protected String originCountry;

    /**
     * Gets the value of the organization property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationApplicant }
     *     
     */
    public OrganizationApplicant getOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationApplicant }
     *     
     */
    public void setOrganization(OrganizationApplicant value) {
        this.organization = value;
    }

    /**
     * Gets the value of the accountHolder property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualApplicant }
     *     
     */
    public IndividualApplicant getAccountHolder() {
        return accountHolder;
    }

    /**
     * Sets the value of the accountHolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualApplicant }
     *     
     */
    public void setAccountHolder(IndividualApplicant value) {
        this.accountHolder = value;
    }

    /**
     * Gets the value of the jointHolders property.
     * 
     * @return
     *     possible object is
     *     {@link JointApplicant }
     *     
     */
    public JointApplicant getJointHolders() {
        return jointHolders;
    }

    /**
     * Sets the value of the jointHolders property.
     * 
     * @param value
     *     allowed object is
     *     {@link JointApplicant }
     *     
     */
    public void setJointHolders(JointApplicant value) {
        this.jointHolders = value;
    }

    /**
     * Gets the value of the trust property.
     * 
     * @return
     *     possible object is
     *     {@link TrustApplicant }
     *     
     */
    public TrustApplicant getTrust() {
        return trust;
    }

    /**
     * Sets the value of the trust property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustApplicant }
     *     
     */
    public void setTrust(TrustApplicant value) {
        this.trust = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerType }
     *     
     */
    public CustomerType getType() {
        if (type == null) {
            return CustomerType.ORG;
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerType }
     *     
     */
    public void setType(CustomerType value) {
        this.type = value;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the mdStatusNonpro property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMdStatusNonpro() {
        if (mdStatusNonpro == null) {
            return false;
        } else {
            return mdStatusNonpro;
        }
    }

    /**
     * Sets the value of the mdStatusNonpro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMdStatusNonpro(Boolean value) {
        this.mdStatusNonpro = value;
    }

    /**
     * Gets the value of the legalResidenceCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalResidenceCountry() {
        return legalResidenceCountry;
    }

    /**
     * Sets the value of the legalResidenceCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalResidenceCountry(String value) {
        this.legalResidenceCountry = value;
    }

    /**
     * Gets the value of the taxTreatyCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxTreatyCountry() {
        return taxTreatyCountry;
    }

    /**
     * Sets the value of the taxTreatyCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxTreatyCountry(String value) {
        this.taxTreatyCountry = value;
    }

    /**
     * Gets the value of the meetAmlStandard property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMeetAmlStandard() {
        if (meetAmlStandard == null) {
            return true;
        } else {
            return meetAmlStandard;
        }
    }

    /**
     * Sets the value of the meetAmlStandard property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMeetAmlStandard(Boolean value) {
        this.meetAmlStandard = value;
    }

    /**
     * Gets the value of the hasDirectTradingAccess property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isHasDirectTradingAccess() {
        if (hasDirectTradingAccess == null) {
            return true;
        } else {
            return hasDirectTradingAccess;
        }
    }

    /**
     * Sets the value of the hasDirectTradingAccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasDirectTradingAccess(Boolean value) {
        this.hasDirectTradingAccess = value;
    }

    /**
     * Gets the value of the originCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCountry() {
        return originCountry;
    }

    /**
     * Sets the value of the originCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCountry(String value) {
        this.originCountry = value;
    }

}

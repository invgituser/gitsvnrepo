
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Account Support classifies organizations in 4 categories of
 *                         trading intentions.
 * 			
 * 
 * <p>Java class for AccountSupportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountSupportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrimaryContributor" type="{http://www.interactivebrokers.com/schemas/IBCust_import}PrimaryContributorType" minOccurs="0"/>
 *         &lt;element name="Administrator" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AdministratorType" minOccurs="0"/>
 *         &lt;element name="AdministratorContactPerson" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AdministratorContactPersonType" minOccurs="0"/>
 *         &lt;element name="OwnersResideUS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SolicitOwnersResideUS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AcceptOwnersResideUS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TradeIntention_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountSupportType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "businessDescription",
    "primaryContributor",
    "administrator",
    "administratorContactPerson",
    "ownersResideUS",
    "solicitOwnersResideUS",
    "acceptOwnersResideUS"
})
public class AccountSupportType {

    @XmlElement(name = "BusinessDescription", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected String businessDescription;
    @XmlElement(name = "PrimaryContributor", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected PrimaryContributorType primaryContributor;
    @XmlElement(name = "Administrator", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AdministratorType administrator;
    @XmlElement(name = "AdministratorContactPerson", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AdministratorContactPersonType administratorContactPerson;
    @XmlElement(name = "OwnersResideUS", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Boolean ownersResideUS;
    @XmlElement(name = "SolicitOwnersResideUS", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Boolean solicitOwnersResideUS;
    @XmlElement(name = "AcceptOwnersResideUS", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Boolean acceptOwnersResideUS;
    @XmlAttribute(name = "type", required = true)
    protected TradeIntentionType type;

    /**
     * Gets the value of the businessDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDescription() {
        return businessDescription;
    }

    /**
     * Sets the value of the businessDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDescription(String value) {
        this.businessDescription = value;
    }

    /**
     * Gets the value of the primaryContributor property.
     * 
     * @return
     *     possible object is
     *     {@link PrimaryContributorType }
     *     
     */
    public PrimaryContributorType getPrimaryContributor() {
        return primaryContributor;
    }

    /**
     * Sets the value of the primaryContributor property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimaryContributorType }
     *     
     */
    public void setPrimaryContributor(PrimaryContributorType value) {
        this.primaryContributor = value;
    }

    /**
     * Gets the value of the administrator property.
     * 
     * @return
     *     possible object is
     *     {@link AdministratorType }
     *     
     */
    public AdministratorType getAdministrator() {
        return administrator;
    }

    /**
     * Sets the value of the administrator property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdministratorType }
     *     
     */
    public void setAdministrator(AdministratorType value) {
        this.administrator = value;
    }

    /**
     * Gets the value of the administratorContactPerson property.
     * 
     * @return
     *     possible object is
     *     {@link AdministratorContactPersonType }
     *     
     */
    public AdministratorContactPersonType getAdministratorContactPerson() {
        return administratorContactPerson;
    }

    /**
     * Sets the value of the administratorContactPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdministratorContactPersonType }
     *     
     */
    public void setAdministratorContactPerson(AdministratorContactPersonType value) {
        this.administratorContactPerson = value;
    }

    /**
     * Gets the value of the ownersResideUS property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOwnersResideUS() {
        return ownersResideUS;
    }

    /**
     * Sets the value of the ownersResideUS property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOwnersResideUS(Boolean value) {
        this.ownersResideUS = value;
    }

    /**
     * Gets the value of the solicitOwnersResideUS property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSolicitOwnersResideUS() {
        return solicitOwnersResideUS;
    }

    /**
     * Sets the value of the solicitOwnersResideUS property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSolicitOwnersResideUS(Boolean value) {
        this.solicitOwnersResideUS = value;
    }

    /**
     * Gets the value of the acceptOwnersResideUS property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAcceptOwnersResideUS() {
        return acceptOwnersResideUS;
    }

    /**
     * Sets the value of the acceptOwnersResideUS property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAcceptOwnersResideUS(Boolean value) {
        this.acceptOwnersResideUS = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TradeIntentionType }
     *     
     */
    public TradeIntentionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeIntentionType }
     *     
     */
    public void setType(TradeIntentionType value) {
        this.type = value;
    }

}

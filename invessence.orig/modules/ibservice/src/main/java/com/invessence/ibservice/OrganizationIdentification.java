
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				The organization name will be the account title for cashiering
 * 				functions. Otherwise, this information is compliance mandated.
 * 			
 * 
 * <p>Java class for OrganizationIdentification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrganizationIdentification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlaceOfBusiness" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address"/>
 *         &lt;element name="MailingAddress" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
 *         &lt;element name="Phones" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualPhones" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="business_description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="identification" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="identification_country" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CountryCode_Type" />
 *       &lt;attribute name="formation_country" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CountryCode_Type" />
 *       &lt;attribute name="formation_state" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}StateCode_Type" />
 *       &lt;attribute name="same_mail_address" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationIdentification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "placeOfBusiness",
    "mailingAddress",
    "phones"
})
public class OrganizationIdentification {

    @XmlElement(name = "PlaceOfBusiness", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Address placeOfBusiness;
    @XmlElement(name = "MailingAddress", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address mailingAddress;
    @XmlElement(name = "Phones", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IndividualPhones phones;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "business_description", required = true)
    protected String businessDescription;
    @XmlAttribute(name = "identification", required = true)
    protected String identification;
    @XmlAttribute(name = "identification_country", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String identificationCountry;
    @XmlAttribute(name = "formation_country", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String formationCountry;
    @XmlAttribute(name = "formation_state", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String formationState;
    @XmlAttribute(name = "same_mail_address")
    protected Boolean sameMailAddress;

    /**
     * Gets the value of the placeOfBusiness property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getPlaceOfBusiness() {
        return placeOfBusiness;
    }

    /**
     * Sets the value of the placeOfBusiness property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setPlaceOfBusiness(Address value) {
        this.placeOfBusiness = value;
    }

    /**
     * Gets the value of the mailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getMailingAddress() {
        return mailingAddress;
    }

    /**
     * Sets the value of the mailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setMailingAddress(Address value) {
        this.mailingAddress = value;
    }

    /**
     * Gets the value of the phones property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualPhones }
     *     
     */
    public IndividualPhones getPhones() {
        return phones;
    }

    /**
     * Sets the value of the phones property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualPhones }
     *     
     */
    public void setPhones(IndividualPhones value) {
        this.phones = value;
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
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentification(String value) {
        this.identification = value;
    }

    /**
     * Gets the value of the identificationCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationCountry() {
        return identificationCountry;
    }

    /**
     * Sets the value of the identificationCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationCountry(String value) {
        this.identificationCountry = value;
    }

    /**
     * Gets the value of the formationCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormationCountry() {
        return formationCountry;
    }

    /**
     * Sets the value of the formationCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormationCountry(String value) {
        this.formationCountry = value;
    }

    /**
     * Gets the value of the formationState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormationState() {
        return formationState;
    }

    /**
     * Sets the value of the formationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormationState(String value) {
        this.formationState = value;
    }

    /**
     * Gets the value of the sameMailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSameMailAddress() {
        if (sameMailAddress == null) {
            return true;
        } else {
            return sameMailAddress;
        }
    }

    /**
     * Sets the value of the sameMailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSameMailAddress(Boolean value) {
        this.sameMailAddress = value;
    }

}

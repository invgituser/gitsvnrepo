
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				The Trust name will be the account title for cashiering
 * 				functions. Otherwise, this information is compliance mandated.
 * 			
 * 
 * <p>Java class for TrustIdentification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustIdentification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Address" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address"/>
 *         &lt;element name="MailingAddress" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
 *         &lt;element name="Phones" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualPhones"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type_of_trust" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="purpose_of_trust" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="date_formed" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DOB" />
 *       &lt;attribute name="formation_country" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CountryCode_Type" />
 *       &lt;attribute name="formation_state" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}StateCode_Type" />
 *       &lt;attribute name="registration_number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="registration_country" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CountryCode_Type" />
 *       &lt;attribute name="same_mail_address" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustIdentification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "address",
    "mailingAddress",
    "phones"
})
public class TrustIdentification {

    @XmlElement(name = "Address", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Address address;
    @XmlElement(name = "MailingAddress", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address mailingAddress;
    @XmlElement(name = "Phones", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected IndividualPhones phones;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "type_of_trust", required = true)
    protected String typeOfTrust;
    @XmlAttribute(name = "purpose_of_trust", required = true)
    protected String purposeOfTrust;
    @XmlAttribute(name = "date_formed", required = true)
    protected XMLGregorianCalendar dateFormed;
    @XmlAttribute(name = "formation_country", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String formationCountry;
    @XmlAttribute(name = "formation_state", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String formationState;
    @XmlAttribute(name = "registration_number", required = true)
    protected String registrationNumber;
    @XmlAttribute(name = "registration_country", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String registrationCountry;
    @XmlAttribute(name = "same_mail_address")
    protected Boolean sameMailAddress;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
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
     * Gets the value of the typeOfTrust property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfTrust() {
        return typeOfTrust;
    }

    /**
     * Sets the value of the typeOfTrust property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfTrust(String value) {
        this.typeOfTrust = value;
    }

    /**
     * Gets the value of the purposeOfTrust property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeOfTrust() {
        return purposeOfTrust;
    }

    /**
     * Sets the value of the purposeOfTrust property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeOfTrust(String value) {
        this.purposeOfTrust = value;
    }

    /**
     * Gets the value of the dateFormed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFormed() {
        return dateFormed;
    }

    /**
     * Sets the value of the dateFormed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFormed(XMLGregorianCalendar value) {
        this.dateFormed = value;
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
     * Gets the value of the registrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the value of the registrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationNumber(String value) {
        this.registrationNumber = value;
    }

    /**
     * Gets the value of the registrationCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationCountry() {
        return registrationCountry;
    }

    /**
     * Sets the value of the registrationCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationCountry(String value) {
        this.registrationCountry = value;
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

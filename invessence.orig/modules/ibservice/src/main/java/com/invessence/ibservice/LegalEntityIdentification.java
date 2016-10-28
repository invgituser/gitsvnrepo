
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
 * 				Partial organization identification information. For associated
 * 				legal entities of an organizational customer.
 * 			
 * 
 * <p>Java class for LegalEntityIdentification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LegalEntityIdentification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlaceOfBusiness" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
 *         &lt;element name="MailingAddress" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="identification" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="identification_country" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="formation_country" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CountryCode_Type" />
 *       &lt;attribute name="formation_type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Organization_Type" />
 *       &lt;attribute name="exchange_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="exchange_symbol" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="same_mail_address" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegalEntityIdentification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "placeOfBusiness",
    "mailingAddress"
})
public class LegalEntityIdentification {

    @XmlElement(name = "PlaceOfBusiness", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address placeOfBusiness;
    @XmlElement(name = "MailingAddress", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address mailingAddress;
    @XmlAttribute(name = "identification", required = true)
    protected String identification;
    @XmlAttribute(name = "identification_country", required = true)
    protected String identificationCountry;
    @XmlAttribute(name = "formation_country", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String formationCountry;
    @XmlAttribute(name = "formation_type", required = true)
    protected OrganizationType formationType;
    @XmlAttribute(name = "exchange_code")
    protected String exchangeCode;
    @XmlAttribute(name = "exchange_symbol")
    protected String exchangeSymbol;
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
     * Gets the value of the formationType property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationType }
     *     
     */
    public OrganizationType getFormationType() {
        return formationType;
    }

    /**
     * Sets the value of the formationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationType }
     *     
     */
    public void setFormationType(OrganizationType value) {
        this.formationType = value;
    }

    /**
     * Gets the value of the exchangeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeCode() {
        return exchangeCode;
    }

    /**
     * Sets the value of the exchangeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeCode(String value) {
        this.exchangeCode = value;
    }

    /**
     * Gets the value of the exchangeSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeSymbol() {
        return exchangeSymbol;
    }

    /**
     * Sets the value of the exchangeSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeSymbol(String value) {
        this.exchangeSymbol = value;
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

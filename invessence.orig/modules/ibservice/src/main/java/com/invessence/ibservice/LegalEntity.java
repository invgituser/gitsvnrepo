
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A legal entity is anything other than an individual.
 * 			
 * 
 * <p>Java class for LegalEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LegalEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.interactivebrokers.com/schemas/IBCust_import}EntityName"/>
 *         &lt;element name="Location" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
 *         &lt;element name="Phones" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualPhones"/>
 *         &lt;element name="Email" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Email"/>
 *         &lt;element name="LegalEntityIdentification" type="{http://www.interactivebrokers.com/schemas/IBCust_import}LegalEntityIdentification"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegalEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "name",
    "location",
    "phones",
    "email",
    "legalEntityIdentification"
})
@XmlSeeAlso({
    AssociatedEntity.class
})
public class LegalEntity {

    @XmlElement(name = "Name", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected EntityName name;
    @XmlElement(name = "Location", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address location;
    @XmlElement(name = "Phones", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected IndividualPhones phones;
    @XmlElement(name = "Email", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Email email;
    @XmlElement(name = "LegalEntityIdentification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected LegalEntityIdentification legalEntityIdentification;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "external_id", required = true)
    protected String externalId;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link EntityName }
     *     
     */
    public EntityName getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityName }
     *     
     */
    public void setName(EntityName value) {
        this.name = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setLocation(Address value) {
        this.location = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link Email }
     *     
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link Email }
     *     
     */
    public void setEmail(Email value) {
        this.email = value;
    }

    /**
     * Gets the value of the legalEntityIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntityIdentification }
     *     
     */
    public LegalEntityIdentification getLegalEntityIdentification() {
        return legalEntityIdentification;
    }

    /**
     * Sets the value of the legalEntityIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntityIdentification }
     *     
     */
    public void setLegalEntityIdentification(LegalEntityIdentification value) {
        this.legalEntityIdentification = value;
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

}

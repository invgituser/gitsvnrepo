
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A legal entity is anything other than an individual. A simple
 * 				legal entity being asscoiated with an account in tertiary
 * 				capacity such as IRA beneficiary where the entity's name is the
 * 				only required piece of information.
 * 			
 * 
 * <p>Java class for SimpleLegalEntityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimpleLegalEntityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.interactivebrokers.com/schemas/IBCust_import}EntityName"/>
 *         &lt;element name="Location" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
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
@XmlType(name = "SimpleLegalEntityType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "name",
    "location"
})
@XmlSeeAlso({
    IRAContingentBeneficiaryEntity.class,
    IRAPrimaryBeneficiaryEntity.class
})
public class SimpleLegalEntityType {

    @XmlElement(name = "Name", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected EntityName name;
    @XmlElement(name = "Location", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address location;
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

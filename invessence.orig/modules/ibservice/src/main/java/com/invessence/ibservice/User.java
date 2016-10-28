
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A user is an individual with specific access to one or more
 * 				customer accounts. A user must be linked to an individual
 * 				through a specified id.
 * 			
 * 
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserPrivilege" type="{http://www.interactivebrokers.com/schemas/IBCust_import}UserPrivilege" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MDServices" type="{http://www.interactivebrokers.com/schemas/IBCust_import}MDServicesType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_user_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_individual_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="prefix" type="{http://www.interactivebrokers.com/schemas/IBCust_import}UserNamePrefix_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "userPrivilege",
    "mdServices"
})
public class User {

    @XmlElement(name = "UserPrivilege", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<UserPrivilege> userPrivilege;
    @XmlElement(name = "MDServices", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected MDServicesType mdServices;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "external_user_id", required = true)
    protected String externalUserId;
    @XmlAttribute(name = "external_individual_id", required = true)
    protected String externalIndividualId;
    @XmlAttribute(name = "prefix")
    protected String prefix;

    /**
     * Gets the value of the userPrivilege property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userPrivilege property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserPrivilege().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserPrivilege }
     * 
     * 
     */
    public List<UserPrivilege> getUserPrivilege() {
        if (userPrivilege == null) {
            userPrivilege = new ArrayList<UserPrivilege>();
        }
        return this.userPrivilege;
    }

    /**
     * Gets the value of the mdServices property.
     * 
     * @return
     *     possible object is
     *     {@link MDServicesType }
     *     
     */
    public MDServicesType getMDServices() {
        return mdServices;
    }

    /**
     * Sets the value of the mdServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link MDServicesType }
     *     
     */
    public void setMDServices(MDServicesType value) {
        this.mdServices = value;
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
     * Gets the value of the externalUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalUserId() {
        return externalUserId;
    }

    /**
     * Sets the value of the externalUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalUserId(String value) {
        this.externalUserId = value;
    }

    /**
     * Gets the value of the externalIndividualId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIndividualId() {
        return externalIndividualId;
    }

    /**
     * Sets the value of the externalIndividualId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIndividualId(String value) {
        this.externalIndividualId = value;
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

}

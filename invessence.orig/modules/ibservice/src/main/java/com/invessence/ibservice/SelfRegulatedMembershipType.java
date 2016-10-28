
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				List of exchanges and self regulated organizations the customer
 * 				is a member of.
 * 			
 * 
 * <p>Java class for SelfRegulatedMembershipType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SelfRegulatedMembershipType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exchanges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organizations" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelfRegulatedMembershipType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "exchanges",
    "organizations"
})
public class SelfRegulatedMembershipType {

    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", defaultValue = "")
    protected String exchanges;
    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", defaultValue = "")
    protected String organizations;

    /**
     * Gets the value of the exchanges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchanges() {
        return exchanges;
    }

    /**
     * Sets the value of the exchanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchanges(String value) {
        this.exchanges = value;
    }

    /**
     * Gets the value of the organizations property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizations() {
        return organizations;
    }

    /**
     * Sets the value of the organizations property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizations(String value) {
        this.organizations = value;
    }

}

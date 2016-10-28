
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A legal entity that is the trustee of a trust. An entity trustee
 * 				must detail an employee signatory.
 * 			
 * 
 * <p>Java class for TrusteeEntityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrusteeEntityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LegalEntity" type="{http://www.interactivebrokers.com/schemas/IBCust_import}LegalEntity"/>
 *         &lt;element name="Employee" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Individual"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrusteeEntityType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "legalEntity",
    "employee"
})
public class TrusteeEntityType {

    @XmlElement(name = "LegalEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected LegalEntity legalEntity;
    @XmlElement(name = "Employee", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Individual employee;

    /**
     * Gets the value of the legalEntity property.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntity }
     *     
     */
    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    /**
     * Sets the value of the legalEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntity }
     *     
     */
    public void setLegalEntity(LegalEntity value) {
        this.legalEntity = value;
    }

    /**
     * Gets the value of the employee property.
     * 
     * @return
     *     possible object is
     *     {@link Individual }
     *     
     */
    public Individual getEmployee() {
        return employee;
    }

    /**
     * Sets the value of the employee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Individual }
     *     
     */
    public void setEmployee(Individual value) {
        this.employee = value;
    }

}

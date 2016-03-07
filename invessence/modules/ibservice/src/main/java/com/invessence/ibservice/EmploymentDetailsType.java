
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Employment details of the customer.
 * 			
 * 
 * <p>Java class for EmploymentDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmploymentDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="occupation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employer_business" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employer_address" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address"/>
 *         &lt;element name="employer_phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmploymentDetailsType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "employer",
    "occupation",
    "employerBusiness",
    "employerAddress",
    "employerPhone"
})
public class EmploymentDetailsType {

    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String employer;
    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String occupation;
    @XmlElement(name = "employer_business", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String employerBusiness;
    @XmlElement(name = "employer_address", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Address employerAddress;
    @XmlElement(name = "employer_phone", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String employerPhone;

    /**
     * Gets the value of the employer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * Sets the value of the employer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployer(String value) {
        this.employer = value;
    }

    /**
     * Gets the value of the occupation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the value of the occupation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupation(String value) {
        this.occupation = value;
    }

    /**
     * Gets the value of the employerBusiness property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployerBusiness() {
        return employerBusiness;
    }

    /**
     * Sets the value of the employerBusiness property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployerBusiness(String value) {
        this.employerBusiness = value;
    }

    /**
     * Gets the value of the employerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getEmployerAddress() {
        return employerAddress;
    }

    /**
     * Sets the value of the employerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setEmployerAddress(Address value) {
        this.employerAddress = value;
    }

    /**
     * Gets the value of the employerPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployerPhone() {
        return employerPhone;
    }

    /**
     * Sets the value of the employerPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployerPhone(String value) {
        this.employerPhone = value;
    }

}

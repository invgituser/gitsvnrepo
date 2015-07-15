
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Required information for completing a Form W-9 Request for TIN
 * 				and Certification. Address details will be defaulted from the
 * 				application.
 * 			
 * 
 * <p>Java class for FormW9 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormW9">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="business_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="customer_type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}W9Customer_Type" />
 *       &lt;attribute name="tax_classification" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="other_customer_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tin_type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TIN_Type" default="SSN" />
 *       &lt;attribute name="cert1" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="cert2" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="cert3" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormW9", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class FormW9 {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "business_name")
    protected String businessName;
    @XmlAttribute(name = "customer_type", required = true)
    protected W9CustomerType customerType;
    @XmlAttribute(name = "tax_classification")
    protected String taxClassification;
    @XmlAttribute(name = "other_customer_type")
    protected String otherCustomerType;
    @XmlAttribute(name = "tin", required = true)
    protected String tin;
    @XmlAttribute(name = "tin_type")
    protected TINType tinType;
    @XmlAttribute(name = "cert1", required = true)
    protected boolean cert1;
    @XmlAttribute(name = "cert2", required = true)
    protected boolean cert2;
    @XmlAttribute(name = "cert3", required = true)
    protected boolean cert3;

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
     * Gets the value of the businessName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the value of the businessName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessName(String value) {
        this.businessName = value;
    }

    /**
     * Gets the value of the customerType property.
     * 
     * @return
     *     possible object is
     *     {@link W9CustomerType }
     *     
     */
    public W9CustomerType getCustomerType() {
        return customerType;
    }

    /**
     * Sets the value of the customerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link W9CustomerType }
     *     
     */
    public void setCustomerType(W9CustomerType value) {
        this.customerType = value;
    }

    /**
     * Gets the value of the taxClassification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxClassification() {
        return taxClassification;
    }

    /**
     * Sets the value of the taxClassification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxClassification(String value) {
        this.taxClassification = value;
    }

    /**
     * Gets the value of the otherCustomerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherCustomerType() {
        return otherCustomerType;
    }

    /**
     * Sets the value of the otherCustomerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherCustomerType(String value) {
        this.otherCustomerType = value;
    }

    /**
     * Gets the value of the tin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTin() {
        return tin;
    }

    /**
     * Sets the value of the tin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTin(String value) {
        this.tin = value;
    }

    /**
     * Gets the value of the tinType property.
     * 
     * @return
     *     possible object is
     *     {@link TINType }
     *     
     */
    public TINType getTinType() {
        if (tinType == null) {
            return TINType.SSN;
        } else {
            return tinType;
        }
    }

    /**
     * Sets the value of the tinType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TINType }
     *     
     */
    public void setTinType(TINType value) {
        this.tinType = value;
    }

    /**
     * Gets the value of the cert1 property.
     * 
     */
    public boolean isCert1() {
        return cert1;
    }

    /**
     * Sets the value of the cert1 property.
     * 
     */
    public void setCert1(boolean value) {
        this.cert1 = value;
    }

    /**
     * Gets the value of the cert2 property.
     * 
     */
    public boolean isCert2() {
        return cert2;
    }

    /**
     * Sets the value of the cert2 property.
     * 
     */
    public void setCert2(boolean value) {
        this.cert2 = value;
    }

    /**
     * Gets the value of the cert3 property.
     * 
     */
    public boolean isCert3() {
        return cert3;
    }

    /**
     * Sets the value of the cert3 property.
     * 
     */
    public void setCert3(boolean value) {
        this.cert3 = value;
    }

}

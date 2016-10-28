
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Notification details for fully paid Stock Lend and Borrow
 * 			
 * 
 * <p>Java class for FullyPaidSLBRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FullyPaidSLBRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ib_acct" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="user_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FullyPaidSLBRequestType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class FullyPaidSLBRequestType {

    @XmlAttribute(name = "ib_acct", required = true)
    protected String ibAcct;
    @XmlAttribute(name = "user_name", required = true)
    protected String userName;

    /**
     * Gets the value of the ibAcct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbAcct() {
        return ibAcct;
    }

    /**
     * Sets the value of the ibAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbAcct(String value) {
        this.ibAcct = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}

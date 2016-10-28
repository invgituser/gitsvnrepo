
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Tax information about the customer required by the IRS. A Form
 * 				W-9 or W-8 is required for all customers except customers of
 * 				IB-India.
 * 			
 * 
 * <p>Java class for IndividualTaxInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndividualTaxInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="W9" type="{http://www.interactivebrokers.com/schemas/IBCust_import}FormW9" minOccurs="0"/>
 *         &lt;element name="W8Ben" type="{http://www.interactivebrokers.com/schemas/IBCust_import}FormW8BEN" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualTaxInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "w9",
    "w8Ben"
})
public class IndividualTaxInformation {

    @XmlElement(name = "W9", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected FormW9 w9;
    @XmlElement(name = "W8Ben", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected FormW8BEN w8Ben;

    /**
     * Gets the value of the w9 property.
     * 
     * @return
     *     possible object is
     *     {@link FormW9 }
     *     
     */
    public FormW9 getW9() {
        return w9;
    }

    /**
     * Sets the value of the w9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormW9 }
     *     
     */
    public void setW9(FormW9 value) {
        this.w9 = value;
    }

    /**
     * Gets the value of the w8Ben property.
     * 
     * @return
     *     possible object is
     *     {@link FormW8BEN }
     *     
     */
    public FormW8BEN getW8Ben() {
        return w8Ben;
    }

    /**
     * Sets the value of the w8Ben property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormW8BEN }
     *     
     */
    public void setW8Ben(FormW8BEN value) {
        this.w8Ben = value;
    }

}

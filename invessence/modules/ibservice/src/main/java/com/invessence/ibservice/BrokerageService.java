
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An ACES account brokerage service.
 * 			
 * 
 * <p>Java class for BrokerageService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BrokerageService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="code" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}BrokerageService_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrokerageService", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class BrokerageService {

    @XmlAttribute(name = "code", required = true)
    protected BrokerageServiceType code;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link BrokerageServiceType }
     *     
     */
    public BrokerageServiceType getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrokerageServiceType }
     *     
     */
    public void setCode(BrokerageServiceType value) {
        this.code = value;
    }

}

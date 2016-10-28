
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An ACES asset class, aka financial instrument type.
 * 			
 * 
 * <p>Java class for AssetClass complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="code" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssetClass_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetClass", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class AssetClass {

    @XmlAttribute(name = "code", required = true)
    protected AssetClassType code;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link AssetClassType }
     *     
     */
    public AssetClassType getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetClassType }
     *     
     */
    public void setCode(AssetClassType value) {
        this.code = value;
    }

}

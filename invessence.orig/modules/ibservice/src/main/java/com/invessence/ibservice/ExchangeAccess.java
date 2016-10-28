
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An ACES account exchange access.
 * 			
 * 
 * <p>Java class for ExchangeAccess complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExchangeAccess">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="asset_class" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssetClass_Type" />
 *       &lt;attribute name="exchange" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ExchangeCode_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangeAccess", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class ExchangeAccess {

    @XmlAttribute(name = "asset_class", required = true)
    protected AssetClassType assetClass;
    @XmlAttribute(name = "exchange", required = true)
    protected ExchangeCodeType exchange;

    /**
     * Gets the value of the assetClass property.
     * 
     * @return
     *     possible object is
     *     {@link AssetClassType }
     *     
     */
    public AssetClassType getAssetClass() {
        return assetClass;
    }

    /**
     * Sets the value of the assetClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetClassType }
     *     
     */
    public void setAssetClass(AssetClassType value) {
        this.assetClass = value;
    }

    /**
     * Gets the value of the exchange property.
     * 
     * @return
     *     possible object is
     *     {@link ExchangeCodeType }
     *     
     */
    public ExchangeCodeType getExchange() {
        return exchange;
    }

    /**
     * Sets the value of the exchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangeCodeType }
     *     
     */
    public void setExchange(ExchangeCodeType value) {
        this.exchange = value;
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An ACES account trading bundle.
 * 			
 * 
 * <p>Java class for TradingPermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TradingPermission">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="asset_class" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssetClass_Type" />
 *       &lt;attribute name="exchange_group" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradingPermission", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class TradingPermission {

    @XmlAttribute(name = "asset_class")
    protected AssetClassType assetClass;
    @XmlAttribute(name = "exchange_group", required = true)
    protected String exchangeGroup;

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
     * Gets the value of the exchangeGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeGroup() {
        return exchangeGroup;
    }

    /**
     * Sets the value of the exchangeGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeGroup(String value) {
        this.exchangeGroup = value;
    }

}

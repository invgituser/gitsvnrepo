
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PublicCompanyInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PublicCompanyInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExchangeTradedOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QuotedSymbol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PublicCompanyInfoType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "exchangeTradedOn",
    "quotedSymbol"
})
public class PublicCompanyInfoType {

    @XmlElement(name = "ExchangeTradedOn", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true, defaultValue = "")
    protected String exchangeTradedOn;
    @XmlElement(name = "QuotedSymbol", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true, defaultValue = "")
    protected String quotedSymbol;

    /**
     * Gets the value of the exchangeTradedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeTradedOn() {
        return exchangeTradedOn;
    }

    /**
     * Sets the value of the exchangeTradedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeTradedOn(String value) {
        this.exchangeTradedOn = value;
    }

    /**
     * Gets the value of the quotedSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotedSymbol() {
        return quotedSymbol;
    }

    /**
     * Sets the value of the quotedSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotedSymbol(String value) {
        this.quotedSymbol = value;
    }

}

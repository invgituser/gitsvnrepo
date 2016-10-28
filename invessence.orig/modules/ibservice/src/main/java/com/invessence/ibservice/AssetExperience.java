
package com.invessence.ibservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Trading experience and knowledge for a specific financial
 * 				instrument.
 * 			
 * 
 * <p>Java class for AssetExperience complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetExperience">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="asset_class" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssetClass_Type" />
 *       &lt;attribute name="years_trading" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="trades_per_year" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="knowledge_level" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}KnowledgeLevel_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetExperience", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class AssetExperience {

    @XmlAttribute(name = "asset_class", required = true)
    protected AssetClassType assetClass;
    @XmlAttribute(name = "years_trading", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger yearsTrading;
    @XmlAttribute(name = "trades_per_year", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger tradesPerYear;
    @XmlAttribute(name = "knowledge_level", required = true)
    protected KnowledgeLevelType knowledgeLevel;

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
     * Gets the value of the yearsTrading property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYearsTrading() {
        return yearsTrading;
    }

    /**
     * Sets the value of the yearsTrading property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYearsTrading(BigInteger value) {
        this.yearsTrading = value;
    }

    /**
     * Gets the value of the tradesPerYear property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTradesPerYear() {
        return tradesPerYear;
    }

    /**
     * Sets the value of the tradesPerYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTradesPerYear(BigInteger value) {
        this.tradesPerYear = value;
    }

    /**
     * Gets the value of the knowledgeLevel property.
     * 
     * @return
     *     possible object is
     *     {@link KnowledgeLevelType }
     *     
     */
    public KnowledgeLevelType getKnowledgeLevel() {
        return knowledgeLevel;
    }

    /**
     * Sets the value of the knowledgeLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link KnowledgeLevelType }
     *     
     */
    public void setKnowledgeLevel(KnowledgeLevelType value) {
        this.knowledgeLevel = value;
    }

}

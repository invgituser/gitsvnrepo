
package com.invessence.ibservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * EFP order quantity limits.
 * 
 * <p>Java class for EFPQuantityLimits complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EFPQuantityLimits">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="max_nominal_efp_per_order" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="max_net_efp_trades" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="max_gross_efp_trades" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EFPQuantityLimits", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class EFPQuantityLimits {

    @XmlAttribute(name = "max_nominal_efp_per_order", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxNominalEfpPerOrder;
    @XmlAttribute(name = "max_net_efp_trades", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxNetEfpTrades;
    @XmlAttribute(name = "max_gross_efp_trades", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxGrossEfpTrades;

    /**
     * Gets the value of the maxNominalEfpPerOrder property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNominalEfpPerOrder() {
        return maxNominalEfpPerOrder;
    }

    /**
     * Sets the value of the maxNominalEfpPerOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNominalEfpPerOrder(BigInteger value) {
        this.maxNominalEfpPerOrder = value;
    }

    /**
     * Gets the value of the maxNetEfpTrades property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNetEfpTrades() {
        return maxNetEfpTrades;
    }

    /**
     * Sets the value of the maxNetEfpTrades property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNetEfpTrades(BigInteger value) {
        this.maxNetEfpTrades = value;
    }

    /**
     * Gets the value of the maxGrossEfpTrades property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxGrossEfpTrades() {
        return maxGrossEfpTrades;
    }

    /**
     * Sets the value of the maxGrossEfpTrades property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxGrossEfpTrades(BigInteger value) {
        this.maxGrossEfpTrades = value;
    }

}

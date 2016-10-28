
package com.invessence.ibservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Per day order value limits.
 * 
 * <p>Java class for OrderValueLimits complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderValueLimits">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="max_order_value" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="max_gross_value" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="max_net_value" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="net_contract_limit" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderValueLimits", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class OrderValueLimits {

    @XmlAttribute(name = "max_order_value", required = true)
    protected BigDecimal maxOrderValue;
    @XmlAttribute(name = "max_gross_value", required = true)
    protected BigDecimal maxGrossValue;
    @XmlAttribute(name = "max_net_value", required = true)
    protected BigDecimal maxNetValue;
    @XmlAttribute(name = "net_contract_limit", required = true)
    protected BigDecimal netContractLimit;

    /**
     * Gets the value of the maxOrderValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxOrderValue() {
        return maxOrderValue;
    }

    /**
     * Sets the value of the maxOrderValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxOrderValue(BigDecimal value) {
        this.maxOrderValue = value;
    }

    /**
     * Gets the value of the maxGrossValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxGrossValue() {
        return maxGrossValue;
    }

    /**
     * Sets the value of the maxGrossValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxGrossValue(BigDecimal value) {
        this.maxGrossValue = value;
    }

    /**
     * Gets the value of the maxNetValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxNetValue() {
        return maxNetValue;
    }

    /**
     * Sets the value of the maxNetValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxNetValue(BigDecimal value) {
        this.maxNetValue = value;
    }

    /**
     * Gets the value of the netContractLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetContractLimit() {
        return netContractLimit;
    }

    /**
     * Sets the value of the netContractLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetContractLimit(BigDecimal value) {
        this.netContractLimit = value;
    }

}

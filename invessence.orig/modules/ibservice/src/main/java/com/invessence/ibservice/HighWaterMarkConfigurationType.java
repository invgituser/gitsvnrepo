
package com.invessence.ibservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				HighWaterMark Configuration.
 * 			
 * 
 * <p>Java class for HighWaterMarkConfigurationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HighWaterMarkConfigurationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="NumberOfPeriods" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="ProrateForWithdrawals" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HighWaterMarkConfigurationType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class HighWaterMarkConfigurationType {

    @XmlAttribute(name = "NumberOfPeriods", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numberOfPeriods;
    @XmlAttribute(name = "ProrateForWithdrawals")
    protected Boolean prorateForWithdrawals;

    /**
     * Gets the value of the numberOfPeriods property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfPeriods() {
        return numberOfPeriods;
    }

    /**
     * Sets the value of the numberOfPeriods property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfPeriods(BigInteger value) {
        this.numberOfPeriods = value;
    }

    /**
     * Gets the value of the prorateForWithdrawals property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isProrateForWithdrawals() {
        if (prorateForWithdrawals == null) {
            return false;
        } else {
            return prorateForWithdrawals;
        }
    }

    /**
     * Sets the value of the prorateForWithdrawals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProrateForWithdrawals(Boolean value) {
        this.prorateForWithdrawals = value;
    }

}

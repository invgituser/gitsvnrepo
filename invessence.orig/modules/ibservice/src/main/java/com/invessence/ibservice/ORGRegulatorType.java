
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ORGRegulatorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ORGRegulatorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegulatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegulatorCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegulatedInCapacity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegulatorId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ORGRegulatorType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "regulatorName",
    "regulatorCountry",
    "regulatedInCapacity",
    "regulatorId"
})
public class ORGRegulatorType {

    @XmlElement(name = "RegulatorName", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true, defaultValue = "")
    protected String regulatorName;
    @XmlElement(name = "RegulatorCountry", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true, defaultValue = "")
    protected String regulatorCountry;
    @XmlElement(name = "RegulatedInCapacity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true, defaultValue = "")
    protected String regulatedInCapacity;
    @XmlElement(name = "RegulatorId", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true, defaultValue = "")
    protected String regulatorId;

    /**
     * Gets the value of the regulatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegulatorName() {
        return regulatorName;
    }

    /**
     * Sets the value of the regulatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegulatorName(String value) {
        this.regulatorName = value;
    }

    /**
     * Gets the value of the regulatorCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegulatorCountry() {
        return regulatorCountry;
    }

    /**
     * Sets the value of the regulatorCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegulatorCountry(String value) {
        this.regulatorCountry = value;
    }

    /**
     * Gets the value of the regulatedInCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegulatedInCapacity() {
        return regulatedInCapacity;
    }

    /**
     * Sets the value of the regulatedInCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegulatedInCapacity(String value) {
        this.regulatedInCapacity = value;
    }

    /**
     * Gets the value of the regulatorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegulatorId() {
        return regulatorId;
    }

    /**
     * Sets the value of the regulatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegulatorId(String value) {
        this.regulatorId = value;
    }

}

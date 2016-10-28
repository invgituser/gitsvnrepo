
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Our five accredited investor questions. See a printed
 * 				application.
 * 			
 * 
 * <p>Java class for AccreditedInvestorInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccreditedInvestorInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="q1" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="q2" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="q3" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="q4" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="q5" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccreditedInvestorInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class AccreditedInvestorInformation {

    @XmlAttribute(name = "q1")
    protected Boolean q1;
    @XmlAttribute(name = "q2")
    protected Boolean q2;
    @XmlAttribute(name = "q3")
    protected Boolean q3;
    @XmlAttribute(name = "q4")
    protected Boolean q4;
    @XmlAttribute(name = "q5")
    protected Boolean q5;

    /**
     * Gets the value of the q1 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isQ1() {
        if (q1 == null) {
            return true;
        } else {
            return q1;
        }
    }

    /**
     * Sets the value of the q1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQ1(Boolean value) {
        this.q1 = value;
    }

    /**
     * Gets the value of the q2 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isQ2() {
        if (q2 == null) {
            return true;
        } else {
            return q2;
        }
    }

    /**
     * Sets the value of the q2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQ2(Boolean value) {
        this.q2 = value;
    }

    /**
     * Gets the value of the q3 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isQ3() {
        if (q3 == null) {
            return true;
        } else {
            return q3;
        }
    }

    /**
     * Sets the value of the q3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQ3(Boolean value) {
        this.q3 = value;
    }

    /**
     * Gets the value of the q4 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isQ4() {
        if (q4 == null) {
            return true;
        } else {
            return q4;
        }
    }

    /**
     * Sets the value of the q4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQ4(Boolean value) {
        this.q4 = value;
    }

    /**
     * Gets the value of the q5 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isQ5() {
        if (q5 == null) {
            return true;
        } else {
            return q5;
        }
    }

    /**
     * Sets the value of the q5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQ5(Boolean value) {
        this.q5 = value;
    }

}


package com.invessence.ibservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Specific markup in a commission schedule.
 * 			
 * 
 * <p>Java class for CommissionMarkupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommissionMarkupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stair" type="{http://www.interactivebrokers.com/schemas/IBCust_import}MarkupStaircaseType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="minimum" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="maximum" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CommissionMarkups_Type" />
 *       &lt;attribute name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="plus_cost" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommissionMarkupType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "stair"
})
public class CommissionMarkupType {

    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<MarkupStaircaseType> stair;
    @XmlAttribute(name = "code", required = true)
    protected String code;
    @XmlAttribute(name = "minimum")
    protected BigDecimal minimum;
    @XmlAttribute(name = "maximum")
    protected BigDecimal maximum;
    @XmlAttribute(name = "type", required = true)
    protected CommissionMarkupsType type;
    @XmlAttribute(name = "amount")
    protected BigDecimal amount;
    @XmlAttribute(name = "plus_cost")
    protected Boolean plusCost;

    /**
     * Gets the value of the stair property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stair property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStair().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarkupStaircaseType }
     * 
     * 
     */
    public List<MarkupStaircaseType> getStair() {
        if (stair == null) {
            stair = new ArrayList<MarkupStaircaseType>();
        }
        return this.stair;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the minimum property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinimum() {
        return minimum;
    }

    /**
     * Sets the value of the minimum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinimum(BigDecimal value) {
        this.minimum = value;
    }

    /**
     * Gets the value of the maximum property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximum() {
        return maximum;
    }

    /**
     * Sets the value of the maximum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximum(BigDecimal value) {
        this.maximum = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CommissionMarkupsType }
     *     
     */
    public CommissionMarkupsType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommissionMarkupsType }
     *     
     */
    public void setType(CommissionMarkupsType value) {
        this.type = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the plusCost property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPlusCost() {
        if (plusCost == null) {
            return false;
        } else {
            return plusCost;
        }
    }

    /**
     * Sets the value of the plusCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPlusCost(Boolean value) {
        this.plusCost = value;
    }

}

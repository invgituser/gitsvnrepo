
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Various limits for the llimit tracking mechanism.
 * 			
 * 
 * <p>Java class for TradingLimits complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TradingLimits">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderValueLimits" type="{http://www.interactivebrokers.com/schemas/IBCust_import}OrderValueLimits"/>
 *         &lt;element name="EFPQuantityLimits" type="{http://www.interactivebrokers.com/schemas/IBCust_import}EFPQuantityLimits"/>
 *         &lt;element name="OrderQuantityLimit" type="{http://www.interactivebrokers.com/schemas/IBCust_import}OrderQuantityLimit" maxOccurs="9"/>
 *         &lt;element name="DayQuantityLimit" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DayQuantityLimit" maxOccurs="9"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradingLimits", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "orderValueLimits",
    "efpQuantityLimits",
    "orderQuantityLimit",
    "dayQuantityLimit"
})
public class TradingLimits {

    @XmlElement(name = "OrderValueLimits", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected OrderValueLimits orderValueLimits;
    @XmlElement(name = "EFPQuantityLimits", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected EFPQuantityLimits efpQuantityLimits;
    @XmlElement(name = "OrderQuantityLimit", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<OrderQuantityLimit> orderQuantityLimit;
    @XmlElement(name = "DayQuantityLimit", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<DayQuantityLimit> dayQuantityLimit;

    /**
     * Gets the value of the orderValueLimits property.
     * 
     * @return
     *     possible object is
     *     {@link OrderValueLimits }
     *     
     */
    public OrderValueLimits getOrderValueLimits() {
        return orderValueLimits;
    }

    /**
     * Sets the value of the orderValueLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderValueLimits }
     *     
     */
    public void setOrderValueLimits(OrderValueLimits value) {
        this.orderValueLimits = value;
    }

    /**
     * Gets the value of the efpQuantityLimits property.
     * 
     * @return
     *     possible object is
     *     {@link EFPQuantityLimits }
     *     
     */
    public EFPQuantityLimits getEFPQuantityLimits() {
        return efpQuantityLimits;
    }

    /**
     * Sets the value of the efpQuantityLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link EFPQuantityLimits }
     *     
     */
    public void setEFPQuantityLimits(EFPQuantityLimits value) {
        this.efpQuantityLimits = value;
    }

    /**
     * Gets the value of the orderQuantityLimit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderQuantityLimit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderQuantityLimit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderQuantityLimit }
     * 
     * 
     */
    public List<OrderQuantityLimit> getOrderQuantityLimit() {
        if (orderQuantityLimit == null) {
            orderQuantityLimit = new ArrayList<OrderQuantityLimit>();
        }
        return this.orderQuantityLimit;
    }

    /**
     * Gets the value of the dayQuantityLimit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dayQuantityLimit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDayQuantityLimit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DayQuantityLimit }
     * 
     * 
     */
    public List<DayQuantityLimit> getDayQuantityLimit() {
        if (dayQuantityLimit == null) {
            dayQuantityLimit = new ArrayList<DayQuantityLimit>();
        }
        return this.dayQuantityLimit;
    }

}

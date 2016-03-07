
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Advisor Wrap Fee details for a client account.
 * 			
 * 
 * <p>Java class for AdvisorWrapFeesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdvisorWrapFeesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="automated_fees_details" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AutomatedWrapFeeDetailsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="highWaterMarkConfigHwma" type="{http://www.interactivebrokers.com/schemas/IBCust_import}HighWaterMarkType" minOccurs="0"/>
 *         &lt;element name="highWaterMarkConfigHwmq" type="{http://www.interactivebrokers.com/schemas/IBCust_import}HighWaterMarkType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="strategy" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AdvisorWrapFeeStrategies_Type" default="NO_FEES" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdvisorWrapFeesType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "automatedFeesDetails",
    "highWaterMarkConfigHwma",
    "highWaterMarkConfigHwmq"
})
public class AdvisorWrapFeesType {

    @XmlElement(name = "automated_fees_details", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<AutomatedWrapFeeDetailsType> automatedFeesDetails;
    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected HighWaterMarkType highWaterMarkConfigHwma;
    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected HighWaterMarkType highWaterMarkConfigHwmq;
    @XmlAttribute(name = "strategy")
    protected AdvisorWrapFeeStrategiesType strategy;

    /**
     * Gets the value of the automatedFeesDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the automatedFeesDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutomatedFeesDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AutomatedWrapFeeDetailsType }
     * 
     * 
     */
    public List<AutomatedWrapFeeDetailsType> getAutomatedFeesDetails() {
        if (automatedFeesDetails == null) {
            automatedFeesDetails = new ArrayList<AutomatedWrapFeeDetailsType>();
        }
        return this.automatedFeesDetails;
    }

    /**
     * Gets the value of the highWaterMarkConfigHwma property.
     * 
     * @return
     *     possible object is
     *     {@link HighWaterMarkType }
     *     
     */
    public HighWaterMarkType getHighWaterMarkConfigHwma() {
        return highWaterMarkConfigHwma;
    }

    /**
     * Sets the value of the highWaterMarkConfigHwma property.
     * 
     * @param value
     *     allowed object is
     *     {@link HighWaterMarkType }
     *     
     */
    public void setHighWaterMarkConfigHwma(HighWaterMarkType value) {
        this.highWaterMarkConfigHwma = value;
    }

    /**
     * Gets the value of the highWaterMarkConfigHwmq property.
     * 
     * @return
     *     possible object is
     *     {@link HighWaterMarkType }
     *     
     */
    public HighWaterMarkType getHighWaterMarkConfigHwmq() {
        return highWaterMarkConfigHwmq;
    }

    /**
     * Sets the value of the highWaterMarkConfigHwmq property.
     * 
     * @param value
     *     allowed object is
     *     {@link HighWaterMarkType }
     *     
     */
    public void setHighWaterMarkConfigHwmq(HighWaterMarkType value) {
        this.highWaterMarkConfigHwmq = value;
    }

    /**
     * Gets the value of the strategy property.
     * 
     * @return
     *     possible object is
     *     {@link AdvisorWrapFeeStrategiesType }
     *     
     */
    public AdvisorWrapFeeStrategiesType getStrategy() {
        if (strategy == null) {
            return AdvisorWrapFeeStrategiesType.NO_FEES;
        } else {
            return strategy;
        }
    }

    /**
     * Sets the value of the strategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdvisorWrapFeeStrategiesType }
     *     
     */
    public void setStrategy(AdvisorWrapFeeStrategiesType value) {
        this.strategy = value;
    }

}

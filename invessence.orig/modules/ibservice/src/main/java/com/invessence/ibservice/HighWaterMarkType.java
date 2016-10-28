
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				HighWaterMark Setup.
 * 			
 * 
 * <p>Java class for HighWaterMarkType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HighWaterMarkType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hwm" type="{http://www.interactivebrokers.com/schemas/IBCust_import}HighWaterMarkConfigurationType"/>
 *         &lt;element name="previousLosses" type="{http://www.interactivebrokers.com/schemas/IBCust_import}PreviousLossesType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HighWaterMarkType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "hwm",
    "previousLosses"
})
public class HighWaterMarkType {

    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected HighWaterMarkConfigurationType hwm;
    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<PreviousLossesType> previousLosses;

    /**
     * Gets the value of the hwm property.
     * 
     * @return
     *     possible object is
     *     {@link HighWaterMarkConfigurationType }
     *     
     */
    public HighWaterMarkConfigurationType getHwm() {
        return hwm;
    }

    /**
     * Sets the value of the hwm property.
     * 
     * @param value
     *     allowed object is
     *     {@link HighWaterMarkConfigurationType }
     *     
     */
    public void setHwm(HighWaterMarkConfigurationType value) {
        this.hwm = value;
    }

    /**
     * Gets the value of the previousLosses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the previousLosses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreviousLosses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreviousLossesType }
     * 
     * 
     */
    public List<PreviousLossesType> getPreviousLosses() {
        if (previousLosses == null) {
            previousLosses = new ArrayList<PreviousLossesType>();
        }
        return this.previousLosses;
    }

}


package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Schedule of interest rate markups.
 * 			
 * 
 * <p>Java class for InterestMarkupScheduleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InterestMarkupScheduleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="markup" type="{http://www.interactivebrokers.com/schemas/IBCust_import}InterestMarkupType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterestMarkupScheduleType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "markup"
})
public class InterestMarkupScheduleType {

    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<InterestMarkupType> markup;

    /**
     * Gets the value of the markup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the markup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarkup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InterestMarkupType }
     * 
     * 
     */
    public List<InterestMarkupType> getMarkup() {
        if (markup == null) {
            markup = new ArrayList<InterestMarkupType>();
        }
        return this.markup;
    }

}


package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				One or more regulated memberships.
 * 			
 * 
 * <p>Java class for RegulatedMemberships complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegulatedMemberships">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegulatedMembership" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatedMembership" maxOccurs="9"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegulatedMemberships", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "regulatedMembership"
})
public class RegulatedMemberships {

    @XmlElement(name = "RegulatedMembership", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<RegulatedMembership> regulatedMembership;

    /**
     * Gets the value of the regulatedMembership property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regulatedMembership property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegulatedMembership().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegulatedMembership }
     * 
     * 
     */
    public List<RegulatedMembership> getRegulatedMembership() {
        if (regulatedMembership == null) {
            regulatedMembership = new ArrayList<RegulatedMembership>();
        }
        return this.regulatedMembership;
    }

}

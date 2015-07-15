
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				List of agreements that are a part of the IB Customer
 * 				Application process that were executed by the customer on the
 * 				remote application which generated this Electronic Customer
 * 				Application.
 * 			
 * 
 * <p>Java class for Agreements complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Agreements">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Agreement" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Agreement" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Agreements", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "agreement"
})
public class Agreements {

    @XmlElement(name = "Agreement", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<Agreement> agreement;

    /**
     * Gets the value of the agreement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agreement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgreement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Agreement }
     * 
     * 
     */
    public List<Agreement> getAgreement() {
        if (agreement == null) {
            agreement = new ArrayList<Agreement>();
        }
        return this.agreement;
    }

}

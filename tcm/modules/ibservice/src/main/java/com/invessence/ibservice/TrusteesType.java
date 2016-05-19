
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				One or more individuals or legal entities that are the trustees
 * 				of the Trust.
 * 			
 * 
 * <p>Java class for TrusteesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrusteesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Individual" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Individual" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Entity" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TrusteeEntityType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrusteesType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "individual",
    "entity"
})
public class TrusteesType {

    @XmlElement(name = "Individual", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<Individual> individual;
    @XmlElement(name = "Entity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<TrusteeEntityType> entity;

    /**
     * Gets the value of the individual property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the individual property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndividual().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Individual }
     * 
     * 
     */
    public List<Individual> getIndividual() {
        if (individual == null) {
            individual = new ArrayList<Individual>();
        }
        return this.individual;
    }

    /**
     * Gets the value of the entity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrusteeEntityType }
     * 
     * 
     */
    public List<TrusteeEntityType> getEntity() {
        if (entity == null) {
            entity = new ArrayList<TrusteeEntityType>();
        }
        return this.entity;
    }

}

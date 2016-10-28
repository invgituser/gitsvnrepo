
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				One or more individuals or legal entities that are associated
 * 				with the customer.
 * 			
 * 
 * <p>Java class for AssociatedEntities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssociatedEntities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssociatedIndividual" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssociatedIndividual" maxOccurs="9"/>
 *         &lt;element name="AssociatedEntity" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssociatedEntity" maxOccurs="9" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssociatedEntities", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "associatedIndividual",
    "associatedEntity"
})
public class AssociatedEntities {

    @XmlElement(name = "AssociatedIndividual", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<AssociatedIndividual> associatedIndividual;
    @XmlElement(name = "AssociatedEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<AssociatedEntity> associatedEntity;

    /**
     * Gets the value of the associatedIndividual property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associatedIndividual property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedIndividual().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssociatedIndividual }
     * 
     * 
     */
    public List<AssociatedIndividual> getAssociatedIndividual() {
        if (associatedIndividual == null) {
            associatedIndividual = new ArrayList<AssociatedIndividual>();
        }
        return this.associatedIndividual;
    }

    /**
     * Gets the value of the associatedEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associatedEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssociatedEntity }
     * 
     * 
     */
    public List<AssociatedEntity> getAssociatedEntity() {
        if (associatedEntity == null) {
            associatedEntity = new ArrayList<AssociatedEntity>();
        }
        return this.associatedEntity;
    }

}


package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				One or more individuals or legal entities with a specific
 * 				association with the customer as defined by the declaring
 * 				element.
 * 			
 * 
 * <p>Java class for AssociationTypeEntities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssociationTypeEntities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Individual" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Individual" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LegalEntity" type="{http://www.interactivebrokers.com/schemas/IBCust_import}LegalEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssociationTypeEntities", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "individual",
    "legalEntity"
})
public class AssociationTypeEntities {

    @XmlElement(name = "Individual", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<Individual> individual;
    @XmlElement(name = "LegalEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<LegalEntity> legalEntity;

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
     * Gets the value of the legalEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the legalEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLegalEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LegalEntity }
     * 
     * 
     */
    public List<LegalEntity> getLegalEntity() {
        if (legalEntity == null) {
            legalEntity = new ArrayList<LegalEntity>();
        }
        return this.legalEntity;
    }

}

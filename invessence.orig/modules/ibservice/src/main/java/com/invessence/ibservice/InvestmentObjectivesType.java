
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Investment objectives of the customer.
 * 			
 * 
 * <p>Java class for InvestmentObjectivesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvestmentObjectivesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objective" type="{http://www.interactivebrokers.com/schemas/IBCust_import}InvestmentObjective_Type" maxOccurs="6"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvestmentObjectivesType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "objective"
})
public class InvestmentObjectivesType {

    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<InvestmentObjectiveType> objective;

    /**
     * Gets the value of the objective property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objective property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjective().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvestmentObjectiveType }
     * 
     * 
     */
    public List<InvestmentObjectiveType> getObjective() {
        if (objective == null) {
            objective = new ArrayList<InvestmentObjectiveType>();
        }
        return this.objective;
    }

}

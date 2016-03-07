
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				The individual who is the primary beneficiary of the IRA
 * 				account. Here the Ownership denotes the beneficiary's share.
 * 			
 * 
 * <p>Java class for IRAPrimaryBeneficiary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IRAPrimaryBeneficiary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.interactivebrokers.com/schemas/IBCust_import}Individual">
 *       &lt;sequence>
 *         &lt;element name="Ownership" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Ownership"/>
 *         &lt;element name="Title" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Title" minOccurs="0"/>
 *         &lt;element name="Relationship" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRABeneficiaryRelation_Type"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRAPrimaryBeneficiary", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "ownership",
    "title",
    "relationship"
})
public class IRAPrimaryBeneficiary
    extends Individual
{

    @XmlElement(name = "Ownership", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Ownership ownership;
    @XmlElement(name = "Title", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Title title;
    @XmlElement(name = "Relationship", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected IRABeneficiaryRelationType relationship;

    /**
     * Gets the value of the ownership property.
     * 
     * @return
     *     possible object is
     *     {@link Ownership }
     *     
     */
    public Ownership getOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ownership }
     *     
     */
    public void setOwnership(Ownership value) {
        this.ownership = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link Title }
     *     
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link Title }
     *     
     */
    public void setTitle(Title value) {
        this.title = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link IRABeneficiaryRelationType }
     *     
     */
    public IRABeneficiaryRelationType getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRABeneficiaryRelationType }
     *     
     */
    public void setRelationship(IRABeneficiaryRelationType value) {
        this.relationship = value;
    }

}

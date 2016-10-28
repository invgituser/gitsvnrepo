
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A legal entity which is associated with this customer and
 * 				qualification of the association.
 * 			
 * 
 * <p>Java class for AssociatedEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssociatedEntity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.interactivebrokers.com/schemas/IBCust_import}LegalEntity">
 *       &lt;sequence>
 *         &lt;element name="Ownership" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Ownership"/>
 *         &lt;element name="Title" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Title" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssociatedEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "ownership",
    "title"
})
public class AssociatedEntity
    extends LegalEntity
{

    @XmlElement(name = "Ownership", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Ownership ownership;
    @XmlElement(name = "Title", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Title title;

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

}

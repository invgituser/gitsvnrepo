
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Applications" type="{http://www.interactivebrokers.com/schemas/IBCust_import}NewApplications"/>
 *         &lt;element name="Actions" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Actions"/>
 *         &lt;element name="Additions" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Additions"/>
 *         &lt;element name="Modifications" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Modifications"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applications",
    "actions",
    "additions",
    "modifications"
})
@XmlRootElement(name = "Synchronization", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class Synchronization {

    @XmlElement(name = "Applications", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected NewApplications applications;
    @XmlElement(name = "Actions", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Actions actions;
    @XmlElement(name = "Additions", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Additions additions;
    @XmlElement(name = "Modifications", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Modifications modifications;

    /**
     * Gets the value of the applications property.
     * 
     * @return
     *     possible object is
     *     {@link NewApplications }
     *     
     */
    public NewApplications getApplications() {
        return applications;
    }

    /**
     * Sets the value of the applications property.
     * 
     * @param value
     *     allowed object is
     *     {@link NewApplications }
     *     
     */
    public void setApplications(NewApplications value) {
        this.applications = value;
    }

    /**
     * Gets the value of the actions property.
     * 
     * @return
     *     possible object is
     *     {@link Actions }
     *     
     */
    public Actions getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Actions }
     *     
     */
    public void setActions(Actions value) {
        this.actions = value;
    }

    /**
     * Gets the value of the additions property.
     * 
     * @return
     *     possible object is
     *     {@link Additions }
     *     
     */
    public Additions getAdditions() {
        return additions;
    }

    /**
     * Sets the value of the additions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Additions }
     *     
     */
    public void setAdditions(Additions value) {
        this.additions = value;
    }

    /**
     * Gets the value of the modifications property.
     * 
     * @return
     *     possible object is
     *     {@link Modifications }
     *     
     */
    public Modifications getModifications() {
        return modifications;
    }

    /**
     * Sets the value of the modifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link Modifications }
     *     
     */
    public void setModifications(Modifications value) {
        this.modifications = value;
    }

}

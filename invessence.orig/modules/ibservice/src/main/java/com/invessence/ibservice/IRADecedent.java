
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				The deceased individual from whom the IRA account is being
 * 				inherited. Required information for a decedent is the name, date
 * 				of birth, date of death, SSN and the inheritor type.
 * 			
 * 
 * <p>Java class for IRADecedent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IRADecedent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.interactivebrokers.com/schemas/IBCust_import}Individual">
 *       &lt;sequence>
 *         &lt;element name="DateOfDeath" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DOB"/>
 *         &lt;element name="Title" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Title"/>
 *         &lt;element name="InheritorType" type="{http://www.interactivebrokers.com/schemas/IBCust_import}InheritedIRABeneficiary_Type"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRADecedent", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "dateOfDeath",
    "title",
    "inheritorType"
})
public class IRADecedent
    extends Individual
{

    @XmlElement(name = "DateOfDeath", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected XMLGregorianCalendar dateOfDeath;
    @XmlElement(name = "Title", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Title title;
    @XmlElement(name = "InheritorType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected InheritedIRABeneficiaryType inheritorType;

    /**
     * Gets the value of the dateOfDeath property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfDeath() {
        return dateOfDeath;
    }

    /**
     * Sets the value of the dateOfDeath property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfDeath(XMLGregorianCalendar value) {
        this.dateOfDeath = value;
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
     * Gets the value of the inheritorType property.
     * 
     * @return
     *     possible object is
     *     {@link InheritedIRABeneficiaryType }
     *     
     */
    public InheritedIRABeneficiaryType getInheritorType() {
        return inheritorType;
    }

    /**
     * Sets the value of the inheritorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link InheritedIRABeneficiaryType }
     *     
     */
    public void setInheritorType(InheritedIRABeneficiaryType value) {
        this.inheritorType = value;
    }

}

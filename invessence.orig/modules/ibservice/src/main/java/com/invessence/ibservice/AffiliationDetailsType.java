
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Details of customer or customer household member affiliation to
 * 				a broker dealer.
 * 			
 * 
 * <p>Java class for AffiliationDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AffiliationDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="affiliation_relationship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="person_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="company_mailing_address" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address"/>
 *         &lt;element name="company_phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AffiliationDetailsType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "affiliationRelationship",
    "personName",
    "company",
    "companyMailingAddress",
    "companyPhone"
})
public class AffiliationDetailsType {

    @XmlElement(name = "affiliation_relationship", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String affiliationRelationship;
    @XmlElement(name = "person_name", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String personName;
    @XmlElement(namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String company;
    @XmlElement(name = "company_mailing_address", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Address companyMailingAddress;
    @XmlElement(name = "company_phone", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected String companyPhone;

    /**
     * Gets the value of the affiliationRelationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffiliationRelationship() {
        return affiliationRelationship;
    }

    /**
     * Sets the value of the affiliationRelationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffiliationRelationship(String value) {
        this.affiliationRelationship = value;
    }

    /**
     * Gets the value of the personName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Sets the value of the personName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonName(String value) {
        this.personName = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the companyMailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getCompanyMailingAddress() {
        return companyMailingAddress;
    }

    /**
     * Sets the value of the companyMailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setCompanyMailingAddress(Address value) {
        this.companyMailingAddress = value;
    }

    /**
     * Gets the value of the companyPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * Sets the value of the companyPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyPhone(String value) {
        this.companyPhone = value;
    }

}

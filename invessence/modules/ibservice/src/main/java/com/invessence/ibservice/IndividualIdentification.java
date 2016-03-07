
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				All possible forms of indentification for an individual. SSN is
 * 				required for US citizens and legal residents. For non-US
 * 				customers, at least one form of identification must be provided.
 * 			
 * 
 * <p>Java class for IndividualIdentification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndividualIdentification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="citizenship" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SSN" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DriversLicense" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Passport" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AlienCard" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NationalCard" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssuingCountry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LegalResidenceCountry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LegalResidenceState" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualIdentification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class IndividualIdentification {

    @XmlAttribute(name = "citizenship", required = true)
    protected String citizenship;
    @XmlAttribute(name = "SSN")
    protected String ssn;
    @XmlAttribute(name = "DriversLicense")
    protected String driversLicense;
    @XmlAttribute(name = "Passport")
    protected String passport;
    @XmlAttribute(name = "AlienCard")
    protected String alienCard;
    @XmlAttribute(name = "NationalCard")
    protected String nationalCard;
    @XmlAttribute(name = "IssuingCountry")
    protected String issuingCountry;
    @XmlAttribute(name = "LegalResidenceCountry")
    protected String legalResidenceCountry;
    @XmlAttribute(name = "LegalResidenceState")
    protected String legalResidenceState;

    /**
     * Gets the value of the citizenship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitizenship() {
        return citizenship;
    }

    /**
     * Sets the value of the citizenship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitizenship(String value) {
        this.citizenship = value;
    }

    /**
     * Gets the value of the ssn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSN() {
        return ssn;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSN(String value) {
        this.ssn = value;
    }

    /**
     * Gets the value of the driversLicense property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriversLicense() {
        return driversLicense;
    }

    /**
     * Sets the value of the driversLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriversLicense(String value) {
        this.driversLicense = value;
    }

    /**
     * Gets the value of the passport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Sets the value of the passport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassport(String value) {
        this.passport = value;
    }

    /**
     * Gets the value of the alienCard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlienCard() {
        return alienCard;
    }

    /**
     * Sets the value of the alienCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlienCard(String value) {
        this.alienCard = value;
    }

    /**
     * Gets the value of the nationalCard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalCard() {
        return nationalCard;
    }

    /**
     * Sets the value of the nationalCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalCard(String value) {
        this.nationalCard = value;
    }

    /**
     * Gets the value of the issuingCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuingCountry() {
        return issuingCountry;
    }

    /**
     * Sets the value of the issuingCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingCountry(String value) {
        this.issuingCountry = value;
    }

    /**
     * Gets the value of the legalResidenceCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalResidenceCountry() {
        return legalResidenceCountry;
    }

    /**
     * Sets the value of the legalResidenceCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalResidenceCountry(String value) {
        this.legalResidenceCountry = value;
    }

    /**
     * Gets the value of the legalResidenceState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalResidenceState() {
        return legalResidenceState;
    }

    /**
     * Sets the value of the legalResidenceState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalResidenceState(String value) {
        this.legalResidenceState = value;
    }

}

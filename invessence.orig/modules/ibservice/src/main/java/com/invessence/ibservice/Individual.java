
package com.invessence.ibservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				A general individual. Identification information is mandatory.
 * 			
 * 
 * <p>Java class for Individual complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Individual">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualName"/>
 *         &lt;element name="DOB" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DOB"/>
 *         &lt;element name="Gender" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Gender_Type" minOccurs="0"/>
 *         &lt;element name="MaritalStatus" type="{http://www.interactivebrokers.com/schemas/IBCust_import}MaritalStatus_Type" minOccurs="0"/>
 *         &lt;element name="NumDependents" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="Residence" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
 *         &lt;element name="MailingAddress" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Address" minOccurs="0"/>
 *         &lt;element name="Phones" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualPhones" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Email" minOccurs="0"/>
 *         &lt;element name="Identification" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IndividualIdentification" minOccurs="0"/>
 *         &lt;element name="EmploymentType" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Employment_Type" minOccurs="0"/>
 *         &lt;element name="EmploymentDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}EmploymentDetailsType" minOccurs="0"/>
 *         &lt;element name="EmployeeTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="same_mail_address" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Individual", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "name",
    "dob",
    "gender",
    "maritalStatus",
    "numDependents",
    "residence",
    "mailingAddress",
    "phones",
    "email",
    "identification",
    "employmentType",
    "employmentDetails",
    "employeeTitle"
})
@XmlSeeAlso({
    AssociatedIndividual.class,
    IRAPrimaryBeneficiary.class,
    IRAContingentBeneficiary.class,
    IRADecedent.class,
    ControllingOfficer.class,
    PrimaryTrader.class
})
public class Individual {

    @XmlElement(name = "Name", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected IndividualName name;
    @XmlElement(name = "DOB", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected XMLGregorianCalendar dob;
    @XmlElement(name = "Gender", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected GenderType gender;
    @XmlElement(name = "MaritalStatus", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected MaritalStatusType maritalStatus;
    @XmlElement(name = "NumDependents", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numDependents;
    @XmlElement(name = "Residence", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address residence;
    @XmlElement(name = "MailingAddress", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Address mailingAddress;
    @XmlElement(name = "Phones", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IndividualPhones phones;
    @XmlElement(name = "Email", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Email email;
    @XmlElement(name = "Identification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IndividualIdentification identification;
    @XmlElement(name = "EmploymentType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected EmploymentType employmentType;
    @XmlElement(name = "EmploymentDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected EmploymentDetailsType employmentDetails;
    @XmlElement(name = "EmployeeTitle", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected String employeeTitle;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "external_id", required = true)
    protected String externalId;
    @XmlAttribute(name = "same_mail_address")
    protected Boolean sameMailAddress;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualName }
     *     
     */
    public IndividualName getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualName }
     *     
     */
    public void setName(IndividualName value) {
        this.name = value;
    }

    /**
     * Gets the value of the dob property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDOB() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDOB(XMLGregorianCalendar value) {
        this.dob = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link GenderType }
     *     
     */
    public GenderType getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenderType }
     *     
     */
    public void setGender(GenderType value) {
        this.gender = value;
    }

    /**
     * Gets the value of the maritalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link MaritalStatusType }
     *     
     */
    public MaritalStatusType getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the value of the maritalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaritalStatusType }
     *     
     */
    public void setMaritalStatus(MaritalStatusType value) {
        this.maritalStatus = value;
    }

    /**
     * Gets the value of the numDependents property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumDependents() {
        return numDependents;
    }

    /**
     * Sets the value of the numDependents property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumDependents(BigInteger value) {
        this.numDependents = value;
    }

    /**
     * Gets the value of the residence property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getResidence() {
        return residence;
    }

    /**
     * Sets the value of the residence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setResidence(Address value) {
        this.residence = value;
    }

    /**
     * Gets the value of the mailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getMailingAddress() {
        return mailingAddress;
    }

    /**
     * Sets the value of the mailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setMailingAddress(Address value) {
        this.mailingAddress = value;
    }

    /**
     * Gets the value of the phones property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualPhones }
     *     
     */
    public IndividualPhones getPhones() {
        return phones;
    }

    /**
     * Sets the value of the phones property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualPhones }
     *     
     */
    public void setPhones(IndividualPhones value) {
        this.phones = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link Email }
     *     
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link Email }
     *     
     */
    public void setEmail(Email value) {
        this.email = value;
    }

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualIdentification }
     *     
     */
    public IndividualIdentification getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualIdentification }
     *     
     */
    public void setIdentification(IndividualIdentification value) {
        this.identification = value;
    }

    /**
     * Gets the value of the employmentType property.
     * 
     * @return
     *     possible object is
     *     {@link EmploymentType }
     *     
     */
    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    /**
     * Sets the value of the employmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmploymentType }
     *     
     */
    public void setEmploymentType(EmploymentType value) {
        this.employmentType = value;
    }

    /**
     * Gets the value of the employmentDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EmploymentDetailsType }
     *     
     */
    public EmploymentDetailsType getEmploymentDetails() {
        return employmentDetails;
    }

    /**
     * Sets the value of the employmentDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmploymentDetailsType }
     *     
     */
    public void setEmploymentDetails(EmploymentDetailsType value) {
        this.employmentDetails = value;
    }

    /**
     * Gets the value of the employeeTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeTitle() {
        return employeeTitle;
    }

    /**
     * Sets the value of the employeeTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeTitle(String value) {
        this.employeeTitle = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the sameMailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSameMailAddress() {
        if (sameMailAddress == null) {
            return true;
        } else {
            return sameMailAddress;
        }
    }

    /**
     * Sets the value of the sameMailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSameMailAddress(Boolean value) {
        this.sameMailAddress = value;
    }

}

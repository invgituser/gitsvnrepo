
package com.invessence.ibservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Request to duplicate customer's existing account at IB. Only
 * 				available for Advisor Client accounts. In addition to trading
 * 				configuration, Advisor Client Fees and Commission Schedules will
 * 				also be duplicated. the duplicate account will have a different
 * 				account id and will be accessible with the customer's existing
 * 				user session.
 * 			
 * 
 * <p>Java class for DuplicateAcctRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DuplicateAcctRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="reference_account_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="no_of_duplicates" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DuplicateAcctRequest", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class DuplicateAcctRequest {

    @XmlAttribute(name = "reference_account_id", required = true)
    protected String referenceAccountId;
    @XmlAttribute(name = "no_of_duplicates")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger noOfDuplicates;

    /**
     * Gets the value of the referenceAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceAccountId() {
        return referenceAccountId;
    }

    /**
     * Sets the value of the referenceAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceAccountId(String value) {
        this.referenceAccountId = value;
    }

    /**
     * Gets the value of the noOfDuplicates property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNoOfDuplicates() {
        return noOfDuplicates;
    }

    /**
     * Sets the value of the noOfDuplicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNoOfDuplicates(BigInteger value) {
        this.noOfDuplicates = value;
    }

}

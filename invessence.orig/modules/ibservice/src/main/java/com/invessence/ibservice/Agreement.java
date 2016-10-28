
package com.invessence.ibservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Details of execution of an agreement that is a part of the IB
 * 				Customer Application process that was executed by the customer
 * 				on the remote application which generated this Electronic
 * 				Customer Application.
 * 			
 * 
 * <p>Java class for Agreement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Agreement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="form_no" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="exec_login_ts" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="exec_ts" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="signature" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_acct_id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_individual_id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Agreement", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class Agreement {

    @XmlAttribute(name = "form_no", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger formNo;
    @XmlAttribute(name = "exec_login_ts", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger execLoginTs;
    @XmlAttribute(name = "exec_ts", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger execTs;
    @XmlAttribute(name = "signature")
    protected String signature;
    @XmlAttribute(name = "external_acct_id")
    protected String externalAcctId;
    @XmlAttribute(name = "external_individual_id")
    protected String externalIndividualId;

    /**
     * Gets the value of the formNo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFormNo() {
        return formNo;
    }

    /**
     * Sets the value of the formNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFormNo(BigInteger value) {
        this.formNo = value;
    }

    /**
     * Gets the value of the execLoginTs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExecLoginTs() {
        return execLoginTs;
    }

    /**
     * Sets the value of the execLoginTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExecLoginTs(BigInteger value) {
        this.execLoginTs = value;
    }

    /**
     * Gets the value of the execTs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExecTs() {
        return execTs;
    }

    /**
     * Sets the value of the execTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExecTs(BigInteger value) {
        this.execTs = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the value of the externalAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalAcctId() {
        return externalAcctId;
    }

    /**
     * Sets the value of the externalAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalAcctId(String value) {
        this.externalAcctId = value;
    }

    /**
     * Gets the value of the externalIndividualId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIndividualId() {
        return externalIndividualId;
    }

    /**
     * Sets the value of the externalIndividualId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIndividualId(String value) {
        this.externalIndividualId = value;
    }

}

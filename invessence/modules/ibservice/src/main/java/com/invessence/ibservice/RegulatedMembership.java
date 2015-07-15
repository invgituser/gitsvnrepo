
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				We must know of the membership in any regulated organizations.
 * 				E.g. an NYSE or NASD member.
 * 			
 * 
 * <p>Java class for RegulatedMembership complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegulatedMembership">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="organization_code" type="{http://www.w3.org/2001/XMLSchema}string" default="NASD" />
 *       &lt;attribute name="membership_id" type="{http://www.w3.org/2001/XMLSchema}string" default="IBKR" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegulatedMembership", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class RegulatedMembership {

    @XmlAttribute(name = "organization_code")
    protected String organizationCode;
    @XmlAttribute(name = "membership_id")
    protected String membershipId;

    /**
     * Gets the value of the organizationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationCode() {
        if (organizationCode == null) {
            return "NASD";
        } else {
            return organizationCode;
        }
    }

    /**
     * Sets the value of the organizationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationCode(String value) {
        this.organizationCode = value;
    }

    /**
     * Gets the value of the membershipId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipId() {
        if (membershipId == null) {
            return "IBKR";
        } else {
            return membershipId;
        }
    }

    /**
     * Sets the value of the membershipId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipId(String value) {
        this.membershipId = value;
    }

}

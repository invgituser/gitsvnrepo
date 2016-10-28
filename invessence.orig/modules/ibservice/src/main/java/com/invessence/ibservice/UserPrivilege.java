
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				A user is privileged for a specified account.
 * 			
 * 
 * <p>Java class for UserPrivilege complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserPrivilege">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="external_acct_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="privilege" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Privilege_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserPrivilege", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class UserPrivilege {

    @XmlAttribute(name = "external_acct_id", required = true)
    protected String externalAcctId;
    @XmlAttribute(name = "privilege", required = true)
    protected PrivilegeType privilege;

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
     * Gets the value of the privilege property.
     * 
     * @return
     *     possible object is
     *     {@link PrivilegeType }
     *     
     */
    public PrivilegeType getPrivilege() {
        return privilege;
    }

    /**
     * Sets the value of the privilege property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivilegeType }
     *     
     */
    public void setPrivilege(PrivilegeType value) {
        this.privilege = value;
    }

}

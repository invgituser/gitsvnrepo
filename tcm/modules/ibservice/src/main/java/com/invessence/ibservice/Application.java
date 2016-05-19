
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An application specifies a new customer and one or more
 * 				brokerage accounts and users.
 * 			
 * 
 * <p>Java class for Application complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Customer" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Customer"/>
 *         &lt;element name="Accounts" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Accounts"/>
 *         &lt;element name="Users" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Users"/>
 *         &lt;element name="Agreements" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Agreements" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "customer",
    "accounts",
    "users",
    "agreements"
})
public class Application {

    @XmlElement(name = "Customer", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Customer customer;
    @XmlElement(name = "Accounts", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Accounts accounts;
    @XmlElement(name = "Users", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Users users;
    @XmlElement(name = "Agreements", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Agreements agreements;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the accounts property.
     * 
     * @return
     *     possible object is
     *     {@link Accounts }
     *     
     */
    public Accounts getAccounts() {
        return accounts;
    }

    /**
     * Sets the value of the accounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accounts }
     *     
     */
    public void setAccounts(Accounts value) {
        this.accounts = value;
    }

    /**
     * Gets the value of the users property.
     * 
     * @return
     *     possible object is
     *     {@link Users }
     *     
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Sets the value of the users property.
     * 
     * @param value
     *     allowed object is
     *     {@link Users }
     *     
     */
    public void setUsers(Users value) {
        this.users = value;
    }

    /**
     * Gets the value of the agreements property.
     * 
     * @return
     *     possible object is
     *     {@link Agreements }
     *     
     */
    public Agreements getAgreements() {
        return agreements;
    }

    /**
     * Sets the value of the agreements property.
     * 
     * @param value
     *     allowed object is
     *     {@link Agreements }
     *     
     */
    public void setAgreements(Agreements value) {
        this.agreements = value;
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

}

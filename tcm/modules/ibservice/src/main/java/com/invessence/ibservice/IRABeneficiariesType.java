
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				One or more individuals or legal entities who are the primary
 * 				and contingent beneficiaries of the IRA account. At least one
 * 				primary beneficiary, individual or legal entity, must be
 * 				provided. Net ownership of primary and contingent beneficiaries
 * 				must add up to 100% for each beneficary type.
 * 			
 * 
 * <p>Java class for IRABeneficiariesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IRABeneficiariesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrimaryBeneficiary" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRAPrimaryBeneficiary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PrimaryBeneficiaryEntity" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRAPrimaryBeneficiaryEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ContingentBeneficiary" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRAContingentBeneficiary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ContingentBeneficiaryEntity" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRAContingentBeneficiaryEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="spouse_primary_beneficary" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRABeneficiariesType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "primaryBeneficiary",
    "primaryBeneficiaryEntity",
    "contingentBeneficiary",
    "contingentBeneficiaryEntity"
})
public class IRABeneficiariesType {

    @XmlElement(name = "PrimaryBeneficiary", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<IRAPrimaryBeneficiary> primaryBeneficiary;
    @XmlElement(name = "PrimaryBeneficiaryEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<IRAPrimaryBeneficiaryEntity> primaryBeneficiaryEntity;
    @XmlElement(name = "ContingentBeneficiary", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<IRAContingentBeneficiary> contingentBeneficiary;
    @XmlElement(name = "ContingentBeneficiaryEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<IRAContingentBeneficiaryEntity> contingentBeneficiaryEntity;
    @XmlAttribute(name = "spouse_primary_beneficary")
    protected Boolean spousePrimaryBeneficary;

    /**
     * Gets the value of the primaryBeneficiary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the primaryBeneficiary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrimaryBeneficiary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IRAPrimaryBeneficiary }
     * 
     * 
     */
    public List<IRAPrimaryBeneficiary> getPrimaryBeneficiary() {
        if (primaryBeneficiary == null) {
            primaryBeneficiary = new ArrayList<IRAPrimaryBeneficiary>();
        }
        return this.primaryBeneficiary;
    }

    /**
     * Gets the value of the primaryBeneficiaryEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the primaryBeneficiaryEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrimaryBeneficiaryEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IRAPrimaryBeneficiaryEntity }
     * 
     * 
     */
    public List<IRAPrimaryBeneficiaryEntity> getPrimaryBeneficiaryEntity() {
        if (primaryBeneficiaryEntity == null) {
            primaryBeneficiaryEntity = new ArrayList<IRAPrimaryBeneficiaryEntity>();
        }
        return this.primaryBeneficiaryEntity;
    }

    /**
     * Gets the value of the contingentBeneficiary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contingentBeneficiary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContingentBeneficiary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IRAContingentBeneficiary }
     * 
     * 
     */
    public List<IRAContingentBeneficiary> getContingentBeneficiary() {
        if (contingentBeneficiary == null) {
            contingentBeneficiary = new ArrayList<IRAContingentBeneficiary>();
        }
        return this.contingentBeneficiary;
    }

    /**
     * Gets the value of the contingentBeneficiaryEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contingentBeneficiaryEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContingentBeneficiaryEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IRAContingentBeneficiaryEntity }
     * 
     * 
     */
    public List<IRAContingentBeneficiaryEntity> getContingentBeneficiaryEntity() {
        if (contingentBeneficiaryEntity == null) {
            contingentBeneficiaryEntity = new ArrayList<IRAContingentBeneficiaryEntity>();
        }
        return this.contingentBeneficiaryEntity;
    }

    /**
     * Gets the value of the spousePrimaryBeneficary property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSpousePrimaryBeneficary() {
        return spousePrimaryBeneficary;
    }

    /**
     * Sets the value of the spousePrimaryBeneficary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSpousePrimaryBeneficary(Boolean value) {
        this.spousePrimaryBeneficary = value;
    }

}


package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				All information which is required for compliance reasons. The
 * 				attribute names are codes for the various types of organizations
 * 				that the customer must declare.
 * 			
 * 
 * <p>Java class for RegulatoryInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegulatoryInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="RegulatoryDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatoryDetail" maxOccurs="unbounded"/>
 *           &lt;element name="RegulatoryDetail" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RegulatoryDetail" maxOccurs="unbounded"/>
 *         &lt;/choice>
 *         &lt;element name="SelfRegulatedMembership" type="{http://www.interactivebrokers.com/schemas/IBCust_import}SelfRegulatedMembershipType" minOccurs="0"/>
 *         &lt;element name="AffiliationDetails" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AffiliationDetailsType" minOccurs="0"/>
 *         &lt;element name="FinancialOrgType" type="{http://www.interactivebrokers.com/schemas/IBCust_import}FinancialOrgType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ORGRegulatoryInfo" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ORGRegulatoryInfoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegulatoryInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "regulatoryDetails",
    "regulatoryDetail",
    "selfRegulatedMembership",
    "affiliationDetails",
    "financialOrgType",
    "orgRegulatoryInfo"
})
public class RegulatoryInformation {

    @XmlElement(name = "RegulatoryDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<RegulatoryDetail> regulatoryDetails;
    @XmlElement(name = "RegulatoryDetail", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<RegulatoryDetail> regulatoryDetail;
    @XmlElement(name = "SelfRegulatedMembership", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected SelfRegulatedMembershipType selfRegulatedMembership;
    @XmlElement(name = "AffiliationDetails", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AffiliationDetailsType affiliationDetails;
    @XmlElement(name = "FinancialOrgType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<FinancialOrgType> financialOrgType;
    @XmlElement(name = "ORGRegulatoryInfo", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected ORGRegulatoryInfoType orgRegulatoryInfo;

    /**
     * Gets the value of the regulatoryDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regulatoryDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegulatoryDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegulatoryDetail }
     * 
     * 
     */
    public List<RegulatoryDetail> getRegulatoryDetails() {
        if (regulatoryDetails == null) {
            regulatoryDetails = new ArrayList<RegulatoryDetail>();
        }
        return this.regulatoryDetails;
    }

    /**
     * Gets the value of the regulatoryDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regulatoryDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegulatoryDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegulatoryDetail }
     * 
     * 
     */
    public List<RegulatoryDetail> getRegulatoryDetail() {
        if (regulatoryDetail == null) {
            regulatoryDetail = new ArrayList<RegulatoryDetail>();
        }
        return this.regulatoryDetail;
    }

    /**
     * Gets the value of the selfRegulatedMembership property.
     * 
     * @return
     *     possible object is
     *     {@link SelfRegulatedMembershipType }
     *     
     */
    public SelfRegulatedMembershipType getSelfRegulatedMembership() {
        return selfRegulatedMembership;
    }

    /**
     * Sets the value of the selfRegulatedMembership property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelfRegulatedMembershipType }
     *     
     */
    public void setSelfRegulatedMembership(SelfRegulatedMembershipType value) {
        this.selfRegulatedMembership = value;
    }

    /**
     * Gets the value of the affiliationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AffiliationDetailsType }
     *     
     */
    public AffiliationDetailsType getAffiliationDetails() {
        return affiliationDetails;
    }

    /**
     * Sets the value of the affiliationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AffiliationDetailsType }
     *     
     */
    public void setAffiliationDetails(AffiliationDetailsType value) {
        this.affiliationDetails = value;
    }

    /**
     * Gets the value of the financialOrgType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the financialOrgType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFinancialOrgType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FinancialOrgType }
     * 
     * 
     */
    public List<FinancialOrgType> getFinancialOrgType() {
        if (financialOrgType == null) {
            financialOrgType = new ArrayList<FinancialOrgType>();
        }
        return this.financialOrgType;
    }

    /**
     * Gets the value of the orgRegulatoryInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ORGRegulatoryInfoType }
     *     
     */
    public ORGRegulatoryInfoType getORGRegulatoryInfo() {
        return orgRegulatoryInfo;
    }

    /**
     * Sets the value of the orgRegulatoryInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ORGRegulatoryInfoType }
     *     
     */
    public void setORGRegulatoryInfo(ORGRegulatoryInfoType value) {
        this.orgRegulatoryInfo = value;
    }

}

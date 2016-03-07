
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ORGRegulatoryInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ORGRegulatoryInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PublicCompanyInfo" type="{http://www.interactivebrokers.com/schemas/IBCust_import}PublicCompanyInfoType" minOccurs="0"/>
 *         &lt;element name="ORGRegulator" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ORGRegulatorType" maxOccurs="3" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="is_public" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="is_regulated" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ORGRegulatoryInfoType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "publicCompanyInfo",
    "orgRegulator"
})
public class ORGRegulatoryInfoType {

    @XmlElement(name = "PublicCompanyInfo", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected PublicCompanyInfoType publicCompanyInfo;
    @XmlElement(name = "ORGRegulator", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<ORGRegulatorType> orgRegulator;
    @XmlAttribute(name = "is_public")
    protected Boolean isPublic;
    @XmlAttribute(name = "is_regulated")
    protected Boolean isRegulated;

    /**
     * Gets the value of the publicCompanyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PublicCompanyInfoType }
     *     
     */
    public PublicCompanyInfoType getPublicCompanyInfo() {
        return publicCompanyInfo;
    }

    /**
     * Sets the value of the publicCompanyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicCompanyInfoType }
     *     
     */
    public void setPublicCompanyInfo(PublicCompanyInfoType value) {
        this.publicCompanyInfo = value;
    }

    /**
     * Gets the value of the orgRegulator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orgRegulator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getORGRegulator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ORGRegulatorType }
     * 
     * 
     */
    public List<ORGRegulatorType> getORGRegulator() {
        if (orgRegulator == null) {
            orgRegulator = new ArrayList<ORGRegulatorType>();
        }
        return this.orgRegulator;
    }

    /**
     * Gets the value of the isPublic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsPublic() {
        if (isPublic == null) {
            return false;
        } else {
            return isPublic;
        }
    }

    /**
     * Sets the value of the isPublic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPublic(Boolean value) {
        this.isPublic = value;
    }

    /**
     * Gets the value of the isRegulated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRegulated() {
        if (isRegulated == null) {
            return false;
        } else {
            return isRegulated;
        }
    }

    /**
     * Sets the value of the isRegulated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRegulated(Boolean value) {
        this.isRegulated = value;
    }

}

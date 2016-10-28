
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Required information for completing a Form W-8BEN for the
 * 				customer certifying Foreign Status of Beneficial Owner for US
 * 				tax withholding. Address details will be defaulted from the
 * 				application.
 * 			
 * 
 * <p>Java class for FormW8BEN complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormW8BEN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ben_type" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}W8Ben_Type" />
 *       &lt;attribute name="tin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tin_type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TIN_Type" />
 *       &lt;attribute name="foreign_tax_id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="part_2_9a" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="part_2_9a_country" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="part_2_9b" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="part_2_9c" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="part_2_9d" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="part_2_9e" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormW8BEN", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class FormW8BEN {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "ben_type", required = true)
    protected W8BenType benType;
    @XmlAttribute(name = "tin")
    protected String tin;
    @XmlAttribute(name = "tin_type")
    protected TINType tinType;
    @XmlAttribute(name = "foreign_tax_id")
    protected String foreignTaxId;
    @XmlAttribute(name = "part_2_9a")
    protected Boolean part29A;
    @XmlAttribute(name = "part_2_9a_country")
    protected String part29ACountry;
    @XmlAttribute(name = "part_2_9b")
    protected Boolean part29B;
    @XmlAttribute(name = "part_2_9c")
    protected Boolean part29C;
    @XmlAttribute(name = "part_2_9d")
    protected Boolean part29D;
    @XmlAttribute(name = "part_2_9e")
    protected Boolean part29E;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the benType property.
     * 
     * @return
     *     possible object is
     *     {@link W8BenType }
     *     
     */
    public W8BenType getBenType() {
        return benType;
    }

    /**
     * Sets the value of the benType property.
     * 
     * @param value
     *     allowed object is
     *     {@link W8BenType }
     *     
     */
    public void setBenType(W8BenType value) {
        this.benType = value;
    }

    /**
     * Gets the value of the tin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTin() {
        return tin;
    }

    /**
     * Sets the value of the tin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTin(String value) {
        this.tin = value;
    }

    /**
     * Gets the value of the tinType property.
     * 
     * @return
     *     possible object is
     *     {@link TINType }
     *     
     */
    public TINType getTinType() {
        return tinType;
    }

    /**
     * Sets the value of the tinType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TINType }
     *     
     */
    public void setTinType(TINType value) {
        this.tinType = value;
    }

    /**
     * Gets the value of the foreignTaxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeignTaxId() {
        return foreignTaxId;
    }

    /**
     * Sets the value of the foreignTaxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeignTaxId(String value) {
        this.foreignTaxId = value;
    }

    /**
     * Gets the value of the part29A property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPart29A() {
        return part29A;
    }

    /**
     * Sets the value of the part29A property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPart29A(Boolean value) {
        this.part29A = value;
    }

    /**
     * Gets the value of the part29ACountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPart29ACountry() {
        return part29ACountry;
    }

    /**
     * Sets the value of the part29ACountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPart29ACountry(String value) {
        this.part29ACountry = value;
    }

    /**
     * Gets the value of the part29B property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPart29B() {
        return part29B;
    }

    /**
     * Sets the value of the part29B property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPart29B(Boolean value) {
        this.part29B = value;
    }

    /**
     * Gets the value of the part29C property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPart29C() {
        return part29C;
    }

    /**
     * Sets the value of the part29C property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPart29C(Boolean value) {
        this.part29C = value;
    }

    /**
     * Gets the value of the part29D property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPart29D() {
        return part29D;
    }

    /**
     * Sets the value of the part29D property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPart29D(Boolean value) {
        this.part29D = value;
    }

    /**
     * Gets the value of the part29E property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPart29E() {
        return part29E;
    }

    /**
     * Sets the value of the part29E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPart29E(Boolean value) {
        this.part29E = value;
    }

}

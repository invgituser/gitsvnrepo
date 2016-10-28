
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				The Legal Entity that is the contingent beneficiary of the IRA
 * 				account. Here the Ownership denotes the beneficiary's share.
 * 			
 * 
 * <p>Java class for IRAContingentBeneficiaryEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IRAContingentBeneficiaryEntity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.interactivebrokers.com/schemas/IBCust_import}SimpleLegalEntityType">
 *       &lt;sequence>
 *         &lt;element name="Ownership" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Ownership"/>
 *         &lt;element name="Title" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Title" minOccurs="0"/>
 *         &lt;element name="Relationship" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRABeneficiaryRelation_Type"/>
 *         &lt;element name="Executor" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Individual" minOccurs="0"/>
 *         &lt;element name="ExecutionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ArticleOfWill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRAContingentBeneficiaryEntity", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "ownership",
    "title",
    "relationship",
    "executor",
    "executionDate",
    "articleOfWill"
})
public class IRAContingentBeneficiaryEntity
    extends SimpleLegalEntityType
{

    @XmlElement(name = "Ownership", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected Ownership ownership;
    @XmlElement(name = "Title", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Title title;
    @XmlElement(name = "Relationship", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected IRABeneficiaryRelationType relationship;
    @XmlElement(name = "Executor", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Individual executor;
    @XmlElement(name = "ExecutionDate", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar executionDate;
    @XmlElement(name = "ArticleOfWill", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected String articleOfWill;

    /**
     * Gets the value of the ownership property.
     * 
     * @return
     *     possible object is
     *     {@link Ownership }
     *     
     */
    public Ownership getOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ownership }
     *     
     */
    public void setOwnership(Ownership value) {
        this.ownership = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link Title }
     *     
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link Title }
     *     
     */
    public void setTitle(Title value) {
        this.title = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link IRABeneficiaryRelationType }
     *     
     */
    public IRABeneficiaryRelationType getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRABeneficiaryRelationType }
     *     
     */
    public void setRelationship(IRABeneficiaryRelationType value) {
        this.relationship = value;
    }

    /**
     * Gets the value of the executor property.
     * 
     * @return
     *     possible object is
     *     {@link Individual }
     *     
     */
    public Individual getExecutor() {
        return executor;
    }

    /**
     * Sets the value of the executor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Individual }
     *     
     */
    public void setExecutor(Individual value) {
        this.executor = value;
    }

    /**
     * Gets the value of the executionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the value of the executionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecutionDate(XMLGregorianCalendar value) {
        this.executionDate = value;
    }

    /**
     * Gets the value of the articleOfWill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleOfWill() {
        return articleOfWill;
    }

    /**
     * Sets the value of the articleOfWill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleOfWill(String value) {
        this.articleOfWill = value;
    }

}

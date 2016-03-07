
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				The customer's experience and knowledge with the various
 * 				financial instruments required to qualify trading in those
 * 				instruments.
 * 			
 * 
 * <p>Java class for InvestmentExperience complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvestmentExperience">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetExperience" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AssetExperience" maxOccurs="5"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvestmentExperience", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "assetExperience"
})
public class InvestmentExperience {

    @XmlElement(name = "AssetExperience", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<AssetExperience> assetExperience;

    /**
     * Gets the value of the assetExperience property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetExperience property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetExperience().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetExperience }
     * 
     * 
     */
    public List<AssetExperience> getAssetExperience() {
        if (assetExperience == null) {
            assetExperience = new ArrayList<AssetExperience>();
        }
        return this.assetExperience;
    }

}

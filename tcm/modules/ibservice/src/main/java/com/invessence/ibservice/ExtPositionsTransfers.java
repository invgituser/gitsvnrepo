
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExtPositionsTransfer" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ExtPositionsTransferType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "extPositionsTransfer"
})
@XmlRootElement(name = "ExtPositionsTransfers", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class ExtPositionsTransfers {

    @XmlElement(name = "ExtPositionsTransfer", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<ExtPositionsTransferType> extPositionsTransfer;

    /**
     * Gets the value of the extPositionsTransfer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extPositionsTransfer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtPositionsTransfer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtPositionsTransferType }
     * 
     * 
     */
    public List<ExtPositionsTransferType> getExtPositionsTransfer() {
        if (extPositionsTransfer == null) {
            extPositionsTransfer = new ArrayList<ExtPositionsTransferType>();
        }
        return this.extPositionsTransfer;
    }

}

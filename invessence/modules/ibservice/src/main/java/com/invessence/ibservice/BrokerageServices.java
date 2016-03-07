
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				One or more brokerage services.
 * 			
 * 
 * <p>Java class for BrokerageServices complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BrokerageServices">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrokerageService" type="{http://www.interactivebrokers.com/schemas/IBCust_import}BrokerageService" maxOccurs="3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrokerageServices", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "brokerageService"
})
public class BrokerageServices {

    @XmlElement(name = "BrokerageService", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<BrokerageService> brokerageService;

    /**
     * Gets the value of the brokerageService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the brokerageService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrokerageService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BrokerageService }
     * 
     * 
     */
    public List<BrokerageService> getBrokerageService() {
        if (brokerageService == null) {
            brokerageService = new ArrayList<BrokerageService>();
        }
        return this.brokerageService;
    }

}

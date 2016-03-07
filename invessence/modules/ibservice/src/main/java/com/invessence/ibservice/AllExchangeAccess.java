
package com.invessence.ibservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				If a trading permission bundle would provide improper exchange
 * 				access, then specify exactly a set of exchanges.
 * 			
 * 
 * <p>Java class for AllExchangeAccess complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllExchangeAccess">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExchangeAccess" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ExchangeAccess" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllExchangeAccess", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "exchangeAccess"
})
public class AllExchangeAccess {

    @XmlElement(name = "ExchangeAccess", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected List<ExchangeAccess> exchangeAccess;

    /**
     * Gets the value of the exchangeAccess property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exchangeAccess property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExchangeAccess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExchangeAccess }
     * 
     * 
     */
    public List<ExchangeAccess> getExchangeAccess() {
        if (exchangeAccess == null) {
            exchangeAccess = new ArrayList<ExchangeAccess>();
        }
        return this.exchangeAccess;
    }

}

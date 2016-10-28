
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
 *         &lt;element name="DuplicateAcct" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DuplicateAcctRequest" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DepositNotification" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DepositNotificationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FullyPaidSLBRequests" type="{http://www.interactivebrokers.com/schemas/IBCust_import}FullyPaidSLBRequestType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RecurringTransaction" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RecurringTransactionType" maxOccurs="unbounded" minOccurs="0"/>
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
    "duplicateAcct",
    "depositNotification",
    "fullyPaidSLBRequests",
    "recurringTransaction"
})
@XmlRootElement(name = "AcctMgmtRequests", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
public class AcctMgmtRequests {

    @XmlElement(name = "DuplicateAcct", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<DuplicateAcctRequest> duplicateAcct;
    @XmlElement(name = "DepositNotification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<DepositNotificationType> depositNotification;
    @XmlElement(name = "FullyPaidSLBRequests", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<FullyPaidSLBRequestType> fullyPaidSLBRequests;
    @XmlElement(name = "RecurringTransaction", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<RecurringTransactionType> recurringTransaction;

    /**
     * Gets the value of the duplicateAcct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the duplicateAcct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDuplicateAcct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DuplicateAcctRequest }
     * 
     * 
     */
    public List<DuplicateAcctRequest> getDuplicateAcct() {
        if (duplicateAcct == null) {
            duplicateAcct = new ArrayList<DuplicateAcctRequest>();
        }
        return this.duplicateAcct;
    }

    /**
     * Gets the value of the depositNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the depositNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepositNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepositNotificationType }
     * 
     * 
     */
    public List<DepositNotificationType> getDepositNotification() {
        if (depositNotification == null) {
            depositNotification = new ArrayList<DepositNotificationType>();
        }
        return this.depositNotification;
    }

    /**
     * Gets the value of the fullyPaidSLBRequests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fullyPaidSLBRequests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFullyPaidSLBRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FullyPaidSLBRequestType }
     * 
     * 
     */
    public List<FullyPaidSLBRequestType> getFullyPaidSLBRequests() {
        if (fullyPaidSLBRequests == null) {
            fullyPaidSLBRequests = new ArrayList<FullyPaidSLBRequestType>();
        }
        return this.fullyPaidSLBRequests;
    }

    /**
     * Gets the value of the recurringTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recurringTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecurringTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecurringTransactionType }
     * 
     * 
     */
    public List<RecurringTransactionType> getRecurringTransaction() {
        if (recurringTransaction == null) {
            recurringTransaction = new ArrayList<RecurringTransactionType>();
        }
        return this.recurringTransaction;
    }

}

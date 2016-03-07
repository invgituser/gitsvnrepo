
package com.invessence.ibservice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An account is the brokerage account which provides trading
 * 				access, funds, and positions.
 * 			
 * 
 * <p>Java class for Account complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Account">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvestmentObjectives" type="{http://www.interactivebrokers.com/schemas/IBCust_import}InvestmentObjectivesType" minOccurs="0"/>
 *         &lt;element name="BrokerageServices" type="{http://www.interactivebrokers.com/schemas/IBCust_import}BrokerageServices" minOccurs="0"/>
 *         &lt;element name="Capabilities" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Capabilities" minOccurs="0"/>
 *         &lt;element name="TradingPermissions" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TradingPermissions" minOccurs="0"/>
 *         &lt;element name="AllExchangeAccess" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AllExchangeAccess" minOccurs="0"/>
 *         &lt;element name="DVPInstructions" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DVPInstructions" minOccurs="0"/>
 *         &lt;element name="TradingLimits" type="{http://www.interactivebrokers.com/schemas/IBCust_import}TradingLimits" minOccurs="0"/>
 *         &lt;element name="AdvisorWrapFees" type="{http://www.interactivebrokers.com/schemas/IBCust_import}AdvisorWrapFeesType" minOccurs="0"/>
 *         &lt;element name="ClientCommissionSchedule" type="{http://www.interactivebrokers.com/schemas/IBCust_import}CommissionScheduleType" minOccurs="0"/>
 *         &lt;element name="ClientInterestMarkupSchedule" type="{http://www.interactivebrokers.com/schemas/IBCust_import}InterestMarkupScheduleType" minOccurs="0"/>
 *         &lt;element name="Decendent" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRADecedent" minOccurs="0"/>
 *         &lt;element name="IRABeneficiaries" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRABeneficiariesType" minOccurs="0"/>
 *         &lt;element name="ExtPositionsTransfer" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ExtPositionsTransferType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DepositNotification" type="{http://www.interactivebrokers.com/schemas/IBCust_import}DepositNotificationType" minOccurs="0"/>
 *         &lt;element name="ACHInstruction" type="{http://www.interactivebrokers.com/schemas/IBCust_import}ACHInstructionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RecurringTransaction" type="{http://www.interactivebrokers.com/schemas/IBCust_import}RecurringTransactionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="external_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="base_currency" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Currency_Type" />
 *       &lt;attribute name="multicurrency" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="margin" use="required" type="{http://www.interactivebrokers.com/schemas/IBCust_import}Margin_Type" />
 *       &lt;attribute name="IRA" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="IRA_type" type="{http://www.interactivebrokers.com/schemas/IBCust_import}IRA_Type" />
 *       &lt;attribute name="IRA_official_title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="duplicate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="no_of_duplicates" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "investmentObjectives",
    "brokerageServices",
    "capabilities",
    "tradingPermissions",
    "allExchangeAccess",
    "dvpInstructions",
    "tradingLimits",
    "advisorWrapFees",
    "clientCommissionSchedule",
    "clientInterestMarkupSchedule",
    "decendent",
    "iraBeneficiaries",
    "extPositionsTransfer",
    "depositNotification",
    "achInstruction",
    "recurringTransaction"
})
public class Account {

    @XmlElement(name = "InvestmentObjectives", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected InvestmentObjectivesType investmentObjectives;
    @XmlElement(name = "BrokerageServices", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected BrokerageServices brokerageServices;
    @XmlElement(name = "Capabilities", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected Capabilities capabilities;
    @XmlElement(name = "TradingPermissions", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected TradingPermissions tradingPermissions;
    @XmlElement(name = "AllExchangeAccess", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AllExchangeAccess allExchangeAccess;
    @XmlElement(name = "DVPInstructions", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected DVPInstructions dvpInstructions;
    @XmlElement(name = "TradingLimits", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected TradingLimits tradingLimits;
    @XmlElement(name = "AdvisorWrapFees", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected AdvisorWrapFeesType advisorWrapFees;
    @XmlElement(name = "ClientCommissionSchedule", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected CommissionScheduleType clientCommissionSchedule;
    @XmlElement(name = "ClientInterestMarkupSchedule", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected InterestMarkupScheduleType clientInterestMarkupSchedule;
    @XmlElement(name = "Decendent", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IRADecedent decendent;
    @XmlElement(name = "IRABeneficiaries", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected IRABeneficiariesType iraBeneficiaries;
    @XmlElement(name = "ExtPositionsTransfer", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<ExtPositionsTransferType> extPositionsTransfer;
    @XmlElement(name = "DepositNotification", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected DepositNotificationType depositNotification;
    @XmlElement(name = "ACHInstruction", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<ACHInstructionType> achInstruction;
    @XmlElement(name = "RecurringTransaction", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected List<RecurringTransactionType> recurringTransaction;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "external_id", required = true)
    protected String externalId;
    @XmlAttribute(name = "base_currency", required = true)
    protected CurrencyType baseCurrency;
    @XmlAttribute(name = "multicurrency")
    protected Boolean multicurrency;
    @XmlAttribute(name = "margin", required = true)
    protected MarginType margin;
    @XmlAttribute(name = "IRA")
    protected Boolean ira;
    @XmlAttribute(name = "IRA_type")
    protected IRAType iraType;
    @XmlAttribute(name = "IRA_official_title")
    protected String iraOfficialTitle;
    @XmlAttribute(name = "duplicate")
    protected Boolean duplicate;
    @XmlAttribute(name = "no_of_duplicates")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger noOfDuplicates;

    /**
     * Gets the value of the investmentObjectives property.
     * 
     * @return
     *     possible object is
     *     {@link InvestmentObjectivesType }
     *     
     */
    public InvestmentObjectivesType getInvestmentObjectives() {
        return investmentObjectives;
    }

    /**
     * Sets the value of the investmentObjectives property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvestmentObjectivesType }
     *     
     */
    public void setInvestmentObjectives(InvestmentObjectivesType value) {
        this.investmentObjectives = value;
    }

    /**
     * Gets the value of the brokerageServices property.
     * 
     * @return
     *     possible object is
     *     {@link BrokerageServices }
     *     
     */
    public BrokerageServices getBrokerageServices() {
        return brokerageServices;
    }

    /**
     * Sets the value of the brokerageServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrokerageServices }
     *     
     */
    public void setBrokerageServices(BrokerageServices value) {
        this.brokerageServices = value;
    }

    /**
     * Gets the value of the capabilities property.
     * 
     * @return
     *     possible object is
     *     {@link Capabilities }
     *     
     */
    public Capabilities getCapabilities() {
        return capabilities;
    }

    /**
     * Sets the value of the capabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Capabilities }
     *     
     */
    public void setCapabilities(Capabilities value) {
        this.capabilities = value;
    }

    /**
     * Gets the value of the tradingPermissions property.
     * 
     * @return
     *     possible object is
     *     {@link TradingPermissions }
     *     
     */
    public TradingPermissions getTradingPermissions() {
        return tradingPermissions;
    }

    /**
     * Sets the value of the tradingPermissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link TradingPermissions }
     *     
     */
    public void setTradingPermissions(TradingPermissions value) {
        this.tradingPermissions = value;
    }

    /**
     * Gets the value of the allExchangeAccess property.
     * 
     * @return
     *     possible object is
     *     {@link AllExchangeAccess }
     *     
     */
    public AllExchangeAccess getAllExchangeAccess() {
        return allExchangeAccess;
    }

    /**
     * Sets the value of the allExchangeAccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllExchangeAccess }
     *     
     */
    public void setAllExchangeAccess(AllExchangeAccess value) {
        this.allExchangeAccess = value;
    }

    /**
     * Gets the value of the dvpInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link DVPInstructions }
     *     
     */
    public DVPInstructions getDVPInstructions() {
        return dvpInstructions;
    }

    /**
     * Sets the value of the dvpInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DVPInstructions }
     *     
     */
    public void setDVPInstructions(DVPInstructions value) {
        this.dvpInstructions = value;
    }

    /**
     * Gets the value of the tradingLimits property.
     * 
     * @return
     *     possible object is
     *     {@link TradingLimits }
     *     
     */
    public TradingLimits getTradingLimits() {
        return tradingLimits;
    }

    /**
     * Sets the value of the tradingLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link TradingLimits }
     *     
     */
    public void setTradingLimits(TradingLimits value) {
        this.tradingLimits = value;
    }

    /**
     * Gets the value of the advisorWrapFees property.
     * 
     * @return
     *     possible object is
     *     {@link AdvisorWrapFeesType }
     *     
     */
    public AdvisorWrapFeesType getAdvisorWrapFees() {
        return advisorWrapFees;
    }

    /**
     * Sets the value of the advisorWrapFees property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdvisorWrapFeesType }
     *     
     */
    public void setAdvisorWrapFees(AdvisorWrapFeesType value) {
        this.advisorWrapFees = value;
    }

    /**
     * Gets the value of the clientCommissionSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link CommissionScheduleType }
     *     
     */
    public CommissionScheduleType getClientCommissionSchedule() {
        return clientCommissionSchedule;
    }

    /**
     * Sets the value of the clientCommissionSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommissionScheduleType }
     *     
     */
    public void setClientCommissionSchedule(CommissionScheduleType value) {
        this.clientCommissionSchedule = value;
    }

    /**
     * Gets the value of the clientInterestMarkupSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link InterestMarkupScheduleType }
     *     
     */
    public InterestMarkupScheduleType getClientInterestMarkupSchedule() {
        return clientInterestMarkupSchedule;
    }

    /**
     * Sets the value of the clientInterestMarkupSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link InterestMarkupScheduleType }
     *     
     */
    public void setClientInterestMarkupSchedule(InterestMarkupScheduleType value) {
        this.clientInterestMarkupSchedule = value;
    }

    /**
     * Gets the value of the decendent property.
     * 
     * @return
     *     possible object is
     *     {@link IRADecedent }
     *     
     */
    public IRADecedent getDecendent() {
        return decendent;
    }

    /**
     * Sets the value of the decendent property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRADecedent }
     *     
     */
    public void setDecendent(IRADecedent value) {
        this.decendent = value;
    }

    /**
     * Gets the value of the iraBeneficiaries property.
     * 
     * @return
     *     possible object is
     *     {@link IRABeneficiariesType }
     *     
     */
    public IRABeneficiariesType getIRABeneficiaries() {
        return iraBeneficiaries;
    }

    /**
     * Sets the value of the iraBeneficiaries property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRABeneficiariesType }
     *     
     */
    public void setIRABeneficiaries(IRABeneficiariesType value) {
        this.iraBeneficiaries = value;
    }

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

    /**
     * Gets the value of the depositNotification property.
     * 
     * @return
     *     possible object is
     *     {@link DepositNotificationType }
     *     
     */
    public DepositNotificationType getDepositNotification() {
        return depositNotification;
    }

    /**
     * Sets the value of the depositNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepositNotificationType }
     *     
     */
    public void setDepositNotification(DepositNotificationType value) {
        this.depositNotification = value;
    }

    /**
     * Gets the value of the achInstruction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the achInstruction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACHInstruction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACHInstructionType }
     * 
     * 
     */
    public List<ACHInstructionType> getACHInstruction() {
        if (achInstruction == null) {
            achInstruction = new ArrayList<ACHInstructionType>();
        }
        return this.achInstruction;
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

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the baseCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyType }
     *     
     */
    public CurrencyType getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Sets the value of the baseCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyType }
     *     
     */
    public void setBaseCurrency(CurrencyType value) {
        this.baseCurrency = value;
    }

    /**
     * Gets the value of the multicurrency property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMulticurrency() {
        if (multicurrency == null) {
            return true;
        } else {
            return multicurrency;
        }
    }

    /**
     * Sets the value of the multicurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMulticurrency(Boolean value) {
        this.multicurrency = value;
    }

    /**
     * Gets the value of the margin property.
     * 
     * @return
     *     possible object is
     *     {@link MarginType }
     *     
     */
    public MarginType getMargin() {
        return margin;
    }

    /**
     * Sets the value of the margin property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarginType }
     *     
     */
    public void setMargin(MarginType value) {
        this.margin = value;
    }

    /**
     * Gets the value of the ira property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIRA() {
        if (ira == null) {
            return false;
        } else {
            return ira;
        }
    }

    /**
     * Sets the value of the ira property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIRA(Boolean value) {
        this.ira = value;
    }

    /**
     * Gets the value of the iraType property.
     * 
     * @return
     *     possible object is
     *     {@link IRAType }
     *     
     */
    public IRAType getIRAType() {
        return iraType;
    }

    /**
     * Sets the value of the iraType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IRAType }
     *     
     */
    public void setIRAType(IRAType value) {
        this.iraType = value;
    }

    /**
     * Gets the value of the iraOfficialTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIRAOfficialTitle() {
        return iraOfficialTitle;
    }

    /**
     * Sets the value of the iraOfficialTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIRAOfficialTitle(String value) {
        this.iraOfficialTitle = value;
    }

    /**
     * Gets the value of the duplicate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDuplicate() {
        return duplicate;
    }

    /**
     * Sets the value of the duplicate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDuplicate(Boolean value) {
        this.duplicate = value;
    }

    /**
     * Gets the value of the noOfDuplicates property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNoOfDuplicates() {
        return noOfDuplicates;
    }

    /**
     * Sets the value of the noOfDuplicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNoOfDuplicates(BigInteger value) {
        this.noOfDuplicates = value;
    }

}


package com.invessence.ibservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Financial information about the customer required to qualify
 * 				them for trading and brokerage services. All amount values must
 * 				be in USD.
 * 			
 * 
 * <p>Java class for FinancialInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FinancialInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvestmentExperience" type="{http://www.interactivebrokers.com/schemas/IBCust_import}InvestmentExperience"/>
 *         &lt;element name="InvestmentObjectives" type="{http://www.interactivebrokers.com/schemas/IBCust_import}InvestmentObjectivesType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="net_worth" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="liquid_net_worth" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="annual_net_income" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="total_assets" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="source_of_funds" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FinancialInformation", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", propOrder = {
    "investmentExperience",
    "investmentObjectives"
})
public class FinancialInformation {

    @XmlElement(name = "InvestmentExperience", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import", required = true)
    protected InvestmentExperience investmentExperience;
    @XmlElement(name = "InvestmentObjectives", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
    protected InvestmentObjectivesType investmentObjectives;
    @XmlAttribute(name = "net_worth", required = true)
    protected BigDecimal netWorth;
    @XmlAttribute(name = "liquid_net_worth", required = true)
    protected BigDecimal liquidNetWorth;
    @XmlAttribute(name = "annual_net_income", required = true)
    protected BigDecimal annualNetIncome;
    @XmlAttribute(name = "total_assets", required = true)
    protected BigDecimal totalAssets;
    @XmlAttribute(name = "source_of_funds")
    protected String sourceOfFunds;

    /**
     * Gets the value of the investmentExperience property.
     * 
     * @return
     *     possible object is
     *     {@link InvestmentExperience }
     *     
     */
    public InvestmentExperience getInvestmentExperience() {
        return investmentExperience;
    }

    /**
     * Sets the value of the investmentExperience property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvestmentExperience }
     *     
     */
    public void setInvestmentExperience(InvestmentExperience value) {
        this.investmentExperience = value;
    }

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
     * Gets the value of the netWorth property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetWorth() {
        return netWorth;
    }

    /**
     * Sets the value of the netWorth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetWorth(BigDecimal value) {
        this.netWorth = value;
    }

    /**
     * Gets the value of the liquidNetWorth property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLiquidNetWorth() {
        return liquidNetWorth;
    }

    /**
     * Sets the value of the liquidNetWorth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLiquidNetWorth(BigDecimal value) {
        this.liquidNetWorth = value;
    }

    /**
     * Gets the value of the annualNetIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAnnualNetIncome() {
        return annualNetIncome;
    }

    /**
     * Sets the value of the annualNetIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAnnualNetIncome(BigDecimal value) {
        this.annualNetIncome = value;
    }

    /**
     * Gets the value of the totalAssets property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    /**
     * Sets the value of the totalAssets property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAssets(BigDecimal value) {
        this.totalAssets = value;
    }

    /**
     * Gets the value of the sourceOfFunds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceOfFunds() {
        return sourceOfFunds;
    }

    /**
     * Sets the value of the sourceOfFunds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceOfFunds(String value) {
        this.sourceOfFunds = value;
    }

}

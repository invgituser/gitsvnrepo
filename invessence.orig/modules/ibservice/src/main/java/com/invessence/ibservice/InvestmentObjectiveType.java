
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvestmentObjective_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InvestmentObjective_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Trading"/>
 *     &lt;enumeration value="Growth"/>
 *     &lt;enumeration value="Speculation"/>
 *     &lt;enumeration value="Hedging"/>
 *     &lt;enumeration value="Preservation"/>
 *     &lt;enumeration value="Income"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvestmentObjective_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum InvestmentObjectiveType {

    @XmlEnumValue("Trading")
    TRADING("Trading"),
    @XmlEnumValue("Growth")
    GROWTH("Growth"),
    @XmlEnumValue("Speculation")
    SPECULATION("Speculation"),
    @XmlEnumValue("Hedging")
    HEDGING("Hedging"),
    @XmlEnumValue("Preservation")
    PRESERVATION("Preservation"),
    @XmlEnumValue("Income")
    INCOME("Income");
    private final String value;

    InvestmentObjectiveType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvestmentObjectiveType fromValue(String v) {
        for (InvestmentObjectiveType c: InvestmentObjectiveType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

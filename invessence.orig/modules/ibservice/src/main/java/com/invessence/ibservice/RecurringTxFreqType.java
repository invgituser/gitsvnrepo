
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecurringTxFreq_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RecurringTxFreq_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="MONTHLY"/>
 *     &lt;enumeration value="QUARTERLY"/>
 *     &lt;enumeration value="YEARLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RecurringTxFreq_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum RecurringTxFreqType {

    MONTHLY,
    QUARTERLY,
    YEARLY;

    public String value() {
        return name();
    }

    public static RecurringTxFreqType fromValue(String v) {
        return valueOf(v);
    }

}

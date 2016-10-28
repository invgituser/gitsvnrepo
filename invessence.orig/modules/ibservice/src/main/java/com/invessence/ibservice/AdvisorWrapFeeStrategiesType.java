
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdvisorWrapFeeStrategies_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AdvisorWrapFeeStrategies_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AUTOMATED"/>
 *     &lt;enumeration value="DIRECTBILLING"/>
 *     &lt;enumeration value="NO_FEES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AdvisorWrapFeeStrategies_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum AdvisorWrapFeeStrategiesType {

    AUTOMATED,
    DIRECTBILLING,
    NO_FEES;

    public String value() {
        return name();
    }

    public static AdvisorWrapFeeStrategiesType fromValue(String v) {
        return valueOf(v);
    }

}

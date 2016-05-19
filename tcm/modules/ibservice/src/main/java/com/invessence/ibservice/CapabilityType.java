
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Capability_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Capability_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="BOND"/>
 *     &lt;enumeration value="FOP"/>
 *     &lt;enumeration value="FUND"/>
 *     &lt;enumeration value="FUT"/>
 *     &lt;enumeration value="MULT"/>
 *     &lt;enumeration value="OPT"/>
 *     &lt;enumeration value="SSF"/>
 *     &lt;enumeration value="STK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Capability_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum CapabilityType {

    BOND,
    FOP,
    FUND,
    FUT,
    MULT,
    OPT,
    SSF,
    STK;

    public String value() {
        return name();
    }

    public static CapabilityType fromValue(String v) {
        return valueOf(v);
    }

}

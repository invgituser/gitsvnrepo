
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetClass_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AssetClass_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="BILL"/>
 *     &lt;enumeration value="BOND"/>
 *     &lt;enumeration value="CASH"/>
 *     &lt;enumeration value="CFD"/>
 *     &lt;enumeration value="COMB"/>
 *     &lt;enumeration value="FOP"/>
 *     &lt;enumeration value="FUND"/>
 *     &lt;enumeration value="FUT"/>
 *     &lt;enumeration value="OPT"/>
 *     &lt;enumeration value="SSF"/>
 *     &lt;enumeration value="STK"/>
 *     &lt;enumeration value="WAR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AssetClass_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum AssetClassType {

    BILL,
    BOND,
    CASH,
    CFD,
    COMB,
    FOP,
    FUND,
    FUT,
    OPT,
    SSF,
    STK,
    WAR;

    public String value() {
        return name();
    }

    public static AssetClassType fromValue(String v) {
        return valueOf(v);
    }

}

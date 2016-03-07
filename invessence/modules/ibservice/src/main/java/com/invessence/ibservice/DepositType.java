
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Deposit_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Deposit_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CHECK"/>
 *     &lt;enumeration value="WIRE"/>
 *     &lt;enumeration value="ACH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Deposit_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum DepositType {

    CHECK,
    WIRE,
    ACH;

    public String value() {
        return name();
    }

    public static DepositType fromValue(String v) {
        return valueOf(v);
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommissionMarkups_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommissionMarkups_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="FA"/>
 *     &lt;enumeration value="FM"/>
 *     &lt;enumeration value="PM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommissionMarkups_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum CommissionMarkupsType {

    FA,
    FM,
    PM;

    public String value() {
        return name();
    }

    public static CommissionMarkupsType fromValue(String v) {
        return valueOf(v);
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Privilege_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Privilege_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="OWNER"/>
 *     &lt;enumeration value="TRADER"/>
 *     &lt;enumeration value="CUSTOM"/>
 *     &lt;enumeration value="NONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Privilege_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum PrivilegeType {

    OWNER,
    TRADER,
    CUSTOM,
    NONE;

    public String value() {
        return name();
    }

    public static PrivilegeType fromValue(String v) {
        return valueOf(v);
    }

}

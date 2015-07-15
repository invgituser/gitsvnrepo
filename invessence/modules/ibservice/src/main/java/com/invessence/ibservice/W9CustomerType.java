
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for W9Customer_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="W9Customer_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Individual"/>
 *     &lt;enumeration value="Corporation"/>
 *     &lt;enumeration value="Partnership"/>
 *     &lt;enumeration value="LLC"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "W9Customer_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum W9CustomerType {

    @XmlEnumValue("Individual")
    INDIVIDUAL("Individual"),
    @XmlEnumValue("Corporation")
    CORPORATION("Corporation"),
    @XmlEnumValue("Partnership")
    PARTNERSHIP("Partnership"),
    LLC("LLC"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    W9CustomerType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static W9CustomerType fromValue(String v) {
        for (W9CustomerType c: W9CustomerType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

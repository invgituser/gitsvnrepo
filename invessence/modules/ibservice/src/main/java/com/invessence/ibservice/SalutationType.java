
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Salutation_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Salutation_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Mr."/>
 *     &lt;enumeration value="Mrs."/>
 *     &lt;enumeration value="Ms."/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Salutation_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum SalutationType {

    @XmlEnumValue("Mr.")
    MR("Mr."),
    @XmlEnumValue("Mrs.")
    MRS("Mrs."),
    @XmlEnumValue("Ms.")
    MS("Ms.");
    private final String value;

    SalutationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SalutationType fromValue(String v) {
        for (SalutationType c: SalutationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

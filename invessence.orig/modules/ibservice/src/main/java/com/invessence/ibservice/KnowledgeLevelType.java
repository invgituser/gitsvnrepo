
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KnowledgeLevel_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="KnowledgeLevel_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Extensive"/>
 *     &lt;enumeration value="Good"/>
 *     &lt;enumeration value="Limited"/>
 *     &lt;enumeration value="None"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "KnowledgeLevel_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum KnowledgeLevelType {

    @XmlEnumValue("Extensive")
    EXTENSIVE("Extensive"),
    @XmlEnumValue("Good")
    GOOD("Good"),
    @XmlEnumValue("Limited")
    LIMITED("Limited"),
    @XmlEnumValue("None")
    NONE("None");
    private final String value;

    KnowledgeLevelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KnowledgeLevelType fromValue(String v) {
        for (KnowledgeLevelType c: KnowledgeLevelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

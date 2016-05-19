
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Joint_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Joint_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="community"/>
 *     &lt;enumeration value="joint_tenants"/>
 *     &lt;enumeration value="tenants_common"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Joint_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum JointType {

    @XmlEnumValue("community")
    COMMUNITY("community"),
    @XmlEnumValue("joint_tenants")
    JOINT_TENANTS("joint_tenants"),
    @XmlEnumValue("tenants_common")
    TENANTS_COMMON("tenants_common");
    private final String value;

    JointType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static JointType fromValue(String v) {
        for (JointType c: JointType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

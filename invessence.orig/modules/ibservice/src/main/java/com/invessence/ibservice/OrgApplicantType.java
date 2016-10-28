
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrgApplicant_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrgApplicant_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="LLC"/>
 *     &lt;enumeration value="CORPORATION"/>
 *     &lt;enumeration value="PARTNERSHIP"/>
 *     &lt;enumeration value="UNINCORPORATED BUSINESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrgApplicant_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum OrgApplicantType {

    LLC("LLC"),
    CORPORATION("CORPORATION"),
    PARTNERSHIP("PARTNERSHIP"),
    @XmlEnumValue("UNINCORPORATED BUSINESS")
    UNINCORPORATED_BUSINESS("UNINCORPORATED BUSINESS");
    private final String value;

    OrgApplicantType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrgApplicantType fromValue(String v) {
        for (OrgApplicantType c: OrgApplicantType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

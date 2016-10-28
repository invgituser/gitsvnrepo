
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for W8Ben_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="W8Ben_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Individual"/>
 *     &lt;enumeration value="Corporation"/>
 *     &lt;enumeration value="Partnership"/>
 *     &lt;enumeration value="Disregarded_entity"/>
 *     &lt;enumeration value="Simple_trust"/>
 *     &lt;enumeration value="Complex_trust"/>
 *     &lt;enumeration value="Estate"/>
 *     &lt;enumeration value="Government"/>
 *     &lt;enumeration value="International_org"/>
 *     &lt;enumeration value="Central_bank_of_issue"/>
 *     &lt;enumeration value="Tax_exempt_org"/>
 *     &lt;enumeration value="Private_foundation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "W8Ben_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum W8BenType {

    @XmlEnumValue("Individual")
    INDIVIDUAL("Individual"),
    @XmlEnumValue("Corporation")
    CORPORATION("Corporation"),
    @XmlEnumValue("Partnership")
    PARTNERSHIP("Partnership"),
    @XmlEnumValue("Disregarded_entity")
    DISREGARDED_ENTITY("Disregarded_entity"),
    @XmlEnumValue("Simple_trust")
    SIMPLE_TRUST("Simple_trust"),
    @XmlEnumValue("Complex_trust")
    COMPLEX_TRUST("Complex_trust"),
    @XmlEnumValue("Estate")
    ESTATE("Estate"),
    @XmlEnumValue("Government")
    GOVERNMENT("Government"),
    @XmlEnumValue("International_org")
    INTERNATIONAL_ORG("International_org"),
    @XmlEnumValue("Central_bank_of_issue")
    CENTRAL_BANK_OF_ISSUE("Central_bank_of_issue"),
    @XmlEnumValue("Tax_exempt_org")
    TAX_EXEMPT_ORG("Tax_exempt_org"),
    @XmlEnumValue("Private_foundation")
    PRIVATE_FOUNDATION("Private_foundation");
    private final String value;

    W8BenType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static W8BenType fromValue(String v) {
        for (W8BenType c: W8BenType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

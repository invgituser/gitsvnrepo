
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IRAContribTaxYear_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IRAContribTaxYear_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="current"/>
 *     &lt;enumeration value="prior"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IRAContribTaxYear_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum IRAContribTaxYearType {

    @XmlEnumValue("current")
    CURRENT("current"),
    @XmlEnumValue("prior")
    PRIOR("prior");
    private final String value;

    IRAContribTaxYearType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IRAContribTaxYearType fromValue(String v) {
        for (IRAContribTaxYearType c: IRAContribTaxYearType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

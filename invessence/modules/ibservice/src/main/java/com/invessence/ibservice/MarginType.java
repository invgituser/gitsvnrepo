
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Margin_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Margin_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="RegT"/>
 *     &lt;enumeration value="PortfolioMargin"/>
 *     &lt;enumeration value="GPMargin"/>
 *     &lt;enumeration value="Cash"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Margin_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum MarginType {

    @XmlEnumValue("RegT")
    REG_T("RegT"),
    @XmlEnumValue("PortfolioMargin")
    PORTFOLIO_MARGIN("PortfolioMargin"),
    @XmlEnumValue("GPMargin")
    GP_MARGIN("GPMargin"),
    @XmlEnumValue("Cash")
    CASH("Cash");
    private final String value;

    MarginType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MarginType fromValue(String v) {
        for (MarginType c: MarginType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExchangeCode_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExchangeCode_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NYSE"/>
 *     &lt;enumeration value="AMEX"/>
 *     &lt;enumeration value="NASDAQ"/>
 *     &lt;enumeration value="CBOE"/>
 *     &lt;enumeration value="ISE"/>
 *     &lt;enumeration value="BOX"/>
 *     &lt;enumeration value="AMEX"/>
 *     &lt;enumeration value="PHLX"/>
 *     &lt;enumeration value="PSE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ExchangeCode_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum ExchangeCodeType {

    NYSE,
    AMEX,
    NASDAQ,
    CBOE,
    ISE,
    BOX,
    PHLX,
    PSE;

    public String value() {
        return name();
    }

    public static ExchangeCodeType fromValue(String v) {
        return valueOf(v);
    }

}

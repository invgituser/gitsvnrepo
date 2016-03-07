
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Currency_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Currency_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="USD"/>
 *     &lt;enumeration value="EUR"/>
 *     &lt;enumeration value="GBP"/>
 *     &lt;enumeration value="CAD"/>
 *     &lt;enumeration value="JPY"/>
 *     &lt;enumeration value="HKD"/>
 *     &lt;enumeration value="AUD"/>
 *     &lt;enumeration value="CHF"/>
 *     &lt;enumeration value="MXN"/>
 *     &lt;enumeration value="SEK"/>
 *     &lt;enumeration value="NZD"/>
 *     &lt;enumeration value="HUF"/>
 *     &lt;enumeration value="CZK"/>
 *     &lt;enumeration value="CNH"/>
 *     &lt;enumeration value="DKK"/>
 *     &lt;enumeration value="RUB"/>
 *     &lt;enumeration value="ILS"/>
 *     &lt;enumeration value="NOK"/>
 *     &lt;enumeration value="SGD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Currency_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum CurrencyType {

    USD,
    EUR,
    GBP,
    CAD,
    JPY,
    HKD,
    AUD,
    CHF,
    MXN,
    SEK,
    NZD,
    HUF,
    CZK,
    CNH,
    DKK,
    RUB,
    ILS,
    NOK,
    SGD;

    public String value() {
        return name();
    }

    public static CurrencyType fromValue(String v) {
        return valueOf(v);
    }

}

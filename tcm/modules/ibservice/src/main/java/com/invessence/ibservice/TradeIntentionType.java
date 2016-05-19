
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TradeIntention_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TradeIntention_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="FINANCIALINSTITUTION"/>
 *     &lt;enumeration value="PROPRIETARYTRADING"/>
 *     &lt;enumeration value="FAMILYINVVEHICLE"/>
 *     &lt;enumeration value="OPERATINGBUSINESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TradeIntention_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum TradeIntentionType {

    FINANCIALINSTITUTION,
    PROPRIETARYTRADING,
    FAMILYINVVEHICLE,
    OPERATINGBUSINESS;

    public String value() {
        return name();
    }

    public static TradeIntentionType fromValue(String v) {
        return valueOf(v);
    }

}

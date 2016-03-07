
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TXGroupCode_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TXGroupCode_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="G"/>
 *     &lt;enumeration value="Z"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="N"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TXGroupCode_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum TXGroupCodeType {

    G,
    Z,
    R,
    N;

    public String value() {
        return name();
    }

    public static TXGroupCodeType fromValue(String v) {
        return valueOf(v);
    }

}

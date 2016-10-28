
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PositionsTransfer_SubType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PositionsTransfer_SubType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ACATS"/>
 *     &lt;enumeration value="ATON"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PositionsTransfer_SubType", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum PositionsTransferSubType {

    ACATS,
    ATON;

    public String value() {
        return name();
    }

    public static PositionsTransferSubType fromValue(String v) {
        return valueOf(v);
    }

}

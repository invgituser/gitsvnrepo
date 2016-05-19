
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DVP_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DVP_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="DTCID"/>
 *     &lt;enumeration value="NSCC"/>
 *     &lt;enumeration value="CMTA"/>
 *     &lt;enumeration value="GUS"/>
 *     &lt;enumeration value="OCCSSF"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DVP_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum DVPType {

    DTCID,
    NSCC,
    CMTA,
    GUS,
    OCCSSF;

    public String value() {
        return name();
    }

    public static DVPType fromValue(String v) {
        return valueOf(v);
    }

}

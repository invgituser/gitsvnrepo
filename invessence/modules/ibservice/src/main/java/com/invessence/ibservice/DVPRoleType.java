
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DVPRole_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DVPRole_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="B"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DVPRole_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum DVPRoleType {

    E,
    C,
    B;

    public String value() {
        return name();
    }

    public static DVPRoleType fromValue(String v) {
        return valueOf(v);
    }

}

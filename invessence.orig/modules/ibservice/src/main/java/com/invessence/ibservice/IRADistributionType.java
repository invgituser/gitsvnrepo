
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IRADistribution_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IRADistribution_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NORMAL"/>
 *     &lt;enumeration value="EARLY"/>
 *     &lt;enumeration value="EARLY_EXCEPT"/>
 *     &lt;enumeration value="DEATH"/>
 *     &lt;enumeration value="DISABILITY"/>
 *     &lt;enumeration value="EXCESS_CONTRIB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IRADistribution_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum IRADistributionType {

    NORMAL,
    EARLY,
    EARLY_EXCEPT,
    DEATH,
    DISABILITY,
    EXCESS_CONTRIB;

    public String value() {
        return name();
    }

    public static IRADistributionType fromValue(String v) {
        return valueOf(v);
    }

}

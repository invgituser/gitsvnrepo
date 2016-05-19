
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BrokerageService_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BrokerageService_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="IBClearing"/>
 *     &lt;enumeration value="IBExecution"/>
 *     &lt;enumeration value="IBPrime"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BrokerageService_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum BrokerageServiceType {

    @XmlEnumValue("IBClearing")
    IB_CLEARING("IBClearing"),
    @XmlEnumValue("IBExecution")
    IB_EXECUTION("IBExecution"),
    @XmlEnumValue("IBPrime")
    IB_PRIME("IBPrime");
    private final String value;

    BrokerageServiceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BrokerageServiceType fromValue(String v) {
        for (BrokerageServiceType c: BrokerageServiceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

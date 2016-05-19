
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IRADeposit_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IRADeposit_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="contribution"/>
 *     &lt;enumeration value="rollover"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IRADeposit_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum IRADepositType {

    @XmlEnumValue("contribution")
    CONTRIBUTION("contribution"),
    @XmlEnumValue("rollover")
    ROLLOVER("rollover");
    private final String value;

    IRADepositType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IRADepositType fromValue(String v) {
        for (IRADepositType c: IRADepositType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

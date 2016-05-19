
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AutomatedWrapFees_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AutomatedWrapFees_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ANNUALFLATFEE"/>
 *     &lt;enumeration value="PERCENTOFEQUITY"/>
 *     &lt;enumeration value="PERCENTOFPROFIT"/>
 *     &lt;enumeration value="PERCENTOFPROFIT_QUARTER"/>
 *     &lt;enumeration value="INVOICE_LIMIT"/>
 *     &lt;enumeration value="INVOICE_LIMIT_Q"/>
 *     &lt;enumeration value="PERTRADE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AutomatedWrapFees_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum AutomatedWrapFeesType {

    ANNUALFLATFEE,
    PERCENTOFEQUITY,
    PERCENTOFPROFIT,
    PERCENTOFPROFIT_QUARTER,
    INVOICE_LIMIT,
    INVOICE_LIMIT_Q,
    PERTRADE;

    public String value() {
        return name();
    }

    public static AutomatedWrapFeesType fromValue(String v) {
        return valueOf(v);
    }

}


package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InheritedIRABeneficiary_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InheritedIRABeneficiary_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="T"/>
 *     &lt;enumeration value="O"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InheritedIRABeneficiary_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum InheritedIRABeneficiaryType {


    /**
     * 
     *                                                 Spouse
     *                                         
     * 
     */
    S,

    /**
     * 
     *                                                 Individual
     *                                         
     * 
     */
    I,

    /**
     * 
     *                                                 Trust
     *                                         
     * 
     */
    T,

    /**
     * 
     *                                                 Other
     *                                         
     * 
     */
    O;

    public String value() {
        return name();
    }

    public static InheritedIRABeneficiaryType fromValue(String v) {
        return valueOf(v);
    }

}

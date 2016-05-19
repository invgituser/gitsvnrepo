
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Employment_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Employment_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="UNEMPLOYED"/>
 *     &lt;enumeration value="EMPLOYED"/>
 *     &lt;enumeration value="SELFEMPLOYED"/>
 *     &lt;enumeration value="RETIRED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Employment_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum EmploymentType {

    UNEMPLOYED,
    EMPLOYED,
    SELFEMPLOYED,
    RETIRED;

    public String value() {
        return name();
    }

    public static EmploymentType fromValue(String v) {
        return valueOf(v);
    }

}

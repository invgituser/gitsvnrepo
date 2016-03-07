
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IRA_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IRA_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="RI"/>
 *     &lt;enumeration value="RO"/>
 *     &lt;enumeration value="RT"/>
 *     &lt;enumeration value="SP"/>
 *     &lt;enumeration value="ED"/>
 *     &lt;enumeration value="TH"/>
 *     &lt;enumeration value="RH"/>
 *     &lt;enumeration value="SH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IRA_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum IRAType {


    /**
     * 
     *                                                 Traditional New
     *                                         
     * 
     */
    RI,

    /**
     * 
     *                                                 Traditional Rollover
     *                                         
     * 
     */
    RO,

    /**
     * 
     *                                                 Roth New
     *                                         
     * 
     */
    RT,

    /**
     * 
     *                                                 SEP New
     *                                         
     * 
     */
    SP,

    /**
     * 
     *                                                 Education New
     *                                         
     * 
     */
    ED,

    /**
     * 
     *                                                 Traditional-Inherited
     *                                         
     * 
     */
    TH,

    /**
     * 
     *                                                 Roth-Inherited
     *                                         
     * 
     */
    RH,

    /**
     * 
     *                                                 SEP-Inherited
     *                                         
     * 
     */
    SH;

    public String value() {
        return name();
    }

    public static IRAType fromValue(String v) {
        return valueOf(v);
    }

}

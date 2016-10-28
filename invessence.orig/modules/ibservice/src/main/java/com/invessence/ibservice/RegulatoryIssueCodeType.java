
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegulatoryIssueCode_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RegulatoryIssueCode_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="DISPUTE"/>
 *     &lt;enumeration value="INVESTIGATION"/>
 *     &lt;enumeration value="CRIMINAL"/>
 *     &lt;enumeration value="MEMBERSHIP"/>
 *     &lt;enumeration value="AFFILIATION"/>
 *     &lt;enumeration value="CFTCREGISTERED"/>
 *     &lt;enumeration value="IBACCOUNTS"/>
 *     &lt;enumeration value="REGULATORYCONTROL"/>
 *     &lt;enumeration value="BROKERDEALER"/>
 *     &lt;enumeration value="EXCHANGEMEMBERSHIP"/>
 *     &lt;enumeration value="STOCKCONTROL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RegulatoryIssueCode_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum RegulatoryIssueCodeType {

    DISPUTE,
    INVESTIGATION,
    CRIMINAL,
    MEMBERSHIP,
    AFFILIATION,
    CFTCREGISTERED,
    IBACCOUNTS,
    REGULATORYCONTROL,
    BROKERDEALER,
    EXCHANGEMEMBERSHIP,
    STOCKCONTROL;

    public String value() {
        return name();
    }

    public static RegulatoryIssueCodeType fromValue(String v) {
        return valueOf(v);
    }

}

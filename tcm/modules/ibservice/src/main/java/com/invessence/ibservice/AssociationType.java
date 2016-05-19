
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Association_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Association_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Account Holder"/>
 *     &lt;enumeration value="FIRST HOLDER"/>
 *     &lt;enumeration value="SECOND HOLDER"/>
 *     &lt;enumeration value="TRADER"/>
 *     &lt;enumeration value="CEO"/>
 *     &lt;enumeration value="SECRETARY"/>
 *     &lt;enumeration value="TREASURER"/>
 *     &lt;enumeration value="OWNER"/>
 *     &lt;enumeration value="PRINCIPAL"/>
 *     &lt;enumeration value="SHAREHOLDER"/>
 *     &lt;enumeration value="TRUSTEE"/>
 *     &lt;enumeration value="BENEFICIARY"/>
 *     &lt;enumeration value="GRANTOR"/>
 *     &lt;enumeration value="Employee"/>
 *     &lt;enumeration value="CONTINGENT"/>
 *     &lt;enumeration value="IRA_BENEFICIARY"/>
 *     &lt;enumeration value="IRA DECEDENT"/>
 *     &lt;enumeration value="COMP_OFFICER"/>
 *     &lt;enumeration value="Other Officer"/>
 *     &lt;enumeration value="Controlling Officer"/>
 *     &lt;enumeration value="SIGNATORY"/>
 *     &lt;enumeration value="NON-EMPLOYEE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Association_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum AssociationType {

    @XmlEnumValue("Account Holder")
    ACCOUNT_HOLDER("Account Holder"),
    @XmlEnumValue("FIRST HOLDER")
    FIRST_HOLDER("FIRST HOLDER"),
    @XmlEnumValue("SECOND HOLDER")
    SECOND_HOLDER("SECOND HOLDER"),
    TRADER("TRADER"),
    CEO("CEO"),
    SECRETARY("SECRETARY"),
    TREASURER("TREASURER"),
    OWNER("OWNER"),
    PRINCIPAL("PRINCIPAL"),
    SHAREHOLDER("SHAREHOLDER"),
    TRUSTEE("TRUSTEE"),
    BENEFICIARY("BENEFICIARY"),
    GRANTOR("GRANTOR"),
    @XmlEnumValue("Employee")
    EMPLOYEE("Employee"),
    CONTINGENT("CONTINGENT"),
    IRA_BENEFICIARY("IRA_BENEFICIARY"),
    @XmlEnumValue("IRA DECEDENT")
    IRA_DECEDENT("IRA DECEDENT"),
    COMP_OFFICER("COMP_OFFICER"),
    @XmlEnumValue("Other Officer")
    OTHER_OFFICER("Other Officer"),
    @XmlEnumValue("Controlling Officer")
    CONTROLLING_OFFICER("Controlling Officer"),
    SIGNATORY("SIGNATORY"),
    @XmlEnumValue("NON-EMPLOYEE")
    NON_EMPLOYEE("NON-EMPLOYEE");
    private final String value;

    AssociationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AssociationType fromValue(String v) {
        for (AssociationType c: AssociationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

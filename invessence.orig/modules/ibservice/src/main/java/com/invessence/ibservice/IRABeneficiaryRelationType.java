
package com.invessence.ibservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IRABeneficiaryRelation_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IRABeneficiaryRelation_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Brother"/>
 *     &lt;enumeration value="Daughter"/>
 *     &lt;enumeration value="Estate"/>
 *     &lt;enumeration value="Father"/>
 *     &lt;enumeration value="Husband"/>
 *     &lt;enumeration value="Mother"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Sister"/>
 *     &lt;enumeration value="Son"/>
 *     &lt;enumeration value="Wife"/>
 *     &lt;enumeration value="Charity"/>
 *     &lt;enumeration value="Trust"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IRABeneficiaryRelation_Type", namespace = "http://www.interactivebrokers.com/schemas/IBCust_import")
@XmlEnum
public enum IRABeneficiaryRelationType {

    @XmlEnumValue("Brother")
    BROTHER("Brother"),
    @XmlEnumValue("Daughter")
    DAUGHTER("Daughter"),
    @XmlEnumValue("Estate")
    ESTATE("Estate"),
    @XmlEnumValue("Father")
    FATHER("Father"),
    @XmlEnumValue("Husband")
    HUSBAND("Husband"),
    @XmlEnumValue("Mother")
    MOTHER("Mother"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Sister")
    SISTER("Sister"),
    @XmlEnumValue("Son")
    SON("Son"),
    @XmlEnumValue("Wife")
    WIFE("Wife"),
    @XmlEnumValue("Charity")
    CHARITY("Charity"),
    @XmlEnumValue("Trust")
    TRUST("Trust");
    private final String value;

    IRABeneficiaryRelationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IRABeneficiaryRelationType fromValue(String v) {
        for (IRABeneficiaryRelationType c: IRABeneficiaryRelationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

/**
 * LotDetailResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class LotDetailResult  implements java.io.Serializable {
    private java.util.Calendar purchaseDate;

    private java.math.BigDecimal costBasisPrice;

    private java.math.BigDecimal shares;

    private java.math.BigDecimal freeShares;

    private short ageAdjustments;

    private short age;

    private org.apache.axis.types.UnsignedByte daysOrMonthsIndicator;

    private java.lang.String daysOrMonthsIndicatorDescription;

    private java.math.BigDecimal CDSCPercentage;

    private java.math.BigDecimal CDSCAmount;

    private short lotType;

    private java.lang.String lotTypeDescription;

    private java.util.Calendar tollStartDate;

    public LotDetailResult() {
    }

    public LotDetailResult(
           java.util.Calendar purchaseDate,
           java.math.BigDecimal costBasisPrice,
           java.math.BigDecimal shares,
           java.math.BigDecimal freeShares,
           short ageAdjustments,
           short age,
           org.apache.axis.types.UnsignedByte daysOrMonthsIndicator,
           java.lang.String daysOrMonthsIndicatorDescription,
           java.math.BigDecimal CDSCPercentage,
           java.math.BigDecimal CDSCAmount,
           short lotType,
           java.lang.String lotTypeDescription,
           java.util.Calendar tollStartDate) {
           this.purchaseDate = purchaseDate;
           this.costBasisPrice = costBasisPrice;
           this.shares = shares;
           this.freeShares = freeShares;
           this.ageAdjustments = ageAdjustments;
           this.age = age;
           this.daysOrMonthsIndicator = daysOrMonthsIndicator;
           this.daysOrMonthsIndicatorDescription = daysOrMonthsIndicatorDescription;
           this.CDSCPercentage = CDSCPercentage;
           this.CDSCAmount = CDSCAmount;
           this.lotType = lotType;
           this.lotTypeDescription = lotTypeDescription;
           this.tollStartDate = tollStartDate;
    }


    /**
     * Gets the purchaseDate value for this LotDetailResult.
     * 
     * @return purchaseDate
     */
    public java.util.Calendar getPurchaseDate() {
        return purchaseDate;
    }


    /**
     * Sets the purchaseDate value for this LotDetailResult.
     * 
     * @param purchaseDate
     */
    public void setPurchaseDate(java.util.Calendar purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    /**
     * Gets the costBasisPrice value for this LotDetailResult.
     * 
     * @return costBasisPrice
     */
    public java.math.BigDecimal getCostBasisPrice() {
        return costBasisPrice;
    }


    /**
     * Sets the costBasisPrice value for this LotDetailResult.
     * 
     * @param costBasisPrice
     */
    public void setCostBasisPrice(java.math.BigDecimal costBasisPrice) {
        this.costBasisPrice = costBasisPrice;
    }


    /**
     * Gets the shares value for this LotDetailResult.
     * 
     * @return shares
     */
    public java.math.BigDecimal getShares() {
        return shares;
    }


    /**
     * Sets the shares value for this LotDetailResult.
     * 
     * @param shares
     */
    public void setShares(java.math.BigDecimal shares) {
        this.shares = shares;
    }


    /**
     * Gets the freeShares value for this LotDetailResult.
     * 
     * @return freeShares
     */
    public java.math.BigDecimal getFreeShares() {
        return freeShares;
    }


    /**
     * Sets the freeShares value for this LotDetailResult.
     * 
     * @param freeShares
     */
    public void setFreeShares(java.math.BigDecimal freeShares) {
        this.freeShares = freeShares;
    }


    /**
     * Gets the ageAdjustments value for this LotDetailResult.
     * 
     * @return ageAdjustments
     */
    public short getAgeAdjustments() {
        return ageAdjustments;
    }


    /**
     * Sets the ageAdjustments value for this LotDetailResult.
     * 
     * @param ageAdjustments
     */
    public void setAgeAdjustments(short ageAdjustments) {
        this.ageAdjustments = ageAdjustments;
    }


    /**
     * Gets the age value for this LotDetailResult.
     * 
     * @return age
     */
    public short getAge() {
        return age;
    }


    /**
     * Sets the age value for this LotDetailResult.
     * 
     * @param age
     */
    public void setAge(short age) {
        this.age = age;
    }


    /**
     * Gets the daysOrMonthsIndicator value for this LotDetailResult.
     * 
     * @return daysOrMonthsIndicator
     */
    public org.apache.axis.types.UnsignedByte getDaysOrMonthsIndicator() {
        return daysOrMonthsIndicator;
    }


    /**
     * Sets the daysOrMonthsIndicator value for this LotDetailResult.
     * 
     * @param daysOrMonthsIndicator
     */
    public void setDaysOrMonthsIndicator(org.apache.axis.types.UnsignedByte daysOrMonthsIndicator) {
        this.daysOrMonthsIndicator = daysOrMonthsIndicator;
    }


    /**
     * Gets the daysOrMonthsIndicatorDescription value for this LotDetailResult.
     * 
     * @return daysOrMonthsIndicatorDescription
     */
    public java.lang.String getDaysOrMonthsIndicatorDescription() {
        return daysOrMonthsIndicatorDescription;
    }


    /**
     * Sets the daysOrMonthsIndicatorDescription value for this LotDetailResult.
     * 
     * @param daysOrMonthsIndicatorDescription
     */
    public void setDaysOrMonthsIndicatorDescription(java.lang.String daysOrMonthsIndicatorDescription) {
        this.daysOrMonthsIndicatorDescription = daysOrMonthsIndicatorDescription;
    }


    /**
     * Gets the CDSCPercentage value for this LotDetailResult.
     * 
     * @return CDSCPercentage
     */
    public java.math.BigDecimal getCDSCPercentage() {
        return CDSCPercentage;
    }


    /**
     * Sets the CDSCPercentage value for this LotDetailResult.
     * 
     * @param CDSCPercentage
     */
    public void setCDSCPercentage(java.math.BigDecimal CDSCPercentage) {
        this.CDSCPercentage = CDSCPercentage;
    }


    /**
     * Gets the CDSCAmount value for this LotDetailResult.
     * 
     * @return CDSCAmount
     */
    public java.math.BigDecimal getCDSCAmount() {
        return CDSCAmount;
    }


    /**
     * Sets the CDSCAmount value for this LotDetailResult.
     * 
     * @param CDSCAmount
     */
    public void setCDSCAmount(java.math.BigDecimal CDSCAmount) {
        this.CDSCAmount = CDSCAmount;
    }


    /**
     * Gets the lotType value for this LotDetailResult.
     * 
     * @return lotType
     */
    public short getLotType() {
        return lotType;
    }


    /**
     * Sets the lotType value for this LotDetailResult.
     * 
     * @param lotType
     */
    public void setLotType(short lotType) {
        this.lotType = lotType;
    }


    /**
     * Gets the lotTypeDescription value for this LotDetailResult.
     * 
     * @return lotTypeDescription
     */
    public java.lang.String getLotTypeDescription() {
        return lotTypeDescription;
    }


    /**
     * Sets the lotTypeDescription value for this LotDetailResult.
     * 
     * @param lotTypeDescription
     */
    public void setLotTypeDescription(java.lang.String lotTypeDescription) {
        this.lotTypeDescription = lotTypeDescription;
    }


    /**
     * Gets the tollStartDate value for this LotDetailResult.
     * 
     * @return tollStartDate
     */
    public java.util.Calendar getTollStartDate() {
        return tollStartDate;
    }


    /**
     * Sets the tollStartDate value for this LotDetailResult.
     * 
     * @param tollStartDate
     */
    public void setTollStartDate(java.util.Calendar tollStartDate) {
        this.tollStartDate = tollStartDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LotDetailResult)) return false;
        LotDetailResult other = (LotDetailResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.purchaseDate==null && other.getPurchaseDate()==null) || 
             (this.purchaseDate!=null &&
              this.purchaseDate.equals(other.getPurchaseDate()))) &&
            ((this.costBasisPrice==null && other.getCostBasisPrice()==null) || 
             (this.costBasisPrice!=null &&
              this.costBasisPrice.equals(other.getCostBasisPrice()))) &&
            ((this.shares==null && other.getShares()==null) || 
             (this.shares!=null &&
              this.shares.equals(other.getShares()))) &&
            ((this.freeShares==null && other.getFreeShares()==null) || 
             (this.freeShares!=null &&
              this.freeShares.equals(other.getFreeShares()))) &&
            this.ageAdjustments == other.getAgeAdjustments() &&
            this.age == other.getAge() &&
            ((this.daysOrMonthsIndicator==null && other.getDaysOrMonthsIndicator()==null) || 
             (this.daysOrMonthsIndicator!=null &&
              this.daysOrMonthsIndicator.equals(other.getDaysOrMonthsIndicator()))) &&
            ((this.daysOrMonthsIndicatorDescription==null && other.getDaysOrMonthsIndicatorDescription()==null) || 
             (this.daysOrMonthsIndicatorDescription!=null &&
              this.daysOrMonthsIndicatorDescription.equals(other.getDaysOrMonthsIndicatorDescription()))) &&
            ((this.CDSCPercentage==null && other.getCDSCPercentage()==null) || 
             (this.CDSCPercentage!=null &&
              this.CDSCPercentage.equals(other.getCDSCPercentage()))) &&
            ((this.CDSCAmount==null && other.getCDSCAmount()==null) || 
             (this.CDSCAmount!=null &&
              this.CDSCAmount.equals(other.getCDSCAmount()))) &&
            this.lotType == other.getLotType() &&
            ((this.lotTypeDescription==null && other.getLotTypeDescription()==null) || 
             (this.lotTypeDescription!=null &&
              this.lotTypeDescription.equals(other.getLotTypeDescription()))) &&
            ((this.tollStartDate==null && other.getTollStartDate()==null) || 
             (this.tollStartDate!=null &&
              this.tollStartDate.equals(other.getTollStartDate())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPurchaseDate() != null) {
            _hashCode += getPurchaseDate().hashCode();
        }
        if (getCostBasisPrice() != null) {
            _hashCode += getCostBasisPrice().hashCode();
        }
        if (getShares() != null) {
            _hashCode += getShares().hashCode();
        }
        if (getFreeShares() != null) {
            _hashCode += getFreeShares().hashCode();
        }
        _hashCode += getAgeAdjustments();
        _hashCode += getAge();
        if (getDaysOrMonthsIndicator() != null) {
            _hashCode += getDaysOrMonthsIndicator().hashCode();
        }
        if (getDaysOrMonthsIndicatorDescription() != null) {
            _hashCode += getDaysOrMonthsIndicatorDescription().hashCode();
        }
        if (getCDSCPercentage() != null) {
            _hashCode += getCDSCPercentage().hashCode();
        }
        if (getCDSCAmount() != null) {
            _hashCode += getCDSCAmount().hashCode();
        }
        _hashCode += getLotType();
        if (getLotTypeDescription() != null) {
            _hashCode += getLotTypeDescription().hashCode();
        }
        if (getTollStartDate() != null) {
            _hashCode += getTollStartDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LotDetailResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotDetailResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PurchaseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("costBasisPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CostBasisPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Shares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("freeShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FreeShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ageAdjustments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AgeAdjustments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("age");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Age"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("daysOrMonthsIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DaysOrMonthsIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("daysOrMonthsIndicatorDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DaysOrMonthsIndicatorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CDSCPercentage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCPercentage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CDSCAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lotType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lotTypeDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotTypeDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tollStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TollStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

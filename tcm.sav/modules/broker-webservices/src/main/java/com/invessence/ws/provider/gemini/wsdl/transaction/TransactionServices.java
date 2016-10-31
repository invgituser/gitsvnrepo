/**
 * TransactionServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.transaction;

public interface TransactionServices extends javax.xml.rpc.Service {
    public java.lang.String getTransactionServicesSoapAddress();

    public com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_PortType getTransactionServicesSoap() throws javax.xml.rpc.ServiceException;

    public com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_PortType getTransactionServicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

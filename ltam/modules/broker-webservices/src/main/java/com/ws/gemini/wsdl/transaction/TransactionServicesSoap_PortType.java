/**
 * TransactionServicesSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.transaction;

public interface TransactionServicesSoap_PortType extends java.rmi.Remote {
    public com.ws.gemini.wsdl.transaction.TransactionCollectionResult createTransaction(com.ws.gemini.wsdl.transaction.AuthenticateLogin userLogin, com.ws.gemini.wsdl.transaction.TransactionInfo transactionInfo, com.ws.gemini.wsdl.transaction.FundInformation[] fundInfo) throws java.rmi.RemoteException;
    public com.ws.gemini.wsdl.transaction.AsofDateResult getAsofDate(com.ws.gemini.wsdl.transaction.AuthenticateLogin userLogin, short holidayListType) throws java.rmi.RemoteException;
}

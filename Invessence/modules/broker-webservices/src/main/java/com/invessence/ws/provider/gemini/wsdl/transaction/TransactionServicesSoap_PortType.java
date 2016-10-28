/**
 * TransactionServicesSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.transaction;

public interface TransactionServicesSoap_PortType extends java.rmi.Remote {
    public com.invessence.ws.provider.gemini.wsdl.transaction.TransactionCollectionResult createTransaction(com.invessence.ws.provider.gemini.wsdl.transaction.AuthenticateLogin userLogin, com.invessence.ws.provider.gemini.wsdl.transaction.TransactionInfo transactionInfo, com.invessence.ws.provider.gemini.wsdl.transaction.FundInformation[] fundInfo) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.transaction.AsofDateResult getAsofDate(com.invessence.ws.provider.gemini.wsdl.transaction.AuthenticateLogin userLogin, short holidayListType) throws java.rmi.RemoteException;
}

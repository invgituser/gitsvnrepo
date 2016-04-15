/**
 * TransactionServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.transaction;

import com.invessence.ws.util.SysParameters;

public class TransactionServicesLocator extends org.apache.axis.client.Service implements com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServices {

    public TransactionServicesLocator() {
    }


    public TransactionServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TransactionServicesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TransactionServicesSoap
    private java.lang.String TransactionServicesSoap_address = SysParameters.geminiEndPointUrl+"/TransactionServices.asmx";

    public java.lang.String getTransactionServicesSoapAddress() {
        return TransactionServicesSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TransactionServicesSoapWSDDServiceName = "TransactionServicesSoap";

    public java.lang.String getTransactionServicesSoapWSDDServiceName() {
        return TransactionServicesSoapWSDDServiceName;
    }

    public void setTransactionServicesSoapWSDDServiceName(java.lang.String name) {
        TransactionServicesSoapWSDDServiceName = name;
    }

    public com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_PortType getTransactionServicesSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TransactionServicesSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTransactionServicesSoap(endpoint);
    }

    public com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_PortType getTransactionServicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_BindingStub _stub = new com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_BindingStub(portAddress, this);
            _stub.setPortName(getTransactionServicesSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTransactionServicesSoapEndpointAddress(java.lang.String address) {
        TransactionServicesSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_BindingStub _stub = new com.invessence.ws.provider.gemini.wsdl.transaction.TransactionServicesSoap_BindingStub(new java.net.URL(TransactionServicesSoap_address), this);
                _stub.setPortName(getTransactionServicesSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TransactionServicesSoap".equals(inputPortName)) {
            return getTransactionServicesSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionServicesSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TransactionServicesSoap".equals(portName)) {
            setTransactionServicesSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

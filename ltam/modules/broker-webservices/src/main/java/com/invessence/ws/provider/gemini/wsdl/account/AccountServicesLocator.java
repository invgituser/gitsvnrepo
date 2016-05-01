/**
 * AccountServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

import com.invessence.ws.util.SysParameters;

public class AccountServicesLocator extends org.apache.axis.client.Service implements com.invessence.ws.provider.gemini.wsdl.account.AccountServices {

    public AccountServicesLocator() {
    }


    public AccountServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AccountServicesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AccountServicesSoap
    private java.lang.String AccountServicesSoap_address = SysParameters.geminiEndPointUrl+"/AccountServices.asmx";

    public java.lang.String getAccountServicesSoapAddress() {
        return AccountServicesSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AccountServicesSoapWSDDServiceName = "AccountServicesSoap";

    public java.lang.String getAccountServicesSoapWSDDServiceName() {
        return AccountServicesSoapWSDDServiceName;
    }

    public void setAccountServicesSoapWSDDServiceName(java.lang.String name) {
        AccountServicesSoapWSDDServiceName = name;
    }

    public com.invessence.ws.provider.gemini.wsdl.account.AccountServicesSoap_PortType getAccountServicesSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AccountServicesSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAccountServicesSoap(endpoint);
    }

    public com.invessence.ws.provider.gemini.wsdl.account.AccountServicesSoap_PortType getAccountServicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.invessence.ws.provider.gemini.wsdl.account.AccountServicesSoap_BindingStub _stub = new com.invessence.ws.provider.gemini.wsdl.account.AccountServicesSoap_BindingStub(portAddress, this);
            _stub.setPortName(getAccountServicesSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAccountServicesSoapEndpointAddress(java.lang.String address) {
        AccountServicesSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.invessence.ws.provider.gemini.wsdl.account.AccountServicesSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.invessence.ws.provider.gemini.wsdl.account.AccountServicesSoap_BindingStub _stub = new com.invessence.ws.provider.gemini.wsdl.account.AccountServicesSoap_BindingStub(new java.net.URL(AccountServicesSoap_address), this);
                _stub.setPortName(getAccountServicesSoapWSDDServiceName());
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
        if ("AccountServicesSoap".equals(inputPortName)) {
            return getAccountServicesSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountServicesSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AccountServicesSoap".equals(portName)) {
            setAccountServicesSoapEndpointAddress(address);
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

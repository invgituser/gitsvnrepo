/**
 * LoginServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.login;

import com.invessence.util.SysParameters;

public class LoginServicesLocator extends org.apache.axis.client.Service implements com.ws.gemini.wsdl.login.LoginServices {

    public LoginServicesLocator() {
    }


    public LoginServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LoginServicesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LoginServicesSoap
    private java.lang.String LoginServicesSoap_address = SysParameters.geminiEndPointUrl+"/LoginServices.asmx";

    public java.lang.String getLoginServicesSoapAddress() {
        return LoginServicesSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LoginServicesSoapWSDDServiceName = "LoginServicesSoap";

    public java.lang.String getLoginServicesSoapWSDDServiceName() {
        return LoginServicesSoapWSDDServiceName;
    }

    public void setLoginServicesSoapWSDDServiceName(java.lang.String name) {
        LoginServicesSoapWSDDServiceName = name;
    }

    public com.ws.gemini.wsdl.login.LoginServicesSoap_PortType getLoginServicesSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LoginServicesSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLoginServicesSoap(endpoint);
    }

    public com.ws.gemini.wsdl.login.LoginServicesSoap_PortType getLoginServicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ws.gemini.wsdl.login.LoginServicesSoap_BindingStub _stub = new com.ws.gemini.wsdl.login.LoginServicesSoap_BindingStub(portAddress, this);
            _stub.setPortName(getLoginServicesSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLoginServicesSoapEndpointAddress(java.lang.String address) {
        LoginServicesSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ws.gemini.wsdl.login.LoginServicesSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ws.gemini.wsdl.login.LoginServicesSoap_BindingStub _stub = new com.ws.gemini.wsdl.login.LoginServicesSoap_BindingStub(new java.net.URL(LoginServicesSoap_address), this);
                _stub.setPortName(getLoginServicesSoapWSDDServiceName());
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
        if ("LoginServicesSoap".equals(inputPortName)) {
            return getLoginServicesSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://enfs.com/webservices/", "LoginServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LoginServicesSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LoginServicesSoap".equals(portName)) {
            setLoginServicesSoapEndpointAddress(address);
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

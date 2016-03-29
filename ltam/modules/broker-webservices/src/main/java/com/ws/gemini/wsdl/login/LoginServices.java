/**
 * LoginServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.login;

public interface LoginServices extends javax.xml.rpc.Service {
    public java.lang.String getLoginServicesSoapAddress();

    public com.ws.gemini.wsdl.login.LoginServicesSoap_PortType getLoginServicesSoap() throws javax.xml.rpc.ServiceException;

    public com.ws.gemini.wsdl.login.LoginServicesSoap_PortType getLoginServicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

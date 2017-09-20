/**
 * LoginServicesSoap_BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.login;

public class LoginServicesSoap_BindingStub extends org.apache.axis.client.Stub implements com.invessence.ws.provider.gemini.wsdl.login.LoginServicesSoap_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[14];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("masterLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterWebuserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "masterLoginResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateMasterPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "oldPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "newPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "verifyNewPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterWebuserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateMasterPasswordResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("shareholderLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "noOfUnsuccessfulLoginAttempt"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "shareholderLoginResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateShareholderPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "securityAnswer"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "oldPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "newPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "verifyNewPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateShareholderPasswordResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateShareholderSecurityQuestionAnswer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "currentPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "currentSecurityAnswer"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "newSecurityQuestion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "newSecurityAnswer"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateShareholderSecurityQuestionAnswerResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createShareholderWebUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ssnorTin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "zipCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "emailAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "securityQuestion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "securityAnswer"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accessLinkedAccounts"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "createShareholderWebUserResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getWebUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getWebUserResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getWebUserForAccountSSNOrTINZipcode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ssnorTin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "zipCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getWebUserForAccountSSNOrTINZipcodeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getWebUserForUserIdSSNOrTIN");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ssnorTin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getWebUserForUserIdSSNOrTINResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updatePasswordWithNoAuthentication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ssnorTin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "securityQuestion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "securityAnswer"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "newPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "verifyPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updatePasswordWithNoAuthenticationResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateWebUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "webUser"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserRequest"), com.invessence.ws.provider.gemini.wsdl.login.WebUserRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Status"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateWebUserResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateWebUserCustomValues");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "webUser"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserCustomValuesRequest"), com.invessence.ws.provider.gemini.wsdl.login.WebUserCustomValuesRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Status"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateWebUserCustomValuesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isWebUserExists");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExistResult"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.ExistResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isWebUserExistsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("systemUserLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateSystemUserLogin"), com.invessence.ws.provider.gemini.wsdl.login.AuthenticateSystemUserLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Status"));
        oper.setReturnClass(com.invessence.ws.provider.gemini.wsdl.login.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "systemUserLoginResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

    }

    public LoginServicesSoap_BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public LoginServicesSoap_BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public LoginServicesSoap_BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateSystemUserLogin");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.AuthenticateSystemUserLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExistResult");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.ExistResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterWebuserResult");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Status");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.Status.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserCustomValuesRequest");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.WebUserCustomValuesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserRequest");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.WebUserRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult");
            cachedSerQNames.add(qName);
            cls = com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult masterLogin(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/masterLogin");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "masterLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult updateMasterPassword(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String oldPassword, java.lang.String newPassword, java.lang.String verifyNewPassword) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateMasterPassword");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateMasterPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, oldPassword, newPassword, verifyNewPassword});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult shareholderLogin(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.math.BigDecimal noOfUnsuccessfulLoginAttempt) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/shareholderLogin");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "shareholderLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, noOfUnsuccessfulLoginAttempt});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult updateShareholderPassword(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String securityAnswer, java.lang.String oldPassword, java.lang.String newPassword, java.lang.String verifyNewPassword) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateShareholderPassword");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateShareholderPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, securityAnswer, oldPassword, newPassword, verifyNewPassword});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult updateShareholderSecurityQuestionAnswer(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String currentPassword, java.lang.String currentSecurityAnswer, java.lang.String newSecurityQuestion, java.lang.String newSecurityAnswer) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateShareholderSecurityQuestionAnswer");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateShareholderSecurityQuestionAnswer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, currentPassword, currentSecurityAnswer, newSecurityQuestion, newSecurityAnswer});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult createShareholderWebUser(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String accountNumber, java.lang.String ssnorTin, java.lang.String zipCode, java.lang.String emailAddress, java.lang.String securityQuestion, java.lang.String securityAnswer, org.apache.axis.types.UnsignedByte accessLinkedAccounts) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/createShareholderWebUser");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "createShareholderWebUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, ssnorTin, zipCode, emailAddress, securityQuestion, securityAnswer, accessLinkedAccounts});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult getWebUser(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getWebUser");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getWebUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult getWebUserForAccountSSNOrTINZipcode(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String accountNumber, java.lang.String ssnorTin, java.lang.String zipCode) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getWebUserForAccountSSNOrTINZipcode");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getWebUserForAccountSSNOrTINZipcode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, ssnorTin, zipCode});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult getWebUserForUserIdSSNOrTIN(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String ssnorTin) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getWebUserForUserIdSSNOrTIN");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getWebUserForUserIdSSNOrTIN"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, ssnorTin});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult updatePasswordWithNoAuthentication(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String ssnorTin, java.lang.String securityQuestion, java.lang.String securityAnswer, java.lang.String newPassword, java.lang.String verifyPassword) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updatePasswordWithNoAuthentication");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updatePasswordWithNoAuthentication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, ssnorTin, securityQuestion, securityAnswer, newPassword, verifyPassword});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.WebUserResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.WebUserResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.Status updateWebUser(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, com.invessence.ws.provider.gemini.wsdl.login.WebUserRequest webUser) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateWebUser");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateWebUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, webUser});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.Status updateWebUserCustomValues(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, com.invessence.ws.provider.gemini.wsdl.login.WebUserCustomValuesRequest webUser) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateWebUserCustomValues");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateWebUserCustomValues"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, webUser});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.ExistResult isWebUserExists(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/isWebUserExists");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isWebUserExists"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.ExistResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.ExistResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.ExistResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.invessence.ws.provider.gemini.wsdl.login.Status systemUserLogin(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateSystemUserLogin userLogin) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/systemUserLogin");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "systemUserLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.invessence.ws.provider.gemini.wsdl.login.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.invessence.ws.provider.gemini.wsdl.login.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.invessence.ws.provider.gemini.wsdl.login.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

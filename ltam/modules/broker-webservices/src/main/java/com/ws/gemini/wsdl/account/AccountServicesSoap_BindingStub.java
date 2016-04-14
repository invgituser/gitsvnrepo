/**
 * AccountServicesSoap_BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AccountServicesSoap_BindingStub extends org.apache.axis.client.Stub implements com.ws.gemini.wsdl.account.AccountServicesSoap_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[47];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAccountInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isShareholder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountInfoResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AccountInfoResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccountInfoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMailingAddress");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "mailingAddressType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "useRegistrationAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isShareholder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.MailingAddressesResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getMailingAddressResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBeneficiary529");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isShareholder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529Result"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Beneficiary529Result.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getBeneficiary529Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPlan529AccountBalanceActivityCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "beneficiarySSN"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "holidayListId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isShareholder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPlan529AccountBalanceActivityCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getActivityCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "fundId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationBeginDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isShareholder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.ActivityCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getActivityCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAuthorizedAccountCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthorizedAccountCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AuthorizedAccountCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAuthorizedAccountCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AccountResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccountResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isAccountClosed");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthorizedAccountsResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AuthorizedAccountsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isAccountClosedResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPlan529AccountBalanceSummaryCollectionForAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPlan529AccountBalanceSummaryCollectionForAccountResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPlan529AccountBalanceSummaryCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPlan529AccountBalanceSummaryCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBalanceCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "fundId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.BalanceCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getBalanceCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBalanceCollectionWithDateRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "fundId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"), short.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "displayCurrencyId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationBeginDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "computeShareAmounts"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "computePeriodDistributions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "computeDividendPayable"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "computeAverageCostBasis"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "computeReinvestmentSettings"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "computeActivitySummary"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "returnAllLinkedAccounts"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "returnZeroBalanceSubAccounts"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationPriceCycleId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "valuationBeginPriceCycleId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.BalanceCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getBalanceCollectionWithDateRangeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isAccountPayeeBeingUsed");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountPayeeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AchPayeeResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isAccountPayeeBeingUsedResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAchPayeeCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AchPayeeCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAchPayeeCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addAchPayee");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "achPayee"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeRequest"), com.ws.gemini.wsdl.account.AchPayeeRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAchPayeeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteAchPayee");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountPayeeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "achpayeeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "deleteAchPayeeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCheckWirePayeeCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CheckWirePayeeCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.CheckWirePayeeCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getCheckWirePayeeCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAutomaticInvestmentCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentsCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AutomaticInvestmentsCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAutomaticInvestmentCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addAutomaticInvestmentPlan");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "automaticInvestment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentRequest"), com.ws.gemini.wsdl.account.AutomaticInvestmentRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "achPayeeInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeRequest"), com.ws.gemini.wsdl.account.AchPayeeRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAutomaticInvestmentPlanResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modifyAutomaticInvestmentPlan");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "automaticInvestment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentRequest"), com.ws.gemini.wsdl.account.AutomaticInvestmentRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "achPayeeInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeRequest"), com.ws.gemini.wsdl.account.AchPayeeRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "modifyAutomaticInvestmentPlanResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteAutomaticInvestmentPlan");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "automaticInvestmentID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "deleteAutomaticInvestmentPlanResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addAccountForCommonMailing");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountLinkages"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountLinkagesRequest"), com.ws.gemini.wsdl.account.AccountLinkagesRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAccountForCommonMailingResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStatementByFormCategory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "statementbyFormCategory"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "StatementbyFormCategoryRequest"), com.ws.gemini.wsdl.account.StatementbyFormCategoryRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "StatementbyFormCategoryResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.StatementbyFormCategoryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getStatementByFormCategoryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateStatementByFormCategory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "statementbyFormCategory"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "StatementbyFormCategoryRequest"), com.ws.gemini.wsdl.account.StatementbyFormCategoryRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "StatementbyFormCategoryResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.StatementbyFormCategoryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateStatementByFormCategoryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMailingAddressForMailingAddressType");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "mailingAddressType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.MailingAddressesResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getMailingAddressForMailingAddressTypeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateMailingAddresses");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "mailingAddresses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesRequest"), com.ws.gemini.wsdl.account.MailingAddressesRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateMailingAddressesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateSuccessorBeneficiaries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "successorBeneficiary"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryRequest"), com.ws.gemini.wsdl.account.SuccessorBeneficiaryRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateSuccessorBeneficiariesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addSuccessorBeneficiaries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "successorBeneficiary"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryRequest"), com.ws.gemini.wsdl.account.SuccessorBeneficiaryRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addSuccessorBeneficiariesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateBeneficiary529");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "beneficiary529"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529Request"), com.ws.gemini.wsdl.account.Beneficiary529Request.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateBeneficiary529Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addAccountMessages");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageRequest"), com.ws.gemini.wsdl.account.AccountMessageRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAccountMessagesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addAccountCustomField");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountCustomField"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldRequest"), com.ws.gemini.wsdl.account.AccountCustomFieldRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAccountCustomFieldResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAssetAllocationModelWeights");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelWeightsResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AssetAllocationModelWeightsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAssetAllocationModelWeightsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateAssetAllocationModel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "assetAllocationModel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelsRequest"), com.ws.gemini.wsdl.account.AssetAllocationModelsRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateAssetAllocationModelResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createNewAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "account"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountRequest"), com.ws.gemini.wsdl.account.AccountRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NewAccountResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.NewAccountResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "createNewAccountResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAccountForCustomFieldValue");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountCustomFieldValueRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldValuesRequest"), com.ws.gemini.wsdl.account.AccountCustomFieldValuesRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldValuesResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AccountCustomFieldValuesResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccountForCustomFieldValueResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPendingTradesForAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.PendingOrderCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPendingTradesForAccountResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateDeliveryModeForEdocs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "edocDeliveryModeRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeRequest"), com.ws.gemini.wsdl.account.EdocsDeliveryModeRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateDeliveryModeForEdocsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEdocs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.EdocsCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getEdocsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPrepaidActivityCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "prepaidActivityRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityRequest"), com.ws.gemini.wsdl.account.PrepaidActivityRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.PrepaidActivityCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPrepaidActivityCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPrepaidSummaryCollection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "prepaidSummaryRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryRequest"), com.ws.gemini.wsdl.account.PrepaidSummaryRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.PrepaidSummaryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPrepaidSummaryCollectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFavoriteInstitutionsForBeneficiary");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.FavoriteInstitutionsCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getFavoriteInstitutionsForBeneficiaryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateFavouriteInstitutionsForBeneficiary");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateFavoriteInstitutionsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateFavoriteInstitutionsRequest"), com.ws.gemini.wsdl.account.UpdateFavoriteInstitutionsRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateFavouriteInstitutionsForBeneficiaryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAccountCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.AccountCustomFieldsCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccountCustomFieldsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateAccountCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateCustomFieldsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateCustomFieldsRequest"), com.ws.gemini.wsdl.account.UpdateCustomFieldsRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateAccountCustomFieldsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCustomFieldListValues");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountCustomFieldDefinition"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValuesCollectionResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.CustomFieldListValuesCollectionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getCustomFieldListValuesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("calculateCDSCForAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "cdscCalculatorRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCCalculatorRequest"), com.ws.gemini.wsdl.account.CDSCCalculatorRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCCalculatorResult"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.CDSCCalculatorResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "calculateCDSCForAccountResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateAccountDividendSetup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "userLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"), com.ws.gemini.wsdl.account.AuthenticateLogin.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://enfs.com/webservices/", "accountDividendSetupRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountDividendSetupRequest"), com.ws.gemini.wsdl.account.AccountDividendSetupRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
        oper.setReturnClass(com.ws.gemini.wsdl.account.Status.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateAccountDividendSetupResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[46] = oper;

    }

    public AccountServicesSoap_BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AccountServicesSoap_BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AccountServicesSoap_BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
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
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldsCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldValuesRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldValuesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldValuesResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldValuesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountDividendSetupRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountDividendSetupRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountInfoResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountInfoResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountLinkagesRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountLinkagesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageCollectionRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountMessageCollectionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountMessageCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountMessageRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountMessageResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeCollectionRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AchPayeeCollectionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AchPayeeCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AchPayeeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AchPayeeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.ActivityCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.ActivityResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformation");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AIPFundInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformationResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AIPFundInformationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAccountAssetAllocationModelWeightsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsRequest");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAccountAssetAllocationModelWeightsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAccountCustomFieldsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldsRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsRequest");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAccountCustomFieldsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountCustomFieldsResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAccountMessageRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountMessageRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageRequest");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAccountMessageResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AccountMessageResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAchPayeeRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AchPayeeRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeRequest");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAchPayeeResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AchPayeeResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfActivityResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.ActivityResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAIPFundInformation");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AIPFundInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformation");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformation");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAIPFundInformationResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AIPFundInformationResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformationResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformationResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAssetAllocationFundsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AssetAllocationFundsRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsRequest");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAssetAllocationFundsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AssetAllocationFundsResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAuthorizedAccountsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AuthorizedAccountsResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthorizedAccountsResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthorizedAccountsResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAutomaticInvestmentLinesResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AutomaticInvestmentLinesResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLinesResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLinesResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfAutomaticInvestmentResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AutomaticInvestmentResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfBalanceResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.BalanceResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfCheckWirePayeeResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CheckWirePayeeResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CheckWirePayeeResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CheckWirePayeeResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfCustomFieldListValue");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CustomFieldListValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValue");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValue");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfCustomFieldListValuesResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CustomFieldListValuesResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValuesResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValuesResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfEdocsDeliveryModeResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.EdocsDeliveryModeResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfEdocsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.EdocsResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfFavoriteInstitutionsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.FavoriteInstitutionsRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsRequest");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfFundForRedemptionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.FundForRedemptionResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundForRedemptionResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundForRedemptionResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfInstitution");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Institution[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Institution");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Institution");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfLotDetailResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.LotDetailResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotDetailResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotDetailResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfPendingOrderResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PendingOrderResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfPlan529AccountBalanceActivityResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfPlan529AccountBalanceSummaryResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfPrepaidActivityResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidActivityResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfPrepaidDetailResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidDetailResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidDetailResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidDetailResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfSuccessorBeneficiaryRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.SuccessorBeneficiaryRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryRequest");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfSuccessorBeneficiaryResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.SuccessorBeneficiaryResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "ArrayOfTaxFormResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.TaxFormResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "TaxFormResult");
            qName2 = new javax.xml.namespace.QName("http://enfs.com/webservices/", "TaxFormResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AssetAllocationFundsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AssetAllocationFundsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AssetAllocationModelsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AssetAllocationModelsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelWeightsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AssetAllocationModelWeightsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AuthenticateLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthorizedAccountCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AuthorizedAccountCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthorizedAccountsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AuthorizedAccountsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLinesResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AutomaticInvestmentLinesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AutomaticInvestmentRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AutomaticInvestmentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentsCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.AutomaticInvestmentsCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.BalanceCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.BalanceResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529Request");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Beneficiary529Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529Result");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Beneficiary529Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCCalculatorRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CDSCCalculatorRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCCalculatorResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CDSCCalculatorResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CheckWirePayeeCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CheckWirePayeeCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CheckWirePayeeResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CheckWirePayeeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValue");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CustomFieldListValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValuesCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CustomFieldListValuesCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValuesResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.CustomFieldListValuesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.EdocsCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.EdocsDeliveryModeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.EdocsDeliveryModeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.EdocsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.FavoriteInstitutionsCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.FavoriteInstitutionsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundForRedemptionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.FundForRedemptionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "GenderEnum");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.GenderEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "GenderEnum1");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.GenderEnum1.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Institution");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Institution.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotDetailResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.LotDetailResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.MailingAddressesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.MailingAddressesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "NewAccountResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.NewAccountResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PendingOrderCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PendingOrderResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidActivityCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidActivityRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidActivityResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidDetailResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidDetailResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryInfo");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidSummaryInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidSummaryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.PrepaidSummaryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "StatementbyFormCategoryRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.StatementbyFormCategoryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "StatementbyFormCategoryResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.StatementbyFormCategoryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.Status.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryCollectionRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.SuccessorBeneficiaryCollectionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryCollectionResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.SuccessorBeneficiaryCollectionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.SuccessorBeneficiaryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.SuccessorBeneficiaryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "TaxFormResult");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.TaxFormResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateCustomFieldsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.UpdateCustomFieldsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateFavoriteInstitutionsRequest");
            cachedSerQNames.add(qName);
            cls = com.ws.gemini.wsdl.account.UpdateFavoriteInstitutionsRequest.class;
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

    public com.ws.gemini.wsdl.account.AccountInfoResult getAccountInfo(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, boolean isShareholder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAccountInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccountInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, new java.lang.Boolean(isShareholder)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AccountInfoResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AccountInfoResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AccountInfoResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.MailingAddressesResult getMailingAddress(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, org.apache.axis.types.UnsignedByte mailingAddressType, org.apache.axis.types.UnsignedByte useRegistrationAddress, boolean isShareholder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getMailingAddress");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getMailingAddress"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, mailingAddressType, useRegistrationAddress, new java.lang.Boolean(isShareholder)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.MailingAddressesResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.MailingAddressesResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.MailingAddressesResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Beneficiary529Result getBeneficiary529(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, boolean isShareholder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getBeneficiary529");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getBeneficiary529"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, new java.lang.Boolean(isShareholder)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Beneficiary529Result) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Beneficiary529Result) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Beneficiary529Result.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityCollectionResult getPlan529AccountBalanceActivityCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, java.lang.String beneficiarySSN, int holidayListId, boolean isShareholder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getPlan529AccountBalanceActivityCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPlan529AccountBalanceActivityCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, beneficiarySSN, new java.lang.Integer(holidayListId), new java.lang.Boolean(isShareholder)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.ActivityCollectionResult getActivityCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, short fundId, java.util.Calendar valuationDate, java.util.Calendar valuationBeginDate, boolean isShareholder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getActivityCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getActivityCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, new java.lang.Short(fundId), valuationDate, valuationBeginDate, new java.lang.Boolean(isShareholder)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.ActivityCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.ActivityCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.ActivityCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AuthorizedAccountCollectionResult getAuthorizedAccountCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAuthorizedAccountCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAuthorizedAccountCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AuthorizedAccountCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AuthorizedAccountCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AuthorizedAccountCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AccountResult getAccount(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAccount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AccountResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AccountResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AccountResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AuthorizedAccountsResult isAccountClosed(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/isAccountClosed");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isAccountClosed"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AuthorizedAccountsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AuthorizedAccountsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AuthorizedAccountsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult getPlan529AccountBalanceSummaryCollectionForAccount(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getPlan529AccountBalanceSummaryCollectionForAccount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPlan529AccountBalanceSummaryCollectionForAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult getPlan529AccountBalanceSummaryCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, java.util.Calendar valuationDate) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getPlan529AccountBalanceSummaryCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPlan529AccountBalanceSummaryCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, valuationDate});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.BalanceCollectionResult getBalanceCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, short fundId, java.util.Calendar valuationDate) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getBalanceCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getBalanceCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, new java.lang.Short(fundId), valuationDate});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.BalanceCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.BalanceCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.BalanceCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.BalanceCollectionResult getBalanceCollectionWithDateRange(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, short fundId, java.util.Calendar valuationDate, org.apache.axis.types.UnsignedByte displayCurrencyId, java.util.Calendar valuationBeginDate, org.apache.axis.types.UnsignedByte computeShareAmounts, org.apache.axis.types.UnsignedByte computePeriodDistributions, org.apache.axis.types.UnsignedByte computeDividendPayable, org.apache.axis.types.UnsignedByte computeAverageCostBasis, org.apache.axis.types.UnsignedByte computeReinvestmentSettings, org.apache.axis.types.UnsignedByte computeActivitySummary, org.apache.axis.types.UnsignedByte returnAllLinkedAccounts, org.apache.axis.types.UnsignedByte returnZeroBalanceSubAccounts, org.apache.axis.types.UnsignedByte valuationPriceCycleId, org.apache.axis.types.UnsignedByte valuationBeginPriceCycleId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getBalanceCollectionWithDateRange");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getBalanceCollectionWithDateRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, new java.lang.Short(fundId), valuationDate, displayCurrencyId, valuationBeginDate, computeShareAmounts, computePeriodDistributions, computeDividendPayable, computeAverageCostBasis, computeReinvestmentSettings, computeActivitySummary, returnAllLinkedAccounts, returnZeroBalanceSubAccounts, valuationPriceCycleId, valuationBeginPriceCycleId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.BalanceCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.BalanceCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.BalanceCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AchPayeeResult isAccountPayeeBeingUsed(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, org.apache.axis.types.UnsignedByte accountPayeeId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/isAccountPayeeBeingUsed");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "isAccountPayeeBeingUsed"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, accountPayeeId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AchPayeeResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AchPayeeResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AchPayeeResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AchPayeeCollectionResult getAchPayeeCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAchPayeeCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAchPayeeCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});
        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            System.out.println("Soap Request :"+_call.getMessageContext().getRequestMessage().getSOAPPartAsString());
            System.out.println("Soap Response :"+_call.getMessageContext().getResponseMessage().getSOAPPartAsString());

            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AchPayeeCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AchPayeeCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AchPayeeCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status addAchPayee(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AchPayeeRequest achPayee) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/addAchPayee");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAchPayee"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, achPayee});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status deleteAchPayee(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, org.apache.axis.types.UnsignedByte accountPayeeId, int achpayeeId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/deleteAchPayee");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "deleteAchPayee"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, accountPayeeId, new java.lang.Integer(achpayeeId)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.CheckWirePayeeCollectionResult getCheckWirePayeeCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getCheckWirePayeeCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getCheckWirePayeeCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.CheckWirePayeeCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.CheckWirePayeeCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.CheckWirePayeeCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AutomaticInvestmentsCollectionResult getAutomaticInvestmentCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAutomaticInvestmentCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAutomaticInvestmentCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AutomaticInvestmentsCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AutomaticInvestmentsCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AutomaticInvestmentsCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status addAutomaticInvestmentPlan(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AutomaticInvestmentRequest automaticInvestment, com.ws.gemini.wsdl.account.AchPayeeRequest achPayeeInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/addAutomaticInvestmentPlan");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAutomaticInvestmentPlan"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, automaticInvestment, achPayeeInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status modifyAutomaticInvestmentPlan(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AutomaticInvestmentRequest automaticInvestment, com.ws.gemini.wsdl.account.AchPayeeRequest achPayeeInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/modifyAutomaticInvestmentPlan");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "modifyAutomaticInvestmentPlan"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, automaticInvestment, achPayeeInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status deleteAutomaticInvestmentPlan(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, int automaticInvestmentID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/deleteAutomaticInvestmentPlan");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "deleteAutomaticInvestmentPlan"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, new java.lang.Integer(automaticInvestmentID)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status addAccountForCommonMailing(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AccountLinkagesRequest accountLinkages) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/addAccountForCommonMailing");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAccountForCommonMailing"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountLinkages});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.StatementbyFormCategoryResult getStatementByFormCategory(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.StatementbyFormCategoryRequest statementbyFormCategory) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getStatementByFormCategory");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getStatementByFormCategory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, statementbyFormCategory});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.StatementbyFormCategoryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.StatementbyFormCategoryResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.StatementbyFormCategoryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.StatementbyFormCategoryResult updateStatementByFormCategory(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.StatementbyFormCategoryRequest statementbyFormCategory) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateStatementByFormCategory");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateStatementByFormCategory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, statementbyFormCategory});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.StatementbyFormCategoryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.StatementbyFormCategoryResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.StatementbyFormCategoryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.MailingAddressesResult getMailingAddressForMailingAddressType(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, org.apache.axis.types.UnsignedByte mailingAddressType) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getMailingAddressForMailingAddressType");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getMailingAddressForMailingAddressType"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, mailingAddressType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.MailingAddressesResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.MailingAddressesResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.MailingAddressesResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateMailingAddresses(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber, com.ws.gemini.wsdl.account.MailingAddressesRequest mailingAddresses) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateMailingAddresses");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateMailingAddresses"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber, mailingAddresses});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateSuccessorBeneficiaries(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.SuccessorBeneficiaryRequest successorBeneficiary) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateSuccessorBeneficiaries");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateSuccessorBeneficiaries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, successorBeneficiary});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status addSuccessorBeneficiaries(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.SuccessorBeneficiaryRequest successorBeneficiary) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/addSuccessorBeneficiaries");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addSuccessorBeneficiaries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, successorBeneficiary});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateBeneficiary529(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.Beneficiary529Request beneficiary529) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateBeneficiary529");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateBeneficiary529"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, beneficiary529});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status addAccountMessages(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AccountMessageRequest accountMessage) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/addAccountMessages");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAccountMessages"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountMessage});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status addAccountCustomField(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AccountCustomFieldRequest accountCustomField) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/addAccountCustomField");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "addAccountCustomField"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountCustomField});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AssetAllocationModelWeightsResult getAssetAllocationModelWeights(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAssetAllocationModelWeights");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAssetAllocationModelWeights"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AssetAllocationModelWeightsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AssetAllocationModelWeightsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AssetAllocationModelWeightsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateAssetAllocationModel(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AssetAllocationModelsRequest assetAllocationModel) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateAssetAllocationModel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateAssetAllocationModel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, assetAllocationModel});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.NewAccountResult createNewAccount(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AccountRequest account) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/createNewAccount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "createNewAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, account});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.NewAccountResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.NewAccountResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.NewAccountResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AccountCustomFieldValuesResult getAccountForCustomFieldValue(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AccountCustomFieldValuesRequest accountCustomFieldValueRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAccountForCustomFieldValue");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccountForCustomFieldValue"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountCustomFieldValueRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AccountCustomFieldValuesResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AccountCustomFieldValuesResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AccountCustomFieldValuesResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.PendingOrderCollectionResult getPendingTradesForAccount(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getPendingTradesForAccount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPendingTradesForAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.PendingOrderCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.PendingOrderCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.PendingOrderCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateDeliveryModeForEdocs(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.EdocsDeliveryModeRequest edocDeliveryModeRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateDeliveryModeForEdocs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateDeliveryModeForEdocs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, edocDeliveryModeRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.EdocsCollectionResult getEdocs(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getEdocs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getEdocs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.EdocsCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.EdocsCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.EdocsCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.PrepaidActivityCollectionResult getPrepaidActivityCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.PrepaidActivityRequest prepaidActivityRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getPrepaidActivityCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPrepaidActivityCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, prepaidActivityRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.PrepaidActivityCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.PrepaidActivityCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.PrepaidActivityCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.PrepaidSummaryResult getPrepaidSummaryCollection(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.PrepaidSummaryRequest prepaidSummaryRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getPrepaidSummaryCollection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getPrepaidSummaryCollection"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, prepaidSummaryRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.PrepaidSummaryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.PrepaidSummaryResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.PrepaidSummaryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.FavoriteInstitutionsCollectionResult getFavoriteInstitutionsForBeneficiary(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getFavoriteInstitutionsForBeneficiary");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getFavoriteInstitutionsForBeneficiary"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.FavoriteInstitutionsCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.FavoriteInstitutionsCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.FavoriteInstitutionsCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateFavouriteInstitutionsForBeneficiary(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.UpdateFavoriteInstitutionsRequest updateFavoriteInstitutionsRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateFavouriteInstitutionsForBeneficiary");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateFavouriteInstitutionsForBeneficiary"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, updateFavoriteInstitutionsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.AccountCustomFieldsCollectionResult getAccountCustomFields(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getAccountCustomFields");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getAccountCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountNumber});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.AccountCustomFieldsCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.AccountCustomFieldsCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.AccountCustomFieldsCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateAccountCustomFields(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.UpdateCustomFieldsRequest updateCustomFieldsRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateAccountCustomFields");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateAccountCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, updateCustomFieldsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.CustomFieldListValuesCollectionResult getCustomFieldListValues(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, java.lang.String accountCustomFieldDefinition) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/getCustomFieldListValues");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "getCustomFieldListValues"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountCustomFieldDefinition});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.CustomFieldListValuesCollectionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.CustomFieldListValuesCollectionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.CustomFieldListValuesCollectionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.CDSCCalculatorResult calculateCDSCForAccount(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.CDSCCalculatorRequest cdscCalculatorRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/calculateCDSCForAccount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "calculateCDSCForAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, cdscCalculatorRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.CDSCCalculatorResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.CDSCCalculatorResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.CDSCCalculatorResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.ws.gemini.wsdl.account.Status updateAccountDividendSetup(com.ws.gemini.wsdl.account.AuthenticateLogin userLogin, com.ws.gemini.wsdl.account.AccountDividendSetupRequest accountDividendSetupRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://enfs.com/webservices/updateAccountDividendSetup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "updateAccountDividendSetup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userLogin, accountDividendSetupRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ws.gemini.wsdl.account.Status) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ws.gemini.wsdl.account.Status) org.apache.axis.utils.JavaUtils.convert(_resp, com.ws.gemini.wsdl.account.Status.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

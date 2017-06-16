/**
 * LoginServicesSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.login;

public interface LoginServicesSoap_PortType extends java.rmi.Remote {
    public com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult masterLogin(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.MasterWebuserResult updateMasterPassword(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String oldPassword, java.lang.String newPassword, java.lang.String verifyNewPassword) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult shareholderLogin(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.math.BigDecimal noOfUnsuccessfulLoginAttempt) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult updateShareholderPassword(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String securityAnswer, java.lang.String oldPassword, java.lang.String newPassword, java.lang.String verifyNewPassword) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult updateShareholderSecurityQuestionAnswer(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String currentPassword, java.lang.String currentSecurityAnswer, java.lang.String newSecurityQuestion, java.lang.String newSecurityAnswer) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult createShareholderWebUser(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String accountNumber, java.lang.String ssnorTin, java.lang.String zipCode, java.lang.String emailAddress, java.lang.String securityQuestion, java.lang.String securityAnswer, org.apache.axis.types.UnsignedByte accessLinkedAccounts) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult getWebUser(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult getWebUserForAccountSSNOrTINZipcode(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String accountNumber, java.lang.String ssnorTin, java.lang.String zipCode) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult getWebUserForUserIdSSNOrTIN(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String ssnorTin) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.WebUserResult updatePasswordWithNoAuthentication(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, java.lang.String ssnorTin, java.lang.String securityQuestion, java.lang.String securityAnswer, java.lang.String newPassword, java.lang.String verifyPassword) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.Status updateWebUser(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, com.invessence.ws.provider.gemini.wsdl.login.WebUserRequest webUser) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.Status updateWebUserCustomValues(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin, com.invessence.ws.provider.gemini.wsdl.login.WebUserCustomValuesRequest webUser) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.ExistResult isWebUserExists(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateLogin userLogin) throws java.rmi.RemoteException;
    public com.invessence.ws.provider.gemini.wsdl.login.Status systemUserLogin(com.invessence.ws.provider.gemini.wsdl.login.AuthenticateSystemUserLogin userLogin) throws java.rmi.RemoteException;
}

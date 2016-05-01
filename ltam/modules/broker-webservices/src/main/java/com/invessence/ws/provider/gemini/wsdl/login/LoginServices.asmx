<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://enfs.com/webservices/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://enfs.com/webservices/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://enfs.com/webservices/">
      <s:element name="masterLogin">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AuthenticateLogin">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FundGroupName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AllowableShareClassList" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="masterLoginResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="masterLoginResult" type="tns:MasterWebuserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="MasterWebuserResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="UserType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="MasterFirmNumber" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="BranchNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountRepNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="UserStatus" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfUnsuccessfulLoginAttempts" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LastUpdatedDateTime" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="EmailAddress" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SecretQuestion" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SecretAnswer" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="Status">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ErrorCode" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorMessage" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateMasterPassword">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="oldPassword" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="newPassword" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="verifyNewPassword" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateMasterPasswordResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateMasterPasswordResult" type="tns:MasterWebuserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="shareholderLogin">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="noOfUnsuccessfulLoginAttempt" type="s:decimal" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="shareholderLoginResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="shareholderLoginResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="WebUserResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="VerificationStatus" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="VerificationCode" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="UserType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FirmNumber" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="FirmBranchNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountRepId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="DefaultWebPage" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccessLinkedAccounts" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="ProfileId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="UserStatus" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfUnsuccessfulLoginAttempts" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LastUpdatedDateTime" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue1" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue2" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue3" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue4" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue5" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="EmailAddress" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SecretQuestion" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SecretAnswer" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SsnOrTIN" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ZipCode" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Counts" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="FirmRepNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateShareholderPassword">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="securityAnswer" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="oldPassword" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="newPassword" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="verifyNewPassword" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateShareholderPasswordResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateShareholderPasswordResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateShareholderSecurityQuestionAnswer">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="currentPassword" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="currentSecurityAnswer" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="newSecurityQuestion" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="newSecurityAnswer" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateShareholderSecurityQuestionAnswerResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateShareholderSecurityQuestionAnswerResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="createShareholderWebUser">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="ssnorTin" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="zipCode" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="emailAddress" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="securityQuestion" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="securityAnswer" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="accessLinkedAccounts" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="createShareholderWebUserResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="createShareholderWebUserResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getWebUser">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getWebUserResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getWebUserResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getWebUserForAccountSSNOrTINZipcode">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="ssnorTin" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="zipCode" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getWebUserForAccountSSNOrTINZipcodeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getWebUserForAccountSSNOrTINZipcodeResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getWebUserForUserIdSSNOrTIN">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="ssnorTin" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getWebUserForUserIdSSNOrTINResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getWebUserForUserIdSSNOrTINResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updatePasswordWithNoAuthentication">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="ssnorTin" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="securityQuestion" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="securityAnswer" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="newPassword" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="verifyPassword" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updatePasswordWithNoAuthenticationResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updatePasswordWithNoAuthenticationResult" type="tns:WebUserResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateWebUser">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="0" maxOccurs="1" name="webUser" type="tns:WebUserRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="WebUserRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="EMailAddress" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateWebUserResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateWebUserResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateWebUserCustomValues">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="0" maxOccurs="1" name="webUser" type="tns:WebUserCustomValuesRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="WebUserCustomValuesRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue1" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue2" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue3" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue4" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomValue5" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateWebUserCustomValuesResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateWebUserCustomValuesResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="isWebUserExists">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="isWebUserExistsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="isWebUserExistsResult" type="tns:ExistResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ExistResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="IsExist" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="systemUserLogin">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateSystemUserLogin" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AuthenticateSystemUserLogin">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FundGroupName" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="systemUserLoginResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="systemUserLoginResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="masterLoginSoapIn">
    <wsdl:part name="parameters" element="tns:masterLogin" />
  </wsdl:message>
  <wsdl:message name="masterLoginSoapOut">
    <wsdl:part name="parameters" element="tns:masterLoginResponse" />
  </wsdl:message>
  <wsdl:message name="updateMasterPasswordSoapIn">
    <wsdl:part name="parameters" element="tns:updateMasterPassword" />
  </wsdl:message>
  <wsdl:message name="updateMasterPasswordSoapOut">
    <wsdl:part name="parameters" element="tns:updateMasterPasswordResponse" />
  </wsdl:message>
  <wsdl:message name="shareholderLoginSoapIn">
    <wsdl:part name="parameters" element="tns:shareholderLogin" />
  </wsdl:message>
  <wsdl:message name="shareholderLoginSoapOut">
    <wsdl:part name="parameters" element="tns:shareholderLoginResponse" />
  </wsdl:message>
  <wsdl:message name="updateShareholderPasswordSoapIn">
    <wsdl:part name="parameters" element="tns:updateShareholderPassword" />
  </wsdl:message>
  <wsdl:message name="updateShareholderPasswordSoapOut">
    <wsdl:part name="parameters" element="tns:updateShareholderPasswordResponse" />
  </wsdl:message>
  <wsdl:message name="updateShareholderSecurityQuestionAnswerSoapIn">
    <wsdl:part name="parameters" element="tns:updateShareholderSecurityQuestionAnswer" />
  </wsdl:message>
  <wsdl:message name="updateShareholderSecurityQuestionAnswerSoapOut">
    <wsdl:part name="parameters" element="tns:updateShareholderSecurityQuestionAnswerResponse" />
  </wsdl:message>
  <wsdl:message name="createShareholderWebUserSoapIn">
    <wsdl:part name="parameters" element="tns:createShareholderWebUser" />
  </wsdl:message>
  <wsdl:message name="createShareholderWebUserSoapOut">
    <wsdl:part name="parameters" element="tns:createShareholderWebUserResponse" />
  </wsdl:message>
  <wsdl:message name="getWebUserSoapIn">
    <wsdl:part name="parameters" element="tns:getWebUser" />
  </wsdl:message>
  <wsdl:message name="getWebUserSoapOut">
    <wsdl:part name="parameters" element="tns:getWebUserResponse" />
  </wsdl:message>
  <wsdl:message name="getWebUserForAccountSSNOrTINZipcodeSoapIn">
    <wsdl:part name="parameters" element="tns:getWebUserForAccountSSNOrTINZipcode" />
  </wsdl:message>
  <wsdl:message name="getWebUserForAccountSSNOrTINZipcodeSoapOut">
    <wsdl:part name="parameters" element="tns:getWebUserForAccountSSNOrTINZipcodeResponse" />
  </wsdl:message>
  <wsdl:message name="getWebUserForUserIdSSNOrTINSoapIn">
    <wsdl:part name="parameters" element="tns:getWebUserForUserIdSSNOrTIN" />
  </wsdl:message>
  <wsdl:message name="getWebUserForUserIdSSNOrTINSoapOut">
    <wsdl:part name="parameters" element="tns:getWebUserForUserIdSSNOrTINResponse" />
  </wsdl:message>
  <wsdl:message name="updatePasswordWithNoAuthenticationSoapIn">
    <wsdl:part name="parameters" element="tns:updatePasswordWithNoAuthentication" />
  </wsdl:message>
  <wsdl:message name="updatePasswordWithNoAuthenticationSoapOut">
    <wsdl:part name="parameters" element="tns:updatePasswordWithNoAuthenticationResponse" />
  </wsdl:message>
  <wsdl:message name="updateWebUserSoapIn">
    <wsdl:part name="parameters" element="tns:updateWebUser" />
  </wsdl:message>
  <wsdl:message name="updateWebUserSoapOut">
    <wsdl:part name="parameters" element="tns:updateWebUserResponse" />
  </wsdl:message>
  <wsdl:message name="updateWebUserCustomValuesSoapIn">
    <wsdl:part name="parameters" element="tns:updateWebUserCustomValues" />
  </wsdl:message>
  <wsdl:message name="updateWebUserCustomValuesSoapOut">
    <wsdl:part name="parameters" element="tns:updateWebUserCustomValuesResponse" />
  </wsdl:message>
  <wsdl:message name="isWebUserExistsSoapIn">
    <wsdl:part name="parameters" element="tns:isWebUserExists" />
  </wsdl:message>
  <wsdl:message name="isWebUserExistsSoapOut">
    <wsdl:part name="parameters" element="tns:isWebUserExistsResponse" />
  </wsdl:message>
  <wsdl:message name="systemUserLoginSoapIn">
    <wsdl:part name="parameters" element="tns:systemUserLogin" />
  </wsdl:message>
  <wsdl:message name="systemUserLoginSoapOut">
    <wsdl:part name="parameters" element="tns:systemUserLoginResponse" />
  </wsdl:message>
  <wsdl:portType name="LoginServicesSoap">
    <wsdl:operation name="masterLogin">
      <wsdl:input message="tns:masterLoginSoapIn" />
      <wsdl:output message="tns:masterLoginSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateMasterPassword">
      <wsdl:input message="tns:updateMasterPasswordSoapIn" />
      <wsdl:output message="tns:updateMasterPasswordSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="shareholderLogin">
      <wsdl:input message="tns:shareholderLoginSoapIn" />
      <wsdl:output message="tns:shareholderLoginSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateShareholderPassword">
      <wsdl:input message="tns:updateShareholderPasswordSoapIn" />
      <wsdl:output message="tns:updateShareholderPasswordSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateShareholderSecurityQuestionAnswer">
      <wsdl:input message="tns:updateShareholderSecurityQuestionAnswerSoapIn" />
      <wsdl:output message="tns:updateShareholderSecurityQuestionAnswerSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="createShareholderWebUser">
      <wsdl:input message="tns:createShareholderWebUserSoapIn" />
      <wsdl:output message="tns:createShareholderWebUserSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getWebUser">
      <wsdl:input message="tns:getWebUserSoapIn" />
      <wsdl:output message="tns:getWebUserSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getWebUserForAccountSSNOrTINZipcode">
      <wsdl:input message="tns:getWebUserForAccountSSNOrTINZipcodeSoapIn" />
      <wsdl:output message="tns:getWebUserForAccountSSNOrTINZipcodeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getWebUserForUserIdSSNOrTIN">
      <wsdl:input message="tns:getWebUserForUserIdSSNOrTINSoapIn" />
      <wsdl:output message="tns:getWebUserForUserIdSSNOrTINSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updatePasswordWithNoAuthentication">
      <wsdl:input message="tns:updatePasswordWithNoAuthenticationSoapIn" />
      <wsdl:output message="tns:updatePasswordWithNoAuthenticationSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateWebUser">
      <wsdl:input message="tns:updateWebUserSoapIn" />
      <wsdl:output message="tns:updateWebUserSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateWebUserCustomValues">
      <wsdl:input message="tns:updateWebUserCustomValuesSoapIn" />
      <wsdl:output message="tns:updateWebUserCustomValuesSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="isWebUserExists">
      <wsdl:input message="tns:isWebUserExistsSoapIn" />
      <wsdl:output message="tns:isWebUserExistsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="systemUserLogin">
      <wsdl:input message="tns:systemUserLoginSoapIn" />
      <wsdl:output message="tns:systemUserLoginSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="LoginServicesSoap" type="tns:LoginServicesSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="masterLogin">
      <soap:operation soapAction="http://enfs.com/webservices/masterLogin" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateMasterPassword">
      <soap:operation soapAction="http://enfs.com/webservices/updateMasterPassword" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="shareholderLogin">
      <soap:operation soapAction="http://enfs.com/webservices/shareholderLogin" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateShareholderPassword">
      <soap:operation soapAction="http://enfs.com/webservices/updateShareholderPassword" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateShareholderSecurityQuestionAnswer">
      <soap:operation soapAction="http://enfs.com/webservices/updateShareholderSecurityQuestionAnswer" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="createShareholderWebUser">
      <soap:operation soapAction="http://enfs.com/webservices/createShareholderWebUser" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getWebUser">
      <soap:operation soapAction="http://enfs.com/webservices/getWebUser" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getWebUserForAccountSSNOrTINZipcode">
      <soap:operation soapAction="http://enfs.com/webservices/getWebUserForAccountSSNOrTINZipcode" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getWebUserForUserIdSSNOrTIN">
      <soap:operation soapAction="http://enfs.com/webservices/getWebUserForUserIdSSNOrTIN" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updatePasswordWithNoAuthentication">
      <soap:operation soapAction="http://enfs.com/webservices/updatePasswordWithNoAuthentication" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateWebUser">
      <soap:operation soapAction="http://enfs.com/webservices/updateWebUser" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateWebUserCustomValues">
      <soap:operation soapAction="http://enfs.com/webservices/updateWebUserCustomValues" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="isWebUserExists">
      <soap:operation soapAction="http://enfs.com/webservices/isWebUserExists" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="systemUserLogin">
      <soap:operation soapAction="http://enfs.com/webservices/systemUserLogin" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="LoginServicesSoap12" type="tns:LoginServicesSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="masterLogin">
      <soap12:operation soapAction="http://enfs.com/webservices/masterLogin" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateMasterPassword">
      <soap12:operation soapAction="http://enfs.com/webservices/updateMasterPassword" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="shareholderLogin">
      <soap12:operation soapAction="http://enfs.com/webservices/shareholderLogin" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateShareholderPassword">
      <soap12:operation soapAction="http://enfs.com/webservices/updateShareholderPassword" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateShareholderSecurityQuestionAnswer">
      <soap12:operation soapAction="http://enfs.com/webservices/updateShareholderSecurityQuestionAnswer" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="createShareholderWebUser">
      <soap12:operation soapAction="http://enfs.com/webservices/createShareholderWebUser" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getWebUser">
      <soap12:operation soapAction="http://enfs.com/webservices/getWebUser" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getWebUserForAccountSSNOrTINZipcode">
      <soap12:operation soapAction="http://enfs.com/webservices/getWebUserForAccountSSNOrTINZipcode" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getWebUserForUserIdSSNOrTIN">
      <soap12:operation soapAction="http://enfs.com/webservices/getWebUserForUserIdSSNOrTIN" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updatePasswordWithNoAuthentication">
      <soap12:operation soapAction="http://enfs.com/webservices/updatePasswordWithNoAuthentication" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateWebUser">
      <soap12:operation soapAction="http://enfs.com/webservices/updateWebUser" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateWebUserCustomValues">
      <soap12:operation soapAction="http://enfs.com/webservices/updateWebUserCustomValues" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="isWebUserExists">
      <soap12:operation soapAction="http://enfs.com/webservices/isWebUserExists" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="systemUserLogin">
      <soap12:operation soapAction="http://enfs.com/webservices/systemUserLogin" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="LoginServices">
    <wsdl:port name="LoginServicesSoap" binding="tns:LoginServicesSoap">
      <soap:address location="http://testpawebservices.geminifund.com/LoginServices.asmx" />
    </wsdl:port>
    <wsdl:port name="LoginServicesSoap12" binding="tns:LoginServicesSoap12">
      <soap12:address location="http://testpawebservices.geminifund.com/LoginServices.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>
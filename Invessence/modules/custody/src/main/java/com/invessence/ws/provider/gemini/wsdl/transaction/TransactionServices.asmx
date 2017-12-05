<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://enfs.com/webservices/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://enfs.com/webservices/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://enfs.com/webservices/">
      <s:element name="createTransaction">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="transactionInfo" type="tns:TransactionInfo" />
            <s:element minOccurs="0" maxOccurs="1" name="fundInfo" type="tns:ArrayOfFundInformation" />
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
      <s:complexType name="TransactionInfo">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="MasterTransactionType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MoneyTransactionType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="MasterTransactionSource" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="MoneyAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CurrencyId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="RetirementIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DealerTransactionIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountPayeeId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AssetAllocationTradeIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="EndResultExchangeInd" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="BankAccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ExcludeAccountsList" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ReturnReadBackInfo" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="ReturnMessagesInfo" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="NumberOfMasterTransactionLines" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="UserId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="TradeDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="AddToExcludedAccounts" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="PriceCycleId" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="NameOnAccount" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankRoutingNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankAccount" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BankAccountType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="IsAmountSpecified" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLines" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AddressLines" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="UpdateAgeBasedModelIdInd" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="InvestmentOption" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AgeBasedModelId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="AmountTypeIndicator" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="GiftReferenceId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AutoDetermineFunds" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="WebUserId" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="WithholdingIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="WithholdingPercentOrAmount" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="WithholdingAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="WithholdingPercent" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfFundInformation">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="FundInformation" type="tns:FundInformation" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="FundInformation">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="AmountType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="Amount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FromToLineIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CBDMIdForCoveredShares" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="CBDMIdForNonCoveredShares" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="CBALotInfo" type="tns:ArrayOfCBALotInformation" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfCBALotInformation">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="CBALotInformation" type="tns:CBALotInformation" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="CBALotInformation">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="LotId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="ShareAmount" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:element name="createTransactionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="createTransactionResult" type="tns:TransactionCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="TransactionCollectionResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="MasterTransactionId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="MinConfirmationNumber" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="MaxConfirmationNumber" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfInformationLines" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfWarnings" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfOverrides" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfErrors" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
          <s:element minOccurs="0" maxOccurs="1" name="Transaction" type="tns:ArrayOfTransactionResult" />
          <s:element minOccurs="0" maxOccurs="1" name="TransactionMessageCollection" type="tns:ArrayOfTransactionMessageResult" />
          <s:element minOccurs="1" maxOccurs="1" name="MinConfirmNumberCrDateTime" type="s:dateTime" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="Status">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ErrorCode" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorMessage" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfTransactionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="TransactionResult" nillable="true" type="tns:TransactionResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TransactionResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="FundTransactionType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="TradeDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="PriceCycleId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalGrossAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalFeeAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalWithholdngAmount" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfTransactionMessageResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="TransactionMessageResult" nillable="true" type="tns:TransactionMessageResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TransactionMessageResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="TransactionId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FundTransactionLineNo" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="TransactionMessageId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="TransactionMessage" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="SelectedMessageType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FundShortName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomTransactionMessageString" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="getAsofDate">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="holidayListType" type="s:short" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAsofDateResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAsofDateResult" type="tns:AsofDateResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AsofDateResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="PreviousBusinessDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="LatestNAVDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="createTransactionSoapIn">
    <wsdl:part name="parameters" element="tns:createTransaction" />
  </wsdl:message>
  <wsdl:message name="createTransactionSoapOut">
    <wsdl:part name="parameters" element="tns:createTransactionResponse" />
  </wsdl:message>
  <wsdl:message name="getAsofDateSoapIn">
    <wsdl:part name="parameters" element="tns:getAsofDate" />
  </wsdl:message>
  <wsdl:message name="getAsofDateSoapOut">
    <wsdl:part name="parameters" element="tns:getAsofDateResponse" />
  </wsdl:message>
  <wsdl:portType name="TransactionServicesSoap">
    <wsdl:operation name="createTransaction">
      <wsdl:input message="tns:createTransactionSoapIn" />
      <wsdl:output message="tns:createTransactionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAsofDate">
      <wsdl:input message="tns:getAsofDateSoapIn" />
      <wsdl:output message="tns:getAsofDateSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="TransactionServicesSoap" type="tns:TransactionServicesSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="createTransaction">
      <soap:operation soapAction="http://enfs.com/webservices/createTransaction" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAsofDate">
      <soap:operation soapAction="http://enfs.com/webservices/getAsofDate" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="TransactionServicesSoap12" type="tns:TransactionServicesSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="createTransaction">
      <soap12:operation soapAction="http://enfs.com/webservices/createTransaction" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAsofDate">
      <soap12:operation soapAction="http://enfs.com/webservices/getAsofDate" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="TransactionServices">
    <wsdl:port name="TransactionServicesSoap" binding="tns:TransactionServicesSoap">
      <soap:address location="http://testpawebservices.geminifund.com/TransactionServices.asmx" />
    </wsdl:port>
    <wsdl:port name="TransactionServicesSoap12" binding="tns:TransactionServicesSoap12">
      <soap12:address location="http://testpawebservices.geminifund.com/TransactionServices.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>
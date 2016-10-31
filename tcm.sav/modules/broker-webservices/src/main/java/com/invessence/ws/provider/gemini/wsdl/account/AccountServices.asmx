<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://enfs.com/webservices/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://enfs.com/webservices/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://enfs.com/webservices/">
      <s:element name="getAccountInfo">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="isShareholder" type="s:boolean" />
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
      <s:element name="getAccountInfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAccountInfoResult" type="tns:AccountInfoResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountInfoResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="OldAccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomerName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccounTypeName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BlueskyState" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="SsnOrTINIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="SsnOrTIN" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="BackupWithholdingIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountRepId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="ShareClassId" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountAdvisorId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="TradingEntity" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryOfOriginParm" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="BlueskyExemptIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="JointDateOfBirth" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="JointSSNOrTIN" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="NetworkControlIndicator" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="DealerAccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountClosingDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="PortfolioAllocatorIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="Status">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ErrorCode" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorMessage" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="getMailingAddress">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="mailingAddressType" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="useRegistrationAddress" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="isShareholder" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getMailingAddressResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getMailingAddressResult" type="tns:MailingAddressesResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="MailingAddressesResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLines" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AddressLines" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="PostalZip" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryCode" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="VoicePhone" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AltPhone" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FaxPhone" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="EmailAddress" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="EntityIdentifier" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="getBeneficiary529">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="isShareholder" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getBeneficiary529Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getBeneficiary529Result" type="tns:Beneficiary529Result" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="Beneficiary529Result">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiaryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="IsSsn" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="SsnOrTin" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryOfOriginTaxResidence" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryOfCitizenship" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="Relationship" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CollegeEnteringAge" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CollegeEnteringDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="Gender" type="tns:GenderEnum" />
          <s:element minOccurs="1" maxOccurs="1" name="IsAddressSameAsAccountOwner" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLine1" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLine2" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AddressLine" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="City" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="State" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="Zip" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryCode" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="InvestmentOption" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="LastInvestmentOptionChangeDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="Portfolio" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AgeBasedModelId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfAvaliableInvestmentChanges" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="InvestmentOptionType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="TargetPortfolioFundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="ExpectedEnrollmentYearId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="ExpectedEnrollmentYearDescription" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiaryFullName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AgeBasedModelName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="PortfolioAllocatorIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="MailingAddress" type="tns:MailingAddressesResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:simpleType name="GenderEnum">
        <s:restriction base="s:string">
          <s:enumeration value="Null" />
          <s:enumeration value="Male" />
          <s:enumeration value="Female" />
        </s:restriction>
      </s:simpleType>
      <s:element name="getPlan529AccountBalanceActivityCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="beneficiarySSN" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="holidayListId" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="isShareholder" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getPlan529AccountBalanceActivityCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getPlan529AccountBalanceActivityCollectionResult" type="tns:Plan529AccountBalanceActivityCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="Plan529AccountBalanceActivityCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountBalanceActivity" type="tns:ArrayOfPlan529AccountBalanceActivityResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfPlan529AccountBalanceActivityResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="Plan529AccountBalanceActivityResult" nillable="true" type="tns:Plan529AccountBalanceActivityResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="Plan529AccountBalanceActivityResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FundShortName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiarySSN" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiaryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CurrentBalance" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="NAV" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="Rate" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="InfoDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="PrincipalProtectionAmount" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:element name="getActivityCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="fundId" type="s:short" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationDate" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationBeginDate" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="isShareholder" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getActivityCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getActivityCollectionResult" type="tns:ActivityCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ActivityCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
          <s:element minOccurs="0" maxOccurs="1" name="Activity" type="tns:ArrayOfActivityResult" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalRows" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfActivityResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="ActivityResult" nillable="true" type="tns:ActivityResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ActivityResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FundName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TransactionId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FundTransactionLineno" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="TradeDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="PostingDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="FundTransactionType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="GrossAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="WithholdingTaxAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="NetAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="PricePerShare" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ShareAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ShareChangeIndicator" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="FromToTransactionLineno" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="SharesOrAmountIndicator" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FromToAccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FromToFundid" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FundTransactionSource" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="RetirementIndicator" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BookPhysicalSharesIndicator" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountStatementLines" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DividendRate" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="FundTransactionStatus" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Memo" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="StateTaxAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FeeType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="FeeAmount" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="LoadIndicator" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DealerCommissionAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalCommissionAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FxGrossAmount" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="CurrencyName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FxRate" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="FundBaseCurrency" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Rate" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="MaturityDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="AmortizedValue" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="TransactionDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="SignedGrossAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="SignedShareAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CummulativeSharesOwned" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:element name="getAuthorizedAccountCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAuthorizedAccountCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAuthorizedAccountCollectionResult" type="tns:AuthorizedAccountCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AuthorizedAccountCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AuthorizedAccounts" type="tns:ArrayOfAuthorizedAccountsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAuthorizedAccountsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AuthorizedAccountsResult" nillable="true" type="tns:AuthorizedAccountsResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AuthorizedAccountsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="DealerAccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomerName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountTypeName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SSNOrTIN" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="SSNOrTINIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="GroupAccountIndicator" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="WebAccessIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountTaxReportingIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiaryName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Portfolio" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ShareClassId" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountClosingDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="PortfolioAllocatorIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="BalanceCollection" type="tns:BalanceCollectionResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ActivityCollection" type="tns:ArrayOfActivityResult" />
          <s:element minOccurs="0" maxOccurs="1" name="FundForRedemptionCollection" type="tns:ArrayOfFundForRedemptionResult" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountInfo" type="tns:AccountInfoResult" />
          <s:element minOccurs="0" maxOccurs="1" name="TaxFormCollection" type="tns:ArrayOfTaxFormResult" />
          <s:element minOccurs="0" maxOccurs="1" name="Beneficiary529" type="tns:Beneficiary529Result" />
          <s:element minOccurs="0" maxOccurs="1" name="AssetAllocationModels" type="tns:AssetAllocationModelsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="BalanceCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Balance" type="tns:ArrayOfBalanceResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfBalanceResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="BalanceResult" nillable="true" type="tns:BalanceResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="BalanceResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FundName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FundShortName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="InceptionDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="BeginningTotalShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="BeginningNAVPricePerShare" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="PostedSettledShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="UnsettledShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalValue" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="YTDIncomeDividend" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="YTDSTCG" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="YTDLTCG" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="YTDMTCG" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="YTDReturnofCapital" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="MarketValue" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalPhysicalShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="NavPricePerShare" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="PriceDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomerName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="PriceCycleId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="ReinvestDividend" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="ReinvestSTCG" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="ReinvestLTCG" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="FundUrl" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="RedemptionAvailableShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ExchangeAvailableShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="RedemptionAvailableBalance" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ExchangeAvailableBalance" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LastCBAComputedShareAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LastCostbasisComputedDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="CBDMIdForCoveredShares" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="CBDMNameForCoveredShares" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CBDMIdForNonCoveredShares" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="CBDMNameForNonCoveredShares" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfFundForRedemptionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="FundForRedemptionResult" nillable="true" type="tns:FundForRedemptionResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="FundForRedemptionResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FundName" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfTaxFormResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="TaxFormResult" nillable="true" type="tns:TaxFormResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TaxFormResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FormName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FormId" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AssetAllocationModelsResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ModelId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ModelName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ModelDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ModelScope" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="Status" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="RebalanceOnPurchasesFlag" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="RebalanceOnRedemptionsFlag" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="AllocationPercentageTolerance" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfFundsInModel" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AgeBasedModelId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="InvestmentOption" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="UpdateBeneficiaryAgeBasedModel" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="AssetAllocationFunds" type="tns:ArrayOfAssetAllocationFundsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountAssetAllocationModelWeightsCollection" type="tns:ArrayOfAccountAssetAllocationModelWeightsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="AgeBasedModelName" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAssetAllocationFundsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AssetAllocationFundsResult" nillable="true" type="tns:AssetAllocationFundsResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AssetAllocationFundsResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="AssetAllocationPercentage" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAccountAssetAllocationModelWeightsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AccountAssetAllocationModelWeightsResult" nillable="true" type="tns:AccountAssetAllocationModelWeightsResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AccountAssetAllocationModelWeightsResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ModelId" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="Fundid" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="AllocationPercentage" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="FundName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="getAccount">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAccountResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAccountResult" type="tns:AccountResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomerName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="IsBlueSkyState" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="BlueSkyState" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="IsSsn" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="SsnOrTin" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="BackupWithholdingIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountRepId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="ShareClassId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountAdvisorId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="TradingEntity" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryOfOriginParm" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="IsBlueSkyExempt" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="JointDateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="JointSsnOrTin" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Gender" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PortfolioAllocatorIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountMessages" type="tns:AccountMessageCollectionResult" />
          <s:element minOccurs="0" maxOccurs="1" name="AssetAllocationModel" type="tns:AssetAllocationModelsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="AutomaticInvestments" type="tns:AutomaticInvestmentResult" />
          <s:element minOccurs="0" maxOccurs="1" name="BankAccounts" type="tns:AchPayeeCollectionResult" />
          <s:element minOccurs="0" maxOccurs="1" name="Beneficiary" type="tns:Beneficiary529Result" />
          <s:element minOccurs="0" maxOccurs="1" name="MailingAddress" type="tns:MailingAddressesResult" />
          <s:element minOccurs="0" maxOccurs="1" name="Successors" type="tns:SuccessorBeneficiaryCollectionResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AccountMessageCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountMessage" type="tns:ArrayOfAccountMessageResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAccountMessageResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AccountMessageResult" nillable="true" type="tns:AccountMessageResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AccountMessageResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="MessageId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MessageTypeCode" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="MessageDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CreationDateTime" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="CreatedByUserId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="WebUserId" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AutomaticInvestmentResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="StartDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="FinalProcessingDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="SchedulerFrequency" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="FrequencyText" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="StartMonth" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DateQualifierForDay1" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DateQualifierForDay2" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DayOfTheMonth1" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DayOfTheMonth2" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="MonthIndicators" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountPayeeId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AutomaticInvestmentId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfAutomaticInvestmentLines" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CurrencyId" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="Memo" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MonthlySchedulerId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AchPayeeId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="UserId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="BankName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankRoutingNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundSelectionIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="BankAccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="NameOnAccount" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BankAccountType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PrenotificationDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="SendPrenotification" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AIPAmount" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="AIPText" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AutomaticInvestmentStatus" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="Fundid" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AIPFunds" type="tns:ArrayOfAIPFundInformationResult" />
          <s:element minOccurs="0" maxOccurs="1" name="AutomaticInvestmentLines" type="tns:ArrayOfAutomaticInvestmentLinesResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAIPFundInformationResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AIPFundInformationResult" nillable="true" type="tns:AIPFundInformationResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AIPFundInformationResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="MoneyAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="RetirementIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="SettlementAmountIndicator" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAutomaticInvestmentLinesResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AutomaticInvestmentLinesResult" nillable="true" type="tns:AutomaticInvestmentLinesResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AutomaticInvestmentLinesResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="AutomaticInvestmentId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AutomaticInvestmentLineno" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="Fundid" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="MoneyAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="RetirementIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountStatementLines" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="LoadIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="NAVReasonCode" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="SettlementAmountIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="RedemptionFeeIndicatorId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="FeeType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="FeeAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FeeComputationMethodIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="FeePercent" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AchPayeeCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AchPayee" type="tns:ArrayOfAchPayeeResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAchPayeeResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AchPayeeResult" nillable="true" type="tns:AchPayeeResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AchPayeeResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AchPayeeId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="BankName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankRoutingNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankAccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="NameOnAccount" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BankAccountType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PreferredCurrencyId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PreNotificationDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountPayeeId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PowerAgentUserId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="IsAccountPayeeBeingUsed" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="SuccessorBeneficiaryCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="SuccessorBeneficiary" type="tns:ArrayOfSuccessorBeneficiaryResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfSuccessorBeneficiaryResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="SuccessorBeneficiaryResult" nillable="true" type="tns:SuccessorBeneficiaryResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="SuccessorBeneficiaryResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="SSNOrTIN" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BeneficiaryType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="BeneficiaryStatus" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PrimaryBeneficiaryIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PrimaryBeneIndicator" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="PercentAssigned" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="DateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="DateAssigned" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="DateDropped" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="MailingAddresses" type="tns:MailingAddressesResult" />
        </s:sequence>
      </s:complexType>
      <s:element name="isAccountClosed">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="isAccountClosedResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="isAccountClosedResult" type="tns:AuthorizedAccountsResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getPlan529AccountBalanceSummaryCollectionForAccount">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getPlan529AccountBalanceSummaryCollectionForAccountResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getPlan529AccountBalanceSummaryCollectionForAccountResult" type="tns:Plan529AccountBalanceSummaryCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="Plan529AccountBalanceSummaryCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Plan529AccountBalanceSummary" type="tns:ArrayOfPlan529AccountBalanceSummaryResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfPlan529AccountBalanceSummaryResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="Plan529AccountBalanceSummaryResult" nillable="true" type="tns:Plan529AccountBalanceSummaryResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="Plan529AccountBalanceSummaryResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiaryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BegOfYearBalance" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="YTDContributions" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="YTDWithdrawals" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CurrentBalance" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ChangeInValue" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiarySSN" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="LoggedOnUserAccount" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="IRR" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LTDContributions" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LTDWithdrawals" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="PortfolioAllocatorIndicator" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="getPlan529AccountBalanceSummaryCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationDate" type="s:dateTime" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getPlan529AccountBalanceSummaryCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getPlan529AccountBalanceSummaryCollectionResult" type="tns:Plan529AccountBalanceSummaryCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getBalanceCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="fundId" type="s:short" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationDate" type="s:dateTime" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getBalanceCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getBalanceCollectionResult" type="tns:BalanceCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getBalanceCollectionWithDateRange">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="fundId" type="s:short" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationDate" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="displayCurrencyId" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationBeginDate" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="computeShareAmounts" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="computePeriodDistributions" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="computeDividendPayable" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="computeAverageCostBasis" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="computeReinvestmentSettings" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="computeActivitySummary" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="returnAllLinkedAccounts" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="returnZeroBalanceSubAccounts" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationPriceCycleId" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="valuationBeginPriceCycleId" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getBalanceCollectionWithDateRangeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getBalanceCollectionWithDateRangeResult" type="tns:BalanceCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="isAccountPayeeBeingUsed">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="0" maxOccurs="1" name="accountNumber" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="accountPayeeId" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="isAccountPayeeBeingUsedResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="isAccountPayeeBeingUsedResult" type="tns:AchPayeeResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAchPayeeCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="0" maxOccurs="1" name="accountNumber" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAchPayeeCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAchPayeeCollectionResult" type="tns:AchPayeeCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="addAchPayee">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="0" maxOccurs="1" name="achPayee" type="tns:AchPayeeRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AchPayeeRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AchPayeeId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="BankName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankRoutingNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BankAccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="NameOnAccount" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BankAccountType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountPayeeId" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="addAchPayeeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="addAchPayeeResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="deleteAchPayee">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="accountPayeeId" type="s:unsignedByte" />
            <s:element minOccurs="1" maxOccurs="1" name="achpayeeId" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="deleteAchPayeeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="deleteAchPayeeResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getCheckWirePayeeCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="0" maxOccurs="1" name="accountNumber" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getCheckWirePayeeCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getCheckWirePayeeCollectionResult" type="tns:CheckWirePayeeCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="CheckWirePayeeCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="CheckWirePayee" type="tns:ArrayOfCheckWirePayeeResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfCheckWirePayeeResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="CheckWirePayeeResult" nillable="true" type="tns:CheckWirePayeeResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="CheckWirePayeeResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountPayeeId" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLines" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AddressLines" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="PostalZip" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryCode" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="Mode" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="getAutomaticInvestmentCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAutomaticInvestmentCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAutomaticInvestmentCollectionResult" type="tns:AutomaticInvestmentsCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AutomaticInvestmentsCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AutomaticInvestments" type="tns:ArrayOfAutomaticInvestmentResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAutomaticInvestmentResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AutomaticInvestmentResult" nillable="true" type="tns:AutomaticInvestmentResult" />
        </s:sequence>
      </s:complexType>
      <s:element name="addAutomaticInvestmentPlan">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="automaticInvestment" nillable="true" type="tns:AutomaticInvestmentRequest" />
            <s:element minOccurs="1" maxOccurs="1" name="achPayeeInfo" nillable="true" type="tns:AchPayeeRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AutomaticInvestmentRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="StartDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="SchedulerFrequency" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DateQualifierForDay1" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DateQualifierForDay2" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DayOfTheMonth1" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DayOfTheMonth2" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="MonthIndicators" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountPayeeId" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AutomaticInvestmentId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Memo" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MonthlySchedulerId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FundSelectionIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AIPFunds" type="tns:ArrayOfAIPFundInformation" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAIPFundInformation">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AIPFundInformation" type="tns:AIPFundInformation" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AIPFundInformation">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="MoneyAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="RetirementIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="SettlementAmountIndicator" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="addAutomaticInvestmentPlanResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="addAutomaticInvestmentPlanResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="modifyAutomaticInvestmentPlan">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="automaticInvestment" nillable="true" type="tns:AutomaticInvestmentRequest" />
            <s:element minOccurs="1" maxOccurs="1" name="achPayeeInfo" nillable="true" type="tns:AchPayeeRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="modifyAutomaticInvestmentPlanResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="modifyAutomaticInvestmentPlanResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="deleteAutomaticInvestmentPlan">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="automaticInvestmentID" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="deleteAutomaticInvestmentPlanResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="deleteAutomaticInvestmentPlanResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="addAccountForCommonMailing">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountLinkages" nillable="true" type="tns:AccountLinkagesRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountLinkagesRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="WebUserId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountToAdd" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="RelationType" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="addAccountForCommonMailingResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="addAccountForCommonMailingResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getStatementByFormCategory">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="statementbyFormCategory" nillable="true" type="tns:StatementbyFormCategoryRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="StatementbyFormCategoryRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FormCategoryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountLinkType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DeliveryMode" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="getStatementByFormCategoryResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getStatementByFormCategoryResult" type="tns:StatementbyFormCategoryResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="StatementbyFormCategoryResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FormCategoryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountLinkType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="DeliveryMode" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateStatementByFormCategory">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="statementbyFormCategory" nillable="true" type="tns:StatementbyFormCategoryRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateStatementByFormCategoryResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateStatementByFormCategoryResult" type="tns:StatementbyFormCategoryResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getMailingAddressForMailingAddressType">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="mailingAddressType" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getMailingAddressForMailingAddressTypeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getMailingAddressForMailingAddressTypeResult" type="tns:MailingAddressesResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateMailingAddresses">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="mailingAddresses" nillable="true" type="tns:MailingAddressesRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="MailingAddressesRequest">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLines" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AddressLines" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="PostalZip" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryCode" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="VoicePhone" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AltPhone" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FaxPhone" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="EmailAddress" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="EntityIdentifier" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateMailingAddressesResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateMailingAddressesResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateSuccessorBeneficiaries">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="successorBeneficiary" nillable="true" type="tns:SuccessorBeneficiaryRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="SuccessorBeneficiaryRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="SSNOrTIN" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BeneficiaryType" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="BeneficiaryStatus" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PrimaryBeneficiaryIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PrimaryBeneIndicator" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="PercentAssigned" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="DateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="DateAssigned" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="DateDropped" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="MailingAddresses" type="tns:MailingAddressesRequest" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateSuccessorBeneficiariesResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateSuccessorBeneficiariesResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="addSuccessorBeneficiaries">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="successorBeneficiary" nillable="true" type="tns:SuccessorBeneficiaryRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="addSuccessorBeneficiariesResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="addSuccessorBeneficiariesResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="updateBeneficiary529">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="beneficiary529" nillable="true" type="tns:Beneficiary529Request" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="Beneficiary529Request">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiaryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="IsSsn" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="SsnOrTin" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryOfOriginTaxResidence" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryOfCitizenship" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="Relationship" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CollegeEnteringAge" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CollegeEnteringDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="Gender" type="tns:GenderEnum1" />
          <s:element minOccurs="1" maxOccurs="1" name="IsAddressSameAsAccountOwner" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLine1" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="NameLine2" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AddressLine" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="City" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="State" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="Zip" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryCode" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="InvestmentOption" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="LastInvestmentOptionChangeDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="Portfolio" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AgeBasedModelId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="UgmaUtmaState" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="ExpectedEnrollmentYearId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="BeneficiaryFullName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="MailingAddress" type="tns:MailingAddressesRequest" />
        </s:sequence>
      </s:complexType>
      <s:simpleType name="GenderEnum1">
        <s:restriction base="s:string">
          <s:enumeration value="Null" />
          <s:enumeration value="Male" />
          <s:enumeration value="Female" />
        </s:restriction>
      </s:simpleType>
      <s:element name="updateBeneficiary529Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateBeneficiary529Result" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="addAccountMessages">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountMessage" nillable="true" type="tns:AccountMessageRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountMessageRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="MessageTypeCode" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="MessageDescription" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="WebUserId" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="InvitationGiftReferenceId" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:element name="addAccountMessagesResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="addAccountMessagesResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="addAccountCustomField">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountCustomField" nillable="true" type="tns:AccountCustomFieldRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountCustomFieldRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomFieldDefinition" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomFieldValue" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="addAccountCustomFieldResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="addAccountCustomFieldResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAssetAllocationModelWeights">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAssetAllocationModelWeightsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAssetAllocationModelWeightsResult" type="tns:AssetAllocationModelWeightsResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AssetAllocationModelWeightsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountAssetAllocationModelWeightsCollection" type="tns:ArrayOfAccountAssetAllocationModelWeightsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateAssetAllocationModel">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="assetAllocationModel" nillable="true" type="tns:AssetAllocationModelsRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AssetAllocationModelsRequest">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ModelId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ModelName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ModelDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ModelScope" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="Status" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="RebalanceOnPurchasesFlag" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="RebalanceOnRedemptionsFlag" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="AllocationPercentageTolerance" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="NoOfFundsInModel" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AgeBasedModelId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="InvestmentOption" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="UpdateBeneficiaryAgeBasedModel" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="AssetAllocationFunds" type="tns:ArrayOfAssetAllocationFundsRequest" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountAssetAllocationModelWeightsCollection" type="tns:ArrayOfAccountAssetAllocationModelWeightsRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAssetAllocationFundsRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AssetAllocationFundsRequest" nillable="true" type="tns:AssetAllocationFundsRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AssetAllocationFundsRequest">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="AssetAllocationPercentage" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAccountAssetAllocationModelWeightsRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AccountAssetAllocationModelWeightsRequest" nillable="true" type="tns:AccountAssetAllocationModelWeightsRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AccountAssetAllocationModelWeightsRequest">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ModelId" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="Fundid" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="AllocationPercentage" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="FundName" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateAssetAllocationModelResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateAssetAllocationModelResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="createNewAccount">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="account" nillable="true" type="tns:AccountRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomerName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="IsBlueSkyState" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="BlueSkyState" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="IsSsn" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="SsnOrTin" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="BackupWithholdingIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountRepId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="ShareClassId" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountAdvisorId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="TradingEntity" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="CountryOfOriginParm" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="IsBlueSkyExempt" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="JointDateOfBirth" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="MailingAddressId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="JointSsnOrTin" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Gender" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="PortfolioAllocatorIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountMessages" type="tns:AccountMessageCollectionRequest" />
          <s:element minOccurs="0" maxOccurs="1" name="AssetAllocationModel" type="tns:AssetAllocationModelsRequest" />
          <s:element minOccurs="0" maxOccurs="1" name="AutomaticInvestments" type="tns:AutomaticInvestmentRequest" />
          <s:element minOccurs="0" maxOccurs="1" name="BankAccounts" type="tns:AchPayeeCollectionRequest" />
          <s:element minOccurs="0" maxOccurs="1" name="Beneficiary" type="tns:Beneficiary529Request" />
          <s:element minOccurs="0" maxOccurs="1" name="MailingAddress" type="tns:MailingAddressesRequest" />
          <s:element minOccurs="0" maxOccurs="1" name="Successors" type="tns:SuccessorBeneficiaryCollectionRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AccountMessageCollectionRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountMessage" type="tns:ArrayOfAccountMessageRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAccountMessageRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AccountMessageRequest" nillable="true" type="tns:AccountMessageRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AchPayeeCollectionRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AchPayee" type="tns:ArrayOfAchPayeeRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAchPayeeRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AchPayeeRequest" nillable="true" type="tns:AchPayeeRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="SuccessorBeneficiaryCollectionRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="SuccessorBeneficiary" type="tns:ArrayOfSuccessorBeneficiaryRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfSuccessorBeneficiaryRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="SuccessorBeneficiaryRequest" nillable="true" type="tns:SuccessorBeneficiaryRequest" />
        </s:sequence>
      </s:complexType>
      <s:element name="createNewAccountResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="createNewAccountResult" type="tns:NewAccountResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="NewAccountResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="getAccountForCustomFieldValue">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountCustomFieldValueRequest" nillable="true" type="tns:AccountCustomFieldValuesRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountCustomFieldValuesRequest">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FieldId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="FieldValue" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="getAccountForCustomFieldValueResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAccountForCustomFieldValueResult" type="tns:AccountCustomFieldValuesResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountCustomFieldValuesResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FieldId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="FieldValue" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:element name="getPendingTradesForAccount">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getPendingTradesForAccountResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getPendingTradesForAccountResult" type="tns:PendingOrderCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="PendingOrderCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="PendingOrder" type="tns:ArrayOfPendingOrderResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfPendingOrderResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="PendingOrderResult" nillable="true" type="tns:PendingOrderResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="PendingOrderResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="TransactionID" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundTransactionLineNo" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FundTransactionStatus" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="FundShortName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundTransactionType" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="SharesOrAmountIndicator" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FxGrossAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ShareAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TradeDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="Settlementdate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="CurrencyId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FundBaseCurrencyId" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateDeliveryModeForEdocs">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="edocDeliveryModeRequest" nillable="true" type="tns:EdocsDeliveryModeRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="EdocsDeliveryModeRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FormCategoryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DeliveryMode" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateDeliveryModeForEdocsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateDeliveryModeForEdocsResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getEdocs">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getEdocsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getEdocsResult" type="tns:EdocsCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="EdocsCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Edocs" type="tns:ArrayOfEdocsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfEdocsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="EdocsResult" nillable="true" type="tns:EdocsResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EdocsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="EmailAddress" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="EdocsDeliveryMode" type="tns:ArrayOfEdocsDeliveryModeResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfEdocsDeliveryModeResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="EdocsDeliveryModeResult" nillable="true" type="tns:EdocsDeliveryModeResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EdocsDeliveryModeResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="FormCategoryId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FormCategoryName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="DeliveryMode" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="DeliveryModeDescription" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="getPrepaidActivityCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="prepaidActivityRequest" nillable="true" type="tns:PrepaidActivityRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="PrepaidActivityRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="StartDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="EndDate" type="s:dateTime" />
        </s:sequence>
      </s:complexType>
      <s:element name="getPrepaidActivityCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getPrepaidActivityCollectionResult" type="tns:PrepaidActivityCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="PrepaidActivityCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="PrepaidActivities" type="tns:ArrayOfPrepaidActivityResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfPrepaidActivityResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="PrepaidActivityResult" nillable="true" type="tns:PrepaidActivityResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="PrepaidActivityResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="TransactionId" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="TransactionLineNo" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="TradeDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="TransactionType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="TransactionDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TransactionAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="BasisAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="EarningsAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="InstitutionId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="InstitutionName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ProgramYearId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="ProgramYearDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TuitionCreditYears" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="FromToAccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="RedemptionType" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="Memo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="RetirementIndicatorDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="RetirementIndicator" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="getPrepaidSummaryCollection">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="prepaidSummaryRequest" nillable="true" type="tns:PrepaidSummaryRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="PrepaidSummaryRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
        </s:sequence>
      </s:complexType>
      <s:element name="getPrepaidSummaryCollectionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getPrepaidSummaryCollectionResult" type="tns:PrepaidSummaryResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="PrepaidSummaryResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="PrepaidSummary" type="tns:PrepaidSummaryInfo" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="PrepaidSummaryInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="PriorYearsGrossPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="PriorYearsNetPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LTDGrossPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LTDNetPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CurrentYearGrossPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CurrentYearNetPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId1" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName1" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalTuitionCredits1" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId2" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName2" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalTuitionCredits2" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId3" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName3" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalTuitionCredits3" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId4" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName4" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalTuitionCredits4" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId5" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName5" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalTuitionCredits5" type="s:decimal" />
          <s:element minOccurs="0" maxOccurs="1" name="PrepaidDetails" type="tns:ArrayOfPrepaidDetailResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfPrepaidDetailResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="PrepaidDetailResult" nillable="true" type="tns:PrepaidDetailResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="PrepaidDetailResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="ProgramYearId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="ProgramYearDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="RefundValue" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LatestNAV" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LatestNAVDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="EarliestEligibleRefundDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="EarliestEligibleRedemptionDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="FirstPurchaseDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="ProgramYearGrossPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ProgramYearNetPurchases" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalBasisAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="TotalContributionAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId1" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName1" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TuitionCredits1" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId2" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName2" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TuitionCredits2" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId3" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName3" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TuitionCredits3" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId4" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName4" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TuitionCredits4" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FavoriteInstitutionId5" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutionName5" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TuitionCredits5" type="s:decimal" />
        </s:sequence>
      </s:complexType>
      <s:element name="getFavoriteInstitutionsForBeneficiary">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getFavoriteInstitutionsForBeneficiaryResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getFavoriteInstitutionsForBeneficiaryResult" type="tns:FavoriteInstitutionsCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="FavoriteInstitutionsCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutions" type="tns:ArrayOfInstitution" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfInstitution">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="Institution" nillable="true" type="tns:Institution" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="Institution">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="InstitutionId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="InstitutionName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="InstitutionStateId" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="InstitutionStateShortName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="InstitutionStateFullName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="InstitutionURL" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ParticipationStartYearId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="ParticipationStartYear" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="ParticipationEndYearId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="ParticipationEndYear" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="DefaultDiscountRate" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="DefaultAlmaMaterDiscountRate" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ReligiousAffiliationsId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="ReligiousAffiliationsDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TypeOfSchoolId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="TypeOfSchoolDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="SizeOfSchoolId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="SizeOfSchoolDescription" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateFavouriteInstitutionsForBeneficiary">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="updateFavoriteInstitutionsRequest" nillable="true" type="tns:UpdateFavoriteInstitutionsRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="UpdateFavoriteInstitutionsRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FavoriteInstitutions" type="tns:ArrayOfFavoriteInstitutionsRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfFavoriteInstitutionsRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="FavoriteInstitutionsRequest" nillable="true" type="tns:FavoriteInstitutionsRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="FavoriteInstitutionsRequest">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="InstitutionId" type="s:short" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateFavouriteInstitutionsForBeneficiaryResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateFavouriteInstitutionsForBeneficiaryResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAccountCustomFields">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountNumber" nillable="true" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getAccountCustomFieldsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getAccountCustomFieldsResult" type="tns:AccountCustomFieldsCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountCustomFieldsCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountCustomFields" type="tns:ArrayOfAccountCustomFieldsResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAccountCustomFieldsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AccountCustomFieldsResult" nillable="true" type="tns:AccountCustomFieldsResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AccountCustomFieldsResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountCustomFieldDefinitionSortOrder" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountCustomFieldId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountCustomFieldDefinition" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AccountCustomFieldValue" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateAccountCustomFields">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="updateCustomFieldsRequest" nillable="true" type="tns:UpdateCustomFieldsRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="UpdateCustomFieldsRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountCustomFields" type="tns:ArrayOfAccountCustomFieldsRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfAccountCustomFieldsRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="AccountCustomFieldsRequest" nillable="true" type="tns:AccountCustomFieldsRequest" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="AccountCustomFieldsRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomFieldDefinition" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomFieldValue" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateAccountCustomFieldsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateAccountCustomFieldsResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getCustomFieldListValues">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="0" maxOccurs="1" name="accountCustomFieldDefinition" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="getCustomFieldListValuesResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="getCustomFieldListValuesResult" type="tns:CustomFieldListValuesCollectionResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="CustomFieldListValuesCollectionResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="CustomFieldListValuesCollection" type="tns:ArrayOfCustomFieldListValuesResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfCustomFieldListValuesResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="CustomFieldListValuesResult" nillable="true" type="tns:CustomFieldListValuesResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="CustomFieldListValuesResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="CustomFieldDefinition" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ListId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="CustomFieldListValues" type="tns:ArrayOfCustomFieldListValue" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfCustomFieldListValue">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="CustomFieldListValue" nillable="true" type="tns:CustomFieldListValue" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="CustomFieldListValue">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="ListValueId" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="ListValue" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="calculateCDSCForAccount">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="cdscCalculatorRequest" nillable="true" type="tns:CDSCCalculatorRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="CDSCCalculatorRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="SharesOrAmountIndicator" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="GrossAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ShareAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ValuationDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="ValuationPrice" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="SettlementAmountIndicator" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="calculateCDSCForAccountResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="calculateCDSCForAccountResult" type="tns:CDSCCalculatorResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="CDSCCalculatorResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="GrossSharesRedeemed" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="RedemptionShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="MoneyMarketShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FrontLoadNoLotShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="NoLoadNoLotShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CDSCApplicableShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CDSCReinvestedShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CDSCExcludedShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FLCDSCApplicableShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FLCDSCReinvestedShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FLCDSCExcludedShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="NoLoadLotShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="ValuationDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="ValuationPrice" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="RedemptionAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CDSCAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="SettlementAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="SettlementAmountIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="SettlementAmountIndicatorDescription" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FundBaseCurrencyCode" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FundShortName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="LotDetails" type="tns:ArrayOfLotDetailResult" />
          <s:element minOccurs="0" maxOccurs="1" name="ErrorStatus" type="tns:Status" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfLotDetailResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="LotDetailResult" nillable="true" type="tns:LotDetailResult" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="LotDetailResult">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="PurchaseDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="CostBasisPrice" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="Shares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="FreeShares" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="AgeAdjustments" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="Age" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="DaysOrMonthsIndicator" type="s:unsignedByte" />
          <s:element minOccurs="0" maxOccurs="1" name="DaysOrMonthsIndicatorDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="CDSCPercentage" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CDSCAmount" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LotType" type="s:short" />
          <s:element minOccurs="0" maxOccurs="1" name="LotTypeDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="TollStartDate" type="s:dateTime" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateAccountDividendSetup">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="userLogin" nillable="true" type="tns:AuthenticateLogin" />
            <s:element minOccurs="1" maxOccurs="1" name="accountDividendSetupRequest" nillable="true" type="tns:AccountDividendSetupRequest" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="AccountDividendSetupRequest">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="AccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FundId" type="s:short" />
          <s:element minOccurs="1" maxOccurs="1" name="Dividend" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="STCG" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="LTCG" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="ReturnOfCapital" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="AccountPayeeId" nillable="true" type="s:unsignedByte" />
          <s:element minOccurs="1" maxOccurs="1" name="RetirementIndicator" type="s:unsignedByte" />
        </s:sequence>
      </s:complexType>
      <s:element name="updateAccountDividendSetupResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="updateAccountDividendSetupResult" type="tns:Status" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="getAccountInfoSoapIn">
    <wsdl:part name="parameters" element="tns:getAccountInfo" />
  </wsdl:message>
  <wsdl:message name="getAccountInfoSoapOut">
    <wsdl:part name="parameters" element="tns:getAccountInfoResponse" />
  </wsdl:message>
  <wsdl:message name="getMailingAddressSoapIn">
    <wsdl:part name="parameters" element="tns:getMailingAddress" />
  </wsdl:message>
  <wsdl:message name="getMailingAddressSoapOut">
    <wsdl:part name="parameters" element="tns:getMailingAddressResponse" />
  </wsdl:message>
  <wsdl:message name="getBeneficiary529SoapIn">
    <wsdl:part name="parameters" element="tns:getBeneficiary529" />
  </wsdl:message>
  <wsdl:message name="getBeneficiary529SoapOut">
    <wsdl:part name="parameters" element="tns:getBeneficiary529Response" />
  </wsdl:message>
  <wsdl:message name="getPlan529AccountBalanceActivityCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getPlan529AccountBalanceActivityCollection" />
  </wsdl:message>
  <wsdl:message name="getPlan529AccountBalanceActivityCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getPlan529AccountBalanceActivityCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getActivityCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getActivityCollection" />
  </wsdl:message>
  <wsdl:message name="getActivityCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getActivityCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getAuthorizedAccountCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getAuthorizedAccountCollection" />
  </wsdl:message>
  <wsdl:message name="getAuthorizedAccountCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getAuthorizedAccountCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getAccountSoapIn">
    <wsdl:part name="parameters" element="tns:getAccount" />
  </wsdl:message>
  <wsdl:message name="getAccountSoapOut">
    <wsdl:part name="parameters" element="tns:getAccountResponse" />
  </wsdl:message>
  <wsdl:message name="isAccountClosedSoapIn">
    <wsdl:part name="parameters" element="tns:isAccountClosed" />
  </wsdl:message>
  <wsdl:message name="isAccountClosedSoapOut">
    <wsdl:part name="parameters" element="tns:isAccountClosedResponse" />
  </wsdl:message>
  <wsdl:message name="getPlan529AccountBalanceSummaryCollectionForAccountSoapIn">
    <wsdl:part name="parameters" element="tns:getPlan529AccountBalanceSummaryCollectionForAccount" />
  </wsdl:message>
  <wsdl:message name="getPlan529AccountBalanceSummaryCollectionForAccountSoapOut">
    <wsdl:part name="parameters" element="tns:getPlan529AccountBalanceSummaryCollectionForAccountResponse" />
  </wsdl:message>
  <wsdl:message name="getPlan529AccountBalanceSummaryCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getPlan529AccountBalanceSummaryCollection" />
  </wsdl:message>
  <wsdl:message name="getPlan529AccountBalanceSummaryCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getPlan529AccountBalanceSummaryCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getBalanceCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getBalanceCollection" />
  </wsdl:message>
  <wsdl:message name="getBalanceCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getBalanceCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getBalanceCollectionWithDateRangeSoapIn">
    <wsdl:part name="parameters" element="tns:getBalanceCollectionWithDateRange" />
  </wsdl:message>
  <wsdl:message name="getBalanceCollectionWithDateRangeSoapOut">
    <wsdl:part name="parameters" element="tns:getBalanceCollectionWithDateRangeResponse" />
  </wsdl:message>
  <wsdl:message name="isAccountPayeeBeingUsedSoapIn">
    <wsdl:part name="parameters" element="tns:isAccountPayeeBeingUsed" />
  </wsdl:message>
  <wsdl:message name="isAccountPayeeBeingUsedSoapOut">
    <wsdl:part name="parameters" element="tns:isAccountPayeeBeingUsedResponse" />
  </wsdl:message>
  <wsdl:message name="getAchPayeeCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getAchPayeeCollection" />
  </wsdl:message>
  <wsdl:message name="getAchPayeeCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getAchPayeeCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="addAchPayeeSoapIn">
    <wsdl:part name="parameters" element="tns:addAchPayee" />
  </wsdl:message>
  <wsdl:message name="addAchPayeeSoapOut">
    <wsdl:part name="parameters" element="tns:addAchPayeeResponse" />
  </wsdl:message>
  <wsdl:message name="deleteAchPayeeSoapIn">
    <wsdl:part name="parameters" element="tns:deleteAchPayee" />
  </wsdl:message>
  <wsdl:message name="deleteAchPayeeSoapOut">
    <wsdl:part name="parameters" element="tns:deleteAchPayeeResponse" />
  </wsdl:message>
  <wsdl:message name="getCheckWirePayeeCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getCheckWirePayeeCollection" />
  </wsdl:message>
  <wsdl:message name="getCheckWirePayeeCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getCheckWirePayeeCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getAutomaticInvestmentCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getAutomaticInvestmentCollection" />
  </wsdl:message>
  <wsdl:message name="getAutomaticInvestmentCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getAutomaticInvestmentCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="addAutomaticInvestmentPlanSoapIn">
    <wsdl:part name="parameters" element="tns:addAutomaticInvestmentPlan" />
  </wsdl:message>
  <wsdl:message name="addAutomaticInvestmentPlanSoapOut">
    <wsdl:part name="parameters" element="tns:addAutomaticInvestmentPlanResponse" />
  </wsdl:message>
  <wsdl:message name="modifyAutomaticInvestmentPlanSoapIn">
    <wsdl:part name="parameters" element="tns:modifyAutomaticInvestmentPlan" />
  </wsdl:message>
  <wsdl:message name="modifyAutomaticInvestmentPlanSoapOut">
    <wsdl:part name="parameters" element="tns:modifyAutomaticInvestmentPlanResponse" />
  </wsdl:message>
  <wsdl:message name="deleteAutomaticInvestmentPlanSoapIn">
    <wsdl:part name="parameters" element="tns:deleteAutomaticInvestmentPlan" />
  </wsdl:message>
  <wsdl:message name="deleteAutomaticInvestmentPlanSoapOut">
    <wsdl:part name="parameters" element="tns:deleteAutomaticInvestmentPlanResponse" />
  </wsdl:message>
  <wsdl:message name="addAccountForCommonMailingSoapIn">
    <wsdl:part name="parameters" element="tns:addAccountForCommonMailing" />
  </wsdl:message>
  <wsdl:message name="addAccountForCommonMailingSoapOut">
    <wsdl:part name="parameters" element="tns:addAccountForCommonMailingResponse" />
  </wsdl:message>
  <wsdl:message name="getStatementByFormCategorySoapIn">
    <wsdl:part name="parameters" element="tns:getStatementByFormCategory" />
  </wsdl:message>
  <wsdl:message name="getStatementByFormCategorySoapOut">
    <wsdl:part name="parameters" element="tns:getStatementByFormCategoryResponse" />
  </wsdl:message>
  <wsdl:message name="updateStatementByFormCategorySoapIn">
    <wsdl:part name="parameters" element="tns:updateStatementByFormCategory" />
  </wsdl:message>
  <wsdl:message name="updateStatementByFormCategorySoapOut">
    <wsdl:part name="parameters" element="tns:updateStatementByFormCategoryResponse" />
  </wsdl:message>
  <wsdl:message name="getMailingAddressForMailingAddressTypeSoapIn">
    <wsdl:part name="parameters" element="tns:getMailingAddressForMailingAddressType" />
  </wsdl:message>
  <wsdl:message name="getMailingAddressForMailingAddressTypeSoapOut">
    <wsdl:part name="parameters" element="tns:getMailingAddressForMailingAddressTypeResponse" />
  </wsdl:message>
  <wsdl:message name="updateMailingAddressesSoapIn">
    <wsdl:part name="parameters" element="tns:updateMailingAddresses" />
  </wsdl:message>
  <wsdl:message name="updateMailingAddressesSoapOut">
    <wsdl:part name="parameters" element="tns:updateMailingAddressesResponse" />
  </wsdl:message>
  <wsdl:message name="updateSuccessorBeneficiariesSoapIn">
    <wsdl:part name="parameters" element="tns:updateSuccessorBeneficiaries" />
  </wsdl:message>
  <wsdl:message name="updateSuccessorBeneficiariesSoapOut">
    <wsdl:part name="parameters" element="tns:updateSuccessorBeneficiariesResponse" />
  </wsdl:message>
  <wsdl:message name="addSuccessorBeneficiariesSoapIn">
    <wsdl:part name="parameters" element="tns:addSuccessorBeneficiaries" />
  </wsdl:message>
  <wsdl:message name="addSuccessorBeneficiariesSoapOut">
    <wsdl:part name="parameters" element="tns:addSuccessorBeneficiariesResponse" />
  </wsdl:message>
  <wsdl:message name="updateBeneficiary529SoapIn">
    <wsdl:part name="parameters" element="tns:updateBeneficiary529" />
  </wsdl:message>
  <wsdl:message name="updateBeneficiary529SoapOut">
    <wsdl:part name="parameters" element="tns:updateBeneficiary529Response" />
  </wsdl:message>
  <wsdl:message name="addAccountMessagesSoapIn">
    <wsdl:part name="parameters" element="tns:addAccountMessages" />
  </wsdl:message>
  <wsdl:message name="addAccountMessagesSoapOut">
    <wsdl:part name="parameters" element="tns:addAccountMessagesResponse" />
  </wsdl:message>
  <wsdl:message name="addAccountCustomFieldSoapIn">
    <wsdl:part name="parameters" element="tns:addAccountCustomField" />
  </wsdl:message>
  <wsdl:message name="addAccountCustomFieldSoapOut">
    <wsdl:part name="parameters" element="tns:addAccountCustomFieldResponse" />
  </wsdl:message>
  <wsdl:message name="getAssetAllocationModelWeightsSoapIn">
    <wsdl:part name="parameters" element="tns:getAssetAllocationModelWeights" />
  </wsdl:message>
  <wsdl:message name="getAssetAllocationModelWeightsSoapOut">
    <wsdl:part name="parameters" element="tns:getAssetAllocationModelWeightsResponse" />
  </wsdl:message>
  <wsdl:message name="updateAssetAllocationModelSoapIn">
    <wsdl:part name="parameters" element="tns:updateAssetAllocationModel" />
  </wsdl:message>
  <wsdl:message name="updateAssetAllocationModelSoapOut">
    <wsdl:part name="parameters" element="tns:updateAssetAllocationModelResponse" />
  </wsdl:message>
  <wsdl:message name="createNewAccountSoapIn">
    <wsdl:part name="parameters" element="tns:createNewAccount" />
  </wsdl:message>
  <wsdl:message name="createNewAccountSoapOut">
    <wsdl:part name="parameters" element="tns:createNewAccountResponse" />
  </wsdl:message>
  <wsdl:message name="getAccountForCustomFieldValueSoapIn">
    <wsdl:part name="parameters" element="tns:getAccountForCustomFieldValue" />
  </wsdl:message>
  <wsdl:message name="getAccountForCustomFieldValueSoapOut">
    <wsdl:part name="parameters" element="tns:getAccountForCustomFieldValueResponse" />
  </wsdl:message>
  <wsdl:message name="getPendingTradesForAccountSoapIn">
    <wsdl:part name="parameters" element="tns:getPendingTradesForAccount" />
  </wsdl:message>
  <wsdl:message name="getPendingTradesForAccountSoapOut">
    <wsdl:part name="parameters" element="tns:getPendingTradesForAccountResponse" />
  </wsdl:message>
  <wsdl:message name="updateDeliveryModeForEdocsSoapIn">
    <wsdl:part name="parameters" element="tns:updateDeliveryModeForEdocs" />
  </wsdl:message>
  <wsdl:message name="updateDeliveryModeForEdocsSoapOut">
    <wsdl:part name="parameters" element="tns:updateDeliveryModeForEdocsResponse" />
  </wsdl:message>
  <wsdl:message name="getEdocsSoapIn">
    <wsdl:part name="parameters" element="tns:getEdocs" />
  </wsdl:message>
  <wsdl:message name="getEdocsSoapOut">
    <wsdl:part name="parameters" element="tns:getEdocsResponse" />
  </wsdl:message>
  <wsdl:message name="getPrepaidActivityCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getPrepaidActivityCollection" />
  </wsdl:message>
  <wsdl:message name="getPrepaidActivityCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getPrepaidActivityCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getPrepaidSummaryCollectionSoapIn">
    <wsdl:part name="parameters" element="tns:getPrepaidSummaryCollection" />
  </wsdl:message>
  <wsdl:message name="getPrepaidSummaryCollectionSoapOut">
    <wsdl:part name="parameters" element="tns:getPrepaidSummaryCollectionResponse" />
  </wsdl:message>
  <wsdl:message name="getFavoriteInstitutionsForBeneficiarySoapIn">
    <wsdl:part name="parameters" element="tns:getFavoriteInstitutionsForBeneficiary" />
  </wsdl:message>
  <wsdl:message name="getFavoriteInstitutionsForBeneficiarySoapOut">
    <wsdl:part name="parameters" element="tns:getFavoriteInstitutionsForBeneficiaryResponse" />
  </wsdl:message>
  <wsdl:message name="updateFavouriteInstitutionsForBeneficiarySoapIn">
    <wsdl:part name="parameters" element="tns:updateFavouriteInstitutionsForBeneficiary" />
  </wsdl:message>
  <wsdl:message name="updateFavouriteInstitutionsForBeneficiarySoapOut">
    <wsdl:part name="parameters" element="tns:updateFavouriteInstitutionsForBeneficiaryResponse" />
  </wsdl:message>
  <wsdl:message name="getAccountCustomFieldsSoapIn">
    <wsdl:part name="parameters" element="tns:getAccountCustomFields" />
  </wsdl:message>
  <wsdl:message name="getAccountCustomFieldsSoapOut">
    <wsdl:part name="parameters" element="tns:getAccountCustomFieldsResponse" />
  </wsdl:message>
  <wsdl:message name="updateAccountCustomFieldsSoapIn">
    <wsdl:part name="parameters" element="tns:updateAccountCustomFields" />
  </wsdl:message>
  <wsdl:message name="updateAccountCustomFieldsSoapOut">
    <wsdl:part name="parameters" element="tns:updateAccountCustomFieldsResponse" />
  </wsdl:message>
  <wsdl:message name="getCustomFieldListValuesSoapIn">
    <wsdl:part name="parameters" element="tns:getCustomFieldListValues" />
  </wsdl:message>
  <wsdl:message name="getCustomFieldListValuesSoapOut">
    <wsdl:part name="parameters" element="tns:getCustomFieldListValuesResponse" />
  </wsdl:message>
  <wsdl:message name="calculateCDSCForAccountSoapIn">
    <wsdl:part name="parameters" element="tns:calculateCDSCForAccount" />
  </wsdl:message>
  <wsdl:message name="calculateCDSCForAccountSoapOut">
    <wsdl:part name="parameters" element="tns:calculateCDSCForAccountResponse" />
  </wsdl:message>
  <wsdl:message name="updateAccountDividendSetupSoapIn">
    <wsdl:part name="parameters" element="tns:updateAccountDividendSetup" />
  </wsdl:message>
  <wsdl:message name="updateAccountDividendSetupSoapOut">
    <wsdl:part name="parameters" element="tns:updateAccountDividendSetupResponse" />
  </wsdl:message>
  <wsdl:portType name="AccountServicesSoap">
    <wsdl:operation name="getAccountInfo">
      <wsdl:input message="tns:getAccountInfoSoapIn" />
      <wsdl:output message="tns:getAccountInfoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getMailingAddress">
      <wsdl:input message="tns:getMailingAddressSoapIn" />
      <wsdl:output message="tns:getMailingAddressSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getBeneficiary529">
      <wsdl:input message="tns:getBeneficiary529SoapIn" />
      <wsdl:output message="tns:getBeneficiary529SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceActivityCollection">
      <wsdl:input message="tns:getPlan529AccountBalanceActivityCollectionSoapIn" />
      <wsdl:output message="tns:getPlan529AccountBalanceActivityCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getActivityCollection">
      <wsdl:input message="tns:getActivityCollectionSoapIn" />
      <wsdl:output message="tns:getActivityCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAuthorizedAccountCollection">
      <wsdl:input message="tns:getAuthorizedAccountCollectionSoapIn" />
      <wsdl:output message="tns:getAuthorizedAccountCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAccount">
      <wsdl:input message="tns:getAccountSoapIn" />
      <wsdl:output message="tns:getAccountSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="isAccountClosed">
      <wsdl:input message="tns:isAccountClosedSoapIn" />
      <wsdl:output message="tns:isAccountClosedSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceSummaryCollectionForAccount">
      <wsdl:input message="tns:getPlan529AccountBalanceSummaryCollectionForAccountSoapIn" />
      <wsdl:output message="tns:getPlan529AccountBalanceSummaryCollectionForAccountSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceSummaryCollection">
      <wsdl:input message="tns:getPlan529AccountBalanceSummaryCollectionSoapIn" />
      <wsdl:output message="tns:getPlan529AccountBalanceSummaryCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getBalanceCollection">
      <wsdl:input message="tns:getBalanceCollectionSoapIn" />
      <wsdl:output message="tns:getBalanceCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getBalanceCollectionWithDateRange">
      <wsdl:input message="tns:getBalanceCollectionWithDateRangeSoapIn" />
      <wsdl:output message="tns:getBalanceCollectionWithDateRangeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="isAccountPayeeBeingUsed">
      <wsdl:input message="tns:isAccountPayeeBeingUsedSoapIn" />
      <wsdl:output message="tns:isAccountPayeeBeingUsedSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAchPayeeCollection">
      <wsdl:input message="tns:getAchPayeeCollectionSoapIn" />
      <wsdl:output message="tns:getAchPayeeCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="addAchPayee">
      <wsdl:input message="tns:addAchPayeeSoapIn" />
      <wsdl:output message="tns:addAchPayeeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="deleteAchPayee">
      <wsdl:input message="tns:deleteAchPayeeSoapIn" />
      <wsdl:output message="tns:deleteAchPayeeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getCheckWirePayeeCollection">
      <wsdl:input message="tns:getCheckWirePayeeCollectionSoapIn" />
      <wsdl:output message="tns:getCheckWirePayeeCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAutomaticInvestmentCollection">
      <wsdl:input message="tns:getAutomaticInvestmentCollectionSoapIn" />
      <wsdl:output message="tns:getAutomaticInvestmentCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="addAutomaticInvestmentPlan">
      <wsdl:input message="tns:addAutomaticInvestmentPlanSoapIn" />
      <wsdl:output message="tns:addAutomaticInvestmentPlanSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="modifyAutomaticInvestmentPlan">
      <wsdl:input message="tns:modifyAutomaticInvestmentPlanSoapIn" />
      <wsdl:output message="tns:modifyAutomaticInvestmentPlanSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="deleteAutomaticInvestmentPlan">
      <wsdl:input message="tns:deleteAutomaticInvestmentPlanSoapIn" />
      <wsdl:output message="tns:deleteAutomaticInvestmentPlanSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="addAccountForCommonMailing">
      <wsdl:input message="tns:addAccountForCommonMailingSoapIn" />
      <wsdl:output message="tns:addAccountForCommonMailingSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getStatementByFormCategory">
      <wsdl:input message="tns:getStatementByFormCategorySoapIn" />
      <wsdl:output message="tns:getStatementByFormCategorySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateStatementByFormCategory">
      <wsdl:input message="tns:updateStatementByFormCategorySoapIn" />
      <wsdl:output message="tns:updateStatementByFormCategorySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getMailingAddressForMailingAddressType">
      <wsdl:input message="tns:getMailingAddressForMailingAddressTypeSoapIn" />
      <wsdl:output message="tns:getMailingAddressForMailingAddressTypeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateMailingAddresses">
      <wsdl:input message="tns:updateMailingAddressesSoapIn" />
      <wsdl:output message="tns:updateMailingAddressesSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateSuccessorBeneficiaries">
      <wsdl:input message="tns:updateSuccessorBeneficiariesSoapIn" />
      <wsdl:output message="tns:updateSuccessorBeneficiariesSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="addSuccessorBeneficiaries">
      <wsdl:input message="tns:addSuccessorBeneficiariesSoapIn" />
      <wsdl:output message="tns:addSuccessorBeneficiariesSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateBeneficiary529">
      <wsdl:input message="tns:updateBeneficiary529SoapIn" />
      <wsdl:output message="tns:updateBeneficiary529SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="addAccountMessages">
      <wsdl:input message="tns:addAccountMessagesSoapIn" />
      <wsdl:output message="tns:addAccountMessagesSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="addAccountCustomField">
      <wsdl:input message="tns:addAccountCustomFieldSoapIn" />
      <wsdl:output message="tns:addAccountCustomFieldSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAssetAllocationModelWeights">
      <wsdl:input message="tns:getAssetAllocationModelWeightsSoapIn" />
      <wsdl:output message="tns:getAssetAllocationModelWeightsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateAssetAllocationModel">
      <wsdl:input message="tns:updateAssetAllocationModelSoapIn" />
      <wsdl:output message="tns:updateAssetAllocationModelSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="createNewAccount">
      <wsdl:input message="tns:createNewAccountSoapIn" />
      <wsdl:output message="tns:createNewAccountSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAccountForCustomFieldValue">
      <wsdl:input message="tns:getAccountForCustomFieldValueSoapIn" />
      <wsdl:output message="tns:getAccountForCustomFieldValueSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getPendingTradesForAccount">
      <wsdl:input message="tns:getPendingTradesForAccountSoapIn" />
      <wsdl:output message="tns:getPendingTradesForAccountSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateDeliveryModeForEdocs">
      <wsdl:input message="tns:updateDeliveryModeForEdocsSoapIn" />
      <wsdl:output message="tns:updateDeliveryModeForEdocsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getEdocs">
      <wsdl:input message="tns:getEdocsSoapIn" />
      <wsdl:output message="tns:getEdocsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getPrepaidActivityCollection">
      <wsdl:input message="tns:getPrepaidActivityCollectionSoapIn" />
      <wsdl:output message="tns:getPrepaidActivityCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getPrepaidSummaryCollection">
      <wsdl:input message="tns:getPrepaidSummaryCollectionSoapIn" />
      <wsdl:output message="tns:getPrepaidSummaryCollectionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getFavoriteInstitutionsForBeneficiary">
      <wsdl:input message="tns:getFavoriteInstitutionsForBeneficiarySoapIn" />
      <wsdl:output message="tns:getFavoriteInstitutionsForBeneficiarySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateFavouriteInstitutionsForBeneficiary">
      <wsdl:input message="tns:updateFavouriteInstitutionsForBeneficiarySoapIn" />
      <wsdl:output message="tns:updateFavouriteInstitutionsForBeneficiarySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getAccountCustomFields">
      <wsdl:input message="tns:getAccountCustomFieldsSoapIn" />
      <wsdl:output message="tns:getAccountCustomFieldsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateAccountCustomFields">
      <wsdl:input message="tns:updateAccountCustomFieldsSoapIn" />
      <wsdl:output message="tns:updateAccountCustomFieldsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="getCustomFieldListValues">
      <wsdl:input message="tns:getCustomFieldListValuesSoapIn" />
      <wsdl:output message="tns:getCustomFieldListValuesSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="calculateCDSCForAccount">
      <wsdl:input message="tns:calculateCDSCForAccountSoapIn" />
      <wsdl:output message="tns:calculateCDSCForAccountSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="updateAccountDividendSetup">
      <wsdl:input message="tns:updateAccountDividendSetupSoapIn" />
      <wsdl:output message="tns:updateAccountDividendSetupSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="AccountServicesSoap" type="tns:AccountServicesSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="getAccountInfo">
      <soap:operation soapAction="http://enfs.com/webservices/getAccountInfo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getMailingAddress">
      <soap:operation soapAction="http://enfs.com/webservices/getMailingAddress" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getBeneficiary529">
      <soap:operation soapAction="http://enfs.com/webservices/getBeneficiary529" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceActivityCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getPlan529AccountBalanceActivityCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getActivityCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getActivityCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAuthorizedAccountCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getAuthorizedAccountCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAccount">
      <soap:operation soapAction="http://enfs.com/webservices/getAccount" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="isAccountClosed">
      <soap:operation soapAction="http://enfs.com/webservices/isAccountClosed" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceSummaryCollectionForAccount">
      <soap:operation soapAction="http://enfs.com/webservices/getPlan529AccountBalanceSummaryCollectionForAccount" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceSummaryCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getPlan529AccountBalanceSummaryCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getBalanceCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getBalanceCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getBalanceCollectionWithDateRange">
      <soap:operation soapAction="http://enfs.com/webservices/getBalanceCollectionWithDateRange" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="isAccountPayeeBeingUsed">
      <soap:operation soapAction="http://enfs.com/webservices/isAccountPayeeBeingUsed" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAchPayeeCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getAchPayeeCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAchPayee">
      <soap:operation soapAction="http://enfs.com/webservices/addAchPayee" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="deleteAchPayee">
      <soap:operation soapAction="http://enfs.com/webservices/deleteAchPayee" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getCheckWirePayeeCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getCheckWirePayeeCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAutomaticInvestmentCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getAutomaticInvestmentCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAutomaticInvestmentPlan">
      <soap:operation soapAction="http://enfs.com/webservices/addAutomaticInvestmentPlan" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="modifyAutomaticInvestmentPlan">
      <soap:operation soapAction="http://enfs.com/webservices/modifyAutomaticInvestmentPlan" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="deleteAutomaticInvestmentPlan">
      <soap:operation soapAction="http://enfs.com/webservices/deleteAutomaticInvestmentPlan" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAccountForCommonMailing">
      <soap:operation soapAction="http://enfs.com/webservices/addAccountForCommonMailing" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getStatementByFormCategory">
      <soap:operation soapAction="http://enfs.com/webservices/getStatementByFormCategory" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateStatementByFormCategory">
      <soap:operation soapAction="http://enfs.com/webservices/updateStatementByFormCategory" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getMailingAddressForMailingAddressType">
      <soap:operation soapAction="http://enfs.com/webservices/getMailingAddressForMailingAddressType" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateMailingAddresses">
      <soap:operation soapAction="http://enfs.com/webservices/updateMailingAddresses" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateSuccessorBeneficiaries">
      <soap:operation soapAction="http://enfs.com/webservices/updateSuccessorBeneficiaries" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addSuccessorBeneficiaries">
      <soap:operation soapAction="http://enfs.com/webservices/addSuccessorBeneficiaries" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateBeneficiary529">
      <soap:operation soapAction="http://enfs.com/webservices/updateBeneficiary529" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAccountMessages">
      <soap:operation soapAction="http://enfs.com/webservices/addAccountMessages" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAccountCustomField">
      <soap:operation soapAction="http://enfs.com/webservices/addAccountCustomField" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAssetAllocationModelWeights">
      <soap:operation soapAction="http://enfs.com/webservices/getAssetAllocationModelWeights" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateAssetAllocationModel">
      <soap:operation soapAction="http://enfs.com/webservices/updateAssetAllocationModel" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="createNewAccount">
      <soap:operation soapAction="http://enfs.com/webservices/createNewAccount" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAccountForCustomFieldValue">
      <soap:operation soapAction="http://enfs.com/webservices/getAccountForCustomFieldValue" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPendingTradesForAccount">
      <soap:operation soapAction="http://enfs.com/webservices/getPendingTradesForAccount" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateDeliveryModeForEdocs">
      <soap:operation soapAction="http://enfs.com/webservices/updateDeliveryModeForEdocs" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getEdocs">
      <soap:operation soapAction="http://enfs.com/webservices/getEdocs" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPrepaidActivityCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getPrepaidActivityCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPrepaidSummaryCollection">
      <soap:operation soapAction="http://enfs.com/webservices/getPrepaidSummaryCollection" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getFavoriteInstitutionsForBeneficiary">
      <soap:operation soapAction="http://enfs.com/webservices/getFavoriteInstitutionsForBeneficiary" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateFavouriteInstitutionsForBeneficiary">
      <soap:operation soapAction="http://enfs.com/webservices/updateFavouriteInstitutionsForBeneficiary" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAccountCustomFields">
      <soap:operation soapAction="http://enfs.com/webservices/getAccountCustomFields" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateAccountCustomFields">
      <soap:operation soapAction="http://enfs.com/webservices/updateAccountCustomFields" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getCustomFieldListValues">
      <soap:operation soapAction="http://enfs.com/webservices/getCustomFieldListValues" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="calculateCDSCForAccount">
      <soap:operation soapAction="http://enfs.com/webservices/calculateCDSCForAccount" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateAccountDividendSetup">
      <soap:operation soapAction="http://enfs.com/webservices/updateAccountDividendSetup" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="AccountServicesSoap12" type="tns:AccountServicesSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="getAccountInfo">
      <soap12:operation soapAction="http://enfs.com/webservices/getAccountInfo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getMailingAddress">
      <soap12:operation soapAction="http://enfs.com/webservices/getMailingAddress" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getBeneficiary529">
      <soap12:operation soapAction="http://enfs.com/webservices/getBeneficiary529" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceActivityCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getPlan529AccountBalanceActivityCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getActivityCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getActivityCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAuthorizedAccountCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getAuthorizedAccountCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAccount">
      <soap12:operation soapAction="http://enfs.com/webservices/getAccount" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="isAccountClosed">
      <soap12:operation soapAction="http://enfs.com/webservices/isAccountClosed" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceSummaryCollectionForAccount">
      <soap12:operation soapAction="http://enfs.com/webservices/getPlan529AccountBalanceSummaryCollectionForAccount" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPlan529AccountBalanceSummaryCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getPlan529AccountBalanceSummaryCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getBalanceCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getBalanceCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getBalanceCollectionWithDateRange">
      <soap12:operation soapAction="http://enfs.com/webservices/getBalanceCollectionWithDateRange" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="isAccountPayeeBeingUsed">
      <soap12:operation soapAction="http://enfs.com/webservices/isAccountPayeeBeingUsed" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAchPayeeCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getAchPayeeCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAchPayee">
      <soap12:operation soapAction="http://enfs.com/webservices/addAchPayee" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="deleteAchPayee">
      <soap12:operation soapAction="http://enfs.com/webservices/deleteAchPayee" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getCheckWirePayeeCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getCheckWirePayeeCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAutomaticInvestmentCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getAutomaticInvestmentCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAutomaticInvestmentPlan">
      <soap12:operation soapAction="http://enfs.com/webservices/addAutomaticInvestmentPlan" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="modifyAutomaticInvestmentPlan">
      <soap12:operation soapAction="http://enfs.com/webservices/modifyAutomaticInvestmentPlan" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="deleteAutomaticInvestmentPlan">
      <soap12:operation soapAction="http://enfs.com/webservices/deleteAutomaticInvestmentPlan" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAccountForCommonMailing">
      <soap12:operation soapAction="http://enfs.com/webservices/addAccountForCommonMailing" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getStatementByFormCategory">
      <soap12:operation soapAction="http://enfs.com/webservices/getStatementByFormCategory" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateStatementByFormCategory">
      <soap12:operation soapAction="http://enfs.com/webservices/updateStatementByFormCategory" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getMailingAddressForMailingAddressType">
      <soap12:operation soapAction="http://enfs.com/webservices/getMailingAddressForMailingAddressType" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateMailingAddresses">
      <soap12:operation soapAction="http://enfs.com/webservices/updateMailingAddresses" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateSuccessorBeneficiaries">
      <soap12:operation soapAction="http://enfs.com/webservices/updateSuccessorBeneficiaries" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addSuccessorBeneficiaries">
      <soap12:operation soapAction="http://enfs.com/webservices/addSuccessorBeneficiaries" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateBeneficiary529">
      <soap12:operation soapAction="http://enfs.com/webservices/updateBeneficiary529" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAccountMessages">
      <soap12:operation soapAction="http://enfs.com/webservices/addAccountMessages" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="addAccountCustomField">
      <soap12:operation soapAction="http://enfs.com/webservices/addAccountCustomField" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAssetAllocationModelWeights">
      <soap12:operation soapAction="http://enfs.com/webservices/getAssetAllocationModelWeights" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateAssetAllocationModel">
      <soap12:operation soapAction="http://enfs.com/webservices/updateAssetAllocationModel" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="createNewAccount">
      <soap12:operation soapAction="http://enfs.com/webservices/createNewAccount" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAccountForCustomFieldValue">
      <soap12:operation soapAction="http://enfs.com/webservices/getAccountForCustomFieldValue" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPendingTradesForAccount">
      <soap12:operation soapAction="http://enfs.com/webservices/getPendingTradesForAccount" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateDeliveryModeForEdocs">
      <soap12:operation soapAction="http://enfs.com/webservices/updateDeliveryModeForEdocs" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getEdocs">
      <soap12:operation soapAction="http://enfs.com/webservices/getEdocs" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPrepaidActivityCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getPrepaidActivityCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getPrepaidSummaryCollection">
      <soap12:operation soapAction="http://enfs.com/webservices/getPrepaidSummaryCollection" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getFavoriteInstitutionsForBeneficiary">
      <soap12:operation soapAction="http://enfs.com/webservices/getFavoriteInstitutionsForBeneficiary" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateFavouriteInstitutionsForBeneficiary">
      <soap12:operation soapAction="http://enfs.com/webservices/updateFavouriteInstitutionsForBeneficiary" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getAccountCustomFields">
      <soap12:operation soapAction="http://enfs.com/webservices/getAccountCustomFields" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateAccountCustomFields">
      <soap12:operation soapAction="http://enfs.com/webservices/updateAccountCustomFields" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="getCustomFieldListValues">
      <soap12:operation soapAction="http://enfs.com/webservices/getCustomFieldListValues" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="calculateCDSCForAccount">
      <soap12:operation soapAction="http://enfs.com/webservices/calculateCDSCForAccount" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="updateAccountDividendSetup">
      <soap12:operation soapAction="http://enfs.com/webservices/updateAccountDividendSetup" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="AccountServices">
    <wsdl:port name="AccountServicesSoap" binding="tns:AccountServicesSoap">
      <soap:address location="http://testpawebservices.geminifund.com/AccountServices.asmx" />
    </wsdl:port>
    <wsdl:port name="AccountServicesSoap12" binding="tns:AccountServicesSoap12">
      <soap12:address location="http://testpawebservices.geminifund.com/AccountServices.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>
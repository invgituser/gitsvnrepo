package com.invessence.broker.constants;

public interface BrokerServiceConstants
{
   static final String YEAR_MONTH_DAY_FORMAT = "yyyyMMdd";
   static final String YEAR_MONTH_FORMAT = "yyyyMM";

   static final String[] POSITION_TABLE_COLUMNS = {
      "ClientAccountID",
      "AccountAlias",
      "CurrencyPrimary",
      "AssetClass",
      "FXRateToBase",
      "Symbol",
      "Description",
      "ReportDate",
      "Side",
      "Quantity",
      "CostBasisPrice",
      "CostBasisMoney",
      "MarkPrice",
      "PositionValue",
      "FifoPnlUnrealized",
      "LevelOfDetail"};

   static final String[] ACCOUNT_TABLE_COLUMNS = {
      "ClientAccountID",
      "AccountAlias",
      "CurrencyPrimary",
      "Name",
      "AccountType",
      "CustomerType",
      "AccountCapabilities",
      "TradingPermissions",
      "DateOpened",
      "DateClosed",
      "Street",
      "Street2",
      "City",
      "State",
      "Country",
      "PostalCode"
   };

   static final String[] CASH_TRANSACTION_TABLE_COLUMN = {
      "clientAccountID",
      "currencyPrimary",
      "assetClass",
      "fxRateToBase",
      "symbol",
      "description",
      "reportDate",
      "amount",
      "type",
      "tradeID",
      "code"
   };

   static final String[] COMMISSION_TABLE_COLUMN =  {
      "clientAccountID",
      "fromDate",
      "toDate",
      "commission",
      "otherFee",
      "advisorFee"
   };

   static final String[] NAV_DAILY_TABLE_COLUMN = {
      "clientAccountID",
      "reportDate",
      "cash",
      "stock",
      "funds",
      "interestAccrual",
      "dividentAccrual",
      "total"
   };

   static final String[] TRADE_TABLE_COLUMN = {
      "clientAccountID",
      "currencyPrimary",
      "assetClass",
      "fxRateToBase",
      "symbol",
      "description",
      "tradeID",
      "reportDate",
      "quantity",
      "tradeprice",
      "proceed",
      "ibcommission",
      "levelofDetail"
   };

   static final String[] UNBNDLD_COMMISSION_TABLE_COLUMN = {
      "clientAccountID",
      "currencyPrimary",
      "assetClass",
      "fxRateToBase",
      "symbol",
      "description",
      "reportDate",
      "reportTime",
      "exchange",
      "buy_sell",
      "quantity",
      "price",
      "tradeID",
      "totalcommission",
      "brokerExecutionCharge",
      "brokerClearingCharge",
      "thirdPartyExecutionCharge",
      "thirdPartyRegulatoryCharge",
      "other"
   };
}

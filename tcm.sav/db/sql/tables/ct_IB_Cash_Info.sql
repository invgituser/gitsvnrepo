DROP TABLE IF EXISTS IB_Cash_Info;

CREATE TABLE IB_Cash_Info (
clientAccountID	VARCHAR(12)	NOT NULL	,
accountAlias	VARCHAR(20)	NULL	,
currencyPrimary	VARCHAR(20)	NULL	,
fromDate	VARCHAR(10)	NULL	,
toDate	VARCHAR(10)	NULL	,
startingCash	Double	NULL	,
endingCash	Double	NULL	,
PRIMARY KEY (clientAccountID, fromDate, toDate)	
);

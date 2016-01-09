DROP PROCEDURE IF EXISTS `sel_position`;

DELIMITER $$
CREATE PROCEDURE `sel_position`(
 	IN p_acctnum  bigint(20)
)
BEGIN

	DROP TEMPORARY TABLE IF EXISTS tmp_fetched_position_table;
	CREATE TEMPORARY TABLE tmp_fetched_position_table
	(
		 sortorder 		 INTEGER
		,acctnum 		 BIGINT(20)
		,clientAccountID VARCHAR(20)
		,lastname		 VARCHAR(30)
		,firstname		 VARCHAR(30)
		,advisor		 VARCHAR(30)
		,theme			 VARCHAR(30)
		,dateOpened		 VARCHAR(10)
		,accountAlias	 VARCHAR(20)
		,currencyPrimary VARCHAR(20)
		,assetClass		 VARCHAR(20)
		,color			 VARCHAR(10)
		,subclass		 VARCHAR(20)
		,fxRateToBase	 DOUBLE
		,symbol			 VARCHAR(20)
		,description	 VARCHAR(60)
		,reportDate		 VARCHAR(10)
		,side			 VARCHAR(10)
		,quantity		 INTEGER
		,costBasisPrice	 DOUBLE
		,costBasisMoney	 DOUBLE
		,markPrice		 DOUBLE
		,positionValue	 DOUBLE
		,fifoPnlUnrealized DOUBLE
		,levelOfDetail	 VARCHAR(20)
		,totalInvestment DOUBLE
		,weight			 DOUBLE
		,yield			 DOUBLE
		,expenseRatio	 DOUBLE
		,risk			 DOUBLE
		,ytdinvoiceFee	 DOUBLE
	);

	INSERT INTO tmp_fetched_position_table
	select
		 IFNULL(amg1.sortorder,999999) as sortorder
		,utp.`acctnum`
		,p.`clientAccountID`
		,ib.`lastname`
		,ib.`firstname`
		,utp.`advisor`
		,utp.`theme`
		,ib.`dateOpened`
		,p.`accountAlias`
		,p.`currencyPrimary`
		,IFNULL(sec.assetclass,p.assetClass) as assetClass
		,IFNULL(amg1.`color`,'#ffffff') as color
		,sec.`subclass` as subclass
		,p.`fxRateToBase`
		,p.`symbol`
		,p.`description`
		,p.`reportDate`
		,p.`side`
		,p.`quantity`
		,p.`costBasisPrice`
		,p.`costBasisMoney`
		,p.`markPrice`
		,p.`positionValue`
		,p.`fifoPnlUnrealized`
		,p.`levelOfDetail`
		,funct_get_actualCapital(`utp`.`acctnum`) as `totalInvestment`
		,(p.`positionValue` / funct_get_actualCapital(`utp`.`acctnum`)) as `weight`
		,(IFNULL((morningstar.dividendYield/100), sec.yield) * (p.`positionValue` / funct_get_actualCapital(`utp`.`acctnum`))) as yield
		,(IFNULL((morningstar.expenseRatio/100),sec.expenseRatio) * (p.`positionValue` / funct_get_actualCapital(`utp`.`acctnum`))) as expenseRatio
		,(IFNULL((morningstar.standardDeviation/100),sec.securityRiskSTD) * (p.`positionValue` / funct_get_actualCapital(`utp`.`acctnum`))) as risk
		,IFNULL(fees.ytdinvoiceFee,0) as ytdinvoiceFee
    from sec_assetclass_group as amg1,
		 user_trade_profile utp,
		 IB_Accounts ib
		 LEFT JOIN position p
		 ON (ib.IB_acctnum = p.`clientAccountID`)
		 LEFT JOIN sec_master sec
		 ON (p.`symbol` = sec.`ticker`)
		 LEFT JOIN morningstarData morningstar
		 ON (p.`symbol` = morningstar.`ticker`)
		 LEFT JOIN invdb.fees_charged fees
		 ON (fees.clientAccountID = p.`clientAccountID`
			AND fees.datecreated = invdb.funct_get_switch('LAST_BILLING_DAY'))
	WHERE utp.acctnum = ib.acctnum
	AND   IFNULL(sec.assetclass,p.assetClass) = amg1.assetclass
	AND   IFNULL(utp.theme,'POSITION') = IFNULL(amg1.theme,'POSITION')
    AND   utp.acctnum = p_acctnum
	AND   p.reportDate = invdb.funct_get_switch('BROKER_BDATE')
	;

	INSERT INTO tmp_fetched_position_table
	( sortorder, assetClass, color)
	SELECT
		IFNULL(amg1.sortorder,999999) as sortorder
		,IFNULL(amg1.assetclass,'Other') as assetClass
		,IFNULL(amg1.`color`,'#ffffff') as color
	FROM sec_assetclass_group as amg1
	WHERE amg1.theme = 'POSITION'
	;

	SELECT *
	FROM tmp_fetched_position_table
	ORDER BY 1;
	

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `sp_upload_performance`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_performance`()
BEGIN
  DECLARE tDate				date;
  DECLARE tTodayDate		varchar(8);
  DECLARE tFirstofMonth		varchar(8);
  DECLARE tLastdayofMonth	varchar(8);
  DECLARE tFirstdayofYear	varchar(8);
  DECLARE tLastdayofYear  	varchar(8);
  DECLARE t2BusinessDate	varchar(8);

  set tTodayDate = `funct_get_switch`('BROKER_BDATE');
  set tFirstofMonth = `funct_get_switch`('1ST_BDATE_THIS_MONTH');
  set tLastdayofMonth = `funct_get_switch`('LAST_BDATE_OF_MONTH');
  set t2BusinessDate = `funct_get_switch`('T-2_BUSINESS_DATE');
 
  begin
	-- First set the Beginning to year to 01-05-Year, then find the first business date for master account only.
	set tFirstdayofYear = concat(substring(tTodayDate,1,4),'0105');
	select min(reportDate)
	into tFirstdayofYear
	from nav_daily
    where clientAccountID = 'F1230824'
	and   reportDate < tFirstdayofYear
	;

	-- First set the T-2 date from switch, then find the first business date <= this date for master account only.
	-- We only need to do this because we don't have a holiday table.
	set tDate = get_business_date(str_to_date(tTodayDate,'%Y%m%d'),-5);
	select max(reportDate)
	into t2BusinessDate
	from nav_daily
    where clientAccountID = 'F1230824'
	and   reportDate <= t2BusinessDate
	and   reportDate >= DATE_FORMAT(tDate,'%Y%m%d')
	;

  end;

  
  set tLastdayofYear = concat(substring(tTodayDate,1,4),'1231');

    DROP TABLE IF EXISTS ttt_dates;
	CREATE temporary table ttt_dates
		(
		  `clientAccountID`     varchar(20) NOT NULL,
		  `reportDate` 			varchar(8),
		  `reportDate_prev`		varchar(8),
		  `reportDate_bom`		varchar(8),
		  `reportDate_boy`		varchar(8)
		);

    DROP TABLE IF EXISTS ttt_dates_prev;
	CREATE temporary table ttt_dates_prev
		(
		  `clientAccountID` varchar(20) NOT NULL,
		  `reportDate`		varchar(8)
		);
	

    DROP TABLE IF EXISTS ttt_dates_bom;
	CREATE temporary table ttt_dates_bom
		(
		  `clientAccountID` varchar(20) NOT NULL,
		  `reportDate`		varchar(8)
		);

    DROP TABLE IF EXISTS ttt_dates_boy;
	CREATE temporary table ttt_dates_boy
		(
		  `clientAccountID` varchar(20) NOT NULL,
		  `reportDate`		varchar(8)
		);

	INSERT INTO ttt_dates_prev (`clientAccountID`, `reportDate`)
	SELECT `clientAccountID`, max(`reportDate`) from nav_daily 
	where `reportDate` < tTodayDate and cash > 0 
	group by `clientAccountID`;

	INSERT INTO ttt_dates_bom (`clientAccountID`, `reportDate`)
	SELECT `clientAccountID`, min(`reportDate`) from nav_daily 
	where `reportDate` >= tFirstofMonth  and cash > 0
	group by `clientAccountID`;

	INSERT INTO ttt_dates_boy (`clientAccountID`, `reportDate`)
	SELECT `clientAccountID`, min(`reportDate`) from nav_daily 
	where `reportDate` >= tFirstdayofYear and cash > 0
	group by `clientAccountID`;

	INSERT INTO ttt_dates
		(
		  `clientAccountID`,
		  `reportDate`,
		  `reportDate_prev`,
		  `reportDate_bom`,
		  `reportDate_boy`
		)
	SELECT n.`clientAccountID`, n.`reportDate`
		 , n0.`reportDate`, mtd.`reportDate`, ytd.`reportDate`
	FROM nav_daily n
	LEFT JOIN ttt_dates_prev as n0
		ON (n.`clientAccountID` = n0.`clientAccountID`)
	LEFT JOIN ttt_dates_bom as mtd
		ON (n.`clientAccountID` = mtd.`clientAccountID`)
	LEFT JOIN ttt_dates_boy as ytd
		ON (n.`clientAccountID` = ytd.`clientAccountID`)
	WHERE n.cash > 0
	;
	

    DROP TABLE IF EXISTS ttt_divident;
	CREATE temporary table ttt_divident
		(
		  `clientAccountID`     varchar(20) NOT NULL,
		  `divident` 			Double DEFAULT 0.0,
		  `deposit`				Double Default 0.0,
		  `withdrawl`			Double Default 0.0		  
		);

		INSERT INTO ttt_divident
		(
		  `clientAccountID`,
		  `divident`,
		  `deposit`,
		  `withdrawl`
		)
		SELECT `d1`.`clientAccountID`, 
				      SUM(CASE WHEN (`d1`.`type` like 'Payment%Dividend%') THEN `d1`.`amount`
							   ELSE 0.0
						  END) as divident,
					  SUM(CASE WHEN (`d1`.`type` like 'Deposit%' AND `d1`.`amount` > 0) then `d1`.`amount`
						  ELSE 0.0
						  END) as deposit,
					  SUM(CASE WHEN (`d1`.`type` like '%Withdrawal%' AND `d1`.`amount` < 0) then `d1`.`amount`
						  ELSE 0.0
						  END) as withdrawl
			   FROM `cash_transaction` as d1
			   WHERE (`d1`.`type` like 'Payment%Dividend%'
					  OR `d1`.`type` like 'Deposits%'
					  OR `d1`.`type` like '%Withdrawal%')
			   AND   `d1`.`reportDate` >= tTodayDate
			   GROUP BY `d1`.`clientAccountID`
		;


    DROP TABLE IF EXISTS ttt_dividentMTD;
	CREATE temporary table ttt_dividentMTD
		(
		  `clientAccountID`     varchar(20) NOT NULL,
		  `divident` 			Double DEFAULT 0.0,
		  `deposit`				Double Default 0.0,
		  `withdrawl`			Double Default 0.0
		);

		INSERT INTO ttt_dividentMTD
		(
		  `clientAccountID`,
		  `divident`,
		  `deposit`,
		  `withdrawl`
		)
		SELECT `d1`.`clientAccountID`, 
				      SUM(CASE WHEN (`d1`.`type` like 'Payment%Dividend%') THEN `d1`.`amount`
							   ELSE 0.0
						  END) as divident,
					  SUM(CASE WHEN (`d1`.`type` like 'Deposit%' AND `d1`.`amount` > 0) then `d1`.`amount`
						  ELSE 0.0
						  END) as deposit,
					  SUM(CASE WHEN (`d1`.`type` like '%Withdrawal%' AND `d1`.`amount` < 0) then `d1`.`amount`
						  ELSE 0.0
						  END) as withdrawl
			   FROM `cash_transaction` as d1
			   WHERE (`d1`.`type` like 'Payment%Dividend%'
					  OR `d1`.`type` like 'Deposits%'
					  OR `d1`.`type` like '%Withdrawal%')
			   AND   `d1`.`reportDate` >= tFirstofMonth
			   GROUP BY `d1`.`clientAccountID`
		;

    DROP TABLE IF EXISTS ttt_dividentYTD;
	CREATE temporary table ttt_dividentYTD
		(
		  `clientAccountID`     varchar(20) NOT NULL,
		  `divident` 			Double DEFAULT 0.0,
		  `deposit`				Double Default 0.0,
		  `withdrawl`			Double Default 0.0
		);

		INSERT INTO ttt_dividentYTD
		(
		  `clientAccountID`,
		  `divident`,
		  `deposit`,
		  `withdrawl`
		)
		SELECT `d1`.`clientAccountID`, 
				      SUM(CASE WHEN (`d1`.`type` like 'Payment%Dividend%') THEN `d1`.`amount`
							   ELSE 0.0
						  END) as divident,
					  SUM(CASE WHEN (`d1`.`type` like 'Deposit%' AND `d1`.`amount` > 0) then `d1`.`amount`
						  ELSE 0.0
						  END) as deposit,
					  SUM(CASE WHEN (`d1`.`type` like '%Withdrawal%' AND `d1`.`amount` < 0) then `d1`.`amount`
						  ELSE 0.0
						  END) as withdrawl
			   FROM `cash_transaction` as d1
			   WHERE (`d1`.`type` like 'Payment%Dividend%'
					  OR `d1`.`type` like 'Deposits%'
					  OR `d1`.`type` like '%Withdrawal%')
			   AND   `d1`.`reportDate` >= tFirstdayofYear
			   GROUP BY `d1`.`clientAccountID`
		;

-- 	   select  tTodayDate, tFirstofMonth, tLastdayofMonth, tFirstdayofYear,tLastdayofYear;
-- 		select * from ttt_divident;
-- 		select * from ttt_dividentMTD;
-- 		select * from ttt_dividentYTD;
-- 
    DROP TABLE IF EXISTS ttt_nav;
	CREATE temporary table ttt_nav
		(
		  `clientAccountID`     varchar(20) NOT NULL,
		  `reportDate`			varchar(8),
		  `cash`			    Double Default 0.0,
		  `stock` 				Double DEFAULT 0.0,
		  `reportDate_prev`		varchar(8),
		  `daily_cash_prior`	Double Default 0.0,
		  `daily_stock_prior`	Double DEFAULT 0.0,
		  `reportDate_bom`		varchar(8),
		  `mtd_cash_bom`		Double Default 0.0,
		  `mtd_stock_bom`		Double DEFAULT 0.0,
		  `reportDate_boy`		varchar(8),
		  `ytd_cash_boy`		Double Default 0.0,
		  `ytd_stock_boy`		Double DEFAULT 0.0
		);

		INSERT INTO ttt_nav
		(
		  `clientAccountID`,
		  `reportDate`,
		  `cash`,
		  `stock`,
		  `reportDate_prev`,
		  `daily_cash_prior`,
		  `daily_stock_prior`,
		  `reportDate_bom`,
		  `mtd_cash_bom`,
		  `mtd_stock_bom`,
		  `reportDate_boy`,
		  `ytd_cash_boy`,
		  `ytd_stock_boy`
		)
		SELECT  `d1`.`clientAccountID`,
			    `d1`.`reportDate`,
				`n`.`cash`,
				`n`.`stock` + `n`.`funds`,
			    `d1`.`reportDate_prev`,
				`n0`.`cash` as n0Cash,
				`n0`.`stock` + `n0`.`funds` as n0stock,
			    `d1`.`reportDate_bom`,
				`mtd`.`cash` mtdcash,
				`mtd`.`stock` + `mtd`.`funds` mtdstock,
			    `d1`.`reportDate_boy`,
				`ytd`.`cash` ytdcash,
				`ytd`.`stock` + `ytd`.`funds` ytdstock
		FROM `ttt_dates` as d1
			LEFT JOIN nav_daily n
			ON (d1.clientAccountID = n.clientAccountID
			AND d1.reportDate = n.reportDate )
			LEFT JOIN nav_daily n0
			ON (d1.clientAccountID = n.clientAccountID
			AND d1.reportDate_prev = n.reportDate )
			LEFT JOIN nav_daily mtd
			ON (d1.clientAccountID = n.clientAccountID
			AND d1.reportDate_bom = n.reportDate )
			LEFT JOIN nav_daily ytd
			ON (d1.clientAccountID = n.clientAccountID
			AND d1.reportDate_boy = n.reportDate )
		;

	   SELECT 
		  ib.`IB_acctnum`,
		  d.`divident` as divident,
		  d.`deposit` as deposit,
		  d.`withdrawl` as withdrawl,
		  m.`divident` dividentMTD,
		  m.`deposit` depositMTD,
		  m.`withdrawl` withdrawlMTD,
		  y.`divident` dividentYTD,
		  y.`deposit` depositYTD,
		  y.`withdrawl` withdrawlYTD,
		  nav.reportDate,
		  nav.cash as cash,
		  nav.stock as stock,
		  nav.reportDate_prev,
		  nav.daily_cash_prior as daily_cash_prior,
		  nav.daily_stock_prior as daily_stock_prior,
		  nav.reportDate_bom,
		  nav.mtd_cash_bom as mtd_cash,
		  nav.mtd_stock_bom as mtd_stock,
		  nav.reportDate_boy,
		  nav.ytd_cash_boy as ytd_cash,
		  nav.ytd_stock_boy as ytd_stock

	   FROM `IB_Accounts` ib
	   LEFT JOIN ttt_divident d
		 ON (ib.`IB_acctnum` = d.`clientAccountID`)
	   LEFT JOIN ttt_dividentMTD m
		 ON (ib.`IB_acctnum` = m.`clientAccountID`)
	   LEFT JOIN ttt_dividentYTD y
		 ON (ib.`IB_acctnum` = y.`clientAccountID`)
	   LEFT JOIN ttt_nav nav
		 ON (ib.`IB_acctnum` = nav.`clientAccountID`)
		;


END$$
DELIMITER ;

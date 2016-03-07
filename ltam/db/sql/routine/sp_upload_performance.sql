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
  -- set tTodayDate = '20141031';
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
	and   reportDate >= tFirstdayofYear
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
-- 
	INSERT INTO ttt_dates_boy (`clientAccountID`, `reportDate`)
	SELECT `clientAccountID`, min(`reportDate`) from nav_daily 
	where `reportDate` >= tFirstdayofYear and cash > 0
	group by `clientAccountID`;
-- 
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
	WHERE n.reportDate = tTodayDate
	AND   n.cash > 0
	;

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
				IFNULL(`n`.`cash`, 0.0) as cash,
				IFNULL(`n`.`stock`, 0.0) + IFNULL(`n`.`funds`, 0.0) as stock,
			    `d1`.`reportDate_prev`,
				IFNULL(`n0`.`cash`, 0.0) as n0Cash,
				IFNULL(`n0`.`stock`, 0.0) + IFNULL(`n0`.`funds`, 0.0) as n0stock,
			    `d1`.`reportDate_bom`,
				IFNULL(`mtd`.`cash`, 0.0) mtdcash,
				IFNULL(`mtd`.`stock`, 0.0) + IFNULL(`mtd`.`funds`, 0.0) mtdstock,
			    `d1`.`reportDate_boy`,
				IFNULL(`ytd`.`cash`, 0.0) ytdcash,
				IFNULL(`ytd`.`stock`, 0.0) + IFNULL(`ytd`.`funds`, 0.0) ytdstock
		FROM `ttt_dates` as d1
		LEFT JOIN nav_daily n
			ON (d1.clientAccountID = n.clientAccountID AND d1.reportDate = n.reportDate)
		LEFT JOIN nav_daily n0
			ON (d1.clientAccountID = n0.clientAccountID AND d1.reportDate_prev = n0.reportDate)
		LEFT JOIN nav_daily mtd
			ON (d1.clientAccountID = mtd.clientAccountID AND d1.reportDate_bom = mtd.reportDate)
		LEFT JOIN nav_daily ytd
			ON (d1.clientAccountID = ytd.clientAccountID AND d1.reportDate_boy = ytd.reportDate)
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
			   AND   `d1`.`reportDate` >= t2BusinessDate
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

	DELETE FROM performance_info
	WHERE reportDate = tTodayDate;

	INSERT INTO performance_info (
			  clientAccountID
		,	  reportDate
		,	  reportDate_prev
		,	  reportDate_bom
		,	  reportDate_boy
		,	  daily_divident
		,	  daily_deposit
		,	  daily_withdrawl
		,	  daily_unrealized
		,	  daily_realized
		,	  daily_nav_cash
		,	  daily_nav_stock
		,	  daily_nav_cash_prior
		,	  daily_nav_stock_prior
		,	  daily_performance
		,	  mtd_divident
		,	  mtd_deposit
		,	  mtd_withdrawl
		,	  mtd_unrealized
		,	  mtd_realized
		,	  bom_nav_cash
		,	  bom_nav_stock
		,	  mtd_performance
		,	  ytd_divident
		,	  ytd_deposit
		,	  ytd_withdrawl
		,	  ytd_unrealized
		,	  ytd_realized
		,	  boy_nav_cash
		,	  boy_nav_stock
		,	  ytd_performance
		,	  created
		)
	   SELECT 
		   nav.`clientAccountID`
		  ,nav.`reportDate`
		  ,nav.`reportDate_prev`
		  ,nav.`reportDate_bom`
		  ,nav.`reportDate_boy`
		  ,IFNULL(d.`divident`,0.0) 			as daily_divident
		  ,IFNULL(d.`deposit`,0.0) 				as daily_deposit
		  ,IFNULL(d.`withdrawl`,0.0) 			as daily_withdrawl
		  , 0.0 					as daily_unrealized
		  , 0.0 					as daily_realized
		  ,IFNULL(`cash`,0.0) 					as daily_nav_cash
		  ,IFNULL(`stock`,0.0) 					as daily_nav_stock
		  ,IFNULL(`daily_cash_prior`,0.0) 		as daily_nav_cash_prior
		  ,IFNULL(`daily_stock_prior`,0.0) 		as daily_nav_stock_prior
		  ,	CASE WHEN (	`daily_cash_prior` + `daily_stock_prior` <> 0)
				THEN (((`cash` + `stock`) - (`daily_cash_prior` + `daily_stock_prior`)) /  (`daily_cash_prior` + `daily_stock_prior`)) 
				ELSE 0.0
			END as daily_performance
		  ,IFNULL(m.`divident`,0.0) 			as mtd_divident
		  ,IFNULL(m.`deposit`,0.0) 				as mtd_deposit
		  ,IFNULL(m.`withdrawl`,0.0) 			as mtd_withdrwal
		  , 0.0 					as mtd_unrealized
		  , 0.0 					as mtd_realized
		  ,IFNULL(`mtd_cash_bom`,0.0) 			as bom_nav_cash
		  ,IFNULL(`mtd_stock_bom`,0.0) 			as bom_nav_stock
		  ,	CASE WHEN (	`mtd_cash_bom` + `mtd_stock_bom` - IFNULL(m.`deposit`,0.0) + IFNULL(m.`withdrawl`,0.0) <> 0)
				THEN (((`cash` + `stock` - IFNULL(m.`deposit`,0.0) + IFNULL(m.`withdrawl`,0.0)) - (`mtd_cash_bom` + `mtd_stock_bom` - IFNULL(m.`deposit`,0.0) + IFNULL(m.`withdrawl`,0.0))) /  (`mtd_cash_bom` + `mtd_stock_bom` - IFNULL(m.`deposit`,0.0) + IFNULL(m.`withdrawl`,0.0))) 
				ELSE 0.0
			END as mtd_performance
		  ,IFNULL(y.`divident`,0.0) 			as ytd_divident
		  ,IFNULL(y.`deposit`,0.0)				as ytd_deposit
		  ,IFNULL(y.`withdrawl`,0.0) 			as ytd_withdrawl
		  , 0.0 					as ytd_unrealized
		  , 0.0 					as ytd_realized
		  ,IFNULL(`ytd_cash_boy`,0.0)			as boy_nav_cash
		  ,IFNULL(`ytd_stock_boy`,0.0)			as boy_nav_stock
		  ,	CASE WHEN (	IFNULL(`ytd_cash_boy`,0.0) + IFNULL(`ytd_stock_boy`,0.0) - IFNULL(y.`deposit`,0.0) + IFNULL(y.`withdrawl`,0.0) <> 0)
				THEN (((IFNULL(`cash`,0.0) + IFNULL(`stock`,0.0) - IFNULL(y.`deposit`,0.0) + IFNULL(y.`withdrawl`,0.0)) - (IFNULL(`ytd_cash_boy`,0.0) + IFNULL(`ytd_stock_boy`,0.0) - IFNULL(y.`deposit`,0.0) + IFNULL(y.`withdrawl`,0.0))) /  (IFNULL(`ytd_cash_boy`,0.0) + IFNULL(`ytd_stock_boy`,0.0) - IFNULL(y.`deposit`,0.0) + IFNULL(y.`withdrawl`,0.0))) 
				ELSE 0.0
			END as ytd_performance
		  , CURDATE()					as created
	   FROM ttt_nav nav
	   LEFT JOIN ttt_divident d
		 ON (nav.`clientAccountID` = d.`clientAccountID`)
	   LEFT JOIN ttt_dividentMTD m
		 ON (nav.`clientAccountID` = m.`clientAccountID`)
	   LEFT JOIN ttt_dividentYTD y
		 ON (nav.`clientAccountID` = y.`clientAccountID`)
		;

END$$
DELIMITER ;

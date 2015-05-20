DROP PROCEDURE IF EXISTS `sp_upload_billing`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_billing`()
BEGIN
  DECLARE tNextCycleDate   varchar(8);
  DECLARE tFirstofMonth    varchar(8);
  DECLARE tLastdayofMonth  varchar(8);
  DECLARE tCommissionData  Integer;
  DECLARE tFirstDay        varchar(8);
  DECLARE tLastDay         varchar(8);
  DECLARE tPriorFirstDay   varchar(8);
  DECLARE tPriorLastDay    varchar(8);
  DECLARE tTodayDate       varchar(8);

  set tNextCycleDate = `funct_get_switch`('FIRST_DAY_OF_MONTH');

  select count(*)
  into tCommissionData
  from commission
  where fromDate >= tNextCycleDate;

  IF (tCommissionData > 0)
	THEN

		set tTodayDate = DATE_FORMAT(now(),'%Y%m%d');
		set tPriorFirstDay = funct_FirstDayofPriorMonth(tNextCycleDate);
		set tPriorLastDay = date_format(last_day(str_to_date(tPriorFirstDay,'%Y%m%d')),'%Y%m%d');
		set tFirstDay = concat(substring(tNextCycleDate,1,6),'01');
		set tLastDay = date_format(last_day(str_to_date(tNextCycleDate,'%Y%m%d')),'%Y%m%d');


		DROP temporary TABLE IF EXISTS fees_calculated;
		CREATE temporary table fees_calculated
		(
		  `clientAccountID`     varchar(20) NOT NULL,
		  `processed`           varchar(1) DEFAULT 'N',
		  `fromDate` 			varchar(8) NOT NULL,
		  `toDate` 				varchar(8) NOT NULL,
		  `firstBusinessDate`   varchar(8) NOT NULL,
		  `lastBusinessDate`     varchar(8) NOT NULL,
		  `billingYear`         varchar(4) NOT NULL,
		  `advisor` 			varchar(30) NOT NULL,
		  `cash`                double DEFAULT 0,
		  `invested` 			double DEFAULT 0,
		  `priorfromDate`       varchar(8) default null,
		  `priortoDate`         varchar(8) DEFAULT null,
		  `priorcash`           double DEFAULT 0,
		  `priorinvested`       double default 0,
		  `billingDays`         integer default 0,
		  `commission` 			double DEFAULT 0,
		  `otherFee` 			double DEFAULT 0,
		  `invessenceFee`  		double DEFAULT 0,
		  `advisorFee` 			double DEFAULT 0,
		  `totalAdvisorFee`     double DEFAULT 0,
		  `invoiceFee`          double DEFAULT 0,
		  `ytdcommission`       double DEFAULT 0,
		  `ytdotherFee`         double DEFAULT 0,
		  `ytdinvessenceFee`    double DEFAULT 0,
		  `ytdAdvisorFee`       double DEFAULT 0,
		  `ytdtotalAdvisorFee`  double DEFAULT 0,
		  `ytdinvoiceFee`       double DEFAULT 0,
		  `datecreated`        varchar(8) default null
		);

		INSERT INTO fees_calculated
		(
		  `clientAccountID`,
		  `processed`,
		  `fromDate`,
		  `toDate`,
		  `firstBusinessDate`,
		  `lastBusinessDate`,
		  `billingYear`,
		  `advisor`,
		  `cash`,
		  `invested`,
		  `priorfromDate`,
		  `priortoDate`,
		  `billingDays`,
		  `commission`,
		  `otherFee`,
		  `invessenceFee`,
		  `advisorFee`,
		  `datecreated`
		)
		SELECT 
			commission.clientAccountID
			,CASE WHEN (IFNULL(nav_daily.total,0.0) = 0.0) THEN 'X'
				  WHEN (upper(IB_Accounts.accountStatus) in ('Active')) THEN 'N'
				  ELSE 'S'
             END as processed
			,tFirstDay as firstdayofmonth
			,tLastDay as lastdayofMonth
			,commission.fromDate
			,commission.toDate
			,substring(commission.fromDate,1,4) as billingYear
			,IFNULL(user_trade_profile.advisor,'Invessence') as advisor 
			,ROUND(nav_daily.cash,2) as cash
			,ROUND(nav_daily.total - nav_daily.cash,2) as invested
			,tPriorFirstDay as priorFirstDay
			,tPriorLastDay as priotLastDay
			-- ,fee.priorCapital
			,funct_DaysInThisMonth(commission.toDate) as daysinMonth
			,commission.commission as commission 
			,commission.otherFee as otherfee
			,CASE WHEN (upper(IB_Accounts.accountStatus) in ('Active'))
					THEN funct_calc_invessence_fee(nav_daily.total,user_trade_profile.advisor,IB_Accounts.dateOpened, commission.toDate)
				  ELSE 0.0
             END as invesseceFee
			,CASE WHEN (upper(IB_Accounts.accountStatus) in ('Active')) 
					THEN funct_calc_advisor_fee(nav_daily.total,user_trade_profile.advisor,IB_Accounts.dateOpened, commission.toDate)
				  ELSE 0.0
             END as advisorFee
			,tTodayDate
		FROM commission,
			 IB_Accounts, 
			 nav_daily,
			 user_trade_profile
		WHERE commission.clientAccountID = IB_Accounts.IB_acctnum
		AND   IB_Accounts.acctnum = user_trade_profile.acctnum
		AND   commission.clientAccountID = nav_daily.clientAccountID
		AND  commission.toDate = nav_daily.reportDate
		AND   commission.fromDate >= tNextCycleDate
		;

		DELETE FROM fees_charged
		WHERE Concat(clientAccountID,fromDate,toDate) in (select concat(a.clientAccountID,a.fromDate,a.toDate) from fees_calculated as a)
		;

		INSERT into fees_charged
		(
				`clientAccountID`
			,   `processed`
			,	`fromDate`
			,	`toDate`
			,	`firstBusinessDate`
			,	`lastBusinessDate`
			,	`billingYear`
			,	`advisor`
			,	`cash`
			,	`invested`
			,	`priorfromDate`
			,	`priortoDate`
			,	`priorcash`
			,	`priorinvested`
			,	`billingDays`
			,	`commission`
			,	`otherFee`
			,	`invessenceFee`
			,	`advisorFee`
			,	`totalAdvisorFee`
			,	`invoiceFee`
			,	`ytdcommission`
			,	`ytdotherFee`
			,	`ytdinvessenceFee`
			,	`ytdAdvisorFee`
			,	`ytdtotalAdvisorFee`
			,	`ytdinvoiceFee`
			,   `datecreated`
		)
		SELECT
				`newfees`.`clientAccountID`
			,	CASE 
				  WHEN (`newfees`.`processed` <> 'N' ) THEN `newfees`.`processed`
				  WHEN (`newfees`.`invessenceFee` + 
						`newfees`.`advisorFee` + 
						abs(`newfees`.`commission`) +
						abs(`newfees`.`otherFee`) = 0.0) THEN 'W'
				  ELSE `newfees`.`processed`
				END as processed
			,	`newfees`.`fromDate`
			,	`newfees`.`toDate`
			,	`newfees`.`firstBusinessDate`
			,	`newfees`.`lastBusinessDate`
			,	`newfees`.`billingYear`
			,	`newfees`.`advisor`
			,	`newfees`.`cash`
			,	`newfees`.`invested`
			,	`newfees`.`priorfromDate`
			,	`newfees`.`priortoDate`
			,   `prior`.`cash`
			,   `prior`.`invested`
			,	`newfees`.`billingDays`
			,	CASE 
					WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) THEN ABS(`newfees`.`commission`)
					ELSE `newfees`.`commission`
				END as commission
			,	CASE 
					WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) THEN ABS(`newfees`.`otherFee`)
					ELSE `newfees`.`otherFee`
				END as otherFee
			,	round(`newfees`.`invessenceFee`,2) as invessenceFee
			,	round(`newfees`.`advisorFee`,2) as advisorFee
			,	round(`newfees`.`invessenceFee` + `newfees`.`advisorFee`,2) as totalAdvisorFee
			,	CASE 
					WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) 
						THEN round(abs(`newfees`.`commission`) + abs(`newfees`.`otherFee`) + `newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
					ELSE round(`newfees`.`commission` + `newfees`.`otherFee` + `newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
				END as invoiceFee
			,   CASE WHEN (prior.billingYear = substring(`newfees`.`firstBusinessDate`,1,4))
					THEN CASE 
							WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) 
								THEN round(abs(`newfees`.`commission`) + IFNULL(`prior`.`ytdcommission`,0),2)
								ELSE round(`newfees`.`commission` + IFNULL(`prior`.`ytdcommission`,0),2)
						  END
					ELSE CASE 
							WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) 
								THEN round(abs(`newfees`.`commission`) + 0.0,2)
								ELSE round(`newfees`.`commission` + 0.0,2)
						  END
				END as ytdcommission
			,   CASE WHEN (prior.billingYear = substring(`newfees`.`firstBusinessDate`,1,4))
					THEN CASE 
							WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) 
								THEN round(abs(`newfees`.`otherFee`) + IFNULL(`prior`.`ytdotherFee`,0),2)
								ELSE round(`newfees`.`otherFee` + IFNULL(`prior`.`ytdotherFee`,0),2)
						  END
					ELSE CASE 
							WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) 
								THEN round(abs(`newfees`.`otherFee`) + 0.0,2)
								ELSE round(`newfees`.`otherFee` + 0.0,2)
						  END
				END as ytdcommission
			,   CASE WHEN (prior.billingYear = substring(`newfees`.`firstBusinessDate`,1,4))
					THEN round(IFNULL(`prior`.`ytdinvessenceFee`,0) + `newfees`.`invessenceFee`,2)
					ELSE round(`newfees`.`invessenceFee`,2)
				END as ytdinvessenceFee
			,   CASE WHEN (prior.billingYear = substring(`newfees`.`firstBusinessDate`,1,4))
					THEN round(IFNULL(`prior`.`ytdtotalAdvisorFee`,0) + `newfees`.`advisorFee`,2)
					ELSE round(`newfees`.`advisorFee`,2)
				END as ytdAdvisorFee
			,   CASE WHEN (prior.billingYear = substring(`newfees`.`firstBusinessDate`,1,4))
					THEN round(IFNULL(`prior`.`ytdtotalAdvisorFee`,0)+ `newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
					ELSE round(`newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
				END as ytdtotalAdvisorFee
			,   CASE WHEN (prior.billingYear = substring(`newfees`.`firstBusinessDate`,1,4))
					THEN CASE 
							WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) 
								THEN round(IFNULL(`prior`.`ytdinvoiceFee`,0) + abs(`newfees`.`commission`) + abs(`newfees`.`otherFee`) + `newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
								ELSE round(IFNULL(`prior`.`ytdinvoiceFee`,0) + `newfees`.`commission` + `newfees`.`otherFee` + `newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
						  END
					ELSE CASE 
							WHEN (`newfees`.`invessenceFee` + `newfees`.`advisorFee` = 0.0) 
								THEN round(abs(`newfees`.`commission`) + abs(`newfees`.`otherFee`) + `newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
								ELSE round(`newfees`.`commission` + `newfees`.`otherFee` + `newfees`.`invessenceFee` + `newfees`.`advisorFee`,2)
						  END
				END as ytdinvoiceFee
			,   `newfees`.`datecreated`
		FROM `fees_calculated` as `newfees`
		LEFT JOIN fees_charged prior
			ON (newfees.clientAccountID = prior.clientAccountID
				and `newfees`.`priorfromDate` = prior.`fromDate`
				)
		;

		CALL `sp_invessence_switch_post`('LAST_BILLING_DAY', tTodayDate);
		
  END IF;

END$$
DELIMITER ;

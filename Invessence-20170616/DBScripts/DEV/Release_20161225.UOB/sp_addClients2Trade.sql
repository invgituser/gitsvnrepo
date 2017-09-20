DROP PROCEDURE IF EXISTS `sp_addClients2Trade`;

DELIMITER $$
CREATE PROCEDURE `sp_addClients2Trade`()
BEGIN
	DECLARE vPostDate datetime;
	DECLARE vBusinessDate varchar(10);
	DECLARE vNumofDays Integer;
	DECLARE vVariance DECIMAL(5,2);

	set vPostDate = now();

	begin
		select cast(value as unsigned) 
		into vNumofDays
		from invessence_switch where name = 'DAYS_TO_REBALANCE';

		DELETE FROM pretrade_details;
        
        DROP TEMPORARY table IF EXISTS `position_balance`;
	    CREATE TEMPORARY TABLE `position_balance` AS 
		SELECT `position`.`clientAccountID`,
			`sec_master`.`assetclass`,
			SUM(`position`.`costBasisMoney`) as costBasisMoney,
			SUM(`position`.`positionValue`) as positionValue,
			SUM(`position`.`pnlUnrealized`) as fifoPnlUnrealized
 		FROM `invdb`.`ext_position` `position`,
			 `invdb`.`sec_master`
	    WHERE `position`.`symbol` = `sec_master`.`ticker`
        AND   `position`.`reportDate` = funct_get_switch('BROKER_BDATE')
        GROUP BY `position`.`clientAccountID`,
				 `sec_master`.`assetclass`
		;
        
		DROP TEMPORARY table IF EXISTS `position_value`;
	    CREATE TEMPORARY TABLE `position_value` AS 
		SELECT `position`.`clientAccountID`,
			`ext_nav`.`total` as `navTotal`,
			SUM(`position`.`costBasisMoney`) as totalcostBasis,
			SUM(`position`.`positionValue`) as totalPosValue,
			SUM(`position`.`pnlUnrealized`) as totalPNL
 		FROM `invdb`.`ext_position` `position`,
			 `invdb`.`ext_nav`
	    WHERE `position`.`reportDate` = funct_get_switch('BROKER_BDATE')
        AND   `position`.`clientAccountID` = `ext_nav`.`clientAccountID`
		and   `ext_nav`.`reportDate` = funct_get_switch('BROKER_BDATE')
        GROUP BY `position`.`clientAccountID`, `ext_nav`.`total`
		;
        
		
		DROP temporary table if exists `acct_outofBalance`;
	    CREATE TEMPORARY TABLE `acct_outofBalance` AS
			select 
            p.clientAccountID, p.assetclass, 
			p.positionValue as positionValue, 
			p.costBasisMoney as costBasisMoney,
			p.fifoPnlUnrealized fifoPnlUnrealized,
			pv.navTotal as navTotal,
			pv.totalPosValue totalPosValue,
            pv.totalcostBasis as totalcostBasis,
            pv.totalPNL as totalPNL,
			round((p.costBasisMoney/pv.totalcostBasis)*100,2) as allocationWeight,
            round((p.positionValue/pv.totalPosValue)*100,2) as positionWeights,
            round(((p.costBasisMoney/pv.totalcostBasis)*100) - ((p.positionValue/pv.totalPosValue)*100),2) as weightdiff,
            abs(funct_get_switch('ALLOC_MAX_OFFSET')) as defaultcheck,
            atp.marketlimitLow,
            atp.maketlimitHigh
			from `position_balance` p,
				 `position_value` pv,
				 ext_acct_info
                 LEFT JOIN acct_trade_preference atp 
					ON (ext_acct_info.acctnum = atp.acctnum)
			where p.clientAccountID = pv.clientAccountID
            AND   p.clientAccountID = ext_acct_info.clientAccountID
		;


		
		DROP temporary table if exists `only_outofBalance`;
	    CREATE TEMPORARY TABLE `only_outofBalance` AS 
			select a.clientAccountID,
            a.assetclass,
			a.positionValue, 
			a.costBasisMoney ,
			a.fifoPnlUnrealized,
			a.navTotal,
			a.totalPosValue,
			a.allocationWeight,
            a.positionWeights,
			IFNULL(a.marketlimitLow, -1 * a.defaultcheck) as marketlimitLow,
            IFNULL(a.maketlimitHigh,a.defaultcheck) as maketlimitHigh,
			a.weightdiff,
            abs(a.weightdiff) as maxweightdiff
			from acct_outofBalance a
			WHERE a.assetclass not in ('CASH')
            AND (a.weightdiff < IFNULL(a.marketlimitLow, -1 * a.defaultcheck) 
                OR a.weightdiff > IFNULL(a.maketlimitHigh,a.defaultcheck))
            ;
            
 		DROP temporary table if exists `unique_outofBalance`;
	    CREATE TEMPORARY TABLE `unique_outofBalance` AS 
		select 
			a.clientAccountID,
			a.navTotal,
			a.totalPosValue,
			a.marketlimitLow,
            a.maketlimitHigh,
			max(a.maxweightdiff) as weightdiff
 		from only_outofBalance a
		group by 
			a.clientAccountID,
			a.navTotal,
			a.totalPosValue,
			a.marketlimitLow,
            a.maketlimitHigh
		;

		
		DROP temporary table if exists `tmp_dateFilter`;
	    CREATE TEMPORARY TABLE `tmp_dateFilter` AS (
			select ext_acct_info.acctnum, ext_acct_info.applicantLName as lastname, ext_acct_info.applicantFName as firstname, t.clientAccountID,  
			max(t.reportDate) as lastTraded
			from trades t,
				ext_acct_info
			where t.clientAccountID = ext_acct_info.clientAccountID
			AND ext_acct_info.`status` in ('A')
			group by ext_acct_info.acctnum, ext_acct_info.applicantLName, ext_acct_info.applicantFName, t.clientAccountID
		);

		
		DELETE FROM `clients_to_trade`;

		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`lastTraded`,
 		`position`,
		`actualAvailable`,       
		`created`,
		`lastupdated`)
		SELECT distinct 
			ext_acct_info.acctnum,
			ext_acct_info.clientAccountID,
			'P' as processStatus,
			now() as tradedate,
			'New' as reason,
			null as lastTraded,
			null, 
 			funct_get_actualCapital(ext_acct_info.acctnum), 
			now() as created,
			null as lastupdated
		from ext_acct_info
		where ext_acct_info.clientAccountID not in (select `position`.clientAccountID from `invdb`.`position`)
		and ext_acct_info.acctnum not in (select r.acctnum from clients_to_trade r)
		;

		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`lastTraded`,
 		`position`,
		`actualAvailable`,       
		`created`,
		`lastupdated`)
		SELECT distinct 
			t.acctnum,
			t.clientAccountID,
			'P' as processStatus,
			now() as tradedate,
			'Date' as reason,
			t.lastTraded as lastTraded,
            pv.totalPosValue,
            pv.navTotal,
			now() as created,
			null as lastupdated
		from tmp_dateFilter t,
             position_value as pv
		WHERE t.clientAccountID = pv.clientAccountID
        and   t.acctnum not in (select r.acctnum from clients_to_trade r)
		and   t.clientAccountID in (select ext_acct_info.clientAccountID from ext_acct_info
							    where upper(`status`) in ('A'))
		and   t.lastTraded < (DATE_ADD(now(), Interval (-1 * funct_get_switch('DAYS_TO_REBALANCE')) DAY))
		;

		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`lastTraded`,
		`position`,
		`actualAvailable`,
		`assetAllocationOffset`,
		`created`,
		`lastupdated`)
		SELECT 
			ext_acct_info.acctnum,
			uao.clientAccountID,
			'P' as processStatus, 
			now() as tradedate, 
			'Offset' as reason, 
			t.lastTraded as lastTraded, 
 			uao.totalPosValue,
			uao.navtotal,
 			uao.weightdiff,
			now() as created, 
			null as lastupdated 
		from unique_outofBalance uao,
			 ext_acct_info
             LEFT JOIN tmp_dateFilter t
             ON (ext_acct_info.clientAccountID = t.clientAccountID)
		where uao.clientAccountID = ext_acct_info.clientAccountID
		and   ext_acct_info.acctnum not in (select r.acctnum from clients_to_trade r)
		and   upper(ext_acct_info.`status`) in ('A')
		;

		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`lastTraded`,
		`position`,
		`actualAvailable`,
		`currentAllocation`,
		`assetclass`,
		`requiredAllocation`,
		`assetAllocationOffset`,
		`created`,
		`lastupdated`)
		SELECT 
			ext_acct_info.acctnum,
			ext_acct_info.clientAccountID,
			'P', 
			now(), 
			'View', 
			t.lastTraded, 
			pv.totalPosValue, 
			funct_get_actualCapital(ext_acct_info.acctnum), 
			null as currentAllocation,
			'', 
			null, 
			null as assetAllocationOffset,
			now(), 
			null 
		from ext_acct_info
             LEFT JOIN position_value pv
             ON (ext_acct_info.clientAccountID = pv.clientAccountID)
             LEFT JOIN tmp_dateFilter t
             ON (ext_acct_info.clientAccountID = t.clientAccountID)
		where  ext_acct_info.acctnum not in (select acctnum from  `clients_to_trade`)
		and ext_acct_info.acctnum is not null
		and upper(ext_acct_info.`status`) not in  ('C')
		;

		commit;
	end;

	

END$$
DELIMITER ;

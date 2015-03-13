DROP PROCEDURE `sp_addClients2Trade`;

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

		-- 1) Collect those accounts that are X% offset.
		DROP temporary table if exists `acct2Rebalance_outofBalance`;
	    CREATE TEMPORARY TABLE IF NOT EXISTS `acct2Rebalance_outofBalance` AS (
			select ib.acctnum, ib.lastname, ib.firstname, p.clientAccountID, sec.assetclass, 
			sum(p.positionValue) as pos, 
			sum(p.costBasisMoney) as origpos,
			sum(p.fifoPnlUnrealized) pnl,
			nav.total,
			funct_get_actualCapital(ib.acctnum) actualtotal,
			asset.weight as weight,
			funct_get_switch('ALLOC_MAX_OFFSET') as vCheck,
			((sum(p.positionValue)/funct_get_actualCapital(ib.acctnum)) - (asset.weight/100)) as assetAllocationOffset,
			null as lastTraded
			from position p,
-- 			LEFT JOIN user_trade_log utl
-- 			ON p.clientAccountID = utl.clientAccountID
-- 			AND utl.tradeStatus not in ('P'),
 			IB_Accounts ib,
			sec_master sec,
			virtual_portfolio vp,
			nav_daily nav,
			asset_alloc asset
			where p.reportDate = funct_get_switch('BROKER_BDATE')
			AND p.clientAccountID = ib.IB_acctnum
			AND ib.accountStatus in ('ACTIVE')
			AND p.symbol = sec.ticker
			and sec.ticker not in ('CASH')
			AND ib.acctnum = vp.acctnum
			AND sec.ticker = vp.ticker
			AND p.clientAccountID = nav.clientAccountID
			AND p.reportDate = nav.reportDate
			AND ib.acctnum = asset.acctnum
			AND sec.assetclass = asset.assetclass
			AND asset.active = 'A'
			group by ib.acctnum, ib.lastname, ib.firstname, p.clientAccountID, sec.assetclass, nav.total, asset.weight,
			funct_get_actualCapital(ib.acctnum)
			having 
			   (
				abs(((sum(p.positionValue)/funct_get_actualCapital(ib.acctnum)) - (asset.weight/100))) > IFNULL(funct_get_switch('ALLOC_MAX_OFFSET'),5.0)/100
				)
		);

		DROP temporary table if exists `unique_acct2Rebalance_outofBalance`;
	    CREATE TEMPORARY TABLE IF NOT EXISTS `unique_acct2Rebalance_outofBalance` AS (
		SELECT 
			t.acctnum,
			t.clientAccountID,
			max(t.assetAllocationOffset) as assetAllocationOffset
		from acct2Rebalance_outofBalance t
		where   t.acctnum in (select acctnum from user_trade_profile
							    where tradePreference in ('A'))
		and   (t.lastTraded is null or t.lastTraded <= DATE_ADD(now(), INTERVAL -10 DAY)) 
		group by 
			t.acctnum,
			t.clientAccountID
		);

		-- 2) Find old trades
		DROP temporary table if exists `tmp_listOfAcct2Rebalance`;
	    CREATE TEMPORARY TABLE IF NOT EXISTS `tmp_listOfAcct2Rebalance` AS (
			select ib.acctnum, ib.lastname, ib.firstname, t.clientAccountID,  
			max(t.reportDate) as lastTraded
			from trades t,
 			IB_Accounts ib
			where t.clientAccountID = ib.IB_acctnum
			AND ib.accountStatus in ('ACTIVE')
			group by ib.acctnum, ib.lastname, ib.firstname, t.clientAccountID
			having 
			   (
				max(t.reportDate) < (DATE_ADD(now(), Interval (-1 * funct_get_switch('DAYS_TO_REBALANCE')) DAY))
				)
		);

		DELETE FROM `clients_to_trade`
		WHERE processStatus in ('P')
		;

		DELETE FROM `pretrade_details`;

		DELETE FROM `user_trade_log`
		WHERE `tradeStatus` in ('P', 'T');

		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`lastTraded`,
		`created`,
		`lastupdated`)
		SELECT distinct 
			ib.acctnum,
			ib.IB_acctnum,
			'P',
			vPostDate,
			'N',
			null,
			vPostDate,
			null
		from IB_Accounts ib
		where ib.acctnum not in (select r.acctnum from clients_to_trade r
							    where r.processStatus = 'P')
		and   UPPER(ib.accountStatus) in ('FUNDED')
		;

		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`lastTraded`,
		`created`,
		`lastupdated`)
		SELECT distinct 
			t.acctnum,
			t.clientAccountID,
			'P',
			vPostDate,
			'D',
			t.lastTraded,
			vPostDate,
			null
		from tmp_listOfAcct2Rebalance t
		where t.acctnum not in (select r.acctnum from clients_to_trade r
							    where r.processStatus = 'P')
		and   t.acctnum in (select acctnum from user_trade_profile
							    where tradePreference in ('A'))
		;

		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`position`,
		`actualAvailable`,
		`currentAllocation`,
		`assetclass`,
		`requiredAllocation`,
		`assetAllocationOffset`,
		`created`,
		`lastupdated`)
		SELECT 
			uao.acctnum,
			uao.clientAccountID,
			'P' as processStatus, -- processStatus
			lastTraded as tradedate, -- tradedate
			'O' as reason, -- reason
			ao.`pos`, -- position
			ao.`actualtotal`, -- actualAvailable
			((ao.`pos`/ao.`actualtotal`) * 100) as currentAllocation,
			ao.`assetclass`, -- assetclass
			ao.`weight`, -- requiredAllocation
			round((uao.assetAllocationOffset*100),3) as assetAllocationOffset,
			now() as created, -- created
			null as lastupdated -- lastupdated
		from unique_acct2Rebalance_outofBalance uao,
			 acct2Rebalance_outofBalance ao
		where uao.clientAccountID = ao.clientAccountID
		and   uao.assetAllocationOffset = ao.assetAllocationOffset
		and   uao.acctnum not in (select r.acctnum from clients_to_trade r
							    where r.processStatus = 'P')
		and   uao.acctnum in (select acctnum from user_trade_profile
							    where tradePreference in ('A'))
		and   (ao.lastTraded is null or ao.lastTraded <= DATE_ADD(now(), INTERVAL -10 DAY)) 
		;


		INSERT INTO `clients_to_trade`
		(`acctnum`,
		`clientAccountID`,
		`processStatus`,
		`tradedate`,
		`reason`,
		`position`,
		`actualAvailable`,
		`currentAllocation`,
		`assetclass`,
		`requiredAllocation`,
		`assetAllocationOffset`,
		`created`,
		`lastupdated`)
		SELECT 
			ib.acctnum,
			ib.IB_acctnum,
			'P', -- processStatus
			vPostDate, -- tradedate
			'V', -- reason
			null, -- position
			funct_get_actualCapital(ib.acctnum), -- actualAvailable
			null as currentAllocation,
			'', -- assetclass
			null, -- requiredAllocation
			null as assetAllocationOffset,
			vPostDate, -- created
			null -- lastupdated
		from IB_Accounts ib
		where  ib.acctnum not in (select acctnum from  `clients_to_trade`)
		and ib.acctnum is not null
		;


		commit;
	end;

	

END$$
DELIMITER ;

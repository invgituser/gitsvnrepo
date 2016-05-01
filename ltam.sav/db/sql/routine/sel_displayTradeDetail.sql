DROP PROCEDURE IF EXISTS `sel_displayTradeDetail`;

DELIMITER $$
CREATE PROCEDURE `sel_displayTradeDetail`(
	p_acctnum BIGINT(20)
)
BEGIN

	IF (p_acctnum is null)
		then
			begin
				SELECT 
					td.acctnum	,
					td.clientAccountID	,
					td.name	,
					td.tradedate	,
					td.ticker	,
					td.accttype	,
					td.currentqty	,
					td.costBasisPrice	,
					td.costBasisMoney	,
					td.currentValue	,
					td.pnl	,
					td.newqty	,
					td.newValue	,
					td.tradeqty	,
					IFNULL(td.adjustedQty,td.tradeqty) as curQty,
					td.tradeprice	curPrice,
					td.tradeValue	curValue,
					td.priceperShare	,
					td.realizedPnL	,
					td.tradedPnL	,
					td.runningCashBal	,
					td.percentAllocated	,
					td.sortOrder	,
					IFNULL(`sec_master`.`assetclass`,'STK') as assetclass
				FROM `pretrade_details` as td
				LEFT JOIN `sec_master`
				ON (td.`ticker` = `sec_master`.`ticker`)
				ORDER BY `td`.`acctnum`, `td`.`sortOrder`
				;
			end;
		else
			begin
				SELECT 
					td.acctnum	,
					td.clientAccountID	,
					td.name	,
					td.tradedate	,
					td.ticker	,
					td.accttype	,
					td.currentqty	,
					td.costBasisPrice	,
					td.costBasisMoney	,
					td.currentValue	,
					td.pnl	,
					td.newqty	,
					td.newValue	,
					td.tradeqty	,
					IFNULL(td.adjustedQty,td.tradeqty) as curQty,
					td.tradeprice	curPrice,
					td.tradeValue	curValue,
					td.priceperShare	,
					td.realizedPnL	,
					td.tradedPnL	,
					td.runningCashBal	,
					td.percentAllocated	,
					td.sortOrder	,
					IFNULL(`sec_master`.`assetclass`,'STK') as assetclass
				FROM `pretrade_details` as td
				LEFT JOIN `sec_master`
				ON (td.`ticker` = `sec_master`.`ticker`)
				WHERE td.acctnum = p_acctnum
				ORDER BY `td`.`acctnum`, `td`.`sortOrder`
				;
			end;
	END IF;
END$$
DELIMITER ;

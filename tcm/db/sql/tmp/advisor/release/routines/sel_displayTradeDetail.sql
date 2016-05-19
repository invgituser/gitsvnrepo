DROP PROCEDURE IF Exists `sel_displayTradeDetail`;

DELIMITER $$
CREATE PROCEDURE `sel_displayTradeDetail`(
	p_acctnum BIGINT(20)
)
BEGIN
	IF (p_acctnum is null)
		then
			begin
				SELECT `td`.`acctnum`,
					`td`.`clientAccountID`,
					`td`.`name`,
					`td`.`tradedate`,
					IFNULL(`sec_master`.`assetclass`,'STK') as assetclass,
					`td`.`ticker`,
					`td`.`accttype`,
					`td`.`posqty`,
					`td`.`newqty`,
					`td`.`tradeqty`,
					`td`.`adjustedQty`,
					`td`.`pnl`,
					`td`.`priceperShare`,
					`td`.`gainloss`,
					`td`.`tradeprice`,
					`td`.`posamount`,
					`td`.`newamount`,
					`td`.`tradeamount`,
					`td`.`runningCashAmt`,
					`td`.`sortOrder`,
					`td`.`percentAllocated`
				FROM `pretrade_details` as td
				LEFT JOIN `sec_master`
				ON (td.`ticker` = `sec_master`.`ticker`)
				ORDER BY `td`.`acctnum`, `td`.`sortOrder`
				;
			end;
		else
			begin
				SELECT `td`.`acctnum`,
					`td`.`clientAccountID`,
					`td`.`name`,
					`td`.`tradedate`,
					IFNULL(`sec_master`.`assetclass`,'STK') as assetclass,
					`td`.`ticker`,
					`td`.`accttype`,
					`td`.`posqty`,
					`td`.`newqty`,
					`td`.`tradeqty`,
					`td`.`adjustedQty`,
					`td`.`pnl`,
					`td`.`priceperShare`,
					`td`.`gainloss`,
					`td`.`tradeprice`,
					`td`.`posamount`,
					`td`.`newamount`,
					`td`.`tradeamount`,
					`td`.`runningCashAmt`,
					`td`.`sortOrder`,
					`td`.`percentAllocated`
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

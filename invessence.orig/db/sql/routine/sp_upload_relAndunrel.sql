DROP PROCEDURE IF EXISTS `sp_upload_relAndunrel`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_relAndunrel`()
BEGIN 

   begin
		delete from relAndunrel
		where concat(`clientAccountID`,`symbol`,`reportDate`) in (select concat(`clientAccountID`,`symbol`,`reportDate`)
																  from tmp_relAndunrel)
		;
   end;

   begin
		INSERT INTO `relAndunrel`
		(`clientAccountID`,
		`symbol`,
		`reportDate`,
		`realizedShortTermProfit`,
		`realizedShortTermLoss`,
		`realizedLongTermProfit`,
		`realizedLongTermLoss`,
		`totalRealizedPnl`,
		`unrealizedProfit`,
		`unrealizedLoss`,
		`unrealizedSTProfit`,
		`unrealizedSTLoss`,
		`unrealizedLTProfit`,
		`unrealizedLTLoss`,
		`totalUnrealizedPnl`,
		`totalFifoPnl`)
		SELECT `tmp_relAndunrel`.`clientAccountID`,
			`tmp_relAndunrel`.`symbol`,
			`tmp_relAndunrel`.`reportDate`,
			`tmp_relAndunrel`.`realizedShortTermProfit`,
			`tmp_relAndunrel`.`realizedShortTermLoss`,
			`tmp_relAndunrel`.`realizedLongTermProfit`,
			`tmp_relAndunrel`.`realizedLongTermLoss`,
			`tmp_relAndunrel`.`totalRealizedPnl`,
			`tmp_relAndunrel`.`unrealizedProfit`,
			`tmp_relAndunrel`.`unrealizedLoss`,
			`tmp_relAndunrel`.`unrealizedSTProfit`,
			`tmp_relAndunrel`.`unrealizedSTLoss`,
			`tmp_relAndunrel`.`unrealizedLTProfit`,
			`tmp_relAndunrel`.`unrealizedLTLoss`,
			`tmp_relAndunrel`.`totalUnrealizedPnl`,
			`tmp_relAndunrel`.`totalFifoPnl`
		FROM `tmp_relAndunrel`;
   end;
END

$$
DELIMITER ;

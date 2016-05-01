
DROP PROCEDURE IF EXISTS `admin_updateProfile`;

DELIMITER $$
CREATE PROCEDURE `admin_updateProfile`(
	IN p_acctnum    bigint,
	IN p_riskIndex	Integer,
	IN p_investment Long,
	IN p_keepliquid Long,
	IN p_tradepreference VARCHAR(1)
)
BEGIN
		  begin
			update `user_trade_profile`
				set `riskIndex` = IFNULL(p_riskIndex,`riskIndex`),
					`initialInvestment` = IFNULL(p_investment,`initialInvestment`),
					`keepliquid` = IFNULL(p_keepliquid,0),
					`tradePreference` = IFNULL(p_tradepreference,'A')
			WHERE `acctnum` = p_acctnum;
		  end;
END;
$$

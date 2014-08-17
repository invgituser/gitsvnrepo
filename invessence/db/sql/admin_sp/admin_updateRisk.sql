
DROP PROCEDURE IF EXISTS `admin_updateRisk`;

DELIMITER $$
CREATE PROCEDURE `admin_updateRisk`(
	IN p_acctnum    bigint,
	IN p_riskIndex	Integer
)
BEGIN
		  begin
			update `user_trade_profile`
				set `riskIndex` = p_riskIndex
			WHERE `acctnum` = p_acctnum;
		  end;
END;
$$

DROP PROCEDURE IF EXISTS `del_rebalanced_trades`;

DELIMITER $$
CREATE PROCEDURE `del_rebalanced_trades`(
  IN p_acctnum LONG
)
BEGIN

	DELETE FROM rebalance_trade
	WHERE acctnum = p_acctnum
	AND processed = 'N';
END$$
DELIMITER ;

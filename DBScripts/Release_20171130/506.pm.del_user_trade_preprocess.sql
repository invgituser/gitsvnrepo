DROP PROCEDURE IF EXISTS `invdb`.`del_user_trade_preprocess`;
DROP PROCEDURE IF EXISTS `invdb`.`del_rebalanced_trades`;


DELIMITER $$
CREATE PROCEDURE `invdb`.`del_user_trade_preprocess`(
  IN p_acctnum LONG
)
BEGIN

	DELETE FROM `user_trade_preprocess`
	WHERE acctnum = p_acctnum
	;
END$$
DELIMITER ;

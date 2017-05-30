DROP PROCEDURE IF EXISTS `sp_save_rebalanced_trades`;
DROP PROCEDURE IF EXISTS `save_rebalanced_trades`;

DELIMITER $$
CREATE PROCEDURE `save_rebalanced_trades`(
  IN p_advisor VARCHAR(20),
  IN p_clientAccountID VARCHAR(20),
  IN p_acctnum LONG,
  IN p_ticker VARCHAR(25),
  IN p_assetclass VARCHAR(40),
  IN p_subclass VARCHAR(40),
  IN p_color VARCHAR(10),
  IN p_curQty INTEGER,
  IN p_curPrice  DOUBLE,
  IN p_curValue  DOUBLE,
  IN p_holdingTicker VARCHAR(25),
  IN p_holdingQty INTEGER,
  IN p_holdingPrice DOUBLE,
  IN p_holdingValue DOUBLE,
  IN p_newQty INTEGER,
  IN p_newValue DOUBLE,
  IN p_tradeType VARCHAR(20),
  IN p_reason VARCHAR(40)
)
BEGIN

	INSERT INTO rebalance_trade (
	  advisor,
	  clientAccountID,
	  acctnum,
	  processed,
	  ticker,
      assetclass,
      subclass,
      color,
	  curQty,
	  curPrice,
	  curValue,
	  holdingTicker,
	  holdingQty,
	  holdingPrice,
	  holdingValue,
	  newQty,
	  newValue,
	  tradeType,
      reason,
	  created
	)
	VALUE (
      p_advisor,
	  p_clientAccountID,
	  p_acctnum,
	  'N', 
	  p_ticker,
      p_assetclass,
      p_subclass,
      p_color,
	  p_curQty,
      p_curPrice,
	  p_curValue,
	  p_holdingTicker,
	  p_holdingQty,
	  p_holdingPrice,
	  p_holdingValue,
	  p_newQty,
	  p_newValue,
	  p_tradeType,
	  p_reason,
	  now()
	);


END$$
DELIMITER ;

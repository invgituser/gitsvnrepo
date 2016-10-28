DROP PROCEDURE IF EXISTS `sp_save_rebalanced_trades`;

DELIMITER $$
CREATE PROCEDURE `sp_save_rebalanced_trades`(
  IN p_clientAccountID VARCHAR(20),
  IN p_acctnum LONG,
  IN p_ticker VARCHAR(25),
  IN p_curQty INTEGER,
  IN p_curPrice  DOUBLE,
  IN p_curValue  DOUBLE,
  IN p_holdingTicker VARCHAR(25),
  IN p_holdingQty INTEGER,
  IN p_holdingPrice DOUBLE,
  IN p_holdingValue DOUBLE,
  IN p_holdingWeight DOUBLE,
  IN p_holdingCostBasis DOUBLE,
  IN p_allocTicker VARCHAR(25),
  IN p_allocQty INTEGER,
  IN p_allocPrice DOUBLE,
  IN p_allocValue DOUBLE,
  IN p_allocWeight DOUBLE
)
BEGIN

	INSERT INTO rebalance_trade (
	  clientAccountID,
	  acctnum,
	  processed,
	  ticker,
	  curQty,
	  curPrice,
	  curValue,
	  holdingTicker,
	  holdingQty,
	  holdingPrice,
	  holdingValue,
	  holdingWeight,
	  holdingCostBasis,
	  allocTicker,
	  allocQty,
	  allocPrice,
	  allocValue,
	  allocWeight,
	  created
	)
	VALUE (
	  p_clientAccountID,
	  p_acctnum,
	  'N', 
	  p_ticker,
	  p_curQty,
      p_curPrice,
	  p_curValue,
	  p_holdingTicker,
	  p_holdingQty,
	  p_holdingPrice,
	  p_holdingValue,
	  p_holdingWeight,
	  p_holdingCostBasis,
	  p_allocTicker,
	  p_allocQty,
	  p_allocPrice,
	  p_allocValue,
	  p_allocWeight,
	  now()
	);


END$$
DELIMITER ;

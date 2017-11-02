DROP PROCEDURE IF EXISTS `invdb`.`save_rebalanced_trades`;
DROP PROCEDURE IF EXISTS `invdb`.`save_user_trade_preprocess`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`save_user_trade_preprocess`(
		IN	`p_advisor`	varchar(20)
	,	IN	`p_clientAccountID`	varchar(20)
	,	IN	`p_acctnum`	bigint(20)
	,	IN	`p_processed`	varchar(1)
	,	IN	`p_tradeDate`	varchar(8)
	,	IN	`p_tradeCurrency`	varchar(3)
	,	IN	`p_ticker`	varchar(25)
	,	IN	`p_assetclass`	varchar(40)
	,	IN	`p_subclass`	varchar(40)
	,	IN	`p_color`	varchar(10)
	,	IN	`p_curQty`	double
	,	IN	`p_curPrice`	double
	,	IN	`p_curValue`	double
	,	IN	`p_holdingTicker`	varchar(25)
	,	IN	`p_holdingQty`	int(11)
	,	IN	`p_holdingPrice`	double
	,	IN	`p_holdingValue`	double
	,	IN	`p_newQty`	int(11)
	,	IN	`p_newValue`	double
	,	IN	`p_tradetype`	varchar(20)
	,	IN	`p_exchangeRate`	double
	,	IN	`p_settleCurrency`	varchar(3)
	,	IN	`p_settleQty`	double
	,	IN	`p_settlePrice`	double
	,	IN	`p_settleValue`	double
	,	IN	`p_reason`	varchar(40)
)
BEGIN

	INSERT INTO `invdb`.`user_trade_preprocess`
	(
			`advisor`
		,	`clientAccountID`
		,	`acctnum`
		,	`processed`
		,	`tradeDate`
		,	`tradeCurrency`
		,	`ticker`
		,	`assetclass`
		,	`subclass`
		,	`color`
		,	`curQty`
		,	`curPrice`
		,	`curValue`
		,	`holdingTicker`
		,	`holdingQty`
		,	`holdingPrice`
		,	`holdingValue`
		,	`newQty`
		,	`newValue`
		,	`tradetype`
        ,	`exchangeRate`
		,	`settleCurrency`
		,	`settleQty`
		,	`settlePrice`
		,	`settleValue`
		,	`reason`
		,	`created`

    )
	VALUE (
			`p_advisor`
		,	`p_clientAccountID`
		,	`p_acctnum`
		,	`p_processed`
		,	`p_tradeDate`
		,	`p_tradeCurrency`
		,	`p_ticker`
		,	`p_assetclass`
		,	`p_subclass`
		,	`p_color`
		,	`p_curQty`
		,	`p_curPrice`
		,	`p_curValue`
		,	`p_holdingTicker`
		,	`p_holdingQty`
		,	`p_holdingPrice`
		,	`p_holdingValue`
		,	`p_newQty`
		,	`p_newValue`
		,	`p_tradetype`
        ,	`p_exchangeRate`
		,	`p_settleCurrency`
		,	`p_settleQty`
		,	`p_settlePrice`
		,	`p_settleValue`
		,	`p_reason`
		,	now()
	)
    ON DUPLICATE KEY UPDATE
		 	`acctnum`	 =	`p_acctnum`
		,	`processed`	 =	`p_processed`
		,	`assetclass`	 =	`p_assetclass`
		,	`subclass`	 =	`p_subclass`
		,	`color`	 =	`p_color`
		,	`curQty`	 =	`p_curQty`
		,	`curPrice`	 =	`p_curPrice`
		,	`curValue`	 =	`p_curValue`
		,	`holdingTicker`	 =	`p_holdingTicker`
		,	`holdingQty`	 =	`p_holdingQty`
		,	`holdingPrice`	 =	`p_holdingPrice`
		,	`holdingValue`	 =	`p_holdingValue`
		,	`newQty`	 =	`p_newQty`
		,	`newValue`	 =	`p_newValue`
		,	`tradetype`	 =	`p_tradetype`
        ,	`exchangeRate` = `p_exchangeRate`
		,	`settleCurrency`	 =	`p_settleCurrency`
		,	`settleQty`	 =	`p_settleQty`
		,	`settlePrice`	 =	`p_settlePrice`
		,	`settleValue`	 =	`p_settleValue`
		,	`reason`	 =	`p_reason`
	;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `temp`.`sp_upload_trades`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_trades`(
)
BEGIN 

   begin
		delete from `invdb`.`ext_transaction`
		where concat(`clientAccountID`,`tradeDate`) 
				in (select distinct concat(`tmp_transaction`.`clientAccountID`,replace(`tmp_transaction`.`tradeDate`,'-',''))
					from `temp`.`tmp_transaction`)
		;

		insert into `invdb`.`ext_transaction` (
				`acctnum`
			,	`clientAccountID`
			,	`tickerISIN`
			,	`confirmNumber`
			,	`transactionSource`
			,	`transactionType`
			,	`transactionStatus`
			,	`controlNumber`
			,	`quantity`
			,	`price`
			,	`netAmount`
			,	`commission`
			,	`otherFees`
			,	`tradeDate`
			,	`settDate`
			,	`voidDate`
			,	`comment`
			,	`tradedCurrency`
			,	`fxRateToBase`
			,	`baseCurrency`
			,	`convertedNetAmount`
			,	`created`
          )
		SELECT
				`ext_acct_info`.`acctnum`
			,	`tmp_transaction`.`clientAccountID`
			,	`tmp_transaction`.`symbolSIN` 			-- `tickerISIN`
            ,	IFNULL(`tmp_transaction`.`confirmNumber`,concat(`tmp_transaction`.`clientAccountID`,'-',`tmp_transaction`.`tradeDate`))	-- `confirmNumber`
            ,	'EOD' 									-- `transactionSource`
			,	SUBSTR(`tmp_transaction`.`transactionType`,1,1)	-- `transactionType` (Buy,Sell, etc.)
            ,	'P'										-- `transactionStatus` ('P' = Pending Settlement)
            ,	null									-- `controlNumber`
			,	`tmp_transaction`.`quantity`			-- `quantity`
			,	`tmp_transaction`.`price`				-- `price`
			,	`tmp_transaction`.`netAmount`			-- `netAmount`
			,	`tmp_transaction`.`brokerFee`			-- `commission`
			,	`tmp_transaction`.`otherFees`			-- `otherFees`
			,	`tmp_transaction`.`tradeDate`			-- `tradeDate`
			,	`tmp_transaction`.`settleDate`			-- `settDate`
			,	null									-- `voidDate`
			,	`tmp_transaction`.`comments`			-- `comment`
			,	`tmp_transaction`.`executionCurrency` 	-- `tradedCurrency`
			,	`tmp_transaction`.`exchangeRate`		-- `fxRateToBase`
			,	`tmp_transaction`.`localCurrency`		-- `baseCurrency`
            ,	(IFNULL(`tmp_transaction`.`netAmount`,0) * IFNULL(`tmp_transaction`.`exchangeRate`,1)) -- `convertedNetAmount`
            ,	now()								-- `created`
		FROM `temp`.`tmp_transaction`
        INNER JOIN `invdb`.`ext_acct_info`
        ON (`ext_acct_info`.`clientAccountID` = `tmp_transaction`.`clientAccountID`)
        ;

   end;

END$$
DELIMITER ;

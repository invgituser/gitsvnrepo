DROP PROCEDURE IF EXISTS `testing`.`sp_fund_account`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_fund_account`(
  IN p_acctnum BIGINT,
  IN p_amount  DOUBLE
)
  BEGIN
      IF (IFNULL(p_acctnum, 'XXX') != 'XXX')
      THEN
			INSERT INTO `invdb`.`ext_position`
				(`acctnum`,
				`clientAccountID`,
				`currencyPrimary`,
				`fxRateToBase`,
				`symbol`,
				`reportDate`,
				`purchaseDate`,
				`side`,
				`quantity`,
				`costBasisPrice`,
				`costBasisMoney`,
				`markPrice`,
				`positionValue`,
				`pnlUnrealized`,
				`levelOfDetail`,
				`created`
				)
			SELECT
				`ext_acct_info`.`acctnum` 
				, `ext_acct_info`.`clientAccountID` as clientAccountID 
				, 'USD' as currencyPrimary 
				, '1.0' as fxRateToBase
				, 'Cash' as symbol 
				, `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE') as reportDate 
				, `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE') as purchaseDate 
				, 'Long' as `side`
				, `p_amount` as `quantity`
				, 1 as costBasisPrice
				, `p_amount` as `costBasisMoney`
				, 1 as markPrice
				, `p_amount` as `positionValue`
				, 0 as pnlUnrealized
				, 'Cash' as `levelOfDetail`
				, now() as created
			FROM `invdb`.`ext_acct_info` as `ext_acct_info`
			WHERE `ext_acct_info`.`acctnum` = `p_acctnum`
            ON duplicate key update
				  `quantity` = `quantity` + `p_amount`
                , `costBasisMoney` = `costBasisMoney` + `p_amount`
                , `positionValue` = `positionValue` + `p_amount`
                , `levelOfDetail` = 'Funded'
			;
                

		   INSERT INTO `invdb`.`ext_nav`
				(`clientAccountID`,
				`reportDate`,
				`cash`,
				`stock`,
				`funds`,
				`interestAccrual`,
				`dividentAccrual`,
				`total`)
			VALUES
			(tClientAccountID,
			`invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE'),
			`p_amount`,
			0,
			0,
			0,
			0,
			`p_amount`)
            ON duplicate key update
				 `cash` = `cash` + `p_amount`
                , `total` = `total` + `p_amount`
			;

    END IF;
  END$$
DELIMITER ;

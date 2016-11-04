DROP PROCEDURE IF EXISTS `temp`.`sp_upload_td_unrealized`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_td_unrealized`(
)
BEGIN

	DECLARE t_reportDate	VARCHAR(10);
    
    -- First delete all position for this business date
	DELETE FROM `invdb`.`ext_position`
    where reportDate in (select distinct `invdb`.`funct_strdate2inv_date`(`tmp_td_unrealized`.`businessDate`, '%m/%d/%Y') 
					     from `temp`.`tmp_td_unrealized`)
    ;
    
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
	`created`)
    SELECT `ext_acct_info`.`acctnum`
		 , `ext_acct_info`.`clientAccountID`
         , 'USD' as currencyPrimary
         , 1.0 as fxRateToBase
         , `tmp_td_unrealized`.`symbolCUSIP`
         , `invdb`.`funct_strdate2inv_date`(`tmp_td_unrealized`.`businessDate`, '%m/%d/%Y') as `businessdate`
         , `invdb`.`funct_strdate2inv_date`(`tmp_td_unrealized`.`originalPurchaseDate`, '%m/%d/%Y') as `originalPurchaseDate`
         , 'Long' as `side`
		 , `tmp_td_unrealized`.`currentQuantity`
		 , CASE WHEN (`tmp_td_unrealized`.`currentQuantity` is null) THEN 1
				WHEN (`tmp_td_unrealized`.`currentQuantity` = 0) THEN 1
                ELSE (`tmp_td_unrealized`.`bookCost` / `tmp_td_unrealized`.`currentQuantity`) 
			END costBasisPrice
		 , `tmp_td_unrealized`.`bookCost` as `costBasisMoney`
		 , IFNULL(`tmp_td_price`.price, 
				CASE WHEN (`tmp_td_unrealized`.`currentQuantity` is null) THEN 1
				WHEN (`tmp_td_unrealized`.`currentQuantity` = 0) THEN 1
                ELSE (`tmp_td_unrealized`.`bookCost` / `tmp_td_unrealized`.`currentQuantity`) 
				END) as markPrice
		 ,  (IFNULL(`tmp_td_price`.price,1) * IFNULL(`tmp_td_unrealized`.`currentQuantity`,1)) as `positionValue`
		 ,  (IFNULL(`tmp_td_price`.price,1) * IFNULL(`tmp_td_unrealized`.`currentQuantity`,1)) - `tmp_td_unrealized`.`bookCost` as pnlUnrealized
		 , `tmp_td_unrealized`.`ID` as `levelOfDetail`
		 , now() as created
    FROM `invdb`.`ext_acct_info`
		 INNER JOIN `temp`.`tmp_td_unrealized`
         ON (`ext_acct_info`.`clientAccountID` = `tmp_td_unrealized`.`accountNumber`)
		 LEFT JOIN `temp`.`tmp_td_price`
         ON (`tmp_td_unrealized`.`symbolCUSIP` = `tmp_td_price`.`symbolCUSIP`)
	;
END$$
DELIMITER ;

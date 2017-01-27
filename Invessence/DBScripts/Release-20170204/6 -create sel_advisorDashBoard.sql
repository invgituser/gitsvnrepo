
DROP PROCEDURE `invdb`.`sel_advisorDashBoard`;

DELIMITER $$
CREATE  PROCEDURE `invdb`.`sel_advisorDashBoard`(
	IN p_logonid BIGINT
)
BEGIN
	DECLARE tRep 	 VARCHAR(25);
	DECLARE tAdvisor VARCHAR(25);

IF (p_logonid is not null)
	THEN
    
		BEGIN
				SELECT rep, advisor
				INTO tRep, tAdvisor
				FROM user_advisor_access
				WHERE logonid = p_logonid
                LIMIT 1;
		END;

			SELECT
				'AUM' src,
				IFNULL(SUM(IFNULL(`ext_nav`.`total`,0)),0) as `value`
			FROM  
			  `ext_acct_info`
              INNER JOIN `user_trade_profile`
              ON (`user_trade_profile`.`acctnum` = `ext_acct_info`.`acctnum`
              AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'000') like tAdvisor)
			  INNER JOIN `ext_nav`
			  ON (`ext_nav`.`clientAccountID` = `ext_acct_info`.`clientAccountID`
				  AND   `ext_nav`.`reportDate` = funct_get_switch('BROKER_BDATE')
				)
			 WHERE `ext_acct_info`.`status` = 'A'
		UNION
			SELECT
			'Active' src,
			count(`user_trade_profile`.`acctnum`) as `value`
			FROM  `user_trade_profile`
			 WHERE IFNULL(`user_trade_profile`.`managed`,'N') = 'A'
             AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'000') like tAdvisor
 		UNION
			SELECT
				'Pending' src,
				count(distinct `user_trade_profile`.`acctnum`) as `value`
			FROM  `user_trade_profile`
			WHERE IFNULL(`user_trade_profile`.`managed`,'N') in ( 'N' )
			AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'000') like tAdvisor

		UNION
			SELECT
				'VisitorToday' src,
				count(distinct `user_trade_profile`.`acctnum`) as `value`
			FROM  `user_trade_profile`
			WHERE created >= DATE_FORMAT(now(),'%Y-%m-%d')
			AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'000') like tAdvisor
            AND managed='N' AND status = 'V'
			;
        
        SELECT distinct idx.ticker,
			   price.close_price
		FROM `invdb`.sec_assetclass_group idx
            , `invdb`.sec_daily_info price
		WHERE idx.ticker = price.ticker
        AND   DATE_FORMAT(`price`.`businessdate`,'%Y%m%d') = `invdb`.funct_get_switch('PRICE_DATE')
        ;
        
        
END IF;


END$$
DELIMITER ;

USE `invdb`;
DROP procedure IF EXISTS `sel_advisorDashBoard`;

DELIMITER $$
USE `invdb`$$
CREATE  PROCEDURE `sel_advisorDashBoard`(
	IN p_logonid BIGINT,
    IN p_advisor VARCHAR(20),
    IN p_rep VARCHAR(20)
)
BEGIN
	DECLARE tRep 	 VARCHAR(25);
	DECLARE tAdvisor VARCHAR(25);
	DECLARE vAdvisor VARCHAR(25);

IF (p_logonid is not null)
	THEN
    
		BEGIN

			select advisor into vAdvisor  from user_logon where logonid=p_logonid;
			if(vAdvisor='DEMO') then
				set tAdvisor = IFNULL(p_advisor,'');
				set tRep = IFNULL(p_rep,'');
			else
						SELECT rep, advisor
						INTO tRep, tAdvisor
						FROM user_advisor_access
						WHERE logonid = p_logonid
						LIMIT 1;
			end if;
		END;

			SELECT
				'AUM' src,
				IFNULL(SUM(IFNULL(`ext_nav`.`total`,0)),0) as `value`
			FROM  
			  `ext_acct_info`
              INNER JOIN `user_trade_profile`
              ON (`user_trade_profile`.`acctnum` = `ext_acct_info`.`acctnum`)
			  INNER JOIN `ext_nav`
			  ON (`ext_nav`.`clientAccountID` = `ext_acct_info`.`clientAccountID`
				  AND   `ext_nav`.`reportDate` = funct_get_switch('BROKER_BDATE')
				)
			 WHERE `ext_acct_info`.`status` = 'A'
			 AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'Invessence') like tAdvisor
			 AND IFNULL(TRIM(`user_trade_profile`.`rep`),'CATCHALL') like tRep
		UNION
			SELECT
			'Active' src,
			count(`user_trade_profile`.`acctnum`) as `value`
			FROM  
			  `ext_acct_info`
              INNER JOIN `user_trade_profile`
              ON (`user_trade_profile`.`acctnum` = `ext_acct_info`.`acctnum`)
			WHERE `ext_acct_info`.`status` not in ( 'X' )
			AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'Invessence') like tAdvisor
			AND IFNULL(TRIM(`user_trade_profile`.`rep`),'CATCHALL') like tRep
 		UNION
			SELECT
				'Pending' src,
				count(distinct `user_trade_profile`.`acctnum`) as `value`
			FROM  `user_trade_profile`
			WHERE IFNULL(`user_trade_profile`.`managed`,'N') in ( 'N' )
			AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'Inv') like tAdvisor
			AND IFNULL(TRIM(`user_trade_profile`.`rep`),'000') like tRep
		UNION
			SELECT
				'VisitorToday' src,
				count(distinct `user_trade_profile`.`acctnum`) as `value`
			FROM  `user_trade_profile`
			WHERE `user_trade_profile`.`created` >= DATE_FORMAT(now(),'%Y-%m-%d')
            AND `user_trade_profile`.`managed`='N' 
            AND `user_trade_profile`.`status` = 'V'
			AND IFNULL(TRIM(`user_trade_profile`.`advisor`),'Invessence') like tAdvisor
			AND IFNULL(TRIM(`user_trade_profile`.`rep`),'CATCHALL') like tRep
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


DROP PROCEDURE IF EXISTS `web_page3_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `web_page3_user_trade_profile`(
	IN  p_addmodflag VARCHAR(1),
    IN  p_logonid    BIGINT(20),
	INOUT	p_acctnum	bigint(20),
	IN	p_riskIndex	double
)
BEGIN 

   BEGIN
	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
		   BEGIN
			IF (p_acctnum is null) THEN
				set p_acctnum = funct_Nextval('ACCTNUM');
			END IF;

			INSERT INTO `user_trade_profile` (
				`riskIndex`,
				`created`
			)
			VALUES (
				p_riskIndex,
				now()
			);

			select last_insert_id() into p_acctnum;
			call sp_user_access_add_mod('A', p_logonid, p_acctnum, NULL, NULL, NULL);

		   END;
	   ELSE
		   BEGIN
			 UPDATE  `user_trade_profile`
			 SET 
				`riskIndex`	 =	p_riskIndex,	
				`lastupdated` = now()
			 WHERE
				`acctnum` = p_acctnum;
		   END;
	   END IF;
	END;

END$$
DELIMITER ;

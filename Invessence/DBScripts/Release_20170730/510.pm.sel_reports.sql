DROP PROCEDURE IF EXISTS  `invdb`.`sel_reports`;

DELIMITER $$
CREATE PROCEDURE `sel_reports`(
	IN p_logonid BIGINT,
    IN p_fromdate VARCHAR(10),
    IN p_todate   VARCHAR(10)
)
BEGIN
	DECLARE tYEAR VARCHAR(4);
    
    SET tYEAR = DATE_FORMAT(now(),'%Y');
    
    IF (p_fromdate is null)
		THEN
			SET p_fromdate = CONCAT(tYEAR,'0101');
	END IF;
    
	IF (p_todate is null)
		THEN
			SET p_todate = DATE_FORMAT(now(),'%Y%m%d');
	END IF;


	IF (p_logonid is not null)
		THEN
			BEGIN
				SELECT
					  `user_reports`.`acctnum` as `acctnum`
					,  `ext_acct_info`.`clientAccountID` as `clientAccountID`
					, `user_reports`.`reportDate`
					, `user_reports`.`reportName`
					, `user_reports`.`filename`
				FROM `user_reports`
					INNER JOIN  `user_access_role`
                    ON (`user_reports`.`acctnum` = `user_access_role`.`acctnum`
                    AND  `user_access_role`.`role` = 'OWNER')
                    LEFT JOIN `ext_acct_info`
                    ON (`user_reports`.`acctnum` = `ext_acct_info`.`acctnum`)
				WHERE 					  
					  `user_access_role`.`logonid` = p_logonid
                AND   `user_reports`.`reportDate` >= p_fromdate
                AND   `user_reports`.`reportDate` <= p_todate
                ORDER BY 1,3 desc ,4;
            END;
	END IF;


END$$
DELIMITER ;

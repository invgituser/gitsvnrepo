DROP FUNCTION IF EXISTS `invdb`.`funct_get_actualCapital`;

DELIMITER $$
CREATE FUNCTION `invdb`.`funct_get_actualCapital`(
        p_acctnum       BIGINT(20)
) RETURNS double
    DETERMINISTIC
BEGIN
	DECLARE tAmount DOUBLE;
	DECLARE tPosition DOUBLE;
	BEGIN
		IF (p_acctnum is NULL)
		   THEN RETURN 0.0;
		END IF;

		BEGIN
			SELECT SUM(nav.total)
			INTO tAmount
			FROM ext_acct_info,
				 ext_nav nav
			WHERE ext_acct_info.acctnum = p_acctnum
			AND  ext_acct_info.clientAccountID = nav.clientAccountID
			AND  nav.reportDate = funct_get_switch('BROKER_BDATE')
			;

			IF (IFNULL(tAmount,0.0) > 0)
			THEN return tAmount;
			ELSE
					SELECT SUM(pos.positionValue)
					INTO tPosition
					FROM ext_position pos
					WHERE pos.acctnum = p_acctnum
					AND  pos.reportDate = funct_get_switch('BROKER_BDATE')
					AND  pos.symbol in (SELECT ticker from sec_master where sec_master.`status` = 'A')
					;
                
					RETURN IFNULL(tPosition,0.0);
			END IF;

		END;
		RETURN 0.0;
	END;
END$$
DELIMITER ;

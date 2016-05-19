DROP FUNCTION IF EXISTS `funct_get_actualCapital`;

DELIMITER $$
CREATE FUNCTION `funct_get_actualCapital`(
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
			SELECT nav.total
			INTO tAmount
			FROM IB_Accounts IB,
				 nav_daily nav
			WHERE IB.acctnum = p_acctnum
			AND  IB.IB_acctnum = nav.clientAccountID
			AND  nav.reportDate = funct_get_switch('BROKER_BDATE')
			;

			IF (IFNULL(tAmount,0.0) > 0)
				THEN return tAmount;
				else
					SELECT SUM(pos.positionValue)
					INTO tPosition
					FROM IB_Accounts IB,
						 position pos
					WHERE IB.acctnum = p_acctnum
					AND  IB.IB_acctnum = pos.clientAccountID
					AND  pos.reportDate = funct_get_switch('BROKER_BDATE')
					AND  pos.symbol in (SELECT ticker from sec_master where sec_master.`status` = 'A')
					;
					IF (IFNULL(tPosition,0.0) > 0.0)
						THEN
							RETURN 0.0;
						ELSE
							RETURN (tAmount);
					END IF;
			END IF;

		END;
		RETURN 0.0;
	END;
END$$
DELIMITER ;

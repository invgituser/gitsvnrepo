DROP FUNCTION IF EXISTS `funct_get_actualCapital`;

delimiter $$
CREATE FUNCTION `funct_get_actualCapital`(
        p_acctnum       BIGINT(20)
) RETURNS Double
    DETERMINISTIC
BEGIN
	DECLARE tAmount DOUBLE;
	DECLARE tPosition DOUBLE;
	DECLARE tKeepCash  DOUBLE;

	BEGIN
		IF (p_acctnum is NULL)
		   THEN RETURN 0.0;
		END IF;

		BEGIN
			SELECT IFNULL(keepLiquid,0)
			INTO tKeepCash
			FROM user_trade_profile
			WHERE acctnum = p_acctnum;

			SELECT nav.total
			INTO tAmount
			FROM IB_Accounts IB,
				 nav_daily nav
			WHERE IB.acctnum = p_acctnum
			AND  IB.IB_acctnum = nav.clientAccountID
			AND  nav.reportDate = funct_get_switch('BROKER_BDATE')
			;

				SELECT SUM(pos.positionValue)
				INTO tPosition
				FROM IB_Accounts IB,
					 position pos
				WHERE IB.acctnum = p_acctnum
				AND  IB.IB_acctnum = pos.clientAccountID
				AND  pos.reportDate = funct_get_switch('BROKER_BDATE')
				AND  pos.symbol in (SELECT ticker from sec_master where sec_master.`status` = 'A')
				;

		END;

		IF (IFNULL(tPosition,0.0) > 0)
			THEN return tPosition - tKeepCash;
			else
				IF (tAmount is NULL)
					THEN
						RETURN 0.0;
					ELSE
						RETURN (tAmount - tKeepCash);
				END IF;
		END IF;

		RETURN 0.0;
	END;
END$$


delimiter $$

DROP PROCEDURE `sp_acct_investements_sel`
$$

CREATE PROCEDURE `sp_acct_investements_sel`(
	IN p_logonid bigint(20),
	IN p_acctnum bigint(20)
)
BEGIN
	IF (p_logonid is not NULL)
	THEN
		IF (p_acctnum is NULL)
		THEN
			SELECT
				`customer_investments`.`logonid`,
				`customer_investments`.`acctnum`,
				`customer_investments`.`investobjective`,
				`customer_investments`.`name`,
				`customer_investments`.`portfoliostate`,
				`customer_investments`.`created`,
				`customer_investments`.`lastupdated`
			FROM `customer_investments`
			WHERE logonid = p_logonid;
		ELSE
			SELECT
				`customer_investments`.`logonid`,
				`customer_investments`.`acctnum`,
				`customer_investments`.`investobjective`,
				`customer_investments`.`name`,
				`customer_investments`.`portfoliostate`,
				`customer_investments`.`created`,
				`customer_investments`.`lastupdated`
			FROM `customer_investments`
			WHERE `customer_investments`.`logonid` = p_logonid
			AND   `customer_investments`.`acctnum` = p_acctnum
			;
		END IF;
	ELSE
		-- This section is so that just column details are sent, if no logon id was provided.
	    -- This is to insure that binding this object returns same content.
		SELECT
			`customer_investments`.`logonid`,
			`customer_investments`.`acctnum`,
			`customer_investments`.`investobjective`,
			`customer_investments`.`name`,
			`customer_investments`.`portfoliostate`,
			`customer_investments`.`created`,
			`customer_investments`.`lastupdated`
		FROM `customer_investments`
		WHERE 1 = 2;
	END IF;
END
$$


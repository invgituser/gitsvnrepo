DELIMITER $$
CREATE  PROCEDURE `invdb`.`sel_Consumer4Advisor`(
  p_logonid BIGINT,
  p_filter  VARCHAR(1),
  p_days    INTEGER,
  p_filterActive varchar(10)
)
BEGIN

    DECLARE isAdvisor BOOLEAN;
    DECLARE tAdvisor VARCHAR(25);
    DECLARE tRep VARCHAR(25);

    SELECT
      advisor,
      rep
    INTO tAdvisor, tRep
    FROM user_advisor_access
    WHERE logonid = p_logonid
    LIMIT 1;

    IF (p_filter IS NULL)
    THEN SET p_filter = 'B';
    END IF;
    
    IF (p_filterActive IS NULL)
    THEN SET p_filterActive = `acctnum`;
    END IF;

    IF (tAdvisor IS NOT NULL)
    THEN SET isAdvisor = TRUE;
    ELSE SET isAdvisor = FALSE;
    END IF;
    
	IF (p_days is null)
		THEN	set p_days = -36500;
		ELSE	IF (p_days > 0)
					THEN set p_days = -1 * p_days;
				END IF;
	END IF;


    BEGIN
      IF (isAdvisor)
      THEN
        CASE
          WHEN (p_filter = 'A')
          THEN
            SELECT
              user.logonid,
              advisor_access.`privileges`                                 `advisor_privileges`,
              `ext_acct_info`.`clientAccountID`,
              `profile`.`acctnum` as acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
              'Active'                                                 AS acctstatus,
				CASE WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and IFNULL(`profile`.`status`,'V') in ('V')) THEN 'Visitor'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'N') THEN 'Pending'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'P') THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'O') THEN 'Opened'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) is not null) THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'A' and upper(`profile`.`status`) is not null) THEN 'Active'
					 ELSE 'Visitor'
				END as `status`,
              user.email                                               AS email,
              ext_acct_info.applicantLName                             AS lastname,
              ext_acct_info.applicantFName                             AS firstname,
              `profile`.tradePreference,
              `profile`.`acctType`                                     AS accttype,
              IFNULL(`profile`.age, 30)                                AS age,
              IFNULL(`profile`.horizon, 35)                            AS horizon,
              IFNULL(`profile`.riskIndex, 0)                           AS riskIndex,
              CAST(IFNULL(`profile`.initialInvestment, 0) AS SIGNED)   AS initialInvestment,
              funct_get_actualCapital(`profile`.acctnum)               AS actualCapital,
              `profile`.keepLiquid                                     AS keepLiquid,
              CAST(IFNULL(`profile`.recurringInvestment, 0) AS SIGNED) AS recurringInvestment,
              IFNULL(`profile`.longTermGoal, 0)                        AS longTermGoal,
              IFNULL(`profile`.stayInvested, 0)                        AS stayInvested,
              DATE_FORMAT(`profile`.created, '%Y-%m-%d')               AS created
		    FROM
			  user_trade_profile `profile`
              INNER JOIN  user_access_role uar
              INNER JOIN  user_logon user
              INNER JOIN user_advisor_access advisor_access
              LEFT JOIN ext_acct_info
              ON (ext_acct_info.acctnum = `profile`.acctnum)
            WHERE IFNULL(`profile`.managed, 'N') = 'A'
				  AND IFNULL(`profile`.`status`, 'N') = ( 'A' )
                  AND uar.logonid = user.logonid
                  AND `profile`.acctnum = uar.acctnum
                  AND uar.role = 'OWNER'
                  AND advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%')
			ORDER BY `profile`.`p_filterActive` desc;
            WHEN (p_filter = 'N')
          THEN
            SELECT
              user.logonid,
              advisor_access.`privileges`                                 `advisor_privileges`,
              `ext_acct_info`.`clientAccountID`,
              `profile`.`acctnum` as acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
              'Active'                                                 AS acctstatus,
				CASE WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and IFNULL(`profile`.`status`,'V') in ('V')) THEN 'Visitor'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'N') THEN 'Pending'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'N') THEN 'New'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'P') THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'O') THEN 'Opened'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) is not null) THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'A' and upper(`profile`.`status`) is not null) THEN 'Active'
					 ELSE 'Visitor'
				END as `status`,
              user.email                                               AS email,
              ext_acct_info.applicantLName                             AS lastname,
              ext_acct_info.applicantFName                             AS firstname,
              `profile`.tradePreference,
              `profile`.`acctType`                                     AS accttype,
              IFNULL(`profile`.age, 30)                                AS age,
              IFNULL(`profile`.horizon, 35)                            AS horizon,
              IFNULL(`profile`.riskIndex, 0)                           AS riskIndex,
              CAST(IFNULL(`profile`.initialInvestment, 0) AS SIGNED)   AS initialInvestment,
              funct_get_actualCapital(`profile`.acctnum)               AS actualCapital,
              `profile`.keepLiquid                                     AS keepLiquid,
              CAST(IFNULL(`profile`.recurringInvestment, 0) AS SIGNED) AS recurringInvestment,
              IFNULL(`profile`.longTermGoal, 0)                        AS longTermGoal,
              IFNULL(`profile`.stayInvested, 0)                        AS stayInvested,
              DATE_FORMAT(`profile`.created, '%Y-%m-%d')               AS created
		    FROM
			  user_trade_profile `profile`
              INNER JOIN  user_access_role uar
              INNER JOIN  user_logon user
              INNER JOIN user_advisor_access advisor_access
              LEFT JOIN ext_acct_info
              ON (ext_acct_info.acctnum = `profile`.acctnum)
            WHERE     IFNULL(`profile`.managed, 'N') = 'N'
				  AND IFNULL(`profile`.`status`, 'N') = ( 'N' )
                  AND uar.logonid = user.logonid
                  AND `profile`.acctnum = uar.acctnum
                  AND uar.role = 'OWNER'
                  AND advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%')
				  AND (IFNULL(`profile`.`created`,now()) >= DATE_ADD(now(),INTERVAL p_days DAY))
			ORDER BY `profile`.`acctnum` desc;
		  WHEN (p_filter = 'P')
          THEN
            SELECT
              user.logonid,
              advisor_access.`privileges`                                 `advisor_privileges`,
              null as `clientAccountID`,
              `profile`.`acctnum` as acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
              'Pending'                                                AS acctstatus,
				CASE WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and IFNULL(`profile`.`status`,'V') in ('V')) THEN 'Visitor'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'N') THEN 'Pending'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'P') THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'O') THEN 'Opened'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) is not null) THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'A' and upper(`profile`.`status`) is not null) THEN 'Active'
					 ELSE 'Visitor'
				END as `status`,
              user.email                                               AS email,
              IFNULL(`profile`.`lastname`, user.lastname)              AS lastname,
              IFNULL(`profile`.`firstname`,user.firstname)             AS firstname,
              `profile`.tradePreference,
              `profile`.`acctType`                                     AS accttype,
              IFNULL(`profile`.age, 30)                                AS age,
              IFNULL(`profile`.horizon, 35)                            AS horizon,
              IFNULL(`profile`.riskIndex, 0)                           AS riskIndex,
              CAST(IFNULL(`profile`.initialInvestment, 0) AS SIGNED)   AS initialInvestment,
              round(funct_get_actualCapital(`profile`.acctnum), 0)     AS actualCapital,
              `profile`.keepLiquid                                     AS keepLiquid,
              CAST(IFNULL(`profile`.recurringInvestment, 0) AS SIGNED) AS recurringInvestment,
              IFNULL(`profile`.longTermGoal, 0)                        AS longTermGoal,
              IFNULL(`profile`.stayInvested, 0)                        AS stayInvested,
              DATE_FORMAT(`profile`.created, '%Y-%m-%d')               AS created,
              profile.lastUpdated
            FROM
              user_trade_profile `profile`
              INNER JOIN user_advisor_access advisor_access
              LEFT JOIN user_access_role uar
                ON (`profile`.acctnum = uar.acctnum
                    AND uar.role = 'OWNER')
              LEFT JOIN user_logon user
                ON (uar.logonid = user.logonid)
            WHERE IFNULL(`profile`.`managed`, 'N') = ( 'N' )
				AND IFNULL(`profile`.`status`, 'N') = ( 'P' )
                  AND advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%')
				  AND (IFNULL(`profile`.`created`,now()) >= DATE_ADD(now(),INTERVAL p_days DAY))
			ORDER BY `profile`.`acctnum` desc;

          WHEN (p_filter = 'V')
          THEN
            SELECT
              user.logonid,
              advisor_access.`privileges`                                 `advisor_privileges`,
              null as `clientAccountID`,
              `profile`.`acctnum` as acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
              'Pending'                                                AS acctstatus,
				CASE WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and IFNULL(`profile`.`status`,'V') in ('V')) THEN 'Visitor'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'N') THEN 'Pending'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'P') THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'O') THEN 'Opened'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) is not null) THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'A' and upper(`profile`.`status`) is not null) THEN 'Active'
					 ELSE 'Visitor'
				END as `status`,
              user.email                                                  email,
              user.lastname                                            AS lastname,
              user.firstname                                           AS firstname,
              `profile`.tradePreference,
              `profile`.`acctType`                                     AS accttype,
              IFNULL(`profile`.age, 30)                                AS age,
              IFNULL(`profile`.horizon, 35)                            AS horizon,
              IFNULL(`profile`.riskIndex, 0)                           AS riskIndex,
              CAST(IFNULL(`profile`.initialInvestment, 0) AS SIGNED)   AS initialInvestment,
              round(funct_get_actualCapital(`profile`.acctnum), 0)     AS actualCapital,
              `profile`.keepLiquid                                     AS keepLiquid,
              CAST(IFNULL(`profile`.recurringInvestment, 0) AS SIGNED) AS recurringInvestment,
              IFNULL(`profile`.longTermGoal, 0)                        AS longTermGoal,
              IFNULL(`profile`.stayInvested, 0)                        AS stayInvested,
              DATE_FORMAT(`profile`.created, '%Y-%m-%d')               AS created,
              profile.lastUpdated
            FROM
              user_trade_profile `profile`
              INNER JOIN user_advisor_access advisor_access
              LEFT JOIN user_access_role uar
                ON (`profile`.acctnum = uar.acctnum
                    AND uar.role = 'OWNER')
              LEFT JOIN user_logon user
                ON (uar.logonid = user.logonid)
            WHERE IFNULL(`profile`.`status`, 'N') = 'V'
                  AND advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%')
				  AND (IFNULL(`profile`.`created`,now()) > DATE_ADD(now(),INTERVAL p_days DAY))
			ORDER BY `profile`.`acctnum` desc;
          WHEN (p_filter = 'B')
          THEN
            SELECT
              user.logonid,
              advisor_access.`privileges`                                 `advisor_privileges`,
              `ext_acct_info`.`clientAccountID`,
              `profile`.`acctnum` as acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
				CASE WHEN (IFNULL(`profile`.`managed`,'N') in ('N')) THEN 'Pending'
					 WHEN (upper(`profile`.`managed`) in ('A')) THEN 'Active'
					 ELSE 'Pending'
				END as acctStatus,
				CASE WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and IFNULL(`profile`.`status`,'V') in ('V')) THEN 'Visitor'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'N') THEN 'Pending'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'P') THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) = 'O') THEN 'Opened'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'N' and upper(`profile`.`status`) is not null) THEN 'Processing'
					 WHEN (IFNULL(`profile`.`managed`,'N') = 'A' and upper(`profile`.`status`) is not null) THEN 'Active'
					 ELSE 'Visitor'
				END as `status`,
              user.email                                               AS email,
              IFNULL(`profile`.`lastname`, user.lastname)              AS lastname,
              IFNULL(`profile`.`firstname`,user.firstname)             AS firstname,
              `profile`.tradePreference,
              `profile`.`acctType`                                     AS accttype,
              IFNULL(`profile`.age, 30)                                AS age,
              IFNULL(`profile`.horizon, 35)                            AS horizon,
              IFNULL(`profile`.riskIndex, 0)                           AS riskIndex,
              CAST(IFNULL(`profile`.initialInvestment, 0) AS SIGNED)   AS initialInvestment,
              round(funct_get_actualCapital(`profile`.acctnum), 0)     AS actualCapital,
              `profile`.keepLiquid                                     AS keepLiquid,
              CAST(IFNULL(`profile`.recurringInvestment, 0) AS SIGNED) AS recurringInvestment,
              IFNULL(`profile`.longTermGoal, 0)                        AS longTermGoal,
              IFNULL(`profile`.stayInvested, 0)                        AS stayInvested,
              DATE_FORMAT(`profile`.created, '%Y-%m-%d')               AS created
            FROM user_trade_profile `profile`
              INNER JOIN user_advisor_access advisor_access
              LEFT JOIN ext_acct_info
                ON (ext_acct_info.acctnum = `profile`.acctnum)
              LEFT JOIN user_access_role uar
                ON (`profile`.acctnum = uar.acctnum
                    AND uar.role = 'OWNER')
              LEFT JOIN user_logon user
                ON (uar.logonid = user.logonid)
            WHERE IFNULL(`profile`.`status`, 'N') like '%' 
				  AND advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%')
			ORDER BY `profile`.`acctnum` desc;
        END CASE;
      END IF;
    END;
 END$$
DELIMITER ;

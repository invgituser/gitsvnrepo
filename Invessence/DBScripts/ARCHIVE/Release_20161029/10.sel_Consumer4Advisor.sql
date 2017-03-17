DROP PROCEDURE IF EXISTS `invdb`.`sel_Consumer4Advisor`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_Consumer4Advisor`(
  p_logonid BIGINT,
  p_filter  VARCHAR(1),
  p_days    INTEGER
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

    IF (tAdvisor IS NOT NULL)
    THEN SET isAdvisor = TRUE;
    ELSE SET isAdvisor = FALSE;
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
              `profile`.acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
              'Active'                                                 AS acctstatus,
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
              ext_acct_info,
              user_trade_profile `profile`,
              user_access_role uar,
              user_logon user,
              user_advisor_access advisor_access
            WHERE ext_acct_info.acctnum = `profile`.acctnum
                  AND ext_acct_info.status = 'A'
                  AND uar.logonid = user.logonid
                  AND `profile`.acctnum = uar.acctnum
                  AND uar.role = 'OWNER'
                  AND advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%');
          WHEN (p_filter = 'P')
          THEN
            SELECT
              user.logonid,
              advisor_access.`privileges`                                 `advisor_privileges`,
              `profile`.acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
              'Pending'                                                AS acctstatus,
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
            WHERE `profile`.acctnum NOT IN (SELECT ext.acctnum
                                            FROM ext_acct_info ext
                                            WHERE ext.status = 'A')
                  AND advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%');
          WHEN (p_filter = 'B')
          THEN
            SELECT
              user.logonid,
              advisor_access.`privileges`                                 `advisor_privileges`,
              `profile`.acctnum,
              `profile`.advisor,
              `profile`.rep,
              `profile`.theme,
              `profile`.goal,
              `profile`.portfolioName,
              CASE WHEN (ext_acct_info.`status` = 'A')
                THEN 'Active'
              ELSE 'Pending'
              END                                                      AS acctstatus,
              user.email                                               AS email,
              ext_acct_info.applicantLName                             AS lastname,
              ext_acct_info.applicantFName                             AS firstname,
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
            WHERE advisor_access.logonid = p_logonid
                  AND IFNULL(`profile`.advisor, 'Invessence') LIKE IFNULL(advisor_access.advisor, '%')
                  AND IFNULL(`profile`.rep, '000') LIKE IFNULL(advisor_access.rep, '%');
        END CASE;
      END IF;
    END;
  END$$
DELIMITER ;

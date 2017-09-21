CREATE or REPLACE
VIEW `invdb`.`vw_historical_returns` AS
    SELECT 
        `rbsa_daily`.`ticker` AS `indexFund`,
        CONCAT(SUBSTR(`rbsa_daily`.`businessdate`,
                    1,
                    4),
                SUBSTR(`rbsa_daily`.`businessdate`,
                    6,
                    2),
                SUBSTR(`rbsa_daily`.`businessdate`,
                    9,
                    2)) AS `seqno`,
        `rbsa_daily`.`daily_return` AS `monthly_return`
    FROM
        `rbsa`.`rbsa_daily`
    WHERE
        (`rbsa_daily`.`ticker` IN ('SPY' , 'IEF', 'IAU', 'BIL', 'VEU'))
    ORDER BY 1 , 2 DESC;

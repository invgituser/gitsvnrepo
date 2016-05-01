DROP PROCEDURE IF EXISTS `sp_upload_monthly_dates`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_monthly_dates`()
BEGIN

	TRUNCATE TABLE inv_date_table;

	INSERT INTO inv_date_table
	SELECT 
		  DATE_FORMAT(businessdate,'%Y-%m-%d'),
		  DATE_FORMAT(prev_businessdate,'%Y-%m-%d')
	FROM invdb.sec_daily_info
	WHERE ticker = 'SPY';

	DROP TABLE IF EXISTS tmp_monthly_date_table_a;
	CREATE TEMPORARY TABLE tmp_monthly_date_table_a
	(
	  `businessdateYYYYMM` 	varchar(8) NOT NULL,
	  `first_businessdate`  varchar(10) NOT NULL,
	  `last_businessdate`   varchar(10) NOT NULL
	);

	DROP TABLE IF EXISTS tmp_monthly_date_table_b;
	CREATE temporary table tmp_monthly_date_table_b
	(
	  `businessdateYYYYMM` 	varchar(8),
	  `first_businessdate`  varchar(10),
	  `last_businessdate`   varchar(10)
	);

	INSERT INTO tmp_monthly_date_table_a
	SELECT 
		  substring(DATE_FORMAT(businessdate,'%Y-%m-%d'),1,7)
		, min(substring(DATE_FORMAT(businessdate,'%Y-%m-%d'),1,10))
		, max(substring(DATE_FORMAT(businessdate,'%Y-%m-%d'),1,10))
	FROM invdb.inv_date_table
	group by substring(businessdate,1,7);


	INSERT INTO tmp_monthly_date_table_b
	(`businessdateYYYYMM`, `first_businessdate`, `last_businessdate`)
	SELECT * from  tmp_monthly_date_table_a
	;

	DROP TABLE IF EXISTS tmp_composite_dates;

	CREATE temporary table tmp_composite_dates
	(
	  `businessdateYYYYMM` 		varchar(8),
	  `prev_businessdateYYYYMM` varchar(8)	
	);

	INSERT INTO tmp_composite_dates
	(`businessdateYYYYMM`, `prev_businessdateYYYYMM`)
	SELECT `a`.`businessdateYYYYMM`
		 , max(`b`.`businessdateYYYYMM`)
	FROM tmp_monthly_date_table_a a
		,tmp_monthly_date_table_b b
	WHERE a.`businessdateYYYYMM` > b.`businessdateYYYYMM`
	GROUP BY a.`businessdateYYYYMM`;

	TRUNCATE TABLE `inv_monthly_date_table`;
	INSERT INTO `inv_monthly_date_table` (
		  `businessdateYYYYMM`,
		  `first_businessdate`,
		  `last_businessdate`,
		  `prev_first_bdate`,
		  `prev_last_bdate`
	)
	SELECT 
		 a.`businessdateYYYYMM`, a.`first_businessdate`, a.`last_businessdate`
		,b.`first_businessdate`, b.`last_businessdate`
	FROM tmp_monthly_date_table_a a
		, tmp_monthly_date_table_b b
		, tmp_composite_dates c
	WHERE c.businessdateYYYYMM = a.businessdateYYYYMM
	AND   c.prev_businessdateYYYYMM = b.businessdateYYYYMM
	;

	commit;

END$$
DELIMITER ;

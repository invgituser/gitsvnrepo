DELIMITER $$
CREATE PROCEDURE `sp_upload_rbsa_security`(
)
BEGIN 

   begin
		delete from `tmp_rbsa_daily`
		where ticker in (select distinct ticker from `tmp_sec_load`);
		
		-- #1) Copy the data from tmp_sec_load into this temporary table
		INSERT INTO `tmp_rbsa_daily`
		(
		`ticker`,
		`businessdate`,
		`open_price`,
		`close_price`,
		`high_price`,
		`low_price`,
		`adjusted_price`,
		`volume`)
		SELECT 
			`tmp_sec_load`.`ticker`,
			STR_TO_DATE(`tmp_sec_load`.`bdate`,'%m/%d/%Y'),
			`tmp_sec_load`.`open`,
			`tmp_sec_load`.`close`,
			`tmp_sec_load`.`high`,
			`tmp_sec_load`.`low`,
			CAST(`tmp_sec_load`.`adj` as decimal(10,5)),
			`tmp_sec_load`.`volume`
		FROM `tmp_sec_load`
		;

		-- #2  Create a default date table.
		insert into tmp_date_table
		(businessdate, prev_businessdate)
		select tmp_rbsa_daily.businessdate,
			   ( select max(STR_TO_DATE(bdate,'%m/%d/%Y'))
				 FROM `tmp_sec_load`
				 WHERE tmp_rbsa_daily.ticker = `tmp_sec_load`.ticker
				 AND   invdb.get_business_date(tmp_rbsa_daily.businessdate,-1) >= STR_TO_DATE(bdate,'%m/%d/%Y')
				 GROUP BY `tmp_sec_load`.ticker
				)
		from tmp_rbsa_daily
		where tmp_date_table.businessdate <> tmp_rbsa_daily.businessdate;

		-- #3) Find the appropriate prev_business_date.
		-- Use this method, because it support holiday as data is loaded from approriate source.
		update tmp_rbsa_daily
		set prev_businessdate = ( select prev_businessdate
								  FROM `tmp_date_table`
								  WHERE tmp_date_table.businessdate = `tmp_rbsa_daily`.businessdate
								)
		where prev_businessdate is null
		;

		-- #4) Now upload the prev_close_price.
		update tmp_rbsa_daily
		set prev_close_price = (select t2.close 
								from tmp_sec_load t2
		where tmp_rbsa_daily.ticker = t2.ticker
		and tmp_rbsa_daily.prev_businessdate = STR_TO_DATE(t2.bdate,'%m/%d/%Y'))
		where prev_close_price is null
		;

		-- #5) Now update the daily_return column using the log function.
		update tmp_rbsa_daily
		set daily_return = ln (close_price / prev_close_price )
		where prev_close_price is not null
		and daily_return is null
		;

		-- #6) delete these tickers sec_daily_daily table
		-- delete from invdb.sec_daily_daily
		-- where ticker in (select distinct ticker from tmp_rbsa_daily);


		-- #7) Move data to sec_daily_daily table
		-- 			INSERT INTO `invdb`.`sec_daily_info`
		-- 			(`instrumentid`,
		-- 			`ticker`,
		-- 			`businessdate`,
		-- 			`open_price`,
		-- 			`close_price`,
		-- 			`high_price`,
		-- 			`low_price`,
		-- 			`adjusted_price`,
		-- 			`prev_businessdate`,
		-- 			`prev_close_price`,
		-- 			`daily_return`,
		-- 			`volume`)
		-- 			SELECT
		-- 				`sec`.`instrumentid`,
		-- 				`rbsa`.`ticker`,
		-- 				`rbsa`.`businessdate`,
		-- 				`rbsa`.`open_price`,
		-- 				`rbsa`.`close_price`,
		-- 				`rbsa`.`high_price`,
		-- 				`rbsa`.`low_price`,
		-- 				`rbsa`.`adjusted_price`,
		-- 				`rbsa`.`prev_businessdate`,
		-- 				`rbsa`.`prev_close_price`,
		-- 				`rbsa`.`daily_return`,
		-- 				`rbsa`.`volume`
		-- 			FROM `rbsa`.`tmp_rbsa_daily`, `invdb`.`sec_master` `sec`
		-- 			WHERE `rbsa`.`ticker` = `sec`.`ticker`
		--
 			
		commit; 
   end;

END$$
DELIMITER ;

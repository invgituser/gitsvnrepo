DROP procedure IF EXISTS `sp_upload_sec_daily`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_sec_daily`(
)
BEGIN 

   begin
		DECLARE tinputDate varchar(10);
		DECLARE dateformat varchar(10);

		DECLARE t_ticker varchar(30);

		TRUNCATE TABLE `rbsa`.`tmp_date_table`;
		SELECT MIN(`t`.`bdate`)
		INTO tinputDate
		FROM `rbsa`.`tmp_sec_load` as `t`
		;

		set dateformat='%Y-%m-%d';
		IF (INSTR(tinputDate,'-') = 0)
			then 
				update `tmp_sec_load`
				set bdate = concat(substr(bdate,1,4),'-',substr(bdate,5,2),'-',substr(bdate,7,2));
		END IF;


		INSERT INTO `rbsa`.`tmp_date_table`
		SELECT ticker, STR_TO_DATE(`tmp_sec_load`.`bdate`,dateformat) as bdate
		FROM `tmp_sec_load`
		WHERE STR_TO_DATE(`tmp_sec_load`.`bdate`,dateformat) NOT IN (SELECT businessdate from `rbsa`.`date_table`);

		commit;
		
		-- #1  Create a default date table.
		insert into `rbsa`.`date_table`
		(`businessdate`, `previousbdate`)
		select `tmp_date_table`.`businessdate` as bdate, s1.prevbdate
		from `rbsa`.`tmp_date_table`,
			   ( select t1.ticker, `t1`.`businessdate`, max(STR_TO_DATE(`t2`.`bdate`,dateformat)) as prevbdate
				 FROM `rbsa`.`tmp_date_table` as `t1`
				 LEFT JOIN `tmp_sec_load` as `t2`
					ON (`t1`.`ticker` = `t2`.`ticker`)
				 WHERE STR_TO_DATE(`t2`.`bdate`,dateformat) < `t1`.`businessdate`
				 GROUP BY `t1`.ticker, t1.`businessdate`
				) as s1
		WHERE `tmp_date_table`.ticker = s1.ticker
		AND   `tmp_date_table`.businessdate = s1.businessdate
		;

		commit;

		select min(ticker) 
		into t_ticker
		from `tmp_sec_load`;

		-- #1) Delete rbsa_daily table
		delete from `invdb`.`sec_daily_info`
		where ticker = t_ticker;

		commit;

		-- #2) Copy the data from tmp_sec_load into this temporary table
		INSERT INTO `invdb`.`sec_daily_info`
		(
		  `instrumentid`,
		  `ticker`,
		  `businessdate`,
		  `open_price`,
		  `close_price`,
		  `high_price`,
		  `low_price`,
		  `prev_businessdate`,
		  `prev_close_price`,
		  `daily_return`,
		  `volume` 
		)
		SELECT
			`sec`.`instrumentid`,
			`t1`.`ticker`,
			STR_TO_DATE(`t1`.`bdate`,'%Y-%m-%d') as bdate,
			`t1`.`open`,
			`t1`.`close`,
			`t1`.`high`,
			`t1`.`low`,
			STR_TO_DATE(`t2`.`bdate`,'%Y-%m-%d') as prevbdate,
			`t2`.`close`,
			ln (`t1`.`close` / IFNULL(`t2`.`close`,`t1`.`close` )) as dailyReturn,
			`t1`.`volume`
		FROM `tmp_sec_load` as `t1`,
			 `tmp_sec_load` as `t2`,
			 `rbsa`.`date_table` as `date_table`,
			 `sec_master` as `sec`
		WHERE `t1`.`ticker` = `t2`.`ticker`
		AND   `t1`.`ticker` = `sec`.`ticker`
		AND   `t1`.`bdate` = `date_table`.`businessdate`
		AND   `t2`.`bdate` = `date_table`.`previousbdate`
		;

		commit;

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

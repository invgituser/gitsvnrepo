
DROP PROCEDURE if exists sp_create_daily_returns;
delimiter $$

create procedure sp_create_daily_returns()
begin
  declare no_more_data int(2) DEFAULT 0;
  declare instrid      bigint(20);
  declare ticker       varchar(20);
  declare bdate        date;
  declare close_price  double;
  declare open_price  double;
  declare prev_bdate        date;
  declare prev_close_price  double;

  declare current_data CURSOR for
		SELECT `s1`.`instrumentid`,
			  `s1`.`ticker`,
			  `s1`.`businessdate`,
			  `s1`.`close`,
			  `s1`.`open`
		FROM `sec_daily_info` s1
		WHERE s1.businessdate NOT IN (select distinct dr.businessdate from daily_returns dr)
        ;
  declare CONTINUE HANDLER FOR NOT FOUND SET no_more_data=1;

  OPEN current_data;
  current_data_cursor: REPEAT
    FETCH current_data INTO instrid, ticker, bdate, close_price, open_price;
	SELECT 
			  MAX(`s2`.`businessdate`),
			  MAX(`s2`.`close`)
    INTO prev_bdate, prev_close_price
    FROM `sec_daily_info` s2
    WHERE `s2`.`instrumentid` = instrid
	AND   `s2`.`businessdate`< bdate
	;

	IF (prev_close_price is NULL)
		then  
			SET prev_close_price = open_price;
			SET prev_bdate = bdate;
	END IF;

		INSERT INTO `daily_returns`
			(`instrumentid`,
			`ticker`,
			`businessdate`,
			`prev_businessdate`,
			`prev_close`,
			`current_close`,
			`daily_return`)
		VALUES
			(
			   instrid
			 , ticker
			 , bdate
			 , prev_bdate
			 , prev_close_price
			 , close_price
			 , ln(close_price/prev_close_price)
			)
		;

  UNTIL no_more_data
  END REPEAT current_data_cursor;
  CLOSE current_data;
  
end
$$

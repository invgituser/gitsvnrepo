DROP PROCEDURE IF EXISTS `sp_sec_daily_info_add_mod`;

DELIMITER $$
CREATE PROCEDURE `sp_sec_daily_info_add_mod`(
	IN  p_addmodflag         VARCHAR(1),
    IN  p_ticker             VARCHAR(20),
	IN  p_businessdate       DATE,
	IN  p_open               double,
    IN  p_high               double,
    IN  p_low                double,
    IN  p_close              double,
	IN  p_volume             bigint(20),
    IN  p_adjusted           double,
    IN  p_prev_businessdate  date,
    IN  p_prev_close_price   double
)
BEGIN 
    DECLARE t_count   INTEGER;
    DECLARE t_instrID BIGINT(20);
	DECLARE t_businessdate date;
    DECLARE t_daily_return double;

    BEGIN

		 SELECT instrumentid
		 INTO t_instrID
		 FROM sec_master
		 WHERE ticker = p_ticker;

		 IF (t_instrID is NOT NULL)
		 THEN
		
			 SELECT COUNT(*)
			 INTO t_count
			 FROM sec_daily_info
			 WHERE instrumentid = t_instrID
			 AND   businessdate = p_businessdate
			 ;
		
			IF (IFNULL(p_prev_close_price,0) <> 0)
			then 
				SET t_daily_return = ln (p_close/p_prev_close_price);
					 -- SELECT 'Calc' as src, p_ticker, p_prev_close_price, t_daily_return;
			END IF;

             IF (p_prev_businessdate is NULL)
				THEN
					select max(s.businessdate)
					INTO p_prev_businessdate
					from sec_daily_info s
					where s.instrumentid = t_instrID
					AND   s.businessdate < p_businessdate;
			 END IF;

			 IF (t_count > 0)
				THEN 
					SELECT 'M' into p_addmodflag;
			 END IF;
   
			IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
				BEGIN
				 -- SELECT 'INSERT' as src, p_ticker, p_prev_close_price, t_daily_return;
					INSERT INTO `sec_daily_info`
					(`instrumentid`,
					`ticker`,
					`businessdate`,
					`open_price`,
					`high_price`,
					`low_price`,
					`close_price`,
					`volume`,
					`adjusted_price`,
					`prev_businessdate`,
					`prev_close_price`,
					`daily_return`

					)
					VALUES
					( 
					t_instrID,
				    p_ticker,
					p_businessdate,
					p_open,
					p_high,
					p_low,
					p_close,
					p_volume,
					p_adjusted,
					p_prev_businessdate,
					p_prev_close_price,
					t_daily_return
					 ) ; 
				END;
			ELSE
				BEGIN
				 -- SELECT 'UPDATE' as src, p_ticker, p_prev_close_price, t_daily_return;
				 UPDATE  sec_daily_info
				 SET 
				`open_price` = p_open,
				`close_price` = p_close,
				`high_price`  = p_high,
				`low_price`   = p_low,
				`adjusted_price` = p_adjusted,
				`volume` = p_volume,
				`prev_businessdate` = p_prev_businessdate,
				`prev_close_price` = p_prev_close_price,
				`daily_return` = t_daily_return
				 WHERE
				instrumentid = t_instrID and
				businessdate = p_businessdate;
				END;
			END IF;
		 END IF;
    END;

END$$
DELIMITER ;

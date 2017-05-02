## Create Procedure rbsa.daily_price_processor

USE `rbsa`;
DROP procedure IF EXISTS `daily_price_processor`;

DELIMITER $$
USE `rbsa`$$
CREATE PROCEDURE `daily_price_processor`(in p_businessDate varchar(20),out dailymsg varchar(20),in p_dest_currency varchar(20))
BEGIN
  DECLARE v_finished INTEGER DEFAULT 0;

  DECLARE v_ticker,v_dest_currency varchar(20) DEFAULT "";
  DECLARE v_businessdate varchar(10) DEFAULT "";
  DECLARE v_open_price double DEFAULT 0;
  DECLARE v_close_price double DEFAULT 0;
  DECLARE v_high_price double DEFAULT 0;
  DECLARE v_low_price double DEFAULT 0;
  DECLARE v_adjusted_price double DEFAULT 0;
  DECLARE v_prev_businessdate date DEFAULT 0;
  DECLARE v_prev_close_price double DEFAULT 0;
  DECLARE v_daily_return double DEFAULT 0;
  DECLARE v_volume bigint(20) DEFAULT 0;
  DECLARE v_monthly_return double DEFAULT 0;
  DECLARE v_return_threshold double default 0;

   declare v_prev_date varchar(10) DEFAULT "";
  DECLARE r_count   INTEGER;
  DECLARE t_count   INTEGER;
  DECLARE t_instrID BIGINT(20);
  DECLARE v_addmodflag VARCHAR(1);
  DECLARE v_prev_month_businessdate DATE;
  DECLARE v_prev_monthly_adjusted DOUBLE DEFAULT 0;
  DECLARE v_prev_converted_adjusted_price double DEFAULT 0;
  DECLARE v_converted_adjusted_price double DEFAULT 0;


   DEClARE price_cursor CURSOR FOR SELECT ticker,
  businessdate,
  open_price,
  close_price,
  high_price,
  low_price,
  adjusted_price,
  prev_businessdate,
  prev_close_price,
  daily_return,
  volume,
  monthly_return,
prev_month_businessdate,prev_monthly_adjusted,converted_adjusted_price,dest_currency FROM  tmp_rbsa_daily;

   DECLARE CONTINUE HANDLER
          FOR NOT FOUND SET v_finished = 1;

   OPEN price_cursor;

   get_price: LOOP

  FETCH price_cursor INTO v_ticker,
  v_businessdate,
  v_open_price,
  v_close_price,
  v_high_price,
  v_low_price,
  v_adjusted_price,
  v_prev_businessdate,
  v_prev_close_price,
  v_daily_return,
  v_volume,
  v_monthly_return,v_prev_month_businessdate,v_prev_monthly_adjusted,v_converted_adjusted_price,v_dest_currency;

  BEGIN

   IF v_finished = 1
   THEN
       LEAVE get_price;
   END IF;
	set v_prev_converted_adjusted_price=0;

SELECT
    COUNT(*), return_threshold
INTO t_instrID , v_return_threshold FROM
    invdb.sec_source_mapping
WHERE
    ticker_source_name = v_ticker;

  IF (t_instrID is NOT NULL)
  THEN

      SELECT COUNT(*)
      INTO t_count
      FROM invdb.sec_daily_info
      WHERE ticker = v_ticker
      AND   businessdate = v_businessdate;

      IF (v_prev_businessdate is NULL)
          THEN
          select invdb.get_business_date(p_businessDate,-1) into v_prev_businessdate;

SELECT
    converted_adjusted_price, close_price
INTO v_prev_converted_adjusted_price , v_prev_close_price FROM
    rbsa_daily
WHERE
    ticker = v_ticker
        AND businessdate = v_prev_businessdate;
              SET v_daily_return = ln (v_converted_adjusted_price/v_prev_converted_adjusted_price);


UPDATE tmp_rbsa_daily
SET
    daily_return = v_daily_return,
    prev_businessdate = v_prev_businessdate,
    prev_close_price = v_prev_close_price,
    converted_prev_adjusted=v_prev_converted_adjusted_price
WHERE
    ticker = v_ticker
        AND businessdate = v_businessdate;

          END IF;

        --   IF (IFNULL(v_prev_close_price,0) <> 0)
SELECT
    COUNT(*)
INTO r_count FROM
    rbsa_daily
WHERE
    ticker = v_ticker
        AND businessdate = v_businessdate;
  	if(r_count>0)
         then
       begin
         update rbsa_daily
         set
         ticker=v_ticker,
         businessdate=v_businessdate,
         open_price=v_open_price,
         close_price=v_close_price,
         high_price=v_high_price,
         low_price=v_low_price,
         adjusted_price=v_adjusted_price,
         prev_businessdate=v_prev_businessdate,
         daily_return=v_daily_return,
         prev_close_price=v_prev_close_price,
         volume=v_volume,dest_currency=v_dest_currency,converted_adjusted_price=v_converted_adjusted_price,converted_prev_adjusted=v_prev_converted_adjusted_price
         where
         ticker=v_ticker and
         businessdate=v_businessdate and
         dest_currency=p_dest_currency;

       end;
       else
        INSERT INTO rbsa_daily
  		 	  (ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,volume,prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,dest_currency,converted_adjusted_price,converted_prev_monthly_adjusted)
  	select ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,volume,prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,dest_currency,converted_adjusted_price,converted_prev_monthly_adjusted
  	from tmp_rbsa_daily;

      end if;




   END IF;

  BEGIN


  end;

  END;

  END LOOP get_price;

  CLOSE price_cursor;
SELECT v_daily_return, v_return_threshold;
  if(v_daily_return>v_return_threshold)then
   set dailymsg='Send Alert';
   else
   set dailymsg='Do not Send Alert';
   end if;
  END$$

DELIMITER ;


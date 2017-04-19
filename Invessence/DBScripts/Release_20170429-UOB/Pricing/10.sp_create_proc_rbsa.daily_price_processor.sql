## Create Procedure rbsa.daily_price_processor

USE rbsa;
DROP procedure IF EXISTS daily_price_processor;

DELIMITER $$
USE rbsa$$
CREATE  PROCEDURE daily_price_processor(in p_businessDate varchar(20))
BEGIN
  DECLARE v_finished INTEGER DEFAULT 0;
  
  DECLARE v_ticker varchar(20) DEFAULT "";
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
   
   declare v_prev_date varchar(10) DEFAULT ""; 
  DECLARE r_count   INTEGER;
  DECLARE t_count   INTEGER;
  DECLARE t_instrID BIGINT(20);
  DECLARE v_addmodflag VARCHAR(1);
  DECLARE v_prev_month_businessdate DATE;
  DECLARE v_prev_monthly_adjusted DOUBLE DEFAULT 0;
  DECLARE v_exchange_symbol varchar(20);
   
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
prev_month_businessdate,prev_monthly_adjusted,exchange_symbol FROM  tmp_rbsa_daily;
   
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
  v_monthly_return,v_prev_month_businessdate,v_prev_monthly_adjusted,v_exchange_symbol;
   
  BEGIN
  
   IF v_finished = 1 
   THEN 
       LEAVE get_price;
   END IF;
  
     SELECT instrumentid
     INTO t_instrID
     FROM invdb.sec_master
     WHERE ticker = v_ticker;
  
  IF (t_instrID is NOT NULL)
  THEN
    
      SELECT COUNT(*)
      INTO t_count
      FROM invdb.sec_daily_info
      WHERE ticker = v_ticker
      AND   businessdate = v_businessdate;
      
      IF (v_prev_businessdate is NULL)
          THEN
          select invdb.get_business_date(p_businessDate,-1)
  		into v_prev_businessdate;   
          update tmp_rbsa_daily set daily_return=v_daily_return ,
                prev_businessdate = v_prev_businessdate
  		      where ticker =v_ticker and businessdate= v_businessdate;
          END IF;
      
          IF (IFNULL(v_prev_close_price,0) <> 0)
          then
                SET v_daily_return = ln (v_adjusted_price/v_prev_close_price);
                update tmp_rbsa_daily set daily_return=v_daily_return ,
                prev_businessdate = v_prev_businessdate
  		      where ticker =v_ticker and businessdate= v_businessdate;
          END IF;
  
  		
  
  IF (t_count > 0)
  THEN
   BEGIN    
       UPDATE  invdb.sec_daily_info
       SET
      open_price = v_open_price,
      close_price = v_close_price,
      high_price  = v_high_price,
      low_price   = v_low_price,
      adjusted_price = v_adjusted_price,
      volume = v_volume,
      prev_businessdate = v_prev_businessdate,
      prev_close_price = v_prev_close_price,
      daily_return = v_daily_return
       WHERE      
      ticker =v_ticker and
      businessdate = v_businessdate;
   END;
  else
    BEGIN    
       INSERT INTO invdb.sec_daily_info
       (
       ticker,
       businessdate,
       open_price,
       high_price,
       low_price,
       close_price,
       volume,
       adjusted_price,
       prev_businessdate,
       prev_close_price,
       daily_return
  
       )
       VALUES
       (       
          v_ticker,
       v_businessdate,
       v_open_price,
       v_high_price,
       v_low_price,
       v_close_price,
       v_volume,
       v_adjusted_price,
       v_prev_businessdate,
       v_prev_close_price,
       v_daily_return
        ) ;
    END;
  END IF; 
                 
         SELECT COUNT(*)
  		INTO r_count
          FROM  rbsa_daily
          WHERE ticker=v_ticker and
         businessdate=v_businessdate;
         select r_count;
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
         volume=v_volume
         where
         ticker=v_ticker and
         businessdate=v_businessdate;
     select 'update';
       end;
       else
        INSERT INTO rbsa_daily                  
  		 	  (ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,volume,prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,exchange_symbol)  
  	select ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,volume,prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,exchange_symbol
  	from tmp_rbsa_daily;
     select 'insert';
      end if;             
             
      
   
  
   END IF;
  		
  BEGIN
         
          
  end; 
  
  END;
  
  END LOOP get_price;
   
  CLOSE price_cursor;
  
  END$$

DELIMITER ;
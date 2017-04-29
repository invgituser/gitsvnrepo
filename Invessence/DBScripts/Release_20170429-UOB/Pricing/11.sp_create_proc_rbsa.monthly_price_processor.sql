## Create Procedure rbsa.monthly_price_processor

USE `rbsa`;
DROP procedure IF EXISTS `monthly_price_processor`;

DELIMITER $$
USE `rbsa`$$
CREATE PROCEDURE `monthly_price_processor`(in  p_businessDate varchar(20), in  p_ticker varchar(20))
BEGIN
    DECLARE v_finished INTEGER DEFAULT 0;
    DECLARE v_ticker varchar(20) DEFAULT '';
    DECLARE v_businessdate varchar(10) DEFAULT '';
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
    declare v_last_businessdate varchar(10);
    declare v_prev_last_bdate varchar(10) default '';
    declare pvd varchar(10) default '';
    DECLARE v_addmodflag VARCHAR(1);
    declare m_count integer;
    declare v_prev_eom double(20,8) default 0.0;
    DECLARE t_count   INTEGER;
    DECLARE t_instrID BIGINT(20);
    declare vc integer;
    declare vctext text(50000);
    declare p_count integer;
    declare pd_count integer;
    declare plbd_count integer;
    declare cnt BIGINT(20);
    declare v_prev_adjusted_price,v_prev_monthly_return double(20,8) default 0.0;
    declare v_prev_month_end DATE;
    DECLARE v_prev_converted_adjusted_price double DEFAULT 0;
    declare v_prev_converted_eom double(20,8) default 0.0;
    DECLARE v_converted_adjusted_price double DEFAULT 0;
    DECLARE v_converted_prev_monthly_adjusted double DEFAULT 0;


    DEClARE price_cursor CURSOR FOR SELECT ticker,
    businessdate,open_price,close_price,high_price,low_price,
    adjusted_price,prev_businessdate,prev_close_price,daily_return,
    volume,monthly_return,converted_adjusted_price FROM  tmp_rbsa_daily where ticker =p_ticker ;
    DECLARE CONTINUE HANDLER
    FOR NOT FOUND SET v_finished = 1;
    OPEN price_cursor;
    set vc=0;
    set vctext='';
    set v_prev_eom =0;
    set v_prev_converted_eom=0;
    set cnt=0;
    get_price: LOOP

    FETCH price_cursor INTO v_ticker,
    v_businessdate,v_open_price,v_close_price,v_high_price,v_low_price,
    v_adjusted_price,v_prev_businessdate,v_prev_close_price,v_daily_return,
    v_volume,v_monthly_return,v_converted_adjusted_price;


    	IF (v_prev_businessdate is NULL and cnt>0) THEN
    		select count(*) into pd_count from invdb.inv_date_table
    		where businessdate=v_businessdate;

    		if(pd_count>0)then
    			select  prev_businessdate
                INTO v_prev_businessdate
    			from invdb.inv_date_table
    			where businessdate=v_businessdate;

               select adjusted_price,converted_adjusted_price INTO v_prev_close_price,v_prev_converted_adjusted_price
    			from rbsa.tmp_rbsa_daily
    			where businessdate=v_prev_businessdate;

    			UPDATE tmp_rbsa_daily
    			SET prev_businessdate = v_prev_businessdate,
    			 prev_close_price = v_prev_close_price,
    			 converted_prev_adjusted=v_prev_converted_adjusted_price
    			WHERE ticker = p_ticker
    			AND businessdate = v_businessdate;
    		end if;
    	END IF;

    set cnt=cnt+1;
    	SELECT COUNT(*)
    	INTO m_count
    	FROM invdb.inv_monthly_date_table
    	WHERE last_businessdate = v_businessdate;

    	IF(m_count = 0)then
    		IF (IFNULL(v_prev_close_price,0) <> 0)then
				-- SET v_daily_return = ln (v_adjusted_price/v_prev_close_price);
              select v_converted_adjusted_price,v_prev_converted_adjusted_price,'B';
                SET v_daily_return = ln (v_converted_adjusted_price/v_prev_converted_adjusted_price);
    			UPDATE tmp_rbsa_daily
    			SET daily_return = v_daily_return
    			WHERE ticker = p_ticker
    			AND businessdate = v_businessdate;
    		END IF;
    	else
    		select count(prev_last_bdate)
    		into plbd_count
    		from invdb.inv_monthly_date_table
    		where last_businessdate = v_businessdate;
    		if(plbd_count>0)then
    			select prev_last_bdate
    			into v_prev_last_bdate
    			from invdb.inv_monthly_date_table
    			where last_businessdate = v_businessdate;

    			IF (v_prev_last_bdate is not null or v_prev_last_bdate <> '') then
    				IF (IFNULL(v_prev_close_price,0) <> 0)then
    					-- SET v_daily_return = ln (v_adjusted_price/v_prev_close_price);
    					SET v_daily_return = ln (v_converted_adjusted_price/v_prev_converted_adjusted_price);
               -- select v_converted_adjusted_price,v_prev_converted_adjusted_price,'A';

    					UPDATE tmp_rbsa_daily
    					SET daily_return = v_daily_return
    					WHERE ticker = p_ticker
    					AND businessdate = v_businessdate;
    				END IF;

    				SELECT count(*) into p_count
    				FROM tmp_rbsa_daily
    				WHERE ticker = p_ticker
    				AND businessdate = v_prev_last_bdate;

    					if(p_count>0) then
    						SELECT ifnull(adjusted_price,0),ifnull(converted_adjusted_price,0)
    						INTO v_prev_eom,v_prev_converted_eom FROM
    						tmp_rbsa_daily
    						WHERE ticker = p_ticker
    						AND businessdate = v_prev_last_bdate;

 						select prev_last_bdate into v_prev_month_end from invdb.inv_monthly_date_table where last_businessdate=v_businessdate;
 						select adjusted_price,monthly_return,converted_adjusted_price into v_prev_adjusted_price,v_prev_monthly_return,v_converted_prev_monthly_adjusted  from rbsa.tmp_rbsa_daily where businessdate=v_prev_month_end;
    						IF (IFNULL(v_prev_eom,0) <> 0)then
                        -- select v_prev_month_end,v_prev_adjusted_price,v_prev_monthly_return;
    							-- SET v_monthly_return = ln (v_adjusted_price/v_prev_eom);
                                SET v_monthly_return = ln (v_converted_adjusted_price/v_prev_converted_eom);
						-- select v_converted_adjusted_price,v_prev_converted_eom;
    							update tmp_rbsa_daily set monthly_return=v_monthly_return ,prev_month_businessdate=v_prev_month_end,prev_monthly_adjusted=v_prev_adjusted_price,converted_prev_monthly_adjusted=v_converted_prev_monthly_adjusted
    							where ticker =v_ticker and businessdate= v_businessdate;
    						end if;
    					end if;
    			end if;
    		end if;
    	END IF;
    	IF v_finished = 1 THEN
    	LEAVE get_price;
    	END IF;


    END LOOP get_price;

    CLOSE price_cursor;

    delete from rbsa_daily where ticker= p_ticker;

    		INSERT INTO rbsa_daily
    			  (ticker, businessdate, open_price, close_price, high_price, low_price, adjusted_price, prev_businessdate, prev_close_price, daily_return, volume, monthly_return,prev_month_businessdate,prev_monthly_adjusted,dest_currency,converted_adjusted_price,converted_prev_adjusted,converted_prev_monthly_adjusted)
    		select ticker, businessdate, open_price, close_price, high_price, low_price, adjusted_price, prev_businessdate, prev_close_price, daily_return, volume, monthly_return,prev_month_businessdate,prev_monthly_adjusted,dest_currency,converted_adjusted_price,converted_prev_adjusted,converted_prev_monthly_adjusted
    		from tmp_rbsa_daily
    		where ticker=p_ticker;

    end$$

DELIMITER ;


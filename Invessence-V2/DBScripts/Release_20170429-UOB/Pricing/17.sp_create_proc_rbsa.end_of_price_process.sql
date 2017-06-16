## Create Procedure rbsa.end_of_price_process

USE `rbsa`;
DROP procedure IF EXISTS `end_of_price_process`;

DELIMITER $$
USE `rbsa`$$
CREATE   PROCEDURE `end_of_price_process`(in p_process varchar(20),in p_businessdate varchar(20) )
BEGIN
  DECLARE d_count   INTEGER;
  declare m_count integer ;
  declare md_count integer;
  declare v_prev_date varchar(10) DEFAULT "";
  declare v_businessdateYYYYMM varchar(10) default "";
  declare v_first_bdate_month varchar(10) default "";
  declare v_last_bdate_month varchar(10) default "";
  declare v_prev_last_bdate_month varchar(10) default "";
  declare v_prev_first_bdate varchar(10) default "";
  declare pfd_count integer ;

  if p_process='DAILY' THEN
  select count(*) into d_count from invdb.inv_date_table where businessdate= p_businessdate;

  begin
  	    update invdb.invessence_switch
          set
              lastupdated= NOW(),
          value=p_businessdate
          where
          name= 'PRICE_DATE';
   end;



      IF(d_count = 0) then
  begin
  declare v_prev_date varchar(10) DEFAULT "";

 SELECT
     invdb.get_prev_bdate(STR_TO_DATE(p_businessdate, '%Y-%m-%d'),
             1)
 INTO v_prev_date;

         insert into invdb.inv_date_table
         (businessdate,
          prev_businessdate)

          values
          ( p_businessdate, v_prev_date);
             end;
             end if;




  elseif p_process='MONTHLY' THEN
  select count(*)
      into m_count
  	from invdb.inv_date_table
      where businessdate=p_businessdate;

         	 begin
  	    update invdb.invessence_switch
          set
              lastupdated= NOW(),
          value=p_businessdate
          where
          name='PRICE_DATE';
          end;

      IF(m_count = 0) then
  begin

         select invdb.get_prev_bdate(str_to_date(p_businessdate,'%Y-%m-%d') ,1)
  	   into  v_prev_date ;

         insert into invdb.inv_date_table
         (businessdate,
          prev_businessdate)
          values
          (p_businessdate, v_prev_date);
             end;



 SELECT
     STR_TO_DATE(value, '%Y%m%d')
 INTO v_first_bdate_month FROM
     invdb.invessence_switch
 WHERE
     name = '1ST_BDATE_THIS_MONTH';



 SELECT invdb.get_business_date(v_first_bdate_month, - 1) INTO v_prev_last_bdate_month;

 SELECT
     COUNT(first_businessdate)
 INTO pfd_count FROM
     invdb.inv_monthly_date_table
 WHERE
     last_businessdate = v_prev_last_bdate_month;

       if(pfd_count>0) then
       select first_businessdate into v_prev_first_bdate from invdb.inv_monthly_date_table
      where last_businessdate=v_prev_last_bdate_month;
      end if;





             insert into invdb.inv_monthly_date_table
             (businessdateYYYYMM, first_businessdate, last_businessdate, prev_first_bdate, prev_last_bdate)
             values
             (substring(p_businessdate,1,7),v_first_bdate_month,p_businessdate,v_prev_first_bdate,v_prev_last_bdate_month);




   end if;

  end if;
 UPDATE invdb.invessence_switch
 SET
     lastupdated = NOW(),
     value = REPLACE(p_businessdate, '-', '')
 WHERE
     name = 'PRICE_DATE';

 delete from invdb.sec_daily_info;
 insert into invdb.sec_daily_info(dest_currency,ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,converted_adjusted_price,volume,
 prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,converted_prev_adjusted,converted_prev_monthly_adjusted)
 select rd.dest_currency,rd.ticker,rd.businessdate,rd.open_price,rd.close_price,rd.high_price,rd.low_price,rd.adjusted_price,rd.converted_adjusted_price,rd.volume,
 rd.prev_businessdate,rd.prev_close_price,rd.daily_return,rd.prev_month_businessdate,rd.prev_monthly_adjusted,rd.monthly_return,rd.converted_prev_adjusted,rd.converted_prev_monthly_adjusted
 from rbsa.rbsa_daily rd join invdb.sec_source_mapping ssm on(rd.ticker=ssm.ticker_source_name and rd.dest_currency=ssm.dest_currency) where businessdate=p_businessdate;

  END$$

DELIMITER ;




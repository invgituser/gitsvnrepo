## Create Procedure rbsa.end_of_price_process

USE rbsa;
DROP procedure IF EXISTS end_of_price_process;

DELIMITER $$
USE rbsa$$
CREATE PROCEDURE end_of_price_process(in p_process varchar(20),in p_businessdate varchar(20) )
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
 select count(*)
     into d_count
 	from invdb.inv_date_table
     where businessdate= p_businessdate;
     
     
     
         begin 
 	    update invdb.invessence_switch
         set
             lastupdated= NOW(),
         value=REPLACE (p_businessdate,'-','')
         where
         name= 'PRICE_DATE';
        end;



     IF(d_count = 0) then
 begin
 declare v_prev_date varchar(10) DEFAULT "";

        select invdb.get_prev_bdate(str_to_date(p_businessdate,'%Y-%m-%d') ,1)
 	   into  v_prev_date ;

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
         value=REPLACE (p_businessdate,'-','')
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



      select str_to_date(value,'%Y%m%d')
 	 into v_first_bdate_month from invdb.invessence_switch where name='1ST_BDATE_THIS_MONTH';



      select invdb.get_business_date(v_first_bdate_month,-1)
      into v_prev_last_bdate_month;

      select count(first_businessdate) into pfd_count from invdb.inv_monthly_date_table
     where last_businessdate=v_prev_last_bdate_month;

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
 
 END$$

DELIMITER ;
## Create Procedure rbsa.p_genMonthTable

USE rbsa;
DROP procedure IF EXISTS p_genMonthTable;

DELIMITER $$
USE rbsa$$
CREATE PROCEDURE p_genMonthTable()
begin
declare p_prev_fst_bdate varchar(10);
declare p_prev_lst_bdate varchar(10);
DECLARE done INT DEFAULT FALSE;
DECLARE curcnt INT;
Declare cur1 CURSOR FOR Select first_businessdate,last_businessdate From invdb.inv_monthly_date_table imdt;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

set curcnt=0;
OPEN cur1;

read_loop: LOOP
FETCH cur1 INTO p_prev_fst_bdate,p_prev_lst_bdate;


IF done THEN
LEAVE read_loop;
END IF;


SET curcnt=curcnt+1;

 update invdb.inv_monthly_date_table  set prev_first_bdate=p_prev_fst_bdate where prev_last_bdate=p_prev_lst_bdate;
 
 



-- set @jsonTableQuery :=concat('',@jsonTableQuery,' -', curcnt) ;

END LOOP;

CLOSE cur1;
SELECT curcnt AS 'MISSING DAYS COUNT';
end$$

DELIMITER ;
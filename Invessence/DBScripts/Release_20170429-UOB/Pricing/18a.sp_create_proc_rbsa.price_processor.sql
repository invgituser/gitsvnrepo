## Procedure created for daily & monthly procedure calls
USE rbsa;
DROP procedure IF EXISTS price_processor;

DELIMITER $$
USE rbsa$$
CREATE PROCEDURE price_processor(in p_process varchar(20),in p_businessDate varchar(20),in p_ticker varchar(20) ,out msg varchar(20),out dailymsg varchar(20)  )
BEGIN

if p_process='DAILY' THEN
call daily_price_processor(p_businessDate,dailymsg);
SELECT 'DAILY' INTO msg;
end if;
if p_process='MONTHLY' THEN
call monthly_price_processor(p_businessDate,p_ticker);
SELECT 'MONTHLY' INTO msg;
end if;
if p_process='ONDEMAND' THEN
SELECT 'ONDEMAND' INTO msg;

call monthly_price_processor(p_businessDate,p_ticker);
end if;
END$$

DELIMITER ;


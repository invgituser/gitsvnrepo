## Script creating sp_td_start_process

USE temp;
DROP procedure IF EXISTS sp_td_start_process;

DELIMITER $$
USE temp$$
CREATE PROCEDURE sp_td_start_process()
begin

SET SQL_SAFE_UPDATES = 0;

 delete from temp.tmp_td_demographic;
 delete from temp.tmp_td_position;
 delete from temp.tmp_td_price;
 delete from temp.tmp_td_security;
 delete from temp.tmp_td_transaction;
 delete from temp.tmp_td_unrealized;

SET SQL_SAFE_UPDATES = 1;
end$$

DELIMITER ;



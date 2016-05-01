DROP PROCEDURE IF EXISTS `del_virtual_portfolio`;

DELIMITER $$
CREATE PROCEDURE `del_virtual_portfolio`(
	IN p_acctnum bigint(20)
)
BEGIN 

   IF (p_acctnum is not NULL) then
	   DELETE FROM virtual_portfolio
	   WHERE acctnum = p_acctnum;
   END IF;
END$$
DELIMITER ;

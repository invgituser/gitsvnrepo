DROP PROCEDURE IF EXISTS `temp`.`sp_td_eod_process`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_td_eod_process`(
)
BEGIN
	DECLARE tDate	varchar(10);
    
  SET SQL_SAFE_UPDATES = 0;
    -- CALL `temp`.`sp_upload_td_nav`(); Moved to `sp_upload_td_unrealized` because to multi advisor load.
    
    CALL `temp`.`sp_updt_ext_acct_flag`();
    
    SELECT max(`ext_nav`.`reportDate`)
    INTO tDate
    FROM `invdb`.`ext_nav`;

	CALL `temp`.`sp_inv_switch_eod`(tDate);
    
	SET SQL_SAFE_UPDATES = 1;


END$$
DELIMITER ;

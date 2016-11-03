DROP PROCEDURE IF EXISTS `temp`.`sp_td_eod_process`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_td_eod_process`(
)
BEGIN
	DECLARE tDate	varchar(10);
    
	CALL `temp`.`sp_upload_td_nav`();
    
    CALL `temp`.`sp_updt_ext_acct_flag`();
    
    SELECT max(`ext_nav`.`reportDate`)
    INTO tDate
    FROM `invdb`.`ext_nav`;

	CALL `temp`.`sp_inv_switch_eod`(tDate);

END$$
DELIMITER ;
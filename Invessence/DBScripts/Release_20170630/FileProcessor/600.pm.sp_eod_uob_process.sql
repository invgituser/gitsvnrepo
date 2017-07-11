DROP PROCEDURE IF EXISTS `temp`.`sp_eod_uob_process`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_eod_uob_process`(
)
BEGIN

	BEGIN
		CALL `invdb`.`sp_upload_trade_process_indetifier`;
		CALL `invdb`.`sp_eod_notification`();
	END;
    
    BEGIN
    END;
END$$
DELIMITER ;

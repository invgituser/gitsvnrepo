DROP PROCEDURE IF EXISTS `updt_mark_billingStatement`;

DELIMITER $$
CREATE PROCEDURE `updt_mark_billingStatement`(
	IN p_clientAccountID   varchar(20)
)
BEGIN
	update fees_charged
	   set processed = 'Y',
		   sentDate = now()
	where processed ='N'
	and clientAccountID = p_clientAccountID;

	
END$$
DELIMITER ;

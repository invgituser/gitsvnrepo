DROP PROCEDURE IF EXISTS `sp_upload_commission`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_commission`(
)
BEGIN 

   begin
		delete from commission
		where concat(`clientAccountID`,`fromDate`,`toDate`) 
				in (select concat(`clientAccountID`,replace(`tmp_commission`.`fromDate`,'-',''),replace(`tmp_commission`.`toDate`,'-',''))
					from tmp_commission)
		;
		INSERT INTO `invdb`.`commission`
		(`clientAccountID`,
		`fromDate`,
		`toDate`,
		`commission`,
		`otherFee`,
		`advisorFee`)
		SELECT `tmp_commission`.`clientAccountID`,
			replace(`tmp_commission`.`fromDate`,'-',''),
			replace(`tmp_commission`.`toDate`,'-',''),
			`tmp_commission`.`commission`,
			`tmp_commission`.`otherFee`,
			`tmp_commission`.`advisorFee`
		FROM `invdb`.`tmp_commission`;

   end;

END$$
DELIMITER ;

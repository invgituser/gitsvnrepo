DROP PROCEDURE IF EXISTS `invdb`.`sp_upload_nav_daily`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_upload_nav_daily`(
)
BEGIN 

   DECLARE tReportDate	VARCHAR(10);
      
   set tReportDate = `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE');
 
   begin
		delete from `ext_nav_daily`
		where concat(`clientAccountID`,`reportDate`) in (select concat(`clientAccountID`,IFNULL(`reportDate`,tReportDate))
																  from tmp_nav_daily)
		;

		INSERT INTO `invdb`.`ext_nav_daily`
		(`clientAccountID`,
		`reportDate`,
		`currencyPrimary`,
		`fxRateToBase`,
		`cash`,
		`stock`,
		`funds`,
		`interestAccrual`,
		`dividentAccrual`,
		`total`)
		SELECT `tmp_nav_daily`.`clientAccountID`,
			IFNULL(`tmp_nav_daily`.`reportDate`,tReportDate) as `reportDate`,
			`tmp_nav_daily`.`currencyPrimary`,
			`tmp_nav_daily`.`fxRateToBase`,
			`tmp_nav_daily`.`cash`,
			`tmp_nav_daily`.`stock`,
			`tmp_nav_daily`.`funds`,
			`tmp_nav_daily`.`interestAccrual`,
			`tmp_nav_daily`.`dividentAccrual`,
			`tmp_nav_daily`.`total`
		FROM `invdb`.`tmp_nav_daily`;
   end;

END$$
DELIMITER ;

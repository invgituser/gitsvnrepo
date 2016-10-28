DROP PROCEDURE IF EXISTS `sp_upload_nav_daily`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_nav_daily`(
)
BEGIN 

   begin
		delete from nav_daily
		where concat(`clientAccountID`,`reportDate`) in (select concat(`clientAccountID`,replace(`reportDate`,'-',''))
																  from tmp_nav_daily)
		;

		INSERT INTO `nav_daily`
		(`clientAccountID`,
		`reportDate`,
		`cash`,
		`stock`,
		`funds`,
		`interestAccrual`,
		`dividentAccrual`,
		`total`)
		SELECT
			`clientAccountID`,
			replace(`reportDate`,'-',''),
			`cash`,
			`stock`,
			`funds`,
			`interestAccrual`,
			`dividentAccrual`,
			`total`
		FROM `tmp_nav_daily`;

   end;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `sp_add_user_reports`;

DELIMITER $$
CREATE PROCEDURE `sp_add_user_reports`(
  p_acctnum BIGINT(20),
  p_reportDate varchar(8),
  p_reportName varchar(20),
  p_filename   varchar(255)
)
BEGIN

	DELETE from user_reports
	WHERE acctnum = p_acctnum
	AND   reportDate = p_reportDate
	AND   reportName = p_reportName;

		INSERT INTO `user_reports`
		(`acctnum`,
		`reportDate`,
		`reportName`,
		`filename`,
		`created`)
		VALUES
		(p_acctnum,
		p_reportDate,
		p_reportName,
		p_filename,
		now());


END
$$
DELIMITER ;

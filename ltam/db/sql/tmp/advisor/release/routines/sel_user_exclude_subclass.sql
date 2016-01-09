DROP PROCEDURE IF EXISTS `sel_user_exclude_subclass`;

DELIMITER $$
CREATE PROCEDURE `sel_user_exclude_subclass`(
    IN p_acctnum  BIGINT(20)
)
BEGIN

  DECLARE tfound INTEGER;
  if (p_acctnum is NOT NULL)
  then
	BEGIN
		SELECT 
			`user_exclude_subclass`.`acctnum`,
			`user_exclude_subclass`.`assetclass`,
			`user_exclude_subclass`.`subclass`,
			`user_exclude_subclass`.`created`,
			`user_exclude_subclass`.`lastUpdated`
		FROM `user_exclude_subclass`
		WHERE `user_exclude_subclass`.`acctnum` = p_acctnum;

	END;
  end if;
END$$
DELIMITER ;

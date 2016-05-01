DROP PROCEDURE IF EXISTS `sp_ExcludedSubclass_add_mod`;

DELIMITER $$
CREATE PROCEDURE `sp_ExcludedSubclass_add_mod`(
	IN p_acctnum          bigint(20),
	IN p_assetclass		  varchar(20),
	IN p_subclass		  varchar(20)
)
BEGIN
	DECLARE p_count	INTEGER;

	begin
		select count(*)
		into p_count
		FROM `user_exclude_subclass`
		WHERE `user_exclude_subclass`.acctnum = p_acctnum
		AND   `user_exclude_subclass`.assetclass = p_assetclass
		AND   `user_exclude_subclass`.subclass = p_subclass;
	end;
	IF (IFNULL(p_count,0) = 0)
		then
			INSERT INTO `invdb`.`user_exclude_subclass`
				(`acctnum`,
				`assetclass`,
				`subclass`,
				`created`,
				`lastUpdated`)
			VALUES
			(p_acctnum,
			p_assetclass,
			p_subclass,
			now(),
			null);
	end if;

END$$
DELIMITER ;
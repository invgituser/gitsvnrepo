DROP PROCEDURE IF EXISTS `sp_del_user_exclude_subclass`;

DELIMITER $$
CREATE PROCEDURE `sp_del_user_exclude_subclass`(
    IN p_acctnum  BIGINT(20)
)
BEGIN

  DECLARE tfound INTEGER;
  if (p_acctnum is NOT NULL)
  then
	BEGIN
		DELETE FROM user_exclude_subclass
		WHERE acctnum = p_acctnum;
	END;
  end if;
END$$
DELIMITER ;

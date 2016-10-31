DROP PROCEDURE IF EXISTS `sp_add_user_exclude_subclass`;

DELIMITER $$
CREATE PROCEDURE `sp_add_user_exclude_subclass`(
    IN p_acctnum  BIGINT(20),
    IN p_assetclass varchar(20),
	IN p_subclass VARCHAR(20)
)
BEGIN

  DECLARE tfound INTEGER;
  if (p_acctnum is NOT NULL)
  then
	BEGIN
		INSERT INTO user_exclude_subclass
		(
		  `acctnum`,
		  `assetclass`,
		  `subclass`,
		  `created`
		)
		VALUES 
		( p_acctnum,
		  p_assetclass,
		  p_subclass,
		  now()
		);

	END;
  end if;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `sel_themes`;

DELIMITER $$
CREATE PROCEDURE `sel_themes`(
    IN p_groupname  varchar(20)
)
BEGIN

 if (p_groupname is NULL)
  then
		/* This select is to return all groups */
		SELECT distinct
			groupname, 
			theme 
		FROM sec_master_group;
  else
		SELECT distinct
			groupname, 
			theme 
		FROM sec_master_group
		WHERE groupname = p_groupname;
  end if;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS `sp_invessence_switch_post`;

delimiter $$
CREATE PROCEDURE `sp_invessence_switch_post`(
	IN p_name varchar(20),
	IN p_value varchar(20)
)
BEGIN
   DECLARE t_count INTEGER;

   SELECT COUNT(*)
   INTO t_count
   FROM invessence_switch
   WHERE name = p_name;

   -- Only modify if the key is avalaible.
   IF (t_count > 0) 
	THEN 
		 UPDATE  invessence_switch
		 SET 
			value = p_value,
			lastupdated = now()
		 WHERE
			name = p_name;
   END IF;

END$$


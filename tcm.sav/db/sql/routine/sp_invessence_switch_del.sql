delimiter $$

DROP PROCEDURE `sp_invessence_switch_del`
$$

CREATE PROCEDURE `sp_invessence_switch_del`(
	IN p_name varchar(20)
)
BEGIN

	/* Don't delete anything from switch table.  Need to see implication before any such delete.

     DELETE  FROM invessence_switch
     WHERE
           name = p_name;
	*/
	select 'No-Action';

END
$$


DROP PROCEDURE IF EXISTS sel_AdvisorBaskets;

DELIMITER $$
CREATE PROCEDURE `sel_AdvisorBaskets`(
	p_advisor VARCHAR(30)
)
BEGIN

	begin
		IF (upper(p_advisor) is null)
		then
			set p_advisor = 'Invessence';
		END IF;

				select distinct
					theme
				FROM `sec_master_group` smg
				WHERE upper(groupname) = upper(p_advisor);
    end;
END$$
DELIMITER ;

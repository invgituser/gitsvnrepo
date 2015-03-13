DROP PROCEDURE IF EXISTS `sel_AdvisorBaskets`;
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
					theme, basket, sortorder
				FROM `user_basket_access` smg
				WHERE upper(groupname) = upper(p_advisor)
				union
				select distinct
					theme, basket, sortorder
				FROM `basket_info` smg
				WHERE upper(groupname) = upper('Invessence')
				AND   upper(theme) = upper('0.core')
				order by sortorder;
			
    end;
END$$
DELIMITER ;

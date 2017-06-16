DROP PROCEDURE IF EXISTS `invdb`.`sel_advisor_web_info`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_advisor_web_info`(
	IN p_advisor			VARCHAR(60)
    )
BEGIN

  DECLARE tFound INTEGER;
  
  IF (p_advisor is null)
  THEN
	set p_advisor = 'INVESSENCE';
  END IF;

	
  select count(*)
  into tFound
  from `invdb`.`web_advisor_mapping`
  where `web_advisor_mapping`.`advisor` = upper(p_advisor);
  
  IF (IFNULL(tFound,0) = 0)
  THEN
	set p_advisor = 'INVESSENCE';
  END IF;
  
  SELECT `web_advisor_mapping`.`advisor`,
		 `web_advisor_mapping`.`name`,
		 `web_advisor_mapping`.`value`
  FROM `invdb`.`web_advisor_mapping`
  where `web_advisor_mapping`.`advisor` = upper(`p_advisor`)
  ;
  
END$$
DELIMITER ;

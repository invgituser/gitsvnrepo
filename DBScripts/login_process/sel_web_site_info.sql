USE `invdb`;
DROP procedure IF EXISTS `sel_web_site_info`;

DELIMITER $$
USE `invdb`$$
CREATE  PROCEDURE `sel_web_site_info`(
	IN p_url			VARCHAR(60)
    )
BEGIN

  DECLARE tFound INTEGER;
  
  IF (p_url is null)
  THEN
	set p_url = 'invessence';
  END IF;

  if (substr(p_url,1,9) != 'localhost')
	then
		  set p_url = replace(p_url,'www.','');
		  set tFound = locate('.',p_url);
		  IF (tFound > 0)
		  THEN
			set p_url = substr(p_url,1,tFound - 1 );
		  END IF;
		  
		  set tFound = locate(':',p_url);
		  IF (tFound > 0)
		  THEN
			set p_url = substr(p_url,1,tFound - 1 );
		  END IF;
  end if;
    
  select count(*)
  into tFound
  from `invdb`.`web_site_info`
  where `web_site_info`.`url` = lower(p_url);
  
  IF (IFNULL(tFound,0) = 0)
  THEN
	set p_url = 'invessence';
  END IF;
  
  SELECT `web_site_info`.`url`,
		 `web_site_info`.`name`,
		 `web_site_info`.`status`,
		 `web_site_info`.`value`,
		 `web_site_info`.`encrFlag`
  FROM `invdb`.`web_site_info`
  where `web_site_info`.`url` = lower(`p_url`)
  ;
    
END$$

DELIMITER ;


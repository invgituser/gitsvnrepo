DROP PROCEDURE IF EXISTS `temp`.`sp_upload_td_nav`;


DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_td_nav`()
BEGIN

    SET SQL_SAFE_UPDATES = 0;

    -- First delete all position for this business date
	DELETE FROM `invdb`.`ext_nav`
    where reportDate in (select MAX(`e2`.reportDate) from `invdb`.`ext_position` as e2)
    ;
    
   insert into `invdb`.`ext_nav` (
	  `clientAccountID`,
	  `reportDate`,
	  `cash`,
	  `stock`,
	  `funds`,
	  `interestAccrual`,
	  `dividentAccrual`,
	  `total`
    )
	SELECT 
		trim(`ext_position`.`clientAccountID`) as clientAccountID,
		`ext_position`.`reportDate`,
        SUM(case when (lower(`ext_position`.`symbol`) = 'cash') THEN `ext_position`.`positionValue`
				else 0
			end) as `cash`,
        SUM(case when (lower(`ext_position`.`symbol`) = 'cash') THEN 0
				else `ext_position`.`positionValue`
			end) as `stock`,
		0 as funds,
		0 as interestAccrual,
		0 as dividentAccrual,
		SUM(`ext_position`.`positionValue`) as total
	FROM `invdb`.`ext_position` as `ext_position`
    WHERE `ext_position`.`reportDate` = (select MAX(`e2`.reportDate) from `invdb`.`ext_position` as e2)
	group by 
	  `ext_position`.`clientAccountID`,
	  `ext_position`.`reportDate`
	;
        
	SET SQL_SAFE_UPDATES = 0;

END$$
DELIMITER ;

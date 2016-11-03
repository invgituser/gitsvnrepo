DROP PROCEDURE IF EXISTS `temp`.`sp_upload_td_nav`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_td_nav`()
BEGIN

    -- First delete all position for this business date
	DELETE FROM `invdb`.`ext_nav`
    where reportDate in (select distinct `invdb`.`funct_strdate2inv_date`(`tmp_td_unrealized`.`businessDate`, '%m/%d/%Y') 
					     from `temp`.`tmp_td_unrealized`)
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
		0 as cash,
		SUM(`ext_position`.`pnlUnrealized`) as stock,
		0 as funds,
		0 as interestAccrual,
		0 as dividentAccrual,
		SUM(`ext_position`.`pnlUnrealized`) as total
	FROM `invdb`.`ext_position` as `ext_position`
    WHERE `ext_position`.`reportDate` = (select MAX(`e2`.reportDate) from `invdb`.`ext_position` as e2)
	group by 
	  `ext_position`.`clientAccountID`,
	  `ext_position`.`reportDate`
	;
        
END$$
DELIMITER ;

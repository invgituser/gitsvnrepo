DROP PROCEDURE IF EXISTS `del_asset_alloc`;

DELIMITER $$
CREATE PROCEDURE `del_asset_alloc`(
	IN p_acctnum bigint(20),
	IN p_allocationmodel  varchar(1),
	IN p_assetyear        tinyint(3) unsigned
)
BEGIN 

   IF (p_acctnum is not NULL) then
			   DELETE FROM asset_alloc
			   WHERE acctnum = p_acctnum
			   AND   assetyear = IFNULL(p_assetyear,0);
    END IF;
END$$
DELIMITER ;

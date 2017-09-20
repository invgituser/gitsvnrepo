DROP PROCEDURE IF EXISTS `invdb`.`save_visitor`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`save_visitor`(
  INOUT `p_demoid`		bigint(20),
  IN 	`p_acctnum`		bigint(20),
  IN    `p_advisor`		varchar(20),
  IN    `p_rep`			varchar(20),
  IN	`p_ip`			varchar(50),
  IN    `p_source`      varchar(20),
  IN    `p_data`        varchar(120)
)
BEGIN

	DECLARE tfound	INT;


	IF (IFNULL(`p_acctnum`,0) = 0)
    THEN
   
		INSERT into `visitor` (
          `acctnum`,
		  `advisor`,
		  `rep`,
		  `ip`,
		  `source`,
		  `data`,
		  `created`
		)
		VALUES (
          '0', 
		  `p_advisor`,
		  `p_rep`,
		  SUBSTRING(`p_ip`,1,20),
		  `p_source`,
		  `p_data`,
		  now()
		);

		select last_insert_id() into p_demoid;
	ELSE
    
		SELECT count(*)
        INTO tfound 
        FROM `visitor`
        WHERE `acctnum` = p_acctnum;
        
        IF (IFNULL(tfound,0) = 0)
        THEN
			INSERT into `visitor` (
			  `acctnum`,
			  `advisor`,
			  `rep`,
			  `ip`,
			  `source`,
			  `data`,
			  `created`
			)
			VALUES (
			  `p_acctnum`,
			  `p_advisor`,
			  `p_rep`,
			  SUBSTRING(`p_ip`,1,20),
			  `p_source`,
			  `p_data`,
			  now()
			)
			;
			select last_insert_id() into p_demoid;
		END IF;
	END IF;
 
END$$
DELIMITER ;

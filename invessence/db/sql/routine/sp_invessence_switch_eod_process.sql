DROP PROCEDURE IF EXISTS `sp_invessence_switch_eod_process`;

DELIMITER $$
CREATE PROCEDURE `sp_invessence_switch_eod_process`(
	IN p_name varchar(20),
	IN p_value varchar(20)
)
BEGIN
	declare currentDate varchar(8);
	declare processingDate date;
	declare priorDate   varchar(8);
	declare currentMonth, priorMonth varchar(6);

	IF (p_name = 'BROKER_BDATE')
		then
		SET  currentDate = p_value;

		IF (p_value is not NULL)
		  then
			 begin
				 UPDATE  invessence_switch
				 SET 
					value = p_value,
					lastupdated = now()
				 WHERE
					name = p_name;
			 end;

			 begin
				 set processingDate = str_to_date(p_value,'%Y%m%d');
				 set processingDate = get_business_date(processingDate,1);
				 UPDATE  invessence_switch
				 SET 
					value = DATE_FORMAT(processingDate,'%Y%m%d'),
					lastupdated = now()
				 WHERE
					name = 'BUSINESS_DATE';
			 end;

			 begin
				 SELECT value
				 INTO priorDate
				 FROM invessence_switch
				 WHERE name = 'FIRST_DAY_OF_MONTH';

				 set currentMonth = substring(currentDate,1,6);
				 set priorMonth = substring(priorDate,1,6);
				 if (currentMonth != priorMonth)
					then
						begin
							set priorDate = concat(substring(currentDate,1,6),'01');
							 UPDATE  invessence_switch
							 SET 
								value = priorDate,
								lastupdated = now()
							 WHERE
								name = 'FIRST_DAY_OF_MONTH';

							 UPDATE  invessence_switch
							 SET 
								value = substr(priorDate,1,6),
								lastupdated = now()
							 WHERE
								name = 'PREVIOUS_MONTH';
						end;
				 end if;
			 end;
		END IF;
	end if;
END$$
DELIMITER ;

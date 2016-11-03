DROP PROCEDURE IF EXISTS `temp`.`sp_inv_switch_eod`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_inv_switch_eod`(
	p_date varchar(10)
)
BEGIN
	declare currentDate varchar(8);
	declare tempDate     date;
	declare businessDate varchar(8);
	declare currentDB1stDate   varchar(8);
	declare new1stDate  varchar(8);
	declare newLastBdate varchar(8);
    declare nextmonth   varchar(8);
    declare v_nextmonth varchar(8);
	declare currentMonth, priorMonth varchar(8);

	IF (p_date is not null)
		then
		SET  currentDate = p_date;

		IF (currentDate is not NULL)
		  then
			 begin
				 UPDATE  `invdb`.`invessence_switch`
				 SET 
					value = currentDate,
					lastupdated = now()
				 WHERE
					name = 'BROKER_BDATE';
			 end;

			 begin
				 set  tempDate = `invdb`.`get_business_date`(`invdb`.`funct_inv_date2date`(currentDate),1);
				 set businessDate = `invdb`.`funct_date2inv_date`(tempDate);
				 UPDATE  `invdb`.`invessence_switch`
				 SET 
					value = businessDate,
					lastupdated = now()
				 WHERE
					name = 'BUSINESS_DATE';

				 set  tempDate = `invdb`.`get_business_date`(`invdb`.`funct_inv_date2date`(businessDate),3);
				 UPDATE  `invdb`.`invessence_switch`
				 SET 
					value = `invdb`.`funct_date2inv_date`(tempDate),
					lastupdated = now()
				 WHERE
					name = 'T+3_BUSINESS_DATE';


				 set  tempDate = `invdb`.`get_business_date`(`invdb`.`funct_inv_date2date`(businessDate),-2);
				 UPDATE  `invdb`.`invessence_switch`
				 SET 
					value = `invdb`.`funct_date2inv_date`(tempDate),
					lastupdated = now()
				 WHERE
					name = 'T-2_BUSINESS_DATE';

			 end;

			 begin
				 SELECT value
				 INTO currentDB1stDate
				 FROM `invdb`.`invessence_switch`
				 WHERE name = 'FIRST_DAY_OF_MONTH';

				 set currentMonth = substring(currentDate,1,6);
				 set priorMonth = substring(currentDB1stDate,1,6);
				 if (currentMonth != priorMonth)
					then
						begin
							 UPDATE  `invdb`.`invessence_switch`
							 SET 
								value = 'Y',
								lastupdated = now()
							 WHERE
								name = 'IS_MONTH_END';

							
							set new1stDate = concat(substring(currentDate,1,6),'01');
							 UPDATE  `invdb`.`invessence_switch`
							 SET 
								value = new1stDate,
								lastupdated = now()
							 WHERE
								name = 'FIRST_DAY_OF_MONTH';

							 
							 UPDATE `invdb`.`invessence_switch`
							 SET
								value = currentDate,
								lastupdated = now()
							 WHERE
								name = '1ST_BDATE_THIS_MONTH';

							 
							 set  tempDate = `invdb`.`get_business_date`(LAST_DAY(currentDate), 1);
							 set nextmonth = `invdb`.`funct_date2inv_date`(tempDate);
							 UPDATE `invdb`.`invessence_switch`
							 SET
								value = nextmonth,
								lastupdated = now()
							 WHERE
								name = 'NEXT_MONTH_1ST_BDATE';

							 
							 
							 set  tempDate = `invdb`.`get_business_date`(`invdb`.`funct_inv_date2date`(nextmonth),-1);
							 set newLastBdate = `invdb`.`funct_date2inv_date`(tempDate);
							 UPDATE `invdb`.`invessence_switch`
							 SET
								value = newLastBdate,
								lastupdated = now()
							 WHERE
								name = 'LAST_BDATE_OF_MONTH';

							 set  tempDate = `invdb`.`get_business_date`(`invdb`.`funct_inv_date2date`(new1stDate),-1);
							 set priorMonth = `invdb`.`funct_date2inv_date`(tempDate);
							 UPDATE  `invdb`.`invessence_switch`
							 SET 
								value = substr(priorMonth,1,6),
								lastupdated = now()
							 WHERE
								name = 'PREVIOUS_MONTH';


						end;
					else
							 UPDATE  `invdb`.`invessence_switch`
							 SET 
								`value` = 'N',
								lastupdated = now()
							 WHERE
								name = 'IS_MONTH_END'
								and `value` = 'Y';
						
				 end if;
			 end;
		END IF;
	end if;
END$$
DELIMITER ;

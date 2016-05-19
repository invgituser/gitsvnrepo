DROP PROCEDURE IF EXISTS `sp_invessence_switch_eod_process`;

DELIMITER $$
CREATE PROCEDURE `sp_invessence_switch_eod_process`(
	IN p_name varchar(20),
	IN p_value varchar(20)
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

	IF (p_name = 'BROKER_BDATE')
		then
		SET  currentDate = p_value;

		IF (p_value is not NULL)
		  then
			 begin
				 UPDATE  invessence_switch
				 SET 
					value = currentDate,
					lastupdated = now()
				 WHERE
					name = p_name;
			 end;

			 begin
				 set  tempDate = get_business_date(str_to_date(currentDate,'%Y%m%d'),1);
				 set businessDate = DATE_FORMAT(tempDate,'%Y%m%d');
				 UPDATE  invessence_switch
				 SET 
					value = businessDate,
					lastupdated = now()
				 WHERE
					name = 'BUSINESS_DATE';

				 set  tempDate = get_business_date(str_to_date(businessDate,'%Y%m%d'),3);
				 UPDATE  invessence_switch
				 SET 
					value = DATE_FORMAT(tempDate,'%Y%m%d'),
					lastupdated = now()
				 WHERE
					name = 'T+3_BUSINESS_DATE';


				 set  tempDate = get_business_date(str_to_date(businessDate,'%Y%m%d'),-2);
				 UPDATE  invessence_switch
				 SET 
					value = DATE_FORMAT(tempDate,'%Y%m%d'),
					lastupdated = now()
				 WHERE
					name = 'T-2_BUSINESS_DATE';

			 end;

			 begin
				 SELECT value
				 INTO currentDB1stDate
				 FROM invessence_switch
				 WHERE name = 'FIRST_DAY_OF_MONTH';

				 set currentMonth = substring(currentDate,1,6);
				 set priorMonth = substring(currentDB1stDate,1,6);
				 if (currentMonth != priorMonth)
					then
						begin
							 UPDATE  invessence_switch
							 SET 
								value = 'Y',
								lastupdated = now()
							 WHERE
								name = 'IS_MONTH_END';

							-- This process just records the 1st of the month.  See next for valid 1st business date.
							set new1stDate = concat(substring(currentDate,1,6),'01');
							 UPDATE  invessence_switch
							 SET 
								value = new1stDate,
								lastupdated = now()
							 WHERE
								name = 'FIRST_DAY_OF_MONTH';

							 -- Since the month has shifted, we can use the broker_date as next valid business date as 1st business date.
							 UPDATE invessence_switch
							 SET
								value = currentDate,
								lastupdated = now()
							 WHERE
								name = '1ST_BDATE_THIS_MONTH';

							 -- This one attempts to find the next valid working day of next month.
							 set  tempDate = get_business_date(LAST_DAY(currentDate), 1);
							 set nextmonth = DATE_FORMAT(tempDate,'%Y%m%d');
							 UPDATE invessence_switch
							 SET
								value = nextmonth,
								lastupdated = now()
							 WHERE
								name = 'NEXT_MONTH_1ST_BDATE';

							 -- Once we know the valid business date of new month, we subtract -1 to get the last business date of prior month.
							 -- NOTE: We are subtracting, because the LAST_DAY returns the last date (regardless of business date).
							 set  tempDate = get_business_date(str_to_date(nextmonth,'%Y%m%d'),-1);
							 set newLastBdate = DATE_FORMAT(tempDate,'%Y%m%d');
							 UPDATE invessence_switch
							 SET
								value = newLastBdate,
								lastupdated = now()
							 WHERE
								name = 'LAST_BDATE_OF_MONTH';

							 set  tempDate = get_business_date(str_to_date(new1stDate,'%Y%m%d'),-1);
							 set priorMonth = DATE_FORMAT(tempDate,'%Y%m%d');
							 UPDATE  invessence_switch
							 SET 
								value = substr(priorMonth,1,6),
								lastupdated = now()
							 WHERE
								name = 'PREVIOUS_MONTH';


						end;
					else
							 UPDATE  invessence_switch
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

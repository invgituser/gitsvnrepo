DROP FUNCTION IF EXISTS `funct_calc_invessence_fee`;

DELIMITER $$
CREATE FUNCTION `funct_calc_invessence_fee`(
        p_capital     Double,  -- Starting Cash
		p_advisor	  VARCHAR(20),
		p_dateopened	 VARCHAR(10),
		p_feeDate     VARCHAR(10)
 ) RETURNS double
    DETERMINISTIC
BEGIN
		DECLARE t_fee_capital Double;
		DECLARE t_basis Double;
		DECLARE t_fix   Double;
		DECLARE t_promo_basis Double;
		DECLARE t_promo_fix   Double;
		DECLARE t_special   Integer;
		DECLARE t_dateopened Date;
		DECLARE t_daysopened Long;
		DECLARE t_daysinmonth Integer;
		DECLARE t_daysinyear Integer;
		DECLARE t_rate  DOUBLE;
		DECLARE t_promo_rate DOUBLE;
		DECLARE t_capital   DOUBLE;
		DECLARE t_balance   Double;
		DECLARE t_maxFixCapital	DOUBLE;
		DECLARE t_useFixRate BOOLEAN;

		DECLARE done_cursor INT DEFAULT FALSE;
		DECLARE fee_cursor CURSOR FOR
		SELECT
			FLOOR(`fee`.`max_capital`) as capital_fee,
			`fee`.`inv_annual_basis`,
			case when (`fee`.`inv_annual_fixed` > 0)
					then `fee`.`inv_annual_fixed` - 0.009
				else 0.0
			end as annual_fixed,
			`fee`.`special_promotioninDays`,
			`fee`.`inv_promo_basis`,
			case when (`fee`.`inv_promo_fixed` > 0)
					then `fee`.`inv_promo_fixed` - 0.009
				else 0.0
			end as promo_fixed
		FROM `fee_structure` as `fee`
		WHERE `fee`.`advisor` = IFNULL(p_advisor,'Invessence')
		AND  `fee`.`min_capital` <= p_capital
		-- AND  `fee`.`min_capital` <= p_capital
		;

		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_cursor = TRUE;
 
	BEGIN
 
		set t_balance= 0.0;

		set t_daysinmonth = funct_DaysInThisMonth(p_feeDate); -- get days in month
		set t_daysinyear = funct_DaysInThisYear(p_feeDate); -- get days in year

		-- If dateopened is null, then assume (account opened year ago)
		IF (p_dateopened is not null)
			then set t_dateopened = STR_TO_DATE(p_dateopened,'%Y%m%d');
			else set t_dateopened = DATE_ADD(DATE_FORMAT(p_feeDate,'%Y%m%d'), interval -365 day);
		END IF;

		set t_daysopened = datediff(LAST_DAY(DATE_FORMAT(p_feeDate,'%Y%m%d')),t_dateopened) + 1; -- get # of days account opened.

		SELECT max(`fee`.`max_capital`)
		into t_maxFixCapital
		FROM `fee_structure` as `fee`
		WHERE `fee`.`inv_annual_fixed` > 0;

		IF (p_capital >= 0 and p_capital <= t_maxFixCapital)
			then set t_useFixRate = true;
			else set t_useFixRate = false;
		END IF;

		-- select "Info" as txt, t_balance, t_daysinmonth, t_daysinyear, t_daysopened;
		#open cursor
		OPEN fee_cursor;

		the_loop: LOOP
			IF (p_capital <= 0) THEN
				LEAVE the_loop;
			END IF;
				
			#get the values of each column into our variables
			FETCH fee_cursor INTO
				t_fee_capital,
				t_basis,
				t_fix,
				t_special,
				t_promo_basis,
				t_promo_fix;

			IF done_cursor THEN
				LEAVE the_loop;
			END IF;

		-- select "Data" as txt, t_fee_capital, p_capital, t_basis, t_fix, t_special, t_promo_basis, t_promo_fix;
		if (p_capital <= t_fee_capital)
			then
				 set t_capital = p_capital;
			else set t_capital = t_fee_capital;
	    end if;

		if (t_basis > 0 and (! t_useFixRate))
			then set t_rate = t_basis/10000;
				 set t_rate = t_rate/t_daysinyear;
				 set t_rate = t_capital * t_rate;
			else 
				if (t_capital > 0 and t_useFixRate)
					then set t_rate = t_fix/t_daysinyear;
					else set t_rate = 0;
				end if;
		end if;
		if (t_promo_basis > 0 and (! t_useFixRate))
			then set t_promo_rate = t_promo_basis/10000;
				 set t_promo_rate = t_promo_rate/t_daysinyear;
				 set t_promo_rate = t_capital * t_promo_rate;
			else 
				if (t_capital > 0 and t_useFixRate)
					then set t_promo_rate = t_promo_fix/t_daysinyear;
					else set t_promo_rate = 0;
				end if;

		end if;

			-- select "Calc Rate" as txt, t_rate, t_promo_rate;
		    -- Is there special promotion
			if (t_special is not null and t_special > 0)
				then 

					-- If this is special promotion, then give discount.
					if (t_daysopened - t_special < 0)
					then
						-- select "PROMO fee (New)" as txt, t_balance as current, t_promo_rate as rate, t_daysopened as days, (t_promo_rate * t_daysopened) as fee;
						set t_balance = t_balance + (t_promo_rate * t_daysopened);
						LEAVE the_loop;
					end if;

					set t_daysopened = t_daysopened - t_special;
					if (t_daysopened < t_daysinmonth)
					then
						-- select "PROMO fee (Carry)" as txt, t_balance as current, t_promo_rate as rate, t_daysopened as days, (t_promo_rate * t_daysopened) as fee;
						set t_balance = t_balance + (t_promo_rate * t_daysopened);
						set t_daysinmonth = t_daysinmonth - t_daysopened;
						set t_daysopened = 100;
					end if;

					
			end if;

			if (t_daysopened < t_daysinmonth)
			then
				set t_daysinmonth = t_daysopened;
			end if;


			-- select "Adding Fee to balance" as txt, t_balance as current, t_rate as rate, t_daysinmonth as days, (t_rate * t_daysinmonth) as fee;
			set t_balance = t_balance + (t_rate * t_daysinmonth);
			if (t_rate > 0)
				then
					set p_capital = p_capital - t_capital;
			end if;
			-- select "Calc " as txt, t_rate, t_promo_rate, t_balance;

		END LOOP the_loop;

		CLOSE fee_cursor;

		-- select "Final Fee" as txt, t_balance;
		IF (t_balance > 0)
			THEN RETURN ROUND((t_balance-.005),2);
		ELSE
			RETURN 0.00;
		END IF;

	END;
END$$
DELIMITER ;

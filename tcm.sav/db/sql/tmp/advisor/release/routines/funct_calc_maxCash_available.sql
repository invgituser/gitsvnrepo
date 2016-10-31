DROP FUNCTION IF EXISTS `funct_calc_maxCash_available`;

delimiter $$
CREATE FUNCTION `funct_calc_maxCash_available`(
        p_start       Double,  -- Starting Cash
		p_spend		  Double,  -- Negative represents spend, positive represents buy
		p_keepLiquid  Double
) RETURNS Double
    DETERMINISTIC
BEGIN
	DECLARE tFee DOUBLE;
	DECLARE tCashAvail DOUBLE;

	BEGIN
		SET tFee = funct_get_switch('MIN_FEE');

		-- Keep the greater of two, KeepLiquid > fee, then set fee to Liquid.
		IF (p_keepLiquid > tFee)
			then set tFee = p_keepLiquid;
		end if;

		-- If our starting cash < required, then return amount needed.
		if (p_start < tFee)
			then
				return (tFee - p_start);
		end if;

		-- Sell side...
		-- Subtract Total Cash Avail after spending.
		IF (p_spend < 0) then
			SET tCashAvail = p_start + p_spend;
			
			IF (tCashAvail >= tFee) then
				return p_spend; -- We can use the amount as specified in spend.
			else
				if (abs(p_spend) > tFee)
					then
						return tFee + p_spend;
				end if;
			end if;
		end if;

		-- Buy side..
		if (p_spend > 0)
			then return p_spend;
		end if;

	END;
END$$


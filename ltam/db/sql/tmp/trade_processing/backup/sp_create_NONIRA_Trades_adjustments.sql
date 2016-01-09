DROP PROCEDURE IF Exists `sp_create_NONIRA_Trades_adjustments`;

DELIMITER $$
CREATE PROCEDURE `sp_create_NONIRA_Trades_adjustments`(
	p_acctnum BIGINT(20),
	p_customerType VARCHAR(40))

BEGIN
	DECLARE vCustomerType varchar(40);
	DECLARE vAcctnum BIGINT(20);
	DECLARE vticker  VARCHAR(20);
	DECLARE vGainLoss double;
	DECLARE vTradeQty integer;
	DECLARE vTradePrice double;
	DECLARE vPricePerShare double;
	DECLARE vTotalGain double;
	DECLARE vTotalLoss double;
	DECLARE vNumofShares Integer;
	DECLARE vRemaining double;
	DECLARE done INT DEFAULT FALSE;


	declare cur cursor for 
		select `acctnum`,
			   case when (`gainloss` < 0) then 0
					when ((`gainloss` >=0) and (`tradeqty` < 0)) then 1
					when ((`gainloss` >=0) and (`tradeqty` > 0)) then 2
					else 99
				end as sortOrder,
			  `tradeqty`,
			   `ticker`,
			  `gainloss`,
			  `tradeprice`,
			  `priceperShare`
		from `pretrade_details`
		where upper(`ticker`) not in ('CASH')
		and `acctnum` = p_acctnum
		order by 1,2,3 desc
		;

	#declare handle 
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	IF (p_customerType is null) THEN
			begin
				SELECT `acctType`
				INTO vCustomerType
				FROM `user_trade_profile`
				WHERE acctnum = p_acctnum;
			end;
		else
			set vCustomerType = p_customerType;
	END IF;

	IF (instr(upper(vCustomerType),'IRA') = 0)
		then
			begin
			-- 1) Get the number for total gains and total loss.
			select abs(sum(case when `gainloss` > 0 then `gainloss`
						    else 0
					   end)) as gain,
					abs(sum(case when `gainloss` < 0 then `gainloss`
						    else 0
					   end)) as loss
			into vTotalGain, vTotalLoss
		    from `pretrade_details`
			where `acctnum` = p_acctnum
			and `tradeqty` < 0
			;
			-- 2) If the loss < gains, then adjust appropriately on highest security to lower.
			if (vTotalLoss > vTotalGain)
				then
					-- If loss is greater then gain, them create the entire portfolio as is.
					update `pretrade_details`
						set `adjustedQty` = `tradeqty`
					where `acctnum` = p_acctnum;
				else
					begin
						
						  OPEN cur;
						 
						  #starts the loop
						  the_loop: LOOP
						 
							#get the values of each column into our variables
							FETCH cur INTO 	
									vAcctnum,
									vticker,
									vGainLoss,
									vTradeQty,
									vTradePrice,
									vPricePerShare;
							
							IF done THEN
							  LEAVE the_loop;
							END IF;
							
							-- If remaining is less then 1 then don't do further allocation.
							if (vTotalLoss <= 0.011) then
								-- If loss is less then 10th of the cents, then leave.  Don't bother to find another security to distribute.
								LEAVE the_loop;
							end if;

							if (vGainLoss < 0 ) 
								then
										set vNumofShares = round(vTradeQty,0);
								else
									if (vTotalLoss >= vGainLoss)
									  then
										set vNumofShares = round(vTradeQty,0);
									  else
										-- Calculate, but reduce by 1/2 share so that we are using lower bound, instead of upper bound
										set vNumofShares = round(-1 * ((vTotalLoss/vPricePerShare)-.5),0);
									end if;
									set vTotalLoss = vTotalLoss - (abs(vNumofShares)*vPricePerShare);
							end if;

							update `pretrade_details`
								set adjustedQty = vNumofShares
								,   `tradeamount` = vNumofShares * vTradePrice
							where acctnum = vAcctnum
							and   ticker = vticker
							and   tradeqty = vTradeQty 
							;

						  END LOOP the_loop;
						 
						  CLOSE cur;

					end;
				update `pretrade_details`
					set `adjustedQty` = case when (`tradeqty` < 0) then 0
										   when (`tradeqty` > 0) then `tradeqty`
										   else 0
									  end
			   where acctnum = p_acctnum
			   and adjustedQty is null;
			end if;
			end;
 	END IF;
end;
$$



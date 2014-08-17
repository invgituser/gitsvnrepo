DROP PROCEDURE IF Exists `sp_create_Trades_adjustments`;

DELIMITER $$
CREATE PROCEDURE `sp_create_Trades_adjustments`(
	p_acctnum BIGINT(20),
	p_customerType VARCHAR(40))

BEGIN
	DECLARE vCustomerType varchar(40);
	DECLARE vAcctnum BIGINT(20);
	DECLARE vSortOrder INTEGER;
	DECLARE vSortOrder2 INTEGER;
	DECLARE vTradeQty integer;
	DECLARE vticker  VARCHAR(20);
	DECLARE vGainLoss double;
	DECLARE vTradePrice double;
	DECLARE vPricePerShare double;
	DECLARE vTotalGain double;
	DECLARE vTotalLoss double;
	DECLARE vNumofShares Integer;
	DECLARE vAmount double;
	DECLARE vStartingCash double;
	DECLARE vCash double;
	DECLARE vRemainingAmount double;
	DECLARE vKeepLiquid double;
	DECLARE done INT DEFAULT FALSE;
	DECLARE vrownum INTEGER DEFAULT 0;


	declare cur cursor for 
		select pd.`acctnum`,
			   case when (pd.`gainloss` < 0) then 1
					when ((pd.`gainloss` >=0) and (pd.`tradeqty` < 0)) then 2
					when ((pd.`gainloss` >=0) and (pd.`tradeqty` > 0)) then 3
					else 99
				end as sortOrder,
			  IFNULL(pd.`percentAllocated`,0) as sortOrder2,
			  pd.`tradeqty`,
			   pd.`ticker`,
			  pd.`gainloss`,
			  pd.`tradeprice`,
			  pd.`priceperShare`
		from `pretrade_details` pd
		where upper(`ticker`) not in ('CASH')
		and `acctnum` = p_acctnum
		order by 1,2,3 desc
		;

	#declare handle 
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Determine the type of account.  If none passed, then find out by looking at DB.
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

	-- Get Actual available Cash
	SELECT IFNULL(nav.`cash`,0.0) as cash
	INTO vStartingCash
	FROM `nav_daily` nav,
		 `IB_Accounts` ib
	WHERE ib.`acctnum` = p_acctnum
	AND   ib.IB_acctnum = nav.clientAccountID
	AND   nav.reportDate = funct_get_switch('BROKER_BDATE');

	SELECT SUM(IFNULL(pd.`tradeqty`,0)) as cash,
		   IFNULL(utf.`keepLiquid`,0) as keepLiquid
	INTO vCash, vKeepLiquid
	from `pretrade_details` pd, user_trade_profile utf
	where upper(`ticker`) in ('CASH')
	and pd.`acctnum` = p_acctnum
	and pd.`acctnum` = utf.`acctnum`
	;

	SET vRemainingAmount = funct_calc_maxCash_available(vStartingCash, vCash, vKeepLiquid);

   -- We need the logic to build freeing up cash...
	IF (vRemainingAmount > 0)
		THEN SET vRemainingAmount = 0.0;
	END IF;

	update `pretrade_details`
		set `sortOrder` = 0
		,   `runningCashAmt` = vStartingCash
		,   adjustedQty =  round(vRemainingAmount,0)
		,   `tradeamount` =  vRemainingAmount 
	where acctnum = p_acctnum
	and   upper(ticker) = 'CASH'
	; 	

	SET vRemainingAmount = abs(vRemainingAmount);
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

			-- 2) Loop for each one...
			  OPEN cur;
			 
			  #starts the loop
			  the_loop: LOOP
			 
				#get the values of each column into our variables
				FETCH cur INTO 	
						vAcctnum,
						vSortOrder,vSortOrder2,
						vTradeQty,
						vticker,
						vGainLoss,
						vTradePrice,
						vPricePerShare;
				
				IF done THEN
				  LEAVE the_loop;
				END IF;

				SET vrownum = vrownum + 1;
				SET vNumofShares = 0;
				-- Is this SELL
				IF (vTradeQty < 0)
					THEN
						-- IF it is IRA, then don't worry about sell (Gain/Loss)
						IF (instr(upper(vCustomerType),'IRA') > 0)
							THEN
								set vNumofShares = round(vTradeQty,0);
							ELSE
							-- In the case of Taxable account, we need to make sure that we sell only
							-- those that do not have gain.
								if (vGainLoss < 0 ) 
									then -- 1) Sell all loss...
											set vNumofShares = round(vTradeQty,0);
									else -- 2) For gains, determine the value of gain.
										if (vTotalLoss >= vGainLoss)
										  then -- 2a) Entire Gain can be offset.
											set vNumofShares = round(vTradeQty,0);
										  else -- 2a) Only Partial Gain can be offset.
											-- Calculate, but reduce by 1/2 share so that we are using lower bound, instead of upper bound
											set vNumofShares = round(-1 * ((vTotalLoss/vPricePerShare)-.5),0);
										end if;
										set vTotalLoss = vTotalLoss - (abs(vNumofShares)*vPricePerShare);
								end if;
						END IF;
				END IF;
				-- If it is a BUY, then check available amount.
				IF (vTradeQty > 0)
					then
						set vNumofShares = round(vTradeQty,0);
						SET vAmount = vNumofShares * vTradePrice;
						-- There is not enough money to buy?
						IF (vRemainingAmount < vAmount)
							then
								set vNumofShares = round((vRemainingAmount/vTradePrice),0);
						END IF;
				END IF;

				-- Based on above logic, we determined the amount we can Buy/Sell.
				SET vAmount = vNumofShares * vTradePrice;
				SET vRemainingAmount = vRemainingAmount - vAmount;		

				-- Now make adjustments.
				update `pretrade_details`
					set adjustedQty = vNumofShares
					,   `tradeamount` = vAmount
					,   `sortOrder` = vrownum
					,   `runningCashAmt` = vRemainingAmount
				where acctnum = vAcctnum
				and   ticker = vticker
				and   tradeqty = vTradeQty 
				;

			  END LOOP the_loop;
			  CLOSE cur;
			  
		end;
 end;
$$



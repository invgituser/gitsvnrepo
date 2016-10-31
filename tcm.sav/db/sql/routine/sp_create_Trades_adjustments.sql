DROP PROCEDURE IF EXISTS `sp_create_Trades_adjustments`;
DELIMITER $$
CREATE PROCEDURE `sp_create_Trades_adjustments`(
	p_acctnum BIGINT(20),
	p_customerType VARCHAR(40))
BEGIN
	DECLARE vCustomerType varchar(40);
	DECLARE vAcctnum BIGINT(20);
	DECLARE vSortOrder INTEGER;
	DECLARE vSortOrder2 INTEGER;
	DECLARE vSortOrder3 INTEGER;
	DECLARE vTradeQty integer;
	DECLARE vticker  VARCHAR(20);
	DECLARE vThisRealizedPnL double;
	DECLARE vTradePrice double;
	DECLARE vTradeValue double;
	DECLARE vPricePerShare double;
	DECLARE vThisTotalPnL double;
	DECLARE vTotalLoss double;
	DECLARE vNumofShares Integer;
	DECLARE vAmount double;
	DECLARE vStartingCash double;
	DECLARE vCash double;
	DECLARE vRemainingAmount double;
	DECLARE vtradedPnL double;
	DECLARE vKeepLiquid double;
	DECLARE done INT DEFAULT FALSE;
	DECLARE vrownum INTEGER DEFAULT 0;


    -- Create this list:1)All Sales 2) All Sales (Even though the P&L) is positive, 3) All Buys
	-- Second sort order (Largest movement (tradeqty) and then largest Price.
	declare cur cursor for 
		select pd.`acctnum`,
			   case when (pd.`realizedPnL` < 0) then 1
					when ((pd.`realizedPnL` >=0) and (pd.`tradeqty` < 0)) then 2
					when ((pd.`realizedPnL` >=0) and (pd.`tradeqty` > 0)) then 3
					else 99
				end as sortOrder,
			  abs(IFNULL(pd.`tradeValue`,0)) as sortOrder2,
			  IFNULL(pd.`tradeqty`,0) as sortOrder3,
			  pd.`tradeqty`,
			  pd.`ticker`,
			  pd.`realizedPnL`,
			  pd.`tradeprice`,
			  pd.`tradeValue`,
			  pd.`priceperShare`
		from `pretrade_details` pd
		where upper(`ticker`) not in ('CASH')
		and `acctnum` = p_acctnum
		order by 1 asc,2 asc,3 desc, 4 desc
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

	-- If the word is Non-taxable, then make it IRA.
	select case 
			when (instr(upper(vCustomerType),'NON')>0) THEN 'IRA' -- 'Non Taxable'
			when (instr(upper(vCustomerType),'IRA')>0) THEN 'IRA' -- IRA/ROTH IRA
			when (instr(upper(vCustomerType),'SEP')>0) THEN 'IRA' -- SEP
			when (instr(upper(vCustomerType),'40')>0) THEN 'IRA'  -- Any type (401, 402, etc.)
			else 'XXX'
			end
	into vCustomerType;
	
	-- Get Actual available Cash
	SELECT IFNULL(nav.`cash`,0.0) as cash
	INTO vStartingCash
	FROM `nav_daily` nav,
		 `IB_Accounts` ib
	WHERE ib.`acctnum` = p_acctnum
	AND   ib.IB_acctnum = nav.clientAccountID
	AND   nav.reportDate = funct_get_switch('BROKER_BDATE');

	SELECT SUM(IFNULL(pd.`newqty`,0)) as cash, SUM(abs(IFNULL(pd.`tradeValue`,0)))
	INTO vCash, vRemainingAmount
	from `pretrade_details` pd
	where upper(`ticker`) in ('CASH')
	and pd.`acctnum` = p_acctnum
	;

	-- SET vRemainingAmount = funct_CashBalance(vStartingCash, vCash);

   -- We need the logic to build freeing up cash...

	update `pretrade_details`
		set `sortOrder` = 0
		,   `runningCashBal` = vRemainingAmount
	where acctnum = p_acctnum
	and   upper(ticker) = 'CASH'
	; 	

		begin

			set vTotalLoss = 0;
			-- 1) Loop for each one...
			  OPEN cur;
			 
			  #starts the loop
			  the_loop: LOOP
			 
				#get the values of each column into our variables
				FETCH cur INTO 	
						vAcctnum,
						vSortOrder,vSortOrder2,vSortOrder3,
						vTradeQty,
						vticker,
						vThisRealizedPnL,
						vTradePrice,
						vTradeValue,
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
						IF (vCustomerType = 'IRA')
							THEN
								set vNumofShares = vTradeQty;
								set vThisTotalPnL = vThisRealizedPnL;
								set vTotalLoss = vTotalLoss - vThisTotalPnL;
							ELSE
							-- In the case of Taxable account, we need to make sure that we sell only
							-- those that do not have gain.
								if (vThisRealizedPnL < 0 ) 
									then -- 1) Sell all loss (regardless)...
										set vNumofShares = vTradeQty;
										set vThisTotalPnL = vThisRealizedPnL;
									else
										-- Calc. affordable shares. NOTE: Since, realizedPnL is > 0, then it is a gain and Price/Share is also > 0
										IF (vThisRealizedPnL < abs(vTotalLoss))
											then 
												set vNumofShares = vTradeQty;
												set vThisTotalPnL = vThisRealizedPnL;
											else
												if (vPricePerShare > 0)
													then 
														set vNumofShares = truncate(vTotalLoss/vPricePerShare,0) * -1;
														set vThisTotalPnL = abs(vNumofShares*vPricePerShare);
													else
														set vNumofShares = 0;
														set vThisTotalPnL = abs(vNumofShares*vPricePerShare);
												end if;
									end if;
								end if;
								set vTotalLoss = vTotalLoss + vThisTotalPnL;
								SET vtradedPnL = vThisTotalPnL;
						END IF;
						-- if TotalP&L > 0, then we now have gain, something went wrong.
-- select vTradeQty, vNumofShares, vTotalLoss, vPricePerShare, vThisTotalPnL, vtradedPnL;
						if (vTotalLoss > 0)
							then set vNumofShares = 0;  -- set NUM of shares to 0
							else
								if (abs(vNumofShares) > abs(vTradeQty))
									then set vNumofShares = vTradeQty;
								end if;
								IF (vNumofShares > 0)  -- If Num >0, then error, we are on buy side.  Revert to zero.
									then set vNumofShares = 0;
							END IF;
						END IF;
				END IF;
				-- If it is a BUY, then check available amount.
				IF (vCustomerType = 'IRA')
					THEN
						set vThisRealizedPnL = vTradeQty * vTradePrice;
						IF (vRemainingAmount < vThisRealizedPnL)
							THEN set vNumofShares = floor(vRemainingAmount/vTradePrice);
							ELSE set vNumofShares = floor(vTradeQty);
						END IF;
						 SET vtradedPnL = 0.0;
				else
					IF (vTradeQty > 0)
						then
							if (vRemainingAmount > vTradeValue)
								then  set vNumofShares = floor(vTradeQty);
								else  if (vRemainingAmount > 0)
										then set vNumofShares = floor(vRemainingAmount/vTradePrice);
										else set vNumofShares = 0;
									  end if;
							end if;
						 SET vtradedPnL = 0.0;

					END IF;
				END IF;


				-- Based on above logic, we determined the amount we can Buy/Sell.
				SET vAmount = vNumofShares * vTradePrice;
				SET vRemainingAmount = vRemainingAmount - vAmount;		

				-- Now make adjustments.
				update `pretrade_details`
					set adjustedQty = vNumofShares
					,   `tradeValue` = vAmount
					,   `sortOrder` = vrownum
					,   `tradedPnL` = vtradedPnL
					,   `runningCashBal` = vRemainingAmount
				where acctnum = vAcctnum
				and   ticker = vticker
				and   tradeqty = vTradeQty 
				;

			  END LOOP the_loop;
			  CLOSE cur;
			  
		end;
 end$$
DELIMITER ;

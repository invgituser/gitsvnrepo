DROP PROCEDURE IF EXISTS `sp_Cash_Position_addmod`;

DELIMITER $$
CREATE PROCEDURE `sp_Cash_Position_addmod`(
	IN p_addmod	   varchar(1),
	IN p_clientAccountID	VARCHAR(12),
	IN p_accountAlias	VARCHAR(20),
	IN p_currencyPrimary	VARCHAR(20),
	IN p_fromDate	VARCHAR(10),
	IN p_toDate	VARCHAR(10),
	IN p_startingCash	Double,
	IN p_endingCash	Double
)
BEGIN
	DECLARE t_found     INT;

	#If this sp was called and account is in Verify mode, then delete the mapping. 
			delete from `IB_Cash_Info`
		    where `clientAccountID` = p_clientAccountID
			and   `fromDate` = replace(p_fromDate,'-','')
			and   `toDate` = replace(p_toDate,'-','')
			;

			begin
				INSERT INTO `IB_Cash_Info`
				(`clientAccountID`,
				 `accountAlias`,
				 `currencyPrimary`,
				 `fromDate`,
				 `toDate`,
				 `startingCash`,
				 `endingCash`
				)
				VALUES
				(
				Substring(p_clientAccountID,1,12),
				Substring(p_accountAlias,1,20),
				Substring(p_currencyPrimary,1,20),
				replace(p_fromDate,'-',''),
				replace(p_toDate,'-',''),
				p_startingCash,
				p_endingCash
				);
			end;

			begin
				DELETE FROM position
				WHERE `clientAccountID` = p_clientAccountID
				AND   `reportDate` = replace(p_toDate,'-','')
				AND   `symbol` ='Cash'
				;
			end;

			begin
				INSERT INTO `position`
				(`clientAccountID`,
				`accountAlias`,
				`currencyPrimary`,
				`assetClass`,
				`fxRateToBase`,
				`symbol`,
				`description`,
				`reportDate`,
				`side`,
				`quantity`,
				`costBasisPrice`,
				`CostBasisMoney`,
				`markPrice`,
				`positionValue`,
				`fifoPnlUnrealized`,
				`LevelOfDetail`)
				VALUES
				(
				Substring(p_clientAccountID,1,12),
				Substring(p_accountAlias,1,20),
				Substring(p_currencyPrimary,1,20),
				'Cash',
				1,
				'Cash',
				'Money Market',
				replace(p_toDate,'-',''),
			    CASE 
				  WHEN (p_startingCash < 0) THEN 'Short'
				  ELSE 'Long'
                END,
				p_startingCash,
				1,
				p_startingCash,
				1,
				p_startingCash,
				0,
				'SUMMARY'
				);	
			end;

			IF (p_startingCash > 0.0)
				then
				begin
					UPDATE IB_Accounts
						SET accountStatus = 'Funded'
					WHERE `IB_acctnum` = p_clientAccountID
					AND accountStatus in ('Verify', 'Pending')
					;
				end;
			END IF;
END;
$$

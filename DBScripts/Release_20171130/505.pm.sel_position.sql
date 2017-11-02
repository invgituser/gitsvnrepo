DROP PROCEDURE IF EXISTS `invdb`.`sel_position`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_position`(
 	IN p_logonid  bigint(20),
 	IN p_acctnum  bigint(20) ,
    IN p_advisor VARCHAR(20),
    IN p_rep VARCHAR(20)
)
BEGIN

	DECLARE tAdvisor VARCHAR(25);
    DECLARE tfilterType   VARCHAR(1);
    
    DECLARE tTotalPos	 DOUBLE;
	DECLARE tRep VARCHAR(25);
    DECLARE vAdvisor VARCHAR(25);

    select advisor into vAdvisor  from user_logon where logonid=p_logonid;
    if(vAdvisor='DEMO') then
			 set tAdvisor = IFNULL(p_advisor,'%');
             set tRep = IFNULL(p_rep,'%');
    else
		SELECT 
		  advisor,
		  rep
		INTO tAdvisor, tRep FROM
		user_advisor_access
		WHERE logonid = p_logonid;
    
    end if;
        
	IF (tAdvisor is NOT NULL)
		THEN set tfilterType = 'A';
		ELSE set tfilterType = 'O';  
	END IF;

			select SUM(`ext_position`.`positionValue`)
            INTO `tTotalPos`
			FROM `ext_position`
			WHERE `ext_position`.`acctnum` = p_acctnum
			AND   `ext_position`.`reportDate` = ( select max(`pos`.`reportDate`) from `ext_position` `pos`)
			;
            
            IF (`tTotalPos` = 0)
             THEN set `tTotalPos` = 1;
			END IF;

	IF (tfilterType = 'O')
		THEN
			select
				 IFNULL(`sec_asset_mapping`.`assetsortorder`,9999) * 1000 +
                 IFNULL(`sec_asset_mapping`.`subclasssortorder`,9999) as `sortorder`
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
                ,`ext_position`.`tradeCurrency`
				,SUM(`ext_position`.`quantity`) as `quantity`
				,AVG(`ext_position`.`costBasisPrice`) `costBasisPrice`
				,SUM(`ext_position`.`costBasisMoney`) as `costBasisMoney`
				,AVG(`ext_position`.`markPrice`) `markPrice`
				,SUM(`ext_position`.`positionValue`) as `positionValue`
                ,SUM(`ext_position`.`pnlUnrealized`) as `fifoPnlUnrealized`
                ,SUM(`ext_position`.`positionValue`/`tTotalPos`) as `weight`
				, `ext_acct_info`.`acctnum`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_trade_profile`.`firstname`) as `firstname`
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_trade_profile`.`lastname`) as `lastname`
                , CONCAT(IFNULL(`ext_acct_info`.`applicantFName`,`user_trade_profile`.`firstname`),' ',
                 		 IFNULL(`ext_acct_info`.`applicantLName`,`user_trade_profile`.`lastname`)
                         ) as `fullname`  --  , `ext_acct_info`.`applicantFullName` as `fullname`
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name` as `description`
				,IFNULL(`sec_asset_mapping`.`assetclass`,`sec_master`.`assetclass`) as `assetclass`
				,IFNULL(`sec_asset_mapping`.`subclass`,`sec_master`.`subclass`) as `subclass`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`) as `assetName`
				,IFNULL(`sec_asset_mapping`.`subclassName`,`sec_master`.`subclass`) as `subclassName`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff') as `color`
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName` as `accountAlias`
                ,`ext_position`.`settleCurrency`
				,SUM(`ext_position`.`settleQty`) as `settleQty`
				,AVG(`ext_position`.`settlePrice`) `settlePrice`
				,SUM(`ext_position`.`settleQty` * `ext_position`.`settlePrice`) as `settleCostBasisMoney`
				,AVG(`ext_position`.`settlementMarkPrice`) `settlementMarkPrice`
				,SUM(`ext_position`.`settleMoney`) as `settleMoney`
                ,SUM(`ext_position`.`settlePnL`) as `settlePnL`
			FROM `ext_position`
				 INNER JOIN `ext_acct_info`
				 ON (`ext_position`.`clientAccountID` = `ext_acct_info`.`clientAccountID`)
				 INNER JOIN `user_trade_profile`
				 ON (`ext_acct_info`.`acctnum` = `user_trade_profile`.`acctnum`)
				 INNER JOIN `user_access_role`
				 ON (`ext_acct_info`.`acctnum` = `user_access_role`.`acctnum`
				 AND  `user_access_role`.`role` in ('OWNER', 'USER'))
				 INNER JOIN `user_logon`
				 ON (`user_access_role`.`logonid` = `user_logon`.`logonid`)
				 LEFT JOIN `sec_master`
				 ON (`ext_position`.`symbol`= `sec_master`.`ticker`)
				 LEFT JOIN `sec_asset_mapping`
				 ON (`sec_master`.`ticker`= `sec_asset_mapping`.`ticker`
				 AND `sec_asset_mapping`.`theme` = `user_trade_profile`.`theme`)
			WHERE `ext_acct_info`.`acctnum` = p_acctnum
			AND   `user_logon`.`logonid`    = p_logonid
			AND   `ext_position`.`reportDate` = ( select max(`pos`.`reportDate`) from `ext_position` `pos`)
            GROUP BY
                 `sec_asset_mapping`.`assetsortorder`
                ,`sec_asset_mapping`.`subclasssortorder`
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
				, `ext_acct_info`.`acctnum`
                ,`ext_position`.`tradeCurrency`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_trade_profile`.`firstname`)
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_trade_profile`.`lastname`)
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,`sec_master`.`subclass`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff')
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName`
                ,`ext_position`.`settleCurrency`
 			ORDER BY 1, `ext_position`.`symbol`
			;
        ELSE
			select
				 IFNULL(`sec_asset_mapping`.`assetsortorder`,9999) * 1000 +
                 IFNULL(`sec_asset_mapping`.`subclasssortorder`,9999) as `sortorder`
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
                ,`ext_position`.`tradeCurrency`
				,SUM(`ext_position`.`quantity`) as `quantity`
				,AVG(`ext_position`.`costBasisPrice`) `costBasisPrice`
				,SUM(`ext_position`.`costBasisMoney`) as `costBasisMoney`
				,AVG(`ext_position`.`markPrice`) `markPrice`
				,SUM(`ext_position`.`positionValue`) as `positionValue`
                ,SUM(`ext_position`.`pnlUnrealized`) as `fifoPnlUnrealized`
                ,SUM(`ext_position`.`positionValue`/`tTotalPos`) as `weight`
				, `ext_acct_info`.`acctnum`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_trade_profile`.`firstname`) as `firstname`
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_trade_profile`.`lastname`) as `lastname`
                , CONCAT(IFNULL(`ext_acct_info`.`applicantFName`,`user_trade_profile`.`firstname`),' ',
                 		 IFNULL(`ext_acct_info`.`applicantLName`,`user_trade_profile`.`lastname`)
                         ) as `fullname`  --  , `ext_acct_info`.`applicantFullName` as `fullname`
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name` as `description`
				,IFNULL(`sec_asset_mapping`.`assetclass`,`sec_master`.`assetclass`) as `assetclass`
				,IFNULL(`sec_asset_mapping`.`subclass`,`sec_master`.`subclass`) as `subclass`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`) as `assetName`
				,IFNULL(`sec_asset_mapping`.`subclassName`,`sec_master`.`subclass`) as `subclassName`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff') as `color`
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName` as `accountAlias`
                ,`ext_position`.`settleCurrency`
				,SUM(`ext_position`.`settleQty`) as `settleQty`
				,AVG(`ext_position`.`settlePrice`) `settlePrice`
				,SUM(`ext_position`.`settleQty` * `ext_position`.`settlePrice`) as `settleCostBasisMoney`
				,AVG(`ext_position`.`settlementMarkPrice`) `settlementMarkPrice`
				,SUM(`ext_position`.`settleMoney`) as `settleMoney`
                ,SUM(`ext_position`.`settlePnL`) as `settlePnL`
		FROM `ext_position`
				 INNER JOIN `ext_acct_info`
				 ON (`ext_position`.`clientAccountID` = `ext_acct_info`.`clientAccountID`)
				 INNER JOIN `user_trade_profile`
				 ON (`ext_acct_info`.`acctnum` = `user_trade_profile`.`acctnum`)				 
				 LEFT JOIN `sec_master`
				 ON (`ext_position`.`symbol`= `sec_master`.`ticker`)
				 LEFT JOIN `sec_asset_mapping`
				 ON (`sec_master`.`ticker`= `sec_asset_mapping`.`ticker`
				 AND `sec_asset_mapping`.`theme` = `user_trade_profile`.`theme`)
			WHERE `ext_acct_info`.`acctnum` = p_acctnum
			AND   IFNULL(`user_trade_profile`.`advisor`,'Invessence') like tAdvisor
            AND   IFNULL(`user_trade_profile`.rep, 'CATCALL') LIKE tRep
			AND   `ext_position`.`reportDate` = ( select max(`pos`.`reportDate`) from `ext_position` `pos`)
            GROUP BY
                 `sec_asset_mapping`.`assetsortorder`
                ,`sec_asset_mapping`.`subclasssortorder`
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
				, `ext_acct_info`.`acctnum`
                ,`ext_position`.`tradeCurrency`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_trade_profile`.`firstname`)
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_trade_profile`.`lastname`)
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,`sec_master`.`subclass`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff')
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName`
                ,`ext_position`.`settleCurrency`
                ,`sec_asset_mapping`.`assetsortorder`
                ,`sec_asset_mapping`.`subclasssortorder`
			ORDER BY 1, `ext_position`.`symbol`
			;
	END IF;



END$$
DELIMITER ;

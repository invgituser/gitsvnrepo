DROP PROCEDURE IF EXISTS invdb.`save_tddc_requests`;

DELIMITER $$
CREATE PROCEDURE invdb.`save_tddc_requests`(
  INOUT `p_reqId`			bigint(20),
  IN 	`p_acctnum`			bigint(20),
  INOUT `p_eventNum`		integer,
  IN	`p_reqType`			varchar(45),
  IN	`p_envelopeHeading`	varchar(100),
  IN	`p_envelopeId`		varchar(45),
  IN	`p_status` 			varchar(45),
  IN	`p_terminalDetails`	varchar(100),
  IN	`p_requestFor`	varchar(100)
)
BEGIN
  DECLARE tfound Integer;
  DECLARE tnextEvent Integer;
  DECLARE treqId Integer;

SELECT count(*)
  INTO treqId from 
 dc_requests where acctnum=`p_acctnum` and  reqType=`p_reqType` and dc_requestFor=`p_requestFor`;
  -- Every request is new one.
  
  if (p_reqId !=0)
  then
	update `dc_requests`
    set status = 'X'
    where `reqId` = `p_reqId`
    ;
  end if;
  
  if(`p_requestFor`="ACH") then
	  update `dc_requests`
		set status = 'X'
		where dc_requestFor ="ACAT" and acctnum=`p_acctnum`;
       
       update `dc_requests`
		set status = 'X'
		where dc_requestFor ="TDTRF" and acctnum=`p_acctnum`;
       
       update `dc_requests`
		set status = 'I'
		where dc_requestFor ="ACH" and acctnum=`p_acctnum`;
        
   else if(`p_requestFor`="ACAT") then
     update `dc_requests`
		set status = 'X'
		where dc_requestFor ="ACH" and acctnum=`p_acctnum`;
         
         update `dc_requests`
		set status = 'X'
		where dc_requestFor ="TDTRF" and acctnum=`p_acctnum`;
		
        update `dc_requests`
				set status = 'X'
				where dc_requestFor ="ACAT" and acctnum=`p_acctnum` and reqType <>`p_reqType`;
       
       update `dc_requests`
				set status = 'I'
				where dc_requestFor ="ACAT" and acctnum=`p_acctnum`  and reqType =`p_reqType`;
       
       
	else if(`p_requestFor`="TDTRF") then
		update `dc_requests`
		set status = 'X'
		where dc_requestFor ="ACH" and acctnum=`p_acctnum`;
         
         update `dc_requests`
		set status = 'X'
		where dc_requestFor ="ACAT" and acctnum=`p_acctnum`;
        
         update `dc_requests`
		set status = 'I'
		where dc_requestFor ="TDTRF" and acctnum=`p_acctnum`;
   
   end if;
   end if;
  end if;
  
  select 
	max(`eventNum`)
	into tnextEvent
  from `dc_requests`
  where `acctnum` = `p_acctnum`
  ;
  
  IF (tnextEvent is null)
  THEN
	set p_eventNum = 1;
  ELSE 
	set p_eventNum = tnextEvent ;
  END IF;
   IF (IFNULL(treqId,0) = 0)
  THEN 
	  INSERT INTO `dc_requests`
			( `acctnum`,
			  `eventNum`,
			  `reqType`,
			  `envelopeHeading`,
			  `envelopeId`,
			  `status`,
			  `terminalDetails`,
			  `created`,
              `dc_requestFor`
)
		VALUES
			(
			  `p_acctnum`,
			  `p_eventNum`,
			  `p_reqType`,
			  `p_envelopeHeading`,
			  `p_envelopeId`,
			  'I', -- I = Init
			  `p_terminalDetails`,
			  now(),
              `p_requestFor`
			);
         end if;
		select reqId into `p_reqId`
        from  dc_requests where acctnum=`p_acctnum` and  reqType=`p_reqType` and dc_requestFor=`p_requestFor` 
        ;
        
      /*  IF (`p_reqType` ='MOVE_MONEY_NEW') then
			update dc_move_money_details set reqId=p_reqId where acctnum=`p_acctnum` and (reqId is null or reqId=0);			
		elseif (`p_reqType` ='ELEC_FUND_TRAN_NEW') then
			update dc_elecfund_transfer_details set reqId=p_reqId where acctnum=`p_acctnum` and (reqId is null or reqId=0); 
        elseif (`p_reqType` ='ACCT_TRAN_NEW') then
			update dc_acct_transfer_details set reqId=p_reqId where acctnum=`p_acctnum` and (reqId is null or reqId=0);        
        end if;*/

END$$
DELIMITER ;

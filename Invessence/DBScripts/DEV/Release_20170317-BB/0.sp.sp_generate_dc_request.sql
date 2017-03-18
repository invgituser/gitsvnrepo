drop procedure if exists invdb.sp_generate_dc_request;
delimiter $$
CREATE PROCEDURE invdb.sp_generate_dc_request(in p_advisorName varchar(45),in p_repId varchar(45),in p_acctnum int(11),in p_eventno int(11),in p_action varchar(45))
 begin
   declare padvisorid int(11);
   declare eventno,p_reqId,curcnt,p_reqId2 int(11);
   DECLARE done INT DEFAULT FALSE;
   declare p_action2,p_subaction varchar(45);
   Declare cur1 CURSOR FOR select reqId,action,subaction from invdb.dc_requests where acctnum=p_acctnum  and status='I' and eventNum=p_eventno and action=p_action;
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
   
   set curcnt=0;
   set eventno=p_eventno;
  SELECT 
      id
  INTO padvisorid FROM
      invdb.dc_advisor_details
  WHERE
      advisorName = p_advisorName
          AND repId = p_repId;
   -- select padvisorid; 

   select ifnull(max(eventNum),0) into eventno from invdb.dc_requests_final where acctnum=p_acctnum;
   set eventno=eventno+1;
   -- end if;

   /*insert into invdb.dc_requests(acctnum,advisorid,eventNum,reqType,seqno,envelopeHeading,status,created)
   select p_acctnum,padvisorid,eventno,reqType,seqno,envelopeHeading,'I',now() from invdb.adv_request_document_mappings
   where advisorid=padvisorid and action=p_action and subaction=p_subaction;*/

   OPEN cur1;

   read_loop: LOOP
   FETCH cur1 INTO p_reqId,p_action2,p_subaction;


   IF done THEN
   LEAVE read_loop;
   END IF;


    SET curcnt=curcnt+1;
    set p_reqId2=p_reqId;

  -- select p_reqId,p_action2,p_subaction;

  insert into invdb.dc_requests_final(refReqId,acctnum,advisorid,eventNum,reqType,seqno,envelopeHeading,status,created,formType)
  select p_reqId,p_acctnum,padvisorid,eventno,reqType,seqno,envelopeHeading,'I',now(),formType from invdb.adv_request_document_mappings
  where advisorid=padvisorid and action=p_action2 and subaction=p_subaction;
    -- insert into rbsa.tmp_rbsa_daily (ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,prev_close_price,daily_return,volume,monthly_return) select ticker,p_businessdate,open_price,close_price,high_price,low_price,adjusted_price,prev_close_price,daily_return,volume,monthly_return from rbsa.tmp_rbsa_daily  where businessdate=p_previousbdate;

  -- select '22';
   END LOOP;

   CLOSE cur1;



   if(curcnt>0) then

  insert into invdb.dc_requests_final(refReqId,acctnum,advisorid,eventNum,reqType,seqno,envelopeHeading,status,created,formType)
  select p_reqId2,p_acctnum,advisorid,eventno,reqType,seqno,envelopeHeading,'I',now(),formType from invdb.adv_request_document_mappings
  where advisorid=padvisorid and action=p_action and subaction='DEFAULT' and formType='ADV';
 
  
  SELECT eventno AS 'EventNo';
  end if;
   end;

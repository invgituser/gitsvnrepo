drop procedure if exists invdb.sp_generate_dc_request;
DELIMITER $$
CREATE PROCEDURE sp_generate_dc_request(IN p_advisorname VARCHAR(45),
                                        IN p_repid       VARCHAR(45),
                                        IN p_acctnum     INT(11),
                                        IN p_eventno     INT(11),
                                        IN p_action      VARCHAR(45))
begin
  DECLARE padvisorid INT(11);

  DECLARE eventno, p_reqid, curcnt, p_reqid2, vacat2eventid INT(11);

  DECLARE done INT DEFAULT false;

  DECLARE p_action2, p_subaction VARCHAR(45);

  DECLARE cur1 CURSOR FOR
    SELECT reqid,
           action,
           subaction
    FROM   invdb.dc_requests
    WHERE  acctnum = p_acctnum
           AND status = 'I'
           AND eventnum = p_eventno
           AND action = p_action;

  DECLARE CONTINUE handler
  FOR NOT found
    SET done = TRUE;

  SET curcnt=0;

  SET vacat2eventid=0;

  SET eventno=p_eventno;

  SELECT id
  INTO   padvisorid
  FROM   invdb.dc_advisor_details
  WHERE  advisorname = p_advisorname
         AND repid = ( CASE
                         WHEN p_repid IS NULL
                               OR p_repid = '' THEN 'CATCHALL'
                         ELSE p_repid
                       end );

  -- select padvisorid;
  SELECT Ifnull(Max(eventnum), 0)
  INTO   eventno
  FROM   invdb.dc_requests_final
  WHERE  acctnum = p_acctnum;

  SET eventno=eventno+1;
  open cur1;
  READ_LOOP:
LOOP
    FETCH cur1 INTO p_reqid, p_action2, p_subaction;
    IF done THEN
      LEAVE read_loop;
    end IF;
    SET curcnt=curcnt+1;
    SET p_reqid2=p_reqid;

    IF( p_action2 = 'ACAT2' ) THEN
      SET vacat2eventid=eventno+1;
    end IF;
    INSERT INTO invdb.dc_requests_final
                (refreqid,
                 acctnum,
                 advisorid,
                 eventnum,
                 reqtype,
                 seqno,
                 envelopeheading,
                 status,
                 created,
                 formtype)
    SELECT p_reqid,
           p_acctnum,
           padvisorid,
           eventno,
           reqtype,
           seqno,
           envelopeheading,
           'I',
           Now(),
           formtype
    FROM   invdb.adv_request_document_mappings
    WHERE  advisorid = padvisorid
           AND action = p_action2
           AND subaction = p_subaction;

  end LOOP;

  close cur1;

  IF( curcnt > 0 ) THEN
    INSERT INTO invdb.dc_requests_final
                (refreqid,
                 acctnum,
                 advisorid,
                 eventnum,
                 reqtype,
                 seqno,
                 envelopeheading,
                 status,
                 created,
                 formtype)
    SELECT p_reqid2,
           p_acctnum,
           advisorid,
           eventno,
           reqtype,
           seqno,
           envelopeheading,
           'I',
           Now(),
           formtype
    FROM   invdb.adv_request_document_mappings
    WHERE  advisorid = padvisorid
           AND action = p_action
           AND subaction = 'DEFAULT'
           AND formtype = 'ADV';

    IF( vacat2eventid <> 0 ) THEN
      UPDATE invdb.dc_requests_final
      SET    eventnum = vacat2eventid
      WHERE  advisorid = padvisorid
             AND acctnum = p_acctnum
             AND eventnum = eventno
             AND reqtype = 'ACAT_OTHER_NEW';
    end IF;

    SELECT eventno       AS 'EventNo',
           vacat2eventid AS 'OtherEventNo';
  end IF;
end ;
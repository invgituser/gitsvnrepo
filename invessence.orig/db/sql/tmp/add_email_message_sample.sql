CALL `invdb`.`sp_email_messages_add_mod`(
'A', -- <{IN  p_addmodflag      VARCHAR(1)}>, 
'Internal', -- <{IN p_source    varchar(20)}>, 
null, -- <{IN p_messageid bigint(20)}>, 
'pmehta@comcast.net', -- <{IN p_sender varchar(250)}>, 
'pmehta@comcast,net', -- <{IN p_receiver varchar(250)}>, 
null, -- <{IN p_cc varchar(250)}>, 
null,  -- <{IN p_bcc varchar(250)}>, 
'Testing HTML', -- <{IN p_subject varchar(60)}>, 
0, -- <{IN p_status tinyint(4)}>, 
0, -- <{IN p_category tinyint(4)}>, 
0, -- <{IN p_priority tinyint(4)}>, 
0, -- <{IN p_logonid bigint(20)}>, 
null, -- <{IN p_sentdate varchar(12)}>, 
'<!DOCTYPE html>
<html>
  <head>
    <title></title>
  </head>
  <body>
<h1> Testing </h1>
<hr/>
Prashant Mehta<br/>
<img src="file://C:/Workspace/Source/project/modules/web/src/main/webapp/images/invessence_logo_RGB1-300x65.jpg" width="110" height="35" alt="" title="" />
  </body>
</html>', -- <{IN p_msg mediumtext}>, 
'HTML Message' -- <{IN p_comment varchar(250)}>
);


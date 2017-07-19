## Selecting menu list as per product URL

USE `invdb`;
DROP procedure IF EXISTS `sel_web_mnu_detail`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `sel_web_mnu_detail`(in p_url varchar(45),in p_access varchar(20))
begin
 if(lower(p_access)='superadmin') then
 select url, access, permission, label, icon, level, sublevel, seq, status, availOnMobile, destdir, htmlpage, command
  from invdb.web_menu wm where wm.url=p_url and wm.status='A';
  else
 select url, access, permission, label, icon, level, sublevel, seq, status, availOnMobile, destdir, htmlpage, command
  from invdb.web_menu wm where wm.url=p_url and wm.access=p_access and wm.status='A';
  end if;
 end$$

DELIMITER ;


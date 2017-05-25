## Insertion script for Session Timeout

delete from invdb.web_site_info where name='WEB.SESSION.COUNTDOWNTIME';

INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)
select url,'WEB.SESSION.COUNTDOWNTIME','A','2','N',now() from invdb.web_site_info group by url;


delete from invdb.web_site_info where name='WEB.SESSION.TIMEOUT';

INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)
select url,'WEB.SESSION.TIMEOUT','A','15','N',now() from invdb.web_site_info group by url;


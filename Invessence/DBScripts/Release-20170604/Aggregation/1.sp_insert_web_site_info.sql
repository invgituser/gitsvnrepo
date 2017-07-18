## Insertion script for Aggregator Menu

SET SQL_SAFE_UPDATES = 0;

delete from invdb.web_site_info where name=''MENU.AGGREGATOR'';

INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)
select url,''MENU.AGGREGATOR'',''A'',''N'',''N'',now() from invdb.web_site_info group by url;

SET SQL_SAFE_UPDATES = 1;
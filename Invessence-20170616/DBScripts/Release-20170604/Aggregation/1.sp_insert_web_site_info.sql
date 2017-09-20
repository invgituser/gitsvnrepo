## Insertion script for Aggregator Menu

delete from invdb.web_site_info where name='MENU.AGGREGATOR';

INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)
select url,'MENU.AGGREGATOR','A','A','N',now() from invdb.web_site_info group by url;
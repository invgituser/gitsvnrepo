
INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)
select url, 'ALLOW_VISITOR_REGISTER', 'A', 'N', 'N', now() from invdb.web_site_info group by url ;
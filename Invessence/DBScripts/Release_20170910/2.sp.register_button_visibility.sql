# Insert query for all URL
INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)
select url, 'ALLOW_VISITOR_REGISTER', 'A', 'N', 'N', now() from invdb.web_site_info group by url ;

# update query for BB
UPDATE invdb.web_site_info SET value='Y' WHERE url='prebb' and name='ALLOW_VISITOR_REGISTER';
UPDATE invdb.web_site_info SET value='Y' WHERE url='uatbb' and name='ALLOW_VISITOR_REGISTER';
UPDATE invdb.web_site_info SET value='Y' WHERE url='buildingbenjamins' and name='ALLOW_VISITOR_REGISTER';

# update query for UOB
UPDATE invdb.web_site_info SET value='Y' WHERE url='preuob' and name='ALLOW_VISITOR_REGISTER';
UPDATE invdb.web_site_info SET value='Y' WHERE url='uatuob' and name='ALLOW_VISITOR_REGISTER';
UPDATE invdb.web_site_info SET value='Y' WHERE url='uob' and name='ALLOW_VISITOR_REGISTER';
UPDATE invdb.web_site_info SET value='Y' WHERE url='uwealth' and name='ALLOW_VISITOR_REGISTER';


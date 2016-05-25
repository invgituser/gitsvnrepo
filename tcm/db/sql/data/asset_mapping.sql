TRUNCATE TABLE `invdb`.`asset_mapping`;
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Agro', 'Agriculture', 2, 410, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Bond', 'Bond', 1, 2, 'TEN_T_BOND', 0, 1, '008000', NULL, NULL, 0.03, 0.65, 0.8);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Cash', 'Cash', 1, 19, 'THREE_MO_BILLS', 0.01, 0.02, 'FFFFFF', NULL, NULL, 0.01, 0.02, 0.1);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Commodity', 'Commodities', 1, 4, 'GOLD', 0, 0.08, 'FFFF00', NULL, NULL, 0.07, 0.02, 0.05);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Emerging', 'Emerging Market', '2', '150', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Equity', 'Equity', '1', '1', 'SPX', '0.1', '1', '0000FF', NULL, NULL, '0.08', '0.25', '0.05');
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('ETF', 'ETF', '9', '50', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Foreign', 'Foreign x US', '2', '160', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Gold', 'Gold', '2', '420', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('HYLongTerm', 'High Yield Long Term', '2', '270', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('HYMidTerm', 'High Yield Mid Term', '2', '260', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('HYShortTerm', 'High Yield Short Term', '2', '250', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Index', 'Index', '9', '53', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('Large-Mid', 'Large/Mid Capital', '2', '130', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('LargeCap', 'Large Cap', '2', '140', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('MidCap', 'Mid Capital', '2', '120', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('MuniLongTerm', 'Muni Long Term', '2', '530', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('MuniMidTerm', 'Muni Mid Term', '2', '520', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('MuniShortTerm', 'Muni Short Term', '2', '510', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('MutualFund', 'Mutual Fund', '9', '51', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('SmallCap', 'Small Capital', '2', '110', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('TBill', 'TBill', '2', '910', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('TipsLongTerm', 'Tips Long Term', '2', '340', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('TipsMidTerm', 'Tips Mid Term', '2', '320', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('TipsShortTerm', 'Tips Short Term', '2', '310', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('TresLongTerm', 'Treasury Long Term', '2', '230', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('TresMidTerm', 'Treasury Mid Term', '2', '220', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `invdb`.`asset_mapping` (`assetclass`, `description`, `asset_level`, `sortorder`, `index_fund`, `lower_bound`, `upper_bound`, `color`, `index_lower_bound`, `index_upper_bound`, `avg_performace`, `risk_adjustment`, `end_allocation`) 
VALUES ('TresShortTerm', 'Treasury Short Term', '2', '210', NULL, '0', '0', 'NULL', NULL, NULL, NULL, NULL, NULL);

create table mapping_web_objects (
  objName	      varchar(30) not null,
  objSeqNo        integer,
  objType	      varchar(1) not null,
  objState        varchar(1) default 'I',
  objDefaultValue varchar(30) not null,
  objText         varchar(80) not null,
  PRIMARY KEY(objName,objSeqNo)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

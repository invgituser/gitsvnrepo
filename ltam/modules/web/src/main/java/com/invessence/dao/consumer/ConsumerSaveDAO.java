package com.invessence.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "consumerSaveDAO")
@SessionScoped
public class ConsumerSaveDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

}
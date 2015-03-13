package com.invessence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SecuritiesDao extends JdbcDaoSupport
{
   private final String SECURITIES_QUERY = "select * from SECURITIES";
   public final String TICKER_COLUMN = "TICKER";
   public final String WEIGHT_COLUMN = "SECURITY_WEIGHT";
   public final String BASKET_COLUMN = "BASKET";
   public final String ASSET_CLASS_COLUMN = "ASSET_CLASS";
   public final String SUB_CLASS_COLUMN = "SUB_CLASS";

   public List<Security> getSecurities()
   {
      return getJdbcTemplate().query(SECURITIES_QUERY, new RowMapper<Security>()
      {
         @Override
         public Security mapRow(ResultSet resultSet, int i) throws SQLException
         {
            String ticker = resultSet.getString(TICKER_COLUMN);
            float security_weight = resultSet.getFloat(WEIGHT_COLUMN);
            String basket = resultSet.getString(BASKET_COLUMN);
            String assetClass = resultSet.getString(ASSET_CLASS_COLUMN);
            String subClass = resultSet.getString(SUB_CLASS_COLUMN);
            return new Security(ticker, security_weight, basket, assetClass, subClass);
         }
      });
   }

   public String getSecuritiesAsXml()
   {
      StringBuilder sb = new StringBuilder();
      for (Security security : getSecurities())
      {
         sb.append(security.toXML());
      }

      return buildElement("Securities", sb.toString());
   }

   public String buildElement(String tag, String text)
   {
      return "<" + tag + ">" + text + "</" + tag + ">";
   }

   class Security
   {
      final String ticker;
      final Float weight;
      final String theme;
      final String assetClass;
      final String subClass;

      Security(String name, Float weight, String theme, String assetClass, String subClass)
      {
         this.ticker = name;
         this.weight = weight;
         this.theme = theme;
         this.assetClass = assetClass;
         this.subClass = subClass;
      }

      public String getTicker()
      {
         return ticker;
      }

      public Float getWeight()
      {
         return weight;
      }

      public String getTheme()
      {
         return theme;
      }

      public String getAssetClass()
      {
         return assetClass;
      }

      public String getSubClass()
      {
         return subClass;
      }

      public String toXML()
      {
         StringBuilder sb = new StringBuilder();
         sb.append(buildElement("Ticker", getTicker()));
         sb.append(buildElement("Weight", String.valueOf(getWeight())));
         sb.append(buildElement("Theme", getTheme()));
         sb.append(buildElement("AssetClass", getAssetClass()));
         sb.append(buildElement("SubClass", getSubClass()));
         return buildElement("Security", sb.toString());
      }
   }
}

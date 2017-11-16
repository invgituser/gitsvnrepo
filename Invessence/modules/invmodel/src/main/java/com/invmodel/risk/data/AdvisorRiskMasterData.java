package com.invmodel.risk.data;

import com.invessence.converter.SQLData;

/**
 * Created by prashant on 11/9/2017.
 */
public class AdvisorRiskMasterData
{
   Integer sortorder;
   String key;
   String defaultStrValue;
   Double defaultDoubleValue;
   Integer defaultIntValue;
   Boolean defaultBooleanValue;
   String dataType;
   String displayOnStart;

   public AdvisorRiskMasterData()
   {
   }

   public AdvisorRiskMasterData(Integer sortorder, String key,
                                String defaultValue, String dataType,
                                 String displayOnStart)
   {
      this.sortorder = sortorder;
      this.key = key;
      setDefaultValue(defaultValue, dataType);
      this.displayOnStart = displayOnStart;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }

   public String getKey()
   {
      return key;
   }

   public void setKey(String key)
   {
      this.key = key;
   }

   public void setDefaultValue(String defaultValue, String dataType) {
      SQLData converter = new SQLData();
      if (dataType != null)
      {
         if (dataType.equalsIgnoreCase("D"))
         {
            defaultDoubleValue = converter.getDoubleData(defaultValue);
         }
         else if (dataType.equalsIgnoreCase("I"))
         {
            defaultIntValue = converter.getIntData(defaultValue);
         }
         else if (dataType.equalsIgnoreCase("B"))
         {
            defaultBooleanValue = converter.getBooleanData(defaultValue);

         }
      }
      defaultStrValue = defaultValue;
      this.dataType = dataType;

   }

   public String getDisplayOnStart()
   {
      return displayOnStart;
   }

   public void setDisplayOnStart(String displayOnStart)
   {
      this.displayOnStart = displayOnStart;
   }

   public String getDefaultStrValue()
   {
      return defaultStrValue;
   }

   public void setDefaultStrValue(String defaultStrValue)
   {
      this.defaultStrValue = defaultStrValue;
   }

   public Double getDefaultDoubleValue()
   {
      return defaultDoubleValue;
   }

   public void setDefaultDoubleValue(Double defaultDoubleValue)
   {
      this.defaultDoubleValue = defaultDoubleValue;
   }

   public Integer getDefaultIntValue()
   {
      return defaultIntValue;
   }

   public void setDefaultIntValue(Integer defaultIntValue)
   {
      this.defaultIntValue = defaultIntValue;
   }

   public Boolean getDefaultBooleanValue()
   {
      return defaultBooleanValue;
   }

   public void setDefaultBooleanValue(Boolean defaultBooleanValue)
   {
      this.defaultBooleanValue = defaultBooleanValue;
   }

   public String getDataType()
   {
      return dataType;
   }

   public void setDataType(String dataType)
   {
      this.dataType = dataType;
   }
}

package com.invmodel.risk.data;

import com.invessence.converter.SQLData;
import com.invmodel.risk.dao.RiskFetchDAO;

/**
 * Created by prashant on 11/9/2017.
 */
public class AdvisorRiskMasterData
{
   private Integer sortorder;
   private String key;
   private String defaultStrValue;
   private Double defaultDoubleValue;
   private Integer defaultIntValue;
   private Boolean defaultBooleanValue;
   private String dataType;
   private String displayOnStart;
   private Boolean saveforUser;

   public AdvisorRiskMasterData()
   {
   }

   public AdvisorRiskMasterData(Integer sortorder, String key,
                                String defaultValue, String dataType,
                                 String displayOnStart, Boolean saveForUser)
   {
      this.sortorder = sortorder;
      this.key = key;
      setDefaultValue(defaultValue, dataType);
      this.displayOnStart = displayOnStart;
      this.saveforUser = saveForUser;
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
      if (defaultDoubleValue == null && defaultIntValue != null) {
         return defaultIntValue.doubleValue();
      }
      return defaultDoubleValue;
   }

   public void setDefaultDoubleValue(Double defaultDoubleValue)
   {
      this.defaultDoubleValue = defaultDoubleValue;
   }

   public Integer getDefaultIntValue()
   {
      if (defaultIntValue == null && defaultDoubleValue != null) {
         return defaultDoubleValue.intValue();
      }
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

   public Boolean getSaveforUser()
   {
      return saveforUser;
   }

   public void setSaveforUser(Boolean saveforUser)
   {
      this.saveforUser = saveforUser;
   }
}

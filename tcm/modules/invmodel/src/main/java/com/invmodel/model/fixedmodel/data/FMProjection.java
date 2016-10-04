package com.invmodel.model.fixedmodel.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/3/16
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FMProjection
{
   String theme;
   String displayname;
   Map<String, ArrayList<FMProjectionData>> data;

   public FMProjection()
   {
      data = new LinkedHashMap<String, ArrayList<FMProjectionData>>();
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
   }

   public Map<String, ArrayList<FMProjectionData>> getData()
   {
      return data;
   }

   public void setData(Map<String, ArrayList<FMProjectionData>> data)
   {
      this.data = data;
   }

   public ArrayList<FMProjectionData> getProjectionData(String model) {
      if (model != null) {
         if (data.containsKey(model)) {
            return data.get(model);
         }
      }
      return null;
   }
}

package com.invessence.web.data.consumer;

/**
 * Created by prashant on 11/16/2017.
 */
public class Goals
{
   Integer goalID;
   String imagepath;
   String name;
   String description;

   public Goals()
   {
   }

   public Goals(Integer goalID, String imagepath,
                String name, String description)
   {
      this.goalID = goalID;
      this.imagepath = imagepath;
      this.name = name;
      this.description = description;
   }

   public Integer getGoalID()
   {
      return goalID;
   }

   public void setGoalID(Integer goalID)
   {
      this.goalID = goalID;
   }

   public String getImagepath()
   {
      return imagepath;
   }

   public void setImagepath(String imagepath)
   {
      this.imagepath = imagepath;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }
}

package com.invessence.util;

public class XMLBuilder
{
   public static String buildAllocationInput(String age, String risk, String duration)
   {
      return buildElement("AllocationInput", buildElement("Age", age) + buildElement("Risk", risk) + buildElement("Duration", duration));
   }
   public static String buildElement(String tag, String text)
   {
      return "<" + tag + ">" + text + "</" + tag + ">";
   }
}

package com.invessence.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/4/14
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChartInfo {
   private String _name;
   private int _count;
   private String _color;

   public ChartInfo(String name, int count, String color) {
      _name = name;
      _count = count;
      _color = color;
   }

   public String getName() {
      return _name;
   }

   public void setName(String name) {
      this._name = name;
   }

   public int getCount() {
      return _count;
   }

   public void setCount(int count) {
      this._count = count;
   }

   public String getColor() {
      return _color;
   }

   public void setColor(String color) {
      this._color = color;
   }
}
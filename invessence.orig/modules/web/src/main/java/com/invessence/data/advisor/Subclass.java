package com.invessence.data.advisor;

import java.util.*;

import com.invessence.data.*;
import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class Subclass implements Serializable
{
      private String name;
      private String assetclass;
      private String parentclass;
      private String subclass;
      private Double weight;

   public Subclass(String name, String assetclass, String parentclass, String subclass, Double weight)
   {
      this.name = name;
      this.assetclass = assetclass;
      this.parentclass = parentclass;
      this.subclass = subclass;
      this.weight = weight;
   }
}

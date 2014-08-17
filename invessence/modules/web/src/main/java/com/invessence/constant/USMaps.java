package com.invessence.constant;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/24/13
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class USMaps {

    private static USMaps mapsInstance = null;
    private static Map<String, String> states = new LinkedHashMap<String, String>();

    static {
       states.put("AL","Alabama");
       states.put("AK","Alaska");
       states.put("AZ","Arizona");
       states.put("AR","Arkansas");
       states.put("CA","California");
       states.put("CO","Colorado");
       states.put("CT","Connecticut");
       states.put("DE","Delaware");
       states.put("FL","Florida");
       states.put("GA","Georgia");
       states.put("HI","Hawaii");
       states.put("ID","Idaho");
       states.put("IL","Illinois");
       states.put("IN","Indiana");
       states.put("IA","Iowa");
       states.put("KS","Kansas");
       states.put("KY","Kentucky");
       states.put("LA","Louisiana");
       states.put("ME","Maine");
       states.put("MD","Maryland");
       states.put("MA","Massachusetts");
       states.put("MI","Michigan");
       states.put("MN","Minnesota");
       states.put("MS","Mississippi");
       states.put("MO","Missouri");
       states.put("MT","Montana");
       states.put("NE","Nebraska");
       states.put("NV","Nevada");
       states.put("NH","New Hampshire");
       states.put("NJ","New Jersey");
       states.put("NM","New Mexico");
       states.put("NY","New York");
       states.put("NC","North Carolina");
       states.put("ND","North Dakota");
       states.put("OH","Ohio");
       states.put("OK","Oklahoma");
       states.put("OR","Oregon");
       states.put("PA","Pennsylvania");
       states.put("RI","Rhode Island");
       states.put("SC","South Carolina");
       states.put("SD","South Dakota");
       states.put("TN","Tennessee");
       states.put("TX","Texas");
       states.put("UT","Utah");
       states.put("VT","Vermont");
       states.put("VA","Virginia");
       states.put("WA","Washington");
       states.put("WV","West Virginia");
       states.put("WI","Wisconsin");
       states.put("WY","Wyoming");
    }

   public static synchronized USMaps getInstance(){
       if (mapsInstance == null) {
         mapsInstance = new USMaps();
       }
       return mapsInstance;
    }

    private USMaps() {
       super();
    }

   public Map<String, String> getStates()
   {
      return states;
   }

   /*
    public void initCountry () {
        String[] tCountry = new String [] {"USA"};
        try {
            this.country = tCountry;
        }
        catch (Exception ex){
            System.out.println("Error when attempting to create list of US states");
        }
    }
   */



}

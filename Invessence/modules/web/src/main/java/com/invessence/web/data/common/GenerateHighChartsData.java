package com.invessence.web.data.common;

import java.util.*;
import javax.faces.bean.*;

import com.google.api.client.util.ArrayMap;
import com.google.gson.Gson;
import com.invessence.web.data.DataPortfolio;
import com.invmodel.asset.data.Asset;

/**
 * Created by Akhilesh on 2/20/2017.
 */

@ManagedBean(name = "generateHighChartsData")
@SessionScoped
public class GenerateHighChartsData
{

   ArrayList<Map> consolidateAssetAndSubAssetList;

   public ArrayList<Map> getConsolidateAssetAndSubAssetList()
   {
      return consolidateAssetAndSubAssetList;
   }

   public void setConsolidateAssetAndSubAssetList(ArrayList<Map> consolidateAssetAndSubAssetList)
   {
      this.consolidateAssetAndSubAssetList = consolidateAssetAndSubAssetList;
   }

   /**
    * this method is for generate arraylist for donut SingleLAYER
    * @param  assetList list Of asset
    * @return ArrayList<map>
    */
   public String create2DDONUTChart(ArrayList<Asset> assetList){
      ArrayList<Map> list = new ArrayList();

         for (Asset stringArrayListOne : assetList)
         {
            ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
            map.put("name",stringArrayListOne.getAsset());
            map.put("y",new Double(stringArrayListOne.getActualweight()*100));
            map.put("drilldown",stringArrayListOne.getAsset());
            map.put("color",stringArrayListOne.getColor());
            map.put("amount",(int)stringArrayListOne.getValue());
            list.add(map);
         }

      return new Gson().toJson(list);
      //return list;
   }

   /**
    * this method is for generate arraylist for piechart DoubleLAYER
    * @param  assetList list Of asset
    * @return ArrayList<map>
    */
   public String create2DDONUTChart2Layer(ArrayList<Asset> assetList){
      ArrayList<Map> list = new ArrayList();
         ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
         map.put("categories",getAssetData(assetList));
         list.add(map);
      return new Gson().toJson(list);
   }

   /**
    * this method is for generate arraylist for donutchart DoubleLAYER
    * @param  subAssetlist list Of asset
    * @return ArrayList<map>
    */
   public String create2DDONUTChartForDetailLayer(List<DataPortfolio> subAssetlist){
      ArrayList<Map> list = new ArrayList();
      for (DataPortfolio stringArrayListOne : subAssetlist)
      {
         ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
         map.put("y",getAssetTotalWeight(stringArrayListOne.getAssetType(),subAssetlist));
         map.put("color",stringArrayListOne.getColor());
         map.put("drilldown",getdrilldownFor2dDonut(stringArrayListOne.getAssetType(),subAssetlist));
         list.add(map);
      }
      ArrayList<Map> list2 = new ArrayList();
      ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
      map.put("assetList",getConsolidateAssetAndSubAssetList());// for previous chart
      //map.put("assetList",getAssetData(this.getEditableAsset())); for 2d donut chart
      map.put("subAssetList",list);
      list2.add(map);
      return new Gson().toJson(map);
   }

   /**
    * this method is for generate arraylist for piechart DoubleLAYER
    * @param  subAssetlist list Of asset
    * @return ArrayList<map>
    */
   public String createPIEChartForDetailLayer(List<DataPortfolio> subAssetlist){
      ArrayList<Map> list = new ArrayList();

      if(false){
         for (DataPortfolio stringArrayListOne : subAssetlist)
         {
            ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
            map.put("y",getAssetTotalWeight(stringArrayListOne.getAssetType(),subAssetlist));
            map.put("color",stringArrayListOne.getColor());
            map.put("drilldown",getdrilldownFor2dDonut(stringArrayListOne.getAssetType(),subAssetlist));
            list.add(map);
         }
      }else{
         for (DataPortfolio stringArrayListOne : subAssetlist)
         {
            ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
            map.put("name",stringArrayListOne.getAssetType());
            map.put("id",stringArrayListOne.getAssetType());
            map.put("data",internalData(map.get("name").toString(),subAssetlist));
            list.add(map);
         }
      }
      ArrayList<Map> list2 = new ArrayList();
      ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
      map.put("assetList",getConsolidateAssetAndSubAssetList());// for previous chart
      // map.put("assetList",getAssetData(this.getEditableAsset())); for 2d donut chart
      map.put("subAssetList",list);
      list2.add(map);
      return new Gson().toJson(map);
   }

   public ArrayList internalData(String assetType,List<DataPortfolio> edittableAsset){
      ArrayList list1 = new ArrayList();
      ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
      for (DataPortfolio stringArrayList : edittableAsset)
      {
         ArrayList list = new ArrayList();
         if(stringArrayList.getAssetType().equalsIgnoreCase(assetType)){
            list.add(stringArrayList.getSymbol());
            list.add(stringArrayList.getWeight());
            list1.add(list);
         }
      }
      return list1;
   }

   public ArrayList getdrilldownFor2dDonut(String assetType,List<DataPortfolio> edittableAsset){
      ArrayList list1 = new ArrayList();
      ArrayMap<String,Object> map  = new ArrayMap<String,Object>();
      for (DataPortfolio stringArrayList : edittableAsset)
      {
         ArrayList list = new ArrayList();
         if(stringArrayList.getAssetType().equalsIgnoreCase(assetType)){
            map.put("name",stringArrayList.getAssetType());
            map.put("categories",getSubAssetNameData(edittableAsset));
            map.put("data",getSubAssetWeightData(edittableAsset));
            list1.add(map);
         }
      }
      return list1;
   }

   public ArrayList getAssetData(ArrayList<Asset> edittableAsset){
      ArrayList list1 = new ArrayList();
      for (Asset stringArrayListOne : edittableAsset)
      {
         list1.add(stringArrayListOne.getAsset());
      }
      return list1;
   }

   public ArrayList getSubAssetNameData(List<DataPortfolio> edittableAsset){
      ArrayList list1 = new ArrayList();
      for (DataPortfolio stringArrayListOne : edittableAsset)
      {
         list1.add(stringArrayListOne.getSymbol());
      }
      return list1;
   }

   public ArrayList getSubAssetWeightData(List<DataPortfolio> edittableAsset){
      ArrayList list1 = new ArrayList();
      for (DataPortfolio stringArrayListOne : edittableAsset)
      {
         list1.add(stringArrayListOne.getWeight());
      }
      return list1;
   }

   public Double getAssetTotalWeight(String assetType,List<DataPortfolio> edittableAsset){
      ArrayList list1 = new ArrayList();
      Double totalWeight = 0d;
      for (DataPortfolio stringArrayList : edittableAsset)
      {
         if(stringArrayList.getAssetType().equalsIgnoreCase(assetType)){
            totalWeight  =totalWeight +  stringArrayList.getWeight();
         }
      }
      return totalWeight;
   }


}

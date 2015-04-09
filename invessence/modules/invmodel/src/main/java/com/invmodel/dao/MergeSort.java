package com.invmodel.dao;

import java.util.concurrent.locks.*;
import java.util.logging.Logger;

public class MergeSort
{
   private static MergeSort instance = null;
   private final Logger logger = Logger.getLogger(MergeSort.class.getName());
   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public static synchronized MergeSort getInstance()
   {
      if (instance == null)
      {
         instance = new MergeSort();
      }

      return instance;
   }

   private MergeSort()
   {
      super();
   }

   private double[] array;
   private double[] tempMergArr;
   private int[] fArry;
   private int[] tmpFArry;
   private int length;

   public void sort(double inputArr[], int fundOffset[]) {
      this.array = inputArr;
      this.fArry = fundOffset;
      this.length = inputArr.length;
      this.tmpFArry = new int[length];
      this.tempMergArr = new double[length];
      doMergeSort(0, length - 1);
   }

   private void doMergeSort(int lowerIndex, int higherIndex) {

      if (lowerIndex < higherIndex) {
         int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
         // Below step sorts the left side of the array
         doMergeSort(lowerIndex, middle);
         // Below step sorts the right side of the array
         doMergeSort(middle + 1, higherIndex);
         // Now merge both sides
         mergeParts(lowerIndex, middle, higherIndex);
      }
   }

   private void mergeParts(int lowerIndex, int middle, int higherIndex) {

      for (int i = lowerIndex; i <= higherIndex; i++) {
         tempMergArr[i] = array[i];
         tmpFArry[i] = fArry[i];
      }
      int i = lowerIndex;
      int j = middle + 1;
      int k = lowerIndex;
      while (i <= middle && j <= higherIndex) {
         if (tempMergArr[i] <= tempMergArr[j]) {
            array[k] = tempMergArr[i];
            fArry[k] = tmpFArry[i];
            i++;
         } else {
            array[k] = tempMergArr[j];
            fArry[k] = tmpFArry[j];
            j++;
         }
         k++;
      }
      while (i <= middle) {
         array[k] = tempMergArr[i];
         fArry[k] = tmpFArry[i];
         k++;
         i++;
      }

   }
}

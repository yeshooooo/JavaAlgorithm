package com.class01;

@SuppressWarnings({"all"})
public class SelectionSort {
   public static void selectSort(int[] arr) {
      if (arr == null || arr.length < 2) {
         return;
      }

      for (int i = 0; i < arr.length - 1 ; i++) { // 这里实际上最后一个数不用处理, 教程里对此边界没有考虑
         int minValueIndex = i;
         for (int j = i; j < arr.length; j++) {
            minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;

         }
         swap(arr,i,minValueIndex);

      }

   }
   public static void swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
   }
   public static void printArray(int[] arr){
      for (int i = 0; i < arr.length; i++) {
         System.out.print(arr[i] + " ");
      }
      System.out.println();
   }

   public static void main(String[] args) {
      int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
      printArray(arr);
      selectSort(arr);
      printArray(arr);
   }
}

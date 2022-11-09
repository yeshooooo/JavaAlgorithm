package class06;

import java.util.Arrays;

public class ShowComparator {
   public static void printArray(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
         System.out.print(arr[i] + " ");
      }
      System.out.println();
   }
   public static void main(String[] args) {
      int[] arr = {8, 1, 4, 1, 6, 8, 4, 1, 5, 8, 2, 3, 0};
      Arrays.sort(arr);
   }
}

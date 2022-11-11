package class18;

public class Codes02_CardsInLine {


   // 根据规则, 返回获胜者得分数
   public static int win1(int[] arr) {

      // 边界条件
      if (arr == null || arr.length == 0) {
         return 0;
      }

      int first = f1(arr, 0, arr.length - 1);
      int second = g1(arr, 0, arr.length - 1);
      return Math.max(first, second);

   }


   // 先手函数
   // 在arr[L...R] 范围上先手获得得最好分数返回
   public static int f1(int[] arr, int L, int R) {
      if (L == R) {
         return arr[L];
      }

      // 拿左侧
      int p1  = arr[L] + g1(arr, L + 1, R);
      // 拿右侧
      int p2 = arr[R] + g1(arr, L , R - 1);
      return Math.max(p1, p2);
   }

   // 后手函数
   // arr[L...R], 后手获得得最好分数返回
   public static int g1(int[] arr, int L, int R) {
      if (L == R ) {
         return 0;
      }
      int p1 = f1(arr, L + 1, R); // 对手拿走了L位置得数 (对手做了决定, 我在对手剩下得里面选最好)
      int p2 = f1(arr, L, R - 1); // 对手拿走了R位置得数

      // 后手不能决定自己拿最大,因为对手只会把最小得最优扔给你,对手只会留给你都是最好里那个相对较差得
      return Math.min(p1,p2);
   }

   // 优化 --- 傻缓存法
   // 根据规则, 返回获胜者得分数\
   // 因为f2 和 g2 相互依赖, 所以搞两张表
   public static int win2(int[] arr) {

      // 边界条件
      if (arr == null || arr.length == 0) {
         return 0;
      }

      int N = arr.length;
      int[][] fmap = new int[N][N];
      int[][] gmap = new int[N][N];
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            fmap[i][j] = -1;
            gmap[i][j] = -1;
         }
      }

      int first = f2(arr, 0, arr.length - 1, fmap, gmap);
      int second = g2(arr, 0, arr.length - 1, fmap, gmap);
      return Math.max(first, second);

   }


   // 先手函数
   // 在arr[L...R] 范围上先手获得得最好分数返回
   public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
      if (fmap[L][R] != -1) {
         return fmap[L][R];
      }
      int ans = 0; // 在返回答案之前塞到缓存里
      if (L == R) {
         ans = arr[L];
      }else {
         // 拿左侧
         int p1  = arr[L] + g2(arr, L + 1, R, fmap, gmap);
         // 拿右侧
         int p2 = arr[R] + g2(arr, L , R - 1, fmap, gmap);
         ans = Math.max(p1, p2);
      }

      fmap[L][R] = ans;
      return ans;
   }

   // 后手函数
   // arr[L...R], 后手获得得最好分数返回
   public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
      if (gmap[L][R] != -1) {
         return gmap[L][R];
      }

      int ans = 0;
      // 这里本来返回0 ,所以可以声调一个分支
//      if (L == R ) {
//         return 0;
//      }
      if (L != R) {
         int p1 = f2(arr, L + 1, R, fmap, gmap);
         int p2 = f2(arr, L, R - 1, fmap, gmap);
         ans = Math.min(p1,p2);
      }

      gmap[L][R] = ans;

      return ans;
   }

   // 严格表依赖的动态规划
   public static int win3(int[] arr) {
      // 边界条件
      if (arr == null || arr.length == 0) {
         return 0;
      }

      int N = arr.length;
      int[][] fmap = new int[N][N];
      int[][] gmap = new int[N][N];
      
      // 先设置f对象线的值
      for (int i = 0; i < N; i++) {
         fmap[i][i] = arr[i];
      }
      // g 的对象线都是0, java初始化的时候就都是0了,所以这里不用管

      // 描述按照对角线推

      for (int startCol = 1; startCol < N; startCol++) { // 对角线的列从1出发,不能超过N,因为对象线已经设置过了
         int L = 0; // 每个子对角线的开始行
         int R = startCol;
         while (R < N) { // 行++ 列也++ 顺着对角线往下 ,行不会越界.列会越界,所以只判断列越界

            fmap[L][R] =   Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][ R - 1]); // 把递归行为替换成在表里面拿值
            gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
            L++;
            R++;
         }
      }
      return Math.max(fmap[0][N-1], gmap[0][N - 1]);

   }

   public static void main(String[] args) {
      int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
      System.out.println(win1(arr));
      System.out.println(win2(arr));
      System.out.println(win3(arr));
   }

}

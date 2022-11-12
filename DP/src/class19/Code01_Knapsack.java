package class19;
// 背包问题
public class Code01_Knapsack {

   // 所有的货,重量和价值,都在w和v数组里
   // 为了方便, 期中没有负数
   // bag 背包容量, 不能超过这个载重
   // 返回: 不超重的情况下, 能够得到的最大价值
   // w 重量, v价值, bag 背包容量
   public static int maxValue(int[] w, int[] v, int bag) {
      // 边界
      if (w == null || v == null || w.length != v.length || w.length == 0) {
         return 0;
      }

      // 尝试函数
      return process(w,v,0,bag);
   }


   // 当前考虑到了index号货物,index... 所有的货物可以自由选择
   // 做得选择不能超过背包容量
   // 返回最大价值

   // index 0 -N
   // rest 负数~bag
   public static int process(int[] w, int[] v, int index, int rest) {
      if (rest < 0) { // 背包容量小0了,也返回
         return -1;
      }
      if (index == w.length) { // 越界的时候返回0
         return 0;
      }

      // 有货, index 位置的货 (index没到最后)
      // rest 有空间, (不小于0)
      // 有两种选择 : 1. 不要当前的货 2. 要当前的货
      int p1 = process(w, v, index + 1, rest); // 不要当前的货

      // 为了防止第一个数就超重,先调一下后续
      int p2 = 0;
      int next = process(w, v, index + 1, rest - w[index]);
      if (next != -1) { // 当前货物有效
         p2 = v[index] + next; // 要加上此时的货的价值
      }

      return Math.max(p1, p2);
   }

   // 动态规划法
   public static int dp(int[] w, int[] v, int bag) {
      // 边界
      if (w == null || v == null || w.length != v.length || w.length == 0) {
         return 0;
      }

      int N = w.length;

      // 行 0 - N
      // 列 rest 假设 0- bag
      int[][] dp = new int[N+1][bag + 1];

      // dp 表填好
      // dp[N][...] = 0 第 0 行全是 0
      // 只依赖下一行, 所以同一行从左右开始都行
      for (int index = N - 1; index >= 0; index--) { // 行
         for (int rest = 0; rest <= bag; rest++) {
            int p1 = dp[index + 1][rest];
            int p2 = 0;
            int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
            if (next != -1) {
               p2 = v[index] + next;
            }
            dp[index][rest] = Math.max(p1, p2);

         }
      }



      return dp[0][bag]; // 返回是暴力递归决定的,主函数要的是0 bag 这个状态, 暴力递归要什么,动态规划就返回什么
   }

   public static void main(String[] args) {
      int[] weights = {3, 2, 4, 7, 3, 1, 7};
      int[] values = { 5, 6, 3, 19, 12, 4, 2};

      int bag = 15;
      System.out.println(maxValue(weights, values, bag));
      System.out.println(dp(weights, values, bag));
   }
}

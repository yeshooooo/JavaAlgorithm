package class18;

public class Code01_RobotWalk {
   public static int ways1(int N, int start, int aim, int K) {
      // 无厘头边界
      if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
         return -1;
      }
       return process1(start, K, aim, N);
   }
   // 机器人当前来到的位置是cur
    // 机器人还有rest步需要去走
    // 最终的目标是aim
    // 有那些位置 1-N
    // 返回: 机器人从cur 触发,走过rest步之后,最终停在aim的方法数,是多少?
   public static int process1(int cur, int rest, int aim, int N) {
      if (rest == 0) { // 不需要走了
         return cur == aim ? 1: 0;
      }
      // rest > 0
      if (cur == 1) { // 1 -> 2
         return process1(2, rest - 1, aim, N);
      }
      if (cur == N) { // 只能从N到N-1
         return process1(N - 1, rest - 1, aim, N);
      }
      //中间位置 往左走的方法数加上往右走的方法数
      return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim ,N);

   }

   // 缓存法
   // 从顶向下的动态规划 (又叫记忆化搜索)
   public static int ways2(int N, int start, int aim, int K) {
      // 无厘头边界
      if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
         return -1;
      }
      int[][] dp = new int[N+1][K+1];
      for (int i = 0; i <= N; i++) {
         for (int j = 0; j <= K; j++) {
            dp[i][j] = -1;
         }
      }

      // dp就是缓存表
      // dp[cur][rest] = -1  -> process1(cur,rest) 之前没算过
      // dp[cur][rest] = -1  -> process1(cur,rest) 之前算过, 返回值放在了这里
      return process2(start, K, aim, N, dp);
   }

   // cur 范围: 1- N
   // rest 范围: 0 - K
   public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
      if (dp[cur][rest] != -1) {
         return dp[cur][rest];
      }
      // 之前没算过
      int ans = 0;
      if (rest == 0) {
         ans =  cur == aim ? 1: 0;
      }else if (cur == 1){
         ans = process2(2, rest - 1, aim, N, dp);
      } else if (cur == N) {
         ans = process2(N - 1, rest - 1, aim, N, dp);
      } else {
         ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim ,N ,dp);
      }
      // 在返回之前把缓存记上
      dp[cur][rest] = ans;

      return ans;

   }

   // 最终版本
   public static int ways3(int N, int start, int aim, int K) {
      // 无厘头边界
      if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
         return -1;
      }
      int[][] dp = new int[N+1][K+1]; // java中本身dp初始都是0

      // rest == 0 cur == aim 才是1
      // cur 是行
      // 第一列只需要填 aim
      dp[aim][0] = 1; // 第 0 列就填好了

      // 填写dp ,从第一列开始填,因为第 0列已经搞定
      for (int rest = 1; rest <= K; rest++) { //列

         // cur : 1- N ,但是这里从2 开始,因为当前行在第一列的时候,只依赖2 位置上一行
         dp[1][rest] = dp[2][rest - 1];
         for (int cur = 2; cur < N; cur++) {// 行
            dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest -1];
         }
         dp[N][rest] = dp[N-1][rest - 1];

      }

      return dp[start][K]; // 最终要的就是start,k这个格子
   }
   public static void main(String[] args) {
      System.out.println(ways2(4,2,4,4));
      System.out.println(ways2(5,2,4,6));
      System.out.println(ways3(5,2,4,6));
   }
}

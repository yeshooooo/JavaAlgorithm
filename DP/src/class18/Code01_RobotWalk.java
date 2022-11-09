package class18;

public class Code01_RobotWalk {
   public static int ways1(int N, int start, int aim, int K) {
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
      //中间位置
      return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim ,N);

   }

   public static void main(String[] args) {
      System.out.println(ways1(4,2,4,4));
   }
}

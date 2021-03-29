package year2021.month3.no70;

public class ClimbingStairs {

    public static void main(String[] args) {
        int n1 = 5; //8
        int n2 = 7; //21
        System.out.println(climbStairs(n1));
        System.out.println(climbStairs(n2));
    }

    public static int climbStairs(int n) {
        /*DP
        * dp[i]定义为爬到第 i+1 阶楼梯有多少种不同的方法， i = 0, 1, ... , n-1
        * dp[i] = dp[i-1] + dp[i-2]
        * */
        if (n == 1) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}

/*
* 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/climbing-stairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

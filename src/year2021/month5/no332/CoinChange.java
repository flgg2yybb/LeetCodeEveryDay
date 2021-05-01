package year2021.month5.no332;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        int[] coins2 = {2};
        int amount2 = 3;
        int[] coins3 = {1};
        int amount3 = 0;
        int[] coins4 = {1};
        int amount4 = 1;
        int[] coins5 = {1};
        int amount5 = 2;
        System.out.println(coinChange(coins1, amount1));
        System.out.println(coinChange(coins2, amount2));
        System.out.println(coinChange(coins3, amount3));
        System.out.println(coinChange(coins4, amount4));
        System.out.println(coinChange(coins5, amount5));
    }

    public static int coinChange(int[] coins, int amount) {
        /*DP
         * 类似于爬楼梯，求爬上 amount 阶楼梯的最小步数，
         * 每次可以走 coins 数组中元素的步数
         * 则状态转移方程为
         * dp[i] = min{dp[i - coin]} + 1, coin 为 coins数组中的元素
         * */
        final int MAX = amount + 1;
        // 若用 Integer.MAX_VALUE ，在状态转移时会越界为负数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == MAX ? -1 : dp[amount];
    }
}

/*
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

你可以认为每种硬币的数量是无限的。

 

示例 1：

输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
示例 2：

输入：coins = [2], amount = 3
输出：-1
示例 3：

输入：coins = [1], amount = 0
输出：0
示例 4：

输入：coins = [1], amount = 1
输出：1
示例 5：

输入：coins = [1], amount = 2
输出：2
 

提示：

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

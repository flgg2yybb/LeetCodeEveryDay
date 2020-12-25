package twenty.december.no322;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins1 = new int[]{1, 2, 5};
        int amount1 = 11;
        int[] coins2 = new int[]{2};
        int amount2 = 3;
        int[] coins3 = new int[]{1};
        int amount3 = 0;
        int[] coins4 = new int[]{1};
        int amount4 = 1;
        int[] coins5 = new int[]{1};
        int amount5 = 2;
        int[] coins6 = new int[]{186, 419, 83, 408};
        int amount6 = 6249;
        int[] coins7 = new int[]{1, 2, 5, 10, 20, 50, 100, 150};
        int amount7 = 999999;
        System.out.println(coinChange2(coins1, amount1));
        System.out.println(coinChange2(coins2, amount2));
        System.out.println(coinChange2(coins3, amount3));
        System.out.println(coinChange2(coins4, amount4));
        System.out.println(coinChange2(coins5, amount5));
        System.out.println(coinChange2(coins6, amount6));
        System.out.println(coinChange2(coins7, amount7));

    }

    private static int coinChange2(int[] coins, int amount) {
        /* DP, 自上而下
         * IDEA运行会栈溢出！
         * */
        return recusive(coins, amount, new int[amount + 1]);
    }

    private static int recusive(int[] coins, int amount, int[] counts) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (counts[amount] != 0) {
            return counts[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = recusive(coins, amount - coin, counts);
            if (count != -1) {
                min = Math.min(min, count + 1);
            }
        }
        counts[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return counts[amount];
    }

    private static int coinChange(int[] coins, int amount) {
        /* DP，自下而上
         * 状态定义：
         * dp[i]为凑成金额 i所需的最少硬币个数
         * 状态转移方程：
         * dp[i] = min{dp[i - j]} + 1, j 为 coins中的任意一枚硬币金额
         * 初始值：
         * dp[0] = 0
         * */
        final int MAX = amount + 1;
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

    public static int coinChangeByBacktrack(int[] coins, int amount) {
//        Backtrack, time limit exceeded
        if (coins == null || coins.length == 0) {
            return -1;
        }
        Arrays.sort(coins);
        return backtrack(coins, amount, coins.length - 1, 0);
    }

    private static int backtrack(int[] coins, int amount, int pos, int count) {
        if (amount == 0) {
            return count;
        }
        if (pos < 0) {
            return -1;
        }
        if (coins[pos] > amount) {
            return backtrack(coins, amount, pos - 1, count);
        }
        int currentCoin = coins[pos];
        int total = amount / currentCoin;
        int minCount = Integer.MAX_VALUE;
        for (int i = total; i >= 0; i--) {
            count += i;
            amount -= i * currentCoin;
            int result = backtrack(coins, amount, pos - 1, count);
            if (result != -1) {
                minCount = Math.min(minCount, result);
            }
            count -= i;
            amount += i * currentCoin;
        }
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }
}

/*
* 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

你可以认为每种硬币的数量是无限的。

 

示例 1：

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
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

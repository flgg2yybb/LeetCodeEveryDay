package year2021.month6.no309;

public class BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 0, 2};
        int[] prices2 = {1, 2, 4};
        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));
    }

    public static int maxProfit(int[] prices) {
        /*
         * DP, time is O(n), space is O(n)
         * 状态定义
         * dp[i][0][0] 代表第 i 天时，不持有股票，且不处于冷冻期的最大利润
         * dp[i][0][1] 代表第 i 天时，不持有股票，且处于冷冻期的最大利润
         * dp[i][1][0] 代表第 i 天时，持有股票的最大利润
         * 初始值
         * dp[0][0][0] = 0
         * dp[0][0][1] = 0
         * dp[0][1][0] = - prices[0]
         * 状态转移方程
         * dp[i][0][0] = max{dp[i-1][0][1], dp[i-1][0][0]}
         * dp[i][0][1] = dp[i-1][1][0] + prices[i]
         * dp[i][1][0] = max{dp[i-1][0][0] - prices[i], dp[i-1][1][0]}
         * */
        int len = prices.length;
        int[][][] dp = new int[len][2][2];
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        dp[0][1][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0]);
            dp[i][0][1] = dp[i - 1][1][0] + prices[i];
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
        }
        return Math.max(dp[len - 1][0][0], dp[len - 1][0][1]);
    }

}

/*
* 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

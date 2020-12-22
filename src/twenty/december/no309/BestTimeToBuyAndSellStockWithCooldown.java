package twenty.december.no309;

public class BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        int[] prices1 = new int[]{1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices1));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        /*DP
         * 状态定义：
         * mp[i]为第i天结束之后的最大利益
         * mp[i][0]为持有一只股票
         * mp[i][1]为不持有股票，处于冷冻期中
         * mp[i][2]为不持有股票，不处于冷冻期中
         * 状态转移方程：
         * mp[i][0] = max{mp[i-1][0], mp[i-1][2] - prices[i]}
         * mp[i][1] = mp[i-1][0] + prices[i]
         * mp[i][2] = max{mp[i-1][2], mp[i-1][1]}
         * 初始值：
         * mp[0][0] = -prices[0],
         * mp[0][1] = 0,
         * mp[0][2] = 0
         * 最大值为：
         * maxProfit = max{mp[N-1][1], mp[N-1][2]}
         * */
        final int N = prices.length;
        int[][] mp = new int[N][3];
        mp[0][0] = -prices[0];
        for (int i = 1; i < N; i++) {
            mp[i][0] = Math.max(mp[i - 1][0], mp[i - 1][2] - prices[i]);
            mp[i][1] = mp[i - 1][0] + prices[i];
            mp[i][2] = Math.max(mp[i - 1][2], mp[i - 1][1]);
        }
        return Math.max(mp[N - 1][1], mp[N - 1][2]);
    }
}

/*
* 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

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

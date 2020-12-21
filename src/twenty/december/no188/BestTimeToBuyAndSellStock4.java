package twenty.december.no188;

import java.util.stream.IntStream;

public class BestTimeToBuyAndSellStock4 {
    public static void main(String[] args) {
        int[] prices1 = new int[]{2, 4, 1};
        int k1 = 2;
        int[] prices2 = new int[]{3, 2, 6, 5, 0, 3};
        int k2 = 2;
        int[] prices3 = new int[]{2, 9, 1, 10, 3, 8};
        int k3 = 3;
        System.out.println(maxProfit(k1, prices1));
        System.out.println(maxProfit(k2, prices2));
        System.out.println(maxProfit(k3, prices3));
    }

    private static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k < 1) {
            return 0;
        }
        final int n = prices.length;
        if (k >= n - 1) {
            return maxProfitByGreedy(prices);
        }
        /*
         * DP,状态定义：
         * mp[i][k][j]为第i天，已经交易了k股，手上还有j股情况下的最大利益，本题 j = {0, 1}
         * ps：买进后卖出代表一次交易，可简单的把k理解为卖了k次
         * 状态转移方程：
         * mp[i][k][0] = max{mp[i-1][k-1][1] + prices[i], mp[i-1][k][0]}
         * mp[i][k][1] = max{mp[i-1][k][0] - prices[i], mp[i-1][k][1]}
         * 初始值：
         * mp[0][k][0] = 0
         * mp[0][k][1] = -prices[0]
         * 则n天后最大利益即为：
         * maxProfit = max(mp[n-1][k][0], k = 0,1,2...)
         * */
        int[][][] mp = new int[n][k + 1][2];
        for (int x = 0; x < k + 1; x++) {
            mp[0][x][1] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int x = 0; x < k + 1; x++) {
                mp[i][x][0] = x > 0 ? Math.max(mp[i - 1][x - 1][1] + prices[i], mp[i - 1][x][0]) : mp[i - 1][x][0];
                mp[i][x][1] = Math.max(mp[i - 1][x][0] - prices[i], mp[i - 1][x][1]);
            }
        }
        return IntStream.rangeClosed(0, k).map(x -> mp[n - 1][x][0]).max().orElse(-1);
    }

    private static int maxProfitByGreedy(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

}

/*
* 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 

示例 1：

输入：k = 2, prices = [2,4,1]
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
示例 2：

输入：k = 2, prices = [3,2,6,5,0,3]
输出：7
解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 

提示：

0 <= k <= 10^9
0 <= prices.length <= 1000
0 <= prices[i] <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
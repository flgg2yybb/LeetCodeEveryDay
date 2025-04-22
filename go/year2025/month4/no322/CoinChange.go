package main

import "math"

func main() {
	coins1 := []int{1, 2, 5}
	amount1 := 11
	coins2 := []int{2}
	amount2 := 3
	coins3 := []int{1}
	amount3 := 0
	println(coinChange(coins1, amount1)) // 3
	println(coinChange(coins2, amount2)) // -1
	println(coinChange(coins3, amount3)) // 0
}

/*
DP, times: O(nk), space: O(n), n = amount, k = len(coins)
状态定义：dp[i] 表示组成金额为 i 的最少硬币数量
状态转移方程：dp[i] = min(dp[i-j] + 1), j <= i，j 为 coins 中可选的硬币
初始值：dp[0] = 0
*/
func coinChange(coins []int, amount int) int {
	dp := make([]int, amount+1)
	dp[0] = 0
	for i := 1; i <= amount; i++ {
		dp[i] = math.MaxInt
		for _, j := range coins {
			if i-j >= 0 && dp[i-j] != math.MaxInt {
				dp[i] = min(dp[i], dp[i-j]+1)
			}
		}
	}
	if dp[amount] == math.MaxInt {
		return -1
	}
	return dp[amount]
}

/*
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

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


提示：

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
*/

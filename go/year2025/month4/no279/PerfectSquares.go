package main

import "math"

func main() {
	n1 := 12
	n2 := 13
	n3 := 3
	println(numSquares(n1)) // 3
	println(numSquares(n2)) // 2
	println(numSquares(n3)) // 3
}

/*
DP, times: O(n), space: O(n)
状态定义：dp[i] 表示 和为 i 的完全平方数的最少数量
状态转移方程：dp[i] = min(dp[i-j*j]+1), i >= j*j >= 1
初始值：dp[i] = 0（对于刚好是完全平方数的就好计算，比如 dp[64] = dp[0] + 1, i = 64, j = 8）
*/
func numSquares(n int) int {
	dp := make([]int, n+1)
	dp[0] = 0
	for i := 1; i <= n; i++ {
		dp[i] = math.MaxInt64
		for j := 1; j*j <= i; j++ {
			dp[i] = min(dp[i], dp[i-j*j]+1)
		}
	}
	return dp[n]
}

/*
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

示例 1：
输入：n = 12
输出：3
解释：12 = 4 + 4 + 4

示例 2：
输入：n = 13
输出：2
解释：13 = 4 + 9

提示：
1 <= n <= 104
*/

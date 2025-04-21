package main

func main() {
	n1 := 2
	n2 := 3
	println(climbStairs(n1))
	println(climbStairs(n2))
}

/*
DP, times: O(n), space: O(1)
状态定义：dp[i] 表示爬到第 i 阶的方法数
状态转移方程：dp[i] = dp[i-1] + dp[i-2]
初始状态：dp[0] = 1, dp[1] = 1
状态压缩：实际上计算 dp[i] 时，只需要 dp[i-1] 和 dp[i-2] 的值，所以可以只用两个变量来保存这两个值，空间复杂度可以降到 O(1)
*/
func climbStairs(n int) int {
	ans := 1            // 当 n = 0 或 1 时，ans = 1
	prepre, pre := 1, 1 // pre = dp[i-1], prepre = dp[i-2]
	for i := 2; i <= n; i++ {
		ans = pre + prepre
		prepre, pre = pre, ans // 更新 dp[i-2] 和 dp[i-1]
	}
	return ans
}

/*
DP, times: O(n), space: O(n)
状态定义：dp[i] 表示爬到第 i 阶的方法数
状态转移方程：dp[i] = dp[i-1] + dp[i-2]
初始状态：dp[0] = 1, dp[1] = 1
*/
func climbStairs1(n int) int {
	dp := make([]int, n+1)
	dp[0] = 1
	dp[1] = 1
	for i := 2; i <= n; i++ {
		dp[i] = dp[i-1] + dp[i-2]
	}
	return dp[n]
}

/*
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

示例 1：
输入：n = 2
输出：2
解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶

示例 2：
输入：n = 3
输出：3
解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶

提示：
1 <= n <= 45
*/

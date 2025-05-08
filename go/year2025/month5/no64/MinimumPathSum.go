package main

import "math"

func main() {
	grid1 := [][]int{
		{1, 3, 1},
		{1, 5, 1},
		{4, 2, 1},
	}
	grid2 := [][]int{
		{1, 2, 3},
		{4, 5, 6},
	}
	grid3 := [][]int{
		{1, 2},
		{1, 1},
	}
	println(minPathSum(grid1)) // 7
	println(minPathSum(grid2)) // 12
	println(minPathSum(grid3)) // 3
}

/*
DP, times: O(nm), space: O(nm)
状态定义：dp[i][j] 表示从 (0, 0) 到 (i, j) 的最小路径和
状态转移方程：dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
转移方向：i: 0 -> m-1, j: 0 -> n-1
初始值：dp[0][0] = grid[0][0], dp[0][j] 只能从 dp[0][0] 到达，dp[i][0] 只能从 dp[0][0] 到达
*/
func minPathSum(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			dp[i][j] = grid[i][j]
			prev := math.MaxInt
			if i > 0 {
				prev = min(prev, dp[i-1][j])
			}
			if j > 0 {
				prev = min(prev, dp[i][j-1])
			}
			if prev != math.MaxInt {
				dp[i][j] += prev
			}
		}
	}
	return dp[m-1][n-1]
}

/*
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。

示例 1：
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。

示例 2：
输入：grid = [[1,2,3],[4,5,6]]
输出：12

提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
*/

package main

func main() {
	m1, n1 := 3, 7
	m2, n2 := 3, 2
	m3, n3 := 16, 16
	println(uniquePaths(m1, n1)) // 28
	println(uniquePaths(m2, n2)) // 3
	println(uniquePaths(m3, n3)) // 155117520
}

/*
DP - 状态压缩, times: O(mn), space: O(m)
状态定义：dp[i] 表示到达 (i, j) 的路径数, i < m, j < n
状态转移方程：dp[i] += dp[i-1]
状态转移方向，j 从 0 到 n-1，i 从 0 到 m-1
初始值：dp[0] = 1
哨兵处理：为了减少边界判断，i-1 > 0，我们在数组最上测添加一个元素，初始值为 0，同时起点为 dp[1]，终点为 dp[m]
*/
func uniquePaths(m int, n int) int {
	dp := make([]int, m+1) // 压缩了列的空间
	dp[1] = 1
	for j := 1; j <= n; j++ { // 滚动列
		for i := 1; i <= m; i++ {
			dp[i] += dp[i-1]
		}
	}
	return dp[m]
}

/*
DP, times: O(mn), space: O(mn)
状态定义：dp[i][j] 表示到达 (i, j) 的路径数, i < m, j < n
状态转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
状态转移方向，i 从 0 到 m-1, j 从 0 到 n-1
初始值：dp[0][0] = 1
哨兵处理：为了减少边界判断，i-1 > 0, j-1 > 0，我们在数组最左侧和最上测添加一行和一列，初始值为 0，同时起点为 dp[1][1]，终点为 dp[m][n]
*/
func uniquePaths2(m int, n int) int {
	dp := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		dp[i] = make([]int, n+1)
	}
	dp[1][1] = 1
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			dp[i][j] += dp[i-1][j] + dp[i][j-1]
		}
	}
	return dp[m][n]
}

/*
【整形越界】Math, times: O(m), space: O(1)
思路：机器人从左上角到右下角，需要经过 m-1 次向下和 n-1 次向右的移动，所以移动次数相当于 m-1 次向下 和 n-1次向右 排列组合
结果 = c(m+n-2)(m-1) 从 m+n-2 中选择 m-1 = c(m+n-2)(n-1) 从 m+2-2 中选择 n-1
//	= (m+n-2) * .... * (n-1) / (m-1)!
*/
func uniquePaths1(m int, n int) int {
	top := 1
	bottom := 1
	row := min(m, n) - 1
	col := m + n - 2
	for i := row; i > 0; i-- {
		top *= col  // (m+n-2) * (m+n-1) * ... * (n-1)
		bottom *= i // (m-1) * m * ... * 1
		col--
	}
	return top / bottom
}

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？

示例 1：
输入：m = 3, n = 7
输出：28
示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下
示例 3：

输入：m = 7, n = 3
输出：28
示例 4：

输入：m = 3, n = 3
输出：6


提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 109 */

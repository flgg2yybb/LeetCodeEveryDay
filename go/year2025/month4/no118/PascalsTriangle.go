package main

import "fmt"

func main() {
	numRows1 := 5
	numRows2 := 1
	println(fmt.Sprintf("%+v", generate(numRows1)))
	println(fmt.Sprintf("%+v", generate(numRows2)))
}

/*
DP, times: O(n^2), space: O(n^2)
状态定义：dp[i] 就表示第 i 行的杨辉三角
状态转移方程： dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
初始值：dp[0][0] = 1
*/
func generate(numRows int) [][]int {
	dp := make([][]int, 0, numRows)
	dp = append(dp, []int{1}) // 第一行
	for i := 1; i < numRows; i++ {
		dp = append(dp, make([]int, i+1))
		for j := 0; j < len(dp[i]); j++ {
			left, right := 0, 0 // 上一行左边的值，上一行右边的值
			if j-1 >= 0 {
				left = dp[i-1][j-1] // 上一行的第一个值
			}
			if j < len(dp[i-1]) {
				right = dp[i-1][j]
			}
			dp[i][j] = left + right
		}
	}
	return dp
}

/*
给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
在「杨辉三角」中，每个数是它左上方和右上方的数的和。

示例 1:
输入: numRows = 5
输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

示例 2:
输入: numRows = 1
输出: [[1]]

提示:
1 <= numRows <= 30
*/

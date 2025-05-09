package main

func main() {
	text11 := "abcde"
	text12 := "ace"
	text21 := "abc"
	text22 := "abc"
	text31 := "abc"
	text32 := "def"
	println(longestCommonSubsequence(text11, text12)) // 3
	println(longestCommonSubsequence(text21, text22)) // 3
	println(longestCommonSubsequence(text31, text32)) // 0
}

/*
DP, times: O(mn), space: O(mn)
状态定义：dp[i][j] 表示为 text1[0..i] 和 text2[0..j] 的最长公共子序列长度
状态转移方程：
  - if text1[i] == text2[j], dp[i][j] = dp[i-1][j-1] + 1
  - else, dp[i][j] = max(dp[i-1][j], dp[i][j-1])

状态初始化：
  - dp[0][0] = text1[0] == text2[0] ? 1 : 0
  - dp[i][0] = (dp[i-1][0] > 0 || text1[i] == text2[0]) ? 1 : 0
  - dp[0][j] = (dp[0][j-1] > 0 || text1[0] == text2[j]) ? 1 : 0
*/
func longestCommonSubsequence(text1 string, text2 string) int {
	m, n := len(text1), len(text2)
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	// 初始化
	if text1[0] == text2[0] {
		dp[0][0] = 1
	}
	for i := 1; i < m; i++ {
		if dp[i-1][0] > 0 || text1[i] == text2[0] {
			dp[i][0] = 1
		}
	}
	for j := 1; j < n; j++ {
		if dp[0][j-1] > 0 || text1[0] == text2[j] {
			dp[0][j] = 1
		}
	}
	// 状态转移
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if text1[i] == text2[j] {
				dp[i][j] = dp[i-1][j-1] + 1
			} else {
				dp[i][j] = max(dp[i-1][j], dp[i][j-1])
			}
		}
	}
	return dp[m-1][n-1]
}

/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

示例 1：
输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace" ，它的长度为 3 。

示例 2：
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。

示例 3：
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。

提示：
1 <= text1.length, text2.length <= 1000
text1 和 text2 仅由小写英文字符组成。
*/

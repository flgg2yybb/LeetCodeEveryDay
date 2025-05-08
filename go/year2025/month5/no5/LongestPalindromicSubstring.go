package main

func main() {
	s1 := "babad"
	s2 := "cbbd"
	println(longestPalindrome(s1)) // "bab" or "aba"
	println(longestPalindrome(s2)) // "bb"
}

// 中心拓展法，times: O(n^2)，space: O(1)
// 枚举中心点，向两边扩展
func longestPalindrome(s string) string {
	start := 0
	end := 0
	for i := 0; i < len(s); i++ {
		// 以s[i]为中心的回文串
		len1 := expandAroundCenter(s, i, i)
		// 以s[i]和s[i+1]为中心的回文串
		len2 := expandAroundCenter(s, i, i+1)
		maxLen := max(len1, len2)
		if maxLen > end-start {
			start = i - (maxLen-1)/2 // 考虑奇偶数
			end = start + maxLen - 1
		}
	}
	return s[start : end+1]
}

func expandAroundCenter(s string, left int, right int) int {
	for left >= 0 && right < len(s) && s[left] == s[right] {
		left--
		right++
	}
	// 此时left和right都不匹配或者越界了
	return right - left - 1
}

/*
DP, times: O(n^2), space: O(n^2)
状态定义：dp[i][j]表示s[i:j]是否是回文串
状态转移方程：dp[i][j] = dp[i+1][j-1] && s[i] == s[j], i < j，只需要填上半表格
转移方向：左下到右上，len: 2 -> len(s), i: 0 -> len(s), j = len - i - 1
初始化：dp[i][i] = true
*/
func longestPalindrome1(s string) string {
	dp := make([][]bool, len(s))
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]bool, len(s))
		dp[i][i] = true // 单个字符是回文串
	}
	longestStart := 0
	longestEnd := 0
	for l := 2; l <= len(s); l++ { // 长度从小往大拓展
		for i, j := 0, l-1; j < len(s); i, j = i+1, j+1 { // l = j - i + 1
			if l >= 3 {
				dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
			} else {
				dp[i][j] = s[i] == s[j]
			}
			if dp[i][j] && j-i > longestEnd-longestStart { // 更新最长回文子串
				longestStart = i
				longestEnd = j
			}
		}
	}
	return s[longestStart : longestEnd+1]
}

/*
给你一个字符串 s，找到 s 中最长的 回文 子串。

示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。

示例 2：
输入：s = "cbbd"
输出："bb"

提示：
1 <= s.length <= 1000
s 仅由数字和英文字母组成
*/

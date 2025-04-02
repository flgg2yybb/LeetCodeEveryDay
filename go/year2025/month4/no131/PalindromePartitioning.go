package main

import (
	"fmt"
)

func main() {
	s1 := "aaa"
	s2 := "a"
	println(fmt.Sprintf("%v", partition(s1))) // [["a","a","b"],["aa","b"]]
	println(fmt.Sprintf("%v", partition(s2))) // [["a"]]
}

// 记忆化搜索 + 回溯， times: O(n * 2^n), space: O(n * 2^n)
func partition(s string) [][]string {
	ans := make([][]string, 0)
	p := make([]string, 0)
	cache := make([][]int, len(s))
	for i := 0; i < len(s); i++ {
		cache[i] = make([]int, len(s))
	}
	memorySearch(&ans, &p, s, cache, 0)
	return ans
}

// cache: 0 未计算，1 是回文串，-1 不是回文串
func memorySearch(ans *[][]string, p *[]string, s string, cache [][]int, pos int) {
	if pos == len(s) {
		temp := make([]string, 0, len(*p))
		for _, c := range *p {
			temp = append(temp, c)
		}
		*ans = append(*ans, temp)
		return
	}
	for i := pos; i < len(s); i++ {
		if isPalindromeByCache(s, cache, pos, i) {
			*p = append(*p, s[pos:i+1])
			memorySearch(ans, p, s, cache, i+1)
			*p = (*p)[:len(*p)-1]
		}
	}
}

func isPalindromeByCache(s string, cache [][]int, start int, end int) bool {
	left, right := start, end
	if cache[start][end] != 0 {
		return cache[start][end] == 1
	}
	for left < right {
		if s[left] != s[right] {
			cache[start][end] = -1
			return false
		}
		left++
		right--
	}
	cache[start][end] = 1
	return true
}

// DP + 回溯， times: O(n * 2^n), space: O(n * 2^n)
func partition1(s string) [][]string {
	isPalindrome := dp(s)
	ans := make([][]string, 0)
	p := make([]string, 0)
	backtrack(&ans, &p, s, isPalindrome, 0)
	return ans
}

func backtrack(ans *[][]string, p *[]string, s string, isPalindrome [][]bool, pos int) {
	if pos == len(s) {
		temp := make([]string, 0, len(*p))
		for _, c := range *p {
			temp = append(temp, c)
		}
		*ans = append(*ans, temp)
		return
	}
	for i := pos; i < len(s); i++ {
		if isPalindrome[pos][i] {
			*p = append(*p, s[pos:i+1])
			backtrack(ans, p, s, isPalindrome, i+1)
			*p = (*p)[:len(*p)-1]
		}
	}
}

// dp, times: O(n^2), space: O(n^2)
// 状态定义：dp[i][j] 表示 s[i:j] 是否是回文串，i <= j，只需要填表格左下半部分
// 状态转移方程：dp[i][j] = dp[i+1][j-1] && s[i] == s[j]，需要从右上到左下遍历
// 初始化：dp[i][i] = true
func dp(s string) (dp [][]bool) {
	dp = make([][]bool, len(s))
	for i := 0; i < len(s); i++ {
		dp[i] = make([]bool, len(s))
		dp[i][i] = true
	}
	for i := 0; i < len(s); i++ {
		for j := i - 1; j >= 0; j-- {
			dp[j][i] = s[j] == s[i] && (i-j == 1 || dp[j+1][i-1])
		}
	}
	return dp
}

/*
给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

示例 1：
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]

示例 2：
输入：s = "a"
输出：[["a"]]

提示：
1 <= s.length <= 16
s 仅由小写英文字母组成
*/

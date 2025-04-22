package main

func main() {
	s1 := "leetcode"
	wordDict1 := []string{"leet", "code"}
	s2 := "applepenapple"
	wordDict2 := []string{"apple", "pen"}
	s3 := "catsandog"
	wordDict3 := []string{"cats", "dog", "sand", "and", "cat"}
	println(wordBreak(s1, wordDict1)) // true
	println(wordBreak(s2, wordDict2)) // true
	println(wordBreak(s3, wordDict3)) // false
	s := "aa"
	wordDict := []string{"a"}
	println(wordBreak(s, wordDict)) // true
}

/*
DP, times: O(n^2), space: O(n)
状态定义：dp[i] 表示从 0 到 i-1 的子串是否可以被 wordDict 中的单词拼接而成。
状态转移方程：满足以下条件时，有 dp[i] = true
  - dp[j] = true 并且 s[j][i] ∈ wordDict，j < i

初始值：dp[0] = true
最终结果为：dp[n]
*/
func wordBreak(s string, wordDict []string) bool {
	wordSet := make(map[string]bool)
	for _, word := range wordDict {
		wordSet[word] = true
	}
	n := len(s)
	dp := make([]bool, n+1)
	dp[0] = true // 哨兵，方便处理 dp[i] ∈ wordDict 的情况
	for i := 1; i <= n; i++ {
		for j := 0; j < i; j++ {
			if dp[j] && wordSet[s[j:i]] { // s[j:i] 取的是 [j:i) 的字串
				dp[i] = true
				break
			}
		}
	}
	return dp[n]
}

/*
DP, times: O(n^3), space: O(n^2)
状态定义：dp[i][j] 表示从 i 到 j 的子串是否可以被 wordDict 中的单词拼接而成。
状态转移方程：满足以下任一条件时，有 dp[i][j] = true
  - dp[i][k] 和 dp[k][j] 都为 true，其中: i < j, k ∈ [i, j) 时
  - s[i][j] ∈ wordDict

状态转移方向：dp 表格只需填上半区，从左下填到右上（对于 s 的子串，从长度为 1 一直延长到长度为 n）
最终结果为：dp[0][n-1]
*/
func wordBreak1(s string, wordDict []string) bool {
	wordSet := make(map[string]bool)
	for _, word := range wordDict {
		wordSet[word] = true
	}
	n := len(s)
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
	}
	for length := 1; length <= n; length++ { // 长度从小匹配到大
		for i := 0; i+length-1 < n; i++ {
			j := i + length - 1    // i <= j <= n-1
			if wordSet[s[i:j+1]] { // s[i:j+1] 匹配的是 [i,j]
				dp[i][j] = true
				continue
			}
			for k := i; k < j; k++ {
				if dp[i][k] && dp[k+1][j] { // dp[i][k] 和 dp[k+1][j] 已经匹配过了，一定有结果
					dp[i][j] = true
					break
				}
			}
		}
	}
	return dp[0][n-1]
}

/*
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。

示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     注意，你可以重复使用字典中的单词。

示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false

提示：
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s 和 wordDict[i] 仅由小写英文字母组成
wordDict 中的所有字符串 互不相同
*/

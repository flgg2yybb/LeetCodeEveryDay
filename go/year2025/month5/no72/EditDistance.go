package main

func main() {
	word11 := "horse"
	word12 := "ros"
	word21 := "intention"
	word22 := "execution"
	word31 := "sea"
	word32 := "eat"
	word41 := "s"
	word42 := "e"
	println(minDistance(word11, word12)) // 3
	println(minDistance(word21, word22)) // 5
	println(minDistance(word31, word32)) // 2
	println(minDistance(word41, word42)) // 1
}

/*
DP, times: O(mn), space: O(mn)
状态定义：dp[i][j] 表示 word1[0:i-1] 转换成 word2[0:j-1] 的最小操作数
状态转移方程：
  - if word1[i-1] == word2[j-1]:
    dp[i][j] = dp[i-1][j-1]
  - else:		// 可选择，插入，删除，替换
    dp[i][j] = min(

插入		dp[i][j-1],
删除		dp[i-1][j],
替换		dp[i-1][j-1]

	) + 1			操作数

目标：我们希望让 word1[0:i-1] 变成 word2[0:j-1]
解释：
 1. 为什么插入是从 dp[i][j-1] 转变过来，而不是 dp[i-1][j]？
    如果我们在 word1 的末尾插入 word2[j-1] 这个字符，就能“追上” word2 的这个字符。
    所以我们思考的是：先把 word1[0..i-1] 转换成 word2[0..j-2]，也就是：dp[i][j-1]，然后再插入 word2[j-1]。最终就是：dp[i][j] = dp[i][j-1] + 1

状态初始化：
  - dp[0][0] = 0
  - dp[i][0] = i
  - dp[0][j] = j
*/
func minDistance(word1 string, word2 string) int {
	m, n := len(word1), len(word2)
	if m == 0 || n == 0 {
		return max(m, n)
	}
	dp := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		dp[i] = make([]int, n+1)
	}
	// 初始化
	dp[0][0] = 0
	for i := 1; i <= m; i++ {
		dp[i][0] = i
	}
	for j := 1; j <= n; j++ {
		dp[0][j] = j
	}
	// 状态转移
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if word1[i-1] == word2[j-1] {
				dp[i][j] = dp[i-1][j-1]
			} else {
				dp[i][j] = min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) + 1
			}
		}
	}
	return dp[m][n]
}

/*
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
你可以对一个单词进行如下三种操作：
	* 插入一个字符
	* 删除一个字符
	* 替换一个字符

示例 1：
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

示例 2：
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

提示：
0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成
*/

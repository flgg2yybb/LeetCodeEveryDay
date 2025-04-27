package main

func main() {
	s1 := "(()"
	s2 := ")()())"
	s3 := "(()"
	println(longestValidParentheses(s1)) // 2
	println(longestValidParentheses(s2)) // 4
	println(longestValidParentheses(s3)) // 2
}

/*
 * DP, time is O(n), space is O(n)
 * 状态定义：
 * dp[i] 表示以当前 i 结尾的字符串中最长有效括号子串的长度
 * 状态转义方程：
 * if s[i] = '('
 *       dp[i] = 0
 * else s[i] = ')' && i - dp[i-1] - 1 >= 0 && s[i - dp[i-1] - 1] == '('
 *       dp[i] = dp[i-1] + 2
 * 注意：
 *   若当前已配对成功，当前范围的前一个元素也能配对成功（dp[i - dp[i-1] - 2]存在且大于0），则还需加上前面的长度
 *   因i - dp[i-1] - 2配对不成功时，dp[i - dp[i-1] - 2] = 0，则可统一处理
 *   if i - dp[i-1] -2 >= 0
 *       dp[i] += dp[i - dp[i-1] -2]
 * 例子： ()(())
 *        ^   ^
 *        2 + 4 = 6
 * */
func longestValidParentheses(s string) int {
	if len(s) < 2 {
		return 0
	}
	dp := make([]int, len(s))
	maxLen := 0
	for i := 1; i < len(s); i++ {
		if s[i] == ')' && i-dp[i-1]-1 >= 0 && s[i-dp[i-1]-1] == '(' {
			dp[i] = dp[i-1] + 2
			if i-dp[i-1]-2 >= 0 {
				dp[i] += dp[i-dp[i-1]-2]
			}
			maxLen = max(maxLen, dp[i])
		}
	}
	return maxLen
}

/*
【实际上就是暴力】DP - 状态压缩, times: O(n^2), space: O(n)
状态定义：dp[j] 表示字符串 s[i:j] 中如果存在有效括号需要匹配的右括号数量，i 为行数，代表子串起点，i < j
  - 如果 dp[j] = -1，表示 s[i:j] ')' 数量大于 '('，已无法匹配
  - 如果 dp[j] = 0，表示 s[i:j] 中恰好匹配
  - 如果 dp[j] = x，表示 s[i:j] 中 '(' 数量大于 ')'，需要匹配的右括号数量为 x

状态转移方程：
* 如果 dp[j-1] = -1，表示 s[i:j] ')' 数量大于 '('，已无法匹配，则 dp[j] = -1

* 如果 dp[j-1] = 0，表示 s[i:j-1] 中恰好匹配
  - 如果 s[j] = '('，则 dp[j] = 1
  - 如果 s[j] = ')'，则 dp[j] = -1

* 如果 dp[j-1] = x，表示 s[i:j-1] 中 '(' 数量大于 ')'，需要匹配的右括号数量为 x
  - 如果 s[j] = '('，则 dp[j] = x+1
  - 如果 s[j] = ')'，则 dp[j] = x-1

状态转移方向：
  - 因为需要保证 i <= j，因此只需要填 dp 表格的上三角部分即可，i 从 0 到 n-1, j 从 i+1 到 n-1

初始值：
  - s[j] = '('，则 dp[j] = 1
  - s[j] = ')'，则 dp[j] = -1

结果：遍历dp[j]，如果 dp[j] = 0，则表示 s[i:j] 中恰好匹配，更新 maxLen
*/
func longestValidParentheses2(s string) int {
	dp := make([]int, len(s))
	// 状态转移
	maxLen := 0
	for i := 0; i < len(s); i++ {
		// 初始值
		if s[i] == '(' {
			dp[i] = 1
		} else {
			dp[i] = -1
		}
		for j := i + 1; j < len(s); j++ {
			if dp[j-1] == -1 {
				dp[j] = -1
			} else if dp[j-1] == 0 {
				if s[j] == '(' {
					dp[j] = 1
				} else {
					dp[j] = -1
				}
			} else { // dp[j-1] = x
				if s[j] == '(' {
					dp[j] = dp[j-1] + 1
				} else {
					dp[j] = dp[j-1] - 1
				}
			}
			// 更新 maxLen
			if dp[j] == 0 {
				maxLen = max(maxLen, j-i+1)
			}
		}
	}
	return maxLen
}

/*
【fatal error: runtime: out of memory】
DP, times: O(n^2), space: O(n^2)
状态定义：dp[i][j] 表示字符串 s[i:j] 中如果存在有效括号需要匹配的右括号数量，i < j
  - 如果 dp[i][j] = -1，表示 s[i:j] ')' 数量大于 '('，已无法匹配
  - 如果 dp[i][j] = 0，表示 s[i:j] 中恰好匹配
  - 如果 dp[i][j] = x，表示 s[i:j] 中 '(' 数量大于 ')'，需要匹配的右括号数量为 x

状态转移方程：
* 如果 dp[i][j-1] = -1，表示 s[i:j] ')' 数量大于 '('，已无法匹配，则 dp[i][j] = -1

* 如果 dp[i][j-1] = 0，表示 s[i:j-1] 中恰好匹配
  - 如果 s[j] = '('，则 dp[i][j] = 1
  - 如果 s[j] = ')'，则 dp[i][j] = -1

* 如果 dp[i][j-1] = x，表示 s[i:j-1] 中 '(' 数量大于 ')'，需要匹配的右括号数量为 x
  - 如果 s[j] = '('，则 dp[i][j] = x+1
  - 如果 s[j] = ')'，则 dp[i][j] = x-1

状态转移方向：
  - 因为需要保证 i <= j，因此只需要填 dp 表格的上三角部分即可，i 从 0 到 n-1，j 从 i+1 到 n-1

初始值：
  - s[i] = '('，则 dp[i][i] = 1
  - s[i] = ')'，则 dp[i][i] = -1

结果：遍历dp[i][j]，如果 dp[i][j] = 0，则表示 s[i:j] 中恰好匹配，更新 maxLen
*/
func longestValidParentheses1(s string) int {
	dp := make([][]int, len(s))
	for i := 0; i < len(s); i++ {
		dp[i] = make([]int, len(s))
		// 初始值
		if s[i] == '(' {
			dp[i][i] = 1
		} else {
			dp[i][i] = -1
		}
	}
	// 状态转移
	maxLen := 0
	for i := 0; i < len(s); i++ {
		for j := i + 1; j < len(s); j++ {
			if dp[i][j-1] == -1 {
				dp[i][j] = -1
			} else if dp[i][j-1] == 0 {
				if s[j] == '(' {
					dp[i][j] = 1
				} else {
					dp[i][j] = -1
				}
			} else { // dp[i][j-1] = x
				if s[j] == '(' {
					dp[i][j] = dp[i][j-1] + 1
				} else {
					dp[i][j] = dp[i][j-1] - 1
				}
			}
			// 更新 maxLen
			if dp[i][j] == 0 {
				maxLen = max(maxLen, j-i+1)
			}
		}
	}
	return maxLen
}

/*
给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

示例 1：
输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"

示例 2：
输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"

示例 3：
输入：s = ""
输出：0

提示：
0 <= s.length <= 3 * 104
s[i] 为 '(' 或 ')'
*/

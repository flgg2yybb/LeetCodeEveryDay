package main

import "fmt"

func main() {
	s1 := "ababcbacadefegdehijhklij"
	s2 := "eccbbbbdec"
	println(fmt.Sprintf("%+v", partitionLabels(s1))) // [9,7,8]
	println(fmt.Sprintf("%+v", partitionLabels(s2))) // [10]
}

// Greedy, times: O(n), space: O(1)
// 思路：先统计一下字符串中，每个字符出现的最后一个位置
// 遍历字符串，维护区间边界（开始和结束位置），如果区间内所有字母的出现位置都在区间内，则当前区间可以划分，继续划分下一个
// 具体实现：从单个字母开始，此时 start == end，维护一个区间内字母的最远下标 newEnd
//   - if end == newEnd，则说明当前区间可以划分，更新 start 和 end
//   - if ned < newEnd，则说明当前区间还不能划分，则向 newEnd 继续扩展 end，同时更新 newEnd，直到 end == newEnd
func partitionLabels(s string) []int {
	// 统计每个字母出现的最后一个位置
	lastIndex := make([]int, 26)
	for i := 0; i < len(s); i++ {
		c := s[i] - 'a'
		lastIndex[c] = i
	}
	// 划分区间
	ans := make([]int, 0)
	start, end := 0, 0
	for end < len(s) {
		c := s[end]
		newEnd := lastIndex[c-'a']
		for end < newEnd {
			end++
			c = s[end]
			newEnd = max(newEnd, lastIndex[c-'a'])
		}
		// end == newEnd
		ans = append(ans, end-start+1)
		start = end + 1
		end = start
	}
	return ans
}

/*
给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
返回一个表示每个字符串片段的长度的列表。



示例 1：
输入：s = "ababcbacadefegdehijhklij"
输出：[9,7,8]
解释：
划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
示例 2：

输入：s = "eccbbbbdec"
输出：[10]


提示：

1 <= s.length <= 500
s 仅由小写英文字母组成
*/

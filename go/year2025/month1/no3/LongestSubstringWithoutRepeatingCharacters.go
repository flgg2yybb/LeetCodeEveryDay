package main

import (
	"strings"
)

// Sliding Window, time: O(n), space: O(n)
func lengthOfLongestSubstring(s string) int {
	ans := 0
	left := -1
	right := 0
	set := make(map[string]int) // k：char，v：last index
	chars := strings.Split(s, "")
	for right < len(chars) {
		if pos, ok := set[chars[right]]; ok { // exist duplicated char
			if pos > left { // NOTE: need add this if statement since left only increase
				left = pos // move left to the last index of the duplicated char
			}
		}
		cur := right - left
		if cur > ans {
			ans = cur
		}
		set[chars[right]] = right
		right++
	}
	return ans
}

func main() {
	s1 := "abcabcbb"
	s2 := "bbbbb"
	s3 := "pwwkew"
	s4 := "abba"
	println(lengthOfLongestSubstring(s1))
	println(lengthOfLongestSubstring(s2))
	println(lengthOfLongestSubstring(s3))
	println(lengthOfLongestSubstring(s4))
}

/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长
子串
 的长度。



示例 1:

输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成
*/

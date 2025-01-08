package main

import (
	"fmt"
	"strings"
)

type AnagramMap struct {
	// key 为 char， value 为 char 需要的个数
	// 初始化时，对需要匹配的字符串 p 字符依次放入 map 中，同时以 “负数” 标记 p 中字符的个数
	// 后续遍历字符串 s 时，每次取长度等同于 p 的子串 k，将字符依次放入 map 中，同时以 “正数” 标记 k 中字符的个数
	// 当某个 key（字符）的 value == 0 时，将该 key 从 map 中移除
	// 如果 map 中无任何 key（len（dic） == 0）则代表当前子串 k 是 p 的异位词
	dic map[string]int
}

func NewAnagramMap(p string) *AnagramMap {
	anagramMap := &AnagramMap{dic: make(map[string]int)}
	chars := strings.Split(p, "")
	for _, char := range chars {
		anagramMap.Pop(char)
	}
	return anagramMap
}

func (a *AnagramMap) operate(char string, value int) {
	if _, ok := a.dic[char]; !ok {
		a.dic[char] = 0
	}
	a.dic[char] += value
	if a.dic[char] == 0 {
		delete(a.dic, char)
	}
}

func (a *AnagramMap) Push(char string) {
	a.operate(char, 1)
}

func (a *AnagramMap) Pop(char string) {
	a.operate(char, -1)
}

func (a *AnagramMap) IsAnagram() bool {
	return len(a.dic) == 0
}

// Sliding Window, time: O(n), space: O(1)
func findAnagrams(s string, p string) []int {
	result := make([]int, 0)
	if len(s) < len(p) {
		return result
	}
	anagramMap := NewAnagramMap(p)
	chars := strings.Split(s, "")
	for end, char := range chars {
		anagramMap.Push(char)
		if end < len(p)-1 {
			// 当前 [start, end] 长度小于子串 p 的长度，必然不存在异位词
			continue
		}
		start := end - len(p) + 1
		if start > 0 {
			// 需要保证 anagramMap 中始终仅包含 [start, end] 的字符
			anagramMap.Pop(chars[start-1])
		}
		if anagramMap.IsAnagram() {
			result = append(result, start)
		}
	}
	return result
}

func main() {
	s1 := "cbaebabacd"
	p1 := "abc"
	s2 := "abab"
	p2 := "ab"
	println(fmt.Sprintf("%+v", findAnagrams(s1, p1)))
	println(fmt.Sprintf("%+v", findAnagrams(s2, p2)))
}

/*
给定两个字符串 s 和 p，找到 s 中所有 p 的
异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。



示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。


提示:

1 <= s.length, p.length <= 3 * 104
s 和 p 仅包含小写字母
*/

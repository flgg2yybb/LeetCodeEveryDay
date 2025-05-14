package main

import (
	"fmt"
)

func main() {
	strs1 := []string{"eat", "tea", "tan", "ate", "nat", "bat"}
	strs2 := []string{""}
	strs3 := []string{"a"}
	println(fmt.Sprintf("%v", groupAnagrams(strs1)))
	println(fmt.Sprintf("%v", groupAnagrams(strs2)))
	println(fmt.Sprintf("%v", groupAnagrams(strs3)))
}

// HashMap, times: O(nk), space: O(nk)
func groupAnagrams(strs []string) [][]string {
	ans := make(map[string][]string)
	hash := func(s string) (key string) {
		counts := make([]int, 26)
		for _, c := range s {
			counts[c-'a']++
		}
		for _, count := range counts {
			key += fmt.Sprintf("%d#", count)
		}
		return key
	}
	for _, str := range strs {
		hashKey := hash(str)
		if _, ok := ans[hashKey]; !ok {
			ans[hashKey] = make([]string, 0)
		}
		ans[hashKey] = append(ans[hashKey], str)
	}
	result := make([][]string, 0)
	for _, v := range ans {
		result = append(result, v)
	}
	return result
}

/*
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

示例 1:
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

示例 2:
输入: strs = [""]
输出: [[""]]

示例 3:
输入: strs = ["a"]
输出: [["a"]]

提示：
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母
*/

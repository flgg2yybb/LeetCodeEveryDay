package main

import (
	"fmt"
	"sort"
	"strings"
)

// count string's char as hash key
// time: O(nm), space: O(nm)
func groupAnagrams(strs []string) [][]string {
	dic := make(map[string][]string, 0)
	hash := func(s string) string { // O(m)
		// count as alphabet
		alphabet := make([]int, 26)
		for _, c := range s { // O(m)
			alphabet[int(c-'a')]++
		}
		// hash from alphabet by order
		var chars []string
		for index, count := range alphabet { // O(1)
			if count > 0 {
				chars = append(chars, fmt.Sprintf("%d%s", count, index+'a'))
			}
		}
		return strings.Join(chars, "")
	}
	for _, str := range strs {
		key := hash(str)
		if _, ok := dic[key]; !ok {
			dic[key] = make([]string, 0)
		}
		dic[key] = append(dic[key], str)
	}
	result := make([][]string, 0, len(dic))
	for _, strArr := range dic {
		result = append(result, strArr)
	}
	return result
}

// sort string as hash key
// time: O(n * mlog(m)), space: O(nm)
func groupAnagrams1(strs []string) [][]string {
	strSorter := func(s string) string {
		// 将字符串转换为切片
		chars := strings.Split(s, "")
		// 使用 sort.Strings 对切片进行排序
		sort.Strings(chars)
		// 将排序后的切片重新组合为字符串
		return strings.Join(chars, "")
	}
	dic := make(map[string][]string, 0)
	for _, str := range strs {
		key := strSorter(str)
		if _, ok := dic[key]; !ok {
			dic[key] = make([]string, 0)
		}
		dic[key] = append(dic[key], str)
	}
	result := make([][]string, 0, len(dic))
	for _, strArr := range dic {
		result = append(result, strArr)
	}
	return result
}

func main() {
	strs1 := []string{"eat", "tea", "tan", "ate", "nat", "bat"}
	strs2 := []string{""}
	strs3 := []string{"a"}
	println(fmt.Sprintf("%+v", groupAnagrams(strs1)))
	println(fmt.Sprintf("%+v", groupAnagrams(strs2)))
	println(fmt.Sprintf("%+v", groupAnagrams(strs3)))
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

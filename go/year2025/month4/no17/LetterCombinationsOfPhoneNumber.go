package main

import "fmt"

func main() {
	digits1 := "23"
	res1 := letterCombinations(digits1)
	println(fmt.Sprintf("len: %d, %+v", len(res1), res1))
	digits2 := ""
	res2 := letterCombinations(digits2)
	println(fmt.Sprintf("len: %d, %+v", len(res2), res2))
	digits3 := "2"
	res3 := letterCombinations(digits3)
	println(fmt.Sprintf("len: %d, %+v", len(res3), res3))

}

var phoneMap = map[string][]string{
	"2": {"a", "b", "c"},
	"3": {"d", "e", "f"},
	"4": {"g", "h", "i"},
	"5": {"j", "k", "l"},
	"6": {"m", "n", "o"},
	"7": {"p", "q", "r", "s"},
	"8": {"t", "u", "v"},
	"9": {"w", "x", "y", "z"},
}

func letterCombinations(digits string) []string {
	ans := make([]string, 0)
	backtrack(digits, &ans, "", 0)
	return ans
}

// backtrack, times: O(3^n), times: O(n)
func backtrack(digits string, ans *[]string, p string, i int) {
	if i == len(digits) {
		if len(digits) > 0 {
			*ans = append(*ans, p)
		}
		return
	}
	for _, letter := range phoneMap[string(digits[i])] {
		backtrack(digits, ans, p+letter, i+1)
	}
}

/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例 1：
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

示例 2：
输入：digits = ""
输出：[]

示例 3：
输入：digits = "2"
输出：["a","b","c"]


提示：
0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。
*/

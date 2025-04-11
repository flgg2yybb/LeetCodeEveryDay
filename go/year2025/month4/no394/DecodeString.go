package main

func main() {
	println(decodeString1("3[a]2[bc]"))     // "aaabcbc"
	println(decodeString1("3[a2[c]]"))      // "accaccacc"
	println(decodeString1("2[abc]3[cd]ef")) // "abcabccdcdcdef"
	println(decodeString1("abc3[cd]xyz"))   // "abccdcdcdxyz"
}

// Stack, times: O(n), space: O(n)
func decodeString(s string) string {
	numStack := make([]int, 0)
	resStack := make([]string, 0)
	num := 0
	res := "" // 指向当前（括号内）的字符串
	for i := 0; i < len(s); i++ {
		if s[i] <= '9' && s[i] >= '0' { // 可能会存在 10, 20, 30 等数字
			num = num*10 + int(s[i]-'0')
		} else if s[i] == '[' { // 遇到左括号，当前的数字，字符串需要及时存入栈
			numStack = append(numStack, num)
			num = 0
			resStack = append(resStack, res)
			res = ""
		} else if s[i] == ']' { // 遇到右括号，当前的字符串需要重复 numStack 顶部的数字次
			temp := ""
			repeat := numStack[len(numStack)-1]
			numStack = numStack[:len(numStack)-1]
			for k := 0; k < repeat; k++ {
				temp += res
			}
			res = resStack[len(resStack)-1] + temp // 还需将之前的字符串拼接上
			resStack = resStack[:len(resStack)-1]
		} else { // 普通字符，直接拼接到当前字符串上
			res += string(s[i])
		}
	}
	return res
}

// DFS, times: O(n), space: O(n)
var index int

func decodeString1(s string) string {
	index = 0
	return dfs(s)
}

func dfs(s string) (res string) {
	if index >= len(s) {
		return
	}
	num := 0
	for index < len(s) {
		if s[index] >= '0' && s[index] <= '9' { // 可能会存在 10, 20, 30 等数字
			num = num*10 + int(s[index]-'0')
		} else if s[index] == '[' {
			index++
			subfix := dfs(s) // 此处 dfs 只会返回当前括号内的字符串
			for k := 0; k < num; k++ {
				res += subfix
			}
			num = 0
		} else if s[index] == ']' {
			break
		} else { // 字母
			res += string(s[index])
		}
		index++
	}
	return res
}

/*
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。



示例 1：
输入：s = "3[a]2[bc]"
输出："aaabcbc"

示例 2：
输入：s = "3[a2[c]]"
输出："accaccacc"

示例 3：
输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"

示例 4：
输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"

提示：
1 <= s.length <= 30
s 由小写英文字母、数字和方括号 '[]' 组成
s 保证是一个 有效 的输入。
s 中所有整数的取值范围为 [1, 300]
*/

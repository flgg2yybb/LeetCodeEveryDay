package main

func main() {
	s1 := "()"
	s2 := "()[]{}"
	s3 := "(]"
	s4 := "([])"
	println(isValid(s1)) // true
	println(isValid(s2)) // true
	println(isValid(s3)) // false
	println(isValid(s4)) // true
}

// Stack, times: O(n), space: O(n)
// 思路：遇到左括号时，入栈，遇到右括号时，检查栈顶元素是否为匹配的左括号，如果是则弹出栈顶元素，继续检查下一个括号，如果不匹配则无效
func isValid(s string) bool {
	var mapping = map[string]string{
		")": "(",
		"}": "{",
		"]": "[",
	}
	stack := make([]string, 0)
	for _, r := range s {
		c := string(r)
		if expectPeek, ok := mapping[c]; ok {
			// 当前为右括号
			if len(stack) == 0 { // 栈为空
				return false
			}
			if stack[len(stack)-1] != expectPeek { // 栈顶元素不匹配
				return false
			}
			stack = stack[:len(stack)-1] // 弹出栈顶元素
		} else {
			// 当前为左括号
			stack = append(stack, c)
		}
	}
	return len(stack) == 0
}

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	每个右括号都有一个对应的相同类型的左括号。

示例 1：
输入：s = "()"
输出：true

示例 2：
输入：s = "()[]{}"
输出：true

示例 3：
输入：s = "(]"
输出：false

示例 4：
输入：s = "([])"
输出：true

提示：
1 <= s.length <= 104
s 仅由括号 '()[]{}' 组成
*/

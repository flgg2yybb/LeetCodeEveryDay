package main

import "fmt"

func main() {
	n1 := 3
	n2 := 1
	println(fmt.Sprintf("%+v", generateParenthesis(n1)))
	println(fmt.Sprintf("%+v", generateParenthesis(n2)))
}

// backtrack, times: O(2^n), space: O(n)
func generateParenthesis(n int) []string {
	ans := make([]string, 0)
	p := ""
	backtrack(&ans, &p, n, n)
	return ans
}

// left：剩余左括号数量，right：剩余右括号数量
func backtrack(ans *[]string, p *string, left int, right int) {
	if left == 0 && right == 0 {
		*ans = append(*ans, *p)
		return
	}
	if left > 0 {
		*p += "("
		backtrack(ans, p, left-1, right)
		*p = (*p)[:len(*p)-1]
	}
	if right > 0 && right > left { // right > left 保证括号有效
		*p += ")"
		backtrack(ans, p, left, right-1)
		*p = (*p)[:len(*p)-1]
	}
}

/*
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]

示例 2：
输入：n = 1
输出：["()"]

提示：
1 <= n <= 8
*/

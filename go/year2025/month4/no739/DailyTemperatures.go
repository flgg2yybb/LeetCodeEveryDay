package main

import "fmt"

func main() {
	temperatures1 := []int{73, 74, 75, 71, 69, 72, 76, 73}
	println(fmt.Sprintf("%+v", dailyTemperatures(temperatures1))) // [1, 1, 4, 2, 1, 1, 0, 0]
	temperatures2 := []int{30, 40, 50, 60}
	println(fmt.Sprintf("%+v", dailyTemperatures(temperatures2))) // [1, 1, 1, 0]
	temperatures3 := []int{30, 60, 90}
	println(fmt.Sprintf("%+v", dailyTemperatures(temperatures3))) // [1, 1, 0]
}

// 单调栈，times: O(n), space: O(n)
// 思路：用单调递减栈，存储下标，遍历每日温度
// 如果当前温度 > 栈顶元素，说明找到了下一个更高的温度，依次弹出栈顶元素，更新结果
// 如果当前温度 <= 栈顶元素，说明当前温度不是下一个更高的温度，入栈
// 最后栈中剩下的元素就是没有更高温度的元素，结果为 0
func dailyTemperatures(temperatures []int) []int {
	stack := make([]int, 0, len(temperatures))
	ans := make([]int, len(temperatures))
	for i, temperature := range temperatures {
		for len(stack) > 0 && temperatures[stack[len(stack)-1]] < temperature {
			ans[stack[len(stack)-1]] = i - stack[len(stack)-1]
			stack = stack[:len(stack)-1]
		}
		stack = append(stack, i)
	}
	return ans
}

/*
给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
如果气温在这之后都不会升高，请在该位置用 0 来代替。

示例 1:
输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]

示例 2:
输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]

示例 3:
输入: temperatures = [30,60,90]
输出: [1,1,0]

提示：
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
*/

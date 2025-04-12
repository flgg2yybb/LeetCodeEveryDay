package main

func main() {
	heights1 := []int{2, 1, 5, 6, 2, 3}
	println(largestRectangleArea(heights1)) // 10
	heights2 := []int{2, 4}
	println(largestRectangleArea(heights2)) // 4
	heights3 := []int{0, 9}
	println(largestRectangleArea(heights3)) // 9
}

// 单调栈，times: O(n), space: O(n)
// 思路：用单调递增栈，存储下标，遍历柱状图
// 由于是严格递增的，则栈顶元素的左边界就是下一个栈顶元素
// 假设 stack：1 -> 2 ... -> n，n 为栈顶，则有 n 的左边界为 n-1
// 如果当前柱子比栈顶元素小，则说明栈顶元素右边第一个更矮的柱子，此时可以计算栈顶元素的面积
// 如果当前柱子比栈顶元素大，则说明当前柱子不是下一个更矮的柱子，入栈
// 如果当前柱子和栈顶元素一样大，可以忽略掉，我们仅关心左右边界
func largestRectangleArea(heights []int) int {
	// PS: 为了代码简洁，我们在 heights 数组前后各添加一个 0，表示边界
	heights = append([]int{0}, heights...)
	heights = append(heights, 0)
	stack := make([]int, 0, len(heights)) // 单调递增栈，存储坐标
	maxArea := 0
	for i, height := range heights {
		for len(stack) > 0 && height < heights[stack[len(stack)-1]] { // 比栈顶元素小
			top := stack[len(stack)-1] // 栈顶元素
			stack = stack[:len(stack)-1]
			// 计算面积，宽度为当前坐标 - 栈顶元素的左边界 - 1
			// i 为当前栈顶元素的右边界，stack[len(stack)-1] 为栈顶元素的左边界
			width := i - stack[len(stack)-1] - 1
			area := width * heights[top]
			maxArea = max(maxArea, area)
		}
		stack = append(stack, i) // 入栈
	}
	return maxArea
}

// 单调栈，times: O(n), space: O(n)
// 思路：对于每个柱子，找到左边第一个比它矮的柱子和右边第一个比它矮的柱子，即可得知以当前柱子为高度的矩形面积，遍历即可获取最大面积
func largestRectangleArea1(heights []int) int {
	increStack := make([]int, 0, len(heights)) // 单调递增栈，存储坐标
	right := make([]int, len(heights))         // 右边第一个比它矮的柱子坐标
	for i := 0; i < len(right); i++ {          // 默认为 len(heights)，表示数组最右边，有比它矮的柱子
		right[i] = len(heights)
	}
	for i, height := range heights {
		for len(increStack) > 0 && heights[increStack[len(increStack)-1]] > height { // 比栈顶元素小
			right[increStack[len(increStack)-1]] = i
			increStack = increStack[:len(increStack)-1]
		}
		increStack = append(increStack, i)
	}
	increStack = make([]int, 0, len(heights)) // 单调递增栈，存储坐标
	left := make([]int, len(heights))         // 左边第一个比它矮的柱子坐标
	for i := 0; i < len(left); i++ {          // 默认为 -1，表示数组最左边，有比它矮的柱子
		left[i] = -1
	}
	for i := len(heights) - 1; i >= 0; i-- {
		height := heights[i]
		for len(increStack) > 0 && heights[increStack[len(increStack)-1]] > height { // 比栈顶元素小
			left[increStack[len(increStack)-1]] = i
			increStack = increStack[:len(increStack)-1]
		}
		increStack = append(increStack, i)
	}
	maxArea := 0
	for i, height := range heights {
		width := right[i] - left[i] - 1 // 宽度
		area := width * height
		maxArea = max(maxArea, area)
	}
	return maxArea
}

/*
 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。

示例 1:
输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10

示例 2：
输入： heights = [2,4]
输出： 4

提示：
1 <= heights.length <=105
0 <= heights[i] <= 104
*/

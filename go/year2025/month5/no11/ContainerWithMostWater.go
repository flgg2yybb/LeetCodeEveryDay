package main

func main() {
	height1 := []int{1, 8, 6, 2, 5, 4, 8, 3, 7}
	height2 := []int{1, 1}
	println(maxArea(height1)) // 49
	println(maxArea(height2)) // 1
}

// Two Pointers, times: O(n), space: O(1)
// 思路：使用双指针分别从两端向中间移动，每次移动高度较小的那一端，计算每次的面积，取最大值
func maxArea(height []int) int {
	left, right := 0, len(height)-1
	ans := 0
	for left < right {
		high := min(height[left], height[right])
		width := right - left
		ans = max(ans, high*width)
		if height[left] < height[right] {
			left++
		} else {
			right--
		}
	}
	return ans
}

/*
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。返回容器可以储存的最大水量。
说明：你不能倾斜容器。

示例 1：
输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例 2：
输入：height = [1,1]
输出：1

提示：
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
*/

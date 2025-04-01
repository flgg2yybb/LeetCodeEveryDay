package main

import (
	"fmt"
)

func main() {
	nums1 := []int{1, 2, 3}
	nums2 := []int{0, 1}
	nums3 := []int{1}
	println(fmt.Sprintf("%+v", permute(nums1))) // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
	println(fmt.Sprintf("%+v", permute(nums2))) // [[0,1],[1,0]]
	println(fmt.Sprintf("%+v", permute(nums3))) // [[1]]
}

// backtrack 回溯, times: O(n! * n), space: O(n)
func permute(nums []int) [][]int {
	ans := make([][]int, 0)
	backtrack(nums, &ans, 0)
	return ans
}

func backtrack(nums []int, ans *[][]int, i int) {
	if i >= len(nums) {
		clone := make([]int, 0, len(nums))
		for _, num := range nums {
			clone = append(clone, num)
		}
		*ans = append(*ans, clone)
		return
	}
	swap := func(arr []int, a, b int) []int {
		arr[a], arr[b] = arr[b], arr[a]
		return arr
	}
	for k := i; k < len(nums); k++ {
		nums = swap(nums, i, k)
		backtrack(nums, ans, i+1)
		nums = swap(nums, i, k)
	}
	return
}

/*
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

示例 1：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

示例 2：
输入：nums = [0,1]
输出：[[0,1],[1,0]]

示例 3：
输入：nums = [1]
输出：[[1]]

提示：
1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同
*/

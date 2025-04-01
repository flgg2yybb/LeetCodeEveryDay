package main

import "fmt"

func main() {
	nums1 := []int{1, 2, 3}
	nums2 := []int{0}
	println(fmt.Sprintf("%+v", subsets(nums1)))
	println(fmt.Sprintf("%+v", subsets(nums2)))
}

func subsets(nums []int) [][]int {
	ans := make([][]int, 0)
	set := make([]int, 0)
	backtrack(nums, &ans, &set, 0)
	return ans
}

// backtrack 回溯, times: O(n * 2^n), tims: O(n)
func backtrack(nums []int, ans *[][]int, set *[]int, i int) {
	if i >= len(nums) {
		p := make([]int, 0, len(*set)) // copy
		for _, val := range *set {
			p = append(p, val)
		}
		*ans = append(*ans, p)
		return
	}
	// 不选择当前元素
	backtrack(nums, ans, set, i+1)
	// 选择当前元素
	*set = append(*set, nums[i]) // 加入当前元素
	backtrack(nums, ans, set, i+1)
	*set = (*set)[:len(*set)-1] // 回溯，删除当前元素
}

/*
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

示例 1：
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

示例 2：
输入：nums = [0]
输出：[[],[0]]

提示：
1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同
*/

package main

import "fmt"

func main() {
	nums1 := []int{5, 7, 7, 8, 8, 10}
	target1 := 8
	println(fmt.Sprintf("%+v", searchRange(nums1, target1))) // [3, 4]
	nums2 := []int{5, 7, 7, 8, 8, 10}
	target2 := 6
	println(fmt.Sprintf("%+v", searchRange(nums2, target2))) // [-1, -1]
	nums3 := []int{}
	target3 := 0
	println(fmt.Sprintf("%+v", searchRange(nums3, target3))) // [-1, -1]
}

// 二分，times: O(log n), space: O(1)
func searchRange(nums []int, target int) []int {
	left := binarySearchFindFirst(nums, target)
	if left == -1 {
		return []int{-1, -1}
	}
	right := binarySearchFindLast(nums, target)
	return []int{left, right}
}

// 二分查找最后一个等于 target 的元素
func binarySearchFindLast(nums []int, target int) int {
	left, right := 0, len(nums)-1
	// 查找最后一个等于 target 的元素，找不到就返回插入位置
	for left < right {
		mid := (left + right + 1) / 2 // 向 right 靠近，避免死循环
		if nums[mid] <= target {      // 小于等于 target 的元素
			left = mid
		} else { // nums[mid] > target
			right = mid - 1
		}
	}
	if left < len(nums) && nums[left] == target {
		return left
	}
	return -1
}

// 二分查找第一个大于等于 target 的元素
func binarySearchFindFirst(nums []int, target int) int {
	left, right := 0, len(nums)
	// 查找第一个大于等于 target 的元素，找不到就返回插入位置
	for left < right {
		mid := left + (right-left)/2 //	保证向 left 靠近，避免死循环
		if nums[mid] >= target {     // 大于等于 target 的元素
			right = mid
		} else { // nums[mid] < target
			left = mid + 1
		}
	}
	if left < len(nums) && nums[left] == target {
		return left
	}
	return -1
}

/*
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

示例 1：
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]

示例 2：
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]

示例 3：
输入：nums = [], target = 0
输出：[-1,-1]

提示：
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109
*/

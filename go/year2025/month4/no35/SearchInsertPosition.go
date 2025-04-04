package main

func main() {
	nums1 := []int{1, 3, 5, 6}
	target1 := 5
	println(searchInsert(nums1, target1)) // 2
	nums2 := []int{1, 3, 5, 6}
	target2 := 2
	println(searchInsert(nums2, target2)) // 1
	nums3 := []int{1, 3, 5, 6}
	target3 := 7
	println(searchInsert(nums3, target3)) // 4
}

// 二分，times: O(log n), space: O(1)
// 找到第一个大于等于 target 的元素，返回下标
func searchInsert(nums []int, target int) int {
	left, right := 0, len(nums)
	for left < right {
		// left = mid + 1 而 right = mid，为避免死循环，因此 mid 需要靠向 left，
		// 同时可保证取不到 len(nums) 从而不会越界
		mid := left + (right-left)/2
		if nums[mid] >= target { // 大于等于 target 的元素
			right = mid
		} else { // nums[mid] < target
			left = mid + 1
		}
	}
	return left
}

// 二分，times: O(log n), space: O(1)
// 找到第一个大于等于 target 的元素，返回下标
func searchInsert1(nums []int, target int) int {
	left, right := 0, len(nums)
	for left < right {
		// left = mid + 1 而 right = mid，为避免死循环，因此 mid 需要靠向 left，
		// 同时可保证取不到 len(nums) 从而不会越界
		mid := left + (right-left)/2
		if nums[mid] == target {
			return mid
		} else if nums[mid] > target { // 大于等于 target 的元素
			right = mid
		} else { // nums[mid] < target
			left = mid + 1
		}
	}
	return left
}

/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
请必须使用时间复杂度为 O(log n) 的算法。

示例 1:
输入: nums = [1,3,5,6], target = 5
输出: 2

示例 2:
输入: nums = [1,3,5,6], target = 2
输出: 1

示例 3:
输入: nums = [1,3,5,6], target = 7
输出: 4

提示:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums 为 无重复元素 的 升序 排列数组
-104 <= target <= 104
*/

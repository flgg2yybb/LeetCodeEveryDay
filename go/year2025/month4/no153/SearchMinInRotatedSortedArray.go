package main

func main() {
	nums1 := []int{3, 4, 5, 1, 2}
	nums2 := []int{4, 5, 6, 7, 0, 1, 2}
	nums3 := []int{11, 13, 15, 17}
	nums4 := []int{3, 1, 2}
	println(findMin(nums1)) // Output: 1
	println(findMin(nums2)) // Output: 0
	println(findMin(nums3)) // Output: 11
	println(findMin(nums4)) // Output: 1
}

// Binary Search, times: O(log n), space: O(1)
func findMin(nums []int) int {
	left, right := 0, len(nums)-1
	for left < right {
		if nums[left] <= nums[right] { // 区间单调递增，直接返回 nums[left]
			break
		}
		mid := left + (right-left)/2 // 两个元素时，mid == left
		if nums[left] <= nums[mid] { // 左边升序
			left = mid + 1
		} else { // nums[left] > nums[mid] -> nums[mid] <= nums[right] // 右边升序
			right = mid
		}
	}
	return nums[left]
}

/*
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

示例 1：
输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。

示例 2：
输入：nums = [4,5,6,7,0,1,2]
输出：0
解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。

示例 3：
输入：nums = [11,13,15,17]
输出：11
解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。

提示：
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums 中的所有整数 互不相同
nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
*/

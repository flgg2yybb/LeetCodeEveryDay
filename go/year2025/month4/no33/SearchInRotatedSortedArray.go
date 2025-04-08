package main

func main() {
	nums1 := []int{4, 5, 6, 7, 0, 1, 2}
	target1 := 0
	nums2 := []int{4, 5, 6, 7, 0, 1, 2}
	target2 := 3
	nums3 := []int{1}
	target3 := 0
	nums4 := []int{3, 1}
	target4 := 1
	println(search(nums1, target1)) // 4
	println(search(nums2, target2)) // -1
	println(search(nums3, target3)) // -1
	println(search(nums4, target4)) // 1
}

// Binary Search, times: O(log n), space: O(1)
/* 思路
原数组是升序的，经过旋转后，分成了两部分，左边部分是升序的，右边部分也是升序的。
旋转之后进行二分，一定有一边是生序的，因此根据生序的部分判断是否包含 target，从而来决定后续二分走向
	1. nums[left] > nums[right]，说明此时查找区间一部分在左边，一部分在右边
		* 如果 nums[left] <= nums[mid]，说明左部分升序
			* 如果 nums[mid] > target > nums[left]，说明 target 在左边部分，继续二分查找
				right = mid - 1
			* 否则
				left = mid + 1
		* 如果 nums[mid] < nums[left]，说明右部分生序
			* 如果 nums[mid] < target < nums[right]，说明 target 在右边部分，继续二分查找
				left = mid + 1
			* 否则
				right = mid - 1
	2. nums[left] < nums[right]，说明此时查找区间是升序的，继续二分查找即可
			* 如果 nums[mid] > target > nums[left]，说明 target 在左边部分，继续二分查找
				right = mid - 1
			* 否则
				left = mid + 1
上述两种情况可以合并，合并后为：
	1. 如果 nums[mid] > nums[left]，说明左部分升序
		* 如果 nums[mid] > target > nums[left]，说明 target 在左边部分，继续二分查找
			right = mid - 1
		* 否则
			left = mid + 1
	2. 如果 nums[mid] < nums[left]，说明右部分生序
		* 如果 nums[mid] < target < nums[right]，说明 target 在右边部分，继续二分查找
			left = mid + 1
		* 否则
			right = mid - 1
PS：因为初始化时 left 和 right 在数组最左边，则一开始一定是第一种情况，因此一定搜索初数组是否包含 target
图例，旋转之后的数组坐标以及值关系如下
			*
		*
	*
----------------------------------
							*
						*
					*
				*
	0	1	2	3	4	5	6
*/
func search(nums []int, target int) int {
	left, right := 0, len(nums)-1
	for left <= right {
		mid := left + (right-left)/2 // 当只有两个元素时，mid == left
		if nums[mid] == target {
			return mid
		}
		if nums[left] <= nums[mid] { // 左区间生序，需要考虑 mid == left 的情况
			if nums[left] <= target && target < nums[mid] { // mid 在左边部分
				right = mid - 1
			} else {
				left = mid + 1
			}
		} else { //  nums[mid] < nums[left] -> nums[mid] < nums[right] -> 右区间升序
			if nums[mid] < target && target <= nums[right] { // mid 在右边部分
				left = mid + 1
			} else {
				right = mid - 1
			}
		}
	}
	return -1
}

/*
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。



示例 1：

输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
示例 2：

输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
示例 3：

输入：nums = [1], target = 0
输出：-1


提示：

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums 中的每个值都 独一无二
题目数据保证 nums 在预先未知的某个下标上进行了旋转
-104 <= target <= 104
*/

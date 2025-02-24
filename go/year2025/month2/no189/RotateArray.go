package main

import "fmt"

func main() {
	nums1 := []int{1, 2, 3, 4, 5, 6, 7}
	k1 := 3
	nums2 := []int{-1, -100, 3, 99}
	k2 := 2
	rotate(nums1, k1)
	rotate(nums2, k2)
	println(fmt.Sprintf("%+v", nums1))
	println(fmt.Sprintf("%+v", nums2))
}

func rotate(nums []int, k int) {
	n := len(nums)
	k = k % n
	reverse(nums, 0, n-1) // 翻转整个数组
	reverse(nums, 0, k-1) // 翻转前 k 个元素
	reverse(nums, k, n-1) // 翻转后 n-k 个元素
}

func reverse(nums []int, start, end int) {
	for start < end {
		nums[start], nums[end] = nums[end], nums[start]
		start++
		end--
	}
}

/*
*
* 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]

提示：

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105

进阶：

尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
* */

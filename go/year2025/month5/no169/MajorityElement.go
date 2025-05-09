package main

import "math"

func main() {
	nums1 := []int{3, 2, 3}
	nums2 := []int{2, 2, 1, 1, 1, 2, 2}
	println(majorityElement(nums1)) // 3
	println(majorityElement(nums2)) // 2
}

/*
摩尔投票算法，times: O(n)，space: O(1)
1. 先假设第一个元素是候选人，票数为1
2. 遍历数组，遇到候选人票数+1，遇到其他元素票数-1
3. 如果票数为0，则更换候选人，票数重置为1
4. 遍历结束后，候选人即为多数元素

也可以理解为：
抛弃法：多数元素出现次数大于 ⌊ n/2 ⌋ ，则在数组中每次取出两个元素，若元素不同，则抛弃，循环进行下去，最后剩下的元素则为众数

分析：
1. 哈希表统计各个元素出现次数，times: O(n), space: O(n)
2. 排序后，返回中间元素，times: O(nlogn), space: O(1)
3. 摩尔投票算法，时间复杂度 O(n)，空间复杂度 O(1)
*/
func majorityElement(nums []int) int {
	// 摩尔投票算法
	candidate := math.MinInt
	count := 0
	for i := 0; i < len(nums); i++ {
		if count == 0 {
			candidate = nums[i]
		}
		if nums[i] == candidate {
			count++
		} else {
			count--
		}
	}
	return candidate
}

/*
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1：
输入：nums = [3,2,3]
输出：3

示例 2：
输入：nums = [2,2,1,1,1,2,2]
输出：2

提示：
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109

进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
*/

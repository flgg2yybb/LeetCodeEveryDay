package main

import (
	"fmt"
	"sort"
)

/*
双指针, times: O(n^2), space: O(n)
思路：类似 two sum
1. 先排序
2. 遍历数组，固定一个数 nums[i]，然后在 nums[i+1:] 中使用双指针查找另外两个数
3. 如果 nums[i] + nums[left] + nums[right] == 0，说明找到了一个三元组，left++，right--，继续查找
4. 如果 nums[i] + nums[left] + nums[right] < 0，说明需要增大和，left++
5. 如果 nums[i] + nums[left] + nums[right] > 0，说明需要减小和，right--
6. 注意去重，跳过重复的元素
*/
func threeSum(nums []int) [][]int {
	ans := make([][]int, 0)
	sort.Slice(nums, func(i, j int) bool { // sort
		return nums[i] < nums[j]
	})
	for i := 0; i < len(nums); i++ {
		if i > 0 && nums[i] == nums[i-1] { // 去重
			continue
		}
		left, right := i+1, len(nums)-1
		for left < right {
			sum := nums[i] + nums[left] + nums[right]
			if sum == 0 {
				ans = append(ans, []int{nums[i], nums[left], nums[right]})
				left++
				for left < right && nums[left] == nums[left-1] { // 去重
					left++
				}
			} else if sum < 0 {
				left++
			} else {
				right--
			}
		}

	}
	return ans
}

func main() {
	nums1 := []int{-1, 0, 1, 2, -1, -4}
	nums2 := []int{0, 1, 1}
	nums3 := []int{0, 0, 0}
	nums4 := []int{-1, -1, -1, 2, 2, 2, -1, -1, -1, 2, 2, 2, -4}
	println(fmt.Sprintf("%+v", threeSum(nums1)))
	println(fmt.Sprintf("%+v", threeSum(nums2)))
	println(fmt.Sprintf("%+v", threeSum(nums3)))
	println(fmt.Sprintf("%+v", threeSum(nums4)))
}

/*
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。

示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。

示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。

示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。

提示：
3 <= nums.length <= 3000
-105 <= nums[i] <= 105
*/

package main

import "fmt"

func twoSum(nums []int, target int) []int {
	dic := make(map[int]int, 0) // value -> index
	for n, num := range nums {
		diff := target - num
		if k, ok := dic[diff]; ok {
			return []int{k, n}
		}
		dic[num] = n
	}
	return []int{-1, -1}
}

func main() {
	nums1 := []int{2, 7, 11, 15}
	target1 := 9
	nums2 := []int{3, 2, 4}
	target2 := 6
	nums3 := []int{3, 3}
	target3 := 6
	println(fmt.Sprintf("%+v", twoSum(nums1, target1)))
	println(fmt.Sprintf("%+v", twoSum(nums2, target2)))
	println(fmt.Sprintf("%+v", twoSum(nums3, target3)))
}

/*
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。



示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]


提示：

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
只会存在一个有效答案


进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
*/

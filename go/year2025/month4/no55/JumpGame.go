package main

func main() {
	nums1 := []int{2, 3, 1, 1, 4}
	nums2 := []int{3, 2, 1, 0, 4}
	nums3 := []int{0}
	println(canJump(nums1)) // true
	println(canJump(nums2)) // false
	println(canJump(nums3)) // true
}

// Greedy, time: O(n), space: O(1)
func canJump(nums []int) bool {
	next := 0
	for i := 0; i < len(nums); i++ {
		if i > next { // 如果当前下标大于能到达的下标，说明无法到达
			return false
		}
		next = max(next, i+nums[i]) // 更新能到达的最远下标
		if next >= len(nums)-1 {    // 如果能到达最后一个下标，返回 true
			return true
		}
	}
	return false
}

/*
给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。

示例 1：
输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

示例 2：
输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。

提示：
1 <= nums.length <= 104
0 <= nums[i] <= 105
*/

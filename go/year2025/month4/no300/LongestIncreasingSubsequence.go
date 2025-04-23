package main

func main() {
	nums1 := []int{10, 9, 2, 5, 3, 7, 101, 18}
	nums2 := []int{0, 1, 0, 3, 2, 3}
	nums3 := []int{7, 7, 7, 7, 7, 7, 7}
	println(lengthOfLIS(nums1)) // 4
	println(lengthOfLIS(nums2)) // 4
	println(lengthOfLIS(nums3)) // 1
}

/*
DP, tims: O(n^2), space: O(n)
状态定义：dp[i] 为以 nums[i] 结尾的最长递增子序列的长度
状态转移方程：dp[i] = max(dp[j] + 1), (j < i && nums[j] < nums[i])
状态初始化：dp[i] = 1
*/
func lengthOfLIS(nums []int) int {
	dp := make([]int, len(nums))
	ans := 0
	for i := 0; i < len(nums); i++ {
		dp[i] = 1
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		ans = max(ans, dp[i])
	}
	return ans
}

/*
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4

示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1

提示：
1 <= nums.length <= 2500
-104 <= nums[i] <= 104

进阶：
你能将算法的时间复杂度降低到 O(n log(n)) 吗?
*/

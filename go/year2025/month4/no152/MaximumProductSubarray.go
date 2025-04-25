package main

func main() {
	nums1 := []int{2, 3, -2, 4}
	nums2 := []int{-2, 0, -1}
	println(maxProduct(nums1)) // 6
	println(maxProduct(nums2)) // 0
}

/*
DP, times: O(n), space: O(1)
状态定义：dp[i][j] 表示以第 i 个元素结尾的乘积, j = 0 表示最小乘积，j = 1 表示最大乘积
状态转移方程：
  - dp[i][0] = min(dp[i-1][0]*nums[i], dp[i-1][1]*nums[i], nums[i])
  - dp[i][1] = max(dp[i-1][0]*nums[i], dp[i-1][1]*nums[i], nums[i])

初始值：dp[0][0] = dp[0][1] = nums[0]
结果：max(dp[i])
状态压缩：
实际上只需要两个变量来保存前一个状态的最小值和最大值即可，比如用 preMin 和 preMax 来表示前一个状态的最小值和最大值。
*/
func maxProduct(nums []int) int {
	preMin, preMax := nums[0], nums[0]
	ans := preMax
	for i := 1; i < len(nums); i++ {
		num := nums[i]
		curMin := min(preMin*num, preMax*num, num)
		curMax := max(preMin*num, preMax*num, num)
		preMin = curMin
		preMax = curMax
		ans = max(ans, preMax)
	}
	return ans
}

/*
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
测试用例的答案是一个 32-位 整数。

示例 1:
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

示例 2:
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

提示:
1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
nums 的任何子数组的乘积都 保证 是一个 32-位 整数
*/

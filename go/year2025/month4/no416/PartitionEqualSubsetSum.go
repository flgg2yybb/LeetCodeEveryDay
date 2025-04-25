package main

func main() {
	nums1 := []int{1, 5, 11, 5}
	nums2 := []int{1, 2, 3, 5}
	println(canPartition(nums1)) // true
	println(canPartition(nums2)) // false
}

/*
【0-1背包问题】状态压缩 DP, times: O(nm), space: O(m), n 为数组长度，m 为数组和的一半
状态定义：dp[j] 代表当前已遍历的子数组中选取若干元素是否能凑成 j
状态转移方程：dp[j] = dp[j] || dp[j-nums[i]]
是否选择 nums[i]：	不选			  选
状态转移方向：为了防止之前的数据被覆盖，需要从后往前遍历
初始值：dp[0] = true, dp[nums[i]] = true
*/
func canPartition(nums []int) bool {
	sum := 0
	maxVal := 0
	for _, num := range nums {
		sum += num
		maxVal = max(maxVal, num)
	}
	if sum%2 != 0 { // 数组和为奇数不可能凑成
		return false
	}
	target := sum / 2
	if maxVal > target { // 如果存在某一个元素大于数组和的一半，那么也不可能凑成
		return false
	}
	dp := make([]bool, target+1)
	dp[0] = true
	dp[nums[0]] = true
	for i := 1; i < len(nums); i++ {
		for j := target; j-nums[i] >= 0; j-- {
			dp[j] = dp[j] || dp[j-nums[i]]
		}
	}
	return dp[target]
}

/*
【0-1背包问题】DP, times: O(nm), space: O(nm), n 为数组长度，m 为数组和的一半
思路：一个数组若能分割成两个等和子集，则说明原数组和必须为偶数，且若从数组中能取出若干元素，使其等于元原数组和的一半，则说明可以分割
状态定义：dp[i][j] 代表子数组[0,i]中选取若干元素是否能凑成 j
状态转移方程：dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
是否选择 nums[i]：			不选			        选
状态转移方向：左上往右下
初始值：dp[i][0] = true, dp[i][nums[i]] = true
*/
func canPartition1(nums []int) bool {
	sum := 0
	maxVal := 0
	for _, num := range nums {
		sum += num
		maxVal = max(maxVal, num)
	}
	if sum%2 != 0 { // 数组和为奇数不可能凑成
		return false
	}
	target := sum / 2
	if maxVal > target { // 如果存在某一个元素大于数组和的一半，那么也不可能凑成
		return false
	}
	dp := make([][]bool, len(nums))
	dp[0] = make([]bool, target+1)
	dp[0][0] = true
	dp[0][nums[0]] = true
	for i := 1; i < len(nums); i++ {
		dp[i] = make([]bool, target+1)
		for j := 0; j <= target; j++ {
			if j-nums[i] >= 0 { // 因 nums[i] >= 1，故 j-nums[i] <= target
				dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
			} else {
				dp[i][j] = dp[i-1][j]
			}
		}
	}
	return dp[len(nums)-1][target]
}

/*
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。



示例 1：

输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：

输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。


提示：

1 <= nums.length <= 200
1 <= nums[i] <= 100
*/

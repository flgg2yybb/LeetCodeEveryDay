package main

func main() {
	nums1 := []int{1, 2, 3, 1}
	nums2 := []int{2, 7, 9, 3, 1}
	println(rob(nums1)) // 4
	println(rob(nums2)) // 12
}

/*
	DP, times: O(n), space: O(1)

状态定义：dp[i][j] 表示到第 i 间房屋时，目前为止偷窃最大金额，j 表示是否偷窃（0: 不偷窃，1：偷）
状态转移方程：
  - dp[i][0] = max(dp[i-1][0], dp[i-1][1])
  - dp[i][1] = dp[i-1][0] + nums[i]

初始值：dp[0][0] = 0, dp[0][1] = nums[0]
状态压缩：偷第 i 间房时，只关心第 i-1 有没有偷，因此可以用两个变量表示
  - preRob 表示 i-1 间房屋偷的金额，preNotRob 表示 i-1 间房屋不偷的金额

结果：max(preRob, preNotRob)
*/
func rob(nums []int) int {
	n := len(nums)
	preRob := nums[0]
	preNotRob := 0
	for i := 1; i < n; i++ {
		curRob := preNotRob + nums[i]       // 偷第 i 间房屋
		curNotRob := max(preRob, preNotRob) // 不偷第 i 间房屋
		preRob = curRob
		preNotRob = curNotRob
	}
	return max(preRob, preNotRob)
}

/*
	DP, times: O(n), space: O(n)

状态定义：dp[i][j] 表示到第 i 间房屋时，目前为止偷窃最大金额，j 表示是否偷窃（0: 不偷窃，1：偷）
状态转移方程：
  - dp[i][0] = max(dp[i-1][0], dp[i-1][1])
  - dp[i][1] = dp[i-1][0] + nums[i]

初始值：dp[0][0] = 0, dp[0][1] = nums[0]
结果：max(dp[n-1][0], dp[n-1][1])
*/
func rob1(nums []int) int {
	n := len(nums)
	dp := make([][]int, n)
	dp[0] = []int{0, nums[0]} // 第一间房屋
	for i := 1; i < n; i++ {
		dp[i] = make([]int, 2)
		dp[i][0] = max(dp[i-1][0], dp[i-1][1]) // 不偷
		dp[i][1] = dp[i-1][0] + nums[i]        // 偷
	}
	return max(dp[n-1][0], dp[n-1][1])
}

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

示例 1：
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。

提示：
1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

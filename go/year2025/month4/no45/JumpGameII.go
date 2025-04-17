package main

func main() {
	nums1 := []int{2, 3, 1, 1, 4}
	nums2 := []int{2, 3, 0, 1, 4}
	nums3 := []int{2, 1}
	println(jump(nums1)) // 2
	println(jump(nums2)) // 2
	println(jump(nums3)) // 1
}

// Greedy, time: O(n), space: O(1)
func jump1(nums []int) int {
	pos := 0  // 当前位置
	step := 0 // 步数
	next := 0 //下一个能到达的最远下标
	for pos < len(nums)-1 {
		next = max(next, pos+nums[pos]) // 更新能到达的最远下标
		cur := min(next, len(nums)-1)   // 当前这一步能到达的最远下标
		for pos < cur {                 // 在当前这一步的范围内，寻找下一个能到达的最远下标
			pos++
			next = max(next, pos+nums[pos])
		}
		step++ // 更新步数
	}
	return step
}

// DP, times: O(n^2), space: O(n)
// 状态定义：dp[i] 表示到达下标 i 的最小步数
// 状态转移方程：dp[i] = min(dp[j] + 1), j < i, nums[j] >= i - j
// 初始化：dp[0] = 0
func jump(nums []int) int {
	dp := make([]int, len(nums))
	dp[0] = 0
	for i := 1; i < len(dp); i++ {
		dp[i] = -1 // 初始化为 -1，表示不可达
	}
	for i := 1; i < len(dp); i++ {
		for j := 0; j < i; j++ {
			if dp[j] != -1 && nums[j] >= i-j { // 可以从 j 跳到 i
				if dp[i] == -1 {
					dp[i] = dp[j] + 1 // 如果 dp[i] 为 0，说明是第一次到达
				} else {
					dp[i] = min(dp[i], dp[j]+1)
				}
			}
		}
	}
	return dp[len(dp)-1]
}

/*
给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
	* 0 <= j <= nums[i]
	* i + j < n
返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

示例 1:
输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

示例 2:
输入: nums = [2,3,0,1,4]
输出: 2

提示:
1 <= nums.length <= 104
0 <= nums[i] <= 1000
题目保证可以到达 nums[n-1]
*/

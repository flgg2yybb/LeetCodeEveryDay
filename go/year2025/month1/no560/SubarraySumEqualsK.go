package main

// Prefix Sum, time: O(n), space: O(n)
func subarraySum(nums []int, k int) int {
	ans := 0
	prefixMap := make(map[int]int) // k: prefixSum, v: count
	prefixMap[0] = 1               // init
	sum := 0                       // current prefix sum
	for _, num := range nums {
		sum += num                             // update current prefix sum
		if count, ok := prefixMap[sum-k]; ok { // find all possible prefix
			ans += count
		}
		if _, ok := prefixMap[sum]; !ok {
			prefixMap[sum] = 0
		}
		prefixMap[sum]++

	}
	return ans
}

func main() {
	nums1 := []int{1, 1, 1}
	k1 := 2
	nums2 := []int{1, 2, 3}
	k2 := 3
	nums3 := []int{1, 2, 1, 2, 1}
	k3 := 3
	println(subarraySum(nums1, k1))
	println(subarraySum(nums2, k2))
	println(subarraySum(nums3, k3))
}

/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。



示例 1：

输入：nums = [1,1,1], k = 2
输出：2
示例 2：

输入：nums = [1,2,3], k = 3
输出：2


提示：

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
*/

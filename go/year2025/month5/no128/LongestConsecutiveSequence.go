package main

func main() {
	nums1 := []int{100, 4, 200, 1, 3, 2}
	nums2 := []int{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}
	nums3 := []int{0}
	println(longestConsecutive(nums1)) // 4
	println(longestConsecutive(nums2)) // 9
	println(longestConsecutive(nums3)) // 1
}

// HashMap, times: O(n), space: O(n)
// 思路：实现时间复杂度为 O(n) 的思路是，只从最长序列的起点开始遍历，即剪枝
func longestConsecutive(nums []int) int {
	dic := make(map[int]bool)
	for _, num := range nums {
		dic[num] = true
	}
	longest := 0
	for num := range dic { // 从 Map 中遍历
		if dic[num-1] { // 如果 num-1 也在 Map 中，说明这个数字不是连续序列的起点，直接忽略
			continue
		}
		// 找到起点，向后遍历，并且计数
		count := 0
		start := num
		for dic[start] {
			count++
			start++
		}
		if count > longest {
			longest = count
		}

	}
	return longest
}

/*
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1：
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

示例 2：
输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9

提示：
0 <= nums.length <= 105
-109 <= nums[i] <= 109
*/

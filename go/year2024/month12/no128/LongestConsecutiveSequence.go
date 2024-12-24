package main

// 【timeout】hash and count, time: O(n^2), space: O(n)
func longestConsecutive(nums []int) int {
	dic := make(map[int]struct{})
	for _, num := range nums { // O(n)
		dic[num] = struct{}{}
	}
	longest := 0
	// the worse case, nums = [0, 0, 1, 2, 3, ..., n-2], time: O(n^2)
	for _, num := range nums { // O(n^2)
		if longest >= len(nums) {
			return longest
		}
		cur := 0
		for i := num; i < num+len(nums); i++ { // O(n)
			if _, ok := dic[i]; !ok {
				break
			}
			cur++
		}
		if cur > longest {
			longest = cur
		}
	}
	return longest
}

func main() {
	nums1 := []int{100, 4, 200, 1, 3, 2}
	nums2 := []int{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}
	nums3 := []int{0}
	println(longestConsecutive(nums1))
	println(longestConsecutive(nums2))
	println(longestConsecutive(nums3))
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

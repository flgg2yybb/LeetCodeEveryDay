package main

func main() {
	nums1 := []int{1, 2, 0}
	nums2 := []int{3, 4, -1, 1}
	nums3 := []int{7, 8, 9, 11, 12}
	println(firstMissingPositive(nums1))
	println(firstMissingPositive(nums2))
	println(firstMissingPositive(nums3))
}

// Hash In-Place, times: O(n), space: O(1)
// 正常逻辑是：新建一个哈希表，把所有的正整数放进去，然后从1开始遍历，找到第一个不在哈希表中的正整数即可。
// 【因为考虑到空间复杂度为O(1)，所以不能新建哈希表，只能在原数组上进行操作】。
// 1. 遍历数组，把每个元素 i 放到对应的位置 i-1 上，比如1放到nums[0]，2放到nums[1]，3放到nums[2]，以此类推。
// 2. 再次遍历数组，找到第一个不在对应位置上的正整数即可。
// PS：对于不在1~n范围内的正整数，可以直接忽略，因为不在这个范围内的正整数不会影响到结果。
func firstMissingPositive(nums []int) int {
	for i := 0; i < len(nums); i++ {
		// 注意：此处是for循环，而不是if条件判断，因为交换后的nums[i]可能还是不在对应位置上
		for nums[i] > 0 && nums[i] <= len(nums) && nums[nums[i]-1] != nums[i] {
			nums[nums[i]-1], nums[i] = nums[i], nums[nums[i]-1]
		}
	}
	ans := 1
	for _, num := range nums {
		if ans != num {
			return ans
		}
		ans++
	}
	return ans
}

// Hash Table, times: O(n), space: O(n)
func firstMissingPositive1(nums []int) int {
	set := make(map[int]bool)
	for i := 0; i < len(nums); i++ {
		set[nums[i]] = true
	}
	ans := 1
	for set[ans] {
		ans++
	}
	return ans
}

/*
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

示例 1：

输入：nums = [1,2,0]
输出：3
解释：范围 [1,2] 中的数字都在数组中。
示例 2：

输入：nums = [3,4,-1,1]
输出：2
解释：1 在数组中，但 2 没有。
示例 3：

输入：nums = [7,8,9,11,12]
输出：1
解释：最小的正数 1 没有出现。


提示：

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/

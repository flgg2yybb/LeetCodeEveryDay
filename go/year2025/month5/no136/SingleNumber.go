package main

func main() {
	nums1 := []int{2, 2, 1}
	nums2 := []int{4, 1, 2, 1, 2}
	nums3 := []int{1}
	println(singleNumber(nums1)) // 1
	println(singleNumber(nums2)) // 4
	println(singleNumber(nums3)) // 1
}

/*
位运算：times: O(n), space: O(1)
思路：按位异或（XOR）：相同为 0，不同为 1，因为任何数和 0 异或都等于它本身，所以我们可以用异或来找出只出现一次的数字。
*/
func singleNumber(nums []int) int {
	ans := 0
	for _, num := range nums {
		ans ^= num
	}
	return ans
}

/*
给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。

示例 1 ：
输入：nums = [2,2,1]
输出：1

示例 2 ：
输入：nums = [4,1,2,1,2]
输出：4

示例 3 ：
输入：nums = [1]
输出：1

提示：
1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
除了某个元素只出现一次以外，其余每个元素均出现两次。
*/

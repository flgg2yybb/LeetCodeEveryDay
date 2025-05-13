package main

func main() {
	nums1 := []int{1, 3, 4, 2, 2}
	nums2 := []int{3, 1, 3, 4, 2}
	nums3 := []int{3, 3, 3, 3, 3}
	println(findDuplicate(nums1)) // 2
	println(findDuplicate(nums2)) // 3
	println(findDuplicate(nums3)) // 3
}

/*
Fast Slow Pointers - 快慢指针判圈法, tims:O(n), space:O(1)
思路：数组长度为 n+1，数字范围为 [1, n] ，因此可以把数字看成链表的 next，指针从起点 0 开始出发，当前值为 num[i]，则移动至 nums[i] 索引的元素
因至少存在一个重复的整数，所以必然存在环，快慢指针法可以找到环的入口

进阶：
1. 如何证明 nums 中至少存在一个重复的数字? => n+1 的长度存 [1,n] 的数字，必有重复数字
2. 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？ => 通过快慢指针法，时间复杂度 O(n)，空间复杂度 O(1)
*/
func findDuplicate(nums []int) int {
	fast, slow := 0, 0
	for {
		fast = nums[nums[fast]]
		slow = nums[slow]
		if fast == slow { // 相遇了，说明有环
			break
		}
	}
	slow = 0 // 相遇之后，将 slow 指针移动到起点，slow 和 fast 一起移动，最终相遇的地方就是环的入口
	for slow != fast {
		slow = nums[slow]
		fast = nums[fast]
	}
	return slow
}

/*
给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。

你设计的解决方案必须 **不修改** 数组 nums 且只用常量级 **O(1) 的额外空间** 。

示例 1：
输入：nums = [1,3,4,2,2]
输出：2

示例 2：
输入：nums = [3,1,3,4,2]
输出：3

示例 3:
输入：nums = [3,3,3,3,3]
输出：3

提示：
1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次

进阶：
如何证明 nums 中至少存在一个重复的数字?
你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
*/

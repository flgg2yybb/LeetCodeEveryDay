package main

import "math"

func main() {
	nums11 := []int{1, 3}
	nums12 := []int{2}
	nums21 := []int{1, 2}
	nums22 := []int{3, 4}
	println(findMedianSortedArrays(nums11, nums12)) // 2.00000
	println(findMedianSortedArrays(nums21, nums22)) // 2.50000
}

/* 二分查找，times: O(log(min(m,n))), space: O(1)
 * 思路：首先根据二分查找找出可以分界线，如下
 *            left1  right1
 * arr1 :  A    B  |  C    D
 *                 -----------
 * arr2 :  a    b    c    d  |  e    f    g
 *                     left2  right2
 * 此分割线将 arr1 、 arr2 分成左右两部分，并
 *   若为奇数时 leftNum - 1 = rightNum
 *   若为偶数时 leftNum = rightNum
 * 且
 *   left1 <= right2 && left2 <= right1
 * 令分界线 s 为 right1 的下标索引，m = arr1.length, n = arr2.length，则
 * 左半部分的元素数量 leftSize = (m + n + 1) / 2，已处理奇数
 * left1 = arr1[s-1]
 * right1 = arr1[s]
 * left2 = arr2[((m + n + 1) / 2 ) - s - 1]
 * right2 = arr2[((m + n + 1) / 2 ) - s]
 *
 * **注意**：二分查找条件只需判断 left1 <= right2 即可
 * 即找到最大的 left1 使得 left1 <= right2
 * 因为当前 left1 为最大的能满足 left1 <= right2 的元素时
 * right1 为 left1 下一个元素，left2 为 right2的上一个元素
 * 若存在 left2 > right1，则表示 left1 的下一个元素小于 right2 的上一个元素
 * 与【当前 left1 为最大的能满足 left1 <= right2 的元素】矛盾
 * */
func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	m, n := len(nums1), len(nums2)
	if m > n { // 保证 len(nums1) <= len(nums2)
		return findMedianSortedArrays(nums2, nums1)
	}
	leftSize := (m + n + 1) / 2
	start := 0
	end := m
	for start < end {
		// nums1 分界线下标为 i，nums2 为 j
		i := (start + end + 1) / 2
		j := leftSize - i
		// 由于 i 为向上取整，而循环体内 start != end，故一定有 i > 0，则 i - 1 不越界
		// 由于 m <= n，则用划分分割线时，由 i-1 不越界可得 j 必不越界
		left1 := nums1[i-1]
		right2 := nums2[j]
		if left1 <= right2 { // left1 <= right2
			start = i
		} else {
			end = i - 1
		}
	}
	i := start
	j := leftSize - i
	left1, left2 := math.MinInt64, math.MinInt64
	if i-1 >= 0 {
		left1 = nums1[i-1]
	}
	if j-1 >= 0 {
		left2 = nums2[j-1]
	}
	if m+n%2 == 1 {
		return math.Max(float64(left1), float64(left2))
	}
	right1, right2 := math.MaxInt64, math.MaxInt64
	if i < m {
		right1 = nums1[i]
	}
	if j < n {
		right2 = nums2[j]
	}
	return (math.Max(float64(left1), float64(left2)) +
		math.Min(float64(right1), float64(right2))) / 2
}

/*
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

提示：
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
*/

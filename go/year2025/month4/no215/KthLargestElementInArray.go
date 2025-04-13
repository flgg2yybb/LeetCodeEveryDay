package main

import (
	"container/heap"
	"math/rand/v2"
)

func main() {
	nums1 := []int{3, 2, 1, 5, 6, 4}
	println(findKthLargest(nums1, 2)) // 5
	nums2 := []int{3, 2, 3, 1, 2, 4, 5, 5, 6}
	println(findKthLargest(nums2, 4)) // 4
}

// 快速选择算法，时间复杂度 O(n)，空间复杂度 O(logn)
// 思路：partition 每次选择一个元素，在数组中找到一个位置，使得：数组左边的元素都 >= 该元素，数组右边的元素都 <= 该元素
// 因此可以通过找到元素的位置是否为 K-1 来判断是否找到了第 K 个最大元素
func findKthLargest(nums []int, k int) int {
	start, end := 0, len(nums)-1
	index := k - 1 // 第 k 个最大元素坐标为 k-1
	if index < start || index > end {
		panic("k out of range")
	}
	// 随机选择一个元素，避免最坏情况
	for start < end {
		mid := partition(nums, start, end)
		if mid == index {
			return nums[mid]
		} else if mid > index {
			end = mid - 1
		} else {
			start = mid + 1
		}
	}
	return nums[start]
}

func partition(nums []int, start int, end int) int {
	// 随机选择一个元素，避免最坏情况
	ramdom := rand.IntN(end - start)
	nums[start], nums[start+ramdom] = nums[start+ramdom], nums[start]
	// 选择第一个元素用来划分数组
	pivot := nums[start]
	for start < end { // 从大到小排序
		for start < end && nums[end] <= pivot {
			end--
		}
		// nums[end] > pivot
		nums[start], nums[end] = nums[end], nums[start]
		for start < end && nums[start] >= pivot {
			start++
		}
		// nums[start] < pivot
		nums[start], nums[end] = nums[end], nums[start]
	}
	nums[start] = pivot
	return start
}

type MinHeap []int

func (m MinHeap) Len() int {
	return len(m)
}

func (m MinHeap) Less(i, j int) bool {
	return m[i] < m[j]
}

func (m MinHeap) Swap(i, j int) {
	m[i], m[j] = m[j], m[i]
}

func (m *MinHeap) Push(i interface{}) {
	*m = append(*m, i.(int))
}

func (m *MinHeap) Pop() interface{} {
	arr := *m
	n := len(arr)
	if n == 0 {
		return -1
	}
	pop := arr[n-1]
	*m = arr[:n-1]
	return pop
}

// 最小堆，times: O(nlogk), space: O(k)
// 思路：用最小堆，存储前 k 个元素，遍历数组后，最小堆的堆顶元素即是第 k 个最大元素
func findKthLargest1(nums []int, k int) int {
	minHeap := &MinHeap{}
	heap.Init(minHeap)
	for _, num := range nums {
		heap.Push(minHeap, num)
		if minHeap.Len() > k {
			heap.Pop(minHeap)
		}
	}
	return heap.Pop(minHeap).(int)
}

/*
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5

示例 2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4

提示：
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
*/

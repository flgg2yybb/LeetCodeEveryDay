package main

import (
	"container/heap"
	"fmt"
)

func main() {
	nums1 := []int{1, 1, 1, 2, 2, 3}
	k1 := 2
	println(fmt.Sprintf("%v", topKFrequent(nums1, k1))) // [1,2]
	nums2 := []int{1}
	k2 := 1
	println(fmt.Sprintf("%v", topKFrequent(nums2, k2))) // [1]
	nums3 := []int{1, 2}
	k3 := 2
	println(fmt.Sprintf("%v", topKFrequent(nums3, k3))) // [1,2]
}

type MinHeap []entry

func (h MinHeap) Len() int {
	return len(h)
}

func (h MinHeap) Less(i, j int) bool {
	return h[i].freq < h[j].freq
}

func (h MinHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(entry))
}

func (h *MinHeap) Pop() interface{} {
	arr := *h
	pop := arr[len(arr)-1]
	*h = arr[:len(arr)-1]
	return pop
}

type entry struct {
	num  int
	freq int
}

// 最小堆，times: O(nlogk)，space: O(n)
func topKFrequent(nums []int, k int) []int {
	num2FreqMap := make(map[int]int)
	for _, num := range nums {
		num2FreqMap[num]++
	}
	// 维护一个大小为 k 的最小堆，存储频率 freq 筛选出前 k 个高频元素的频率
	maxHeap := &MinHeap{}
	heap.Init(maxHeap)
	for num, freq := range num2FreqMap {
		heap.Push(maxHeap, entry{num: num, freq: freq})
		if maxHeap.Len() > k {
			heap.Pop(maxHeap)
		}
	}
	ans := make([]int, 0)
	for maxHeap.Len() > 0 {
		e := heap.Pop(maxHeap).(entry)
		ans = append(ans, e.num)
	}
	return ans
}

/*
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。



示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]


提示：

1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的


进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
*/

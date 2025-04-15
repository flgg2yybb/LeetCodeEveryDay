package main

import (
	"container/heap"
	"fmt"
	rand2 "math/rand/v2"
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

// Partition，times: O(n)，space: O(n)
func topKFrequent(nums []int, k int) []int {
	num2FreqMap := make(map[int]int)
	for _, num := range nums {
		num2FreqMap[num]++
	}
	freqs := make([]int, 0, len(num2FreqMap))
	for _, freq := range num2FreqMap {
		freqs = append(freqs, freq)
	}
	threshold := getKthMaxValue(freqs, k)
	ans := make([]int, 0, k)
	for num, freq := range num2FreqMap {
		if freq >= threshold {
			ans = append(ans, num)
		}
	}
	return ans
}

// 平均情况下，时间复杂度为 O(N)，最坏时间复杂度：O(N^2)
// 设处理长度为 N 的数组的时间复杂度为 f(N)。由于处理的过程包括一次遍历和一次子分支的递归
//   - 最好情况下，有 f(N)=O(N)+f(N/2)，根据 主定理，能够得到 f(N)=O(N)。
//   - 最坏情况下，每次取的中枢数组的元素都位于数组的两端，时间复杂度退化为 O(N^2 )。但由于每次递归会先随机选取中枢元素，故出现最坏情况的概率很低
//   - 平均情况下，时间复杂度为 O(N)
func getKthMaxValue(freqs []int, k int) int {
	start, end := 0, len(freqs)-1
	if k-1 < start || k-1 > end {
		panic("k out of range")
	}
	for start < end {
		mid := partition(freqs, start, end)
		if mid == k-1 {
			return freqs[mid]
		} else if mid > k-1 {
			end = mid - 1
		} else { // mid < k-1
			start = mid + 1
		}
	}
	return freqs[start]
}

func partition(freqs []int, start int, end int) int {
	rand := rand2.IntN(end-start+1) + start
	freqs[start], freqs[rand] = freqs[rand], freqs[start]
	pivot := freqs[start]
	left, right := start, end
	for left < right { // 从大到小排序
		for left < right && freqs[right] <= pivot {
			right--
		}
		freqs[left] = freqs[right] // 此处 right 不用赋值，最终找到 pivot index 后会赋值上 pivot
		for left < right && freqs[left] >= pivot {
			left++
		}
		freqs[right] = freqs[left]
	}
	freqs[left] = pivot
	return left
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
func topKFrequent1(nums []int, k int) []int {
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

package main

import "container/heap"

func main() {
	mf := Constructor()
	mf.AddNum(1)
	mf.AddNum(2)
	println(mf.FindMedian()) // 1.5
	mf.AddNum(3)
	println(mf.FindMedian()) // 2.0
}

type iHeap struct {
	elements []int
	asc      bool
}

func newIHeap(asc bool) *iHeap {
	return &iHeap{elements: make([]int, 0), asc: asc}
}

func (h *iHeap) Len() int {
	return len(h.elements)
}

func (h *iHeap) Less(i, j int) bool {
	if h.asc { // 递增 => 最小堆
		return h.elements[i] < h.elements[j]
	} else { // 递减 => 最大堆
		return h.elements[i] > h.elements[j]
	}
}

func (h *iHeap) Swap(i, j int) {
	h.elements[i], h.elements[j] = h.elements[j], h.elements[i]
}

func (h *iHeap) Push(x interface{}) {
	h.elements = append(h.elements, x.(int))
}

func (h *iHeap) Pop() interface{} {
	pop := h.elements[len(h.elements)-1]
	h.elements = h.elements[:len(h.elements)-1]
	return pop
}

func (h *iHeap) Top() int {
	return h.elements[0] // heap.Interface 保证堆顶元素就是索引 0 的元素
}

/*
MedianFinder 堆，AddNum 操作 times: O(logn), FindMedian 操作 times: O(1)
思路：分别用一个最大堆，最小堆存储元素，保持最大堆和最小堆的元素数量差 <= 1
  - 插入元素时，先插入最小堆，然后最小堆弹出元素，插入最大堆，然后最大堆弹出元素，此时弹出的元素即是当前元素的中间元素
    整个流程像一个漏斗一样，为保持最小堆元素 >= 最大堆元素，则如果插入元素后，是奇数，则最后需要弹出重新插入最小堆，否则插入最大堆后无需弹出
  - 获取中位数，当前是奇数时，直接返回最小堆堆顶元素，当前是偶数时，返回两个堆的堆顶元素的平均数
*/
type MedianFinder struct {
	minHeap *iHeap
	maxHeap *iHeap
}

func Constructor() MedianFinder {
	minHeap := newIHeap(true)
	heap.Init(minHeap)
	maxHeap := newIHeap(false)
	heap.Init(maxHeap)
	return MedianFinder{
		minHeap: minHeap, // 最小堆
		maxHeap: maxHeap, // 最大堆
	}
}

func (this *MedianFinder) AddNum(num int) {
	heap.Push(this.minHeap, num)                 // 先插入最小堆
	pop := heap.Pop(this.minHeap)                // 弹出最小堆堆顶元素，插入最大堆
	heap.Push(this.maxHeap, pop)                 // 再插入最大堆
	if this.minHeap.Len() < this.maxHeap.Len() { // 保持最小堆元素数量 >= 最大堆元素数量
		pop = heap.Pop(this.maxHeap) // 弹出最大堆堆顶元素，插入最小堆
		heap.Push(this.minHeap, pop)
	}
}

func (this *MedianFinder) FindMedian() float64 {
	if (this.minHeap.Len()+this.maxHeap.Len())%2 == 1 { //奇数
		return float64(this.minHeap.Top())
	}
	return (float64(this.minHeap.Top()) + float64(this.maxHeap.Top())) / 2.0 // 偶数
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */

/*
中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。

例如 arr = [2,3,4] 的中位数是 3 。
例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
实现 MedianFinder 类:

MedianFinder() 初始化 MedianFinder 对象。

void addNum(int num) 将数据流中的整数 num 添加到数据结构中。

double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。

示例 1：

输入
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
输出
[null, null, null, 1.5, null, 2.0]

解释
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
提示:

-105 <= num <= 105
在调用 findMedian 之前，数据结构中至少有一个元素
最多 5 * 104 次调用 addNum 和 findMedian
*/

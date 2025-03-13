package main

import (
	"container/heap"
	"fmt"
)

func main() {
	lists1 := []*ListNode{
		{Val: 1, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5}}},
		{Val: 1, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4}}},
		{Val: 2, Next: &ListNode{Val: 6}},
	}
	lists2 := []*ListNode{
		{Val: 1},
		{Val: 0},
	}
	lists3 := []*ListNode{nil, nil, nil}
	fmt.Println(mergeKLists(lists1).toString())
	fmt.Println(mergeKLists(lists2).toString())
	fmt.Println(mergeKLists(lists3).toString())
}

type MinHeap []*ListNode

func (h MinHeap) Len() int {
	return len(h)
}

func (h MinHeap) Less(i, j int) bool {
	return h[i].Val < h[j].Val
}

func (h MinHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(*ListNode))
}

func (h *MinHeap) Pop() (x interface{}) {
	arr := *h
	n := len(arr)
	x = arr[n-1]   // 取出最后一个元素
	arr[n-1] = nil // **避免内存泄漏**
	*h = arr[:n-1] // 移除最后一个元素
	return x
}

// Min Heap, times: O(logk * kn), space: O(k)
// k 个链表，每个链表平均长度为 n
func mergeKLists(lists []*ListNode) *ListNode {
	root := &ListNode{}
	minHeap := &MinHeap{} // 创建一个最小堆，需要保证里面的元素不为 nil
	heap.Init(minHeap)
	for _, list := range lists {
		if list != nil {
			heap.Push(minHeap, list)
		}
	}
	cur := root
	for minHeap.Len() > 0 { // O(kn)
		pop := heap.Pop(minHeap).(*ListNode) // O(logk)
		cur.Next = pop
		cur = cur.Next
		if pop.Next != nil {
			heap.Push(minHeap, pop.Next)
		}
	}
	return root.Next
}

// Divide and Conquer - Merge Sort, times: O(logk * kn), space: O(1)
// k 个链表，每个链表平均长度为 n
func mergeKLists2(lists []*ListNode) *ListNode {
	if len(lists) == 0 {
		return nil
	}
	k := len(lists)
	for k > 1 { // 循环 k 次
		newIndex := 0
		for i := 0; i < k; i += 2 { // 循环 k/2 次
			l1 := lists[i]
			l2 := (*ListNode)(nil)
			if i != k-1 { // k 为奇数时，最后一个链表不需要合并
				l2 = lists[i+1]
			}
			// 原地替换，不需要额外空间
			lists[newIndex] = mergeTwoLists(l1, l2) // 循环 2n 次
			newIndex++
		}
		k = newIndex
	}
	return lists[0]
}

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	root := &ListNode{}
	cur := root
	for l1 != nil && l2 != nil {
		if l1.Val < l2.Val {
			cur.Next = l1
			l1 = l1.Next
		} else {
			cur.Next = l2
			l2 = l2.Next
		}
		cur = cur.Next
	}
	if l1 != nil {
		cur.Next = l1
	} else {
		cur.Next = l2
	}
	return root.Next
}

// Merge Sort LinkedList, times: O(n*k^2), space: O(1)
// k 个链表，每个链表平均长度为 n
func mergeKLists1(lists []*ListNode) *ListNode {
	root := &ListNode{}
	cur := root
	for { // 循环 kn 次
		minIndex := -1
		for i, list := range lists { // 循环 k 次
			if list == nil {
				continue
			}
			if minIndex == -1 || list.Val < lists[minIndex].Val {
				minIndex = i
			}
		}
		if minIndex == -1 {
			break
		}
		cur.Next = lists[minIndex]
		cur = cur.Next
		lists[minIndex] = lists[minIndex].Next
	}
	return root.Next
}

type ListNode struct {
	Val  int
	Next *ListNode
}

func (l *ListNode) toString() string {
	if l == nil {
		return "nil"
	}
	return fmt.Sprintf("%d->%s", l.Val, l.Next.toString())
}

/*
* 给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。

示例 1：
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6

示例 2：
输入：lists = []
输出：[]

示例 3：
输入：lists = [[]]
输出：[]

提示：
k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4
* */

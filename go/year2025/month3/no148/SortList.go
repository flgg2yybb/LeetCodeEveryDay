package main

import "fmt"

func main() {
	head1 := &ListNode{4, &ListNode{2, &ListNode{1, &ListNode{3, nil}}}}
	println(sortList(head1).toString()) // 1->2->3->4
	head2 := &ListNode{-1, &ListNode{5, &ListNode{3, &ListNode{4, &ListNode{0, nil}}}}}
	println(sortList(head2).toString()) // -1->0->3->4->5
}

// Merge Sort, times: O(nlogn), space: O(1)
func sortList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	// split
	l1, l2 := splitLinkedList(head)
	// sort
	sortedL1 := sortList(l1)
	sortedL2 := sortList(l2)
	// merge
	return mergeLinkedList(sortedL1, sortedL2)
}

func splitLinkedList(head *ListNode) (*ListNode, *ListNode) {
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}
	next := slow.Next
	slow.Next = nil
	return head, next
}

func mergeLinkedList(l1, l2 *ListNode) *ListNode {
	root := &ListNode{Val: -1}
	p := root
	for l1 != nil && l2 != nil {
		if l1.Val < l2.Val {
			p.Next = l1
			l1 = l1.Next
		} else {
			p.Next = l2
			l2 = l2.Next
		}
		p = p.Next
	}
	if l1 != nil {
		p.Next = l1
	}
	if l2 != nil {
		p.Next = l2
	}
	return root.Next
}

// Insertion Sort, times: O(n^2), space: O(1)
func sortList1(head *ListNode) *ListNode {
	root := &ListNode{Val: -1}
	p := head
	for p != nil {
		cur := root
		// 找到插入位置
		for cur.Next != nil {
			if cur.Next.Val > p.Val {
				break
			}
			cur = cur.Next
		}
		next := cur.Next
		cur.Next = p
		p = p.Next
		cur.Next.Next = next
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
 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

示例 1：
输入：head = [4,2,1,3]
输出：[1,2,3,4]

示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]

示例 3：
输入：head = []
输出：[]

提示：
链表中节点的数目在范围 [0, 5 * 104] 内
-105 <= Node.val <= 105

进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
*/

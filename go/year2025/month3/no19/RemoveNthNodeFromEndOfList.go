package main

import "fmt"

func main() {
	l1 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5}}}}}
	println(removeNthFromEnd(l1, 2).toString())
	l2 := &ListNode{Val: 1}
	println(removeNthFromEnd(l2, 1).toString())
	l3 := &ListNode{Val: 1, Next: &ListNode{Val: 2}}
	println(removeNthFromEnd(l3, 1).toString())
}

// Two Pointer, times: O(n), space: O(1)
// 快慢指针遍历链表，快指针先走 n 步，慢指针才开始走，当快指针到达链表最后一个节点时，慢指针指向倒数第 n + 1 个节点
// PS: 需要保证 head 和 n 有效
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	root := &ListNode{Next: head}
	slow, fast := root, root
	// 快指针先走 n 步
	for i := 0; i < n; i++ {
		fast = fast.Next
	}
	// 快慢指针同时走，直到快指针到达链表最后一个节点，此时慢指针指向倒数第 n + 1 个节点
	for fast.Next != nil {
		slow = slow.Next
		fast = fast.Next
	}
	// 移除倒数第 n 个节点
	slow.Next = slow.Next.Next
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
* 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]

提示：
链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

进阶：你能尝试使用一趟扫描实现吗？
* */

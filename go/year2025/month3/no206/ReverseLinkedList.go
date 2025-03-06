package main

import "fmt"

func main() {
	// 5 -> 4 -> 3 -> 2 -> 1
	head1 := &ListNode{Val: 5, Next: &ListNode{Val: 4, Next: &ListNode{Val: 3, Next: &ListNode{Val: 2, Next: &ListNode{Val: 1}}}}}
	display(reverseList(head1)) // 1 -> 2 -> 3 -> 4 -> 5
	// 1 -> 2
	head2 := &ListNode{Val: 1, Next: &ListNode{Val: 2}}
	display(reverseList(head2)) // 2 -> 1
	// nil
	head3 := (*ListNode)(nil)
	display(reverseList(head3)) // nil
}

type ListNode struct {
	Val  int
	Next *ListNode
}

// recursion, time: O(n), space: O(1)
/* 思路：将设当前需要反转以 head 为头的链表，如果 head.Next 为头的链表已经反转完成，
		那么只需要将 head.Next.Next = head，head.Next = nil 即可
		PS：因为是递归，需要在最底层一层层往上返回
考虑初始值：
	* 最底层：当 head 为 nil 时，返回 nil
	* 倒数第二层：当 head.Next 为 nil 时，返回 head

倒数第三层，当前链表为：5 -> 4 -> 3 -> 2 -> 1
head = 2, new head = 1
操作：
	* head.Next.Next = head，即 5 -> 4 -> 3 -> 2 <-> 1
	* head.Next = nil，即 5 -> 4 -> 3 -> 2 <- 1
	* return new head，即 1

对于后续层数一样的操作，最终 new head 即为反转后的链表头
*/

func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	// 反转后续链表
	newHead := reverseList(head.Next)
	// 反转当前链表
	head.Next.Next = head
	head.Next = nil
	return newHead
}

// loop, time: O(n), space: O(1)
func reverseList1(head *ListNode) *ListNode {
	var pre *ListNode
	for head != nil {
		next := head.Next
		head.Next = pre
		pre = head
		head = next
	}
	return pre
}

func display(head *ListNode) {
	for head != nil {
		print(fmt.Sprintf("%+v ", head.Val))
		head = head.Next
	}
	println()
}

/*
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。


示例 1：


输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
示例 2：


输入：head = [1,2]
输出：[2,1]
示例 3：

输入：head = []
输出：[]


提示：

链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000


进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
*/

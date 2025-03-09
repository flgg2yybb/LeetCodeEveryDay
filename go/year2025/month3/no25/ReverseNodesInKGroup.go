package main

import "fmt"

func main() {
	head1 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5}}}}}
	k1 := 2
	fmt.Println(reverseKGroup(head1, k1).toString())
	head2 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5}}}}}
	k2 := 3
	fmt.Println(reverseKGroup(head2, k2).toString())
}

func reverseLinkedList(head *ListNode) *ListNode {
	var prev *ListNode
	cur := head
	for cur != nil {
		next := cur.Next
		cur.Next = prev
		prev = cur
		cur = next
	}
	return prev
}

// Two Pointers & Revert LinkedList, times: O(n), space: O(1)
// 思路：双指针从虚拟头节点开始遍历链表，rear 尾指针先走 k 步，如果 rear 为空，说明剩余节点不足 k 个，直接返回结果
// 如果 rear 走完 k 步不为空，则开始反转 front -> rear 之间的链表
// PS：需要提前保留反转链表前后的连接信息，便可将本题转换为反转链表
func reverseKGroup(head *ListNode, k int) *ListNode {
	root := &ListNode{Next: head}
	front := root
	rear := root
	for rear != nil {
		for i := 0; i < k; i++ {
			rear = rear.Next
			if rear == nil {
				return root.Next
			}
		}
		// rear 为当前链表的尾节点，反转后为头节点
		newRear := front.Next                     // 当前反转链表的头节点，反转后为变为尾节点
		nextFront := rear.Next                    // 下一组反转链表的头节点
		rear.Next = nil                           // 当前链表的尾节点指向 nil
		newFront := reverseLinkedList(front.Next) // 反转当前链表，并返回新的头节点
		front.Next = newFront                     // 当前链表的尾节点指向新的头节点
		newRear.Next = nextFront                  // 当前链表的头节点指向下一组反转链表的头节点
		front = newRear                           // 重置 front 为当前链表的尾节点
		rear = newRear                            // 重置 rear 为当前链表的尾节点
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
 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

示例 1：
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]

示例 2：
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]

提示：
链表中的节点数目为 n
1 <= k <= n <= 5000
0 <= Node.val <= 1000

进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
*/

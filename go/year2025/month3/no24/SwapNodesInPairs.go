package main

import "fmt"

func main() {
	l1 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4}}}}
	fmt.Println(swapPairs(l1).toString())
	l2 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3}}}
	fmt.Println(swapPairs(l2).toString())
	l3 := &ListNode{Val: 1}
	fmt.Println(swapPairs(l3).toString())
}

// Swap LinkedList, times: O(n), space: O(1)
func swapPairs(head *ListNode) *ListNode {
	root := &ListNode{Next: head}
	cur := root
	for cur.Next != nil && cur.Next.Next != nil {
		node1 := cur.Next
		node2 := cur.Next.Next
		next := cur.Next.Next.Next
		cur.Next = node2
		cur.Next.Next = node1
		cur.Next.Next.Next = next
		cur = node1 // 需要移动到下一组的前一个节点
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
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

示例 1：
输入：head = [1,2,3,4]
输出：[2,1,4,3]

示例 2：
输入：head = []
输出：[]

示例 3：
输入：head = [1]
输出：[1]

提示：
链表中节点的数目在范围 [0, 100] 内
0 <= Node.val <= 100
*/

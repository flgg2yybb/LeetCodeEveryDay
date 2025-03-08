package main

import "fmt"

func main() {
	l1 := &ListNode{Val: 2, Next: &ListNode{Val: 4, Next: &ListNode{Val: 3}}}
	l2 := &ListNode{Val: 5, Next: &ListNode{Val: 6, Next: &ListNode{Val: 4}}}
	fmt.Println(addTwoNumbers(l1, l2).toString())
	l1 = &ListNode{Val: 0}
	l2 = &ListNode{Val: 0}
	fmt.Println(addTwoNumbers(l1, l2).toString())
	l1 = &ListNode{9, &ListNode{9, &ListNode{9, &ListNode{9, &ListNode{9, &ListNode{9, &ListNode{9, nil}}}}}}}
	l2 = &ListNode{9, &ListNode{9, &ListNode{9, &ListNode{9, nil}}}}
	fmt.Println(addTwoNumbers(l1, l2).toString())
}

// clean code, times is O(m+n), space is O(m+n)
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	head := &ListNode{Val: 1}
	cur := head
	carry := 0
	for l1 != nil || l2 != nil || carry != 0 {
		value1 := 0
		value2 := 0
		if l1 != nil {
			value1 = l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			value2 = l2.Val
			l2 = l2.Next
		}
		newValue := value1 + value2 + carry
		carry = newValue / 10
		newValue %= 10
		cur.Next = &ListNode{Val: newValue}
		cur = cur.Next
	}
	return head.Next

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
* 给你两个 非空 的链表，表示两个非负的整数。
它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例 1：
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.

示例 2：
输入：l1 = [0], l2 = [0]
输出：[0]

示例 3：
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]

提示：
每个链表中的节点数在范围 [1, 100] 内
0 <= Node.val <= 9
题目数据保证列表表示的数字不含前导零
* */

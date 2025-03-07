package main

func main() {
	head1 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 2, Next: &ListNode{Val: 1}}}}
	println(isPalindrome(head1)) // true
	head2 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 2, Next: &ListNode{Val: 1}}}}}
	println(isPalindrome(head2)) // true
	head3 := &ListNode{Val: 1, Next: &ListNode{Val: 2}}
	println(isPalindrome(head3)) // false
	head4 := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3}}}
	println(isPalindrome(head4)) // false
	head5 := &ListNode{Val: 1}
	println(isPalindrome(head5)) // true
}

// Fast Slow Pointer & Reverse LinkedList, times: O(n), space: O(1)
// 思路：如果链表是回文的，那么链表的前半部分和链表的后半部分是对称的，
// 所以我们可以通过快慢指针找到链表的中点，然后将链表的后半部分反转，最后比较前半部分和后半部分是否相等
func isPalindrome(head *ListNode) bool {
	fast, slow := head, head
	for fast != nil && fast.Next != nil {
		fast = fast.Next.Next
		slow = slow.Next
	}
	var rear *ListNode
	if fast == nil { // fast == nil，说明链表长度为偶数，当前 slow 指向的是后半部分的第一个节点
		rear = reverseLinkedList(slow)
	} else { // fast.next == nil，说明链表长度为奇数，当前 slow 指向正中间的点（对称点）
		rear = reverseLinkedList(slow.Next)
	}
	front := head
	// 分别从前，后往中间遍历判断是否回文
	for front != nil && rear != nil {
		if front.Val != rear.Val {
			return false
		}
		front = front.Next
		rear = rear.Next
	}
	return true
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

type ListNode struct {
	Val  int
	Next *ListNode
}

/*
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。

示例 1：

输入：head = [1,2,2,1]
输出：true
示例 2：

输入：head = [1,2]
输出：false

提示：

链表中节点数目在范围[1, 105] 内
0 <= Node.val <= 9

进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
*/

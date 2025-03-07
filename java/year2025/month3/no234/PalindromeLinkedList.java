package year2025.month3.no234;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(1)))));
        ListNode head3 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(1)))));
        ListNode head4 = new ListNode(1, new ListNode(2));
        ListNode head5 = new ListNode(1, new ListNode(1));
        System.out.println(isPalindrome(head1));
        System.out.println(isPalindrome(head2));
        System.out.println(isPalindrome(head3));
        System.out.println(isPalindrome(head4));
        System.out.println(isPalindrome(head5));

    }

    // Fast Slow Pointer & Reverse LinkedList, times: O(n), space: O(1)
    // 思路：如果链表是回文的，那么链表的前半部分和链表的后半部分是对称的，
    // 所以我们可以通过快慢指针找到链表的中点，然后将链表的后半部分反转，最后比较前半部分和后半部分是否相等
    public static boolean isPalindrome(ListNode head) {
        // 快慢指针找到链表的中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode rear;
        if (fast == null) { // 链表长度为偶数, slow指向后半部分的头节点
            rear = reverseLinkedList(slow);
        } else {    // fast.next == null, 链表长度为奇数, slow 为中间节点（对称点）
            rear = reverseLinkedList(slow.next);
        }
        ListNode front = head;
        // 因为链表的后半部分已经反转，且反转后的后半部分最后尾节点为空，因此可直接用rear为空判断是否结束
        while (front != null && rear != null) {
            if (front.val != rear.val) {
                return false;
            }
            front = front.next;
            rear = rear.next;
        }
        return true;
    }

    // Reverse LinkedList
    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
    }
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


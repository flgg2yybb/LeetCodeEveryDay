package year2021.month3.no234;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        ListNode head3 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(1)))));
        System.out.println(isPalindrome(head1));
        System.out.println(isPalindrome(head2));
        System.out.println(isPalindrome(head3));
    }

    // 给定链表不为空
    public static boolean isPalindrome(ListNode head) {
        //先用快慢指针找到链表中部节点，将后半部分反转，在分别从头和中部开始向后遍历判断元素是否相等即可
        ListNode root = new ListNode(-1, head);
        ListNode slow = root;
        ListNode fast = root;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        reverseLinkedList(slow);
        ListNode p = root.next;
        slow = slow.next;
        while (slow != null) {
            if (slow.val != p.val) {
                return false;
            }
            slow = slow.next;
            p = p.next;
        }
        return true;
    }

    private static void reverseLinkedList(ListNode root) {
        //反转root节点之后的链表
        ListNode prev = null;
        ListNode cur = root.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        root.next = prev;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/*
* 请判断一个 非空链表 是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

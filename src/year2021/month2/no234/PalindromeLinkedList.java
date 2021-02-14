package year2021.month2.no234;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head1 = null;
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(2, new ListNode(3, new ListNode(2)));
        ListNode head4 = new ListNode(2, new ListNode(3, new ListNode(3)));
        System.out.println(isPalindrome(head1));
        System.out.println(isPalindrome(head2));
        System.out.println(isPalindrome(head3));
        System.out.println(isPalindrome(head4));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next) {
            list.add(node.val);
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
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
 * 请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

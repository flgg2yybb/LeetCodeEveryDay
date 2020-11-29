package twenty.november.no206;

import java.util.Objects;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode result = solution1(head);
        ListNode result = solution2(head);
        while (Objects.nonNull(result)) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    private static ListNode solution2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = solution2(head.next);
        head.next.next = head;
//        防止第一层栈的元素（反转前第一个元素）任然指向第二个元素，而不是指向NULL，会使链表有环
        head.next = null;
        return cur;
    }

    private static ListNode solution1(ListNode head) {
        ListNode prev = null;
        ListNode temp;
        while (Objects.nonNull(head)) {
            temp = head;
            head = head.next;
            temp.next = prev;
            prev = temp;
        }
        return prev;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }

    ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }
}

/*
* 反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

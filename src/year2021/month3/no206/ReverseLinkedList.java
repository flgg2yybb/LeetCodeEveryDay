package year2021.month3.no206;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = null;
        ListNode head3 = new ListNode(2);
        disp(reverseList(head1));
        disp(reverseList(head2));
        disp(reverseList(head3));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode root = null;
        ListNode p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = root;
            root = p;
            p = next;
        }
        return root;
    }

    private static void disp(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val + "->");
        }
        System.out.println("NULL");
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

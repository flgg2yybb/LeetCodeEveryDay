package year2021.month1.no19;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int n1 = 2;
        ListNode listNode2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int n2 = 5;
        ListNode listNode3 = new ListNode(1);
        int n3 = 1;
        ListNode listNode4 = null;
        int n4 = 0;
        disp(removeNthFromEnd(listNode1, n1));
        disp(removeNthFromEnd(listNode2, n2));
        disp(removeNthFromEnd(listNode3, n3));
        disp(removeNthFromEnd(listNode4, n4));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode left = head;
        ListNode right = head;
        for (int i = 0; i < n && right != null; i++) {
            right = right.next;
        }
        if (right == null) {
            return left.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return head;
    }

    private static void disp(ListNode listNode) {
        if (listNode == null) {
            System.out.println("EMPTY");
            return;
        }
        ListNode p = listNode;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
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
* 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

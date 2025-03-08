package year2025.month3.no19;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int n1 = 2;
        ListNode head2 = new ListNode(1);
        int n2 = 1;
        ListNode head3 = new ListNode(1, new ListNode(2));
        int n3 = 1;
        System.out.println(removeNthFromEnd(head1, n1));
        System.out.println(removeNthFromEnd(head2, n2));
        System.out.println(removeNthFromEnd(head3, n3));
    }

    // Two Pointer, times: O(n), space: O(1)
    // 快慢指针遍历链表，快指针先走 n 步，慢指针才开始走，当快指针到达链表最后一个节点时，慢指针指向倒数第 n + 1 个节点
    // PS: 需要保证 head 和 n 有效
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root = new ListNode(-1, head);
        ListNode slow = root;
        ListNode fast = root;
        // 快指针先走 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 快慢指针同时走，直到快指针到达链表最后一个节点，此时慢指针指向倒数第 n + 1 个节点
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 移除倒数第 n 个节点
        slow.next = slow.next.next;
        return root.next;
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

    @Override
    public String toString() {
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
    }
}

/*
* 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]

提示：
链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

进阶：你能尝试使用一趟扫描实现吗？
* */

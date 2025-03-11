package year2025.month3.no148;

public class SortList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode head2 = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
        ListNode head3 = null;
        System.out.println(sortList(head1));
        System.out.println(sortList(head2));
        System.out.println(sortList(head3));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode[] nodes = splitLinkedList(head);
        ListNode left = sortList(nodes[0]);
        ListNode right = sortList(nodes[1]);
        return mergeSortedList(left, right);
    }

    private static ListNode mergeSortedList(ListNode left, ListNode right) {
        ListNode root = new ListNode(-1);
        ListNode p = root;
        while (left != null && right != null) {
            if (left.val < right.val) {
                p.next = left;
                left = left.next;
            } else {
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        if (left != null) {
            p.next = left;
        }
        if (right != null) {
            p.next = right;
        }
        return root.next;
    }

    public static ListNode[] splitLinkedList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 需要保证当 head 为两个元素的链表时，能拆成两个链表，故需要判断 fast.next.next != null
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        return new ListNode[]{head, next};
    }

    // Insertion Sort, time is O(n^2), space is O(1)
    public static ListNode sortList1(ListNode head) {
        ListNode root = new ListNode(-1);
        ListNode p = head;
        while (p != null) {
            ListNode cur = root;
            // 找到插入位置
            while (cur.next != null && cur.next.val < p.val) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = p;
            p = p.next;
            cur.next.next = next;
        }
        return root.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
    }
}




/*
 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

示例 1：
输入：head = [4,2,1,3]
输出：[1,2,3,4]

示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]

示例 3：
输入：head = []
输出：[]

提示：
链表中节点的数目在范围 [0, 5 * 104] 内
-105 <= Node.val <= 105

进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
*/

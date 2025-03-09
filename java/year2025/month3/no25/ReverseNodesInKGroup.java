package year2025.month3.no25;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k1 = 2;
        int k2 = 3;
        System.out.println(reverseKGroup(head1, k1));
        System.out.println(reverseKGroup(head2, k2));
    }

    // Two Pointers & Revert LinkedList, times: O(n), space: O(1)
    // 思路：双指针从虚拟头节点开始遍历链表，rear 尾指针先走 k 步，如果 rear 为空，说明剩余节点不足 k 个，直接返回结果
    // 如果 rear 走完 k 步不为空，则开始反转 front -> rear 之间的链表
    // PS：需要提前保留反转链表前后的连接信息，便可将本题转换为反转链表
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode root = new ListNode(-1, head);
        ListNode start = root;
        ListNode end = root;
        while (end != null) {
            for (int i = 0; i < k; i++) {
                end = end.next;
                if (end == null) {
                    return root.next;
                }
            }
            ListNode newRear = start.next;  // 反转链表后的尾节点
            ListNode nextFront = end.next;  // 下一个反转链表的头节点
            end.next = null;                // 断开反转链表后续节点的连接
            ListNode newFront = reverseLinkedList(start.next);  // 反转链表
            start.next = newFront;
            newRear.next = nextFront;
            start = newRear;
            end = start;
        }
        return root.next;
    }

    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;   // 反转链表，下个节点指向当前节点
        head.next = null;        // 当前节点指向为空
        return newHead;
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
 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

示例 1：
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]

示例 2：
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]

提示：
链表中的节点数目为 n
1 <= k <= n <= 5000
0 <= Node.val <= 1000

进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
*/


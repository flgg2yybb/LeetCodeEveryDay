package year2025.month3.no24;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode head3 = new ListNode(1);
        System.out.println(swapPairs(head1));
        System.out.println(swapPairs(head2));
        System.out.println(swapPairs(head3));
    }

    // Swap LinkedList, times: O(n), space: O(1)
    public static ListNode swapPairs(ListNode head) {
        ListNode root = new ListNode(-1, head);
        ListNode cur = root;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;
            ListNode next = cur.next.next.next;
            cur.next = second;
            cur.next.next = first;
            cur.next.next.next = next;
            cur = first; // 需要移动到下一组的前一个节点
        }
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
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

示例 1：
输入：head = [1,2,3,4]
输出：[2,1,4,3]

示例 2：
输入：head = []
输出：[]

示例 3：
输入：head = [1]
输出：[1]

提示：
链表中节点的数目在范围 [0, 100] 内
0 <= Node.val <= 100
*/

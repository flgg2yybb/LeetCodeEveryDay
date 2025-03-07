package year2025.month3.no141;

public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4))));
        head1.next.next.next.next = head1.next;
        System.out.println(hasCycle(head1));
        ListNode head2 = new ListNode(1, new ListNode(2));
        head2.next.next = head2;
        System.out.println(hasCycle(head2));
        ListNode head3 = new ListNode(1);
        System.out.println(hasCycle(head3));
    }

    // Fast Slow Pointer, times: O(n), space: O(1)
    // 思路：快慢指针，如果有环一定回相遇
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            // 快指针每次走两步，慢指针每次走一步
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
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
给你一个链表的头节点 head ，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
如果链表中存在环 ，则返回 true 。 否则，返回 false 。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。

示例 2：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。

示例 3：
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。

提示：

链表中节点的数目范围是 [0, 104]
-105 <= Node.val <= 105
pos 为 -1 或者链表中的一个 有效索引 。

进阶：你能用 O(1)（即，常量）内存解决此问题吗？
*/


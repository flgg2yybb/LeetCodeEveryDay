package year2025.month3.no142;

public class LinkedListCycleII {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4))));
        head1.next.next.next.next = head1.next;
        System.out.println(detectCycle(head1));
        ListNode head2 = new ListNode(1, new ListNode(2));
        head2.next.next = head2;
        System.out.println(detectCycle(head2));
        ListNode head3 = new ListNode(1);
        System.out.println(detectCycle(head3));
    }

    /*
     * 先用快慢指针判断是否有环，若有环，则比在环内相遇，记为点 C
     * 起点记为点 A，环入口交点记为点 B，直线 AB 距离记为 x，
     * 弧 BC 长度记为 y，弧 CB 长度记为 z
     * 假设快慢指针速度有，
     * 快指针 = 2v；慢指针 = v；在 t 时刻于点 C 相遇，有
     * 快指针：2vt = x + y + n(y + z), (n为快指针在环内绕圈数)
     * 慢指针：vt  = x + y，(慢指针必在第一圈与快指针相遇)
     * 则有：x + y = n(y + z)，简化有
     * x = z + (n - 1)(z + y)，即
     * 若在快慢指针相遇时，让慢指针从起点以速度 v 出发，
     * 快指针在相遇点以速度 v 接着行走，则
     * 快慢指针必在起点相遇
     * */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) { // 相遇，则代表有环
                slow = head;            // slow 从头开始走，fast 在相遇点开始走，都是一步一步走
                while (slow != fast) {  // 再次相遇时，即为环的入口
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
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
        return "ListNode{" + "val=" + val + "}";
    }
}


/*
给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
不允许修改 链表。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。

示例 2：
输入：head = [1,2], pos = 0
输出：返回索引为 0 的链表节点
解释：链表中有一个环，其尾部连接到第一个节点。

示例 3：
输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。

提示：
链表中节点的数目范围在范围 [0, 104] 内
-105 <= Node.val <= 105
pos 的值为 -1 或者链表中的一个有效索引

进阶：你是否可以使用 O(1) 空间解决此题？
*/

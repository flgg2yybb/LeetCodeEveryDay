package twenty.november.no142;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle2 {
    public static void main(String[] args) {
        ListNode head = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4))));
        int pos = 2;
        createCycle(head, pos);
//        System.out.println(solution1(head));
//        System.out.println(solution2(head));
        System.out.println(solution3(head));
    }

    private static ListNode solution3(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
//        让slow指针回到链表原点，fast指针调整为一次走一步，
//        则slow指针与fast指针会在入环口相遇
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode solution2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode cycleEnter = null;
        boolean encountered = false;
        while (slow != cycleEnter) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (encountered) {
                cycleEnter = cycleEnter.next;
            }
            if (fast == slow && !encountered) {
                encountered = true;
                cycleEnter = head;
            }
        }
        return cycleEnter;
    }

    private static ListNode solution1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    private static void createCycle(ListNode head, int pos) {
        if (pos < 0 || head == null) {
            return;
        }
        ListNode cycleNode = null;
        ListNode tail = head;
        ListNode curr = head;
        int currIndex = 0;
        while (curr != null) {
            if (currIndex == pos) {
                cycleNode = curr;
            }
            if (curr.next == null) {
                tail = curr;
            }
            curr = curr.next;
            currIndex++;
        }
        tail.next = cycleNode;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
        this.next = null;
    }

    ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}

/*
* 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

说明：不允许修改给定的链表。

进阶：

你是否可以使用 O(1) 空间解决此题？
 

示例 1：



输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
示例 2：



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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
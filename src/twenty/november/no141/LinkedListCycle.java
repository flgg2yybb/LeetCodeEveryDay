package twenty.november.no141;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode head = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4))));
        int pos = 3;
        createCycle(head, pos);
//        System.out.println(solution1(head));
        System.out.println(solution2(head));
//        disp(head);

    }

//    快慢指针，慢的走两步，快的走一步，如果存在环，那么快慢指针必定在环内相遇
    private static boolean solution2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    private static boolean solution1(ListNode head) {
        Set<ListNode> listNodeSet = new HashSet<>();
        while (head != null) {
            if (listNodeSet.contains(head)) {
                return true;
            }
            listNodeSet.add(head);
            head = head.next;
        }
        return false;
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

    private static void disp(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
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
}

/*
* 给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

 

进阶：

你能用 O(1)（即，常量）内存解决此问题吗？

 

示例 1：



输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
示例 2：



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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/linked-list-cycle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
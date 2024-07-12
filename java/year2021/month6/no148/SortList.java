package year2021.month6.no148;

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
        return mergeSortForLinkedList(head);
    }

    private static ListNode mergeSortForLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //此处需保证当 head 只有两个元素时，mid为第一个元素，否则 left == head，而 right == null，死循环
        ListNode mid = findMiddleListNode(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        ListNode sortedLeft = mergeSortForLinkedList(left);
        ListNode sortedRight = mergeSortForLinkedList(right);
        return mergeTwoSortedLinkedList(sortedLeft, sortedRight);
    }

    private static ListNode findMiddleListNode(ListNode head) {
        // 引入虚拟头结点的目的即是在head节点数为两个时，中点为第一个节点
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode mergeTwoSortedLinkedList(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode(-1);
        ListNode rear = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                rear.next = l1;
                l1 = l1.next;
            } else {
                rear.next = l2;
                l2 = l2.next;
            }
            rear = rear.next;
            rear.next = null;
        }
        rear.next = l1 == null ? l2 : l1;
        return head.next;
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
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

/*
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 

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

链表中节点的数目在范围 [0, 5 * 104] 内
-105 <= Node.val <= 105


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month9.no148;

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
        // Merge Sort, time is O(nlogn), space is O(logn)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode[] lists = seperateToTwoList(head);
        ListNode left = sortList(lists[0]);
        ListNode right = sortList(lists[1]);
        return mergeTwoSortedList(left, right);
    }

    private static ListNode mergeTwoSortedList(ListNode left, ListNode right) {
        ListNode root = new ListNode(-1);
        ListNode rear = root;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                rear.next = left;
                left = left.next;
            } else {
                rear.next = right;
                right = right.next;
            }
            rear = rear.next;
        }
        if (left != null) {
            rear.next = left;
        }
        if (right != null) {
            rear.next = right;
        }
        return root.next;
    }

    private static ListNode[] seperateToTwoList(ListNode head) {
        ListNode root = new ListNode(-1, head);
        ListNode fast = root;
        ListNode slow = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        return new ListNode[]{head, mid};
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
* 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 

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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month11.no148;

public class SortList {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode list2 = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
        System.out.println(sortList(list1));
        System.out.println(sortList(list2));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode[] twoNodes = separateLinkedList(head);
        ListNode left = twoNodes[0];
        ListNode right = twoNodes[1];
        ListNode sortedLeft = sortList(left);
        ListNode sortedRight = sortList(right);
        return mergeTwoSortedLinkedList(sortedLeft, sortedRight);
    }

    private static ListNode mergeTwoSortedLinkedList(ListNode left, ListNode right) {
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

    private static ListNode[] separateLinkedList(ListNode head) {
        ListNode root = new ListNode(-1, head);
        ListNode slow = root;
        ListNode fast = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        return new ListNode[]{head, right};
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

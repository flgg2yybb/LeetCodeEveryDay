package year2021.month5.no148;

public class SortList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode head2 = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
        System.out.println(sortList(head1));
        System.out.println(sortList(head2));
    }

    public static ListNode sortList(ListNode head) {
        // 链表归并排序，先拆分后合并
        // time is O(nlogn), space is O(logn)
        // 用快慢指针找到链表的中部，分为两段链表
        // 分别对两段链表进行排序（递归）
        // 合并两个链表
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMiddleNode(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        ListNode sortLeftList = sortList(left);
        ListNode sortRightList = sortList(right);
        return mergeTwoSortedLists(sortLeftList, sortRightList);
    }

    private static ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        ListNode root = new ListNode(-1);
        ListNode rear = root;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                rear.next = list1;
                list1 = list1.next;
            } else {
                rear.next = list2;
                list2 = list2.next;
            }
            rear = rear.next;
        }
        rear.next = list1 == null ? list2 : list1;
        return root.next;
    }

    private static ListNode findMiddleNode(ListNode head) {
        ListNode root = new ListNode(-1, head);
        ListNode fast = root;
        ListNode slow = fast;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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

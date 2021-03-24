package year2021.month3.no19;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int n1 = 2;
        ListNode head2 = new ListNode(1);
        int n2 = 1;
        ListNode head3 = new ListNode(1, new ListNode(2));
        int n3 = 1;
        ListNode head4 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int n4 = 5;
        disp(removeNthFromEnd(head1, n1));
        disp(removeNthFromEnd(head2, n2));
        disp(removeNthFromEnd(head3, n3));
        disp(removeNthFromEnd(head4, n4));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root = new ListNode(-1, head);
        ListNode fast = head;
        ListNode slow = root;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return root.next;
    }

    private static void disp(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val + " ");
        }
        System.out.println();
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
}

/*
* 给你一个链表，删除链表的倒数第   n   个结点，并且返回链表的头结点。

进阶：你能尝试使用一趟扫描实现吗？

   

示例 1：


输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
示例 2：

输入：head = [1], n = 1
输出：[]
示例 3：

输入：head = [1,2], n = 1
输出：[1]
   

提示：

链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month8.no25;

public class ReverseKGroup {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k1 = 2;
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k2 = 3;
        ListNode head3 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k3 = 1;
        ListNode head4 = new ListNode(1);
        int k4 = 1;
        System.out.println(reverseKGroup(head1, k1));
        System.out.println(reverseKGroup(head2, k2));
        System.out.println(reverseKGroup(head3, k3));
        System.out.println(reverseKGroup(head4, k4));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode root = new ListNode(-1, head);
        ListNode prevList = root;
        ListNode p = root;
        int count = 0;
        while (p != null) {
            if (count > 0 && count % k == 0) {
                ListNode postList = p.next;
                ListNode start = prevList.next;
                prevList.next = null;
                p.next = null;
                ListNode[] nodes = reverseListNode(start);
                ListNode newHead = nodes[0];
                ListNode newRear = nodes[1];
                prevList.next = newHead;
                newRear.next = postList;
                prevList = newRear;
                p = newRear;
            }
            p = p.next;
            count++;
        }
        return root.next;
    }

    private static ListNode[] reverseListNode(ListNode head) {
        ListNode rear = null;
        ListNode p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = rear;
            rear = p;
            p = next;
        }
        return new ListNode[]{rear, head};
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
* 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：

你可以设计一个只使用常数额外空间的算法来解决此问题吗？
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 

示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
示例 2：


输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
示例 3：

输入：head = [1,2,3,4,5], k = 1
输出：[1,2,3,4,5]
示例 4：

输入：head = [1], k = 1
输出：[1]
提示：

列表中节点的数量在范围 sz 内
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

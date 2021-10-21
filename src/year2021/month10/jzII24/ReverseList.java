package year2021.month10.jzII24;

public class ReverseList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1, new ListNode(2));
        ListNode head3 = null;
        System.out.println(reverseList(head1));
        System.out.println(reverseList(head2));
        System.out.println(reverseList(head3));
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reverseList1(ListNode head) {
        ListNode p = head;
        ListNode newHead = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = newHead;
            newHead = p;
            p = next;
        }
        return newHead;
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
* 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。

 

示例 1：


输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
示例 2：


输入：head = [1,2]
输出：[2,1]
示例 3：

输入：head = []
输出：[]
 

提示：

链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000
 

进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/UHnkqh
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2025.month3.no206;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode head2 = new ListNode(1, new ListNode(2, null));
        System.out.println(reverseList(head1));
        System.out.println(reverseList(head2));
    }

    // recursion, time: O(n), space: O(1)
    /* 思路：将设当前需要反转以 head 为头的链表，如果 head.Next 为头的链表已经反转完成，
            那么只需要将 head.Next.Next = head，head.Next = nil 即可
            PS：因为是递归，需要在最底层一层层往上返回
    考虑初始值：
        * 最底层：当 head 为 nil 时，返回 nil
        * 倒数第二层：当 head.Next 为 nil 时，返回 head

    倒数第三层，当前链表为：5 -> 4 -> 3 -> 2 -> 1
    head = 2, new head = 1
    操作：
        * head.Next.Next = head，即 5 -> 4 -> 3 -> 2 <-> 1
        * head.Next = nil，即 5 -> 4 -> 3 -> 2 <- 1
        * return new head，即 1

    对于后续层数一样的操作，最终 new head 即为反转后的链表头
    */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    // loop, time: O(n), space: O(1)
    public static ListNode reverseList1(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
    }
}


/*
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。


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
*/


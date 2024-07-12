package year2021.month7.jz22;

public class GetKthFromEnd {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k1 = 2;
        int k2 = 5;
        int k3 = 1;
        System.out.println(getKthFromEnd(head1, k1));
        System.out.println(getKthFromEnd(head1, k2));
        System.out.println(getKthFromEnd(head1, k3));
    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
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
* 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

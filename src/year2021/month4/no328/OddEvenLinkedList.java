package year2021.month4.no328;

public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5,
                new ListNode(6, new ListNode(4, new ListNode(7)))))));
        disp(oddEvenList1(head1));
        disp(oddEvenList1(head2));
    }

    private static ListNode oddEvenList1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode oddRear = head;
        ListNode evenRear = evenHead;
        while (evenRear != null && evenRear.next != null) {
            oddRear.next = evenRear.next;
            oddRear = oddRear.next;
            evenRear.next = oddRear.next;
            evenRear = evenRear.next;
        }
        oddRear.next = evenHead;
        return head;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddRear = head;
        ListNode evenHead = head.next;
        ListNode evenRear = head.next;
        ListNode p = evenRear.next;
        oddRear.next = null;
        evenRear.next = null;
        int count = 1;
        while (p != null) {
            if ((count & 1) == 0) {
                evenRear.next = p;
                evenRear = evenRear.next;
            } else {
                oddRear.next = p;
                oddRear = oddRear.next;
            }
            count++;
            p = p.next;
        }
        evenRear.next = null;
        oddRear.next = evenHead;
        return head;
    }

    private static void disp(ListNode head) {
        if (head == null) {
            System.out.println("EMPTY");
        }
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
* 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:

输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
示例 2:

输入: 2->1->3->5->6->4->7->NULL
输出: 2->3->6->7->1->5->4->NULL
说明:

应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/odd-even-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

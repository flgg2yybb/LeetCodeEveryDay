package year2021.month5.no206;

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1, new ListNode(2));
        System.out.println(reverseList(head1));
        System.out.println(reverseList(head2));
    }

    /*
    * 递归过程：
    * level1:  1 -> 2 -> 3 -> 4 -> 5
    *          ↑
    * level2:  1 -> 2 -> 3 -> 4 -> 5
    *               ↑
    * level3:  1 -> 2 -> 3 -> 4 -> 5
    *                    ↑
    * level4:  1 -> 2 -> 3 -> 4 -> 5
    *                         ↑
    * level5:  1 -> 2 -> 3 -> 4 -> 5
    *                              ↑
    * level4:  1 -> 2 -> 3 -> 4 <- 5
    *                         ↑
    * level3:  1 -> 2 -> 3 <- 4 <- 5
    *                    ↑
    * level5:  1 -> 2 <- 3 <- 4 <- 5
    *               ↑
    * level5:  1 <- 2 <- 3 <- 4 <- 5
    *          ↑
    * */
    private static ListNode reverseList(ListNode head) {
        // recursion, time is O(n), sapce is O(n)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    public static ListNode reverseList1(ListNode head) {
        // iteration, time is O(n), space is O(1)
        ListNode newHead = null;
        while (head != null) {
            ListNode p = head;
            head = head.next;
            p.next = newHead;
            newHead = p;
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
* 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 

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
链接：https://leetcode-cn.com/problems/reverse-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

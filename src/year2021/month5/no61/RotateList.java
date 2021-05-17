package year2021.month5.no61;

public class RotateList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head3 = new ListNode(0, new ListNode(1, new ListNode(2)));
        ListNode head4 = new ListNode(0, new ListNode(1, new ListNode(2)));
        System.out.println(rotateRight(head1, 2));
        System.out.println(rotateRight(head2, 5));
        System.out.println(rotateRight(head3, 4));
        System.out.println(rotateRight(head4, 9));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        ListNode right = head;
        int step = 0;
        while (step < k && right.next != null) {
            right = right.next;
            step++;
        }
        if (right.next == null) {
            int len = step + 1;
            int kMod = k % len;
            if (kMod == 0) {
                return head;
            }
            if (kMod != k) {
                return rotateRight(head, kMod);
            }
        }
        ListNode left = head;
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        ListNode newHead = left.next;
        left.next = null;
        right.next = head;
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
* 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

 

示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
示例 2：


输入：head = [0,1,2], k = 4
输出：[2,0,1]
 

提示：

链表中节点的数目在范围 [0, 500] 内
-100 <= Node.val <= 100
0 <= k <= 2 * 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

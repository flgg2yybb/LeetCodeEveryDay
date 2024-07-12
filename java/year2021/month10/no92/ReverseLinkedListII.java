package year2021.month10.no92;

public class ReverseLinkedListII {
    private static ListNode successor = null;

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int left1 = 2, right1 = 4;
        ListNode head2 = new ListNode(1);
        int left2 = 1, right2 = 1;
        System.out.println(reverseBetween2(head1, left1, right1));
        System.out.println(reverseBetween2(head2, left2, right2));
    }

    private static ListNode reverseBetween2(ListNode head, int left, int right) {
        // clean code
        ListNode root = new ListNode(-1, head);
        ListNode pre = root;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            // 头插法
            ListNode next = cur.next;
            cur.next = cur.next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return root.next;
    }

    private static ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode root = new ListNode(-1, head);
        ListNode pre = root;
        while (left > 1) {
            pre = pre.next;
            left--;
            right--;
        }
        pre.next = iterationReverseN(pre.next, right - left + 1);
        return root.next;
    }

    private static ListNode iterationReverseN(ListNode head, int n) {
        if (head == null || n == 1) {
            return head;
        }
        ListNode newHead = null;
        ListNode p = head;
        ListNode next = head.next;
        while (n > 0) {
            next = p.next;
            p.next = newHead;
            newHead = p;
            p = next;
            n--;
        }
        head.next = next;
        return newHead;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        successor = null;
        return recursionReverse(head, left, right);
    }

    private static ListNode recursionReverse(ListNode head, int left, int right) {
        if (left == 1) {
            return recursionReverseN(head, right - left + 1);
        }
        head.next = recursionReverse(head.next, left - 1, right - 1);
        return head;
    }

    public static ListNode recursionReverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = recursionReverseN(head.next, n - 1);
        head.next.next = head;
        // 对于每个节点都会指向相同的后继，但只要他不是最后一个节点，
        // 就会在下一个节点执行 head.next.next = head; 时覆盖掉
        head.next = successor;
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
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 

示例 1：


输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
示例 2：

输入：head = [5], left = 1, right = 1
输出：[5]
 

提示：

链表中节点数目为 n
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n
 

进阶： 你可以使用一趟扫描完成反转吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

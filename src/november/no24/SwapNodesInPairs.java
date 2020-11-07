package november.no24;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode head = new ListNode(0);
//        ListNode result = solution1(head);
        ListNode result = solution2(head);
        disp(result);
    }

    private static ListNode solution2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode root = head.next;
        ListNode tail = null;
        while (head != null && head.next != null) {
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            if (tail != null) {
                tail.next = temp;
            }
            tail = head;
            head = head.next;
        }
        return root;
    }

    private static ListNode solution1(ListNode head) {
        if (head != null && head.next != null) {
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            head = temp;
            head.next.next = solution1(head.next.next);
        }
        return head;
    }

    private static void disp(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/*
* 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

示例 1：


输入：head = [1,2,3,4]
输出：[2,1,4,3]
示例 2：

输入：head = []
输出：[]
示例 3：

输入：head = [1]
输出：[1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
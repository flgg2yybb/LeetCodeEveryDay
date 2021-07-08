package year2021.month7.jz6;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ReversePrint {

    public static void main(String[] args) {
        ListNode root1 = null;
        ListNode root2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(Arrays.toString(reversePrint2(root1)));
        System.out.println(Arrays.toString(reversePrint2(root2)));
    }

    private static int[] reversePrint2(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode p = head;
        while (p != null) {
            stack.offerFirst(p.val);
            p = p.next;
        }
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] reversePrint1(ListNode root) {
        ListNode p = reverseLinkedList(root);
        List<Integer> list = new LinkedList<>();
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static ListNode reverseLinkedList(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        ListNode next = reverseLinkedList(root.next);
        root.next.next = root;
        root.next = null;
        return next;
    }

    private static ListNode reverseLinkedList1(ListNode root) {
        ListNode newHead = null;
        while (root != null) {
            ListNode next = root.next;
            root.next = newHead;
            newHead = root;
            root = next;
        }
        return newHead;
    }

    public static int[] reversePrint(ListNode head) {
        List<Integer> list = new LinkedList<>();
        ListNode p = head;
        while (p != null) {
            list.add(0, p.val);
            p = p.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
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
}

/*
* 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

示例 1：

输入：head = [1,3,2]
输出：[2,3,1]
 

限制：

0 <= 链表长度 <= 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
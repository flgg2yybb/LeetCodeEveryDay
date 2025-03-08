package year2025.month3.no2;

public class TwoNumberAddition {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode listNode2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        System.out.println(addTwoNumbers(listNode1, listNode2));
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(0);
        System.out.println(addTwoNumbers(listNode3, listNode4));
        ListNode listNode5 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode listNode6 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        System.out.println(addTwoNumbers(listNode5, listNode6));
    }

    // clean code, times is O(m+n), space is O(m+n)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int value1 = l1 != null ? l1.val : 0;
            int value2 = l2 != null ? l2.val : 0;
            int newValue = value1 + value2 + carry;
            carry = newValue / 10;
            newValue %= 10;
            cur.next = new ListNode(newValue);
            cur = cur.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }
        return head.next;
    }

    // mess code, times is O(m+n), space is O(m+n)
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + carry;
            carry = value / 10;
            value %= 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int value = l1.val + carry;
            carry = value / 10;
            value %= 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int value = l2.val + carry;
            carry = value / 10;
            value %= 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            l2 = l2.next;
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return head.next;
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
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
    }
}

/*
* 给你两个 非空 的链表，表示两个非负的整数。
它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例 1：
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.

示例 2：
输入：l1 = [0], l2 = [0]
输出：[0]

示例 3：
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]

提示：
每个链表中的节点数在范围 [1, 100] 内
0 <= Node.val <= 9
题目数据保证列表表示的数字不含前导零
* */

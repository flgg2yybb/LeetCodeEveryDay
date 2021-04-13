package year2021.month4.no2;

public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l12 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode l21 = new ListNode(0);
        ListNode l22 = new ListNode(0);
        ListNode l31 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9,
                new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode l32 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode l41 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode l42 = new ListNode(0);
        ListNode l51 = new ListNode(5, new ListNode(9, new ListNode(9)));
        ListNode l52 = new ListNode(5);
        disp(addTwoNumbers1(l11, l12));
        disp(addTwoNumbers1(l21, l22));
        disp(addTwoNumbers1(l31, l32));
        disp(addTwoNumbers1(l41, l42));
        disp(addTwoNumbers1(l51, l52));
    }

    private static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        return dfs(l1, l2, 0);
    }

    private static ListNode dfs(ListNode l1, ListNode l2, int plus) {
        if (l1 == null && l2 == null && plus == 0) {
            return null;
        }
        int value = 0;
        if (l1 != null) {
            value += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            value += l2.val;
            l2 = l2.next;
        }
        value += plus;
        plus = 0;
        if (value >= 10) {
            value -= 10;
            plus = 1;
        }
        ListNode node = new ListNode(value);
        node.next = dfs(l1, l2, plus);
        return node;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int nextPlus = 0;
        while (l1 != null || l2 != null || nextPlus != 0) {
            int value = 0;
            if (l1 != null) {
                value += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value += l2.val;
                l2 = l2.next;
            }
            value += nextPlus;
            nextPlus = 0;
            if (value >= 10) {
                value -= 10;
                nextPlus = 1;
            }
            cur.next = new ListNode(value);
            cur = cur.next;
        }
        return head.next;
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
* 给你两个   非空 的链表，表示两个非负的整数。它们每位数字都是按照   逆序   的方式存储的，并且每个节点只能存储   一位   数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0   开头。

   

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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

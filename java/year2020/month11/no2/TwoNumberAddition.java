package year2020.month11.no2;

public class TwoNumberAddition {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(5))));
        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode curr = result;
        int addNum = 0;
        while (l1 != null || l2 != null || addNum != 0) {
            int value1 = l1 != null ? l1.val : 0;
            int value2 = l2 != null ? l2.val : 0;
            curr.val = (value1 + value2 + addNum) % 10;
            addNum = (value1 + value2 + addNum) / 10;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
            if (l1 != null || l2 != null || addNum != 0) {
                curr.next = new ListNode();
                curr = curr.next;
            }
        }
        return result;
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
* 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */


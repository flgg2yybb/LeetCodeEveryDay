package year2021.month1.no21;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode node11 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode node12 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode node21 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode node22 = null;
        ListNode node31 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode node32 = new ListNode(4, new ListNode(6, new ListNode(8)));
        ListNode node41 = null;
        ListNode node42 = null;
        disp(mergeTwoLists1(node11, node12));
        disp(mergeTwoLists1(node21, node22));
        disp(mergeTwoLists1(node31, node32));
        disp(mergeTwoLists1(node41, node42));
    }

    private static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                pre = pre.next;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = pre.next;
                l2 = l2.next;
            }
        }
        pre.next = l1 == null ? l2 : l1;
        return head.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                node.next = new ListNode(node1.val);
                node = node.next;
                node1 = node1.next;
            } else {
                node.next = new ListNode(node2.val);
                node = node.next;
                node2 = node2.next;
            }
        }
        while (node1 != null) {
            node.next = new ListNode(node1.val);
            node = node.next;
            node1 = node1.next;
        }
        while (node2 != null) {
            node.next = new ListNode(node2.val);
            node = node.next;
            node2 = node2.next;
        }
        return head.next;
    }

    private static void disp(ListNode listNode) {
        if (listNode == null) {
            System.out.println("EMPTY");
            return;
        }
        for (ListNode p = listNode; p != null; p = p.next) {
            System.out.print(p.val);
            if (p.next != null) {
                System.out.print(" -> ");
            }
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
*将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month3.no21;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l11 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l12 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l21 = null;
        ListNode l22 = null;
        ListNode l31 = null;
        ListNode l32 = new ListNode(0);
        ListNode l41 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l42 = new ListNode(5, new ListNode(7, new ListNode(8)));
        disp(mergeTwoLists(l11, l12));
        disp(mergeTwoLists(l21, l22));
        disp(mergeTwoLists(l31, l32));
        disp(mergeTwoLists(l41, l42));
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode res;
        if (l1.val <= l2.val) {
            res = l1;
            res.next = mergeTwoLists(l1.next, l2);
        } else {
            res = l2;
            res.next = mergeTwoLists(l1, l2.next);
        }
        return res;
    }

    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode root = new ListNode(-1);
        ListNode rear = root;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                rear.next = l1;
                l1 = l1.next;
                rear = rear.next;
            } else {
                rear.next = l2;
                l2 = l2.next;
                rear = rear.next;
            }
        }
        rear.next = l1 == null ? l2 : l1;
        return root.next;
    }

    private static void disp(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val + "->");
        }
        System.out.println("NULL");
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
* 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]
示例 2：

输入：l1 = [], l2 = []
输出：[]
示例 3：

输入：l1 = [], l2 = [0]
输出：[0]

提示：

两个链表的节点数目范围是 [0, 50]
-100 <= Node.val <= 100
l1 和 l2 均按 非递减顺序 排列

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

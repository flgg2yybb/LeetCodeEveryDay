package year2021.month7.jz25;

public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l12 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l21 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l22 = null;
        System.out.println(mergeTwoLists(l11, l12));
        System.out.println(mergeTwoLists(l21, l22));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode root = new ListNode(-1);
        ListNode rear = root;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                rear.next = l1;
                l1 = l1.next;
            } else {
                rear.next = l2;
                l2 = l2.next;
            }
            rear = rear.next;
            rear.next = null;
        }
        if (l1 != null) {
            rear.next = l1;
        }
        if (l2 != null) {
            rear.next = l2;
        }
        return root.next;
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

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

/*
* 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
限制：

0 <= 链表长度 <= 1000

注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

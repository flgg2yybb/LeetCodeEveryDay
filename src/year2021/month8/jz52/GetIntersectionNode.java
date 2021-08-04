package year2021.month8.jz52;

public class GetIntersectionNode {

    public static void main(String[] args) {
        ListNode meet1 = new ListNode(8, new ListNode(4, new ListNode(5)));
        ListNode headA1 = new ListNode(4, new ListNode(1, meet1));
        ListNode headB1 = new ListNode(5, new ListNode(0, new ListNode(1, meet1)));
        ListNode meet2 = new ListNode(2, new ListNode(4));
        ListNode headA2 = new ListNode(0, new ListNode(9, new ListNode(1, meet2)));
        ListNode headB2 = new ListNode(3, meet2);
        ListNode headA3 = new ListNode(2, new ListNode(6, new ListNode(4)));
        ListNode headB3 = new ListNode(1, new ListNode(4));
        System.out.println(getIntersectionNode(headA1, headB1));
        System.out.println(getIntersectionNode(headA2, headB2));
        System.out.println(getIntersectionNode(headA3, headB3));
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;
        while (p != null && q != null) {
            p = p.next;
            q = q.next;
        }
        ListNode longer = p == null ? headB : headA;
        ListNode shorter = p == null ? headA : headB;
        ListNode diff = p == null ? q : p;
        while (diff != null) {
            longer = longer.next;
            diff = diff.next;
        }
        while (longer != null) {
            if (longer == shorter) {
                return longer;
            }
            longer = longer.next;
            shorter = shorter.next;
        }
        return null;
    }

}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}

/*
* 输入两个链表，找出它们的第一个公共节点。

如下面的两个链表：



在节点 c1 开始相交。

 

示例 1：



输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 

示例 2：



输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 

示例 3：



输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
 

注意：

如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

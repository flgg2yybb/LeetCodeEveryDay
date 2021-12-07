package year2021.month12.no86;

public class PartitionList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        int x1 = 3;
        ListNode head2 = new ListNode(2, new ListNode(1));
        int x2 = 2;
        System.out.println(partition(head1, x1));
        System.out.println(partition(head2, x2));
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode(-1);
        ListNode LargeDummy = new ListNode(-1);
        ListNode smallRear = smallDummy;
        ListNode largeRear = LargeDummy;
        while (head != null) {
            if (head.val < x) {
                smallRear.next = head;
                smallRear = smallRear.next;
            } else {
                largeRear.next = head;
                largeRear = largeRear.next;
            }
            head = head.next;
        }
        largeRear.next = null;
        smallRear.next = LargeDummy.next;
        return smallDummy.next;
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
* 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

你应当 保留 两个分区中每个节点的初始相对位置。

 

示例 1：


输入：head = [1,4,3,2,5,2], x = 3
输出：[1,2,2,4,3,5]
示例 2：

输入：head = [2,1], x = 2
输出：[1,2]
 

提示：

链表中节点的数目在范围 [0, 200] 内
-100 <= Node.val <= 100
-200 <= x <= 200

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

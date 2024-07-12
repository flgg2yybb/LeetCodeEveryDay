package year2021.month2.no148;

public class SortList {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode head2 = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
        ListNode head3 = new ListNode(9, new ListNode(7, new ListNode(5, new ListNode(3, new ListNode(1,
                new ListNode(0, new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8))))))))));
        ListNode head4 = null;
        disp(sortList(head1));
        disp(sortList(head2));
        disp(sortList(head3));
        disp(sortList(head4));
    }

    private static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middleNode = findMiddleNode(head);
        ListNode rightHead = middleNode.next;
        middleNode.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        return mergeTwoSortList(left, right);
    }

    private static ListNode findMiddleNode(ListNode head) {
//        用快满指针找到中间结点，慢指针一次走一步，快指针一次走两步
//        链表数量为奇数时，慢指针为中间结点，数量为偶数时，慢指针为中间结点的前驱节点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    private static ListNode mergeTwoSortList(ListNode left, ListNode right) {
//        合并两个有序链表
        ListNode root = new ListNode(-1);
        ListNode rear = root;
        while (left != null && right != null) {
            if (left.val < right.val) {
                rear.next = left;
                left = left.next;
            } else {
                rear.next = right;
                right = right.next;
            }
            rear = rear.next;
            rear.next = null;
        }
        rear.next = left == null ? right : left;
        return root.next;
    }

    public static ListNode sortList1(ListNode head) {
//        直接插入排序, time is O(n^2), space is O(1), node为当前准备插入的节点
        ListNode root = new ListNode(-1, head);
        ListNode cur = root;
        while (cur.next != null) {
            ListNode prev = root;
            while (prev.next != cur.next && prev.next.val <= cur.next.val) {
                prev = prev.next;
            }
            if (prev.next == cur.next) {
                cur = cur.next;
                continue;
            }
            // prev.next为插入点
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            temp.next = prev.next;
            prev.next = temp;
        }
        return root.next;
    }

    private static void disp(ListNode list) {
        if (list == null) {
            System.out.println("EMPTY");
            return;
        }
        ListNode node = list;
        while (node.next != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println(node.val);
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
* 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 

示例 1：


输入：head = [4,2,1,3]
输出：[1,2,3,4]
示例 2：


输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
示例 3：

输入：head = []
输出：[]
 

提示：

链表中节点的数目在范围 [0, 5 * 10^4] 内
-10^5 <= Node.val <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

package twenty.november.no25;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8,
                        new ListNode(9)))))))));
        int k = 3;
//        ListNode result = solution1(head, k);
        ListNode result = solution2(head, k);
        disp(result);
    }

    private static ListNode solution2(ListNode head, int k) {
//        新建一个头节点指向链表头部
        ListNode root = new ListNode(0, head);
//        pre表示上一段链表的尾节点，用来连接反转后的子链表的头部
        ListNode pre = root;
        while (head != null) {
            ListNode tail = head;
//            找到当前有k个元素的子链表，分别用head和tail指向头尾
            for (int i = 1; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return root.next;
                }
            }
//            next为下一个子链表的表头
            ListNode next = tail.next;
//            反转当前子链表，并返回反转后的头、尾节点
            ListNode[] listNodes = reverseLinkedList(head, tail);
            head = listNodes[0];
            tail = listNodes[1];
            tail.next = next;
            pre.next = head;
            pre = tail;
            head = next;
        }
        return root.next;
    }

    private static ListNode[] reverseLinkedList(ListNode head, ListNode tail) {
        ListNode end = tail.next;
        ListNode start = head;
        ListNode nextStart;
        while (end != tail) {
            nextStart = start.next;
            start.next = end;
            end = start;
            start = nextStart;
        }
        return new ListNode[]{tail, head};
    }

    private static ListNode solution1(ListNode head, int k) {
        ListNode root = head;
        ListNode next = head;
        ListNode pre = null;
        while (next != null) {
            int count = 0;
            ListNode start = next;
            ListNode end = next;
//            找到当前需要处理的包含k个元素的组，组头与组尾分别用start和end指向
            while (next != null) {
                count++;
                end = next;
                next = next.next;
                if (count == k) {
                    break;
                }
            }
//            如果当前组包含k个元素，则进行交换
            if (count == k) {
                if (pre != null) {
//                将上个组的组尾指向当前组的end（end在交换后会变成头），后将pre指向当前start（start在交换后会变成组尾）
                    pre.next = end;
                } else {
//                第一次交换，需将头结点root指向交换后的链表头节点
                    root = end;
                }
                pre = start;
//                next为下一个k个元素的组的start
                ListNode nextStart;
                ListNode lastEnd = next;
                while (lastEnd != end) {
                    nextStart = start.next;
                    start.next = lastEnd;
                    lastEnd = start;
                    start = nextStart;
                }
            }
        }
        return root;
    }

    private static void disp(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
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
* 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

 

示例：

给你这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

 

说明：

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
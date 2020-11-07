package november.no24;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode head = new ListNode(0);
//        ListNode result = solution1(head);
        ListNode result = solution2(head);
        disp(result);
    }

    private static ListNode solution2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode root = head.next;
        ListNode tail = null;
        while (head != null && head.next != null) {
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            if (tail != null) {
                tail.next = temp;
            }
            tail = head;
            head = head.next;
        }
        return root;
    }

    private static ListNode solution1(ListNode head) {
        if (head != null && head.next != null) {
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            head = temp;
            head.next.next = solution1(head.next.next);
        }
        return head;
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
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
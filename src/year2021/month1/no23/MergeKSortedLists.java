package year2021.month1.no23;

import java.util.Arrays;
import java.util.Objects;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode list11 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list12 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list13 = new ListNode(2, new ListNode(6));
        ListNode[] lists1 = new ListNode[]{list11, list12, list13};
        ListNode[] lists2 = null;
        ListNode[] lists3 = new ListNode[0];
        ListNode list41 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list42 = null;
        ListNode list43 = new ListNode(2, new ListNode(6));
        ListNode[] lists4 = new ListNode[]{list41, list42, list43};
        disp(mergeKLists(lists1));
        disp(mergeKLists(lists2));
        disp(mergeKLists(lists3));
        disp(mergeKLists(lists4));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        ListNode head = new ListNode(-1);
        ListNode p = head;
        long total = Arrays.stream(lists).filter(Objects::nonNull).count();
        int count = 0;
        while (count < total) {
            int min = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (min == -1 || lists[i].val < lists[min].val) {
                    min = i;
                }
            }
            if (lists[min].next == null) {
                count++;
            }
            p.next = lists[min];
            p = p.next;
            lists[min] = lists[min].next;
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
* 给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

 

示例 1：

输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：

输入：lists = []
输出：[]
示例 3：

输入：lists = [[]]
输出：[]
 

提示：

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
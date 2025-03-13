package year2025.month3.no23;

import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode[] lists1 = new ListNode[]{new ListNode(1, new ListNode(4, new ListNode(5, null))), new ListNode(1, new ListNode(3, new ListNode(4, null))), new ListNode(2, new ListNode(6, null))};
        ListNode[] lists2 = new ListNode[]{};
        ListNode[] lists3 = new ListNode[]{null, null, null};
        System.out.println(mergeKLists(lists1));
        System.out.println(mergeKLists(lists2));
        System.out.println(mergeKLists(lists3));
    }

    // Min Heap, times: O(nklogk), space: O(k)
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        ListNode root = new ListNode(-1);
        ListNode cur = root;
        while (!minHeap.isEmpty()) {            // O(nk)
            ListNode poll = minHeap.poll();     // O(logk)
            cur.next = poll;
            cur = cur.next;
            if (poll.next != null) {
                minHeap.offer(poll.next);
            }
        }
        return root.next;
    }

    // Divide and Conquer - Merge Sort,, times: O(logk * kn), space: O(1)
    // k 个链表，每个链表平均长度为 n
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int k = lists.length;
        while (k > 1) {  // 循环 logk 次
            int newIdx = 0;
            for (int i = 0; i < k; ) {   // 循环 k/2 次
                ListNode l1 = lists[i++];
                ListNode l2 = i < k ? lists[i++] : null;
                // 原地替换，不需要额外的空间
                lists[newIdx++] = mergeLinkedList(l1, l2);   // 循环 2n 次
            }
            k = newIdx;
        }
        return lists[0];
    }

    public static ListNode mergeLinkedList(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(-1);
        ListNode cur = root;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return root.next;
    }

    // Merge Sort LinkedList, times: O(n*k^2), space: O(1)
    // k 个链表，每个链表平均长度为 n
    public static ListNode mergeKLists1(ListNode[] lists) {
        ListNode root = new ListNode(-1);
        ListNode cur = root;
        while (true) {  // 循环 kn 次
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {    // 循环 k 次
                if (lists[i] == null) {
                    continue;
                }
                if (minIndex == -1 || lists[i].val < lists[minIndex].val) {
                    minIndex = i;
                }
            }
            if (minIndex == -1) {
                break;
            }
            cur.next = lists[minIndex];
            cur = cur.next;
            lists[minIndex] = lists[minIndex].next;
        }
        return root.next;
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
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
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
* */

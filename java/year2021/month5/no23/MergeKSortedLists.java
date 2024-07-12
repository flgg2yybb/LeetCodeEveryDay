package year2021.month5.no23;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import year2021.month1.no21.MergeTwoSortedLists;

public class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));
        ListNode[] lists = {list1, list2, list3, null};
        System.out.println(mergeKLists2(lists));
    }

    private static ListNode mergeKLists2(ListNode[] lists) {
        // 使用优先队列优化，每次从 K 个链表中取出头结点值最小的链表
        // 将其头结点尾插入结果链表中，若还有剩余节点则入队
        // time is O(knlogk), space is O(k)
        // 优先队列最大容量为 k ，平均插入节点的时间复杂度为 logk
        // 总共有 kn 个节点需要插入，故总时间复杂度为 knlogk
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        ListNode head = new ListNode(-1);
        ListNode rear = head;
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            rear.next = poll;
            rear = rear.next;
            if (poll.next != null) {
                queue.offer(poll.next);
            }
        }
        return head.next;
    }

    private static ListNode mergeKLists1(ListNode[] lists) {
        // 构造链表合并队列，每次在队列中取出两个链表进行合并
        // 再讲合并之后的链表入队，只到队列剩下一个链表
        Queue<ListNode> queue = new LinkedList<>();
        for (ListNode listNode : lists) {
            queue.offer(listNode);
        }
        while (queue.size() > 1) {
            ListNode list1 = queue.poll();
            ListNode list2 = queue.poll();
            ListNode mergedList = mergeTwoLists(list1, list2);
            queue.offer(mergedList);
        }
        return queue.poll();
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

    public static ListNode mergeKLists(ListNode[] lists) {
        // 类似于合并两个有序链表，每次取 k 个链表中最小的头结点最为尾插节点
        // time is O(ks), space is O(k), k 为链表个数，s 为 k 个链表的总节点数
        ListNode head = new ListNode(0);
        ListNode rear = head;
        Set<Integer> emptyIndexes = new HashSet<>();
        while (emptyIndexes.size() < lists.length) {
            int mid = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    emptyIndexes.add(i);
                    continue;
                }
                if (mid == -1 || lists[i].val < lists[mid].val) {
                    mid = i;
                }
            }
            if (mid != -1) {
                rear.next = lists[mid];
                rear = rear.next;
                lists[mid] = lists[mid].next;
            }
        }
        return head.next;
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

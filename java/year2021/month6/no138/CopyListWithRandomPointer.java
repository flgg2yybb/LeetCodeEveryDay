package year2021.month6.no138;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyListWithRandomPointer {

    public static void main(String[] args) {
        Node node15 = new Node(1);
        Node node14 = new Node(10, node15);
        Node node13 = new Node(11, node14);
        Node node12 = new Node(13, node13);
        Node node11 = new Node(7, node12);
        node12.random = node11;
        node13.random = node15;
        node14.random = node13;
        node15.random = node11;
        Node head1 = node11;
        System.out.println(head1);
        System.out.println(copyRandomList2(head1));

        Node node22 = new Node(2);
        Node node21 = new Node(1, node22, node22);
        node22.random = node22;
        Node head2 = node21;
        System.out.println(head2);
        System.out.println(copyRandomList2(head2));

        Node node33 = new Node(3);
        Node node32 = new Node(3, node33);
        Node node31 = new Node(3, node32);
        node32.random = node31;
        Node head3 = node31;
        System.out.println(head3);
        System.out.println(copyRandomList2(head3));

        System.out.println("null");
        System.out.println(copyRandomList2(null));
    }

    private static Node copyRandomList2(Node head) {
        Map<Node, Node> nodeMap = new HashMap<>();
        Node cur = head;
        Node ans = new Node(-1);
        Node rear = ans;
        while (cur != null) {
            rear.next = copyNode(nodeMap, cur);
            rear = rear.next;
            rear.random = copyNode(nodeMap, cur.random);
            cur = cur.next;
        }
        return ans.next;
    }

    private static Node copyNode(Map<Node, Node> nodeMap, Node head) {
        if (head == null) {
            return null;
        }
        if (nodeMap.containsKey(head)) {
            return nodeMap.get(head);
        }
        Node node = new Node(head.val);
        nodeMap.put(head, node);
        return node;
    }

    private static Map<Node, Node> visitedMap = new HashMap<>();

    private static Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        if (visitedMap.containsKey(head)) {
            return visitedMap.get(head);
        }
        Node node = new Node(head.val);
        visitedMap.put(head, node);
        node.next = copyRandomList1(head.next);
        node.random = copyRandomList1(head.random);
        return node;
    }

    public static Node copyRandomList(Node head) {
        /*
        * HashMap, time is O(n), space is O(n)
        * 遍历原链表，建立每个节点以及对应位置的索引 oldNodeMap
        * 再遍历链表，建立每个节点的 索引 和其 random 节点索引的 mapping randomMap
        * 先拷贝原链表，并将新链表的每个节点存入数组中
        * 再根据原链表的 randomMap 依次更新新链表的 random 属性
        * */
        if (head == null) {
            return null;
        }
        Map<Node, Integer> oldNodeMap = new HashMap<>();
        List<Node> newNodeList = new ArrayList<>();
        Node p = head;
        Node root = new Node(-1);
        Node rear = root;
        while (p != null) {
            rear.next = new Node(p.val);
            rear = rear.next;
            oldNodeMap.put(p, oldNodeMap.size());
            newNodeList.add(rear);
            p = p.next;
        }
        Map<Integer, Integer> randomMap = new HashMap<>();
        p = head;
        while (p != null) {
            randomMap.put(randomMap.size(), oldNodeMap.getOrDefault(p.random, -1));
            p = p.next;
        }
        rear = root.next;
        int cur = 0;
        while (rear != null) {
            int randomIndex = randomMap.get(cur);
            if (randomIndex != -1) {
                rear.random = newNodeList.get(randomIndex);
            }
            rear = rear.next;
            cur++;
        }
        return root.next;
    }

}

class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", next=" + next +
                ", random=" + (random == null ? "null" : random.val) +
                '}';
    }
}

/*
* 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。

 

示例 1：



输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
示例 2：



输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
示例 3：



输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
示例 4：

输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
 

提示：

0 <= n <= 1000
-10000 <= Node.val <= 10000
Node.random 为空（null）或指向链表中的节点。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

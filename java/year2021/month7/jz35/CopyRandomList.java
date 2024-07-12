package year2021.month7.jz35;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

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
        System.out.println(copyRandomList(head1));

        Node node22 = new Node(2);
        Node node21 = new Node(1, node22, node22);
        node22.random = node22;
        Node head2 = node21;
        System.out.println(head2);
        System.out.println(copyRandomList(head2));

        Node node33 = new Node(3);
        Node node32 = new Node(3, node33);
        Node node31 = new Node(3, node32);
        node32.random = node31;
        Node head3 = node31;
        System.out.println(head3);
        System.out.println(copyRandomList(head3));

        System.out.println("null");
        System.out.println(copyRandomList(null));
    }

    private static Node copyRandomList(Node head) {
        /*
         * 使用哈希表存储<旧节点，新节点>的映射，先遍历原链表
         * 并将全部新节点存入哈希表中
         * 再从头遍历新旧头结点，根据旧节点的 next 和 random，
         * 从哈希表中获取新节点的 next 和 random 从而更改新节点
         * next 和 random 的引用
         * */
        if (head == null) {
            return head;
        }
        Map<Node, Node> nodeMap = new HashMap<>();
        Node p = head;
        while (p != null) {
            nodeMap.put(p, new Node(p.val));
            p = p.next;
        }
        Node newHead = nodeMap.get(head);
        Node newRear = newHead;
        p = head;
        while (p != null) {
            newRear.next = nodeMap.get(p.next);
            newRear.random = nodeMap.get(p.random);
            p = p.next;
            newRear = newRear.next;
        }
        return newHead;
    }

    public static Node copyRandomList1(Node head) {
        /*
         * 使用哈希表存储<旧节点，新节点>的映射，动态新增哈希表
         * 遍历链表，分别复制 next 和 random
         * */
        Map<Node, Node> nodeMap = new HashMap<>();
        return copy(head, nodeMap);
    }

    private static Node copy(Node head, Map<Node, Node> nodeMap) {
        if (head == null) {
            return head;
        }
        if (nodeMap.containsKey(head)) {
            return nodeMap.get(head);
        }
        Node node = new Node(head.val);
        nodeMap.put(head, node);
        node.next = copy(head.next, nodeMap);
        node.random = copy(head.random, nodeMap);
        return node;
    }

}

class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
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
* 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

 

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

-10000 <= Node.val <= 10000
Node.random 为空（null）或指向链表中的节点。
节点数目不超过 1000 。
 

注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

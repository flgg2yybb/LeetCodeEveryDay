package year2021.month7.jz36;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeToDoublyList {

    private static Node pre = null;
    private static Node head = null;

    public static void main(String[] args) {
        Node tree = new Node(4,
                new Node(2,
                        new Node(1),
                        new Node(3)),
                new Node(5));
        disp(treeToDoublyList(tree));
    }

    private static Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        pre = null;
        head = null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private static void dfs(Node cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        if (pre == null) {
            head = cur;
        } else {
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

    public static Node treeToDoublyList1(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> stack = new LinkedList<>();
        Node p = root;
        List<Node> queue = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.offerLast(p);
                p = p.left;
            }
            Node pop = stack.pollLast();
            queue.add(pop);
            p = pop.right;
        }
        for (int i = 0; i < queue.size(); i++) {
            Node cur = queue.get(i);
            cur.right = (i + 1 == queue.size()) ? queue.get(0) : queue.get(i + 1);
            cur.left = (i == 0) ? queue.get(queue.size() - 1) : queue.get(i - 1);
        }
        return queue.get(0);
    }

    private static void disp(Node head) {
        if (head == null) {
            System.out.println("NULL");
            return;
        }
        System.out.print(head.val + " ");
        for (Node p = head.right; p != null && p != head; p = p.right) {
            System.out.print(p.val + " ");
        }
        System.out.println();
    }

}

class Node {

    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

/*
* 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

为了让您更好地理解问题，以下面的二叉搜索树为例：

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

注意：此题对比原题有改动。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month4.no230;

import java.util.Deque;
import java.util.LinkedList;

public class KthSmallestElementInBST {

    private static int count;
    private static int ans;

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3,
                new TreeNode(1,
                        null,
                        new TreeNode(2)),
                new TreeNode(4));
        int k1 = 1;
        TreeNode root2 = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(1),
                                null),
                        new TreeNode(4)),
                new TreeNode(6));
        int k2 = 3;
        System.out.println(kthSmallest1(root1, k1));
        System.out.println(kthSmallest1(root2, k2));
    }

    public static int kthSmallest(TreeNode root, int k) {
        count = k;
        dfsInorder(root);
        return ans;
    }

    private static void dfsInorder(TreeNode root) {
        if (count <= 0) {
            return;
        }
        if (root != null) {
            dfsInorder(root.left);
            if (--count == 0) {
                ans = root.val;
            }
            dfsInorder(root.right);
        }
    }

    private static int kthSmallest1(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            TreeNode node = stack.pop();
            if (--k == 0) {
                return node.val;
            }
            p = node.right;
        }
        return -1;
    }

}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
* 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

 

示例 1：


输入：root = [3,1,4,null,2], k = 1
输出：1
示例 2：


输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3
 

 

提示：

树中的节点数为 n 。
1 <= k <= n <= 104
0 <= Node.val <= 104
 

进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month7.jz55b;

import java.util.HashMap;
import java.util.Map;

public class IsBalanced {

    private static final Map<TreeNode, Integer> cacheDepth = new HashMap<>();

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                new TreeNode(4)),
                        new TreeNode(3)),
                new TreeNode(2));
        System.out.println(isBalanced(root1));
        System.out.println(isBalanced(root2));
    }

    private static boolean isBalanced(TreeNode root) {
        return getHeight(root) >= 0;
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        int diff = Math.abs(left - right);
        if (left == -1 || right == -1 || diff > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
    }

    private static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (!cacheDepth.containsKey(root)) {
            int left = getDepth(root.left);
            int right = getDepth(root.right);
            int depth = Math.max(left, right) + 1;
            cacheDepth.put(root, depth);
        }
        return cacheDepth.get(root);
    }

}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
* 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

 

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。

 

限制：

0 <= 树的结点个数 <= 10000
注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

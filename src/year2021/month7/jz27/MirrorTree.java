package year2021.month7.jz27;

public class MirrorTree {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1),
                        new TreeNode(3)),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(9)));
        TreeNode root2 = new TreeNode(4,
                new TreeNode(2,
                        null,
                        new TreeNode(3)),
                new TreeNode(7,
                        new TreeNode(6),
                        null));
        TreeNode root3 = null;
        dfs(mirrorTree(root1));
        System.out.println();
        dfs(mirrorTree(root2));
        System.out.println();
        dfs(mirrorTree(root3));
    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = mirrorTree(right);
        root.right = mirrorTree(left);
        return root;
    }

    private static void dfs(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            dfs(root.left);
            dfs(root.right);
        }
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
* 请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
镜像输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 

示例 1：

输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
 

限制：

0 <= 节点个数 <= 1000

注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month2.no437;

public class PathSumIII {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3, new TreeNode(3), new TreeNode(-2)),
                        new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3, null, new TreeNode(11)));
        int sum1 = 8;
        System.out.println(pathSum(root1, sum1));
    }

    public static int pathSum(TreeNode root, int sum) {
        return dfs(root, sum, false);
    }

    private static int dfs(TreeNode root, int sum, boolean inPath) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        if (!inPath) {
            count += dfs(root.left, sum, false);
            count += dfs(root.right, sum, false);
        }
        count += dfs(root.left, sum - root.val, true);
        count += dfs(root.right, sum - root.val, true);
        return count;
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
* 给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-sum-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

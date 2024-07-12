package year2021.month8.jz54;

public class KthLargest {

    private static Integer ans = null;
    private static int kth;

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
        System.out.println(kthLargest(root1, k1));
        System.out.println(kthLargest(root2, k2));
    }

    public static int kthLargest(TreeNode root, int k) {
        ans = null;
        kth = k;
        dfs(root);
        return ans;
    }

    private static void dfs(TreeNode root) {
        if (kth > 0 && root != null) {
            dfs(root.right);
            kth--;
            if (kth == 0) {
                ans = root.val;
            }
            dfs(root.left);
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
* 给定一棵二叉搜索树，请找出其中第k大的节点。

 

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4
 

限制：

1 ≤ k ≤ 二叉搜索树元素个数

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

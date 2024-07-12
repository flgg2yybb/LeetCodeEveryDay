package year2021.month7.jz68a;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6,
                new TreeNode(2,
                        new TreeNode(0),
                        new TreeNode(4,
                                new TreeNode(3),
                                new TreeNode(5))),
                new TreeNode(8,
                        new TreeNode(7),
                        new TreeNode(9)));
        TreeNode p1 = new TreeNode(2);
        TreeNode q1 = new TreeNode(8);
        TreeNode p2 = new TreeNode(2);
        TreeNode q2 = new TreeNode(4);
        System.out.println(lowestCommonAncestor1(root, p1, q1));
        System.out.println(lowestCommonAncestor1(root, p2, q2));
    }

    private static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode max = p.val > q.val ? p : q;
        TreeNode min = p.val <= q.val ? p : q;
        return dfs(root, max, min);
    }

    private static TreeNode dfs(TreeNode root, TreeNode max, TreeNode min) {
        if (max.val < root.val) {
            return dfs(root.left, max, min);
        }
        if (min.val > root.val) {
            return dfs(root.right, max, min);
        }
        // max.val >= root.val && min.val <= root.val and max.val != min.val
        return root;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        if (min > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (max < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
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

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}

/*
* 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]



 

示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
示例 2:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 

说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。
注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

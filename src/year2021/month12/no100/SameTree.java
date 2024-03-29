package year2021.month12.no100;

public class SameTree {
    public static void main(String[] args) {
        TreeNode p1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        TreeNode q1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        TreeNode q2 = new TreeNode(1,
                null
                , new TreeNode(2));
        TreeNode p2 = new TreeNode(1,
                new TreeNode(2),
                null);
        TreeNode p3 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(1));
        TreeNode q3 = new TreeNode(1,
                new TreeNode(1),
                new TreeNode(2));
        System.out.println(isSameTree(p1, q1));
        System.out.println(isSameTree(p2, q2));
        System.out.println(isSameTree(p3, q3));
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == null && q == null;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

 

示例 1：


输入：p = [1,2,3], q = [1,2,3]
输出：true
示例 2：


输入：p = [1,2], q = [1,null,2]
输出：false
示例 3：


输入：p = [1,2,1], q = [1,1,2]
输出：false
 

提示：

两棵树上的节点数目都在范围 [0, 100] 内
-104 <= Node.val <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/same-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

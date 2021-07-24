package year2021.month7.jz26;

import java.util.Deque;
import java.util.LinkedList;

public class IsSubStructure {

    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(3,
                new TreeNode(4,
                        new TreeNode(1),
                        new TreeNode(2)),
                new TreeNode(5));
        TreeNode b1 = new TreeNode(4,
                new TreeNode(1),
                null);
        TreeNode b2 = new TreeNode(4,
                null,
                new TreeNode(1));
        TreeNode b3 = new TreeNode(4,
                new TreeNode(1),
                new TreeNode(3));
        System.out.println(isSubStructure(a1, b1));
        System.out.println(isSubStructure(a1, b2));
        System.out.println(isSubStructure(a1, b3));
    }

    private static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (isSameStructure(A, B)) {
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public static boolean isSubStructure1(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = A;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                if (p.val == B.val && isSameStructure(p, B)) {
                    return true;
                }
                stack.offerLast(p.right);
                p = p.left;
            }
            p = stack.pollLast();
        }
        return false;
    }

    private static boolean isSameStructure(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSameStructure(a.left, b.left) && isSameStructure(a.right, b.right);
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
* 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

     3
    / \
   4   5
  / \
 1   2
给定的树 B：

   4 
  /
 1
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

示例 1：

输入：A = [1,2,3], B = [3,1]
输出：false
示例 2：

输入：A = [3,4,5,1,2], B = [4,1]
输出：true
限制：

0 <= 节点个数 <= 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month1.no101;

import java.util.ArrayList;
import java.util.List;

public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3)));
        TreeNode root3 = new TreeNode(1,
                new TreeNode(2, new TreeNode(2), null),
                new TreeNode(2, new TreeNode(2), null));

        System.out.println(isSymmetric(root1)); //true
        System.out.println(isSymmetric(root2)); //false
        System.out.println(isSymmetric(root3)); //false
    }

    public static boolean isSymmetric(TreeNode root) {
//        前序遍历加中序遍历可确定一棵树，
//        只要判断前序遍历和反前序遍历以及中序遍历和反中序遍历的结果即可得知树是否是镜像的
//        但是测试用例中的节点的值不唯一，仅仅依靠值无法来判断节点
        if (root == null) {
            return true;
        }
        List<Integer> preOrderList = new ArrayList<>();
        List<Integer> reversePreOrderList = new ArrayList<>();
        List<Integer> inOrderList = new ArrayList<>();
        List<Integer> reverseInOrderList = new ArrayList<>();
        dfsPreOrder(root, preOrderList);
        dfsReversePreOrder(root, reversePreOrderList);
        dfsInOrder(root, inOrderList);
        dfsReverseInOrder(root, reverseInOrderList);
        for (int i = 0; i < preOrderList.size(); i++) {
            if (!preOrderList.get(i).equals(reversePreOrderList.get(i)) || !inOrderList.get(i).equals(reverseInOrderList.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static void dfsReverseInOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            dfsReverseInOrder(root.right, list);
            list.add(root.val);
            dfsReverseInOrder(root.left, list);
        }
    }

    private static void dfsInOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            dfsInOrder(root.left, list);
            list.add(root.val);
            dfsInOrder(root.right, list);
        }
    }

    private static void dfsReversePreOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            dfsReversePreOrder(root.right, list);
            dfsReversePreOrder(root.left, list);
        }
    }

    private static void dfsPreOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            dfsPreOrder(root.left, list);
            dfsPreOrder(root.right, list);
        }
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
* 给定一个二叉树，检查它是否是镜像对称的。

 

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3
 

进阶：

你可以运用递归和迭代两种方法解决这个问题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/symmetric-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

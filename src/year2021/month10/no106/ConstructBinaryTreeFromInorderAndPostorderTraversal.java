package year2021.month10.no106;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        inorderDisp(buildTree(inorder, postorder));
        System.out.println();
        postorderDisp(buildTree(inorder, postorder));
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = IntStream.range(0, inorder.length)
                .boxed()
                .collect(Collectors.toMap(index -> inorder[index], index -> index, (v1, v2) -> v2));
        return build(inorderMap, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private static TreeNode build(Map<Integer, Integer> inorderMap, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) {
            return null;
        }
        int rootValue = postorder[postEnd];
        int rootInorderIndex = inorderMap.get(rootValue);
        int leftSize = rootInorderIndex - inStart;
        TreeNode root = new TreeNode(rootValue);
        root.left = build(inorderMap, inStart, rootInorderIndex - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = build(inorderMap, rootInorderIndex + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
        return root;
    }

    private static void inorderDisp(TreeNode root) {
        if (root != null) {
            inorderDisp(root.left);
            System.out.print(root.val + " ");
            inorderDisp(root.right);
        }
    }

    private static void postorderDisp(TreeNode root) {
        if (root != null) {
            postorderDisp(root.left);
            postorderDisp(root.right);
            System.out.print(root.val + " ");
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
* 根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month4.no105;

import java.util.stream.IntStream;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        dfsPrint(buildTree(preorder, inorder));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfsBuildTree(preorder, 0, inorder, 0, preorder.length);
    }

    private static TreeNode dfsBuildTree(int[] preorder, int preStart, int[] inorder, int inStart, int len) {
        // time is O(n^2), space is O(1), 当树为链表，只有左子树时，即 preorder 与 inorder 顺序相反是时间复杂度最高
        // 每个节点遍历一次 O(n)，并且对每个节点遍历时查找当前根节点在 inorder 索引上需要花费 O(n)
        if (len == 0) {
            return null;
        }
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);
        int rootIndexAtInorder = IntStream.range(inStart, inStart + len)
                .filter(index -> inorder[index] == rootValue)
                .findAny().orElse(-1);
        int leftSize = rootIndexAtInorder - inStart;
        int rightSize = len - leftSize - 1;
        root.left = dfsBuildTree(preorder, preStart + 1, inorder, inStart, leftSize);
        root.right = dfsBuildTree(preorder, preStart + 1 + leftSize, inorder, rootIndexAtInorder + 1, rightSize);
        return root;
    }

    private static void dfsPrint(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            dfsPrint(root.left);
            dfsPrint(root.right);
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
* 根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

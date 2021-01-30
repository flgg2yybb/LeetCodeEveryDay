package year2021.month1.no105;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        dfsPreDisp(root);
        System.out.println();
        dfsInDisp(root);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> nodeIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            nodeIndexMap.put(inorder[i], i);
        }
        return buildTree(preorder, nodeIndexMap, 0, 0, preorder.length);
    }

    private static TreeNode buildTree(int[] preorder, Map<Integer, Integer> nodeIndexMap, int preStart, int inStart, int len) {
        if (len == 0) {
            return null;
        }
        int rootValue = preorder[preStart];
        int rootInOrderIndex = nodeIndexMap.get(rootValue);
        int leftSize = rootInOrderIndex - inStart;
        int rightSize = len - leftSize - 1;
        TreeNode root = new TreeNode(rootValue);
        root.left = buildTree(preorder, nodeIndexMap, preStart + 1, inStart, leftSize);
        root.right = buildTree(preorder, nodeIndexMap, preStart + 1 + leftSize, inStart + 1 + leftSize, rightSize);
        return root;
    }

    //    Use map to optimize
    private static int getLeftSize(int rootValue, int[] inorder, int inStart, int len) {
        int count = 0;
        for (int i = inStart; i < inStart + len; i++) {
            if (inorder[i] == rootValue) {
                break;
            }
            count++;
        }
        return count;
    }

    private static void dfsPreDisp(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            dfsPreDisp(root.left);
            dfsPreDisp(root.right);
        }
    }

    private static void dfsInDisp(TreeNode root) {
        if (root != null) {
            dfsInDisp(root.left);
            System.out.print(root.val + " ");
            dfsInDisp(root.right);
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

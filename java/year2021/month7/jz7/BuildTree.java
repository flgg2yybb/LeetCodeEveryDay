package year2021.month7.jz7;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuildTree {

    public static void main(String[] args) {
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        disp(buildTree(preorder1, inorder1));
        disp(buildTree(preorder2, inorder2));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        Map<Integer, Integer> inorderMap = IntStream.range(0, len)
                .boxed()
                .collect(Collectors.toMap(index -> inorder[index], index -> index));
        return buildTree(preorder, inorderMap, 0, 0, len);
    }

    private static TreeNode buildTree(int[] preorder, Map<Integer, Integer> inorderMap, int preStart, int inStart, int len) {
        if (len <= 0) {
            return null;
        }
        int curVal = preorder[preStart];
        TreeNode root = new TreeNode(curVal);
        Integer rootInorderIndex = inorderMap.get(curVal);
        int leftSize = rootInorderIndex - inStart;
        int rightSize = len - leftSize - 1;
        root.left = buildTree(preorder, inorderMap, preStart + 1, inStart, leftSize);
        root.right = buildTree(preorder, inorderMap, preStart + leftSize + 1, rootInorderIndex + 1, rightSize);
        return root;
    }

    private static void disp(TreeNode node) {
        dfsDisp(node);
        System.out.println();
    }

    private static void dfsDisp(TreeNode node) {
        if (node != null) {
            System.out.println(node.val + " ");
            dfsDisp(node.left);
            dfsDisp(node.right);
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
* 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

示例 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
示例 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
 

限制：

0 <= 节点个数 <= 5000

 

注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

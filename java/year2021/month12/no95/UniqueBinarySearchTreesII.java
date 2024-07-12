package year2021.month12.no95;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 1;
        System.out.println(generateTrees(n1));
        System.out.println(generateTrees(n2));
    }

    public static List<TreeNode> generateTrees(int n) {
        return dfsGen(1, n);
    }

    private static List<TreeNode> dfsGen(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = dfsGen(start, i - 1);
            List<TreeNode> rightTrees = dfsGen(i + 1, end);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);        // 需放进循环里 new 对象，否则会更改之前的 root 对象的左右子树
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                }
            }
        }
        return trees;
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
* 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。

 

示例 1：


输入：n = 3
输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
示例 2：

输入：n = 1
输出：[[1]]
 

提示：

1 <= n <= 8

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

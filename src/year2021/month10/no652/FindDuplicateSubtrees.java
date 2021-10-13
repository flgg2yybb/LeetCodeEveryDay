package year2021.month10.no652;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        null),
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(4),
                                null),
                        new TreeNode(4)));
        disp(findDuplicateSubtrees(root));
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> subTreeMap = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        find(root, subTreeMap, ans);
        return ans;
    }

    /*
     * Tips:
     * 带有空节点的【前序遍历】和【后序遍历】可以唯一确定一棵树
     * */
    private static String find(TreeNode root, Map<String, Integer> subTreeMap, List<TreeNode> ans) {
        if (root == null) {
            return "null";
        }
        String left = find(root.left, subTreeMap, ans);
        String right = find(root.right, subTreeMap, ans);
        // 序列化顺序必须为【前序遍历】或者【后序遍历】才可以 AC，因【中序遍历】无法确定一颗树的结构
        String serialization = root.val + "," + left + "," + right;
        subTreeMap.merge(serialization, 1, Integer::sum);
        if (subTreeMap.get(serialization) == 2) {
            ans.add(root);
        }
        return serialization;
    }

    private static void disp(List<TreeNode> roots) {
        roots.forEach((root) -> {
            print(root);
            System.out.println();
        });
    }

    private static void print(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            print(root.left);
            print(root.right);
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
* 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。

两棵树重复是指它们具有相同的结构以及相同的结点值。

示例 1：

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
下面是两个重复的子树：

      2
     /
    4
和

    4
因此，你需要以列表的形式返回上述重复子树的根结点。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-duplicate-subtrees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
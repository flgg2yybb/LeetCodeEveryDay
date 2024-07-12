package year2021.month2.no437;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3, new TreeNode(3), new TreeNode(-2)),
                        new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3, null, new TreeNode(11)));
        int sum1 = 3;
        System.out.println(pathSum1(root1, sum1));
    }

    private static int pathSum1(TreeNode root, int sum) {
        /*前缀和，time is O(n), space is O(n)
         * 前缀和：对于树来说，就是从根节点到当前节点的路径和
         * 前缀和的应用：
         * 在同一个路径下，如果两个节点的前缀和相等，则这两个节点之间的元素的总和为零（不包括起始节点）
         * 反推，则有：
         * 同一路径下的两个节点的路径和，等于末尾节点的路径和 减去先前节点的父节点的前缀和
         * 如：
                  10                            A
                 /  \                          / \
                5   -3                        B   C
               / \    \       <==>           / \   \
              3   2   11                    D   E   F
             / \   \                       / \   \
            3  -2   1                     G  H    I
         *      值                           节点编号
         *
         *  需要找的路径和sum为 8
         *  1、A的前缀和为10，D的前缀和为10 + 5 + 3 = 18，则 BD的路径和 = D的前缀和 - A的前缀和 = 8
         *  2、A的前缀和为10，I的前缀和为10 + 5 + 2 + 1 = 18，则 BI的路径和 = I的前缀和 - A的前缀和 = 8
         *  3、A的前缀和为10，F的前缀和为10 - 3 + 11 = 18，则 CF的路径和 = F的前缀和 - A的前缀和 = 8
         *
         * */
        Map<Integer, Integer> prefixSumMap = new HashMap<>();  //key为前缀和, value为等于key的前缀和的节点数量
//        加入默认前缀和，什么节点都不选的路径，以此使得根节点也会被算上（任意两点A,B的路径和 为B的前缀和 减去 A的*父节点*的前缀和，
//        因根节点也会被计算在内，所以需要加入什么节点都不选的前缀和）
        prefixSumMap.put(0, 1);
        return recursion(root, prefixSumMap, sum, 0);
    }

    private static int recursion(TreeNode root, Map<Integer, Integer> prefixSumMap, int sum, int prefixSum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
//        更新前缀和
        prefixSum += root.val;
//        查找当前路径上是否有节点到当前节点的路径和为sum，则在前缀和map中输入差值 (prefixSum - sum)
        res += prefixSumMap.getOrDefault(prefixSum - sum, 0);
//        将当前节点的前缀和加入到prefixSumMap中
        prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
//        分别递归查找左右子树
        res += recursion(root.left, prefixSumMap, sum, prefixSum);
        res += recursion(root.right, prefixSumMap, sum, prefixSum);
//        以当前节点为跟的子树已遍历完成，需要将当前节点的前缀和移除
        prefixSumMap.put(prefixSum, prefixSumMap.get(prefixSum) - 1);
        return res;
    }

    public static int pathSum(TreeNode root, int sum) {
        return dfs(root, sum, false);
    }

    private static int dfs(TreeNode root, int sum, boolean inPath) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        if (!inPath) {
            count += dfs(root.left, sum, false);
            count += dfs(root.right, sum, false);
        }
        count += dfs(root.left, sum - root.val, true);
        count += dfs(root.right, sum - root.val, true);
        return count;
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
* 给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-sum-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

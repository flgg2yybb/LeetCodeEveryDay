package year2021.month3.no108;

public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        int[] nums1 = {-10, -3, 0, 5, 9};
        int[] nums2 = {1, 3};
        System.out.println(sortedArrayToBST(nums1));
        System.out.println(sortedArrayToBST(nums2));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return generateBST(nums, 0, nums.length);
    }

    private static TreeNode generateBST(int[] nums, int start, int end) {
        // [start, end)
        if (start >= end) {
            return null;
        }
        // 向下取整，满足 mid < end
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = generateBST(nums, start, mid);
        node.right = generateBST(nums, mid + 1, end);
        return node;
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

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

/*
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。

高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

 

示例 1：


输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]
解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：

示例 2：


输入：nums = [1,3]
输出：[3,1]
解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 

提示：

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums 按 严格递增 顺序排列

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

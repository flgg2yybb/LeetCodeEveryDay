package year2021.month7.jz33;

public class VerifyPostorder {

    public static void main(String[] args) {
        int[] postorder1 = {1, 6, 3, 2, 5};
        int[] postorder2 = {1, 3, 2, 6, 5};
        System.out.println(verifyPostorder(postorder1));
        System.out.println(verifyPostorder(postorder2));
    }

    public static boolean verifyPostorder(int[] postorder) {
        /*
         * 后序遍历，左 -> 右 -> 跟
         * 二叉搜索树：
         *   * 左子树所有节点值 < 根节点值
         *   * 右子树所有节点值 > 根节点值
         * */
        return dfs(postorder, 0, postorder.length - 1);
    }

    private static boolean dfs(int[] postorder, int l, int r) {
        if (l >= r) {
            return true;
        }
        int left = l;
        // r 为根节点
        while (postorder[left] < postorder[r]) {
            left++;
        }
        int right = left;
        while (postorder[right] > postorder[r]) {
            right++;
        }
        return right == r && dfs(postorder, l, left - 1) && dfs(postorder, left, right - 1);
    }

}

/*
* 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

 

参考以下这颗二叉搜索树：

     5
    / \
   2   6
  / \
 1   3
示例 1：

输入: [1,6,3,2,5]
输出: false
示例 2：

输入: [1,3,2,6,5]
输出: true
 

提示：

数组长度 <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month1.no96;

public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        int nums1 = 3;
        int nums2 = 5;
        System.out.println(numTrees(nums1));
        System.out.println(numTrees(nums2));
    }

    public static int numTrees(int n) {
        /*思路：
         * 1 ... n 个节点中，若选择第 i 个节点作为根节点，则
         * 1 ... i - 1 为左子树节点，i + 1 ... n 为右字数节点
         * 定义 G(n) 为 n 个节点的二叉搜索树的个数，f(i) 为 n 个节点中以第 i 个节点作为跟的二叉搜索树的个数，则
         * G(n) = f(1) + f(2) + ... + f(n)
         * 且
         * f(i) = G(i - 1) + G(n - i)
         * 则，
         * G(n) = G(0) * G(n - 1) + G(1) * G (n - 2) + ... + G (n - 1) * G (0)
         * G(n) 即为动态转移方程
         * */
        if (n < 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
//        空树
        dp[0] = 1;
//        只存在根节点
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

}

/*给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

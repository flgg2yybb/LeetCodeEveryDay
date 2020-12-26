package twenty.december.no72;

public class EditDistance {
    public static void main(String[] args) {
        String word11 = "horse";
        String word12 = "ros";
        String word21 = "intention";
        String word22 = "execution";
        System.out.println(minDistance(word11, word12));
        System.out.println(minDistance(word21, word22));
    }

    public static int minDistance(String word1, String word2) {
        /*DP
         * 状态定义：
         * dp[i][j]为word1中前i个字符（包含i）与word2中前j个字符（包含j）
         * 匹配所需要的最小操作数
         * 状态转移方程：
         * if word1[i] == word2[j]
         *   word1中第i个字符与word2中第j个字符刚好相等，则当前不需要操作
         *   dp[i][j] = dp[i-1][j-1]
         * else
         *   当前选操作insert、delete、replace中最小的操作数
         *   dp[i][j] = min{dp[i][j-1],      //insert
         *                  dp[i-1][j],      //delete
         *                  dp[i-1][j-1],    //replace
         *              } + 1
         * 初始值：
         * word1中前0个字符与word2中前j个字符相匹配的最小操作数，即在word1前将word2中的前j个字符一一insert
         * dp[0][j] = j
         * word1中前i个字符与word2中前0个字符相匹配的最小操作数，即将word1中前i个字符一一删除
         * dp[i][0] = i
         * */
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

/*
* 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
 

示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
 

提示：

0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/edit-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month9.no378;

public class KthSmallestElementInSortedMatrix {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k1 = 8;
        int[][] matrix2 = {{-5}};
        int k2 = 1;
        System.out.println(kthSmallest(matrix1, k1));
        System.out.println(kthSmallest(matrix2, k2));
    }

    public static int kthSmallest(int[][] matrix, int k) {
        /*
         * 二分法
         * 矩阵每行和每列元素均按升序排序
         * 则左上角元素为最小值，右下角元素为最大值
         * 每次取中间值，同时统计左上方的元素个数
         * 从而逼近第 k 小的元素
         * */
        int n = matrix.length;
        int min = matrix[0][0];
        int max = matrix[n - 1][n - 1];
        while (min < max) {
            int mid = min + (max - min) / 2;
            int count = countSmallAndEqualMid(matrix, mid);
            if (count < k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }

    private static int countSmallAndEqualMid(int[][] matrix, int mid) {
        int x = matrix.length - 1;
        int y = 0;
        int count = 0;
        while (x >= 0 && y < matrix[0].length) {
            if (matrix[x][y] <= mid) {
                y++;
                // 此处更新 count 则更为简单，因为每次 y++ 就代表着能确定前一列小于 mid 的元素个数
                // 而 x-- 至 x < 0 时，说明当前列 y 没有小于 mid 的元素，故不需要额外考虑
                count += x + 1;
            } else {
                x--;
            }
        }
        return count;
    }

    private static int countSmallAndEqualMid1(int[][] matrix, int mid) {
        int x = matrix.length - 1;
        int y = 0;
        int count = 0;
        while (x >= 0 && y < matrix[0].length) {
            if (matrix[x][y] <= mid) {
                y++;
                if (y == matrix[0].length) {
                    count += (x + 1) * y;
                }
            } else {
                // 若 count 在此处更新，则如果最后循环退出条件是 y == matrix[0].length，假设退出循环是 x = a
                // 则退出循环是需要加上 (a+1)*y 个元素
                count += y;
                x--;
            }
        }
        return count;
    }

}

/*
* 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。

 

示例 1：

输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
输出：13
解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
示例 2：

输入：matrix = [[-5]], k = 1
输出：-5
 

提示：

n == matrix.length
n == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
1 <= k <= n2

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

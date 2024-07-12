package year2021.month4.no240;

public class Search2DMatrixII {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target1 = 5;
        int[][] matrix2 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target2 = 20;
        System.out.println(searchMatrix(matrix1, target1));
        System.out.println(searchMatrix(matrix2, target2));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        // matrix 的性质：每行的元素从左到右升序排列；每列的元素从上到下升序排列。
        // 故对于 matrix 的任意一个元素，当前行的右边部分递增，当前列的上边部分递减
        // 故可从 matrix 左下角元素开始寻找，用做二分
        // time is O(n+m), space is O(1)
        int x = matrix.length - 1;
        int y = 0;
        while (x >= 0 && y < matrix[0].length) {
            int value = matrix[x][y];
            if (value == target) {
                return true;
            } else if (value > target) {
                x--;
            } else {
                y++;
            }
        }
        return false;
    }

}

/*
* 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
 

示例 1：


输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
示例 2：


输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false
 

提示：

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matix[i][j] <= 109
每行的所有元素从左到右升序排列
每列的所有元素从上到下升序排列
-109 <= target <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

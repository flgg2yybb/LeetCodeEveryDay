package year2021.month2.no240;

public class Search2DMatrixII {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 13, 14, 22},
                {10, 12, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target1 = 5;
        int target2 = 20;
        int target3 = 3;
        int target4 = 1;
        int target5 = 0;
        int target6 = 13;
        System.out.println(searchMatrix1(matrix, target1));
        System.out.println(searchMatrix1(matrix, target2));
        System.out.println(searchMatrix1(matrix, target3));
        System.out.println(searchMatrix1(matrix, target4));
        System.out.println(searchMatrix1(matrix, target5));
        System.out.println(searchMatrix1(matrix, target6));
    }

    private static boolean searchMatrix1(int[][] matrix, int target) {
        /*思路：
         * 对于给出的矩阵有一下两个性质
         *   *每行的元素从左到右升序排列。
         *   *每列的元素从上到下升序排列。
         * 则对于矩阵最左下角的元素，必有
         *   *小于正右方的元素
         *   *大于正上方的元素
         * 则可用类似于二叉搜索树的方式搜索节点
         * time is O(m+n), space is O(1)
         * */
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
//        起点坐标
        int row = m - 1;
        int col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        /*二分搜索, time is O(logn), space is O(1)
         * 先对第一行元素进行二分查找，找到小于target的最大元素
         * 再对该元素所在的列进行二分查找，查找是否存在target元素
         * */
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        int maxCol = binarySearchMaxCol(matrix, target);
        if (matrix[0][maxCol] == target) {
            return true;
        }
        int maxRow = binarySearchMaxRow(matrix, target);
        if (matrix[maxRow][0] == target) {
            return true;
        }
        for (int i = maxCol; i > 0; i--) {
            int left = 1;
            int right = maxRow;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (matrix[mid][i] == target) {
                    return true;
                } else if (matrix[mid][i] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }

    private static int binarySearchMaxRow(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private static int binarySearchMaxCol(int[][] matrix, int target) {
        int left = 0;
        int right = matrix[0].length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (matrix[0][mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}

/*
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
 

示例 1：


输入：matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, target = 5
输出：true
示例 2：


输入：matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, target = 20
输出：false
 

提示：

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matix[i][j] <= 109
每行的所有元素从左到右升序排列
每列的所有元素从上到下升序排列
-109 <= target <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

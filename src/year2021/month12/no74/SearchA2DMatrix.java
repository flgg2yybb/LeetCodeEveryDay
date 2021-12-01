package year2021.month12.no74;

public class SearchA2DMatrix {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target1 = 3;
        int[][] matrix2 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target2 = 13;
        int[][] matrix3 = {{1}};
        int target3 = 1;
        System.out.println(searchMatrix1(matrix1, target1));
        System.out.println(searchMatrix1(matrix2, target2));
        System.out.println(searchMatrix1(matrix3, target3));
    }

    private static boolean searchMatrix1(int[][] matrix, int target) {
        /*
         * 若将矩阵每一行拼接在上一行的末尾，则会得到一个升序数组，我们可以在该数组上二分找到目标元素。
         * 代码实现时，可以二分升序数组的下标，将其映射到原矩阵的行和列上。
         * */
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n * m - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int i = mid / n;
            int j = mid % n;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        // 先根据矩阵第一列进行二分查找，找到小于等于 target 的最大元素
        // 而后在当前找到的元素行再进行二分查找
        if (target < matrix[0][0]) {
            return false;
        }
        int top = 0;
        int bottom = matrix.length - 1;
        while (top < bottom) {
            int mid = (top + bottom + 1) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                top = mid;
            } else {
                bottom = mid - 1;
            }
        }
        int row = top;
        int left = 0;
        int right = matrix[0].length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

}

/*
* 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。
 

示例 1：


输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true
示例 2：


输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
输出：false
 

提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-a-2d-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month6.no378;

import java.util.Comparator;
import java.util.PriorityQueue;

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
        System.out.println(kthSmallest1(matrix1, k1));
        System.out.println(kthSmallest1(matrix2, k2));
    }

    private static int kthSmallest1(int[][] matrix, int k) {
        /*
         * 二分查找
         * 由于矩阵每行每列单调不减，故左上角的元素 matrix[0][0] 为最小元素，记为 left
         * 右下角的元素 matrix[n - 1][n - 1] 为最大元素，记为 right
         * 而对于左下角的元素，满足
         * 大于上方的元素，小于右方的元素，
         * 对于矩阵中以每一个元素为左下角所形成的子矩阵均有次性质，故可利用其进行二分
         * 对于上下界 [left, right]，取平均数 mid
         * 可通过从左下角向右上方遍历将矩阵分为两半（左上方矩阵，右下方矩阵）
         * 定义， <= mid 的元素在左上方，> mid 的元素在右下方
         * 则可用左上方的元素的数量 与 k 的关系进行二分
         *
         * */
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1); // mid 为虚拟数，不一定在矩阵中
            int count = calLeftTopCount(matrix, mid);
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 为什么最后返回的 left 一定在矩阵中？
        // 由于每次二分我们都确保第 k 小的数在 [left, right] 范围中，若最终循环结束时 left == right
        // 则第 k 小的数就只能为 left
        return left;
    }

    private static int calLeftTopCount(int[][] matrix, int key) {
        int count = 0;
        int x = matrix.length - 1;
        int y = 0;
        while (x >= 0 && y < matrix[0].length) {
            int curr = matrix[x][y];
            if (curr <= key) {
                count += x + 1;
                y++;
            } else {
                x--;
            }
        }
        return count;
    }

    public static int kthSmallest(int[][] matrix, int k) {
        // 由矩阵中每行元素递增，则可使用归并排序，归并到第 k 小元素停止
        // 使用最小堆（优先队列）
        // 堆内元素数组为 {value, row, col}
        // time is O(klogn) , space is O(n)
        // 最坏情况下 k = n^2, 故最坏时间复杂度为 O(n^2 logn)
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        for (int i = 0; i < matrix.length; i++) {
            priorityQueue.add(new int[]{matrix[i][0], i, 0});
        }
        int i = 1;
        while (i < k) {
            int[] poll = priorityQueue.poll();
            assert poll != null;
            if (poll[2] < matrix[0].length - 1) {
                priorityQueue.add(new int[]{matrix[poll[1]][poll[2] + 1], poll[1], poll[2] + 1});
            }
            i++;
        }
        assert priorityQueue.peek() != null;
        return priorityQueue.peek()[0];
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
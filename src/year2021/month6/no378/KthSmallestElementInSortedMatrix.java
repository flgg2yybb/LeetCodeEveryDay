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
        System.out.println(kthSmallest(matrix1, k1));
        System.out.println(kthSmallest(matrix2, k2));
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
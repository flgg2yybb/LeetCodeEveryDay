package year2021.month7.jz40;

import java.util.Arrays;
import java.util.PriorityQueue;

public class GetLeastNumbers {

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1};
        int k1 = 2;
        int[] arr2 = {0, 1, 2, 1};
        int k2 = 1;
        System.out.println(Arrays.toString(getLeastNumbers1(arr1, k1)));
        System.out.println(Arrays.toString(getLeastNumbers1(arr2, k2)));
    }

    private static int[] getLeastNumbers1(int[] arr, int k) {
        // 利用快排思想找到第 k 小的数的位置，则[0,pos]即为最小的 k 个数
        // Avg time is O(n), worst time is O(n^2), space is O(n)
        if (k <= 0) {
            return new int[0];
        }
        if (k >= arr.length) {
            return arr;
        }
        partitionForTopKth(arr, k);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private static void partitionForTopKth(int[] arr, int k) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int pivot = partition(arr, start, end);
            if (pivot == k - 1) {
                return;
            }
            if (pivot < k - 1) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int key = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= key) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= key) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = key;
        return left;
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        if (k >= arr.length) {
            return arr;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        for (int i = 0; i < k; i++) {
            maxHeap.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        int[] ans = new int[k];
        int pos = k - 1;
        while (!maxHeap.isEmpty()) {
            ans[pos] = maxHeap.poll();
            pos--;
        }
        return ans;
    }

}

/*
* 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

 

示例 1：

输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
示例 2：

输入：arr = [0,1,2,1], k = 1
输出：[0]
 

限制：

0 <= k <= arr.length <= 10000
0 <= arr[i] <= 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month11.no347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 1;
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(Arrays.toString(topKFrequent(nums1, k1)));
        System.out.println(Arrays.toString(topKFrequent(nums2, k2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.merge(num, 1, Integer::sum);
        }
        int[] freqArr = freqMap.values().stream().mapToInt(Integer::intValue).toArray();
        int freq = findTheNthFreq(freqArr, freqArr.length - k);
        return freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() >= freq)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int findTheNthFreq(int[] freqArr, int n) {
        int start = 0;
        int end = freqArr.length - 1;
        while (start < end) {
            int pivot = partition(freqArr, start, end);
            if (pivot == n) {
                return freqArr[pivot];
            } else if (pivot < n) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
            }
        }
        return freqArr[start];
    }

    private static int partition(int[] nums, int left, int right) {
        int key = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= key) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= key) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        return left;
    }
}

/*
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

 

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
 

提示：

1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 

进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/top-k-frequent-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

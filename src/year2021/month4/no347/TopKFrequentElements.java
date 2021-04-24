package year2021.month4.no347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] nums2 = {1};
        int k2 = 1;
        int[] nums3 = {1, 2};
        int k3 = 2;
        System.out.println(Arrays.toString(topKFrequent(nums1, k1)));
        System.out.println(Arrays.toString(topKFrequent(nums2, k2)));
        System.out.println(Arrays.toString(topKFrequent(nums3, k3)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 先遍历一次计算出每个元素与其频数（出现次数）   ==> T: O(n), S: O(n)
        // 然后对频数数组进行查找第 K 大的元素，Partition 思想   ==> T: avg is O(n), worse is O(n^2)
        // 将大于第二部算出来第 K 大的频数的元素过滤粗来     ==> T: O(n)
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).boxed().forEach(num -> map.put(num, map.getOrDefault(num, 0) + 1));
        int[] counts = map.values().stream().mapToInt(Integer::intValue).toArray();
        int kthCount = findMaxKth(counts, k);
        return map.keySet().stream()
                .filter(key -> map.get(key) >= kthCount)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int findMaxKth(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        // the kth max is the (nums.lenth - k + 1)th min, the index should be nums.length - k
        int index = nums.length - k;
        if (index < left || index > right) {
            throw new IllegalArgumentException("illegal k");
        }
        while (left <= right) {
            int pivot = partition(nums, left, right);
            if (pivot == index) {
                return nums[pivot];
            } else if (pivot > index) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        return -1;
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
 

进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/top-k-frequent-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

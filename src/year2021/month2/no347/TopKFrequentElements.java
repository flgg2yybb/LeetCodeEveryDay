package year2021.month2.no347;

import java.util.*;
import java.util.stream.Collectors;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] nums2 = new int[]{1};
        int k2 = 1;
        disp(topKFrequent1(nums1, k1));
        disp(topKFrequent1(nums2, k2));
    }

    private static int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        // 使用Quick Sort中的Partition操作找出第k个高频元素出现的次数，即在出现次数数组中查找第k大的元素，
        // avg time is O(n), worse time is O(n^2), space is O(n)
        int[] counts = countMap.values().stream().mapToInt(Integer::valueOf).toArray();
        int kCount = getKthMinElement(counts, counts.length - k);
        return countMap.keySet().stream().filter(key -> countMap.get(key) >= kCount)
                .mapToInt(Integer::valueOf).toArray();
    }

    private static int getKthMinElement(int[] nums, int k) {
        return recursiveGetKthMax(nums, k, 0, nums.length - 1);
    }

    private static int recursiveGetKthMax(int[] nums, int k, int start, int end) {
        if (k < start || k > end) {
            throw new IllegalArgumentException();
        }
        int left = start;
        int right = end;
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        if (left == k) {
            return nums[left];
        }
        return left < k ? recursiveGetKthMax(nums, k, left + 1, end) : recursiveGetKthMax(nums, k, start, left - 1);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        //容量为K的最小堆，以元素出现次数进行堆排序
        PriorityQueue<Element> priorityQueue = new PriorityQueue<>((Comparator.comparingInt(Element::getCount)));
        for (int num : countMap.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(new Element(num, countMap.get(num)));
                continue;
            }
            if (countMap.get(num) <= priorityQueue.peek().getCount()) {
                continue;
            }
            priorityQueue.poll();
            priorityQueue.add(new Element(num, countMap.get(num)));
        }
        return priorityQueue.stream().map(Element::getValue).mapToInt(Integer::valueOf).toArray();
    }

    private static void disp(int[] nums) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}

class Element {
    private final int value;
    private final int count;

    public Element(int value, int count) {
        this.value = value;
        this.count = count;
    }

    public int getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }
}

/*
* 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

 

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
 

提示：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
你可以按任意顺序返回答案。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/top-k-frequent-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

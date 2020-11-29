package twenty.november.no169;

import java.util.*;

public class MajorityElement {
    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 2, 2, 3};
        int[] nums2 = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement3(nums1));
        System.out.println(majorityElement3(nums2));
    }

    private static int majorityElement3(int[] nums) {
//        摩尔投票法，遇到相同的数，就投一票，遇到不同的数，就减一票，最后还存在票的数就是众数
//        time O(n), space O(1), 一次遍历
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        Integer candidate = null;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            count += candidate == nums[i] ? 1 : -1;
        }
        return candidate;
    }

    private static int majorityElement2(int[] nums) {
//        time O(n), space O(n), 一次遍历
        if (nums == null || nums.length == 0) {
            return 0;
        }
//        若存在众数（出现次数 > n/2），那么每次删掉两个不同的元素，删到最后一定会出现众数（num[left]）
//        deprecate用来标记除0之外的已删除元素的下标，用于left跳过
        Set<Integer> deprecate = new HashSet<>();
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                deprecate.add(right);
                left++;
                while (deprecate.contains(left)) {
                    left++;
                }
            }
            right++;
        }
        return nums[left];
    }

    private static int majorityElement1(int[] nums) {
//        brute force
//        time O(n), space O(n), 两次遍历
        Map<Integer, Integer> countMap = new HashMap<>();
        Arrays.stream(nums).forEach(num -> countMap.put(num, countMap.getOrDefault(num, 0) + 1));
        for (Integer key : countMap.keySet()) {
            if (countMap.get(key) > nums.length / 2) {
                return key;
            }
        }
        return 0;
    }

}

/*
* 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

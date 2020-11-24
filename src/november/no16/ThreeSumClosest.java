package november.no16;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-3, -2, -5, 3, -4};
        int target = -1;
        System.out.println(threeSumClosest2(nums, target));
    }

    private static int threeSumClosest2(int[] nums, int target) {
//        use sort and two pointers, time is O(n^2)
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int closest = 100000;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum > target) {
                    int curRight = right;
                    while (left < right && nums[right] == nums[curRight]) {
                        right--;
                    }
                } else {
                    int curLeft = left;
                    while (left < right && nums[left] == nums[curLeft]) {
                        left++;
                    }
                }
            }
        }
        return closest;
    }

    private static int threeSumClosest1(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
//        ascending sorting
        Arrays.sort(nums);
        int margin = Integer.MAX_VALUE;
        int closest = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int k = target - nums[i] - nums[j];
//                int closestK = linearSearch(nums, j + 1, k);
                int closestK = binarySearchClosest(nums, j + 1, k);
                if (Math.abs(k - closestK) < margin) {
                    margin = Math.abs(k - closestK);
                    closest = nums[i] + nums[j] + closestK;
                }
            }

        }
        return closest;
    }

    private static int binarySearchClosest(int[] nums, int start, int target) {
//        nums is ascending sorting array
        int left = start;
        int right = nums.length - 1;
        int margin = Math.abs(target - nums[left]);
        int closest = nums[left];
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] == target) {
                return target;
            }
            int diff = nums[mid] - target;
            if (Math.abs(diff) < margin) {
                margin = Math.abs(diff);
                closest = nums[mid];
            }
            if (diff < 0) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return closest;
    }

    private static int linearSearch(int[] nums, int start, int target) {
        int margin = Integer.MAX_VALUE;
        int closestTarget = 0;
        for (int i = start; i < nums.length; i++) {
            if (Math.abs(target - nums[i]) < margin) {
                margin = Math.abs(target - nums[i]);
                closestTarget = nums[i];
            }
        }
        return closestTarget;
    }
}

/*
* 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 

示例：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 

提示：

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum-closest
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */


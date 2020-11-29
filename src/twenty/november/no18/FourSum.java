package twenty.november.no18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FourSum {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 0, -1, 0, -2, 2};
        int target1 = 0;
        int[] nums2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        int target2 = 0;
        List<List<Integer>> result = fourSum(nums2, target2);
        disp(result);
    }

//  可优化的剪肢操作
/*
* 具体实现时，还可以进行一些剪枝操作：

在确定第一个数之后，如果 nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target，说明此时剩下的三个数无论取什么值，四数之和一定大于 {target}target，因此退出第一重循环；
在确定第一个数之后，如果 nums[i]+nums[n−3]+nums[n−2]+nums[n−1]<target，说明此时剩下的三个数无论取什么值，四数之和一定小于 {target}target，因此第一重循环直接进入下一轮，枚举 {nums}[i+1]nums[i+1]；
在确定前两个数之后，如果 nums[i]+nums[j]+nums[j+1]+nums[j+2]>target，说明此时剩下的两个数无论取什么值，四数之和一定大于 {target}target，因此退出第二重循环；
在确定前两个数之后，如果 nums[i]+nums[j]+nums[n−2]+nums[n−1]<target，说明此时剩下的两个数无论取什么值，四数之和一定小于 {target}target，因此第二重循环直接进入下一轮，枚举 {nums}[j+1]nums[j+1]。

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/4sum/solution/si-shu-zhi-he-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return Collections.EMPTY_LIST;
        }
//        先排序
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
//            保证每次的i不同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
//                保证每次的j不同
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int a = nums[i];
                int b = nums[j];
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int c = nums[left];
                    int d = nums[right];
                    if (a + b + c + d == target) {
                        List<Integer> fourSum = Arrays.asList(a, b, c, d);
                        resultList.add(fourSum);
//                        保证left不指向重复元素的下标，同时用left < right保证不出线越界
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
//                        当前left指向的元素必不与left - 1指向的元素相等
                        left++;
//                        right同理
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (a + b + c + d > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return resultList;
    }

    private static void disp(List<List<Integer>> result) {
        result.forEach(System.out::println);
    }
}

/*
* 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

答案中不可以包含重复的四元组。

示例：

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/4sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

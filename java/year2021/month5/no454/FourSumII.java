package year2021.month5.no454;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 将nums3数组和nums4数组相加的数以及出现的次数放入到map中
        // 分别遍历nums1、nums2，在map中寻找是否有 nums1[i] + nums2[j] + x = 0
        // 将四数相加简化为三数和
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int c : nums3) {
            for (int d : nums4) {
                int key = c + d;
                countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            }
        }
        int count = 0;
        for (int a : nums1) {
            for (int b : nums2) {
                int diff = -(a + b);
                count += countMap.getOrDefault(diff, 0);
            }
        }
        return count;
    }

}

/*
* 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

例如:

输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:
2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/4sum-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

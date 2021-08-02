package year2021.month8.jz56b;

public class SingleNumber {

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 3, 3};
        int[] nums2 = {9, 1, 7, 9, 7, 9, 7};
        System.out.println(singleNumber(nums1));
        System.out.println(singleNumber(nums2));
    }

    public static int singleNumber(int[] nums) {
        /*
         * nums 中只有一个数字只出现一次，其他数字都出现了三次
         * 对于其他出现三次的数字来说，其二级制位也一定出现了三次
         * 故对所有数字的二进制位出现次数进行累加，最后 %3
         * 即是出现一次数字的二进制位
         * */
        int[] bits = new int[32];
        for (int num : nums) {
            int mask = 1;
            for (int i = 0; i < 31; i++) {
                if ((mask & num) != 0) {
                    bits[i]++;
                }
                mask <<= 1;
            }
        }
        for (int i = 0; i < bits.length; i++) {
            bits[i] %= 3;
        }
        int ans = 0;
        int mask = 1;
        for (int bit : bits) {
            if (bit == 1) {
                ans |= mask;
            }
            mask <<= 1;
        }
        return ans;
    }

}

/*
* 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

 

示例 1：

输入：nums = [3,4,3,3]
输出：4
示例 2：

输入：nums = [9,1,7,9,7,9,7]
输出：1
 

限制：

1 <= nums.length <= 10000
1 <= nums[i] < 2^31
 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

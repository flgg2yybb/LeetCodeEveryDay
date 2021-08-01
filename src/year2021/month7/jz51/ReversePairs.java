package year2021.month7.jz51;

public class ReversePairs {

    public static void main(String[] args) {
        int[] nums1 = {7, 5, 6, 4};
        int[] nums2 = {};
        System.out.println(reversePairs(nums1));
        System.out.println(reversePairs(nums2));
    }

    public static int reversePairs(int[] nums) {
        // Brute Force, time limit exceed
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

}

/*
* 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。



示例 1:

输入: [7,5,6,4]
输出: 5


限制：

0 <= 数组长度 <= 50000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

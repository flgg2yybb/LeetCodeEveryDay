package year2021.month7.jz66;

import java.util.Arrays;

public class ConstructArr {

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = {120, 60, 40, 30, 24};
        System.out.println(Arrays.toString(constructArr1(a1)));
        System.out.println(Arrays.toString(constructArr1(a2)));
    }

    private static int[] constructArr1(int[] a) {
        if (a == null || a.length < 2) {
            return new int[0];
        }
        int length = a.length;
        int[] ans = new int[length];
        ans[0] = 1;
        for (int i = 1; i < length; i++) {
            ans[i] = ans[i - 1] * a[i - 1];
        }
        int right = 1;
        for (int i = length - 2; i >= 0; i--) {
            right = right * a[i + 1];
            ans[i] = ans[i] * right;
        }
        return ans;
    }

    public static int[] constructArr(int[] a) {
        if (a == null || a.length < 2) {
            return new int[0];
        }
        int length = a.length;
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

}

/*
* 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。

 

示例:

输入: [1,2,3,4,5]
输出: [120,60,40,30,24]
 

提示：

所有元素乘积之和不会溢出 32 位整数
a.length <= 100000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
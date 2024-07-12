package year2020.month12.no338;

import java.util.Arrays;

public class CountingBits {
    public static void main(String[] args) {
        int n1 = 0;
        int n2 = 2;
        int n3 = 5;
        disp(countBits3(n1));
        disp(countBits3(n2));
        disp(countBits3(n3));
    }

    private static int[] countBits3(int num) {
//        动态规划，递推公式： result[i] = result[i >> 1] + i % 2;
//          i >> 1 为右移一位，则 i >> 1 的 1的位数 与 i 的 1的位数就取决与右移的那一位是否为 1，
//          因此只需加上 i % 2即可得最低位是否为 1
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i >> 1] + i % 2;
        }
        return result;
    }

    private static int[] countBits2(int num) {
//        动态规划，递推公式： result[i] = result[i & (i - 1)] + 1;
//          i & (i - 1) 意味着把 i 中的最低位的 1 拿掉，那么 i 的二进制中 1 的位数，就比 i & (i - 1) 的二进制中的
//          1 的位数多 1，且 i > i & (i - 1)，所以可用递推公式计算，初始值 0 的 bits count为 0
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    public static int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            result[i] = hammingWeight(i);
        }
        return result;
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    private static void disp(int[] result) {
        Arrays.stream(result).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}

/*
* 给定一个非负整数  num。对于  0 ≤ i ≤ num 范围中的每个数字  i  ，计算其二进制数中的 1 的数目并将它们作为数组返回。

示例 1:

输入: 2
输出: [0,1,1]
示例  2:

输入: 5
输出: [0,1,1,2,1,2]
进阶:

给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
要求算法的空间复杂度为O(n)。
你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的  __builtin_popcount）来执行此操作。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/counting-bits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

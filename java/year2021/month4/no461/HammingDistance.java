package year2021.month4.no461;

public class HammingDistance {
    public static void main(String[] args) {
        int x1 = 1;
        int y1 = 4;
        System.out.println(hammingDistance1(x1, y1));
    }

    private static int hammingDistance1(int x, int y) {
        int distance = 0;
        int xor = x ^ y;    // 取异或
        while (xor != 0) {
            distance++;
            xor &= (xor - 1); // x = x & (x - 1) 去掉最低位的 1
        }
        return distance;
    }

    public static int hammingDistance(int x, int y) {
        int distance = 0;
        while (x != 0 && y != 0) {
            // 看最低位是否相同，不相同则距离加一
            int xMod = x % 2;
            int yMod = y % 2;
            if (xMod != yMod) {
                distance++;
            }
            // 同时右移一位
            x >>= 1;
            y >>= 1;
        }
        while (x != 0) {
            // 距离加一，同时去掉最低位的 1
            distance++;
            x &= (x - 1);
        }
        while (y != 0) {
            distance++;
            y &= (y - 1);
        }
        return distance;
    }
}

/*
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
0 ≤ x, y < 231.

示例:

输入: x = 1, y = 4

输出: 2

解释:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

上面的箭头指出了对应二进制位不同的位置。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/hamming-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

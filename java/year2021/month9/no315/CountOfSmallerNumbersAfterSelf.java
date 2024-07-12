package year2021.month9.no315;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        int[] nums1 = {5, 2, 6, 1};
        int[] nums2 = {4, 3, 2, 1};
        int[] nums3 = {1, 6, 2, 5, 3, 4};
        int[] nums4 = {};
        System.out.println(countSmaller(nums1));
        System.out.println(countSmaller(nums2));
        System.out.println(countSmaller(nums3));
        System.out.println(countSmaller(nums4));
    }

    public static List<Integer> countSmaller(int[] nums) {
        int length = nums.length;
        Element[] arr = new Element[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Element(nums[i], i);
        }
        int[] res = new int[length];
        mergeSort(arr, 0, length - 1, res);
        System.out.println(Arrays.toString(arr));
        return Arrays.stream(res)
                .boxed()
                .collect(Collectors.toList());
    }

    private static void mergeSort(Element[] arr, int start, int end, int[] res) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid, res);
        mergeSort(arr, mid + 1, end, res);
        merge(arr, start, mid, mid + 1, end, res);
    }

    private static void merge(Element[] arr, int lStart, int lEnd, int rStart, int rEnd, int[] res) {
        Element[] temp = new Element[lEnd - lStart + rEnd - rStart + 2];
        int arrStart = lStart;
        int pos = 0;
        while (lStart <= lEnd && rStart <= rEnd) {
            if (arr[lStart].value <= arr[rStart].value) {
                temp[pos] = arr[lStart];
                res[temp[pos].index] += rStart - lEnd - 1;
                lStart++;
            } else {
                temp[pos] = arr[rStart];
                rStart++;
            }
            pos++;
        }
        if (lStart <= lEnd) {
            for (int i = lStart; i <= lEnd; i++) {
                temp[pos] = arr[i];
                res[temp[pos].index] += rStart - lEnd - 1;
                pos++;
            }
        }
        if (rStart <= rEnd) {
            System.arraycopy(arr, rStart, temp, pos, rEnd - rStart + 1);
        }
        System.arraycopy(temp, 0, arr, arrStart, temp.length);
    }
}

class Element {
    int value;
    int index;

    public Element(int value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                ", index=" + index +
                '}';
    }
}

/*
* 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

 

示例：

输入：nums = [5,2,6,1]
输出：[2,1,1,0] 
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素
 

提示：

0 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package year2021.month6.no406;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QueueReconstructionByHeight {

    public static void main(String[] args) {
        int[][] people1 = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] people2 = {{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}};
        System.out.println(Arrays.deepToString(reconstructQueue1(people1)));
        System.out.println(Arrays.deepToString(reconstructQueue1(people2)));

    }

    private static int[][] reconstructQueue1(int[][] people) {
        /*
         * 按身高降序、k升序排序 people
         * 则对于每一个当前的 people 元素
         * 之前的元素一定大于等于当前元素
         * 故可根据当前 people 元素的 k 值
         * 和结果链表中找元素
         * 找到相应的位置后插入（类似插入排序）
         * */
        Arrays.sort(people, (nums1, nums2) -> {
            if (nums1[0] != nums2[0]) {
                return nums2[0] - nums1[0];
            }
            return nums1[1] - nums2[1];
        });
        List<int[]> ans = new LinkedList<>();
        for (int[] nums : people) {
            int pos = nums[1];
            ans.add(pos, nums);
        }
        return ans.toArray(new int[0][0]);
    }

    public static int[][] reconstructQueue(int[][] people) {
        /*
         * 按身高升序、k降序排序 people
         * 则对于每一个当前的 people 元素
         * 之后的元素一定大于等于当前元素
         * 故可根据当前 people 元素的 k 值
         * 在结果数组中找空格（之后的元素要插入）
         * 找到相应的空格则插入
         * */
        Arrays.sort(people, (nums1, nums2) -> {
            if (nums1[0] != nums2[0]) {
                return nums1[0] - nums2[0];
            }
            return nums2[1] - nums1[1];
        });
        int[][] ans = new int[people.length][];
        for (int[] nums : people) {
            int skip = nums[1];
            for (int i = 0; i < ans.length; i++) {
                if (ans[i] == null) {
                    if (skip == 0) {
                        ans[i] = nums;
                        break;
                    }
                    skip--;
                }
            }
        }
        return ans;
    }

}

/*
* 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。

请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。

 

示例 1：

输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
解释：
编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
示例 2：

输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 

提示：

1 <= people.length <= 2000
0 <= hi <= 106
0 <= ki < people.length
题目数据确保队列可以被重建

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

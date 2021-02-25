package year2021.month2.no406;

import java.util.*;
import java.util.stream.Collectors;

public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        int[][] people1 = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] people2 = new int[][]{{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}};
        int[][] people3 = new int[][]{{5, 1}, {5, 3}, {5, 0}, {5, 2}, {5, 5}, {5, 4}};
        disp(reconstructQueue1(people1));
        disp(reconstructQueue1(people2));
        disp(reconstructQueue1(people3));

    }

    private static int[][] reconstructQueue1(int[][] people) {
        /*思路：
         * 将people数组按 身高升序，按 k降序，则遍历people时
         * 矮个子的人先遍历，身高相同的 k大的先遍历
         * （按 k降序的原因是，对于同身高的人，ki大的一定排在后面，所以ki大的需要先遍历）
         * 则，对于每一个遍历的元素而言，之前已遍历的元素身高必小于等于本身，故只需要找空格位置插入即可
         * 对于同身高的元素，因为大k的必排在后面，所以对于小k来说，在遇到大k之前就已经插入队列里了
         * */
        Arrays.sort(people, (nums1, nums2) -> {
            if (nums1[0] != nums2[0]) {
                return nums1[0] - nums2[0];
            }
            return nums2[1] - nums1[1];
        });
        int[][] res = new int[people.length][];
        for (int[] nums : people) {
            int skip = nums[1];
            for (int i = 0; i < people.length; i++) {
                if (res[i] == null) {
                    if (skip == 0) {
                        res[i] = nums;
                        break;
                    }
                    skip--;
                }
            }
        }
        return res;
    }

    public static int[][] reconstructQueue(int[][] people) {
//        time is O(n^2), space is O(n)
        int[][] res = new int[people.length][];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((nums1, nums2) -> nums1 != nums2 ? nums1[0] - nums2[0] : nums1[1] - nums2[1]);
        Collections.addAll(minHeap, people);    //nlogn
        while (!minHeap.isEmpty()) {            //n
            int[] nums = minHeap.poll();        //logn
            int count = 0;
            int pos = 0;
            while (pos < res.length) {          //n
                if (res[pos] == null && count == nums[1]) {
                    break;
                }
                if (res[pos] == null || res[pos][0] >= nums[0]) {
                    count++;
                }
                pos++;
            }
            res[pos] = nums;
        }
        return res;
    }

    private static void disp(int[][] matrix) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int[] ints : matrix) {
            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
            lists.add(list);
        }
        System.out.println(lists);
    }

}

/*
* 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。

请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。

 

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

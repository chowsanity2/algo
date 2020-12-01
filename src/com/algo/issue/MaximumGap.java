package com.algo.issue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chowsanity
 * @date 2020年11月26日
 * @title 164. 最大间距
 */
public class MaximumGap {
    /**
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     *
     * 如果数组元素个数小于 2，则返回 0。
     *
     * 示例 1:
     *
     * 输入: [3,6,9,1]
     * 输出: 3
     * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
     * 示例 2:
     *
     * 输入: [10]
     * 输出: 0
     * 解释: 数组元素个数小于 2，因此返回 0。
     * 说明:
     *
     * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
     * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
     *
     */
    public static void main(String[] args) {
        int[] ints = {3, 6, 9, 1};
        System.out.println(maximumGap(ints));
    }

    public static int maximumGap(int[] nums) {
        if(nums.length <= 1){
            return 0;
        }
        //排序用基数排序或桶排序 保证时间复杂度 （快速排序，归并排序时间复杂度均为O(NlogN)）
        //Arrays.sort() 使用的快排
        Arrays.sort(nums);
        int min = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if(diff > min){
                min = diff;
            }
        }
        return min;
    }
}

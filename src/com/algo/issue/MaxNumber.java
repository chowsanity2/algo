package com.algo.issue;

import java.util.*;

/**
 * @author chowsanity
 * @date 2020年12月2日
 * @title 321. 拼接最大数
 * @see RemoveKdigits
 * @see RemoveDuplicateLetters
 */
public class MaxNumber {
    /**
     * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出
     *  k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     *
     * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     *
     * 说明: 请尽可能地优化你算法的时间和空间复杂度。
     *
     * 示例 1:
     *
     * 输入:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * 输出:
     * [9, 8, 6, 5, 3]
     * 示例 2:
     *
     * 输入:
     * nums1 = [6, 7]
     * nums2 = [6, 0, 4]
     * k = 5
     * 输出:
     * [6, 7, 6, 0, 4]
     * 示例 3:
     *
     * 输入:
     * nums1 = [3, 9]
     * nums2 = [8, 9]
     * k = 3
     * 输出:
     * [9, 8, 9]
     *
     */
    public static void main(String[] args) {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k1 = 5;
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, k1)));
        int[] nums3 = {3, 9};
        int[] nums4 = {8, 9};
        int k2 = 3;
        System.out.println(Arrays.toString(maxNumber(nums3, nums4, k2)));
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        //枚举可能出现的子序列长度组合
        //获取最大子序列
        //合并
        //比较获取最大序列
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    private static int [] maxSubsequence(int [] nums, int k){
        //保持数组相对位置，最大序列 stack.peek() < n 弹出
        //丢弃k等价保留n - k
        int n = nums.length;
        int remain = n - k;
        //保留相对位置
        Deque<Integer> q = new LinkedList<>();
        for (Integer c : nums) {
            while (remain > 0 && !q.isEmpty() && q.peek() < c) {
                q.pop();
                remain--;
            }
            q.push(c);
        }
        int[] buf = new int[k];
        int count = 0;
        while (!q.isEmpty() && count < k) {
            buf[count++] = q.removeLast();
        }
        return buf;

    }

    private static int [] merge(int [] nums1, int[] nums2){
        int l1 = nums1.length;
        int l2 = nums2.length;
        int mergeLength = l1 + l2;
        int [] mergeSort = new int[mergeLength];
        if (l1 == 0) {
            return nums2;
        }
        if (l2 == 0) {
            return nums1;
        }
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(nums1, index1, nums2, index2) > 0) {
                mergeSort[i] = nums1[index1++];
            } else {
                mergeSort[i] = nums2[index2++];
            }
        }
        return mergeSort;

    }

    private static int compare(int [] nums1, int index1, int[] nums2, int index2){
        int x = nums1.length, y = nums2.length;
        while (index1 < x && index2 < y) {
            int difference = nums1[index1] - nums2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

}

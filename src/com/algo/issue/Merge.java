package com.algo.issue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author chowsanity
 * @date 2020年10月23日
 * @title 给出两个有序的整数数组A和B，请将数组B合并到数组A中，变成一个有序的数组
 * 可以假设A数组有足够的空间存放B数组的元素，A和B中初始的元素数目分别为m和n
 */
public class Merge {
    public static void main(String[] args) {
        int [] nums1 = new int[10];
        int [] nums2 = new int[2];
        nums1[0] = 1;
        nums1[1] = 2;
        nums1[2] = 3;
        nums2[0] = 6;
        nums2[1] = 7;
        merge(nums1, 3, nums2, 2);
        display(nums1);
    }

    public static void merge(int [] nums1, int m, int [] nums2, int n){
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }

    public static void display(int [] nums){
        System.out.println(Arrays.toString(nums));
    }
}

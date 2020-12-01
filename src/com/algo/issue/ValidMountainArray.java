package com.algo.issue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chowsanity
 * @date 2020年11月3日
 * @title 941. 有效的山脉数组
 */
public class ValidMountainArray {

    /**
     * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     * A.length >= 3
     * 在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     *
     * 提示：
     * 0 <= A.length <= 10000
     * 0 <= A[i] <= 10000
     */
    public static void main(String[] args) {
        int [] a = {2,1};
        int [] b = {3,5,5};
        int [] c = {0,3,2,1};
        int [] d = {0,1,2,3,4,5,6,7,8,9};
        int [] e = {1,7,9,5,4,1,2};
        System.out.println(validMountainArray1(a));
        System.out.println(validMountainArray1(b));
        System.out.println(validMountainArray1(c));
        System.out.println(validMountainArray1(d));
        System.out.println(validMountainArray1(e));
    }

    public static boolean validMountainArray(int[] a) {
        if(a == null || a.length == 0){
            return false;
        }
        List<Integer> list = Arrays.stream(a).boxed().collect(Collectors.toList());
        int boundary = 3;
        Integer max = Collections.max(list);
        //1. 找到最大值索引
        int index = list.indexOf(max);
        boolean flag = true;
        if(list.size() < boundary){
            return false;
        }
        if( index > 0 && index < list.size() - 1 ) {
            //2. 双指针 0 < left <- max -> right < list.size()
            int left = index;
            int right = index;
            while(left > 0){
                if(list.get(left) <= list.get(--left)){
                    return false;
                }
            }
            while(right < list.size() - 1){
                if(list.get(right) <= list.get(++right)){
                    return false;
                }
            }
        }else{
            flag = false;
        }
        return flag;
    }


    /**
     * 大神题解
     * 可以使用两种指针，一个从左边找最高山峰，一个从右边找最高山峰，最后判断找到的是不是同一个山峰
     */
    public static boolean validMountainArray1(int[] a) {
        int length = a.length;
        int right = a.length - 1;
        int left = 0;
        while (left + 1 < length && a[left] < a[left + 1]){
            left++;
        }
        while (right - 1 > 0 && a[right] < a[right - 1]){
            right--;
        }
        return left > 0 && right < length - 1 && left == right;
    }
}

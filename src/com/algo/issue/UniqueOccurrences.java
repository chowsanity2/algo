package com.algo.issue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author chowsanity
 * @date 2020年10月28日
 * @title 独一无二的出现次数
 */
public class UniqueOccurrences {
    /**
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * 1 <= arr.length <= 1000
     * -1000 <= arr[i] <= 1000
     */
    public static void main(String[] args) {
        int [] arr = {1,2,2,1,1,3};
        System.out.println(uniqueOccurrences(arr));
    }

    public static boolean uniqueOccurrences(int[] arr) {
        if(arr == null){
            return false;
        }
        int [] count = new int [2001];
        for (int value : arr) {
            count[1000 + value]++;
        }
        HashSet<Integer> result = new HashSet<>();
        for (int x:
             count) {
            if(x == 0){
                continue;
            }
            if(!result.add(x)){
                return false;
            }
        }
        return true;
    }
}

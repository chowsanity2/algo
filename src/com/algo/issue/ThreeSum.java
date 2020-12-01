package com.algo.issue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author chowsanity
 * @date 2020年10月26日
 * @title 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
 * 注意：
 * 三元组（a、b、c）中的元素必须按非降序排列。（即a≤b≤c）
 * 解集中不能包含重复的三元组。
 */
public class ThreeSum {
    public static void main(String[] args) {
//        int [] num =  new int[]{-10,0,10,20,-10,-40};
        int [] num =  new int[]{-2,0,3,-1,4,0,3,4,1,1,1,-3,-5,4,0};
        System.out.println(threeSum(num));
    }

    /**
     * 思路： 取出一个数，双指针从首部尾部求和计算结果
     */
    public static ArrayList<ArrayList<Integer>> threeSum(int[] num){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(num == null) {
            return result;
        }
        Arrays.sort(num);
        int pl = 2;
        for (int i = 0; i < num.length - pl ; i++) {
            if(i != 0 && num[i] == num[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = num.length - 1;
            while(left < right){
                int sum = num[left] + num[right];
                if(num[i] + sum == 0){
                    ArrayList<Integer> node = new ArrayList<>();
                    node.add(num[i]);
                    node.add(num[left]);
                    node.add(num[right]);
                    result.add(node);
                    left++;
                    right--;
                    while(left < right && num[left] == num[left - 1] ){
                        left++;
                    }
                    while(left < right && num[right] == num[right + 1]){
                        right--;
                    }
                }else if(num[i] + sum > 0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }


}

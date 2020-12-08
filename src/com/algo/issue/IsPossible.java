package com.algo.issue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chowsanity
 * @date 2020年12月4日
 * @title 659. 分割数组为连续子序列
 */
public class IsPossible {
    /**
     * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
     *
     * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入: [1,2,3,3,4,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3
     * 3, 4, 5
     *  
     *
     * 示例 2：
     *
     * 输入: [1,2,3,3,4,4,5,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3, 4, 5
     * 3, 4, 5
     *  
     *
     * 示例 3：
     *
     * 输入: [1,2,3,4,4,5]
     * 输出: False
     *  
     *
     * 提示：
     *
     * 输入的数组长度范围为 [1, 10000]
     *  
     */
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 4, 5};
        System.out.println(isPossible(ints));
    }

    /**
     * 贪心算法 哈希 + 最小堆
     */
    public static boolean isPossible(int[] nums) {
        //子序列最后一个数字 - 序列长度
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>(16);
        for (int x:
             nums) {
            if(!map.containsKey(x)){
                map.put(x, new PriorityQueue<>());
            }
            if(map.containsKey(x - 1)){
                int preLength = 0;
                PriorityQueue<Integer> queue = map.get(x - 1);
                if(!queue.isEmpty()){
                    preLength =  queue.poll();
                }
                if(queue.isEmpty()){
                    map.remove(x - 1);
                }
                map.get(x).offer(preLength + 1);
            }else{
                map.get(x).offer(1);
            }

        }
        for (PriorityQueue<Integer> queue:
             map.values()) {
            if(!queue.isEmpty()){
                if(queue.peek() < 3){
                    return false;
                }
            }

        }
        return true;
    }
}

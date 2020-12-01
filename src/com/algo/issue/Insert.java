package com.algo.issue;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chowsanity
 * @date 2020年11月4日
 * @title 57. 插入区间
 */
public class Insert {
    /**
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * <p>
     * 示例 1：
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     * <p>
     * 示例 2：
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     */
    public static void main(String[] args) {
        int [][] intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        int [] newInterval = {4, 8};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    /**
     * 插入新区间[L,R]
     * 若没有交集 r < L 或 l > R，则直接加入结果集
     * 若有交集，则取并集 [min(l,L),Max(r,R)]
     */
    public static int[] [] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int left = newInterval[0];
        int right = newInterval[1];
        //保证区间左端点排序
        boolean flag = false;
        for (int[] interval : intervals) {
            if(interval[0] > right){
                if(!flag){
                    result.add(new int[]{left, right});
                    flag = true;
                }
                result.add(interval);
            }
            else if(interval[1] < left){
                result.add(interval);
            }else{
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if(!flag){
            result.add(new int[]{left, right});
        }
        int [][] ans = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }


}

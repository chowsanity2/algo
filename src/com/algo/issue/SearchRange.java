package com.algo.issue;

import java.util.Arrays;

/**
 * @author chowsanity
 * @date 2020年12月1日
 * @title 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class SearchRange {
    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 进阶：
     * <p>
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *  
     * 示例 1：
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     * <p>
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *  
     * 提示：
     * <p>
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     */
    public static void main(String[] args) {
        int[] ints = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(ints, 6)));
    }


    public static int[] searchRange(int[] nums, int target) {
        //二分
        int left = 0;
        int right = nums.length;
        //左边界 [left, right) 右边界 [left, right)
        return new int[]{leftBoundary(nums, target, left, right), rightBoundary(nums, target, left, right)};
    }

    private static int leftBoundary(int[] a, int target, int left, int right) {
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (a[mid] == target) {
                right = mid;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else if (a[mid] > target) {
                right = mid;
            }
        }
        //处理目标值大于最大值，指针不断右移，移动到右边界
        if (left == a.length) {
           return -1;
        }
        return a[left] == target ? left : -1;
    }

    private static int rightBoundary(int[] a, int target, int left, int right) {
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if (a[mid] == target) {
                left = mid + 1;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else if (a[mid] > target) {
                right = mid;
            }
        }
        //处理目标值小于最小值，指针不断左移，移动到左边界
        if(left == 0){
            return -1;
        }
        return a[left - 1] == target ? left - 1 : -1;
    }


}

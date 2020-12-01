package com.algo.search;

import com.algo.sort.Sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author admin
 */
public class Search {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = random.ints(1, 30).limit(50).toArray();
        new Sort().quickSort(arr);
        System.out.println("raw array is:");
        System.out.println(Arrays.toString(arr));
        int[] arr1 = arr.clone();
        int[] arr2 = arr.clone();
        long start1 = System.currentTimeMillis();
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        int i2 = binarySearch(arr, 20);
        long end2 = System.currentTimeMillis();
        System.out.println("binary search cost: " + (end2 - start2) + "ms");
        System.out.println("index is: " + i2);

    }

    public static int binarySearch(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target){
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 左边界
     */
    public static int leftBoundary(int[] arr, int target){
        if (arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                right = mid;
            } else if (arr[mid] > target) {
                right = mid;
            } else if (arr[mid] < target){
                left = mid + 1;
            }
        }
        if(left == arr.length){
            return -1;
        }
        return arr[left] == target ? left : -1;
    }

    /**
     * 右边界
     */
    public static int rightBoundary(int[] arr, int target){
        if (arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid;
            } else if (arr[mid] < target){
                left = mid + 1;
            }
        }
        if(left == 0){
            return -1;
        }
        return arr[left] == target ? (left - 1) : -1;
    }
}

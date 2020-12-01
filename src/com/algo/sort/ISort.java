package com.algo.sort;

/**
 * @author admin
 */
public interface ISort {
    /**
     * 分析排序性能
     */
    void summary();

    /**
     * 选择排序
     * @param arr 数组
     */
     void selectionSort(int [] arr);

    /**
     * 冒泡排序
     * @param arr 数组
     */
    void bubbleSort(int [] arr);

    /**
     * 快速排序
     * 时间复杂度 ↑ O(n^2) ↓ O(nlogn) - O(nlogn)
     * 空间复杂度 ↑ O(n) ↓ O(logn)
     * @param arr 数组
     */
    void quickSort(int [] arr);

    /**
     * 桶排序
     * 时间复杂度 - O(n)
     * 空间复杂度 - O(n)
     * @param arr 数组
     */
    void bucketSort(int [] arr);

    /**
     * 插入排序
     * 时间复杂度 ↑ O(n^2) ↓ O(n) - O(n^2)
     * 空间复杂度 - O(1)
     * @param arr 数组
     */
    void insertionSort(int [] arr);


    /**
     * 基数排序
     * 时间复杂度 - O(d(n + r))
     * 空间复杂度 - O(n + r)
     * @param arr 数组
     */
    void radixSort(int [] arr);

    /**
     * 归并排序
     * 时间复杂度 - O(nlogn)
     * 空间复杂度 - O(n)
     * @param arr 数组
     */
    void mergeSort(int [] arr);

}

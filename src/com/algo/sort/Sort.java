package com.algo.sort;

import java.util.*;

/**
 * @author admin
 */
public class Sort implements ISort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] raw = random.ints(1, 200).limit(100000).toArray();
        int[] arr1 = raw.clone();
        int[] arr2 = raw.clone();
        int[] arr3 = raw.clone();
        int[] arr4 = raw.clone();
        int[] arr5 = raw.clone();
        int[] arr6 = raw.clone();
        ISort proxy = (ISort) new SortSupport(new Sort()).getPrinter();
        proxy.bubbleSort(arr1);
        proxy.selectionSort(arr2);
        proxy.quickSort(arr3);
        proxy.bucketSort(arr4);
        proxy.insertionSort(arr5);
        proxy.radixSort(arr6);
    }

    @Override
    public void summary() {

    }

    @Override
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    @Override
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    @Override
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    @Override
    public void bucketSort(int[] arr) {
        //最大值，最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int value : arr) {
            max = Math.max(value, max);
            min = Math.min(value, min);
        }
        //核心：1.桶数量 2.桶区间
        //左闭右开，最大值需要多一个桶
        int d = Math.max(1, (max - min) / (arr.length - 1));
        int nums = (max - min) / d + 1;
        //初始化容器
        List<List<Integer>> buckets =  new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            buckets.add(new ArrayList<>());
        }
        //向桶里放值
        for (int value : arr) {
            //建立元素和桶的映射
            int index = (value - min) / d;
            buckets.get(index).add(value);
        }
        //排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }
        //数组重排
        int index = 0;
        for (List<Integer> bucket:
             buckets) {
            for (Integer num : bucket) {
                arr[index++] = num;
            }
        }
    }


    public void insertionSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j - 1]){
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    @Override
    public void insertionSort(int [] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j-1] > temp; j--) {
                    arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    @Override
    public void radixSort(int[] arr) {
        if(arr == null || arr.length == 0){
            return;
        }
        //获取数组最大值
        int max = Arrays.stream(arr).max().getAsInt();
        // 1 10 100
        int dev = 1;
        //初始化槽位
        int [] bucket = new int[arr.length];
        while(max > dev){
            int [] count = new int[10];
            //计算各位数量 (num / digit) % 10
            for (int value : arr) {
                int digit = (value / dev) % 10;
                count[digit]++;
            }
            //固定对应位数所在的位置, 表示在数组的第几个，减一为真实索引
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            //赋值
            for (int j = arr.length - 1; j >= 0; j--) {
                int digit = (arr[j] / dev) % 10;
                bucket[count[digit] - 1] = arr[j];
                //达到不同位数值得后边界时，digit更新，放到对应位数的区间
                count[digit]--;
            }
            System.arraycopy(bucket, 0, arr, 0, arr.length);
            dev *= 10;
        }
    }


    private void quickSort(int[] arr, int low, int high) {
        int i = low, j = high;
        if (i < j) {
            int k = arr[i];
            while (i != j) {
                while (i < j && arr[j] >= k) {
                    //[←] <6
                    j--;
                }
                while (i < j && arr[i] <= k) {
                    //[→] >6
                    i++;
                }
                swap(arr, i, j);
            }
            arr[low] = arr[i];
            arr[i] = k;
            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);
        }

    }

    private void quickSort2(int [] arr, int left, int right){
        int i = left, j = right;
        if(i < j){
            int k = arr[i];
            while(i != j){
                while(i < j && arr[j] >= k){
                    j--;
                }
                arr[i] = arr[j];
                while(i < j && arr[i] <= k){
                    i++;
                }
                arr[j] = arr[i];
            }
            arr[i] = k;
            quickSort2(arr, left, i + 1);
            quickSort2(arr, i + 1, right);
        }
    }



    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

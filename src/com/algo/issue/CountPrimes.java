package com.algo.issue;

/**
 * @author chowsanity
 * @date 2020年12月3日
 * @title 204. 计数质数
 */
public class CountPrimes {
    /**
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * 示例 1：
     *
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     *
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     *
     * 输入：n = 1
     * 输出：0
     *  
     * 提示：
     *
     * 0 <= n <= 5 * 106
     *
     */
    public static void main(String[] args) {
        int n = 10;
        System.out.println(countPrimes(n));
        System.out.println(countPrimes2(n));
    }

    /**
     * 暴力解法
     * 大于2的偶数不是质数
     * 如果n不能被[2,√n]中的数整除，则n为质数
     */
    public static int countPrimes(int n) {
        int count =  1;
        if(n < 3){
           return 0;
        }
        for (int i = 3; i < n; i++) {
            if((i & 1) == 0){
                continue;
            }
            boolean flag = true;
            for (int j = 3; j * j <= i; j+=2) {
                if(j % i == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                count++;
            }
        }
        return count;
    }

    /**
     * 埃氏筛 厄拉多塞筛法
     *
     */
    public static int countPrimes2(int n) {
        boolean [] primes =  new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if(!primes[i]){
                count++;
            }
            if((long) i * i < n){
                for (int j = i * i; j < n; j+=i) {
                    primes[j] = true;
                }
            }
        }
       return count;
    }
}

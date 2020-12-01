package com.algo.issue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2020年11月27日
 * @title 454. 四数相加 II
 */
public class FourSumCount {
    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * <p>
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
     * <p>
     * 例如:
     * <p>
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     * <p>
     * 输出:
     * 2
     * <p>
     * 解释:
     * 两个元组如下:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */
    public static void main(String[] args) {
        int[] a = {1, 2};
        int[] b = {-2, -1};
        int[] c = {-1, 2};
        int[] d = {0, 2};
        System.out.println(fourSumCount(a, b, c, d));
    }

    /**
     * 分组 + 哈希
     * 用hashmap存放ab两数之和和出现次数
     * 用cd两数之和求四数之和为零的组合次数
     */
    public static int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
        Map<Integer, Integer> map = new HashMap<>(32);
        int ans = 0;
        for (int value1 : a) {
            for (int value2 : b) {
                Integer count = map.getOrDefault(value1 + value2, 0);
                map.put(value1 + value2, ++count);
            }
        }
        for (int value3 : c) {
            for (int value4 : d) {
                if (map.containsKey(-value3 - value4)) {
                    ans += map.get(-value3 - value4);
                }
            }
        }

        return ans;


    }
}

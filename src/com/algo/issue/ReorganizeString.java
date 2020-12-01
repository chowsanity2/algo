package com.algo.issue;

import java.util.*;

/**
 * @author admin
 * @date 2020年11月30日
 * @title 767. 重构字符串
 */
public class ReorganizeString {
    /**
     * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     * <p>
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: S = "aab"
     * 输出: "aba"
     * 示例 2:
     * <p>
     * 输入: S = "aaab"
     * 输出: ""
     * 注意:
     * <p>
     * S 只包含小写字母并且长度在[1, 500]区间内
     */
    public static void main(String[] args) {
        String s = "vvvlo";
        System.out.println(reorganizeString(s));
    }

    public static String reorganizeString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int l = 2;
        //计数
        int[] count = new int[26];
        for (char c :
                chars) {
            count[c - 'a']++;
        }
        //当字符串中字符数量大于(n+1)/2时，相邻字符必存在相同的情况
        int maxIndex = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > count[maxIndex]) {
                maxIndex = i;
            }
        }

        if (count[maxIndex] > (n + 1) / l) {
            return "";
        }
        //当字符最大数量为(n + 1)/2时，必然在偶数位置，所以先填充数量最多的字符在偶数位置
        int index = 0;
        while (count[maxIndex]-- > 0) {
            chars[index] = (char) ('a' + maxIndex);
            index += 2;
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                if (index >= n) {
                    index = 1;
                }
                chars[index] = (char) ('a' + i);
                index += 2;
            }
        }
        return String.valueOf(chars);
    }

}

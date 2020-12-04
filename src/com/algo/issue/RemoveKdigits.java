package com.algo.issue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author chowsanity
 * @date 2020年12月2日
 * @title 402. 移掉K位数字
 */
public class RemoveKdigits {
    /**
     * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
     * <p>
     * 注意:
     * <p>
     * num 的长度小于 10002 且 ≥ k。
     * num 不会包含任何前导零。
     * 示例 1 :
     * <p>
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
     * 示例 2 :
     * <p>
     * 输入: num = "10200", k = 1
     * 输出: "200"
     * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     * 示例 3 :
     * <p>
     * 输入: num = "10", k = 2
     * 输出: "0"
     * 解释: 从原数字移除所有的数字，剩余为空就是0。
     */
    public static void main(String[] args) {
        String num1 = "1432219";
        int k1 = 3;
        String num2 = "10200";
        int k2 = 1;
        String num3 = "10";
        int k3 = 2;
        String num4 = "112";
        int k4 = 1;
        System.out.println(removeKdigits(num1, k1));
        System.out.println(removeKdigits(num2, k2));
        System.out.println(removeKdigits(num3, k3));
        System.out.println(removeKdigits(num4, k4));

    }

    /**
     * 比较前一个相邻元素，小于前一个元素则丢弃
     */
    public static String removeKdigits(String num, int k) {
        //丢弃k等价保留n - k
        int n = num.length();
        int remain = n - k;
        //保留相对位置
        char[] chars = num.toCharArray();
        Deque<Character> q = new LinkedList<>();
        for (char c : chars) {
            while (k > 0 && !q.isEmpty() && q.peek() > c) {
                q.pop();
                k--;
            }
            q.push(c);
        }
        char[] buf = new char[remain];
        int count = 0;
        while (!q.isEmpty() && count < remain) {
            buf[count++] = q.removeLast();
        }
        String ans = new String(buf).replaceAll("^(0+)", "");
        return "".equals(ans) ? "0" : ans;
    }
}

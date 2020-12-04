package com.algo.issue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author chowsanity
 * @date 2020年12月2日
 * @title 316. 去除重复字母
 */
public class RemoveDuplicateLetters {
    /**
     *
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     *
     * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
     *
     * 示例 1：
     *
     * 输入：s = "bcabc"
     * 输出："abc"
     * 示例 2：
     *
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 104
     * s 由小写英文字母组成
     *
     */
    public static void main(String[] args) {
        String s1 = "bcabc";
        String s2 = "cbacdcbc";
        System.out.println(removeDuplicateLetters(s1));
        System.out.println(removeDuplicateLetters(s2));
    }


    public static String removeDuplicateLetters(String s) {
        //计数
        //判断相邻元素 n < stack.pop() 弹出
        //每个字符至少保留一个，弹出时需判断外部容器中是否还存在该字符
        char[] chars = s.toCharArray();
        int[] counts = new int[26];
        char start = 'a';
        Deque<Character> deck = new LinkedList<>();
        for (char c : chars) {
            counts[c - 'a']++;
        }
        for (char c : chars) {
            if(!deck.contains(c)){
                while (!deck.isEmpty() && counts[deck.peek() - start] > 0 && c < deck.peek()) {
                    deck.pop();
                }
                deck.push(c);
            }
            counts[c - 'a']--;
        }
        char[] buf = new char[deck.size()];
        int count = 0;
        while(!deck.isEmpty()){
            buf[count] = deck.removeLast();
            count++;
        }
        return new String(buf);
    }
}

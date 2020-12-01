package com.algo.issue;

import com.algo.structure.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chowsanity
 * @date 2020年11月20日
 * @title 147. 对链表进行插入排序
 */
public class InsertionSortList {
    /**
     * 对链表进行插入排序。
     * <p>
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     * <p>
     * 插入排序算法：
     * <p>
     * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 重复直到所有输入数据插入完为止。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2：
     * <p>
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     */
    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode listNode = insertionSortList(node1);
        List<Integer> ans = new ArrayList<>();
        while (listNode != null){
            ans.add(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(Arrays.toString(ans.toArray()));
    }


    /**
     *  1. 创建哑节点（虚拟头节点）
     *  2. 从第二个元素开始扫待插入元素（curr指针）
     *  3. 如果大于排序数列的最大值，则扫描排序节点并插入（初始化prev指针）
     *  4. 有序数列节点后移一位（lastSorted指针）
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head;
        ListNode curr = head.next;
        while(curr != null){
            if(curr.val >= lastSorted.val){
                lastSorted = lastSorted.next;
            }else{
                ListNode prev = dummyHead;
                while(prev.next.val <= curr.val){
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }


}

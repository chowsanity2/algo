package com.algo.issue;

import com.algo.structure.ListNode;
import org.w3c.dom.NodeList;

/**
 * @author chowsanity
 * @date 2020年10月22日
 * @title 判断给定的链表中是否有环
 */
public class HasCycle {
    public static void main(String[] args) {
        ListNode cycle1 = new ListNode(1);
        ListNode cycle2 = new ListNode(2);
        ListNode cycle3 = new ListNode(3);
        cycle1.next = cycle2;
        cycle2.next = cycle3;
        cycle3.next = cycle1;
        ListNode noCycle1 = new ListNode(1);
        ListNode noCycle2 = new ListNode(2);
        ListNode noCycle3 = new ListNode(3);
        noCycle1.next = noCycle2;
        noCycle2.next = noCycle3;
        display(cycle1, 2);
        display(noCycle1, 2);
        display(cycle1, 1);
        display(noCycle1, 1);

    }

    /**
     * 思路
     * 1. 遍历链表的同时，让前一个节点的next指向head（或者是任意一个指定的内存），
     * 2. 在后续的遍历中，如果有节点的当前next指向了head，则说明有环。
     * 3. 破坏链表，达到最快
     */
    public static boolean hasCycle1(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (next == head) {
                return true;
            }
            //破坏链表
            cur.next = head;
            //后移一位
            cur = next;
        }

        return false;
    }

    /**
     * 思路 快慢指针 若快指针为空，则无环，相遇为结束条件
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static void display(ListNode head, int solution) {
        long start = System.currentTimeMillis();
        System.out.println("是否有环: " + (solution == 1 ? hasCycle1(head) : hasCycle2(head)));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }

}

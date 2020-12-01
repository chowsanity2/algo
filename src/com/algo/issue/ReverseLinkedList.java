package com.algo.issue;

import com.algo.structure.ListNode;
import org.w3c.dom.NodeList;

/**
 * @author chowsanity
 * @date 2020年10月22日
 * @title 输入一个链表，反转链表后，输出新链表的表头
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        System.out.println("反转前：");
        display(node1);
        System.out.println("反转后：");
        display(reverseList(node1));
    }

    /**
        思路 变量为引用数据类型，值为引用类型的地址
        要从 node1 -> node2 -> node3 -> node4 变为 node1 <- node2 <- node3 <- node4
        需要修改 node.next 指向前一节点
        因为改动会使链表断裂 node1 <- node2 node3 -> node4
        所以必须保存下一节点的信息
        循环依次后移一位, head = next
        当head为空结束
     */
    public static ListNode reverseList(ListNode head){
        if(head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode next;
        while(head != null){
            next = head.next;
            head.next = pre;
            //后移一位
            pre = head;
            head = next;
        }
        //返回头节点
        return pre;
    }

    public static void display(ListNode node){
        StringBuilder sb = new StringBuilder("链表打印：");
        while (node != null){
            sb.append("node").append(node.val);
            node = node.next;
            if(node != null){
                sb.append(" -> ");
            }
        }
        System.out.println(sb);
    }
}

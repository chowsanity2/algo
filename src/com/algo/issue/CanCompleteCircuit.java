package com.algo.issue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chowsanity
 * @date 2020年11月18日
 * @title 134. 加油站
 */
public class CanCompleteCircuit {
    /**
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * <p>
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * <p>
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * <p>
     * 说明: 
     * <p>
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     * 示例 1:
     * <p>
     * 输入:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     * <p>
     * 输出: 3
     * <p>
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     * 示例 2:
     * <p>
     * 输入:
     * gas  = [2,3,4]
     * cost = [3,4,3]
     * <p>
     * 输出: -1
     * <p>
     * 解释:
     * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
     * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
     * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
     * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
     * 因此，无论怎样，你都不可能绕环路行驶一周。
     */
    public static void main(String[] args) {
        int [] gas = new int[]{2,3,4};
        int [] cost = new int[]{3,4,3};
        int[] gas1 = new int[]{4, 5, 2, 6, 5, 3};
        int[] cost1 = new int[]{3, 2, 7, 3, 2, 9};
        int[] gas2 = new int[]{1,2,3,4,5};
        int[] cost2 = new int[]{3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
        System.out.println(canCompleteCircuit(gas1, cost1));
        System.out.println(canCompleteCircuit(gas2, cost2));
    }

    /**
     * 双向链表
     *
     * @return 不能行驶一周返回 -1，否则返回起始站点编号
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length < cost.length) {
            return -1;
        }
        //头节点
        LinkedList<Node> raw = new LinkedList<>();

        //构造有环链表
        for (int i = 0; i < gas.length; i++) {
            raw.add(new Node(gas[i], cost[i]));
        }
        for (int i = 0; i < raw.size() - 1; i++) {
            raw.get(i).next = raw.get(i + 1);
        }
        raw.get(raw.size() - 1).next = raw.get(0);
        int ans = -1;
        //选择起点
        start:
        for (int i = 0; i < raw.size(); i++) {
            int sumGas = 0;
            //初始化油量
            Node head = raw.get(i);
            Node node = head;
            while (node.next != head) {
                sumGas = sumGas + node.gas - node.cost;
                node = node.next;
                if (sumGas < 0) {
                    continue start;
                }
            }
            //回到起点
            sumGas = sumGas + node.gas - node.cost;
            ans = sumGas >= 0 ? i : -1;
            break;
        }
        return ans;
    }


    public static class Node {
        int gas;
        int cost;
        Node next;

        public Node(int gas, int cost) {
            this.gas = gas;
            this.cost = cost;
        }
    }


    /**
     * 大神题解
     * 一次遍历
     * 如果某点ans能一直走到数组结尾，保留从数组结尾到数组第一个节点时剩余汽油数cur。
     * 并保留从数组第一个点走到该点时剩余汽油数res。
     * 比较两个数的大小，如果cur + res >= 0，说明可以从数组第一个节点走到ans。即可以绕环路行驶一周
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        int cur = 0, res = 0, ans = 0;

        for (int i = 0; i < n; i++) {
            cur += gas[i] - cost[i];
            if (cur < 0) {
                ans = i + 1;
                res += cur;
                cur = 0;
            }
        }
        return cur + res >= 0 ? ans : -1;
    }


}


package com.algo.issue;

import com.algo.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author chowsnaity
 * @date 2020年10月29日
 * @title 129. 求根到叶子节点数字之和
 */
public class SumNumbers {
    /**
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     * 说明: 叶子节点是指没有子节点的节点。
     * 输入: [4,9,0,5,1]
     *   4
     *  / \
     * 9   0
     *   / \
     *   5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(0);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(1);
        root.left = treeNode1;
        root.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        while (true) {
            String in = scanner.next();
            switch (in) {
                case "dfs":
                    sum = dfs(root, sum);
                    break;
                case "bfs":
                    sum = bfs(root);
                    break;
                default:
                    return;
            }
            System.out.println("路径之和：" + sum);
        }
    }

    /**
     * depth first search
     */
    private static int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }

    /**
     * breadth first search
     */
    private static int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        queue.offer(root);
        numQueue.offer(root.val);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Integer num = numQueue.poll();
            if (node != null) {
                if (node.left == null && node.right == null) {
                    if (num != null) {
                        sum += num;
                    }
                }else{
                    if (node.left != null && num != null) {
                        queue.offer(node.left);
                        numQueue.offer(num * 10 + node.left.val);
                    }
                    if (node.right != null && num != null) {
                        queue.offer(node.right);
                        numQueue.offer(num * 10 + node.right.val);
                    }
                }
            }
        }
        return sum;
    }
}

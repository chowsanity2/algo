package com.algo.issue;

import com.algo.structure.TreeNode;

import java.util.*;

/**
 * @author chowsanity
 * @date 2020年10月27日
 * @title 遍历树
 */
public class TraversalTreeNode {
    /**
     *              5
     *         /        \
     *        3          8
     *         \        / \
     *          4      7   9
     *                /
     *               6
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        TreeNode treeNode2 = new TreeNode();
        TreeNode treeNode3 = new TreeNode();
        TreeNode treeNode4 = new TreeNode();
        TreeNode treeNode5 = new TreeNode();
        TreeNode treeNode6 = new TreeNode();
        TreeNode treeNode7 = new TreeNode();
        root.val = 5;
        treeNode2.val = 3;
        treeNode3.val = 8;
        treeNode4.val = 4;
        treeNode5.val = 7;
        treeNode6.val = 9;
        treeNode7.val = 6;
        root.left = treeNode2;
        root.right = treeNode3;
        treeNode2.right = treeNode4;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;
        treeNode5.left = treeNode7;
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (i != -1){
            i = scanner.nextInt();
            traversalTreeNode(root, i);
        }
    }

    public static void traversalTreeNode(TreeNode root, int solution){
        if(root == null){
            return;
        }
        ArrayList<Integer> res = new ArrayList<>();
        switch (solution){
            case 1:
                System.out.println("前序遍历：");
                traversal1(root, res);
                break;
            case 2:
                System.out.println("前序遍历：");
                traversal2(root, res);
                break;
            case 3:
                System.out.println("中序遍历：");
                traversal3(root, res);
                break;
            case 4:
                System.out.println("后序遍历：");
                traversal4(root, res);
                break;
            case 5:
                System.out.println("层序遍历：");
                traversal5(root, res);
                break;
            case 11:
                System.out.println("前序遍历-递归：");
                recursion1(root, res);
                break;
            case 12:
                System.out.println("中序遍历-递归：");
                recursion2(root, res);
                break;
            case 13:
                System.out.println("后序遍历-递归：");
                recursion3(root, res);
                break;
            case 14:
                System.out.println("前序遍历变种：");
                dlr(root, res);
                break;
            case 15:
                System.out.println("后续遍历变种：");
                lrd(root, res);
                break;
            default:
        }
        System.out.println(res);
    }



    /**
     * 前序遍历
     * 先序遍历可以想象成，小人从树根开始绕着整棵树的外围转一圈，经过结点的顺序就是先序遍历的顺序
     * 深度优先搜索
     */
    private static void traversal1(TreeNode root, ArrayList<Integer> res) {
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }

    /**
     * 前序遍历
     * 先序遍历可以想象成，小人从树根开始绕着整棵树的外围转一圈，经过结点的顺序就是先序遍历的顺序
     */
    private static void traversal2(TreeNode root, ArrayList<Integer> res) {
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                stack.push(node);
                res.add(node.val);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }

    /**
     * 中序遍历
     * 中序遍历可以想象成，按树画好的左右位置投影下来就可以了
     */
    private static void traversal3(TreeNode root, ArrayList<Integer> res) {
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
    }

    /**
     * 后序遍历
     * 后序遍历就像是剪葡萄，我们要把一串葡萄剪成一颗一颗的
     */
    private static void traversal4(TreeNode root, ArrayList<Integer> res) {
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode prev = null;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(node.right == null || node.right == prev){
                res.add(node.val);
                prev = node;
                node = null;
            }else{
                stack.push(node);
                node = node.right;
            }
        }
    }

    /**
     * 层序遍历
     * 队列
     */
    private static void traversal5(TreeNode root, ArrayList<Integer> res) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if(node != null){
                    res.add(node.val);
                    if(node.left != null){
                        queue.offer(node.left);
                    }
                    if(node.right != null){
                        queue.offer(node.right);
                    }
                }
            }
        }

    }

    /**
     * 递归
     * 前序遍历
     */
    public static void recursion1(TreeNode root , List<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.val);
        recursion1(root.left, res);
        recursion1(root.right, res);
    }

    /**
     * 递归
     * 中序遍历
     */
    private static void recursion2(TreeNode root, ArrayList<Integer> res) {
        if(root == null){
            return;
        }
        recursion2(root.left, res);
        res.add(root.val);
        recursion2(root.right, res);
    }

    /**
     * 递归
     * 后序遍历
     */
    private static void recursion3(TreeNode root, ArrayList<Integer> res) {
        if(root == null){
            return;
        }
        recursion3(root.left, res);
        recursion3(root.right, res);
        res.add(root.val);
    }

    /**
     * dlr 前序遍历变种
     */
    private static void dlr(TreeNode root, List<Integer> ans){
        if(root == null){
            return;
        }
        Deque<TreeNode> stack  = new LinkedList<>();
        TreeNode node = root;
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            ans.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }

    }

    /**
     * lrd 后续遍历变种
     */
    private static void lrd(TreeNode root, List<Integer> ans){
        if(root == null){
            return;
        }
        Deque<TreeNode> stack  = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();
        TreeNode node = root;
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            result.addFirst(node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        ans.addAll(result);
    }




}

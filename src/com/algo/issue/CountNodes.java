package com.algo.issue;

import com.algo.structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2020年11月24日
 * @title 222. 完全二叉树的节点个数
 */
public class CountNodes {
    /**
     * 给出一个完全二叉树，求出该树的节点个数。
     *
     * 说明：
     *
     * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     *
     * 示例:
     *
     * 输入:
     *     1
     *    / \
     *   2   3
     *  / \  /
     * 4  5 6
     *
     * 输出: 6
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        System.out.println(countNodes2(root));
    }

    /**
     * dfs递归
     */
    public static int countNodes1(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1 + countNodes1(root.left) + countNodes1(root.right);
    }

    /**
     * 二分查找 + 位运算
     */
    public static int countNodes2(TreeNode root) {
        //完全二叉树，最底层节点个数一定在 [2^h,2^{h+1}-1]的范围内
        if(root == null){
            return 0;
        }
        //计算层数
        TreeNode node = root;
        int level = 0;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;



    }


    /**
     *     1            h = 0
     *    / \
     *   2   3          h = 1
     *  / \  /
     * 4  5 6           h = 2
     * 现在这个树中的值都是节点的编号，最底下的一层的编号是[2^h ，2^h - 1]，现在h = 2，也就是4, 5, 6, 7。
     * 4, 5, 6, 7对应二进制分别为 100 101 110 111 不看最左边的1，从第二位开始，0表示向左，1表示向右，正好可以表示这个节点相对于根节点的位置。
     * 比如4的 00 就表示从根节点 向左 再向左。6的 10 就表示从根节点 向右 再向左
     *
     * 那么想访问最后一层的节点就可以从节点的编号的二进制入手。从第二位开始的二进制位表示了最后一层的节点相对于根节点的位置。
     * 那么就需要一个bits = 2^(h - 1) 这里就是2，对应二进制为010。这样就可以从第二位开始判断。
     * 比如看5这个节点存不存在，先通过位运算找到编号为5的节点相对于根节点的位置。010 & 101 发现第二位是0，说明从根节点开始，第一步向左走。
     * 之后将bit右移一位，变成001。001 & 101 发现第三位是1，那么第二步向右走。
     * 最后bit为0，说明已经找到编号为5的这个节点相对于根节点的位置，看这个节点是不是空，不是说明存在，exist返回真
     * 编号为5的节点存在，说明总节点数量一定大于等于5。所以二分那里low = mid
     *
     * 再比如看7存不存在，010 & 111 第二位为1，第一部从根节点向右；001 & 111 第三位也为1，第二步继续向右。
     * 然后判断当前节点是不是null，发现是null，exist返回假。
     * 编号为7的节点不存在，说明总节点数量一定小于7。所以high = mid - 1
     */
    private static boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }







}

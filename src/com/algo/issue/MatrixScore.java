package com.algo.issue;

/**
 * @author chowsanity
 * @date 2020年12月7日
 * @title 861. 翻转矩阵后的得分
 */
public class MatrixScore {
    /**
     *
     * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
     *
     * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     *
     * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
     *
     * 返回尽可能高的分数。
     *
     * 示例：
     *
     * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
     * 输出：39
     * 解释：
     * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
     * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     *
     * 提示：
     *
     * 1 <= A.length <= 20
     * 1 <= A[0].length <= 20
     * A[i][j] 是 0 或 1
     */
    public static void main(String[] args) {
        int [][] a = new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println(matrixScore(a));
    }

    /**
     * 第一列行翻转取一
     * 其余列列翻转取最多一的情况
     * 求和
     * 进阶 => 考虑用位运算和异或 因为首列肯定为1 异或总和总行数取最大值
     * 位运算 从低位到高位 ans <<= 1 从高位到低位 ans = (1 << (n - 1))  (0 <= n < col)
     */
    public static int matrixScore(int[][] a) {
        int row = a.length;
        int col = a[0].length;
        for (int i = 0; i < row; i++) {
            if(a[i][0] == 0){
                for (int j = 0; j < col; j++) {
                    if(a[i][j] == 0){
                        a[i][j] = 1;
                    }else{
                        a[i][j] = 0;
                    }
                }
            }
        }
        for (int i = 1; i < col; i++) {
            int mid = (row + 1) / 2;
            int count = 0;
            for (int[] ints : a) {
                if (ints[i] == 1) {
                    count += 1;
                }
            }
            if(count < mid){
                for (int j = 0; j < row; j++) {
                    if(a[j][i] == 0){
                        a[j][i] = 1;
                    }else{
                        a[j][i] = 0;
                    }
                }
            }
        }
        int sum = 0;
        //求和
        for (int[] ints : a) {
            StringBuilder binary = new StringBuilder();
            for (int j = 0; j < col; j++) {
                binary.append(ints[j]);
            }
            sum += Integer.valueOf(binary.toString(), 2);
        }
        return sum;
    }
}

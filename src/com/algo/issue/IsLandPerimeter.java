package com.algo.issue;

import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author chowsanity
 * @date 2020年10月30日
 * @title 463. 岛屿的周长
 */
public class IsLandPerimeter {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    /**
     * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
     * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     * 输入:
     * [[0,1,0,0],
     * [1,1,1,0],
     * [0,1,0,0],
     * [1,1,0,0]]
     * <p>
     * 输出: 16
     */
    public static void main(String[] args) {
        int [][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        isLandPerimeter(grid);
    }

    public static void isLandPerimeter(int[][] grid) {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        while (true) {
            String solution = scanner.next();
            switch (solution) {
                case "dfs":
                    int rowLen = grid.length;
                    int colLen = grid[0].length;
                    //上下左右慢动作，标记，避免死循环
                    for (int r = 0; r < rowLen; r++) {
                        for (int c = 0; c < colLen; c++) {
                            if (grid[r][c] == 1) {
                                result += dfs(r, c, grid, rowLen, colLen);
                            }
                        }
                    }
                    break;
                case "ite":
                    result = iteration(grid);
                    break;
                default:
                    return;
            }
            System.out.println("周长为：" + result);
        }
    }

    /**
     * @param r    row
     * @param c    col
     * @param grid 二维数组
     * @param n    row边界
     * @param m    col边界
     */
    public static int dfs(int r, int c, int[][] grid, int n, int m) {
        if (r < 0 || r >= n || c < 0 || c >= m || grid[r][c] == 0) {
            return 1;
        }

        if (grid[r][c] != 1) {
            return 0;
        }

        grid[r][c] = 2;
        int res = 0;
        int boundary = 4;
        for (int i = 0; i < boundary; ++i) {
            int tx = r + dx[i];
            int ty = c + dy[i];
            res += dfs(tx, ty, grid, n, m);
        }
        return res;
    }

    public static int iteration(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int boundary = 4;
        int result = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if(grid[r][c] == 1){
                    int count = 0;
                    for (int k = 0; k < boundary; ++k) {
                        int tx = c + dx[k];
                        int ty = r + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            count += 1;
                        }
                    }
                    result += count;
                }
            }
        }
        return result;
    }


}

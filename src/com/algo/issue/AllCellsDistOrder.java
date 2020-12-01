package com.algo.issue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chowsanity
 * @date 2020年11月17日
 * @title 1030. 距离顺序排列矩阵单元格
 */
public class AllCellsDistOrder {
    /**
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     * <p>
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     * <p>
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     * <p>
     * 示例 1：
     * <p>
     * 输入：R = 1, C = 2, r0 = 0, c0 = 0
     * 输出：[[0,0],[0,1]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
     * 示例 2：
     * <p>
     * 输入：R = 2, C = 2, r0 = 0, c0 = 1
     * 输出：[[0,1],[0,0],[1,1],[1,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
     * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
     * 示例 3：
     * <p>
     * 输入：R = 2, C = 3, r0 = 1, c0 = 2
     * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
     * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     *  
     * 提示：
     * <p>
     * 1 <= R <= 100
     * 1 <= C <= 100
     * 0 <= r0 < R
     * 0 <= c0 < C
     */
    public static void main(String[] args) {
        int rl = 2, rc = 3, r0 = 1, c0 = 2;
        System.out.println(Arrays.deepToString(allCellsDistOrder(rl, rc, r0, c0)));
    }

    /**
     * 思路 1.遍历计算所有坐标距离并赋值 2.排序输出
     *
     * @param rl 行长度 0 <= r < rl
     * @param cl 列长度 0 <= c < cl
     * @param r0 原点 x 坐标
     * @param c0 原点 y 坐标
     * @return 所有单元格的坐标， 并按到 (r0, c0) 的距离从最小到最大的顺序排 |r1 - r2| + |c1 - c2|
     */
    public static int[][] allCellsDistOrder(int rl, int cl, int r0, int c0) {
        TreeMap<Integer, List<int[]>> raw = new TreeMap<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                int dist = Math.abs(i - r0) + Math.abs(j - c0);
                List<int[]> ints;
                if (raw.containsKey(dist)) {
                    ints = raw.get(dist);
                } else {
                    ints = new ArrayList<>();
                }
                ints.add(new int[]{i, j});
                raw.put(dist, ints);
            }
        }
        List<int[]> reduce = raw.values().stream()
                .reduce(new ArrayList<>(), (all, item) -> {
                    all.addAll(item);
                    return all;
                });
        int[][] ans = new int[reduce.size()][2];
        for (int i = 0; i < reduce.size(); i++) {
            ans[i] = reduce.get(i);
        }
        return ans;
    }

    /**
     * 官方题解
     */
    public int[][] allCellsDistOrder1(int rl, int cl, int r0, int c0) {
        int[][] ret = new int[rl * cl][];
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                ret[i * cl + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, Comparator.comparingInt(a -> (Math.abs(a[0] - r0) + Math.abs(a[1] - c0))));
        return ret;
    }


}

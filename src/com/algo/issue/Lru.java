package com.algo.issue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chowsanity
 * @date 2020年10月27日
 * @title 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 */
public class Lru {
    private static final Node NODE_HEAD = new Node(-1, -1);
    private static final Node NODE_TAIL = new Node(-1, -1);
    private static final Map<Integer, Node> NODE_CONTAINER = new HashMap<>();
    private static int len;

    /**
     * 样例 [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
     * 第一个数字 1 代表 set方法， 2 代表 get方法
     * 第二个第三个 代表 key value
     * 最后一个数字是 k 代表长度 只能容纳多少个key value。
     * 输入的是 每次get的值。 如果找不到就是-1；
     */
    public static void main(String[] args) {
        int [][] operators = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        int size = 3;
        System.out.println(Arrays.toString(lru(operators, size)));
    }

    public static int [] lru(int[][] operators, int size){
        len = size;
        NODE_HEAD.next = NODE_TAIL;
        NODE_TAIL.prev = NODE_HEAD;
        //get
        int length = (int)Arrays.stream(operators).filter(x -> x[0] == 2).count();
        int [] result = new int[length];
        int count = 0;
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                set(operator[1], operator[2]);
            } else {
                result[count++] = get(operator[1]);
            }
        }
        return result;
    }

    private static void set(int key, int value){
        if(get(key) > -1){
            NODE_CONTAINER.get(key).value = value;
        }else{
            if(NODE_CONTAINER.size() == len){
                int rk = NODE_TAIL.prev.key;
                NODE_TAIL.prev.prev.next = NODE_TAIL;
                NODE_TAIL.prev = NODE_TAIL.prev.prev;
                NODE_CONTAINER.remove(rk);
            }
            Node node = new Node(key, value);
            removeToHead(node);
            NODE_CONTAINER.put(key, node);
        }
    }

    private static int get(int key){
        if(NODE_CONTAINER.containsKey(key)){
            Node node = NODE_CONTAINER.get(key);
            node.next.prev = node.prev;
            node.prev.next = node.next;
            removeToHead(node);
            return node.value;
        }
        return -1;
    }

    private static void removeToHead(Node node){
        node.prev = NODE_HEAD;
        node.next = NODE_HEAD.next;
        NODE_HEAD.next.prev = node;
        NODE_HEAD.next = node;
    }

    private static class Node{
        int key, value;
        Node prev, next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}

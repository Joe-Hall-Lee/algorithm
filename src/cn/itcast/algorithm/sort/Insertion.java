package cn.itcast.algorithm.sort;

public class Insertion {

    /*
     * 对数组 a 中的元素进行排序
     * */
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                // 比较索引 j 处的值和索引 j - 1 处的值，如果索引 j - 1 处的值比索引 j 处的值大，则交换数据，如果不大，那么就找到合适的位置了，退出循环即可；
                if (greater(a[j - 1], a[j])) {
                    exch(a, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /*
     * 比较 v 元素是否大于 w 元素
     * */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /*
     * 数组元素 i 和 j 交换位置
     * */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
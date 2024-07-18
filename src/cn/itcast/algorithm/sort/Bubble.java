package cn.itcast.algorithm.sort;

public class Bubble {

    /*
     * 对数组 a 中的元素进行排序
     * */
    public static void sort(Comparable[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                // {6, 5, 4, 3, 2, 1}
                // 比较索引 j 和索引 j + 1 处的值
                if (greater(a[j], a[j + 1])) {
                    exch(a, j, j + 1);
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
package cn.itcast.algorithm.sort;

public class Merge {
    // 归并所需要的辅助数组
    private static Comparable[] assist;

    /*
     * 比较 v 元素是否小于 w 元素
     * */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /*
     * 数组元素 i 和 j 交换位置
     * */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /*
     * 对数组 a 中的元素进行排序
     * */
    public static void sort(Comparable[] a) {
        // 1．初始化辅助数组 assist；
        assist = new Comparable[a.length];
        // 2．定义一个 lo 变量和 hi 变量，分别记录数组中最小的索引和最大的索引；
        int lo = 0;
        int hi = a.length - 1;
        // 3．调用 sort 重载方法完成数组 a 中从索引 lo 到 索引 hi 的元素的排序
        sort(a, lo, hi);
    }

    /*
     * 对数组 a 中从 lo 到 hi 的元素进行排序
     * */
    private static void sort(Comparable[] a, int lo, int hi) {
        // 做安全性校验；
        if (hi <= lo) {
            return;
        }
        // 对 lo 到 hi 之间的数据进行分为两个组
        int mid = lo + (hi - lo) / 2; // 5, 9 mid = 7

        // 分别对每一组数据进行排序
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        // 再把两个组中的数据进行归并
        merge(a, lo, mid, hi);
    }

    /*
     * 对数组中，从 lo 到 mid 为一组，从 mid + 1 到 hi 为一组，对这两组数据进行归并
     * */
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 定义三个指针
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;

        // 遍历，移动 p1 指针和 p2 指针，比较对应索引处的值，找出小的那个，放到辅助数组的对应索引处
        while (p1 <= mid && p2 <= hi) {
            // 比较对应索引处的值
            if (less(a[p1], a[p2])) {
                assist[i++] = a[p1++];
            } else {
                assist[i++] = a[p2++];
            }
        }
        // 遍历，如果 p1 的指针没有走完，那么顺序移动 p1 指针，把对应的元素放到辅助数组的对应索引处
        while (p1 <= mid) {
            assist[i++] = a[p1++];
        }
        // 遍历，如果 p2 的指针没有走完，那么顺序移动 p2 指针，把对应的元素放到辅助数组的对应索引处
        while (p2 <= hi) {
            assist[i++] = a[p2++];
        }
        // 把辅助数组中的元素拷贝到原数组中
        for (int index = lo; index <= hi; index++) {
            a[index] = assist[index];
        }
    }
}
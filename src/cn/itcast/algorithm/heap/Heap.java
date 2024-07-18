package cn.itcast.algorithm.heap;

public class Heap<T extends Comparable<T>> {
    // 存储堆中的元素
    private T[] items;

    // 记录堆中元素的个数
    private int N;

    public Heap(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.N = 0;
    }

    // 判断堆中索引 i 处的元素是否小于索引 j 处的元素
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    // 交换堆中 i 索引和 j 索引处的值
    private void exch(int i, int j) {
        T t = items[i];
        items[i] = items[j];
        items[j] = t;
    }

    // 往堆中插入一个元素
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    // 使用上浮算法，使索引 k 处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        // 通过循环，不断地比较当前结点的值和其父结点的值，如果发现父结点的值比当前结点的值小，则交换位置
        while (k > 1) {
            // 比较当前结点和其父结点
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k = k / 2;

        }
    }

    // 删除堆中最大的元素，并返回这个最大元素
    public T delMax() {
        T max = items[1];

        // 交换索引 1 处的元素和最大索引处的元素，让完全二叉树中最右侧的元素变为临时根结点
        exch(1, N);

        // 最大索引处的元素删除掉
        items[N] = null;
        // 元素个数 - 1
        N--;

        // 通过下沉调整堆，让堆重新有序
        sink(1);
        return max;
    }

    // 使用下沉算法，使索引 k 处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        // 通过循环不断地对比当前 k 结点和其左子结点 2 * k 以及右子结点 2k + 1 处中的较大值的元素大小，如果当前结点小，则需要交换位置
        while (2 * k <= N) {
            // 获取当前结点的子结点中的较大结点
            int max; // 记录当前结点的子结点中的较大结点
            if (2 * k + 1 <= N) {
                // 如果当前结点有右子结点，则需要和右子结点进行比较
                if (less(2 * k, 2 * k + 1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                max = 2 * k;
            }
            // 比较当前结点和较大结点的值
            if (!less(k, max)) {
                break;
            }
            // 交换 k 索引处的值和 max 索引处的值
            exch(k, max);

            // 交换 k 的值
            k = max;
        }
    }
}


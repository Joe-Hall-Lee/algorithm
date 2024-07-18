package cn.itcast.algorithm.priority;

public class IndexMinPriorityQueue<T extends Comparable<T>> {
    // 存储堆中的元素
    private T[] items;
    // 保存每个元素在 items 数组中的索引，pq 数组需要堆有序
    private int[] pq;
    // 保存 pq 的逆序，pq 的值作为索引，pq 的索引作为值
    private int[] qp;
    // 记录堆中的元素的个数
    private int N;

    public IndexMinPriorityQueue(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.pq = new int[capacity + 1];
        this.qp = new int[capacity + 1];
        this.N = 0;

        // 默认情况下，队列中没有存储任何数据，让 qp 中的元素都为 -1
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    // 获取队列中元素的个数
    public int size() {
        return N;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    // 判断堆中索引 i 处的元素是否小于索引 j 处的元素
    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    // 交换堆中 i 索引和 j 索引处的值
    private void exch(int i, int j) {
        // 交换 pq 中的数据
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
        // 更新 qp 中的数据
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    // 判断 k 对应的元素是否存在
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    // 最小元素关联的索引
    public int minIndex() {
        return pq[1];
    }

    // 在队列中插入一个元素，并关联索引 i
    public void insert(int i, T t) {
        // 判断 i 是否已经被关联，如果已经被关联，则不让插入
        if (contains(i)) {
            return;
        }
        // 元素个数 + 1
        N++;
        // 把数据存储到 items 对应的 i 位置处
        items[i] = t;

        // 把 i 存储到 pq 中
        pq[N] = i;

        // 通过 qp 来记录 pq 中的 i
        qp[i] = N;

        // 通过堆上浮完成堆的调整
        swim(N);
    }

    // 删除队列中最小的元素，并返回该元素关联的索引
    public int delMin() {
        // 获取最小元素关联的索引
        int minIndex = pq[1];

        // 交换 pq 中索引 1 处和最大索引处的元素
        exch(1, N);

        // 删除 qp 中对应的内容
        qp[pq[N]] = -1;

        // 删除 pq 最大索引处的内容
        pq[N] = -1;

        // 删除 items 中对应的内容
        items[minIndex] = null;
        // 元素个数 - 1
        N--;

        // 下沉调整
        sink(1);

        return minIndex;
    }

    // 删除索引 i 失联的元素
    public void delete(int i) {
        // 找到 i 在 pq 中的索引
        int k = qp[i];
        // 交换 pq 中索引 k 处的值和索引 N 处的值
        exch(k, N);
        // 删除 qp 中的内容
        qp[pq[N]] = -1;
        // 删除 pq 中的内容
        pq[N] = -1;
        // 删除 items 中的内容
        items[i] = null;
        // 元素的数量 - 1
        N--;
        // 堆的调整
        sink(k);
        swim(k);
    }

    // 把与索引 i 关联的元素修改为 t
    public void changeItem(int i, T t) {
        // 修改 items 数组中 i 位置的元素 t
        items[i] = t;
        // 找到 i 在 pq 中出现的位置
        int k = qp[i];
        // 堆调整
        sink(k);
        swim(k);
    }

    // 使用上浮算法，使索引 k 处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        while (k > 1) {

            if (less(k, k / 2)) {
                exch(k, k / 2);
            }
            k = k / 2;
        }
    }

    // 使用下沉算法，使索引 k 处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        while (2 * k <= N) {
            // 1找到子结点中的较小值
            int min;
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    min = 2 * k;
                } else {
                    min = 2 * k + 1;
                }
            } else {
                min = 2 * k;
            }

            // 比较当前结点和较小值
            if (less(k, min)) {
                break;
            }

            exch(k, min);
            k = min;
        }
    }
}

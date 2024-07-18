package cn.itcast.algorithm.heap;

public class HeapSort {
    // 判断 heap 堆中索引 i 处的元素是否小于索引 j 处的元素
    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    // 交换 heap 堆中索引 i 和 j 处的元素
    private static void exch(Comparable[] heap, int i, int j) {
        Comparable tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // 根据原数组 source，构造出堆 heap
    public static void createHeap(Comparable[] source, Comparable[] heap) {
        // 把 source 中的元素拷贝到 heap 中，heap 中的元素就形成一个无序的堆
        System.arraycopy(source, 0, heap, 1, source.length);

        // 对堆中的元素做下沉调整（从长度的一半处开始，往索引 1 处扫描）
        for (int i = heap.length / 2; i > 0; i--) {
            sink(heap, i, heap.length - 1);
        }
    }

    // 对 source 数组中的数据从小到大排序
    public static void sort(Comparable[] source) {
        // 构建堆
        Comparable[] heap = new Comparable[source.length + 1];
        createHeap(source, heap);
        // 定义一个变量，记录堆中最后一个元素的索引
        int N = heap.length - 1;
        // 通过循环，交换 1 索引处的元素和排序的元素的最大索引处的元素
        while (N > 1) {
            // 交换元素
            exch(heap, 1, N);
            // 排序交换后最大元素所在的索引，让它不要参加堆的下沉调整
            N--;
            // 需要对索引 1 处的元素进行堆的下沉调整
            sink(heap, 1, N);
        }

        // 把 heap 中的数据复制到原数组 source 中
        System.arraycopy(heap, 1, source, 0, source.length);

    }

    // 在 heap 堆中，对 target 处的元素做下沉，范围是 0~range
    private static void sink(Comparable[] heap, int target, int range) {
        while (2 * target <= range) {

            // 1. 找出当前结点的较大的子结点
            int max;
            if (2 * target + 1 <= range) {
                if (less(heap, 2 * target, 2 * target + 1)) {
                    max = 2 * target + 1;
                } else {
                    max = 2 * target;
                }
            } else {
                max = 2 * target;
            }
            // 2，比较当前结点的值和较大子结点的值
            if (!less(heap, target, max)) {
                break;
            }
            exch(heap, target, max);
            target = max;
        }

    }
}

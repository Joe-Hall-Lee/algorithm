package cn.itcast.algorithm.linear;

import java.util.Iterator;

public class SequenceList<T> implements Iterable<T> {
    // 存储元素的数组
    private T[] eles;
    // 记录当前顺序表中的元素个数
    private int N;

    // 构造方法
    public SequenceList(int capacity) {
        // 初始化数组
        this.eles = (T[]) new Object[capacity];
        // 初始化长度
        this.N = 0;
    }

    // 将一个线性表置为空表
    public void clear() {
        this.N = 0;
    }

    // 判断当前线性表是否为空表
    public boolean isEmpty() {
        return N == 0;
    }

    // 获取线性表的长度
    public int length() {
        return N;
    }

    // 获取指定位置的元素
    public T get(int i) {
        return eles[i];
    }

    // 向线性表中添加元素 t
    public void insert(T t) {
        if (N == eles.length) {
            resize(2 * eles.length);
        }
        eles[N++] = t;
    }

    // 在 i 元素处插入元素 t
    public void insert(int i, T t) {
        if (N == eles.length) {
            resize(2 * eles.length);
        }
        // 先把 i 索引处的元素及其后面的元素依次向后移动一位
        for (int index = N; index > i; index--) {
            eles[index] = eles[index - 1];
        }
        // 再把 t 元素放到 i 索引处即可
        eles[i] = t;

        // 元素个数 + 1
        N++;
    }

    // 删除指定位置 i 处的元素，并返回该元素
    public T remove(int i) {
        // 记录索引 i 处的值
        T current = eles[i];
        // 索引 i 后面元素依次向前移动一位即可
        for (int index = i; index < N - 1; index++) {
            eles[index] = eles[index + 1];
        }
        // 元素个数 - 1
        N--;

        if (N < eles.length / 4) {
            resize(eles.length / 2);
        }
        return current;
    }

    // 查找 t 元素第一次出现的位置
    public int indexOf(T t) {
        for (int i = 0; i < N; i++) {
            if (eles[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    // 根据参数 newSize，重置 eles 的大小
    public void resize(int newSize) {
        // 定义一个临时数组，指向原数组
        T[] temp = eles;
        // 创建新数组
        eles = (T[]) new Object[newSize];
        // 把原数组的数据拷贝到新数组即可
        for (int i = 0; i < N; i++) {
            eles[i] = temp[i];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator {
        private int cursor;

        public SIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < N;
        }

        @Override
        public Object next() {
            return eles[cursor++];
        }
    }
}
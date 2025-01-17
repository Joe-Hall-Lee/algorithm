package cn.itcast.algorithm.uf;

public class UF {
    // 记录结点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    // 记录并查集中数据的分组个数
    private int count;

    // 初始化并查集
    public UF(int N) {
        // 初始化分组的数量，默认情况下，有 N 个分组
        this.count = N;

        // 初始化 eleAndGroup 数组
        this.eleAndGroup = new int[N];

        // 初始化 eleAndGroup 中的元素及其所在的组的标识符，让 eleAndGroup 数组的索引作为并查集的每个结点的元素，并且让每个索引处的值（该元素所在的组的标识符）就是该索引
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }

    // 获取当前并查集中的数据有多少个分组
    public int count() {
        return count;
    }

    // 元素 p 所在分组的标识符
    public int find(int p) {
        return eleAndGroup[p];
    }

    // 判断并查集中元素 p 和元素 q 是否在同一分组中
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 把 p 元素所在分组和 q 元素所在分组合并
    public void union(int p, int q) {
        // 判断元素 q 和 p 元素是否已经在同一分组中，如果已经在同一分组中，则结束方法就可以了
        if (connected(p, q)) {
            return;
        }
        // 获取 p 所在分组的标识符
        int pGroup = find(p);
        // 获取 q 所在分组的标识符
        int qGroup = find(q);

        if (pGroup == qGroup) {
            return;
        }
        // 合并组：让 p 所在组的所有元素的组标识符变为 q 所在分组的标识符
        for (int i = 0; i < eleAndGroup.length; i++) {
            if (eleAndGroup[i] == pGroup) {
                eleAndGroup[i] = qGroup;
            }
        }
        // 分组个数 - 1
        count--;
    }
}

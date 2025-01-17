package cn.itcast.algorithm.uf;

public class UF_Tree_Weighted {
    // 记录结点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    // 记录并查集中数据的分组个数
    private int count;

    // 用来存储每一个根结点对应的树中保存的结点的个数
    private int[] sz;

    // 初始化并查集
    public UF_Tree_Weighted(int N) {
        // 初始化分组的数量，默认情况下，有 N 个分组
        this.count = N;

        // 初始化 eleAndGroup 数组
        this.eleAndGroup = new int[N];

        // 初始化 eleAndGroup 中的元素及其所在的组的标识符，让 eleAndGroup 数组的索引作为并查集的每个结点的元素，并且让每个索引处的值（该元素所在的组的标识符）就是该索引
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }

        this.sz = new int[N];
        // 默认情况下，sz 中每个索引处的值都是 1
    }

    // 获取当前并查集中的数据有多少个分组
    public int count() {
        return count;
    }

    // 元素 p 所在分组的标识符
    public int find(int p) {
        while (true){
            if (eleAndGroup[p] == p) {
                return p;
            }
            p = eleAndGroup[p];
        }
    }

    // 判断并查集中元素 p 和元素 q 是否在同一分组中
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 把 p 元素所在分组和 q 元素所在分组合并
    public void union(int p, int q) {
        // 找到 p 元素和 q 元素所在组对应的树的根结点
        int pRoot = find(p);
        int qRoot = find(q);
        // 如果 p 和 q 已经在同一分组，则不需要合并了
        if (pRoot == qRoot) {
            return;
        }

        // 判断 root 对应的树大还是 qroot 对应的树大，最终需要把较小的树合并到较大的树中
        if (sz[pRoot] < sz[qRoot]) {
            eleAndGroup[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            eleAndGroup[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        // 组的数量 - 1
        this.count--;
    }
}

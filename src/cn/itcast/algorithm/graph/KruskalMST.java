package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue;
import cn.itcast.algorithm.priority.MinPriorityQueue;
import cn.itcast.algorithm.uf.UF_Tree_Weighted;


public class KruskalMST {
    // 保存最小生成树的所有边
    private Queue<Edge> mst;
    // 索引代表顶点，使用 uf.connected(v, w) 可以判断顶点 v 和顶点 w 是否在同一棵树中，使用 uf.union(v, w) 可以把顶点 v 所在的树和顶点 w 所在的树合并
    private UF_Tree_Weighted uf;
    // 存储图中所有的边，使用最小优先队列，对边按照权重进行排序
    private MinPriorityQueue<Edge> pq;

    // 根据一副加权无向图，创建最小生成树计算对象
    public KruskalMST(EdgeWeightedGraph G) {
        // 初始化 mst
        this.mst = new Queue<Edge>();
        // 初始化 uf
        this.uf = new UF_Tree_Weighted(G.V());
        // 初始化 pq
        this.pq = new MinPriorityQueue<>(G.E() + 1);
        // 把图中所有的边存储到 pq 中
        for (Edge e : G.edges()) {
            pq.insert(e);
        }


        // 遍历 pq 队列，拿到最小权重的边，进行处理
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // 拿到权重最小的边
            Edge e = pq.delMin();
            // 找到该边的两个顶点
            int v = e.either();
            int w = e.other(v);
            // 判断这两个顶点是否已经在同一棵树中，如果在同一棵树中，则不对该边做处理，如果不在一棵树中，则让这两个顶点属于的两棵树合并成一棵树
            if (uf.connected(v, w)) {
                continue;
            }
            uf.union(v, w);

            // 让边 v 进入 mst 队列中
            mst.enqueue(e);
        }
    }

    // 获取最小生成树的所有边
    public Queue<Edge> edges() {
        return mst;
    }
}

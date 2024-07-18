package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue;
import cn.itcast.algorithm.priority.IndexMinPriorityQueue;

public class PrimMST {
    // 索引代表顶点，值代表当前顶点和最小生成树之间的最短边
    private Edge[] edgeTo;
    // 索引代表顶点，值代表当前顶点和最小生成树之间的最短边的权重
    private double[] distTo;
    // 索引代表顶点，如果当前顶点已经在树中，则值为 true，否则为 false
    private boolean[] marked;
    // 存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    // 根据一副加权无向图，创建最小生成树计算对象
    public PrimMST(EdgeWeightedGraph G) {
        // 初始化 edgeTo
        this.edgeTo = new Edge[G.V()];

        // 初始化 distTo
        this.distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        // 初始化 marked
        this.marked = new boolean[G.V()];

        // 初始化 pq
        pq = new IndexMinPriorityQueue<Double>(G.V());

        // 默认让顶点 0 进入到树中，但是树中只有一个顶点，因此，0 顶点默认没有和其他的顶点相连，所以让 distTo 对应位置处的值存储 0.0
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        // 遍历索引最小优先队列，拿到最小横切边对应的顶点，把该顶点加入最小生成树中
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    // 将顶点 v 添加到最小生成树中，并且更新数据
    private void visit(EdgeWeightedGraph G, int v) {
        // 把顶点 v 添加到最小生成树中
        marked[v] = true;
        // 更新数据
        for (Edge e : G.adj(v)) {
            // 获取 e 边的另外一个顶点（当前顶点是 v）
            int w = e.other(v);
            // 判断另外一个顶点是不是已经在树中，如果在树中，则不做任何处理，如果不在树中，则更新数据
            if (marked[w]) {
                continue;
            }

            // 判断边 e 的权重是否小于从 w 顶点到树中已经存在的最小边的权重
            if (e.weight() < distTo[w]) {
                // 更新数据
                edgeTo[w] = e;
                distTo[w] = e.weight();

                if (pq.contains(w)) {
                    pq.changeItem(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    // 获取最小生成树的所有边
    public Queue<Edge> edges() {
        // 创建队列对象
        Queue<Edge> queue = new Queue<Edge>();
        // 遍历 edgeTo 数组，获取最小生成树中所有的边
        for (int i = 0; i < edgeTo.length; i++) {
            if (edgeTo[i] != null) {
                queue.enqueue(edgeTo[i]);
            }
        }
        return queue;
    }
}

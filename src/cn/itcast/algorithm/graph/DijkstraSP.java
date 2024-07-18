package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue;
import cn.itcast.algorithm.priority.IndexMinPriorityQueue;

public class DijkstraSP {
    // 索引代表顶点，值表示从顶点 s 到当前顶点的最短路径上的最后一条边
    private DirectedEdge[] edgeTo;
    // 索引代表顶点，确认顶点 s 到当前顶点的最短路径的总权重
    private double[] distTo;
    // 存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    // 根据一副加权 有向图 G 和顶点 s，创建一个计算顶点为 s 的最短路径树对象
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        // 初始化 edgeTo
        this.edgeTo = new DirectedEdge[G.V()];
        // 初始化 distTo
        this.distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        // 初始化 pq
        this.pq = new IndexMinPriorityQueue<Double>(G.V());

        // 找到图 G 中从顶点 s 为起点的最短路径树

        // 默认让顶点 s 进入到最短路径树中
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        // 遍历 pq
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    // 松弛图 G 中的顶点 v
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edge : G.adj(v)) {
            // 获取到该边的终点 w
            int w = edge.to();

            // 通过松弛技术，判断从结点 s 到 顶点 w 的最短路径是否需要先从顶点 s 到顶点 v，然后再由顶点 v 到顶点 w
            if (distTo(v) + edge.weight() < distTo(w)) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
                // 判断 pq 中是否已经存在顶点 w，如果存在，则更新权重，如果不存在，则直接添加
                if (!pq.contains(w)) {
                    pq.insert(w, distTo[w]);
                } else {
                    // 如果 w 已经在 pq 中，则更新 w 的优先级
                    pq.changeItem(w, distTo[w]);
                }
            }
        }
    }

    // 获取从顶点 s 到顶点 v 的最短路径的总权重
    public double distTo(int v) {
        return distTo[v];
    }

    // 判断从顶点 s 到顶点 v 是否可达
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // 查询从起点 s 到顶点 v 的最短路径中所有的点
    public Queue<DirectedEdge> pathTo(int v) {
        // 判断从顶点 s 到顶点 v 是否可达，如果不可达，则返回 null
        if (!hasPathTo(v)) {
            return null;
        }

        // 创建队列对象
        Queue<DirectedEdge> allEdges = new Queue<DirectedEdge>();

        while (true) {
            DirectedEdge e = edgeTo[v];
            if (e == null) {
                break;
            }
            allEdges.enqueue(e);
            v = e.from();
        }
        return allEdges;
    }
}

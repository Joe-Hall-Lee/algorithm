package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue;

public class Graph {
    // 顶点数目
    private final int V;
    // 边的数目
    private int E;
    // 邻接表
    private Queue<Integer>[] adj;

    public Graph(int V) {
        // 初始化顶点数量
        this.V = V;
        // 初始化边的数量
        this.E = 0;
        // 初始化邻接表
        this.adj = new Queue[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new Queue<Integer>();
        }

    }

    // 获取顶点数目
    public int V() {
        return V;
    }

    // 获取边的数目
    public int E() {
        return E;
    }

    // 向图中添加一条边 v-w
    public void addEdge(int v, int w) {
        // 在无向图中，边是没有方向的，所以该边既可以是 v 到 w 的边，又可以说是 w 到 v 的边，因此，需要让 w 出现在 v 的邻接表中，并且还要让 v 出现在 w 的邻接表中
        adj[v].enqueue(w);
        adj[w].enqueue(v);
        // 边的数量 + 1
        E++;

    }

    // 获取和顶点 v 相连的所有顶点
    public Queue<Integer> adj(int v) {
        return adj[v];
    }
}

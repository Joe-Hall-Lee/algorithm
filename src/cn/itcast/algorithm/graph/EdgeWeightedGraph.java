package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue;

public class EdgeWeightedGraph {
    // 顶点总数
    private final int V;
    // 边总数
    private int E;
    // 邻接表
    private Queue<Edge>[] adj;

    // 创建一个含有 V 个顶点的空加权无向图
    public EdgeWeightedGraph(int V) {
        // 初始化顶点数量
        this.V = V;
        // 初始化边的数量
        this.E = 0;

        adj = new Queue[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Edge>();
        }
    }

    // 获取图中顶点的数量
    public int V() {
        return V;
    }

    // 获取图中边的数量
    public int E() {
        return E;
    }

    // 向加权无向图中添加一条边 e
    public void addEdge(Edge e) {
        // 需要让边 e 同时出现在 e 这两个边的两个顶点的邻接表中
        int v = e.either();
        int w = e.other(v);

        adj[v].enqueue(e);
        adj[w].enqueue(e);

        // 边的数量 + 1
        E++;
    }

    // 获取和顶点 V 关联的所有边
    public Queue<Edge> adj(int v) {
        return adj[v];
    }

    // 获取加权无向图的所有边
    public Queue<Edge> edges() {
        // 创建一个队列对象，存储所有的边
        Queue<Edge> allEdges = new Queue<>();

        // 遍历图中的每一个顶点，找到该顶点的邻接表，邻接表中存储了该顶点关联的每一条边
        // 因为这是无向图，所以同一条边同时出现在了它关联的两个顶点的邻接表中，需要让一条边只记录一次
        for (int v = 0; v < V; v++) {

            // 遍历 v 顶点的邻接表，找到每一条和 v 关联的边
            for (Edge e : adj(v)) {
                if (e.other(v) < v) {
                    allEdges.enqueue(e);
                }
            }
        }
        return allEdges;
    }
}

package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue;

public class BreadthFirstSearch {
    // 索引代表顶点，值表示该顶点是否被访问
    private boolean[] marked;
    // 记录有多少个顶点与 s 顶点相通
    private int count;
    // 用来存储待搜索邻接表的点
    private Queue<Integer> waitSearch;

    // 构造广度优先搜索对象，使用广度优先搜索找出 G 图中 s 顶点的所有相邻顶点
    public BreadthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.count = 0;
        waitSearch = new Queue<Integer>();

        bfs(G, s);
    }

    // 使用广度优先搜索找出 G 图中 s 顶点的所有相邻顶点
    private void bfs(Graph G, int v) {
        // 把当前顶点 v 标识为已搜索
        marked[v] = true;

        // 让顶点 v 进入队列，待搜索
        waitSearch.enqueue(v);
        // 让相通的顶点 + 1
        count++;
        // 通过循环，如果队列不为空，则从队列中取出一个待搜索的顶点进行搜索
        while (!waitSearch.isEmpty()) {
            Integer wait = waitSearch.dequeue();
            for (Integer w : G.adj(wait)) {
                if (!marked[w]) {
                    // 标记该顶点为已搜索
                    marked[w] = true;
                    // 将新扩展到的顶点放入 waitSearch 队列中
                    waitSearch.enqueue(w);
                    // 让相通的顶点 + 1
                    count++;
                }
            }
        }

    }

    // 判断 w 顶点是否与 s 顶点相通
    public boolean marked(int w) {
        return marked[w];
    }

    // 返回与顶点 s 相通的顶点的总数
    public int count() {
        return count;
    }
}

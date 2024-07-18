package cn.itcast.algorithm.graph;

public class DepthFirstSearch {
    // 索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    // 记录有多少个顶点与 s 顶点连通
    private int count;

    // 构造深度优先搜索对象，使用深度优先搜索找出 G 图中与 s 顶点的所有相邻顶点
    public DepthFirstSearch(Graph G, int s) {
        // 初始化 marked 数组
        this.marked = new boolean[G.V()];
        // 统计连通顶点的个数
        this.count = 0;
        // 使用深度优先搜索找出 G 图中与 s 顶点的所有相邻顶点
        dfs(G, s);
    }

    // 使用深度优先搜索找出 G 图中与 s 顶点的所有相邻顶点
    private void dfs(Graph G, int v) {
        // 把 v 顶点标识为已搜索
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            // 判断当前 k 结点有没有被搜索过，如果没有被搜索过，则递归调用 dfs 方法进行深度搜索
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        // 相通顶点数量 + 1
        count++;
    }

    // 判断 w 顶点是否与 s 顶点连通
    public boolean marked(int w) {
        return marked[w];
    }

    // 获取与顶点 s 相通的所有顶点的总数
    public int count() {
        return count;
    }
}

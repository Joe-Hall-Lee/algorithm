package cn.itcast.algorithm.test;

import cn.itcast.algorithm.graph.DepthFirstSearch;
import cn.itcast.algorithm.graph.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Traffic_Project_Test2 {
    public static void main(String[] args) throws Exception {
        // 构建一个缓冲读取流 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(Traffic_Project_Test2.class.getClassLoader().getResourceAsStream("traffic_project.txt")));

        // 读取第一行数据 20
        int totalNumber = Integer.parseInt(br.readLine());

        // 构建一个 Graph 对象
        Graph g = new Graph(totalNumber);

        // 读取第二行数据 7
        int roadNumber = Integer.parseInt(br.readLine());
        // 循环读取有限次（7），读取已经修建好的道路
        for (int i = 0; i < roadNumber; i++) {
            String road = br.readLine(); // "0 1"
            String[] str = road.split(" ");
            int v = Integer.parseInt(str[0]);
            int w = Integer.parseInt(str[1]);
            // 调用图的 addEdge 方法，把边添加到图中，表示已经修建好的道路
            g.addEdge(v, w);
        }

        // 构建一个深度优先搜索对象，起点设置为 9
        DepthFirstSearch search = new DepthFirstSearch(g, 9);

        // 调用 marked 方法，判断 8 顶点和 10 顶点是否与顶点 9 相同
        System.out.println("顶点 8 和顶点 9 是否相通：" + search.marked(8));
        System.out.println("顶点 10 和顶点 9 是否相通：" + search.marked(10));

    }
}

package cn.itcast.algorithm.test;

import cn.itcast.algorithm.graph.DepthFirstPaths;
import cn.itcast.algorithm.graph.Graph;
import cn.itcast.algorithm.linear.Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DepthFirstPathsTest {
    public static void main(String[] args) throws Exception {
        // 构建一个缓冲读取流 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(DepthFirstPathsTest.class.getClassLoader().getResourceAsStream("road_find.txt")));

        // 读取第一行数据 6
        int totalNumber = Integer.parseInt(br.readLine());

        // 根据第一行数据构建一幅图 Graph
        Graph g = new Graph(totalNumber);

        // 读取第二行数据 8
        int edgeNumbers = Integer.parseInt(br.readLine());

        // 继续通过循环读取每一条边关联的两个顶点，调用 addEdge 方法添加边
        for (int i = 0; i < edgeNumbers; i++) {
            String edge = br.readLine(); // 0 1
            String[] str = edge.split(" ");
            int v = Integer.parseInt(str[0]);
            int w = Integer.parseInt(str[1]);

            g.addEdge(v, w);
        }

        // 构建路径查找对象，并设置起点为 0
        DepthFirstPaths dfs = new DepthFirstPaths(g, 0);

        // 调用 pathTo(4)，找到从起点 0 到终点 4 的路径，返回 Stack
        Stack<Integer> path = dfs.pathTo(4);
        StringBuilder sb = new StringBuilder();

        // 遍历栈对象
        for (Integer v : path) {
            sb.append(v + "-");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}

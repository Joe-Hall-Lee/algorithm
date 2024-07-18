package cn.itcast.algorithm.test;

import cn.itcast.algorithm.uf.UF_Tree_Weighted;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Traffic_Project_Test {
    public static void main(String[] args) throws Exception {
        // 构建一个缓冲读取流 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(Traffic_Project_Test.class.getClassLoader().getResourceAsStream("traffic_project.txt")));

        // 读取第一行数据 20
        int totalNumber = Integer.parseInt(br.readLine());

        // 构建一个并查集对象
        UF_Tree_Weighted uf = new UF_Tree_Weighted(totalNumber);

        // 读取第二行数据 7
        int roadNumber = Integer.parseInt(br.readLine());

        // 循环读取 7 条数据
        for (int i = 1; i <= roadNumber; i++) {
            String line = br.readLine(); // 0 1
            String[] str = line.split(" ");
            int p = Integer.parseInt(str[0]);
            int q = Integer.parseInt(str[1]);
            // 调用并查集对象的 union 方法让两个城市相通
            uf.union(p, q);
        }

        // 获取当前并查集中分组的数量 - 1 就可以得到还需要修建的道路的数目
        int result = uf.count() - 1;
        System.out.println("还需要修建 " + result + " 条道路，才能实现畅通工程");
    }
}

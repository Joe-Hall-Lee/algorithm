package cn.itcast.algorithm.test;

import cn.itcast.algorithm.sort.Student;

// 2．定义测试类 Test，在测试类 Test 中定义测试方法 Comparable getMax(Comparable c1, Comparable c2) 完成测试
public class TestComparable {

    public static void main(String[] args) {
        // 创建两个 Student 对象，并调用 getMax 方法，完成测试
        Student s1 = new Student();
        s1.setUsername("张三");
        s1.setAge(18);

        Student s2 = new Student();
        s2.setUsername("李四");
        s2.setAge(20);

        Comparable max = getMax(s1, s2);
        System.out.println(max);
    }

    public static Comparable getMax(Comparable c1, Comparable c2) {
        int result = c1.compareTo(c2);
        // 如果 result < 0，则 c1 比 c2 小；
        // 如果 result > 0，则 c1 比 c2 大；
        // 如果 result == 0，则 c1 和 c2 一样大；
        if (result >= 0) {
            return c1;
        } else {
            return c2;
        }
    }
}
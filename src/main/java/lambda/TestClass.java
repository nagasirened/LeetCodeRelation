package lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: LeetCode Study
 * @description:
 * @author: ZengGuangfu
 * @create 2019-08-08 15:44
 */
public class TestClass {


    public static Monitory monitory = (name, pwd ) -> System.out.println("hello " + name);

    public static void main(String[] args) {
        monitory.fengyin("zengg","huangyu");

        // 测试分组
        Student zengg = new Student("zengg", "123" ,26);
        Student huangyu = new Student("huangyu", "456" , 22);
        Student zengqian = new Student("zengqian", "456" , 22);
        ArrayList<Student> list = new ArrayList<>();
        list.add(zengg);
        list.add(huangyu);
        list.add(zengqian);


/*
        Map<String, List<Student>> collect = list.stream().collect(Collectors.groupingBy(Student::getPwd));
        collect.keySet().forEach(key -> System.out.println(key));
*/

        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //List<Integer> integers = numList.stream().limit(5).sorted().collect(Collectors.toList());
        List<Integer> integers = numList.stream().filter( i -> (i&1) == 1).collect(Collectors.toList());


        numList.stream().filter( i -> (i&2) == 0).collect(Collectors.toList());
        /*System.out.println("测试map()映射 start ");
        List<Integer> collect = numList.stream().map(s -> s + 1).limit(5).collect(Collectors.toList());
        collect.forEach(s -> System.out.print(s + "  |  "));
        System.out.println();

        List<String> strings = list.stream().map(Student::getName).collect(Collectors.toList());
        strings.forEach(str -> System.out.print(str + "   "));
        System.out.println();
        System.out.println("测试map()映射 end ");*/

        /*Map<Integer, List<Student>> listMap = list.stream().collect(Collectors.groupingBy(Student::getAge));
        listMap.keySet().forEach(q->{
            System.out.println(q + "   "  + listMap.get(q));
        });*/

        /*String str = list.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(str);   //  zengg,huangyu,zengqian   拼接成功
        IntSummaryStatistics statistics = list.stream().map(Student::getAge).collect(Collectors.summarizingInt(x -> x));
        System.out.println(statistics.getSum());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());*/


        list.stream().mapToInt(Student::getAge).sum();
    }

    @FunctionalInterface
    interface Monitory{
        void fengyin(String name ,String password);
    }
}

class Student{
    private String name;
    private String pwd;
    private Integer age;

    public Student() {
    }

    public Student(String name, String pwd , Integer age) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

package other.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * author: ZGF
 * 08-2020/8/19 : 13:19
 * context :
 */

public class TestLambda {

    static List<Node> nodeList = new ArrayList<Node>(){
        {
            add(new Node("pen", 4, "red"));
            add(new Node("pencil", 2, "green"));
            add(new Node("lnk", 6, "black"));
            add(new Node("workbooks", 5, "white"));
        }
    };

    public static void compare(Integer asd, Consumer<Integer> consumer){
        consumer.accept(asd);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 1.想看看购物车有什么商品
         * 2.绿色的有什么
         * 3.两个最贵的
         * 4.只需要两件最贵的名称和总价
         */
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<String> collect = nodeList.stream().filter(item -> !"green".equals(item.getColor()))
                // 默认从小到大，翻转一下
                .sorted(Comparator.comparing(Node::getPrice).reversed())
                .limit(2)
                .peek(item -> atomicInteger.getAndAdd(item.getPrice()))
                .map(Node::getName)
                .collect(Collectors.toList());
        for (String name : collect) {
            System.out.println(name);
        }
        System.out.println(atomicInteger.get());
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Node{

    private String name;

    private Integer price;

    private String color;
}

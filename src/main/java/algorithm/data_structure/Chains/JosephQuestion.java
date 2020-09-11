package algorithm.data_structure.Chains;

import lombok.Data;

/**
 * author: ZGF
 * 08-2020/8/11 : 11:43
 * context : 约瑟夫问题
 * https://www.jianshu.com/writer#/notebooks/45689725/notes/75618302
 */

public class JosephQuestion {

    // 辅助节点
    private Node first = null;

    /**
     * 添加节点的方法，参数代表要添加几个节点，默认编号从1开始往后依次递增
     */
    public void addNodes(int nums){
        if (nums < 1) {
            System.out.println("参数不符合要求");
            return;
        }
        Node currentNode = null;   // 一个辅助指针，动态指代最后一个添加的用户
        for (int i = 1; i <= nums ; i++) {
            Node node = new Node(i);
            // 特殊情况 i==1 的时候，加入第一个node
            if (i == 1){
                first = node;
                first.setNext(first);
                currentNode = first;
            } else {
                currentNode.setNext(node);
                node.setNext(first);
                currentNode = node;
            }
        }
    }

    /**
     * 展示全部节点
     */
    public void showAll(){
        if (first == null){
            System.out.println("没有任何节点");
        }
        Node currentNode = first;
        while (true) {
            System.out.println("当前节点编号：" + currentNode.getNo());
            if (currentNode.getNext() == first){
                break;
            }
            currentNode = currentNode.getNext();
        }
    }

    /**
     * m个人围成一个圈，指定一个数字n,从第一个人开始报数，每轮报到n的选手出局，由下一个人接着从头开始报，最后一个人是赢家。其中m>1，n>2
     * m 表示总节点数   n表示步进   q表示从第几个节点开始数
     *
     * 步进，按照从4号节点开始数，每3个数弹出一个节点，输出的编号应该是1-->4-->3-->5-->2
     * 直到只有最后一个节点为止
     */
    public void pop(Integer m, Integer n, Integer q){
        if (m < 2 || n < 3 || q > m) {
            System.out.println("参数不符合要求");
            return;
        }
        // 先创建对象
        addNodes(m);
        /**
         * 建立一个重要的指针helper，辅助弹出功能
         * first 当前计数的节点，helper则指向first的上一个节点
         * （first和helper指针同时前移的时候，first数到对应的数字n,helper还是到它的前一个。弹出first后，first前移以为，helper的next指向first，就弹出了对应的那个节点）
         */
        // 先让helper去它该去的位置（first的前一个）
        Node helper = first;
        while (true) {
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        // 开始前，先让first到q的位置
        for (int i = 0; i < q - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {
            if (first == helper){  // 只有一个了
                System.out.println("最后节点：" + first.getNo());
                break;
            }
            // 每次步进 n - 1次
            for (int i = 0; i < n - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("弹出节点：" + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
    }

    public static void main(String[] args) {
        JosephQuestion josephQuestion = new JosephQuestion();
        josephQuestion.pop(5, 3, 4);
    }
}


@Data
class Node{

    /**
     * 编号
     */
    private Integer no;

    /**
     * 下一个节点
     */
    private Node next;

    Node(Integer no){
        this.no = no;
    }
}